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

package com.kuflow.temporal.worker.connection;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.Authentication;
import com.kuflow.rest.model.AuthenticationCreateParams;
import com.kuflow.rest.model.AuthenticationEngineCertificate;
import com.kuflow.rest.model.AuthenticationEngineCertificateTls;
import com.kuflow.rest.model.AuthenticationType;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import com.kuflow.temporal.worker.authorization.KuFlowAuthorizationTokenSupplier;
import com.kuflow.temporal.worker.connection.WorkerBuilder.ActivityImplementationRegister;
import com.kuflow.temporal.worker.connection.WorkerBuilder.WorkflowImplementationRegister;
import com.kuflow.temporal.worker.encryption.codec.EncryptionPayloadCodec;
import com.kuflow.temporal.worker.encryption.converter.EncryptionPayloadConverter;
import com.kuflow.temporal.worker.encryption.interceptors.EncryptionWorkerInterceptor;
import com.kuflow.temporal.worker.encryption.interceptors.EncryptionWorkflowClientInterceptor;
import com.kuflow.temporal.worker.jackson.KuFlowObjectMapperFactory;
import com.kuflow.temporal.worker.ssl.SslContextBuilder;
import com.kuflow.temporal.worker.tracing.MDCContextPropagator;
import io.grpc.netty.shaded.io.netty.handler.ssl.SslContext;
import io.temporal.authorization.AuthorizationGrpcMetadataProvider;
import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowClientOptions;
import io.temporal.common.converter.CodecDataConverter;
import io.temporal.common.converter.DataConverter;
import io.temporal.common.converter.DefaultDataConverter;
import io.temporal.common.converter.JacksonJsonPayloadConverter;
import io.temporal.common.interceptors.WorkerInterceptor;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.serviceclient.WorkflowServiceStubsOptions;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;
import io.temporal.worker.WorkerFactoryOptions;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import javax.annotation.Nullable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Configure a temporal client and workers with KuFlow requirements.
 */
public class KuFlowTemporalConnection {

