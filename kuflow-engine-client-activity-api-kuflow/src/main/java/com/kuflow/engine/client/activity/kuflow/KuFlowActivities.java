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
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskResponseResource;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import javax.annotation.Nonnull;

/**
 * KuFlow activities to be used in Temporal.
 *
 */
@ActivityInterface(namePrefix = "KuFlow_Engine_")
public interface KuFlowActivities {
    /**
     * Start a Process. The state of Process is setted to running.
     *
     * @param request must not be {@literal null}.
     * @return process started
     */
    @ActivityMethod
    @Nonnull
    StartProcessResponseResource startProcess(@Nonnull StartProcessRequestResource request);

    /**
     * Complete a Process. The state of Process is setted to completed.
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    @ActivityMethod
    @Nonnull
    CompleteProcessResponseResource completeProcess(@Nonnull CompleteProcessRequestResource request);

    /**
     * Create a Task and optionally fill its elements.
     *
     * If you want the method to be idempotent, please specify the id field in {@link TaskCompleteRequestResource}.
     *
     * @param request must not be {@literal null}.
     * @return task created
     */
    @ActivityMethod
    @Nonnull
    TaskResponseResource createTask(@Nonnull TaskRequestResource request);

    /**
     * Create a Task and optionally fill its elements. The activity is not completed until the <strong>"COMPLETED"</strong> or
     * <strong>"CANCELLED"</strong> event is received from KuFlow. This is useful in KuFlow tasks where you have to wait for an external
     * agent, usually a human, to complete it.
     *
     * If you want the method to be idempotent, please specify the id field in {@link TaskCompleteRequestResource}.
     *
     * @param request must not be {@literal null}.
     * @return task created
     */
    @ActivityMethod
    @Nonnull
    TaskResponseResource createTaskAndWaitTermination(@Nonnull TaskRequestResource request);

    /**
     * Complete a task.
     *
     * Allow to complete a claimed task by the principal.
     *
     * @param request must not be {@literal null}.
     * @return task completed
     */
    @ActivityMethod
    @Nonnull
    TaskCompleteResponseResource completeTask(@Nonnull TaskCompleteRequestResource request);

    /**
     * Claim a task.
     *
     * @param request must not be {@literal null}.
     * @return task claimed
     */
    @ActivityMethod
    @Nonnull
    TaskClaimResponseResource claimTask(@Nonnull TaskClaimRequestResource request);

    /**
     * Append a log to the task.
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param request must not be {@literal null}.
     * @return log appended
     */
    @ActivityMethod
    @Nonnull
    LogResponseResource appendLog(@Nonnull LogRequestResource request);
}
