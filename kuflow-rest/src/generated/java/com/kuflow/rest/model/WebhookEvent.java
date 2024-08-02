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
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * The WebhookEvent model.
 */
@Fluent
public class WebhookEvent implements JsonSerializable<WebhookEvent> {

    /*
     * Type of the Event.
     */
    private WebhookType type = WebhookType.fromString("WebhookEvent");

    /*
     * The id property.
     */
    private UUID id;

    /*
     * The version property.
     */
    private String version;

    /*
     * The timestamp property.
     */
    private OffsetDateTime timestamp;

    /**
     * Creates an instance of WebhookEvent class.
     */
    public WebhookEvent() {}

    /**
     * Get the type property: Type of the Event.
     *
     * @return the type value.
     */
    public WebhookType getType() {
        return this.type;
    }

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the WebhookEvent object itself.
     */
    public WebhookEvent setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the version property: The version property.
     *
     * @return the version value.
     */
    public String getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version property.
     *
     * @param version the version value to set.
     * @return the WebhookEvent object itself.
     */
    public WebhookEvent setVersion(String version) {
        this.version = version;
        return this;
    }

    /**
     * Get the timestamp property: The timestamp property.
     *
     * @return the timestamp value.
     */
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Set the timestamp property: The timestamp property.
     *
     * @param timestamp the timestamp value to set.
     * @return the WebhookEvent object itself.
     */
    public WebhookEvent setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("version", this.version);
        jsonWriter.writeStringField(
            "timestamp",
            this.timestamp == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.timestamp)
        );
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WebhookEvent from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WebhookEvent if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WebhookEvent.
     */
    public static WebhookEvent fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            String discriminatorValue = null;
            try (JsonReader readerToUse = reader.bufferObject()) {
                readerToUse.nextToken(); // Prepare for reading
                while (readerToUse.nextToken() != JsonToken.END_OBJECT) {
                    String fieldName = readerToUse.getFieldName();
                    readerToUse.nextToken();
                    if ("type".equals(fieldName)) {
                        discriminatorValue = readerToUse.getString();
                        break;
                    } else {
                        readerToUse.skipChildren();
                    }
                }
                // Use the discriminator value to determine which subtype should be deserialized.
                if ("PROCESS.CREATED".equals(discriminatorValue)) {
                    return WebhookEventProcessCreated.fromJson(readerToUse.reset());
                } else if ("PROCESS.STATE_CHANGED".equals(discriminatorValue)) {
                    return WebhookEventProcessStateChanged.fromJson(readerToUse.reset());
                } else if ("PROCESS_ITEM.CREATED".equals(discriminatorValue)) {
                    return WebhookEventProcessItemCreated.fromJson(readerToUse.reset());
                } else if ("PROCESS_ITEM.TASK_STATE_CHANGED".equals(discriminatorValue)) {
                    return WebhookEventProcessItemTaskStateChanged.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static WebhookEvent fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WebhookEvent deserializedWebhookEvent = new WebhookEvent();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedWebhookEvent.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("version".equals(fieldName)) {
                    deserializedWebhookEvent.version = reader.getString();
                } else if ("timestamp".equals(fieldName)) {
                    deserializedWebhookEvent.timestamp = reader.getNullable(
                        nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("type".equals(fieldName)) {
                    deserializedWebhookEvent.type = WebhookType.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWebhookEvent;
        });
    }
}
