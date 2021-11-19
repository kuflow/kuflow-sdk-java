/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.common.resource;

import java.util.UUID;

public class WorkflowRequestResource extends AbstractResource {

    private UUID processId;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }
}
