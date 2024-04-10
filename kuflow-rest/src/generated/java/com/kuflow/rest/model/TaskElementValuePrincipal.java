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
 * The TaskElementValuePrincipal model.
 */
@Fluent
public final class TaskElementValuePrincipal extends TaskElementValue {

    /*
     * The type property.
     */
    private TaskElementValueType type = TaskElementValueType.PRINCIPAL;

    /*
     * The value property.
     */
    private TaskElementValuePrincipalItem value;

    /**
     * Creates an instance of TaskElementValuePrincipal class.
     */
    public TaskElementValuePrincipal() {}

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    @Override
    public TaskElementValueType getType() {
        return this.type;
    }

    /**
     * Get the value property: The value property.
     *
     * @return the value value.
     */
    public TaskElementValuePrincipalItem getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value property.
     *
     * @param value the value value to set.
     * @return the TaskElementValuePrincipal object itself.
     */
    public TaskElementValuePrincipal setValue(TaskElementValuePrincipalItem value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TaskElementValuePrincipal setValid(Boolean valid) {
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
        jsonWriter.writeJsonField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TaskElementValuePrincipal from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of TaskElementValuePrincipal if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the TaskElementValuePrincipal.
     */
    public static TaskElementValuePrincipal fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TaskElementValuePrincipal deserializedTaskElementValuePrincipal = new TaskElementValuePrincipal();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("valid".equals(fieldName)) {
                    deserializedTaskElementValuePrincipal.setValid(reader.getNullable(JsonReader::getBoolean));
                } else if ("type".equals(fieldName)) {
                    deserializedTaskElementValuePrincipal.type = TaskElementValueType.fromString(reader.getString());
                } else if ("value".equals(fieldName)) {
                    deserializedTaskElementValuePrincipal.value = TaskElementValuePrincipalItem.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTaskElementValuePrincipal;
        });
    }
}
