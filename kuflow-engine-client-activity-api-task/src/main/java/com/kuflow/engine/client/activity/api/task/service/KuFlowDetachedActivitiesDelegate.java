/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.task.service;

import com.kuflow.engine.client.activity.api.task.resource.TaskRequestResource;
import com.kuflow.engine.client.activity.api.task.resource.TaskResponseResource;

public class KuFlowDetachedActivitiesDelegate implements KuFlowDetachedActivities {

    private final KuFlowDetachedActivities delegate;

    public KuFlowDetachedActivitiesDelegate(KuFlowDetachedActivities delegate) {
        this.delegate = delegate;
    }

    @Override
    public TaskResponseResource createDetachedTask(TaskRequestResource request) {
        return this.delegate.createDetachedTask(request);
    }
}
