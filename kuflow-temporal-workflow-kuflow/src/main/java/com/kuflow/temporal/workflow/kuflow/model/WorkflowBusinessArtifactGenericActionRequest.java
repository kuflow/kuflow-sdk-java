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

/**
 * Request payload sent to the user's workflow when a GENERIC action is invoked from the business artifact list,
 * without selecting artifacts. Unlike {@link WorkflowBusinessArtifactBulkActionRequest} there are no items and no
 * action values: the workflow decides what to do (for example, create artifacts through the API for
 * {@code businessArtifactDefinitionId}). The optional validated {@code input} therefore travels inline, and the
 * documents it references are downloaded through the execution-scoped download operation using
 * {@code businessArtifactBatchExecutionId} — they remain available until this workflow reaches a terminal status.
 */
public class WorkflowBusinessArtifactGenericActionRequest {

    /**
     * The unique identifier of the business artifact definition the action belongs to: the definition this workflow
     * acts for.
     */
    private UUID businessArtifactDefinitionId;

    /**
     * The type of action definition associated with this action request. Generic executions are only supported for
     * {@code START_WORKFLOW} actions today.
     */
    private WorkflowBusinessArtifactActionDefinitionType businessArtifactActionDefinitionType;

    /**
     * The code that defines an action.
     * This code is used to identify and differentiate among various actions
     * within a workflow system.
     */
    private String businessArtifactActionDefinitionCode;

    /**
     * The unique identifier of the execution that fired this workflow: the audit record of the generic invocation and
     * the scope of its input documents.
     */
    private UUID businessArtifactBatchExecutionId;

    /**
     * The unique identifier of the principal (user or system) that requested the generic execution.
     * This field is used to associate the action with the entity that requested it.
     */
    private UUID requestorPrincipalId;

    /**
     * The timestamp when the generic execution was requested.
     */
    private OffsetDateTime requestTime;

    /**
     * The time zone associated with the request.
     * This allows timestamp values to be interpreted in the context of a specific geographical region
     * or offset from UTC.
     */
    private ZoneId requestTimeZone;

    /**
     * The validated action input, when the action definition declares an input form; empty otherwise. It travels
     * inline because a generic execution has no action values the workflow could retrieve it from.
     */
    @Nullable
    private Map<String, Object> input;

    /**
     * Free-form bag of additional values associated with this request.
     * Workflows can read entries to receive arbitrary context from the caller
     * without changing the request schema.
     */
    @Nullable
    private Map<String, Object> extras;

    public UUID getBusinessArtifactDefinitionId() {
        return this.businessArtifactDefinitionId;
    }

    public void setBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        this.businessArtifactDefinitionId = businessArtifactDefinitionId;
    }

    public WorkflowBusinessArtifactActionDefinitionType getBusinessArtifactActionDefinitionType() {
        return this.businessArtifactActionDefinitionType;
    }

    public void setBusinessArtifactActionDefinitionType(WorkflowBusinessArtifactActionDefinitionType businessArtifactActionDefinitionType) {
        this.businessArtifactActionDefinitionType = businessArtifactActionDefinitionType;
    }

    public String getBusinessArtifactActionDefinitionCode() {
        return this.businessArtifactActionDefinitionCode;
    }

    public void setBusinessArtifactActionDefinitionCode(String businessArtifactActionDefinitionCode) {
        this.businessArtifactActionDefinitionCode = businessArtifactActionDefinitionCode;
    }

    public UUID getBusinessArtifactBatchExecutionId() {
        return this.businessArtifactBatchExecutionId;
    }

    public void setBusinessArtifactBatchExecutionId(UUID businessArtifactBatchExecutionId) {
        this.businessArtifactBatchExecutionId = businessArtifactBatchExecutionId;
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
    public Map<String, Object> getInput() {
        if (this.input == null) {
            return Map.of();
        }

        return Collections.unmodifiableMap(this.input);
    }

    public void setInput(@Nullable Map<String, Object> input) {
        if (this.input == null) {
            this.input = new HashMap<>();
        }

        final Map<String, Object> currentInput = Objects.requireNonNull(this.input);
        currentInput.clear();

        if (input != null && !input.isEmpty()) {
            currentInput.putAll(input);
        }
    }

    @Nonnull
    public Map<String, Object> getExtras() {
        if (this.extras == null) {
            return Map.of();
        }

        return Collections.unmodifiableMap(this.extras);
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
