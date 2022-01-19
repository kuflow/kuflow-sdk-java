/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.task.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class CompleteProcessRequestResource extends AbstractResource {

    private UUID processId;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }
}
