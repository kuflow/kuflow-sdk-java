/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.activity.api.task.resource;

import com.kuflow.common.resource.AbstractResource;
import java.util.UUID;

public class TaskClaimRequestResource extends AbstractResource {

    private UUID taskId;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
}
