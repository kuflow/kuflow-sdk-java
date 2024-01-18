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
package com.kuflow.rest.model;

import com.azure.core.annotation.Fluent;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.UUID;

/**
 * Command to change the process initiator, only one option is required.
 */
@Fluent
public final class ProcessChangeInitiatorCommand {

    /*
     * The principalId property.
     */
    @JsonProperty(value = "principalId")
    private UUID principalId;

    /*
     * The email property.
     */
    @JsonProperty(value = "email")
    private String email;

    /**
     * Creates an instance of ProcessChangeInitiatorCommand class.
     */
    public ProcessChangeInitiatorCommand() {}

    /**
     * Get the principalId property: The principalId property.
     *
     * @return the principalId value.
     */
    public UUID getPrincipalId() {
        return this.principalId;
    }

    /**
     * Set the principalId property: The principalId property.
     *
     * @param principalId the principalId value to set.
     * @return the ProcessChangeInitiatorCommand object itself.
     */
    public ProcessChangeInitiatorCommand setPrincipalId(UUID principalId) {
        this.principalId = principalId;
        return this;
    }

    /**
     * Get the email property: The email property.
     *
     * @return the email value.
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email property: The email property.
     *
     * @param email the email value to set.
     * @return the ProcessChangeInitiatorCommand object itself.
     */
    public ProcessChangeInitiatorCommand setEmail(String email) {
        this.email = email;
        return this;
    }
}
