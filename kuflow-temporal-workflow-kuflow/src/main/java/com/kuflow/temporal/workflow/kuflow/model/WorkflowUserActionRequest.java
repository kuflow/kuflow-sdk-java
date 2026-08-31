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

/**
 * @deprecated Use {@link WorkflowProcessUserActionRequest} instead.
 */
@Deprecated
public class WorkflowUserActionRequest {

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
    private WorkflowUserActionDefinitionType userActionDefinitionType;

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

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public WorkflowUserActionDefinitionType getUserActionDefinitionType() {
        return this.userActionDefinitionType;
    }

    public void setUserActionDefinitionType(WorkflowUserActionDefinitionType userActionDefinitionType) {
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
}
