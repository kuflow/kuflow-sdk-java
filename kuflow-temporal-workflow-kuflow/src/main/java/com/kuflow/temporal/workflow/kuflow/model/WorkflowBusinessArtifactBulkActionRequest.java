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

import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Request payload sent to the user's workflow when a BULK batch action is triggered on a selection of business
 * artifacts. Unlike {@link WorkflowBusinessArtifactActionRequest}, a single workflow receives the whole materialized
 * selection as {@code items}: one action value exists per artifact — all sharing this workflow's instance id — and the
 * workflow retrieves each artifact's validated input (and downloads its per-artifact document copies) through the
 * artifact-scoped API using the pair. {@code businessArtifactBatchExecutionId} is the correlation key back to the
 * batch execution that fired the workflow.
 */
public class WorkflowBusinessArtifactBulkActionRequest implements JsonSerializable<WorkflowBusinessArtifactBulkActionRequest> {

    /**
     * The materialized selection: one entry per business artifact that passed the per-artifact checks, in ascending
     * business artifact identifier order. At most 1000 entries (the server-side materialization cap).
     */
    @Nullable
    private List<WorkflowBusinessArtifactBulkActionRequestItem> items;

    /**
     * The type of action definition associated with this action request. Bulk executions are only supported for
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
     * The unique identifier of the batch execution that fired this workflow: the audit record of the bulk invocation
     * (counters, selection snapshot and workflow status live there).
     */
    private UUID businessArtifactBatchExecutionId;

    /**
     * The unique identifier of the principal (user or system) that requested the batch execution.
     * This field is used to associate the bulk action with the entity that requested it.
     */
    private UUID requestorPrincipalId;

    /**
     * The timestamp when the batch execution was requested.
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

    @Nonnull
    public List<WorkflowBusinessArtifactBulkActionRequestItem> getItems() {
        if (this.items == null) {
            return List.of();
        }

        return Objects.requireNonNull(Collections.unmodifiableList(this.items));
    }

    public void setItems(@Nullable List<WorkflowBusinessArtifactBulkActionRequestItem> items) {
        if (this.items == null) {
            this.items = new LinkedList<>();
        }

        final List<WorkflowBusinessArtifactBulkActionRequestItem> currentItems = Objects.requireNonNull(this.items);
        currentItems.clear();

        if (items != null && !items.isEmpty()) {
            currentItems.addAll(items);
        }
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("items", this.items, (writer, element) -> element.toJson(writer));
        jsonWriter.writeStringField(
            "businessArtifactActionDefinitionType",
            Objects.toString(this.businessArtifactActionDefinitionType, null)
        );
        jsonWriter.writeStringField("businessArtifactActionDefinitionCode", this.businessArtifactActionDefinitionCode);
        jsonWriter.writeStringField("businessArtifactBatchExecutionId", Objects.toString(this.businessArtifactBatchExecutionId, null));
        jsonWriter.writeStringField("requestorPrincipalId", Objects.toString(this.requestorPrincipalId, null));
        jsonWriter.writeStringField(
            "requestTime",
            this.requestTime != null ? DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.requestTime) : null
        );
        jsonWriter.writeStringField("requestTimeZone", this.requestTimeZone != null ? this.requestTimeZone.toString() : null);
        return jsonWriter.writeEndObject();
    }

    public static WorkflowBusinessArtifactBulkActionRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WorkflowBusinessArtifactBulkActionRequest value = new WorkflowBusinessArtifactBulkActionRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("items".equals(fieldName)) {
                    value.items = reader.readArray(WorkflowBusinessArtifactBulkActionRequestItem::fromJson);
                } else if ("businessArtifactActionDefinitionType".equals(fieldName)) {
                    value.businessArtifactActionDefinitionType = reader.getNullable(nonNullReader ->
                        WorkflowBusinessArtifactActionDefinitionType.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactActionDefinitionCode".equals(fieldName)) {
                    value.businessArtifactActionDefinitionCode = reader.getNullable(JsonReader::getString);
                } else if ("businessArtifactBatchExecutionId".equals(fieldName)) {
                    value.businessArtifactBatchExecutionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("requestorPrincipalId".equals(fieldName)) {
                    value.requestorPrincipalId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("requestTime".equals(fieldName)) {
                    value.requestTime = reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()));
                } else if ("requestTimeZone".equals(fieldName)) {
                    value.requestTimeZone = reader.getNullable(nonNullReader -> ZoneId.of(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return value;
        });
    }
}
