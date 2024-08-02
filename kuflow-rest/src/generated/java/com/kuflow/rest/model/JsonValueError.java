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
 * Json value.
 */
@Fluent
public final class JsonValueError implements JsonSerializable<JsonValueError> {

    /*
     * JSON pointer to the property with the error. See: https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name
     */
    private String propertyPath;

    /*
     * Error type.
     */
    private String type;

    /**
     * Creates an instance of JsonValueError class.
     */
    public JsonValueError() {}

    /**
     * Get the propertyPath property: JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     *
     * @return the propertyPath value.
     */
    public String getPropertyPath() {
        return this.propertyPath;
    }

    /**
     * Set the propertyPath property: JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     *
     * @param propertyPath the propertyPath value to set.
     * @return the JsonValueError object itself.
     */
    public JsonValueError setPropertyPath(String propertyPath) {
        this.propertyPath = propertyPath;
        return this;
    }

    /**
     * Get the type property: Error type.
     *
     * @return the type value.
     */
    public String getType() {
        return this.type;
    }

    /**
     * Set the type property: Error type.
     *
     * @param type the type value to set.
     * @return the JsonValueError object itself.
     */
    public JsonValueError setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("propertyPath", this.propertyPath);
        jsonWriter.writeStringField("type", this.type);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JsonValueError from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of JsonValueError if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the JsonValueError.
     */
    public static JsonValueError fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JsonValueError deserializedJsonValueError = new JsonValueError();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("propertyPath".equals(fieldName)) {
                    deserializedJsonValueError.propertyPath = reader.getString();
                } else if ("type".equals(fieldName)) {
                    deserializedJsonValueError.type = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedJsonValueError;
        });
    }
}
