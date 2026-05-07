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
 * Action details for actions of type START_PROCESS. Actions of this
 * type complete synchronously, so all fields are present in the invoke
 * response.
 */
@Fluent
public final class BusinessArtifactActionStartProcess implements JsonSerializable<BusinessArtifactActionStartProcess> {

    /*
     * ID of the Process Definition that was instantiated.
     */
    @Generated
    private UUID processDefinitionId;

    /*
     * ID of the Process created by this action.
     */
    @Generated
    private UUID processId;

    /**
     * Creates an instance of BusinessArtifactActionStartProcess class.
     */
    @Generated
    public BusinessArtifactActionStartProcess() {}

    /**
     * Get the processDefinitionId property: ID of the Process Definition that was instantiated.
     *
     * @return the processDefinitionId value.
     */
    @Generated
    public UUID getProcessDefinitionId() {
        return this.processDefinitionId;
    }

    /**
     * Set the processDefinitionId property: ID of the Process Definition that was instantiated.
     *
     * @param processDefinitionId the processDefinitionId value to set.
     * @return the BusinessArtifactActionStartProcess object itself.
     */
    @Generated
    public BusinessArtifactActionStartProcess setProcessDefinitionId(UUID processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
        return this;
    }

    /**
     * Get the processId property: ID of the Process created by this action.
     *
     * @return the processId value.
     */
    @Generated
    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property: ID of the Process created by this action.
     *
     * @param processId the processId value to set.
     * @return the BusinessArtifactActionStartProcess object itself.
     */
    @Generated
    public BusinessArtifactActionStartProcess setProcessId(UUID processId) {
        this.processId = processId;
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
        jsonWriter.writeStringField("processId", Objects.toString(this.processId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionStartProcess from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionStartProcess if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionStartProcess.
     */
    @Generated
    public static BusinessArtifactActionStartProcess fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionStartProcess deserializedBusinessArtifactActionStartProcess = new BusinessArtifactActionStartProcess();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processDefinitionId".equals(fieldName)) {
                    deserializedBusinessArtifactActionStartProcess.processDefinitionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("processId".equals(fieldName)) {
                    deserializedBusinessArtifactActionStartProcess.processId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionStartProcess;
        });
    }
}
