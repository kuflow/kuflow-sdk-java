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
import com.azure.core.annotation.Generated;
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * Process Events.
 */
@Fluent
public final class WebhookEventProcessItemCreated extends WebhookEvent {

    /*
     * Type of the Event.
     */
    @Generated
    private WebhookType type = WebhookType.PROCESS_ITEM_CREATED;

    /*
     * The data property.
     */
    @Generated
    private WebhookEventProcessItemCreatedData data;

    /**
     * Creates an instance of WebhookEventProcessItemCreated class.
     */
    @Generated
    public WebhookEventProcessItemCreated() {}

    /**
     * Get the type property: Type of the Event.
     *
     * @return the type value.
     */
    @Generated
    @Override
    public WebhookType getType() {
        return this.type;
    }

    /**
     * Get the data property: The data property.
     *
     * @return the data value.
     */
    @Generated
    public WebhookEventProcessItemCreatedData getData() {
        return this.data;
    }

    /**
     * Set the data property: The data property.
     *
     * @param data the data value to set.
     * @return the WebhookEventProcessItemCreated object itself.
     */
    @Generated
    public WebhookEventProcessItemCreated setData(WebhookEventProcessItemCreatedData data) {
        this.data = data;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public WebhookEventProcessItemCreated setId(UUID id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public WebhookEventProcessItemCreated setVersion(String version) {
        super.setVersion(version);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public WebhookEventProcessItemCreated setTimestamp(OffsetDateTime timestamp) {
        super.setTimestamp(timestamp);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(getId(), null));
        jsonWriter.writeStringField("version", getVersion());
        jsonWriter.writeStringField(
            "timestamp",
            getTimestamp() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getTimestamp())
        );
        jsonWriter.writeJsonField("data", this.data);
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WebhookEventProcessItemCreated from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WebhookEventProcessItemCreated if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WebhookEventProcessItemCreated.
     */
    @Generated
    public static WebhookEventProcessItemCreated fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WebhookEventProcessItemCreated deserializedWebhookEventProcessItemCreated = new WebhookEventProcessItemCreated();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedWebhookEventProcessItemCreated.setId(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("version".equals(fieldName)) {
                    deserializedWebhookEventProcessItemCreated.setVersion(reader.getString());
                } else if ("timestamp".equals(fieldName)) {
                    deserializedWebhookEventProcessItemCreated.setTimestamp(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("data".equals(fieldName)) {
                    deserializedWebhookEventProcessItemCreated.data = WebhookEventProcessItemCreatedData.fromJson(reader);
                } else if ("type".equals(fieldName)) {
                    deserializedWebhookEventProcessItemCreated.type = WebhookType.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWebhookEventProcessItemCreated;
        });
    }
}
