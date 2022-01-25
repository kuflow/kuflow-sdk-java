/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.email;

import com.kuflow.engine.client.activity.email.resource.SendMailRequestResource;
import com.kuflow.engine.client.activity.email.resource.SendMailResponseResource;
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
