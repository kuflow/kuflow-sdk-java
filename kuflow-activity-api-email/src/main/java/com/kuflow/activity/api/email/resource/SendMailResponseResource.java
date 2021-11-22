/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.activity.api.email.resource;

import com.kuflow.common.resource.AbstractResource;

public class SendMailResponseResource extends AbstractResource {

    private boolean sent;

    public boolean isSent() {
        return this.sent;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }
}
