/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.temporal.common.connection;

import com.azure.core.http.HttpHeader;
import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.Response;
import com.azure.core.util.Context;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.Worker;
import com.kuflow.rest.operation.WorkerOperations;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import io.temporal.client.WorkflowClientOptions;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerInformationNotifier {

    public static final String HEADER_X_KF_DELAY_WINDOW = "X-KF-DELAY-WINDOW";

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerInformationNotifier.class);

    private final AtomicBoolean running = new AtomicBoolean(false);

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private final WorkerInformationNotifierConfiguration configuration;

    private final List<WorkerInformation> workerInformationList;

    private final WorkflowClientOptions workflowClientOptions;

    private final KuFlowRestClient kuFlowRestClient;

    private ScheduledFuture<?> scheduleCreateOrUpdateWorkerFuture;

    private Duration scheduleCreateOrUpdateWorkerDelay;

    private Duration delayWindow = Duration.ofMinutes(5);

    private int consecutiveFailures = 0;

    public WorkerInformationNotifier(
        KuFlowRestClient kuFlowRestClient,
        WorkflowClientOptions workflowClientOptions,
        WorkerInformationNotifierConfiguration configuration,
        List<WorkerInformation> workerInformationList
    ) {
        this.configuration = configuration;
        this.kuFlowRestClient = kuFlowRestClient;
        this.workflowClientOptions = workflowClientOptions;
        this.workerInformationList = workerInformationList;
    }

    public synchronized void start() {
        if (this.running.get()) {
            return;
        }

        this.crearOrUpdateWorkers();
        this.scheduleCreateOrUpdateWorkers();

        this.running.set(true);
    }

    public synchronized void shutdown() {
        if (!this.running.get()) {
            return;
        }

        try {
            this.scheduledExecutorService.shutdown();
            boolean terminated = this.scheduledExecutorService.awaitTermination(1, TimeUnit.MINUTES);
            if (!terminated) {
                LOGGER.warn("Worker took more than 1 minute to terminate");
            }
            this.running.set(false);
        } catch (InterruptedException e) {
            LOGGER.error("Error stopping the worker", e);
        }
    }

    private void crearOrUpdateWorkers() {
        InetAddress localHostInetAddress = this.getLocalHostInetAddress();
        this.workerInformationList.forEach(workerInformation -> this.crearOrUpdateWorker(workerInformation, localHostInetAddress));
    }

    private void crearOrUpdateWorker(WorkerInformation workerInformation, InetAddress localHostInetAddress) {
        String workerIdentity = this.workflowClientOptions.getIdentity();

        try {
            com.kuflow.rest.model.Worker workerRest = new com.kuflow.rest.model.Worker();
            workerRest.setIdentity(workerIdentity);
            workerRest.setIp(localHostInetAddress.getHostAddress());
            workerRest.setHostname(localHostInetAddress.getHostName());
            workerRest.setTaskQueue(workerInformation.getTaskQueue());
            workerRest.setWorkflowTypes(this.copyOf(workerInformation.getWorkflowTypes()));
            workerRest.setActivityTypes(this.copyOf(workerInformation.getActivityTypes()));

            WorkerOperations workerOperations = this.kuFlowRestClient.getWorkerOperations();
            Response<Worker> workerRestResponse = workerOperations.createWorkerWithResponse(workerRest, Context.NONE);

            LOGGER.info(
                "Registered worker {}/{} with id {}",
                workerInformation.getTaskQueue(),
                workerIdentity,
                workerRestResponse.getValue().getId()
            );
            this.consecutiveFailures = 0;

            HttpHeader delayWindowHeader = workerRestResponse.getHeaders().get(HttpHeaderName.fromString(HEADER_X_KF_DELAY_WINDOW));
            if (delayWindowHeader != null) {
                this.delayWindow = Duration.ofSeconds(Long.parseLong(delayWindowHeader.getValue()));
            }
        } catch (Exception e) {
            LOGGER.error("There are some problems registering worker {}/{}", workerInformation.getTaskQueue(), workerIdentity, e);
            this.consecutiveFailures++;
        }
    }

    private void scheduleCreateOrUpdateWorkers() {
        long delay = this.delayWindow.toSeconds();
        if (this.consecutiveFailures > 0) {
            delay =
                Math.round(
                    Math.min(
                        delay,
                        this.configuration.getBackoffSleep().toSeconds() *
                        Math.pow(this.configuration.getBackoffExponentialRate(), this.consecutiveFailures)
                    )
                );
        }

        Duration delayDuration = Duration.ofSeconds(delay);

        if (delayDuration.equals(this.scheduleCreateOrUpdateWorkerDelay)) {
            return;
        }

        this.scheduleCreateOrUpdateWorkerDelay = delayDuration;
        if (this.scheduleCreateOrUpdateWorkerFuture != null) {
            this.scheduleCreateOrUpdateWorkerFuture.cancel(false);
        }
        this.scheduleCreateOrUpdateWorkerFuture =
            this.scheduledExecutorService.scheduleAtFixedRate(
                    () -> {
                        this.crearOrUpdateWorkers();
                        this.scheduleCreateOrUpdateWorkers();
                    },
                    delay,
                    delay,
                    TimeUnit.SECONDS
                );
    }

    private <E> List<E> copyOf(Collection<? extends E> coll) {
        if (coll == null || coll.isEmpty()) {
            return null;
        }

        return List.copyOf(coll);
    }

    private InetAddress getLocalHostInetAddress() {
        try {
            return InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new KuFlowTemporalException("Unable to resolve local address", e);
        }
    }
}
