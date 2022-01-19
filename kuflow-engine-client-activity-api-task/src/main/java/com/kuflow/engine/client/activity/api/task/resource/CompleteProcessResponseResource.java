/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.task.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;

public class CompleteProcessResponseResource extends AbstractResource {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
