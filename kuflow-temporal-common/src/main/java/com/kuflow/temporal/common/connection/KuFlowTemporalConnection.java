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

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.toCollection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.temporal.common.authorization.KuFlowAuthorizationTokenSupplier;
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
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * Configure a temporal client and worker with KuFlow requirements.
 */
public class KuFlowTemporalConnection {

    public static KuFlowTemporalConnectionBuilder newBuilder() {
        return new KuFlowTemporalConnectionBuilder();
    }

    private final WorkflowServiceStubs workflowServiceStubs;
    private final WorkflowClient workflowClient;
    private final WorkerFactory workerFactory;
    private final Worker worker;
    private final Set<String> workflowTypes;
    private final Set<String> activityTypes;

    /**
     * Hold all the temporal connection objects.
     *
     * @param workflowServiceStubs service to hold gRPC blocking and future stubs.
     * @param workflowClient client to the Temporal Service endpoint.
     * @param workerFactory factory maintaining worker creation and lifecycle.
     * @param worker worker hosting activity and workflow implementations.
     * @param workflowTypes workflows types registered
     * @param activityTypes activityTypes types registered
     */
    public KuFlowTemporalConnection(
        WorkflowServiceStubs workflowServiceStubs,
        WorkflowClient workflowClient,
        WorkerFactory workerFactory,
        Worker worker,
        Set<String> workflowTypes,
        Set<String> activityTypes
    ) {
        this.workflowServiceStubs = workflowServiceStubs;
        this.workflowClient = workflowClient;
        this.workerFactory = workerFactory;
        this.worker = worker;
        this.workflowTypes = workflowTypes != null ? unmodifiableSet(workflowTypes) : null;
        this.activityTypes = activityTypes != null ? unmodifiableSet(activityTypes) : null;
    }

    public WorkflowServiceStubs getWorkflowServiceStubs() {
        return this.workflowServiceStubs;
    }

    public WorkflowClient getWorkflowClient() {
        return this.workflowClient;
    }

    public WorkerFactory getWorkerFactory() {
        return this.workerFactory;
    }

    public Worker getWorker() {
        return this.worker;
    }

    public Set<String> getWorkflowTypes() {
        return this.workflowTypes;
    }

    public Set<String> getActivityTypes() {
        return this.activityTypes;
    }

    /**
     * Starts the workers created by the {@link #workerFactory}.
     */
    public void startWorker() {
        Objects.requireNonNull(this.workerFactory, "A worker factory is require");
        this.workerFactory.start();
    }

    /**
     * Initiates an orderly shutdown of the temporal connection.
     */
    public void shutdownWorker() {
        Objects.requireNonNull(this.workerFactory, "A worker factory is require");
        this.workerFactory.shutdown();
    }

