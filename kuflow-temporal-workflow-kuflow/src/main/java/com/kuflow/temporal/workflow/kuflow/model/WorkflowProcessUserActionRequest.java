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

public class WorkflowProcessUserActionRequest implements JsonSerializable<SignalProcessItem> {

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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processId", Objects.toString(this.processId, null));
        jsonWriter.writeStringField("userActionDefinitionType", Objects.toString(this.userActionDefinitionType, null));
        jsonWriter.writeStringField("userActionDefinitionCode", this.userActionDefinitionCode);
        jsonWriter.writeStringField("userActionId", Objects.toString(this.userActionId, null));
        jsonWriter.writeStringField("requestorPrincipalId", Objects.toString(this.requestorPrincipalId, null));
        jsonWriter.writeStringField(
            "requestTime",
            this.requestTime != null ? DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.requestTime) : null
        );
        jsonWriter.writeStringField("requestTimeZone", this.requestTimeZone != null ? this.requestTimeZone.toString() : null);
        return jsonWriter.writeEndObject();
    }

    public static WorkflowProcessUserActionRequest fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WorkflowProcessUserActionRequest value = new WorkflowProcessUserActionRequest();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processId".equals(fieldName)) {
                    value.processId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("userActionDefinitionType".equals(fieldName)) {
                    value.userActionDefinitionType = reader.getNullable(nonNullReader ->
                        WorkflowProcessUserActionDefinitionType.fromString(nonNullReader.getString())
                    );
                } else if ("userActionDefinitionCode".equals(fieldName)) {
                    value.userActionDefinitionCode = reader.getNullable(JsonReader::getString);
                } else if ("userActionId".equals(fieldName)) {
                    value.userActionId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
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
