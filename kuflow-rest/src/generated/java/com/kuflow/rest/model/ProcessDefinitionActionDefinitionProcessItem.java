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
 * The task or thread that the action creates, by its code. When the version does not have it, by its id: KuFlow
 * does not offer the action in the processes of that version. Actions belong to the process definition, and
 * tasks and threads to each version.
 */
@Fluent
public final class ProcessDefinitionActionDefinitionProcessItem implements JsonSerializable<ProcessDefinitionActionDefinitionProcessItem> {

    /*
     * The code of the task or thread that the action creates, when the version has it.
     */
    @Generated
    private String processItemDefinitionCode;

    /*
     * The id of the task or thread that the action creates, when the version does not have it.
     */
    @Generated
    private UUID processItemDefinitionId;

    /**
     * Creates an instance of ProcessDefinitionActionDefinitionProcessItem class.
     */
    @Generated
    public ProcessDefinitionActionDefinitionProcessItem() {}

    /**
     * Get the processItemDefinitionCode property: The code of the task or thread that the action creates, when the
     * version has it.
     *
     * @return the processItemDefinitionCode value.
     */
    @Generated
    public String getProcessItemDefinitionCode() {
        return this.processItemDefinitionCode;
    }

    /**
     * Set the processItemDefinitionCode property: The code of the task or thread that the action creates, when the
     * version has it.
     *
     * @param processItemDefinitionCode the processItemDefinitionCode value to set.
     * @return the ProcessDefinitionActionDefinitionProcessItem object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionProcessItem setProcessItemDefinitionCode(String processItemDefinitionCode) {
        this.processItemDefinitionCode = processItemDefinitionCode;
        return this;
    }

    /**
     * Get the processItemDefinitionId property: The id of the task or thread that the action creates, when the version
     * does not have it.
     *
     * @return the processItemDefinitionId value.
     */
    @Generated
    public UUID getProcessItemDefinitionId() {
        return this.processItemDefinitionId;
    }

    /**
     * Set the processItemDefinitionId property: The id of the task or thread that the action creates, when the version
     * does not have it.
     *
     * @param processItemDefinitionId the processItemDefinitionId value to set.
     * @return the ProcessDefinitionActionDefinitionProcessItem object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinitionProcessItem setProcessItemDefinitionId(UUID processItemDefinitionId) {
        this.processItemDefinitionId = processItemDefinitionId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processItemDefinitionCode", this.processItemDefinitionCode);
        jsonWriter.writeStringField("processItemDefinitionId", Objects.toString(this.processItemDefinitionId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionActionDefinitionProcessItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionActionDefinitionProcessItem if the JsonReader was pointing to an instance
     * of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessDefinitionActionDefinitionProcessItem.
     */
    @Generated
    public static ProcessDefinitionActionDefinitionProcessItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionActionDefinitionProcessItem deserializedProcessDefinitionActionDefinitionProcessItem =
                new ProcessDefinitionActionDefinitionProcessItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processItemDefinitionCode".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionProcessItem.processItemDefinitionCode = reader.getString();
                } else if ("processItemDefinitionId".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinitionProcessItem.processItemDefinitionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionActionDefinitionProcessItem;
        });
    }
}
