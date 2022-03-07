/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class TaskAssignRequestResource extends AbstractResource {

    /**
     * Task id
     */
    private UUID taskId;

    /**
     * User email that want to use to assign the task. Attribute {@link #email} or {@link #principalId} must be set.
     */
    private String email;

    /**
     * Principal id that want to use to assign the task. Attribute @{link email} or @{link principalId} must be set.
     */
    private UUID principalId;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getPrincipalId() {
        return this.principalId;
    }

    public void setPrincipalId(UUID principalId) {
        this.principalId = principalId;
    }
}
