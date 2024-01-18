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
import java.time.OffsetDateTime;
import java.util.List;

/** Default error. */
@Fluent
public final class DefaultError {

    /*
     * Timestamp indicating when the error happened.
     */
    @JsonProperty(value = "timestamp", required = true)
    private OffsetDateTime timestamp;

    /*
     * HTTP Status
     */
    @JsonProperty(value = "status", required = true)
    private int status;

    /*
     * Message Status
     */
    @JsonProperty(value = "message", required = true)
    private String message;

    /*
     * Related error information.
     */
    @JsonProperty(value = "errors")
    private List<DefaultErrorInfo> errors;

    /** Creates an instance of DefaultError class. */
    public DefaultError() {}

    /**
     * Get the timestamp property: Timestamp indicating when the error happened.
     *
     * @return the timestamp value.
     */
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Set the timestamp property: Timestamp indicating when the error happened.
     *
     * @param timestamp the timestamp value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get the status property: HTTP Status.
     *
     * @return the status value.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Set the status property: HTTP Status.
     *
     * @param status the status value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setStatus(int status) {
        this.status = status;
        return this;
    }

    /**
     * Get the message property: Message Status.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: Message Status.
     *
     * @param message the message value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the errors property: Related error information.
     *
     * @return the errors value.
     */
    public List<DefaultErrorInfo> getErrors() {
        return this.errors;
    }

    /**
     * Set the errors property: Related error information.
     *
     * @param errors the errors value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setErrors(List<DefaultErrorInfo> errors) {
        this.errors = errors;
        return this;
    }
}
