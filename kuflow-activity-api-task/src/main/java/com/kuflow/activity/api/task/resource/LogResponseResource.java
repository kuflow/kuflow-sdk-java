/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.activity.api.task.resource;

import com.kuflow.api.resource.TaskResource;
import com.kuflow.common.resource.AbstractResource;

public class LogResponseResource extends AbstractResource {

    private TaskResource task;

    public TaskResource getTask() {
        return this.task;
    }

    public void setTask(TaskResource task) {
        this.task = task;
    }
}