    public static KuFlowTemporalConnection instance(KuFlowRestClient kuFlowRestClient) {
        return new KuFlowTemporalConnection(kuFlowRestClient);
    }

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowTemporalConnection.class);

    private final KuFlowRestClient kuFlowRestClient;

    private final WorkflowServiceStubsOptions.Builder workflowServiceStubsBuilder = WorkflowServiceStubsOptions.newBuilder();

    private final WorkflowClientOptions.Builder workflowClientBuilder = WorkflowClientOptions.newBuilder();

    private final WorkerFactoryOptions.Builder workerFactoryBuilder = WorkerFactoryOptions.newBuilder();

    private final WorkerInformationNotifierConfigurationBuilder workerInformationNotifierConfigurationBuilder =
        WorkerInformationNotifierConfigurationBuilder.instance();

    private final Map<String, WorkerBuilder> workerBuilderByTaskQueue = new LinkedHashMap<>();

    private final Map<String, WorkerInformation> workerInformationByTaskQueue = new LinkedHashMap<>();

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

    /**
     * @deprecated Use {@link #getWorker(String)} or {@link #getWorkers()} instead, a connection can serve several task
     * queues. This method is maintained for backward compatibility and fails if more than one worker is configured.
     */
    @Deprecated
    public Worker getWorker() {
        WorkerInformation workerInformation = this.getSingleWorkerInformation();

        return workerInformation != null ? workerInformation.getWorker() : null;
    }

    /**
     * Get the worker of a task queue. It is available once the worker factory is created.
     *
     * @param taskQueue task queue of the worker
     * @return the worker, or {@code null} if no worker is configured for the task queue or the worker factory is not
     * created yet
     */
    @Nullable
    public Worker getWorker(String taskQueue) {
        WorkerInformation workerInformation = this.workerInformationByTaskQueue.get(taskQueue);

        return workerInformation != null ? workerInformation.getWorker() : null;
    }

    /**
     * Get the workers, one per configured task queue, in configuration order. They are available once the worker
     * factory is created.
     *
     * @return the created workers
     */
    public List<Worker> getWorkers() {
        return this.workerInformationByTaskQueue.values().stream().map(WorkerInformation::getWorker).filter(Objects::nonNull).toList();
    }

    /**
     * @deprecated Use {@link #getWorkerInformation(String)} or {@link #getWorkerInformationList()} instead, a
     * connection can serve several task queues. This method is maintained for backward compatibility and fails if more
     * than one worker is configured.
     */
    @Deprecated
    public WorkerInformation getWorkerInformation() {
        return this.getSingleWorkerInformation();
    }

    /**
     * Get the information of the worker configured for a task queue.
     *
     * @param taskQueue task queue of the worker
     * @return the worker information, or {@code null} if no worker is configured for the task queue
     */
    @Nullable
    public WorkerInformation getWorkerInformation(String taskQueue) {
        return this.workerInformationByTaskQueue.get(taskQueue);
    }

    /**
     * Get the information of the configured workers, one per task queue, in configuration order.
     *
     * @return the worker information list
     */
    public List<WorkerInformation> getWorkerInformationList() {
        return List.copyOf(this.workerInformationByTaskQueue.values());
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
     * Configure the {@link WorkerFactory} that creates all the workers. Its workflow cache and workflow threads are
     * shared by every task queue. The KuFlow encryption interceptor is always added after the configured worker
     * interceptors.
     */
    public synchronized KuFlowTemporalConnection configureWorkerFactory(Consumer<WorkerFactoryOptions.Builder> configurer) {
        this.checkIsNotStarted();

        configurer.accept(this.workerFactoryBuilder);

        return this;
    }

    /**
     * Configure a {@link Worker} to be started. Each invocation configures one more worker, polling the task queue of
     * the builder, so the same connection can serve several task queues.
     *
     * @throws KuFlowTemporalException if the task queue is missing or another worker is configured for it
     */
    public synchronized KuFlowTemporalConnection configureWorker(Consumer<WorkerBuilder> configurer) {
        this.checkIsNotStarted();

        WorkerBuilder workerBuilder = WorkerBuilder.instance();
        configurer.accept(workerBuilder);

        String taskQueue = workerBuilder.getTaskQueue();
        if (taskQueue == null || taskQueue.isBlank()) {
            throw new KuFlowTemporalException("The worker task queue is required");
        }
        if (this.workerBuilderByTaskQueue.containsKey(taskQueue)) {
            throw new KuFlowTemporalException("Duplicate task queue: " + taskQueue);
        }

        WorkerInformation workerInformation = new WorkerInformation(workerBuilder);

        this.workerBuilderByTaskQueue.put(taskQueue, workerBuilder);
        this.workerInformationByTaskQueue.put(taskQueue, workerInformation);

        if (this.workerFactory != null) {
            this.newWorker(workerBuilder);
        }

        return this;
    }

    /**
     * Starts the workers created by the {@link #workerFactory}.
     */
    public synchronized void start() {
        if (this.started) {
            return;
        }

        if (this.workerInformationByTaskQueue.isEmpty()) {
            throw new KuFlowTemporalException("At least one worker must be configured");
        }

        LOGGER.info("Starting KuFlowTemporal Connection");

        this.workerInformationByTaskQueue.values().forEach(workerInformation -> {
            workerInformation.setInstallationId(this.installationId);
            workerInformation.setTenantId(this.tenantId);
            workerInformation.setRobotIds(this.robotIds);
        });

        this.applyDefaultConfiguration();

        this.workerInformationNotifier = new WorkerInformationNotifier(
            this.kuFlowRestClient,
            this.workflowClientBuilder.validateAndBuildWithDefaults(),
            this.workerInformationNotifierConfigurationBuilder.build(),
            this.getWorkerInformationList()
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
            new KuFlowAuthorizationTokenSupplier(this.kuFlowRestClient, () -> this.tenantId)
        );

        WorkflowServiceStubsOptions options = this.workflowServiceStubsBuilder
            .addGrpcMetadataProvider(authorizationGrpcMetadataProvider)
            .validateAndBuildWithDefaults();

        this.workflowServiceStubs = WorkflowServiceStubs.newServiceStubs(options);

        return this.workflowServiceStubs;
    }

    private synchronized WorkflowClient createWorkflowClient() {
        if (this.workflowClient != null) {
            return this.workflowClient;
        }

        WorkflowServiceStubs workflowServiceStubs = this.getOrCreateWorkflowServiceStubs();

        DataConverter dataConverter = this.dataConverter();

        WorkflowClientOptions workflowClientOptions = this.workflowClientBuilder
            .setContextPropagators(List.of(new MDCContextPropagator()))
            .setDataConverter(dataConverter)
            .setInterceptors(new EncryptionWorkflowClientInterceptor())
            .validateAndBuildWithDefaults();

        this.workflowClient = WorkflowClient.newInstance(workflowServiceStubs, workflowClientOptions);

        return this.workflowClient;
    }

    private synchronized WorkerFactory createWorkerFactory() {
        if (this.workerFactory != null) {
            return this.workerFactory;
        }

        WorkflowClient workflowClient = this.getOrCreateWorkflowClient();

        this.workerFactory = WorkerFactory.newInstance(workflowClient, this.workerFactoryOptions());

        this.workerBuilderByTaskQueue.values().forEach(this::newWorker);

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

        this.workerInformationByTaskQueue.get(workerBuilder.getTaskQueue()).registerWorker(worker);
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

    /**
     * Options of the {@link WorkerFactory}: the configured ones plus the KuFlow encryption interceptor.
     */
    WorkerFactoryOptions workerFactoryOptions() {
        WorkerFactoryOptions configuredWorkerFactoryOptions = this.workerFactoryBuilder.build();

        return WorkerFactoryOptions.newBuilder(configuredWorkerFactoryOptions)
            .setWorkerInterceptors(this.workerInterceptors(configuredWorkerFactoryOptions.getWorkerInterceptors()))
            .validateAndBuildWithDefaults();
    }

    /**
     * The encryption interceptor goes last so that it is the outermost one: the encryption wrappers it adds reach the
     * payload converter without passing through the configured interceptors.
     */
    private WorkerInterceptor[] workerInterceptors(@Nullable WorkerInterceptor[] configuredWorkerInterceptors) {
        List<WorkerInterceptor> workerInterceptors = new LinkedList<>();
        if (configuredWorkerInterceptors != null) {
            workerInterceptors.addAll(Arrays.asList(configuredWorkerInterceptors));
        }
        workerInterceptors.add(new EncryptionWorkerInterceptor());

        return workerInterceptors.toArray(WorkerInterceptor[]::new);
    }

    private DataConverter dataConverter() {
        ObjectMapper objectMapper = KuFlowObjectMapperFactory.createObjectMapper();

        JacksonJsonPayloadConverter jacksonJsonPayloadConverter = new JacksonJsonPayloadConverter(objectMapper);

        DefaultDataConverter dataConverter = DefaultDataConverter.newDefaultInstance().withPayloadConverterOverrides(
            new EncryptionPayloadConverter(jacksonJsonPayloadConverter)
        );

        EncryptionPayloadCodec encryptionPayloadCodec = new EncryptionPayloadCodec(this.kuFlowRestClient);

        return new CodecDataConverter(dataConverter, List.of(encryptionPayloadCodec));
    }

    private void applyDefaultConfiguration() {
        AuthenticationCreateParams params = new AuthenticationCreateParams()
            .setType(AuthenticationType.ENGINE_CERTIFICATE)
            .setTenantId(this.tenantId);

        Authentication authentication = this.kuFlowRestClient.getAuthenticationOperations().createAuthentication(params);

        AuthenticationEngineCertificate authenticationEngineCertificate = authentication.getEngineCertificate();

        this.configureWorkflowServiceStubs(builder -> {
            WorkflowServiceStubsOptions stubsOptions = builder.build();
            if (stubsOptions.getSslContext() == null) {
                AuthenticationEngineCertificateTls tls = authenticationEngineCertificate.getTls();
                SslContext sslContext = SslContextBuilder.builder()
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

    @Nullable
    private WorkerInformation getSingleWorkerInformation() {
        if (this.workerInformationByTaskQueue.size() > 1) {
            throw new KuFlowTemporalException("Several workers are configured, get them by task queue");
        }

        return this.workerInformationByTaskQueue.values().stream().findFirst().orElse(null);
    }

    private void checkIsNotStarted() {
        if (this.started) {
            throw new KuFlowTemporalException("Invocation not allowed when the connection is started");
        }
    }
}
