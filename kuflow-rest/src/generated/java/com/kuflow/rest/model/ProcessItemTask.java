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

/**
 * The ProcessItemTask model.
 */
@Fluent
public final class ProcessItemTask implements JsonSerializable<ProcessItemTask> {

    /*
     * Process Item Task state
     */
    private ProcessItemTaskState state;

    /*
     * Json value.
     */
    private JsonValue data;

    /*
     * The logs property.
     */
    private List<ProcessItemTaskLog> logs;

    /**
     * Creates an instance of ProcessItemTask class.
     */
    public ProcessItemTask() {}

    /**
     * Get the state property: Process Item Task state.
     *
     * @return the state value.
     */
    public ProcessItemTaskState getState() {
        return this.state;
    }

    /**
     * Set the state property: Process Item Task state.
     *
     * @param state the state value to set.
     * @return the ProcessItemTask object itself.
     */
    public ProcessItemTask setState(ProcessItemTaskState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the data property: Json value.
     *
     * @return the data value.
     */
    public JsonValue getData() {
        return this.data;
    }

    /**
     * Set the data property: Json value.
     *
     * @param data the data value to set.
     * @return the ProcessItemTask object itself.
     */
    public ProcessItemTask setData(JsonValue data) {
        this.data = data;
        return this;
    }

    /**
     * Get the logs property: The logs property.
     *
     * @return the logs value.
     */
    public List<ProcessItemTaskLog> getLogs() {
        return this.logs;
    }

    /**
     * Set the logs property: The logs property.
     *
     * @param logs the logs value to set.
     * @return the ProcessItemTask object itself.
     */
    public ProcessItemTask setLogs(List<ProcessItemTaskLog> logs) {
        this.logs = logs;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeJsonField("data", this.data);
        jsonWriter.writeArrayField("logs", this.logs, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTask from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTask if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemTask.
     */
    public static ProcessItemTask fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTask deserializedProcessItemTask = new ProcessItemTask();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("state".equals(fieldName)) {
                    deserializedProcessItemTask.state = ProcessItemTaskState.fromString(reader.getString());
                } else if ("data".equals(fieldName)) {
                    deserializedProcessItemTask.data = JsonValue.fromJson(reader);
                } else if ("logs".equals(fieldName)) {
                    List<ProcessItemTaskLog> logs = reader.readArray(reader1 -> ProcessItemTaskLog.fromJson(reader1));
                    deserializedProcessItemTask.logs = logs;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTask;
        });
    }
}
