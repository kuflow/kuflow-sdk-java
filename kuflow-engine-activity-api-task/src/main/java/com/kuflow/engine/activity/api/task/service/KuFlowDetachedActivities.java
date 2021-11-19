/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.activity.api.task.service;

import com.kuflow.engine.activity.api.task.resource.TaskRequestResource;
import com.kuflow.engine.activity.api.task.resource.TaskResponseResource;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

/**
 * Kuflow activities to be used in Temporal.
 *
 * The purpose of this interface is to be used by activities that implement
 * the {@link io.temporal.activity.ActivityExecutionContext#doNotCompleteOnReturn doNotCompleteOnReturn} method
 * of the execution context of a Temporal's Activity.
 *
 */
@ActivityInterface
public interface KuFlowDetachedActivities {
    /**
     * Create a Task and optionally fill its elements.
     *
     * If you want the method to be idempotent, please specify the id field in {@link TaskCompleteRequestResource}.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    TaskResponseResource createDetachedTask(TaskRequestResource request);
}
