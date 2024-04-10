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
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * The Log model.
 */
@Fluent
public final class Log implements JsonSerializable<Log> {

    /*
     * The id property.
     */
    private UUID id;

    /*
     * When this model was created.
     */
    private OffsetDateTime createdAt;

    /*
     * The message property.
     */
    private String message;

    /*
     * The level property.
     */
    private LogLevel level;

    /**
     * Creates an instance of Log class.
     */
    public Log() {}

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
     * @return the Log object itself.
     */
    public Log setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the createdAt property: When this model was created.
     *
     * @return the createdAt value.
     */
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Set the createdAt property: When this model was created.
     *
     * @param createdAt the createdAt value to set.
     * @return the Log object itself.
     */
    public Log setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the Log object itself.
     */
    public Log setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the level property: The level property.
     *
     * @return the level value.
     */
    public LogLevel getLevel() {
        return this.level;
    }

    /**
     * Set the level property: The level property.
     *
     * @param level the level value to set.
     * @return the Log object itself.
     */
    public Log setLevel(LogLevel level) {
        this.level = level;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("message", this.message);
        jsonWriter.writeStringField("level", this.level == null ? null : this.level.toString());
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField(
            "createdAt",
            this.createdAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.createdAt)
        );
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Log from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Log if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Log.
     */
    public static Log fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Log deserializedLog = new Log();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("message".equals(fieldName)) {
                    deserializedLog.message = reader.getString();
                } else if ("level".equals(fieldName)) {
                    deserializedLog.level = LogLevel.fromString(reader.getString());
                } else if ("id".equals(fieldName)) {
                    deserializedLog.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("createdAt".equals(fieldName)) {
                    deserializedLog.createdAt = reader.getNullable(nonNullReader -> OffsetDateTime.parse(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedLog;
        });
    }
}
