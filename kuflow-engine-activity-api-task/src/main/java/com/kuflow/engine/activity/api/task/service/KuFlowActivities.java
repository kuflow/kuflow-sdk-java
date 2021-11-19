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

/**
 * Kuflow activities to be used in Temporal.
 *
 */
@ActivityInterface
public interface KuFlowActivities {
    /**
     * Start a Process. The state of Process is setted to running.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    StartProcessResponseResource startProcess(StartProcessRequestResource request);

    /**
     * Complete a Process. The state of Process is setted to completed.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    CompleteProcessResponseResource completeProcess(CompleteProcessRequestResource request);

    /**
     * Create a Task and optionally fill its elements.
     *
     * If you want the method to be idempotent, please specify the id field in {@link TaskCompleteRequestResource}.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    TaskResponseResource createTask(TaskRequestResource request);

    /**
     * Complete a task.
     *
     * Allow to complete a claimed task by the principal.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    TaskCompleteResponseResource completeTask(TaskCompleteRequestResource request);

    /**
     * Claim a task.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    TaskClaimResponseResource claimTask(TaskClaimRequestResource request);

    /**
     * Append a log to the task.
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    LogResponseResource appendLog(LogRequestResource request);
}
