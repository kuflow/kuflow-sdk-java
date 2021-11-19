/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.activity.api.email.service;

import com.kuflow.engine.activity.api.email.resource.SendMailRequestResource;
import com.kuflow.engine.activity.api.email.resource.SendMailResponseResource;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface EmailActivities {
    @ActivityMethod
    SendMailResponseResource sendMail(SendMailRequestResource request);
}
