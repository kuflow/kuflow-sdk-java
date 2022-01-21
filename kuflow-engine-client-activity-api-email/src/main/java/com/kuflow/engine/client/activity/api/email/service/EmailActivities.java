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
import javax.annotation.Nonnull;

@ActivityInterface(namePrefix = "KuFlow_Email_")
public interface EmailActivities {
    /**
     * Send an e-mail. The template and recipients indicated are used.
     *
     * @param request must not be {@literal null}.
     * @return the email sent
     */
    @ActivityMethod
    @Nonnull
    SendMailResponseResource sendMail(@Nonnull SendMailRequestResource request);
}
