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

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.toCollection;
import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableList;
import static java.util.stream.Collectors.toUnmodifiableSet;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.temporal.common.authorization.KuFlowAuthorizationTokenSupplier;
import com.kuflow.temporal.common.connection.WorkerBuilder.ActivityImplementationRegister;
import com.kuflow.temporal.common.connection.WorkerBuilder.WorkflowImplementationRegister;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.common.tracing.MDCContextPropagator;
import io.temporal.authorization.AuthorizationGrpcMetadataProvider;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.common.converter.DataConverter;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.common.converter.JacksonJsonPayloadConverter;
import io.temporal.common.converter.PayloadConverter;
import io.temporal.common.metadata.POJOActivityImplMetadata;
import io.temporal.common.metadata.POJOActivityMethodMetadata;
import io.temporal.common.metadata.POJOWorkflowImplMetadata;
import io.temporal.common.metadata.POJOWorkflowMethodMetadata;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * Configure a temporal client and worker with KuFlow requirements.
 */
public class KuFlowTemporalConnection {

    public static KuFlowTemporalConnection instance(KuFlowRestClient kuFlowRestClient) {
        return new KuFlowTemporalConnection(kuFlowRestClient);
    }

    private final KuFlowRestClient kuFlowRestClient;

    private final WorkflowServiceStubsOptions.Builder workflowServiceStubsBuilder = WorkflowServiceStubsOptions.newBuilder();

    private final WorkflowClientOptions.Builder workflowClientBuilder = WorkflowClientOptions.newBuilder();

    private final List<WorkerBuilder> workersBuilder = new LinkedList<>();

    private final List<WorkerInfo> workersInfo = new LinkedList<>();

    private WorkflowServiceStubs workflowServiceStubs;

    private WorkflowClient workflowClient;

    private WorkerFactory workerFactory;

    /**
     * Hold all the temporal connection objects.
     *
     * @param kuFlowRestClient rest client to connect to KuFlow service.
     */
    public KuFlowTemporalConnection(KuFlowRestClient kuFlowRestClient) {
        this.kuFlowRestClient = kuFlowRestClient;
    }

    public WorkflowServiceStubs getWorkflowServiceStubs() {
        return this.workflowServiceStubs;
    }

    public WorkflowServiceStubs getOrCreateWorkflowServiceStubs() {
        if (this.workflowServiceStubs != null) {
            return this.workflowServiceStubs;
        }

        return this.createWorkflowServiceStubs();
    }

    public WorkflowClient getWorkflowClient() {
        return this.workflowClient;
    }

    public WorkflowClient getOrCreateWorkflowClient() {
        if (this.workflowClient != null) {
            return this.workflowClient;
        }

        return this.createWorkflowClient();
    }

    public WorkerFactory getWorkerFactory() {
        return this.workerFactory;
    }

    public WorkerFactory getOrCreateWorkerFactory() {
        if (this.workerFactory != null) {
            return this.workerFactory;
        }

        return this.createWorkerFactory();
    }

    public List<Worker> getWorkers() {
        return this.workersInfo.stream().map(WorkerInfo::getWorker).collect(toUnmodifiableList());
    }

    public List<WorkerInfo> getWorkersInfo() {
        return unmodifiableList(this.workersInfo);
    }

    /**
     * Configure a {@link WorkflowServiceStubs}
     */
    public synchronized KuFlowTemporalConnection configureWorkflowServiceStubs(Consumer<WorkflowServiceStubsOptions.Builder> configurer) {
        configurer.accept(this.workflowServiceStubsBuilder);

        return this;
    }

    /**
     * Configure a {@link WorkflowClient}
     */
    public synchronized KuFlowTemporalConnection configureWorkflowClient(Consumer<WorkflowClientOptions.Builder> configurer) {
        configurer.accept(this.workflowClientBuilder);

        return this;
    }

    /**
     * Configure a new {@link Worker} to be started
     */
    public synchronized KuFlowTemporalConnection configureWorker(Consumer<WorkerBuilder> configurer) {
        WorkerBuilder workerBuilder = WorkerBuilder.instance();
        configurer.accept(workerBuilder);

        if (this.workersInfo.stream().anyMatch(it -> it.getTaskQueue().equals(workerBuilder.getTaskQueue()))) {
            throw new KuFlowTemporalException("Duplicate task queue");
        }

        this.workersBuilder.add(workerBuilder);

        Set<String> workflowTypes = workerBuilder
            .getWorkflowImplementationClasses()
            .stream()
            .flatMap(workflowImplementationRegister -> this.computeWorkflowTypes(workflowImplementationRegister).stream())
            .collect(toUnmodifiableSet());

        Set<String> activityTypes = workerBuilder
            .getActivityImplementations()
            .stream()
            .flatMap(activityImplementationRegister -> this.computeActivityTypes(activityImplementationRegister).stream())
            .collect(toUnmodifiableSet());

        this.workersInfo.add(new WorkerInfo(workerBuilder.getTaskQueue(), workflowTypes, activityTypes));

        return this;
    }

    /**
     * Starts the workers created by the {@link #workerFactory}.
     */
    public synchronized void start() {
        WorkerFactory workerFactory = this.getOrCreateWorkerFactory();
        workerFactory.start();
    }

    /**
     * Initiates an orderly shutdown of the temporal connection.
     */
    public void shutdown() {
        this.shutdown(-1, null);
    }

    /**
     * Initiates an orderly shutdown of the temporal connection.
     * Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs.
     */
    public synchronized void shutdown(long timeout, TimeUnit unit) {
        Objects.requireNonNull(this.workflowServiceStubs, "A worker factory is require");
        Objects.requireNonNull(this.workerFactory, "A worker factory is require");

        this.workerFactory.shutdown();
        if (timeout > 0 && unit != null) {
            this.workerFactory.awaitTermination(timeout, unit);
        }
        this.workflowServiceStubs.shutdown();
    }

