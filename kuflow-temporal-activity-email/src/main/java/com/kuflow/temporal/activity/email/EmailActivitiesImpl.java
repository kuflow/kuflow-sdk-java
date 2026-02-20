/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.temporal.activity.email;

import com.kuflow.temporal.activity.email.dto.EmailDto;
import com.kuflow.temporal.activity.email.model.Email;
import com.kuflow.temporal.activity.email.model.EmailAttachment;
import com.kuflow.temporal.activity.email.model.SendMailRequest;
import com.kuflow.temporal.activity.email.model.SendMailResponse;
import com.kuflow.temporal.activity.email.service.EmailService;
import io.temporal.failure.ApplicationFailure;
import javax.annotation.Nonnull;

public class EmailActivitiesImpl implements EmailActivities {

    private final EmailService emailService;

    public EmailActivitiesImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Nonnull
    @Override
    public SendMailResponse sendMail(@Nonnull SendMailRequest request) {
        Email email = request.getEmail();

        this.validateEmail(email);

        EmailDto.EmailDtoBuilder builder = EmailDto.builder()
            .withTemplate(email.getTemplate())
            .withVariables(email.getVariables())
            .withLocale(email.getLocale());

        // Merge deprecated 'to' field with new toAddresses for backward compatibility
        if (email.getTo() != null) {
            builder.addToAddress(email.getTo());
        }
        builder
            .withToAddresses(email.getToAddresses())
            .withCcAddresses(email.getCcAddresses())
            .withBccAddresses(email.getBccAddresses())
            .withAttachments(email.getAttachments())
            .withSubjectOverride(email.getSubjectOverride())
            .withReplyTo(email.getReplyTo())
            .withFromName(email.getFromName())
            .withPriority(email.getPriority());

        EmailDto emailDto = builder.build();

        this.emailService.sendEmail(emailDto);

        SendMailResponse response = new SendMailResponse();
        response.setSent(true);

        return response;
    }

    private void validateEmail(Email email) {
        // Check at least one recipient exists (backward compatible with deprecated 'to' field)
        boolean hasRecipients =
            (email.getTo() != null) ||
            (!email.getToAddresses().isEmpty()) ||
            (!email.getCcAddresses().isEmpty()) ||
            (!email.getBccAddresses().isEmpty());

        if (!hasRecipients) {
            throw ApplicationFailure.newNonRetryableFailure(
                "'email' must have at least one recipient (to/cc/bcc)",
                "EmailActivities.validation"
            );
        }

        if (email.getTemplate() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'email.template' is required", "EmailActivities.validation");
        }

        // Validate all attachments have required fields
        for (EmailAttachment attachment : email.getAttachments()) {
            if (attachment.getFilename() == null || attachment.getResource() == null) {
                throw ApplicationFailure.newNonRetryableFailure(
                    "All attachments must have filename and resource",
                    "EmailActivities.validation"
                );
            }
        }
    }
}
