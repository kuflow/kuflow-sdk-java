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
 * The ProcessDefinitionActionDefinitionSendWorkflowSignal model.
 */
@Fluent
public final class ProcessDefinitionActionDefinitionSendWorkflowSignal
    implements JsonSerializable<ProcessDefinitionActionDefinitionSendWorkflowSignal>
{

    /*
     * The signalName property.
     */
    @Generated
    private String signalName;

    /*
     * A form, with its parts as JSON.
     */
    @Generated
    private ProcessDefinitionJsonDefinition inputDefinition;

    /**
     * Creates an instance of ProcessDefinitionActionDefinitionSendWorkflowSignal class.
     */
    @Generated
    public ProcessDefinitionActionDefinitionSendWorkflowSignal() {}

    /**
     * Get the signalName property: The signalName property.
     *
     * @return the signalName value.
     */
    @Generated
    public String getSignalName() {
        return this.signalName;
    }

    /**
     * Set the signalName property: The signalName property.
     *
     * @param signalName the signalName value to set.
     * @return the ProcessDefinitionActionDefinitionSendWorkflowSignal object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionSendWorkflowSignal setSignalName(String signalName) {
        this.signalName = signalName;
        return this;
    }

    /**
     * Get the inputDefinition property: A form, with its parts as JSON.
     *
     * @return the inputDefinition value.
     */
    @Generated
    public ProcessDefinitionJsonDefinition getInputDefinition() {
        return this.inputDefinition;
    }

    /**
     * Set the inputDefinition property: A form, with its parts as JSON.
     *
     * @param inputDefinition the inputDefinition value to set.
     * @return the ProcessDefinitionActionDefinitionSendWorkflowSignal object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionSendWorkflowSignal setInputDefinition(ProcessDefinitionJsonDefinition inputDefinition) {
        this.inputDefinition = inputDefinition;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("signalName", this.signalName);
        jsonWriter.writeJsonField("inputDefinition", this.inputDefinition);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionActionDefinitionSendWorkflowSignal from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionActionDefinitionSendWorkflowSignal if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionActionDefinitionSendWorkflowSignal.
     */
    @Generated
    public static ProcessDefinitionActionDefinitionSendWorkflowSignal fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionActionDefinitionSendWorkflowSignal deserializedProcessDefinitionActionDefinitionSendWorkflowSignal =
                new ProcessDefinitionActionDefinitionSendWorkflowSignal();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("signalName".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionSendWorkflowSignal.signalName = reader.getString();
                } else if ("inputDefinition".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionSendWorkflowSignal.inputDefinition =
                        ProcessDefinitionJsonDefinition.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionActionDefinitionSendWorkflowSignal;
        });
    }
}
