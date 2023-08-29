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
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorkerInformationNotifier {

    public static final String HEADER_X_KF_DELAY_WINDOW = "X-KF-DELAY-WINDOW";

    private static final Logger LOGGER = LoggerFactory.getLogger(WorkerInformationNotifier.class);

    private final AtomicBoolean running = new AtomicBoolean(false);

    private final WorkerInformationNotifierTask task;

    private final ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();

    private final WorkerInformationNotifierConfiguration configuration;
    private ScheduledFuture<?> scheduledFuture;

    private Duration scheduledDelay;

    private Duration delayWindow = Duration.ofMinutes(5);

    private int consecutiveFailures = 0;

    public WorkerInformationNotifier(
        KuFlowRestClient kuFlowRestClient,
        WorkflowClientOptions workflowClientOptions,
        WorkerInformationNotifierConfiguration configuration,
        List<WorkerInformation> workerInformationList
    ) {
        this.task = new WorkerInformationNotifierTask(this, kuFlowRestClient, workflowClientOptions, workerInformationList);
        this.configuration = configuration;
    }

    public synchronized void start() {
        if (this.running.get()) {
            return;
        }

        this.task.run();
        this.running.set(true);
        this.scheduleTask();
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

    protected void notificationSuccess(@Nullable Duration delayWindow) {
        if (delayWindow != null) {
            this.delayWindow = delayWindow;
        }
        this.consecutiveFailures = 0;
        this.scheduleTask();
    }

    protected void notificationError() {
        this.consecutiveFailures++;
        this.scheduleTask();
    }

    private void scheduleTask() {
        if (!this.running.get()) {
            return;
        }

        if (this.scheduledFuture != null) {
            this.scheduledFuture.cancel(false);
        }

        long delay = this.delayWindow.toMillis();
        if (this.consecutiveFailures > 0) {
            delay =
                Math.round(
                    Math.min(
                        delay,
                        this.configuration.getBackoffSleep().toMillis() *
                        Math.pow(this.configuration.getBackoffExponentialRate(), this.consecutiveFailures)
                    )
                );
        }

        Duration delayDuration = Duration.ofMillis(delay);

        if (this.scheduledDelay == null || !this.scheduledDelay.equals(delayDuration)) {
            this.scheduledDelay = delayDuration;
            this.scheduledFuture = this.scheduledExecutorService.scheduleAtFixedRate(this.task, delay, delay, TimeUnit.MILLISECONDS);
        }
    }

    private static class WorkerInformationNotifierTask implements Runnable {

        private final WorkerInformationNotifier workerInformationNotifier;

        private final KuFlowRestClient kuFlowRestClient;

        private final WorkflowClientOptions workflowClientOptions;

        private final List<WorkerInformation> workerInformationList;

        private WorkerInformationNotifierTask(
            WorkerInformationNotifier workerInformationNotifier,
            KuFlowRestClient kuFlowRestClient,
            WorkflowClientOptions workflowClientOptions,
            List<WorkerInformation> workerInformationList
        ) {
            this.workerInformationNotifier = workerInformationNotifier;
            this.kuFlowRestClient = kuFlowRestClient;
            this.workflowClientOptions = workflowClientOptions;
            this.workerInformationList = workerInformationList;
        }

        @Override
        public void run() {
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

                HttpHeader delayWindowHeader = workerRestResponse.getHeaders().get(HttpHeaderName.fromString(HEADER_X_KF_DELAY_WINDOW));
                Duration delayWindow = null;
                if (delayWindowHeader != null) {
                    delayWindow = Duration.ofSeconds(Long.parseLong(delayWindowHeader.getValue()));
                }
                this.workerInformationNotifier.notificationSuccess(delayWindow);
            } catch (Exception e) {
                LOGGER.error("There are some problems registering worker {}/{}", workerInformation.getTaskQueue(), workerIdentity, e);
                this.workerInformationNotifier.notificationError();
                throw e;
            }
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
}
