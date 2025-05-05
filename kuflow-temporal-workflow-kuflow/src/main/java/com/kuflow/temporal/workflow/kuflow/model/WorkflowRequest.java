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
package com.kuflow.temporal.workflow.kuflow.model;

import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;

public class WorkflowRequest {

    /**
     * Unique identifier for a specific process.
     * This identifier is used to track and reference a process in the workflow system.
     */
    private UUID processId;

    /**
     * The timestamp when the request was made.
     * This field is used to record the exact time the associated robot operation
     * or process item task was requested.
     */
    private OffsetDateTime requestTime;

    /**
     * The time zone associated with the request.
     * This allows timestamp values to be interpreted in the context of a specific geographical region
     * or offset from UTC.
     */
    private ZoneId requestTimeZone;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public OffsetDateTime getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTime(OffsetDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public ZoneId getRequestTimeZone() {
        return this.requestTimeZone;
    }

    public void setRequestTimeZone(ZoneId requestTimeZone) {
        this.requestTimeZone = requestTimeZone;
    }
}
