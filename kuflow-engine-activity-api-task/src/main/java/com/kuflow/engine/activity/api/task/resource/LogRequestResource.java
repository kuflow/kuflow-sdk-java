/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.activity.api.task.resource;

import com.kuflow.engine.api.resource.LogLevelResource;
import com.kuflow.engine.common.resource.AbstractResource;
import java.util.UUID;

public class LogRequestResource extends AbstractResource {

    private UUID taskId;

    private UUID logId;

    private String message;

    private LogLevelResource level;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getLogId() {
        return this.logId;
    }

    public void setLogId(UUID logId) {
        this.logId = logId;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LogLevelResource getLevel() {
        return this.level;
    }

    public void setLevel(LogLevelResource level) {
        this.level = level;
    }
}
