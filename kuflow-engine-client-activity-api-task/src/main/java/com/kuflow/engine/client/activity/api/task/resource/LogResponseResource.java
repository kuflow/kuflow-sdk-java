/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.task.resource;

import com.kuflow.engine.client.common.api.resource.TaskResource;
import com.kuflow.engine.client.common.resource.AbstractResource;

public class LogResponseResource extends AbstractResource {

    private TaskResource task;

    public TaskResource getTask() {
        return this.task;
    }

    public void setTask(TaskResource task) {
        this.task = task;
    }
}
