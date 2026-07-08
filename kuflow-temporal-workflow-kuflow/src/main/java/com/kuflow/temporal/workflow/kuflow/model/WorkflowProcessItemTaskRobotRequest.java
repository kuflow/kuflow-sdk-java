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
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class WorkflowProcessItemTaskRobotRequest {

    /**
     * The unique identifier of a process.
     * This identifier is used to track and reference a process in the workflow system.
     */
    private UUID processId;

    /**
     * The identifier of a specific process item within a workflow.
     * This ID is used to uniquely identify and reference a process item.
     */
    private UUID processItemId;

    /**
     * The unique identifier of the robot associated with the workflow process item task.
     */
    private UUID robotId;

    /**
     * The operation to be performed by the robot within the workflow process.
     * This value specifies the nature of the operation the robot is expected to execute.
     */
    private String robotOperation;

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

    /**
     * Free-form bag of additional values associated with this request.
     * Workflows can read entries to receive arbitrary context from the caller
     * without changing the request schema.
     */
    @Nullable
    private Map<String, Object> extras;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public UUID getProcessItemId() {
        return this.processItemId;
    }

    public void setProcessItemId(UUID processItemId) {
        this.processItemId = processItemId;
    }

    public UUID getRobotId() {
        return this.robotId;
    }

    public void setRobotId(UUID robotId) {
        this.robotId = robotId;
    }

    public String getRobotOperation() {
        return this.robotOperation;
    }

    public void setRobotOperation(String robotOperation) {
        this.robotOperation = robotOperation;
    }

    public void setRequestTime(OffsetDateTime requestTime) {
        this.requestTime = requestTime;
    }

    public OffsetDateTime getRequestTime() {
        return this.requestTime;
    }

    public void setRequestTimeZone(ZoneId requestTimeZone) {
        this.requestTimeZone = requestTimeZone;
    }

    public ZoneId getRequestTimeZone() {
        return this.requestTimeZone;
    }

    @Nonnull
    public Map<String, Object> getExtras() {
        if (this.extras == null) {
            return Map.of();
        }

        return Objects.requireNonNull(Collections.unmodifiableMap(this.extras));
    }

    public void setExtras(@Nullable Map<String, Object> extras) {
        if (this.extras == null) {
            this.extras = new HashMap<>();
        }

        final Map<String, Object> currentExtras = Objects.requireNonNull(this.extras);
        currentExtras.clear();

        if (extras != null && !extras.isEmpty()) {
            currentExtras.putAll(extras);
        }
    }

    public void putExtraItem(@Nonnull String name, @Nonnull Object value) {
        Objects.requireNonNull(name, "'name' is required");
        Objects.requireNonNull(value, "'value' is required");

        if (this.extras == null) {
            this.extras = new HashMap<>();
        }

        Objects.requireNonNull(this.extras).put(name, value);
    }

    @Nullable
    public Object getExtraItem(@Nonnull String name) {
        Objects.requireNonNull(name, "'name' is required");

        return this.getExtras().get(name);
    }
}
