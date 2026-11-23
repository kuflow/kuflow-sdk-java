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
import java.util.Objects;
import java.util.UUID;

/**
 * The ProcessDefinitionActionDefinitionStartRelatedProcess model.
 */
@Fluent
public final class ProcessDefinitionActionDefinitionStartRelatedProcess
    implements JsonSerializable<ProcessDefinitionActionDefinitionStartRelatedProcess>
{

    /*
     * The Process Definition of the process that the action starts.
     */
    @Generated
    private UUID processDefinitionId;

    /*
     * Its code, while it exists.
     */
    @Generated
    private String processDefinitionCode;

    /**
     * Creates an instance of ProcessDefinitionActionDefinitionStartRelatedProcess class.
     */
    @Generated
    public ProcessDefinitionActionDefinitionStartRelatedProcess() {}

    /**
     * Get the processDefinitionId property: The Process Definition of the process that the action starts.
     *
     * @return the processDefinitionId value.
     */
    @Generated
    public UUID getProcessDefinitionId() {
        return this.processDefinitionId;
    }

    /**
     * Set the processDefinitionId property: The Process Definition of the process that the action starts.
     *
     * @param processDefinitionId the processDefinitionId value to set.
     * @return the ProcessDefinitionActionDefinitionStartRelatedProcess object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionStartRelatedProcess setProcessDefinitionId(UUID processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
        return this;
    }

    /**
     * Get the processDefinitionCode property: Its code, while it exists.
     *
     * @return the processDefinitionCode value.
     */
    @Generated
    public String getProcessDefinitionCode() {
        return this.processDefinitionCode;
    }

    /**
     * Set the processDefinitionCode property: Its code, while it exists.
     *
     * @param processDefinitionCode the processDefinitionCode value to set.
     * @return the ProcessDefinitionActionDefinitionStartRelatedProcess object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionStartRelatedProcess setProcessDefinitionCode(String processDefinitionCode) {
        this.processDefinitionCode = processDefinitionCode;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processDefinitionId", Objects.toString(this.processDefinitionId, null));
        jsonWriter.writeStringField("processDefinitionCode", this.processDefinitionCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionActionDefinitionStartRelatedProcess from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionActionDefinitionStartRelatedProcess if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionActionDefinitionStartRelatedProcess.
     */
    @Generated
    public static ProcessDefinitionActionDefinitionStartRelatedProcess fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionActionDefinitionStartRelatedProcess deserializedProcessDefinitionActionDefinitionStartRelatedProcess =
                new ProcessDefinitionActionDefinitionStartRelatedProcess();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processDefinitionId".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionStartRelatedProcess.processDefinitionId = reader.getNullable(
                        nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else if ("processDefinitionCode".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionStartRelatedProcess.processDefinitionCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionActionDefinitionStartRelatedProcess;
        });
    }
}
