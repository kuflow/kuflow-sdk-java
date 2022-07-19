/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class DeleteProcessElementRequestResource extends AbstractResource {

    private UUID processId;

    private String elementDefinitioCode;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public String getElementDefinitioCode() {
        return this.elementDefinitioCode;
    }

    public void setElementDefinitioCode(String elementDefinitioCode) {
        this.elementDefinitioCode = elementDefinitioCode;
    }
}
