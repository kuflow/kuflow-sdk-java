/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.email.service;

import com.kuflow.engine.client.activity.api.email.resource.SendMailRequestResource;
import com.kuflow.engine.client.activity.api.email.resource.SendMailResponseResource;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;

@ActivityInterface
public interface EmailActivities {
    /**
     * Send an e-mail. The template and recipients indicated are used.
     *
     * @param request must not be {@literal null}.
     * @return
     */
    @ActivityMethod
    SendMailResponseResource sendMail(SendMailRequestResource request);
}
