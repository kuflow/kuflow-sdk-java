/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.activity.api.task.resource;

import com.kuflow.common.resource.AbstractResource;

public class CompleteProcessResponseResource extends AbstractResource {

    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
