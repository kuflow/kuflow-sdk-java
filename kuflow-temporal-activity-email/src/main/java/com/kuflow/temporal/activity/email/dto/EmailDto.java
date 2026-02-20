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
package com.kuflow.temporal.activity.email.dto;

import com.kuflow.temporal.activity.email.model.EmailAttachment;
import com.kuflow.temporal.activity.email.model.EmailPriority;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class EmailDto {

    public static EmailDtoBuilder builder() {
        return new EmailDtoBuilder();
    }

    @Nonnull
    private final String to;

    @Nonnull
    private final String template;

    @Nonnull
    private final Map<String, Object> variables;

    @Nonnull
    private final Locale locale;

    @Nonnull
    private final List<String> toAddresses;

    @Nonnull
    private final List<String> ccAddresses;

    @Nonnull
    private final List<String> bccAddresses;

    @Nonnull
    private final List<EmailAttachment> attachments;

    @Nullable
    private final String subjectOverride;

    @Nullable
    private final String replyTo;

    @Nullable
    private final String fromName;

    @Nullable
    private final EmailPriority priority;

    private EmailDto(
        @Nullable String to,
        @Nonnull String template,
        @Nonnull Map<String, Object> variables,
        @Nullable Locale locale,
        @Nonnull List<String> toAddresses,
        @Nonnull List<String> ccAddresses,
        @Nonnull List<String> bccAddresses,
        @Nonnull List<EmailAttachment> attachments,
        @Nullable String subjectOverride,
        @Nullable String replyTo,
        @Nullable String fromName,
        @Nullable EmailPriority priority
    ) {
        Objects.requireNonNull(template, "'template' is required");

        // Merge deprecated 'to' with toAddresses for backward compatibility
        List<String> mergedToAddresses = new LinkedList<>();
        if (to != null && !to.trim().isEmpty()) {
            mergedToAddresses.add(to);
            this.to = to;
        } else {
            this.to = toAddresses.isEmpty() ? "" : toAddresses.get(0);
        }
        mergedToAddresses.addAll(toAddresses);

        // Validate at least one recipient exists
        if (mergedToAddresses.isEmpty() && ccAddresses.isEmpty() && bccAddresses.isEmpty()) {
            throw new IllegalArgumentException("At least one recipient (to/cc/bcc) is required");
        }

        this.template = template;
        this.variables = Collections.unmodifiableMap(variables);
        this.locale = Optional.ofNullable(locale).orElse(Locale.ENGLISH);
        this.toAddresses = Collections.unmodifiableList(mergedToAddresses);
        this.ccAddresses = Collections.unmodifiableList(new LinkedList<>(ccAddresses));
        this.bccAddresses = Collections.unmodifiableList(new LinkedList<>(bccAddresses));
        this.attachments = Collections.unmodifiableList(new LinkedList<>(attachments));
        this.subjectOverride = subjectOverride;
        this.replyTo = replyTo;
        this.fromName = fromName;
        this.priority = priority;
    }

    @Nonnull
    public String getTo() {
        return this.to;
    }

    @Nonnull
    public String getTemplate() {
        return this.template;
    }

    @Nonnull
    public Locale getLocale() {
        return this.locale;
    }

    @Nonnull
    public Map<String, Object> getVariables() {
        return this.variables;
    }

    @Nonnull
    public List<String> getToAddresses() {
        return this.toAddresses;
    }

    @Nonnull
    public List<String> getCcAddresses() {
        return this.ccAddresses;
    }

    @Nonnull
    public List<String> getBccAddresses() {
        return this.bccAddresses;
    }

    @Nonnull
    public List<EmailAttachment> getAttachments() {
        return this.attachments;
    }

    @Nullable
    public String getSubjectOverride() {
        return this.subjectOverride;
    }

    @Nullable
    public String getReplyTo() {
        return this.replyTo;
    }

    @Nullable
    public String getFromName() {
        return this.fromName;
    }

    @Nullable
    public EmailPriority getPriority() {
        return this.priority;
    }

    public static final class EmailDtoBuilder {

        private String to;

        private String template;

        private Locale locale;

        private final Map<String, Object> variables = new HashMap<>();

        private final List<String> toAddresses = new LinkedList<>();

        private final List<String> ccAddresses = new LinkedList<>();

        private final List<String> bccAddresses = new LinkedList<>();

        private final List<EmailAttachment> attachments = new LinkedList<>();

        private String subjectOverride;

        private String replyTo;

        private String fromName;

        private EmailPriority priority;

        private EmailDtoBuilder() {}

        public EmailDtoBuilder withTo(String to) {
            this.to = to;

            return this;
        }

        public EmailDtoBuilder withTemplate(String template) {
            this.template = template;

            return this;
        }

        public EmailDtoBuilder withLocale(Locale locale) {
            this.locale = locale;

            return this;
        }

        public EmailDtoBuilder withVariables(Map<String, Object> variables) {
            this.variables.clear();
            this.variables.putAll(variables);

            return this;
        }

        public EmailDtoBuilder addVariable(String name, Object value) {
            this.variables.put(name, value);

            return this;
        }

        public EmailDtoBuilder withToAddresses(List<String> toAddresses) {
            this.toAddresses.clear();
            if (toAddresses != null) {
                this.toAddresses.addAll(toAddresses);
            }

            return this;
        }

        public EmailDtoBuilder addToAddress(String toAddress) {
            if (toAddress != null && !this.toAddresses.contains(toAddress)) {
                this.toAddresses.add(toAddress);
            }

            return this;
        }

        public EmailDtoBuilder withCcAddresses(List<String> ccAddresses) {
            this.ccAddresses.clear();
            if (ccAddresses != null) {
                this.ccAddresses.addAll(ccAddresses);
            }

            return this;
        }

        public EmailDtoBuilder addCcAddress(String ccAddress) {
            if (ccAddress != null && !this.ccAddresses.contains(ccAddress)) {
                this.ccAddresses.add(ccAddress);
            }

            return this;
        }

        public EmailDtoBuilder withBccAddresses(List<String> bccAddresses) {
            this.bccAddresses.clear();
            if (bccAddresses != null) {
                this.bccAddresses.addAll(bccAddresses);
            }

            return this;
        }

        public EmailDtoBuilder addBccAddress(String bccAddress) {
            if (bccAddress != null && !this.bccAddresses.contains(bccAddress)) {
                this.bccAddresses.add(bccAddress);
            }

            return this;
        }

        public EmailDtoBuilder withAttachments(List<EmailAttachment> attachments) {
            this.attachments.clear();
            if (attachments != null) {
                this.attachments.addAll(attachments);
            }

            return this;
        }

        public EmailDtoBuilder addAttachment(EmailAttachment attachment) {
            if (attachment != null) {
                this.attachments.add(attachment);
            }

            return this;
        }

        public EmailDtoBuilder withSubjectOverride(String subjectOverride) {
            this.subjectOverride = subjectOverride;

            return this;
        }

        public EmailDtoBuilder withReplyTo(String replyTo) {
            this.replyTo = replyTo;

            return this;
        }

        public EmailDtoBuilder withFromName(String fromName) {
            this.fromName = fromName;

            return this;
        }

        public EmailDtoBuilder withPriority(EmailPriority priority) {
            this.priority = priority;

            return this;
        }

        public EmailDto build() {
            return new EmailDto(
                this.to,
                this.template,
                this.variables,
                this.locale,
                this.toAddresses,
                this.ccAddresses,
                this.bccAddresses,
                this.attachments,
                this.subjectOverride,
                this.replyTo,
                this.fromName,
                this.priority
            );
        }
    }
}
