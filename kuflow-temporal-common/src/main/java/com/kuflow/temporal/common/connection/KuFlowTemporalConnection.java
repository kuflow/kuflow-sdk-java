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

import static java.util.stream.Collectors.toCollection;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.Authentication;
import com.kuflow.rest.model.AuthenticationEngineCertificate;
import com.kuflow.rest.model.AuthenticationEngineCertificateTls;
import com.kuflow.rest.model.AuthenticationType;
import com.kuflow.temporal.common.authorization.KuFlowAuthorizationTokenSupplier;
import com.kuflow.temporal.common.connection.WorkerBuilder.ActivityImplementationRegister;
import com.kuflow.temporal.common.connection.WorkerBuilder.WorkflowImplementationRegister;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.common.ssl.SslContextBuilder;
import com.kuflow.temporal.common.tracing.MDCContextPropagator;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.temporal.authorization.AuthorizationGrpcMetadataProvider;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.common.converter.DataConverter;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.common.converter.JacksonJsonPayloadConverter;
import io.temporal.common.converter.PayloadConverter;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configure a temporal client and worker with KuFlow requirements.
 */
public class KuFlowTemporalConnection {

    public static KuFlowTemporalConnection instance(KuFlowRestClient kuFlowRestClient) {
        return new KuFlowTemporalConnection(kuFlowRestClient);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowTemporalConnection.class);

    private final KuFlowRestClient kuFlowRestClient;

    private final WorkflowServiceStubsOptions.Builder workflowServiceStubsBuilder = WorkflowServiceStubsOptions.newBuilder();

    private final WorkflowClientOptions.Builder workflowClientBuilder = WorkflowClientOptions.newBuilder();

    private final WorkerInformationNotifierConfigurationBuilder workerInformationNotifierConfigurationBuilder =
        WorkerInformationNotifierConfigurationBuilder.instance();

    private WorkerBuilder workerBuilder;

    private WorkerInformation workerInformation;

    private WorkerInformationNotifier workerInformationNotifier;

    private WorkflowServiceStubs workflowServiceStubs;

    private WorkflowClient workflowClient;

    private WorkerFactory workerFactory;

    private UUID installationId;

    private UUID tenantId;

    private List<UUID> robotIds;

    private boolean started = false;

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

    public Worker getWorker() {
        return this.workerInformation.getWorker();
    }

    public WorkerInformation getWorkerInformation() {
        return this.workerInformation;
    }

    public KuFlowTemporalConnection withInstallationId(@Nullable UUID installationId) {
        this.installationId = installationId;

        return this;
    }

    public KuFlowTemporalConnection withTenantId(@Nullable UUID tenantId) {
        this.tenantId = tenantId;

        return this;
    }

    public KuFlowTemporalConnection withRobotId(@Nullable UUID robotId) {
        if (robotId == null) {
            return this;
        }

        if (this.robotIds == null) {
            this.robotIds = new LinkedList<>();
        }
        if (!this.robotIds.contains(robotId)) {
            this.robotIds.add(robotId);
        }

        return this;
    }

    public KuFlowTemporalConnection withRobotIds(@Nullable List<UUID> robotIds) {
        if (robotIds == null) {
            this.robotIds = null;
            return this;
        }

        robotIds.forEach(this::withRobotId);

        return this;
    }

    public KuFlowTemporalConnection configureWorkerInformationNotifierConfiguration(
        Consumer<WorkerInformationNotifierConfigurationBuilder> configurer
    ) {
        this.checkIsNotStarted();

        configurer.accept(this.workerInformationNotifierConfigurationBuilder);

        return this;
    }

    /**
     * Configure a {@link WorkflowServiceStubs}
     */
    public synchronized KuFlowTemporalConnection configureWorkflowServiceStubs(Consumer<WorkflowServiceStubsOptions.Builder> configurer) {
        this.checkIsNotStarted();

        configurer.accept(this.workflowServiceStubsBuilder);

        return this;
    }

    /**
     * Configure a {@link WorkflowClient}
     */
    public synchronized KuFlowTemporalConnection configureWorkflowClient(Consumer<WorkflowClientOptions.Builder> configurer) {
        this.checkIsNotStarted();

        configurer.accept(this.workflowClientBuilder);

        return this;
    }

