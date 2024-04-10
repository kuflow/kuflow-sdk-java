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

import static java.util.stream.Collectors.toSet;
import static java.util.stream.Collectors.toUnmodifiableSet;

import io.temporal.common.metadata.POJOActivityImplMetadata;
import io.temporal.common.metadata.POJOActivityMethodMetadata;
import io.temporal.common.metadata.POJOWorkflowImplMetadata;
import io.temporal.common.metadata.POJOWorkflowMethodMetadata;
import io.temporal.worker.Worker;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import javax.annotation.Nullable;

public class WorkerInformation {

    private final String taskQueue;

    private final Set<String> workflowTypes;

    private final Set<String> activityTypes;

    private UUID installationId;

    private UUID tenantId;

    private List<UUID> robotIds;

    private Worker worker;

    public WorkerInformation(WorkerBuilder workerBuilder) {
        this.taskQueue = workerBuilder.getTaskQueue();
        this.workflowTypes = workerBuilder
            .getWorkflowImplementationClasses()
            .stream()
            .flatMap(workflowImplementationRegister -> this.computeWorkflowTypes(workflowImplementationRegister).stream())
            .collect(toUnmodifiableSet());

        this.activityTypes = workerBuilder
            .getActivityImplementations()
            .stream()
            .flatMap(activityImplementationRegister -> this.computeActivityTypes(activityImplementationRegister).stream())
            .collect(toUnmodifiableSet());
    }

    protected WorkerInformation(String taskQueue, Set<String> workflowTypes, Set<String> activityTypes) {
        this.taskQueue = Objects.requireNonNull(taskQueue, "'taskQueue' is required");
        this.workflowTypes = Objects.requireNonNull(workflowTypes, "'workflowTypes' is required");
        this.activityTypes = Objects.requireNonNull(activityTypes, "'activityTypes' is required");
    }

    @Nullable
    public UUID getInstallationId() {
        return this.installationId;
    }

    public void setInstallationId(@Nullable UUID installationId) {
        this.installationId = installationId;
    }

    @Nullable
    public UUID getTenantId() {
        return this.tenantId;
    }

    void setTenantId(@Nullable UUID tenantId) {
        this.tenantId = tenantId;
    }

    @Nullable
    public List<UUID> getRobotIds() {
        return this.robotIds;
    }

    void setRobotIds(@Nullable List<UUID> robotIds) {
        this.robotIds = robotIds;
    }

    public String getTaskQueue() {
        return this.taskQueue;
    }

    public Set<String> getWorkflowTypes() {
        return this.workflowTypes;
    }

    public Set<String> getActivityTypes() {
        return this.activityTypes;
    }

    public Worker getWorker() {
        return this.worker;
    }

    void registerWorker(Worker worker) {
        this.worker = worker;
    }

    private Set<String> computeWorkflowTypes(WorkerBuilder.WorkflowImplementationRegister workflowImplementationRegister) {
        return Arrays.stream(workflowImplementationRegister.getWorkflowImplementationClasses())
            .flatMap(workflowImplementationClass -> {
                POJOWorkflowImplMetadata workflowMetadata = POJOWorkflowImplMetadata.newInstance(workflowImplementationClass);
                return workflowMetadata.getWorkflowMethods().stream();
            })
            .map(POJOWorkflowMethodMetadata::getName)
            .collect(toSet());
    }

    private Set<String> computeActivityTypes(WorkerBuilder.ActivityImplementationRegister activityImplementationRegister) {
        return Arrays.stream(activityImplementationRegister.getActivityImplementations())
            .flatMap(activityImplementation -> {
                Class<?> cls = activityImplementation.getClass();
                POJOActivityImplMetadata activityImplMetadata = POJOActivityImplMetadata.newInstance(cls);
                return activityImplMetadata.getActivityMethods().stream();
            })
            .map(POJOActivityMethodMetadata::getActivityTypeName)
            .collect(toSet());
    }
}
