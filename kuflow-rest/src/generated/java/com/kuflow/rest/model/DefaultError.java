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
import java.util.List;

/**
 * Default error.
 */
@Fluent
public final class DefaultError implements JsonSerializable<DefaultError> {

    /*
     * Timestamp indicating when the error happened.
     */
    private OffsetDateTime timestamp;

    /*
     * HTTP Status
     */
    private int status;

    /*
     * Message Status
     */
    private String message;

    /*
     * Related error information.
     */
    private List<DefaultErrorInfo> errors;

    /**
     * Creates an instance of DefaultError class.
     */
    public DefaultError() {}

    /**
     * Get the timestamp property: Timestamp indicating when the error happened.
     *
     * @return the timestamp value.
     */
    public OffsetDateTime getTimestamp() {
        return this.timestamp;
    }

    /**
     * Set the timestamp property: Timestamp indicating when the error happened.
     *
     * @param timestamp the timestamp value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setTimestamp(OffsetDateTime timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    /**
     * Get the status property: HTTP Status.
     *
     * @return the status value.
     */
    public int getStatus() {
        return this.status;
    }

    /**
     * Set the status property: HTTP Status.
     *
     * @param status the status value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setStatus(int status) {
        this.status = status;
        return this;
    }

    /**
     * Get the message property: Message Status.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: Message Status.
     *
     * @param message the message value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the errors property: Related error information.
     *
     * @return the errors value.
     */
    public List<DefaultErrorInfo> getErrors() {
        return this.errors;
    }

    /**
     * Set the errors property: Related error information.
     *
     * @param errors the errors value to set.
     * @return the DefaultError object itself.
     */
    public DefaultError setErrors(List<DefaultErrorInfo> errors) {
        this.errors = errors;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField(
            "timestamp",
            this.timestamp == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.timestamp)
        );
        jsonWriter.writeIntField("status", this.status);
        jsonWriter.writeStringField("message", this.message);
        jsonWriter.writeArrayField("errors", this.errors, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DefaultError from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DefaultError if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DefaultError.
     */
    public static DefaultError fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DefaultError deserializedDefaultError = new DefaultError();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("timestamp".equals(fieldName)) {
                    deserializedDefaultError.timestamp = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("status".equals(fieldName)) {
                    deserializedDefaultError.status = reader.getInt();
                } else if ("message".equals(fieldName)) {
                    deserializedDefaultError.message = reader.getString();
                } else if ("errors".equals(fieldName)) {
                    List<DefaultErrorInfo> errors = reader.readArray(reader1 -> DefaultErrorInfo.fromJson(reader1));
                    deserializedDefaultError.errors = errors;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDefaultError;
        });
    }
}
