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
import java.util.List;
import java.util.Map;

/**
 * Json value.
 */
@Fluent
public final class JsonValue implements JsonSerializable<JsonValue> {

    /*
     * true if the data complain the related json schema.
     */
    private Boolean valid;

    /*
     * json value filled that complain with the related json schema.
     */
    private Map<String, Object> value;

    /*
     * The errors property.
     */
    private List<JsonValueError> errors;

    /**
     * Creates an instance of JsonValue class.
     */
    public JsonValue() {}

    /**
     * Get the valid property: true if the data complain the related json schema.
     *
     * @return the valid value.
     */
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * Get the value property: json value filled that complain with the related json schema.
     *
     * @return the value value.
     */
    public Map<String, Object> getValue() {
        return this.value;
    }

    /**
     * Set the value property: json value filled that complain with the related json schema.
     *
     * @param value the value value to set.
     * @return the JsonValue object itself.
     */
    public JsonValue setValue(Map<String, Object> value) {
        this.value = value;
        return this;
    }

    /**
     * Get the errors property: The errors property.
     *
     * @return the errors value.
     */
    public List<JsonValueError> getErrors() {
        return this.errors;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeMapField("value", this.value, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JsonValue from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of JsonValue if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the JsonValue.
     */
    public static JsonValue fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JsonValue deserializedJsonValue = new JsonValue();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    Map<String, Object> value = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedJsonValue.value = value;
                } else if ("valid".equals(fieldName)) {
                    deserializedJsonValue.valid = reader.getNullable(JsonReader::getBoolean);
                } else if ("errors".equals(fieldName)) {
                    List<JsonValueError> errors = reader.readArray(reader1 -> JsonValueError.fromJson(reader1));
                    deserializedJsonValue.errors = errors;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedJsonValue;
        });
    }
}
