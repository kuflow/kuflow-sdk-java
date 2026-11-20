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
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
 */
@Fluent
public final class ProcessDefinitionActionDefinitionWorkflow implements JsonSerializable<ProcessDefinitionActionDefinitionWorkflow> {

    /*
     * The taskQueue property.
     */
    @Generated
    private String taskQueue;

    /*
     * The workflowType property.
     */
    @Generated
    private String workflowType;

    /**
     * Creates an instance of ProcessDefinitionActionDefinitionWorkflow class.
     */
    @Generated
    public ProcessDefinitionActionDefinitionWorkflow() {}

    /**
     * Get the taskQueue property: The taskQueue property.
     *
     * @return the taskQueue value.
     */
    @Generated
    public String getTaskQueue() {
        return this.taskQueue;
    }

    /**
     * Set the taskQueue property: The taskQueue property.
     *
     * @param taskQueue the taskQueue value to set.
     * @return the ProcessDefinitionActionDefinitionWorkflow object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionWorkflow setTaskQueue(String taskQueue) {
        this.taskQueue = taskQueue;
        return this;
    }

    /**
     * Get the workflowType property: The workflowType property.
     *
     * @return the workflowType value.
     */
    @Generated
    public String getWorkflowType() {
        return this.workflowType;
    }

    /**
     * Set the workflowType property: The workflowType property.
     *
     * @param workflowType the workflowType value to set.
     * @return the ProcessDefinitionActionDefinitionWorkflow object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionWorkflow setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("taskQueue", this.taskQueue);
        jsonWriter.writeStringField("workflowType", this.workflowType);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionActionDefinitionWorkflow from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionActionDefinitionWorkflow if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessDefinitionActionDefinitionWorkflow.
     */
    @Generated
    public static ProcessDefinitionActionDefinitionWorkflow fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionActionDefinitionWorkflow deserializedProcessDefinitionActionDefinitionWorkflow =
                new ProcessDefinitionActionDefinitionWorkflow();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("taskQueue".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionWorkflow.taskQueue = reader.getString();
                } else if ("workflowType".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionWorkflow.workflowType = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionActionDefinitionWorkflow;
        });
    }
}
