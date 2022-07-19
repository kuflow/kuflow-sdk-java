/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.TaskPageResource;

public class FindTaskResponseResource extends AbstractResource {

    private TaskPageResource taskPage;

    public TaskPageResource getTaskPage() {
        return this.taskPage;
    }

    public void setTaskPage(TaskPageResource taskPage) {
        this.taskPage = taskPage;
    }
}
