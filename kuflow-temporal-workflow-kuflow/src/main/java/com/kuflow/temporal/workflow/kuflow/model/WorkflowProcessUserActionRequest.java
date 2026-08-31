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

public class WorkflowProcessUserActionRequest {

    /**
     * The unique identifier of a process.
     * This identifier is used to track, manage, and reference a specific process
     * within the workflow system.
     */
    private UUID processId;

    /**
     * The type of user action definition associated with this user action request.
     * This variable indicates the specific action type that a user has initiated.
     */
    private WorkflowProcessUserActionDefinitionType userActionDefinitionType;

    /**
     * The code that defines a user action.
     * This code is used to identify and differentiate among various user actions
     * within a workflow system.
     */
    private String userActionDefinitionCode;

    /**
     * The unique identifier for a user action.
     * This identifier is used to track and manage a specific user action
     * within the workflow system.
     */
    private UUID userActionId;

    /**
     * The unique identifier of the principal (user or system)
     * who initiated the user action.
     * This field is used to associate the user action with the entity that requested it.
     */
    private UUID requestorPrincipalId;

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

    public WorkflowProcessUserActionDefinitionType getUserActionDefinitionType() {
        return this.userActionDefinitionType;
    }

    public void setUserActionDefinitionType(WorkflowProcessUserActionDefinitionType userActionDefinitionType) {
        this.userActionDefinitionType = userActionDefinitionType;
    }

    public String getUserActionDefinitionCode() {
        return this.userActionDefinitionCode;
    }

    public void setUserActionDefinitionCode(String userActionDefinitionCode) {
        this.userActionDefinitionCode = userActionDefinitionCode;
    }

    public UUID getUserActionId() {
        return this.userActionId;
    }

    public void setUserActionId(UUID userActionId) {
        this.userActionId = userActionId;
    }

    public UUID getRequestorPrincipalId() {
        return this.requestorPrincipalId;
    }

    public void setRequestorPrincipalId(UUID requestorPrincipalId) {
        this.requestorPrincipalId = requestorPrincipalId;
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

    @Nonnull
    public Map<String, Object> getExtras() {
        if (this.extras == null) {
            return Objects.requireNonNull(Map.of());
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
