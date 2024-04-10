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
 * The TaskSaveJsonFormsValueDocumentResponseCommand model.
 */
@Fluent
public final class TaskSaveJsonFormsValueDocumentResponseCommand
    implements JsonSerializable<TaskSaveJsonFormsValueDocumentResponseCommand> {

    /*
     * JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`
     */
    private String value;

    /**
     * Creates an instance of TaskSaveJsonFormsValueDocumentResponseCommand class.
     */
    public TaskSaveJsonFormsValueDocumentResponseCommand() {}

    /**
     * Get the value property: JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`.
     *
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`.
     *
     * @param value the value value to set.
     * @return the TaskSaveJsonFormsValueDocumentResponseCommand object itself.
     */
    public TaskSaveJsonFormsValueDocumentResponseCommand setValue(String value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TaskSaveJsonFormsValueDocumentResponseCommand from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of TaskSaveJsonFormsValueDocumentResponseCommand if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TaskSaveJsonFormsValueDocumentResponseCommand.
     */
    public static TaskSaveJsonFormsValueDocumentResponseCommand fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TaskSaveJsonFormsValueDocumentResponseCommand deserializedTaskSaveJsonFormsValueDocumentResponseCommand =
                new TaskSaveJsonFormsValueDocumentResponseCommand();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    deserializedTaskSaveJsonFormsValueDocumentResponseCommand.value = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTaskSaveJsonFormsValueDocumentResponseCommand;
        });
    }
}
