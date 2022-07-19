/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.ProcessElementValueWrapperResource;
import java.util.UUID;

public class SaveProcessElementRequestResource extends AbstractResource {

    private UUID processId;

    private String elementDefinitioCode;

    private ProcessElementValueWrapperResource value;

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

    public ProcessElementValueWrapperResource getValue() {
        return this.value;
    }

    public void setValue(ProcessElementValueWrapperResource value) {
        this.value = value;
    }
}
