/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.task.service;

import com.kuflow.engine.client.activity.api.task.resource.CompleteProcessRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.CompleteProcessResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.LogRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.LogResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.StartProcessRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.StartProcessResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskCompleteResponseResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskResponseResource;
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
        return this.delegate.createTask(request);
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
    public LogResponseResource appendLog(@Nonnull LogRequestResource request) {
        return this.delegate.appendLog(request);
    }
}
