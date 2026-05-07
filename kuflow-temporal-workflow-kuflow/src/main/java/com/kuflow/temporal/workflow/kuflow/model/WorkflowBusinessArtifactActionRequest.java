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
import java.util.Objects;
import java.util.UUID;

/**
 * Request payload sent to the user's workflow when an action is triggered on a business artifact.
 * Carries the artifact reference, the action definition that fired, and metadata about who/when
 * requested it so the workflow can run with the proper context.
 */
public class WorkflowBusinessArtifactActionRequest implements JsonSerializable<WorkflowBusinessArtifactActionRequest> {

    /**
     * The unique identifier of a business artifact.
     * This identifier is used to track, manage, and reference a specific business artifact
     * within the workflow system.
     */
    private UUID businessArtifactId;

    /**
     * The type of action definition associated with this action request.
     * This variable indicates the specific action type that has been initiated.
     */
    private WorkflowBusinessArtifactActionDefinitionType businessArtifactActionDefinitionType;

    /**
     * The code that defines an action.
     * This code is used to identify and differentiate among various actions
     * within a workflow system.
     */
    private String businessArtifactActionDefinitionCode;

    /**
     * The unique identifier for an action.
     * This identifier is used to track and manage a specific action
     * within the workflow system.
     */
    private UUID businessArtifactActionValueId;

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

    public UUID getBusinessArtifactId() {
        return this.businessArtifactId;
    }

    public void setBusinessArtifactId(UUID businessArtifactId) {
        this.businessArtifactId = businessArtifactId;
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

    public UUID getBusinessArtifactActionValueId() {
        return this.businessArtifactActionValueId;
    }

    public void setBusinessArtifactActionValueId(UUID businessArtifactActionValueId) {
        this.businessArtifactActionValueId = businessArtifactActionValueId;
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("businessArtifactId", Objects.toString(this.businessArtifactId, null));
        jsonWriter.writeStringField(
            "businessArtifactActionDefinitionType",
            Objects.toString(this.businessArtifactActionDefinitionType, null)
        );
        jsonWriter.writeStringField("businessArtifactActionDefinitionCode", this.businessArtifactActionDefinitionCode);
        jsonWriter.writeStringField("businessArtifactActionValueId", Objects.toString(this.businessArtifactActionValueId, null));
        jsonWriter.writeStringField("requestorPrincipalId", Objects.toString(this.requestorPrincipalId, null));
        jsonWriter.writeStringField(
            "requestTime",
            this.requestTime != null ? DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.requestTime) : null
        );
        jsonWriter.writeStringField("requestTimeZone", this.requestTimeZone != null ? this.requestTimeZone.toString() : null);
        return jsonWriter.writeEndObject();
    }

    public static WorkflowBusinessArtifactActionRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WorkflowBusinessArtifactActionRequest value = new WorkflowBusinessArtifactActionRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("businessArtifactId".equals(fieldName)) {
                    value.businessArtifactId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("businessArtifactActionDefinitionType".equals(fieldName)) {
                    value.businessArtifactActionDefinitionType = reader.getNullable(nonNullReader ->
                        WorkflowBusinessArtifactActionDefinitionType.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactActionDefinitionCode".equals(fieldName)) {
                    value.businessArtifactActionDefinitionCode = reader.getNullable(JsonReader::getString);
                } else if ("businessArtifactActionValueId".equals(fieldName)) {
                    value.businessArtifactActionValueId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
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
