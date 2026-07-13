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

/**
 * Params for invoking an action of type START_PROCESS.
 */
@Fluent
public final class BusinessArtifactActionCreateParamsStartProcess
    implements JsonSerializable<BusinessArtifactActionCreateParamsStartProcess>
{

    /*
     * Metadata applied to the process created by this action.
     */
    @Generated
    private Map<String, Object> metadata;

    /*
     * Entity data applied to the process created by this action. Validated
     * against the process definition entity schema; validation errors are
     * recorded on the process entity value. Ignored if empty or if the
     * process definition has no entity structure.
     */
    @Generated
    private Map<String, Object> entity;

    /**
     * Creates an instance of BusinessArtifactActionCreateParamsStartProcess class.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartProcess() {}

    /**
     * Get the metadata property: Metadata applied to the process created by this action.
     *
     * @return the metadata value.
     */
    @Generated
    public Map<String, Object> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Metadata applied to the process created by this action.
     *
     * @param metadata the metadata value to set.
     * @return the BusinessArtifactActionCreateParamsStartProcess object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartProcess setMetadata(Map<String, Object> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the entity property: Entity data applied to the process created by this action. Validated
     * against the process definition entity schema; validation errors are
     * recorded on the process entity value. Ignored if empty or if the
     * process definition has no entity structure.
     *
     * @return the entity value.
     */
    @Generated
    public Map<String, Object> getEntity() {
        return this.entity;
    }

    /**
     * Set the entity property: Entity data applied to the process created by this action. Validated
     * against the process definition entity schema; validation errors are
     * recorded on the process entity value. Ignored if empty or if the
     * process definition has no entity structure.
     *
     * @param entity the entity value to set.
     * @return the BusinessArtifactActionCreateParamsStartProcess object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartProcess setEntity(Map<String, Object> entity) {
        this.entity = entity;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeMapField("entity", this.entity, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionCreateParamsStartProcess from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionCreateParamsStartProcess if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionCreateParamsStartProcess.
     */
    @Generated
    public static BusinessArtifactActionCreateParamsStartProcess fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionCreateParamsStartProcess deserializedBusinessArtifactActionCreateParamsStartProcess =
                new BusinessArtifactActionCreateParamsStartProcess();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("metadata".equals(fieldName)) {
                    Map<String, Object> metadata = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedBusinessArtifactActionCreateParamsStartProcess.metadata = metadata;
                } else if ("entity".equals(fieldName)) {
                    Map<String, Object> entity = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedBusinessArtifactActionCreateParamsStartProcess.entity = entity;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionCreateParamsStartProcess;
        });
    }
}
