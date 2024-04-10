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

/**
 * The DefaultErrorInfo model.
 */
@Fluent
public final class DefaultErrorInfo implements JsonSerializable<DefaultErrorInfo> {

    /*
     * The code property.
     */
    private String code;

    /*
     * The message property.
     */
    private String message;

    /*
     * The location property.
     */
    private String location;

    /*
     * The locationType property.
     */
    private String locationType;

    /**
     * Creates an instance of DefaultErrorInfo class.
     */
    public DefaultErrorInfo() {}

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the DefaultErrorInfo object itself.
     */
    public DefaultErrorInfo setCode(String code) {
        this.code = code;
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
     * @return the DefaultErrorInfo object itself.
     */
    public DefaultErrorInfo setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the location property: The location property.
     *
     * @return the location value.
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Set the location property: The location property.
     *
     * @param location the location value to set.
     * @return the DefaultErrorInfo object itself.
     */
    public DefaultErrorInfo setLocation(String location) {
        this.location = location;
        return this;
    }

    /**
     * Get the locationType property: The locationType property.
     *
     * @return the locationType value.
     */
    public String getLocationType() {
        return this.locationType;
    }

    /**
     * Set the locationType property: The locationType property.
     *
     * @param locationType the locationType value to set.
     * @return the DefaultErrorInfo object itself.
     */
    public DefaultErrorInfo setLocationType(String locationType) {
        this.locationType = locationType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("message", this.message);
        jsonWriter.writeStringField("location", this.location);
        jsonWriter.writeStringField("locationType", this.locationType);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DefaultErrorInfo from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DefaultErrorInfo if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DefaultErrorInfo.
     */
    public static DefaultErrorInfo fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DefaultErrorInfo deserializedDefaultErrorInfo = new DefaultErrorInfo();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("code".equals(fieldName)) {
                    deserializedDefaultErrorInfo.code = reader.getString();
                } else if ("message".equals(fieldName)) {
                    deserializedDefaultErrorInfo.message = reader.getString();
                } else if ("location".equals(fieldName)) {
                    deserializedDefaultErrorInfo.location = reader.getString();
                } else if ("locationType".equals(fieldName)) {
                    deserializedDefaultErrorInfo.locationType = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDefaultErrorInfo;
        });
    }
}
