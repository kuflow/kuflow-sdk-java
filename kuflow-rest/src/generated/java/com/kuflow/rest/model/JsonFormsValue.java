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
import java.util.Map;

/**
 * Json form values, used when the render type selected is JSON Forms.
 */
@Fluent
public final class JsonFormsValue implements JsonSerializable<JsonFormsValue> {

    /*
     * true if the data complain the related json schema.
     */
    private Boolean valid;

    /*
     * json value filled that complain with the related json schema.
     */
    private Map<String, Object> data;

    /**
     * Creates an instance of JsonFormsValue class.
     */
    public JsonFormsValue() {}

    /**
     * Get the valid property: true if the data complain the related json schema.
     *
     * @return the valid value.
     */
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * Set the valid property: true if the data complain the related json schema.
     *
     * @param valid the valid value to set.
     * @return the JsonFormsValue object itself.
     */
    public JsonFormsValue setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * Get the data property: json value filled that complain with the related json schema.
     *
     * @return the data value.
     */
    public Map<String, Object> getData() {
        return this.data;
    }

    /**
     * Set the data property: json value filled that complain with the related json schema.
     *
     * @param data the data value to set.
     * @return the JsonFormsValue object itself.
     */
    public JsonFormsValue setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("valid", this.valid);
        jsonWriter.writeMapField("data", this.data, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JsonFormsValue from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of JsonFormsValue if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the JsonFormsValue.
     */
    public static JsonFormsValue fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JsonFormsValue deserializedJsonFormsValue = new JsonFormsValue();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("valid".equals(fieldName)) {
                    deserializedJsonFormsValue.valid = reader.getNullable(JsonReader::getBoolean);
                } else if ("data".equals(fieldName)) {
                    Map<String, Object> data = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedJsonFormsValue.data = data;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedJsonFormsValue;
        });
    }
}
