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
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface KuFlowActivities {
    @ActivityMethod
    StartProcessResponseResource startProcess(StartProcessRequestResource request);

    @ActivityMethod
    CompleteProcessResponseResource completeProcess(CompleteProcessRequestResource request);

    @ActivityMethod
    TaskResponseResource createTask(TaskRequestResource request);

    @ActivityMethod
    TaskCompleteResponseResource completeTask(TaskCompleteRequestResource request);

    @ActivityMethod
    TaskClaimResponseResource claimTask(TaskClaimRequestResource request);

    @ActivityMethod
    LogResponseResource appendLog(LogRequestResource request);
}
