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

@ActivityInterface
public interface KuFlowDetachedActivities {
    @ActivityMethod
    TaskResponseResource createDetachedTask(TaskRequestResource request);
}
