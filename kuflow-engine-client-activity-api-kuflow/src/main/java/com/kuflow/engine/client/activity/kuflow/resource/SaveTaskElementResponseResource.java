/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.TaskResource;

public class SaveTaskElementResponseResource extends AbstractResource {

    private TaskResource task;

    public TaskResource getTask() {
        return this.task;
    }

    public void setTask(TaskResource task) {
        this.task = task;
    }
}
