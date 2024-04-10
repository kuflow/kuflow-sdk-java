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
 * The TaskElementValue model.
 */
@Fluent
public class TaskElementValue implements JsonSerializable<TaskElementValue> {

    /*
     * The type property.
     */
    private TaskElementValueType type;

    /*
     * The valid property.
     */
    private Boolean valid;

    /**
     * Creates an instance of TaskElementValue class.
     */
    public TaskElementValue() {
        this.type = TaskElementValueType.fromString("TaskElementValue");
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public TaskElementValueType getType() {
        return this.type;
    }

    /**
     * Get the valid property: The valid property.
     *
     * @return the valid value.
     */
    public Boolean isValid() {
        return this.valid;
    }

    /**
     * Set the valid property: The valid property.
     *
     * @param valid the valid value to set.
     * @return the TaskElementValue object itself.
     */
    public TaskElementValue setValid(Boolean valid) {
        this.valid = valid;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeBooleanField("valid", this.valid);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TaskElementValue from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of TaskElementValue if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the TaskElementValue.
     */
    public static TaskElementValue fromJson(JsonReader jsonReader) throws IOException {
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
                if ("STRING".equals(discriminatorValue)) {
                    return TaskElementValueString.fromJson(readerToUse.reset());
                } else if ("NUMBER".equals(discriminatorValue)) {
                    return TaskElementValueNumber.fromJson(readerToUse.reset());
                } else if ("OBJECT".equals(discriminatorValue)) {
                    return TaskElementValueObject.fromJson(readerToUse.reset());
                } else if ("DOCUMENT".equals(discriminatorValue)) {
                    return TaskElementValueDocument.fromJson(readerToUse.reset());
                } else if ("PRINCIPAL".equals(discriminatorValue)) {
                    return TaskElementValuePrincipal.fromJson(readerToUse.reset());
                } else {
                    return fromJsonKnownDiscriminator(readerToUse.reset());
                }
            }
        });
    }

    static TaskElementValue fromJsonKnownDiscriminator(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TaskElementValue deserializedTaskElementValue = new TaskElementValue();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("type".equals(fieldName)) {
                    deserializedTaskElementValue.type = TaskElementValueType.fromString(reader.getString());
                } else if ("valid".equals(fieldName)) {
                    deserializedTaskElementValue.valid = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTaskElementValue;
        });
    }
}
