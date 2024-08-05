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
 * The ProcessItemTaskPageItem model.
 */
@Fluent
public final class ProcessItemTaskPageItem implements JsonSerializable<ProcessItemTaskPageItem> {

    /*
     * Process Item Task state
     */
    private ProcessItemTaskState state;

    /*
     * The taskDefinition property.
     */
    private TaskDefinitionSummary taskDefinition;

    /**
     * Creates an instance of ProcessItemTaskPageItem class.
     */
    public ProcessItemTaskPageItem() {}

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
     * @return the ProcessItemTaskPageItem object itself.
     */
    public ProcessItemTaskPageItem setState(ProcessItemTaskState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the taskDefinition property: The taskDefinition property.
     *
     * @return the taskDefinition value.
     */
    public TaskDefinitionSummary getTaskDefinition() {
        return this.taskDefinition;
    }

    /**
     * Set the taskDefinition property: The taskDefinition property.
     *
     * @param taskDefinition the taskDefinition value to set.
     * @return the ProcessItemTaskPageItem object itself.
     */
    public ProcessItemTaskPageItem setTaskDefinition(TaskDefinitionSummary taskDefinition) {
        this.taskDefinition = taskDefinition;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeJsonField("taskDefinition", this.taskDefinition);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskPageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskPageItem if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemTaskPageItem.
     */
    public static ProcessItemTaskPageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskPageItem deserializedProcessItemTaskPageItem = new ProcessItemTaskPageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("state".equals(fieldName)) {
                    deserializedProcessItemTaskPageItem.state = ProcessItemTaskState.fromString(reader.getString());
                } else if ("taskDefinition".equals(fieldName)) {
                    deserializedProcessItemTaskPageItem.taskDefinition = TaskDefinitionSummary.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskPageItem;
        });
    }
}