    /**
     * Blocks until all tasks have completed execution after a shutdown request, or the timeout occurs.
     */
    public void awaitTermination(long timeout, TimeUnit unit) {
        this.workerFactory.awaitTermination(timeout, unit);
    }

    /**
     * Initiates an orderly shutdown of the temporal connection.
     */
    public synchronized void shutdownNowWorkers() {
        Objects.requireNonNull(this.workerFactory, "A worker factory is require");
        this.workerFactory.shutdownNow();
    }

    private synchronized WorkflowServiceStubs createWorkflowServiceStubs() {
        if (this.workflowServiceStubs != null) {
            return this.workflowServiceStubs;
        }

        if (this.workflowServiceStubsBuilder.build().getTarget() == null) {
            this.workflowServiceStubsBuilder.setTarget("engine.kuflow.com:443");
        }

        AuthorizationGrpcMetadataProvider authorizationGrpcMetadataProvider = new AuthorizationGrpcMetadataProvider(
            new KuFlowAuthorizationTokenSupplier(this.kuFlowRestClient)
        );

        WorkflowServiceStubsOptions options =
            this.workflowServiceStubsBuilder.addGrpcMetadataProvider(authorizationGrpcMetadataProvider).validateAndBuildWithDefaults();

        this.workflowServiceStubs = WorkflowServiceStubs.newServiceStubs(options);

        return this.workflowServiceStubs;
    }

    private synchronized WorkflowClient createWorkflowClient() {
        if (this.workflowClient != null) {
            return this.workflowClient;
        }

        WorkflowServiceStubs workflowServiceStubs = this.getOrCreateWorkflowServiceStubs();

        DataConverter dataConverter = this.dataConverter();

        WorkflowClientOptions workflowClientOptions =
            this.workflowClientBuilder.setContextPropagators(List.of(new MDCContextPropagator())).setDataConverter(dataConverter).build();

        this.workflowClient = WorkflowClient.newInstance(workflowServiceStubs, workflowClientOptions);

        return this.workflowClient;
    }

    private synchronized WorkerFactory createWorkerFactory() {
        if (this.workerFactory != null) {
            return this.workerFactory;
        }

        WorkflowClient workflowClient = this.getOrCreateWorkflowClient();

        this.workerFactory = WorkerFactory.newInstance(workflowClient);

        this.workersBuilder.forEach(this::newWorker);

        return this.workerFactory;
    }

    private void newWorker(WorkerBuilder workerBuilder) {
        Worker worker = this.workerFactory.newWorker(workerBuilder.getTaskQueue(), workerBuilder.getWorkerOptions());

        workerBuilder
            .getWorkflowImplementationClasses()
            .forEach(workflowImplementationRegister -> this.configureWorker(worker, workflowImplementationRegister));

        workerBuilder
            .getActivityImplementations()
            .forEach(activityImplementationRegister -> this.configureWorker(worker, activityImplementationRegister));

        WorkerInfo workerInfo =
            this.workersInfo.stream().filter(it -> it.getTaskQueue().equals(workerBuilder.getTaskQueue())).findFirst().orElseThrow();

        workerInfo.registerWorker(worker);
    }

    private void configureWorker(Worker worker, WorkflowImplementationRegister workflowImplementationRegister) {
        if (workflowImplementationRegister.getOptions() == null) {
            worker.registerWorkflowImplementationTypes(workflowImplementationRegister.getWorkflowImplementationClasses());
        } else {
            worker.registerWorkflowImplementationTypes(
                workflowImplementationRegister.getOptions(),
                workflowImplementationRegister.getWorkflowImplementationClasses()
            );
        }
    }

    private Set<String> computeWorkflowTypes(WorkflowImplementationRegister workflowImplementationRegister) {
        return Arrays
            .stream(workflowImplementationRegister.getWorkflowImplementationClasses())
            .flatMap(workflowImplementationClass -> {
                POJOWorkflowImplMetadata workflowMetadata = POJOWorkflowImplMetadata.newInstance(workflowImplementationClass);
                return workflowMetadata.getWorkflowMethods().stream();
            })
            .map(POJOWorkflowMethodMetadata::getName)
            .collect(toSet());
    }

    private void configureWorker(Worker worker, ActivityImplementationRegister activityImplementationRegister) {
        worker.registerActivitiesImplementations(activityImplementationRegister.getActivityImplementations());
    }

    private Set<String> computeActivityTypes(ActivityImplementationRegister activityImplementationRegister) {
        return Arrays
            .stream(activityImplementationRegister.getActivityImplementations())
            .flatMap(activityImplementation -> {
                Class<?> cls = activityImplementation.getClass();
                POJOActivityImplMetadata activityImplMetadata = POJOActivityImplMetadata.newInstance(cls);
                return activityImplMetadata.getActivityMethods().stream();
            })
            .map(POJOActivityMethodMetadata::getActivityTypeName)
            .collect(toSet());
    }

    private DataConverter dataConverter() {
        // Customize Temporal's default Jackson object mapper to support unknown properties
        ObjectMapper objectMapper = JacksonJsonPayloadConverter.newDefaultObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<PayloadConverter> converters = Arrays
            .stream(DefaultDataConverter.STANDARD_PAYLOAD_CONVERTERS)
            .filter(it -> !(it instanceof JacksonJsonPayloadConverter))
            .collect(toCollection(LinkedList::new));
        converters.add(new JacksonJsonPayloadConverter(objectMapper));

        return new DefaultDataConverter(converters.toArray(new PayloadConverter[0]));
    }
}