    /**
     * Initiates an orderly shutdown of the temporal connection.
     */
    public void shutdownNowWorker() {
        Objects.requireNonNull(this.workerFactory, "A worker factory is require");
        this.workerFactory.shutdownNow();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        var that = (KuFlowTemporalConnection) obj;
        return (
            Objects.equals(this.workflowServiceStubs, that.workflowServiceStubs) &&
            Objects.equals(this.workflowClient, that.workflowClient) &&
            Objects.equals(this.workerFactory, that.workerFactory) &&
            Objects.equals(this.worker, that.worker) &&
            Objects.equals(this.workflowTypes, that.workflowTypes) &&
            Objects.equals(this.activityTypes, that.activityTypes)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(
            this.workflowServiceStubs,
            this.workflowClient,
            this.workerFactory,
            this.worker,
            this.workflowTypes,
            this.activityTypes
        );
    }

    @Override
    public String toString() {
        return (
            "KuFlowTemporalConnection[" +
            "workflowServiceStubs=" +
            this.workflowServiceStubs +
            ", " +
            "workflowClient=" +
            this.workflowClient +
            ", " +
            "workerFactory=" +
            this.workerFactory +
            ", " +
            "worker=" +
            this.worker +
            ", " +
            "workflowTypes=" +
            this.workflowTypes +
            ", " +
            "activityTypes=" +
            this.activityTypes +
            ']'
        );
    }

    /**
     * KuFlowTemporalConnectionBuilder is the builder for {@link KuFlowTemporalConnection}.
     */
    public static final class KuFlowTemporalConnectionBuilder {

        private KuFlowRestClient kuFlowRestClient;

        private WorkflowServiceStubs workflowServiceStubs;

        private WorkflowServiceStubsOptions.Builder workflowServiceStubsBuilder;

        private WorkflowClientOptions.Builder workflowClientBuilder;

        private WorkflowClient workflowClient;

        private WorkerBuilder workerBuilder;

        private WorkerFactory workerFactory;

        private Worker worker;

        private Set<String> workflowTypes;

        private Set<String> activityTypes;

        private KuFlowTemporalConnectionBuilder() {}

        /**
         * Register the {@link KuFlowRestClient} required
         */
        public KuFlowTemporalConnectionBuilder withKuFlowRestClient(KuFlowRestClient kuFlowRestClient) {
            this.kuFlowRestClient = kuFlowRestClient;

            return this;
        }

        /**
         * Configure a {@link WorkflowServiceStubs}
         */
        public KuFlowTemporalConnectionBuilder configureWorkflowServiceStubs(Configurer<WorkflowServiceStubsOptions.Builder> configurer) {
            this.workflowServiceStubsBuilder = WorkflowServiceStubsOptions.newBuilder();
            configurer.configure(this.workflowServiceStubsBuilder);

            return this;
        }

        /**
         * Configure a {@link WorkflowClient}
         */
        public KuFlowTemporalConnectionBuilder configureWorkflowClient(Configurer<WorkflowClientOptions.Builder> configurer) {
            this.workflowClientBuilder = WorkflowClientOptions.newBuilder();
            configurer.configure(this.workflowClientBuilder);

            return this;
        }

        /**
         * Configure a {@link Worker}
         */
        public KuFlowTemporalConnectionBuilder configureWorker(Configurer<WorkerBuilder> configurer) {
            this.workerBuilder = WorkerBuilder.instance();
            configurer.configure(this.workerBuilder);

            return this;
        }

        /**
         * Builds and returns a {@link KuFlowTemporalConnection} object.
         *
         * @return KuFlowTemporalConnection object.
         */
        public KuFlowTemporalConnection build() {
            this.buildWorkflowServiceStubs();
            this.buildWorkflowClient();
            this.buildWorker();

            return new KuFlowTemporalConnection(
                this.workflowServiceStubs,
                this.workflowClient,
                this.workerFactory,
                this.worker,
                this.workflowTypes,
                this.activityTypes
            );
        }

        private void buildWorkflowServiceStubs() {
            if (this.workflowServiceStubsBuilder != null) {
                Objects.requireNonNull(this.kuFlowRestClient, "kuFlowRestClient is required");

                AuthorizationGrpcMetadataProvider authorizationGrpcMetadataProvider = new AuthorizationGrpcMetadataProvider(
                    new KuFlowAuthorizationTokenSupplier(this.kuFlowRestClient)
                );

                WorkflowServiceStubsOptions options =
                    this.workflowServiceStubsBuilder.addGrpcMetadataProvider(authorizationGrpcMetadataProvider)
                        .validateAndBuildWithDefaults();

                this.workflowServiceStubs = WorkflowServiceStubs.newServiceStubs(options);
            }
        }

        private void buildWorkflowClient() {
            if (this.workflowClientBuilder != null) {
                Objects.requireNonNull(this.workflowServiceStubs, "workflowServiceStubs is required");

                DataConverter dataConverter = this.dataConverter();

                WorkflowClientOptions workflowClientOptions =
                    this.workflowClientBuilder.setContextPropagators(List.of(new MDCContextPropagator()))
                        .setDataConverter(dataConverter)
                        .build();

                this.workflowClient = WorkflowClient.newInstance(this.workflowServiceStubs, workflowClientOptions);
            }
        }

        private void buildWorker() {
            if (this.workerBuilder != null) {
                Objects.requireNonNull(this.workflowClient, "workflowClient is required");
                Objects.requireNonNull(this.workerBuilder.getTaskQueue(), "worker.taskQueue is required");

                this.workflowTypes = new LinkedHashSet<>();
                this.activityTypes = new LinkedHashSet<>();
                this.workerFactory = WorkerFactory.newInstance(this.workflowClient);
                this.worker = this.workerFactory.newWorker(this.workerBuilder.getTaskQueue(), this.workerBuilder.getWorkerOptions());

                this.workerBuilder.getWorkflowImplementationClasses().forEach(this::configureWorker);
                this.workerBuilder.getActivityImplementations().forEach(this::configureWorker);
            }
        }

        private void configureWorker(WorkerBuilder.WorkflowImplementationRegister workflowImplementationRegister) {
            if (workflowImplementationRegister.getOptions() == null) {
                this.worker.registerWorkflowImplementationTypes(workflowImplementationRegister.getWorkflowImplementationClasses());
            } else {
                this.worker.registerWorkflowImplementationTypes(
                        workflowImplementationRegister.getOptions(),
                        workflowImplementationRegister.getWorkflowImplementationClasses()
                    );
            }

            Arrays
                .stream(workflowImplementationRegister.getWorkflowImplementationClasses())
                .forEach(workflowImplementationClass -> {
                    POJOWorkflowImplMetadata workflowMetadata = POJOWorkflowImplMetadata.newInstance(workflowImplementationClass);
                    List<POJOWorkflowMethodMetadata> workflowMethods = workflowMetadata.getWorkflowMethods();
                    for (POJOWorkflowMethodMetadata workflowMethod : workflowMethods) {
                        String workflowName = workflowMethod.getName();
                        this.workflowTypes.add(workflowName);
                    }
                });
        }

        private void configureWorker(WorkerBuilder.ActivityImplementationRegister activityImplementationRegister) {
            this.worker.registerActivitiesImplementations(activityImplementationRegister.getActivityImplementations());

            Arrays
                .stream(activityImplementationRegister.getActivityImplementations())
                .forEach(activityImplementation -> {
                    Class<?> cls = activityImplementation.getClass();
                    POJOActivityImplMetadata activityImplMetadata = POJOActivityImplMetadata.newInstance(cls);
                    for (POJOActivityMethodMetadata activityMetadata : activityImplMetadata.getActivityMethods()) {
                        String activityType = activityMetadata.getActivityTypeName();
                        this.activityTypes.add(activityType);
                    }
                });
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
}
