/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow;

import com.kuflow.engine.client.activity.kuflow.resource.ChangeProcessInitiatorRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.ChangeProcessInitiatorResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.CompleteProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.CreateTaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.CreateTaskResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.FindProcesseResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.FindProcessesRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.FindTaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.FindTaskResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.LogResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveProcessRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveProcessResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveTaskRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.RetrieveTaskResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskAssignRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskAssignResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskClaimResponseResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteRequestResource;
import com.kuflow.engine.client.activity.kuflow.resource.TaskCompleteResponseResource;
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
     * Find all accessible Processes
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    @Nonnull
    FindProcesseResponseResource findProcesses(FindProcessesRequestResource request);

    /**
     * Retrieve a Process.
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    @ActivityMethod
    @Nonnull
    RetrieveProcessResponseResource retrieveProcess(@Nonnull RetrieveProcessRequestResource request);

    /**
     * Complete a Process. The state of Process is set to "COMPLETED".
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    @ActivityMethod
    @Nonnull
    CompleteProcessResponseResource completeProcess(@Nonnull CompleteProcessRequestResource request);

    /**
     * Change the current initiator of a process. Allows you to choose a user (by email or principal
     * identifier) or an application (principal identifier).
     *
     * @param request must not be {@literal null}.
     * @return task assigned
     */
    @ActivityMethod
    @Nonnull
    ChangeProcessInitiatorResponseResource changeProcessInitiator(@Nonnull ChangeProcessInitiatorRequestResource request);

    /**
     * List all the Processes that have been created and the the credentials has access.
     *
     * @param request must not be {@literal null}.
     * @return Processes found paginated
     */
    @ActivityMethod
    @Nonnull
    public FindTaskResponseResource findTasks(FindTaskRequestResource request);

    /**
     * Retrieve a Task.
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    @ActivityMethod
    @Nonnull
    RetrieveTaskResponseResource retrieveTask(@Nonnull RetrieveTaskRequestResource request);

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
    CreateTaskResponseResource createTask(@Nonnull CreateTaskRequestResource request);

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
    CreateTaskResponseResource createTaskAndWaitTermination(@Nonnull CreateTaskRequestResource request);

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
     * Assign a task to a user or application using their email or principalId
     *
     * @param request must not be {@literal null}.
     * @return task assigned
     */
    @ActivityMethod
    @Nonnull
    TaskAssignResponseResource assignTask(@Nonnull TaskAssignRequestResource request);

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
    LogResponseResource appendTaskLog(@Nonnull LogRequestResource request);
}
