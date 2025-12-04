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
package com.kuflow.temporal.activity.email.model;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;

public class Email {

    private Locale locale = Locale.getDefault();

    /**
     * @deprecated Use {@link #toAddresses} instead. This field is maintained for backward compatibility.
     */
    @Deprecated
    private String to;

    private String template;

    private final Map<String, Object> variables = new HashMap<>();

    private final List<String> toAddresses = new LinkedList<>();

    private final List<String> ccAddresses = new LinkedList<>();

    private final List<String> bccAddresses = new LinkedList<>();

    private final List<EmailAttachment> attachments = new LinkedList<>();

    private String subjectOverride;

    private String replyTo;

    private EmailPriority priority;

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * @deprecated Use {@link #getToAddresses()} instead. This method is maintained for backward compatibility.
     */
    @Deprecated
    public String getTo() {
        return this.to;
    }

    /**
     * @deprecated Use {@link #addToAddress(String)} or {@link #setToAddresses(List)} instead. This method is maintained for backward compatibility.
     */
    @Deprecated
    public void setTo(String to) {
        this.to = to;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getVariables() {
        return Collections.unmodifiableMap(this.variables);
    }

    public void addVariables(String name, Object value) {
        Objects.requireNonNull(name, "'name' is required");
        Objects.requireNonNull(value, "'value' is required");

        this.variables.put(name, value);
    }

    public List<String> getToAddresses() {
        return Collections.unmodifiableList(this.toAddresses);
    }

    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses.clear();
        if (toAddresses != null) {
            this.toAddresses.addAll(toAddresses);
        }
    }

    public void setToAddress(String toAddress) {
        this.toAddresses.clear();
        if (toAddress != null) {
            this.toAddresses.add(toAddress);
        }
    }

    public void addToAddress(String toAddress) {
        Objects.requireNonNull(toAddress, "'toAddress' is required");
        if (!this.toAddresses.contains(toAddress)) {
            this.toAddresses.add(toAddress);
        }
    }

    public void removeToAddress(String toAddress) {
        this.toAddresses.remove(toAddress);
    }

    public List<String> getCcAddresses() {
        return Collections.unmodifiableList(this.ccAddresses);
    }

    public void setCcAddresses(List<String> ccAddresses) {
        this.ccAddresses.clear();
        if (ccAddresses != null) {
            this.ccAddresses.addAll(ccAddresses);
        }
    }

    public void setCcAddress(String ccAddress) {
        this.ccAddresses.clear();
        if (ccAddress != null) {
            this.ccAddresses.add(ccAddress);
        }
    }

    public void addCcAddress(String ccAddress) {
        Objects.requireNonNull(ccAddress, "'ccAddress' is required");
        if (!this.ccAddresses.contains(ccAddress)) {
            this.ccAddresses.add(ccAddress);
        }
    }

    public void removeCcAddress(String ccAddress) {
        this.ccAddresses.remove(ccAddress);
    }

    public List<String> getBccAddresses() {
        return Collections.unmodifiableList(this.bccAddresses);
    }

    public void setBccAddresses(List<String> bccAddresses) {
        this.bccAddresses.clear();
        if (bccAddresses != null) {
            this.bccAddresses.addAll(bccAddresses);
        }
    }

    public void setBccAddress(String bccAddress) {
        this.bccAddresses.clear();
        if (bccAddress != null) {
            this.bccAddresses.add(bccAddress);
        }
    }

    public void addBccAddress(String bccAddress) {
        Objects.requireNonNull(bccAddress, "'bccAddress' is required");
        if (!this.bccAddresses.contains(bccAddress)) {
            this.bccAddresses.add(bccAddress);
        }
    }

    public void removeBccAddress(String bccAddress) {
        this.bccAddresses.remove(bccAddress);
    }

    public List<EmailAttachment> getAttachments() {
        return Collections.unmodifiableList(this.attachments);
    }

    public void setAttachments(List<EmailAttachment> attachments) {
        this.attachments.clear();
        if (attachments != null) {
            this.attachments.addAll(attachments);
        }
    }

    public void setAttachment(EmailAttachment attachment) {
        this.attachments.clear();
        if (attachment != null) {
            this.attachments.add(attachment);
        }
    }

    public void addAttachment(EmailAttachment attachment) {
        Objects.requireNonNull(attachment, "'attachment' is required");
        this.attachments.add(attachment);
    }

    public void removeAttachment(EmailAttachment attachment) {
        this.attachments.remove(attachment);
    }

    public String getSubjectOverride() {
        return this.subjectOverride;
    }

    public void setSubjectOverride(String subjectOverride) {
        this.subjectOverride = subjectOverride;
    }

    public String getReplyTo() {
        return this.replyTo;
    }

    public void setReplyTo(String replyTo) {
        this.replyTo = replyTo;
    }

    public EmailPriority getPriority() {
        return this.priority;
    }

    public void setPriority(EmailPriority priority) {
        this.priority = priority;
    }
}
