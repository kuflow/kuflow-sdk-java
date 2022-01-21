/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.impl.email.service;

import com.kuflow.engine.client.activity.api.email.resource.SendMailRequestResource;
import com.kuflow.engine.client.activity.api.email.resource.SendMailResponseResource;
import com.kuflow.engine.client.activity.api.email.service.EmailActivities;

import javax.annotation.Nonnull;

public class EmailActivitiesDelegate implements EmailActivities {

    private final EmailActivities delegate;

    public EmailActivitiesDelegate(EmailActivities delegate) {
        this.delegate = delegate;
    }

    @Nonnull
    @Override
    public SendMailResponseResource sendMail(@Nonnull SendMailRequestResource request) {
        return this.delegate.sendMail(request);
    }
}
