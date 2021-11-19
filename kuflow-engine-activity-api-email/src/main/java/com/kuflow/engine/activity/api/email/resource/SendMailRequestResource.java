/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.activity.api.email.resource;

import com.kuflow.engine.common.resource.AbstractResource;

public class SendMailRequestResource extends AbstractResource {

    private EmailResource email;

    public EmailResource getEmail() {
        return this.email;
    }

    public void setEmail(EmailResource email) {
        this.email = email;
    }
}
