/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class DeleteTaskElementValueDocumentRequestResource extends AbstractResource {

    private UUID taskId;

    private UUID documentId;

    public UUID getTaskId() {
        return this.taskId;
    }

    public void setTaskId(UUID taskId) {
        this.taskId = taskId;
    }

    public UUID getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(UUID documentId) {
        this.documentId = documentId;
    }
}
