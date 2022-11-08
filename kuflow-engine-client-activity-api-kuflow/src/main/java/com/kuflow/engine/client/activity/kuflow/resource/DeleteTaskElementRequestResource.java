/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class DeleteTaskElementRequestResource extends AbstractResource {

    private UUID taskId;

    private String elementDefinitioCode;

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
}
