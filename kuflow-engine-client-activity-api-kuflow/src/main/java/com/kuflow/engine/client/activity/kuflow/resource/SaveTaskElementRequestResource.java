/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.TaskElementValueWrapperResource;
import java.util.UUID;

public class SaveTaskElementRequestResource extends AbstractResource {

    private UUID taskId;

    private String elementDefinitioCode;

    private TaskElementValueWrapperResource value;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public String getElementDefinitioCode() {
        return this.elementDefinitioCode;
    }

    public void setElementDefinitioCode(String elementDefinitioCode) {
        this.elementDefinitioCode = elementDefinitioCode;
    }

    public TaskElementValueWrapperResource getValue() {
        return this.value;
    }

    public void setValue(TaskElementValueWrapperResource value) {
        this.value = value;
    }
}
