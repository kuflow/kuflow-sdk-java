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

public class SignalProcessItem implements JsonSerializable<SignalProcessItem> {

    /**
     * Represents the unique identifier for a SignalProcessItem.
     * This identifier is used to distinguish and refer to a specific signal process item in the workflow system.
     */
    private UUID id;

    /**
     * Defines the type of the signal processing item.
     * This field specifies the category or nature of the item being processed
     * in the workflow system, such as tasks, messages, or threads.
     */
    private SignalProcessItemType type;

    /**
     * Represents the payload associated with a specific signal processing item.
     * This object contains information defining the specific item within a process
     * as well as its associated data structure.
     */
    private SignalProcessItemPayload payload;

    /**
     * Represents the unique identifier of the principal (user or system)
     * who initiated the user action.
     * This field is used to associate the user action with the entity that requested it.
     */
    private OffsetDateTime requestTime;

    /**
     * The timestamp when the request was made.
     * This field is used to record the exact time the associated robot operation
     * or process item task was requested.
     */
    private ZoneId requestTimeZone;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public SignalProcessItemType getType() {
        return this.type;
    }

    public void setType(SignalProcessItemType type) {
        this.type = type;
    }

    public SignalProcessItemPayload getPayload() {
        return this.payload;
    }

    public void setPayload(SignalProcessItemPayload payload) {
        this.payload = payload;
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

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeJsonField("payload", this.payload);
        jsonWriter.writeStringField(
            "requestTime",
            this.requestTime != null ? DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.requestTime) : null
        );
        jsonWriter.writeStringField("requestTimeZone", this.requestTimeZone != null ? this.requestTimeZone.toString() : null);

        return jsonWriter.writeEndObject();
    }

    public static SignalProcessItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            SignalProcessItem deserializedSignalProcessItem = new SignalProcessItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedSignalProcessItem.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("type".equals(fieldName)) {
                    deserializedSignalProcessItem.type = SignalProcessItemType.fromString(reader.getString());
                } else if ("payload".equals(fieldName)) {
                    deserializedSignalProcessItem.payload = SignalProcessItemPayload.fromJson(reader);
                } else if ("requestTime".equals(fieldName)) {
                    deserializedSignalProcessItem.requestTime = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("requestTimeZone".equals(fieldName)) {
                    deserializedSignalProcessItem.requestTimeZone = reader.getNullable(nonNullReader ->
                        ZoneId.of(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedSignalProcessItem;
        });
    }
}
