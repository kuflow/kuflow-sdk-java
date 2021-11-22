/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.activity.impl.email.service;

import com.kuflow.activity.api.email.resource.SendMailRequestResource;
import com.kuflow.activity.api.email.resource.SendMailResponseResource;
import com.kuflow.activity.api.email.service.EmailActivities;

public class EmailActivitiesDelegate implements EmailActivities {

    private final EmailActivities delegate;

    public EmailActivitiesDelegate(EmailActivities delegate) {
        this.delegate = delegate;
    }

    @Override
    public SendMailResponseResource sendMail(SendMailRequestResource request) {
        return this.delegate.sendMail(request);
    }
}
