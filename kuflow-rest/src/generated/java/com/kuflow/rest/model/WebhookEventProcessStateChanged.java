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
public final class WebhookEventProcessStateChanged extends WebhookEvent {

    /*
     * Type of the Event.
     */
    private WebhookType type = WebhookType.PROCESS_STATE_CHANGED;

    /*
     * The data property.
     */
    private WebhookEventProcessStateChangedData data;

    /**
     * Creates an instance of WebhookEventProcessStateChanged class.
     */
    public WebhookEventProcessStateChanged() {}

    /**
     * Get the type property: Type of the Event.
     *
     * @return the type value.
     */
    @Override
    public WebhookType getType() {
        return this.type;
    }

    /**
     * Get the data property: The data property.
     *
     * @return the data value.
     */
    public WebhookEventProcessStateChangedData getData() {
        return this.data;
    }

    /**
     * Set the data property: The data property.
     *
     * @param data the data value to set.
     * @return the WebhookEventProcessStateChanged object itself.
     */
    public WebhookEventProcessStateChanged setData(WebhookEventProcessStateChangedData data) {
        this.data = data;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebhookEventProcessStateChanged setId(UUID id) {
        super.setId(id);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public WebhookEventProcessStateChanged setTimestamp(OffsetDateTime timestamp) {
        super.setTimestamp(timestamp);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(getId(), null));
        jsonWriter.writeStringField(
            "timestamp",
            getTimestamp() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getTimestamp())
        );
        jsonWriter.writeJsonField("data", this.data);
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WebhookEventProcessStateChanged from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WebhookEventProcessStateChanged if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WebhookEventProcessStateChanged.
     */
    public static WebhookEventProcessStateChanged fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WebhookEventProcessStateChanged deserializedWebhookEventProcessStateChanged = new WebhookEventProcessStateChanged();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedWebhookEventProcessStateChanged.setId(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("timestamp".equals(fieldName)) {
                    deserializedWebhookEventProcessStateChanged.setTimestamp(
                        reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()))
                    );
                } else if ("data".equals(fieldName)) {
                    deserializedWebhookEventProcessStateChanged.data = WebhookEventProcessStateChangedData.fromJson(reader);
                } else if ("type".equals(fieldName)) {
                    deserializedWebhookEventProcessStateChanged.type = WebhookType.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWebhookEventProcessStateChanged;
        });
    }
}
