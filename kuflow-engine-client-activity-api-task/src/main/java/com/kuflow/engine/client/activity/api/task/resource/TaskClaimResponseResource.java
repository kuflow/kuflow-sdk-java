/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.task.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class TaskClaimResponseResource extends AbstractResource {

    private UUID taskId;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }
}
