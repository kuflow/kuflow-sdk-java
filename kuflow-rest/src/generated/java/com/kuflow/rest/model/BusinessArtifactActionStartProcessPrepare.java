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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Pre-filled metadata for a START_PROCESS action. Returned by the prepare
 * operation; nothing is persisted on the server.
 */
@Fluent
public final class BusinessArtifactActionStartProcessPrepare implements JsonSerializable<BusinessArtifactActionStartProcessPrepare> {

    /*
     * Process Definition the action will start.
     */
    @Generated
    private UUID processDefinitionId;

    /*
     * Pre-filled process metadata computed from the action definition.
     */
    @Generated
    private Map<String, Object> metadata;

    /**
     * Creates an instance of BusinessArtifactActionStartProcessPrepare class.
     */
    @Generated
    public BusinessArtifactActionStartProcessPrepare() {}

    /**
     * Get the processDefinitionId property: Process Definition the action will start.
     *
     * @return the processDefinitionId value.
     */
    @Generated
    public UUID getProcessDefinitionId() {
        return this.processDefinitionId;
    }

    /**
     * Set the processDefinitionId property: Process Definition the action will start.
     *
     * @param processDefinitionId the processDefinitionId value to set.
     * @return the BusinessArtifactActionStartProcessPrepare object itself.
     */
    @Generated
    public BusinessArtifactActionStartProcessPrepare setProcessDefinitionId(UUID processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
        return this;
    }

    /**
     * Get the metadata property: Pre-filled process metadata computed from the action definition.
     *
     * @return the metadata value.
     */
    @Generated
    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Pre-filled process metadata computed from the action definition.
     *
     * @param metadata the metadata value to set.
     * @return the BusinessArtifactActionStartProcessPrepare object itself.
     */
    @Generated
    public BusinessArtifactActionStartProcessPrepare setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
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
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionStartProcessPrepare from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionStartProcessPrepare if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionStartProcessPrepare.
     */
    @Generated
    public static BusinessArtifactActionStartProcessPrepare fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionStartProcessPrepare deserializedBusinessArtifactActionStartProcessPrepare =
                new BusinessArtifactActionStartProcessPrepare();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processDefinitionId".equals(fieldName)) {
                    deserializedBusinessArtifactActionStartProcessPrepare.processDefinitionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("metadata".equals(fieldName)) {
                    Map<String, Object> metadata = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedBusinessArtifactActionStartProcessPrepare.metadata = metadata;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionStartProcessPrepare;
        });
    }
}
