/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow;

import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.StartProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.StartProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskAssignRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskAssignResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskResponseResource;
import javax.annotation.Nonnull;

public class KuFlowActivitiesDelegate implements KuFlowActivities {

    private final KuFlowActivities delegate;

    public KuFlowActivitiesDelegate(KuFlowActivities delegate) {
        this.delegate = delegate;
    }

    @Nonnull
    @Override
    public StartProcessResponseResource startProcess(@Nonnull StartProcessRequestResource request) {
        return this.delegate.startProcess(request);
    }

    @Nonnull
    @Override
    public CompleteProcessResponseResource completeProcess(@Nonnull CompleteProcessRequestResource request) {
        return this.delegate.completeProcess(request);
    }

    @Nonnull
    @Override
    public TaskResponseResource createTask(@Nonnull TaskRequestResource request) {
        return this.delegate.createTask(request);
    }

    @Nonnull
    @Override
    public TaskResponseResource createTaskAndWaitTermination(@Nonnull TaskRequestResource request) {
        return this.delegate.createTaskAndWaitTermination(request);
    }

    @Nonnull
    @Override
    public TaskCompleteResponseResource completeTask(@Nonnull TaskCompleteRequestResource request) {
        return this.delegate.completeTask(request);
    }

    @Nonnull
    @Override
    public TaskClaimResponseResource claimTask(@Nonnull TaskClaimRequestResource request) {
        return this.delegate.claimTask(request);
    }

    @Nonnull
    @Override
    public TaskAssignResponseResource assignTask(@Nonnull TaskAssignRequestResource request) {
        return this.delegate.assignTask(request);
    }

    @Nonnull
    @Override
    public LogResponseResource appendLog(@Nonnull LogRequestResource request) {
        return this.delegate.appendLog(request);
    }
}
