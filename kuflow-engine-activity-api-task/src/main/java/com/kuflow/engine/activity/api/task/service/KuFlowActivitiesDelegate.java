/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.activity.api.task.service;

import com.kuflow.engine.activity.api.task.resource.CompleteProcessRequestResource;
import com.kuflow.engine.activity.api.task.resource.CompleteProcessResponseResource;
import com.kuflow.engine.activity.api.task.resource.LogRequestResource;
import com.kuflow.engine.activity.api.task.resource.LogResponseResource;
import com.kuflow.engine.activity.api.task.resource.StartProcessRequestResource;
import com.kuflow.engine.activity.api.task.resource.StartProcessResponseResource;
import com.kuflow.engine.activity.api.task.resource.TaskClaimRequestResource;
import com.kuflow.engine.activity.api.task.resource.TaskClaimResponseResource;
import com.kuflow.engine.activity.api.task.resource.TaskCompleteRequestResource;
import com.kuflow.engine.activity.api.task.resource.TaskCompleteResponseResource;
import com.kuflow.engine.activity.api.task.resource.TaskRequestResource;
import com.kuflow.engine.activity.api.task.resource.TaskResponseResource;

public class KuFlowActivitiesDelegate implements KuFlowActivities {

    private final KuFlowActivities delegate;

    public KuFlowActivitiesDelegate(KuFlowActivities delegate) {
        this.delegate = delegate;
    }

    @Override
    public StartProcessResponseResource startProcess(StartProcessRequestResource request) {
        return this.delegate.startProcess(request);
    }

    @Override
    public CompleteProcessResponseResource completeProcess(CompleteProcessRequestResource request) {
        return this.delegate.completeProcess(request);
    }

    @Override
    public TaskResponseResource createTask(TaskRequestResource request) {
        return this.delegate.createTask(request);
    }

    @Override
    public TaskCompleteResponseResource completeTask(TaskCompleteRequestResource request) {
        return this.delegate.completeTask(request);
    }

    @Override
    public TaskClaimResponseResource claimTask(TaskClaimRequestResource request) {
        return this.delegate.claimTask(request);
    }

    @Override
    public LogResponseResource appendLog(LogRequestResource request) {
        return this.delegate.appendLog(request);
    }
}
