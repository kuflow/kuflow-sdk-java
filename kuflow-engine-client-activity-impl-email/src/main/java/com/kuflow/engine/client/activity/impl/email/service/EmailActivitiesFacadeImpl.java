/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.impl.email.service;

import com.kuflow.engine.client.activity.api.email.resource.EmailResource;
import com.kuflow.engine.client.activity.api.email.resource.SendMailRequestResource;
import com.kuflow.engine.client.activity.api.email.resource.SendMailResponseResource;
import com.kuflow.engine.client.activity.api.email.service.EmailActivities;
import com.kuflow.engine.client.activity.impl.email.dto.EmailDto;
import javax.annotation.Nonnull;
import org.springframework.stereotype.Component;

@Component
public class EmailActivitiesFacadeImpl implements EmailActivities {

    private final EmailService emailService;

    public EmailActivitiesFacadeImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Nonnull
    @Override
    public SendMailResponseResource sendMail(@Nonnull SendMailRequestResource request) {
        EmailResource resource = request.getEmail();

        EmailDto emailDto = EmailDto
            .builder()
            .withTo(resource.getTo())
            .withTemplate(resource.getTemplate())
            .withVariables(resource.getVariables())
            .withLocale(resource.getLocale())
            .build();

        this.emailService.sendEmail(emailDto);

        SendMailResponseResource response = new SendMailResponseResource();
        response.setSent(true);

        return response;
    }
}