    /**
     * Configure a {@link Worker} to be started
     */
    public synchronized KuFlowTemporalConnection configureWorker(Consumer<WorkerBuilder> configurer) {
        this.checkIsNotStarted();

        WorkerBuilder workerBuilder = WorkerBuilder.instance();
        configurer.accept(workerBuilder);

        this.workerBuilder = workerBuilder;
        this.workerInformation = new WorkerInformation(workerBuilder);

        return this;
    }

    /**
     * Starts the workers created by the {@link #workerFactory}.
     */
    public synchronized void start() {
        if (this.started) {
            return;
        }

        LOGGER.info("Starting KuFlowTemporal Connection");

        this.workerInformation.setInstallationId(this.installationId);
        this.workerInformation.setTenantId(this.tenantId);
        this.workerInformation.setRobotIds(this.robotIds);

        this.applyDefaultConfiguration();

        this.workerInformationNotifier =
            new WorkerInformationNotifier(
                this.kuFlowRestClient,
                this.workflowClientBuilder.validateAndBuildWithDefaults(),
                this.workerInformationNotifierConfigurationBuilder.build(),
                List.of(this.workerInformation)
            );
        this.workerInformationNotifier.start();

        WorkerFactory workerFactory = this.getOrCreateWorkerFactory();
        workerFactory.start();

        LOGGER.info("Started KuFlowTemporal Connection");

        this.started = true;
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
        if (!this.started) {
            return;
        }

        Objects.requireNonNull(this.workflowServiceStubs, "A worker factory is require");
        Objects.requireNonNull(this.workerFactory, "A worker factory is require");

        this.workerInformationNotifier.shutdown();

        this.workerFactory.shutdown();
        if (timeout > 0 && unit != null) {
            this.workerFactory.awaitTermination(timeout, unit);
        }
        this.workflowServiceStubs.shutdown();

        this.started = false;
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
            new KuFlowAuthorizationTokenSupplier(this.kuFlowRestClient, this.workerInformation)
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

        this.newWorker(this.workerBuilder);

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

        this.workerInformation.registerWorker(worker);
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

    private void configureWorker(Worker worker, ActivityImplementationRegister activityImplementationRegister) {
        worker.registerActivitiesImplementations(activityImplementationRegister.getActivityImplementations());
    }

    private DataConverter dataConverter() {
        // Customize Temporal default Jackson object mapper to support unknown properties
        ObjectMapper objectMapper = JacksonJsonPayloadConverter.newDefaultObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        List<PayloadConverter> converters = Arrays
            .stream(DefaultDataConverter.STANDARD_PAYLOAD_CONVERTERS)
            .filter(it -> !(it instanceof JacksonJsonPayloadConverter))
            .collect(toCollection(LinkedList::new));
        converters.add(new JacksonJsonPayloadConverter(objectMapper));

        return new DefaultDataConverter(converters.toArray(new PayloadConverter[0]));
    }

    private void applyDefaultConfiguration() {
        Authentication authenticationRequest = new Authentication()
            .setType(AuthenticationType.ENGINE_CERTIFICATE)
            .setTenantId(this.workerInformation.getTenantId());

        Authentication authenticationResponse =
            this.kuFlowRestClient.getAuthenticationOperations().createAuthentication(authenticationRequest);

        AuthenticationEngineCertificate authenticationEngineCertificate = authenticationResponse.getEngineCertificate();

        this.configureWorkflowServiceStubs(builder -> {
                WorkflowServiceStubsOptions stubsOptions = builder.build();
                if (stubsOptions.getSslContext() == null) {
                    AuthenticationEngineCertificateTls tls = authenticationEngineCertificate.getTls();
                    SslContext sslContext = SslContextBuilder
                        .builder()
                        .withCaData(tls.getServerRootCaCertificate())
                        .withCertData(tls.getClientCertificate())
                        .withKeyData(tls.getClientPrivateKey())
                        .build();

                    builder.setSslContext(sslContext);
                }
            });

        this.configureWorkflowClient(builder -> {
                if (builder.build().getNamespace() == null) {
                    builder.setNamespace(authenticationEngineCertificate.getNamespace());
                }
            });
    }

    private void checkIsNotStarted() {
        if (this.started) {
            throw new KuFlowTemporalException("Invocation not allowed when the connection is started");
        }
    }
}
