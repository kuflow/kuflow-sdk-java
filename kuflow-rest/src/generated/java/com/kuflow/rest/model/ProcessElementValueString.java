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

/**
 * The ProcessElementValueString model.
 */
@Fluent
public final class ProcessElementValueString extends ProcessElementValue {

    /*
     * Process element value types.
     */
    private ProcessElementValueType type = ProcessElementValueType.STRING;

    /*
     * The value property.
     */
    private String value;

    /**
     * Creates an instance of ProcessElementValueString class.
     */
    public ProcessElementValueString() {}

    /**
     * Get the type property: Process element value types.
     *
     * @return the type value.
     */
    @Override
    public ProcessElementValueType getType() {
        return this.type;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the ProcessElementValueString object itself.
     */
    public ProcessElementValueString setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessElementValueString setValid(Boolean valid) {
        super.setValid(valid);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("valid", isValid());
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessElementValueString from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessElementValueString if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessElementValueString.
     */
    public static ProcessElementValueString fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessElementValueString deserializedProcessElementValueString = new ProcessElementValueString();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("valid".equals(fieldName)) {
                    deserializedProcessElementValueString.setValid(reader.getNullable(JsonReader::getBoolean));
                } else if ("type".equals(fieldName)) {
                    deserializedProcessElementValueString.type = ProcessElementValueType.fromString(reader.getString());
                } else if ("value".equals(fieldName)) {
                    deserializedProcessElementValueString.value = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessElementValueString;
        });
    }
}
