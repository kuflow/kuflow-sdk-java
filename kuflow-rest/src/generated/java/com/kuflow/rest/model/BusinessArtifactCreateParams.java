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
 * Params to create a Business Artifact. Either `businessArtifactDefinitionId` or
 * `businessArtifactDefinitionCode` (with `tenantId`) must be provided.
 */
@Fluent
public final class BusinessArtifactCreateParams implements JsonSerializable<BusinessArtifactCreateParams> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The businessArtifactDefinitionId property.
     */
    @Generated
    private UUID businessArtifactDefinitionId;

    /*
     * The tenantId property.
     */
    @Generated
    private UUID tenantId;

    /*
     * The businessArtifactDefinitionCode property.
     */
    @Generated
    private String businessArtifactDefinitionCode;

    /*
     * Json value.
     */
    @Generated
    private JsonValue data;

    /**
     * Creates an instance of BusinessArtifactCreateParams class.
     */
    @Generated
    public BusinessArtifactCreateParams() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    @Generated
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the BusinessArtifactCreateParams object itself.
     */
    @Generated
    public BusinessArtifactCreateParams setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the businessArtifactDefinitionId property: The businessArtifactDefinitionId property.
     *
     * @return the businessArtifactDefinitionId value.
     */
    @Generated
    public UUID getBusinessArtifactDefinitionId() {
        return this.businessArtifactDefinitionId;
    }

    /**
     * Set the businessArtifactDefinitionId property: The businessArtifactDefinitionId property.
     *
     * @param businessArtifactDefinitionId the businessArtifactDefinitionId value to set.
     * @return the BusinessArtifactCreateParams object itself.
     */
    @Generated
    public BusinessArtifactCreateParams setBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        this.businessArtifactDefinitionId = businessArtifactDefinitionId;
        return this;
    }

    /**
     * Get the tenantId property: The tenantId property.
     *
     * @return the tenantId value.
     */
    @Generated
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: The tenantId property.
     *
     * @param tenantId the tenantId value to set.
     * @return the BusinessArtifactCreateParams object itself.
     */
    @Generated
    public BusinessArtifactCreateParams setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the businessArtifactDefinitionCode property: The businessArtifactDefinitionCode property.
     *
     * @return the businessArtifactDefinitionCode value.
     */
    @Generated
    public String getBusinessArtifactDefinitionCode() {
        return this.businessArtifactDefinitionCode;
    }

    /**
     * Set the businessArtifactDefinitionCode property: The businessArtifactDefinitionCode property.
     *
     * @param businessArtifactDefinitionCode the businessArtifactDefinitionCode value to set.
     * @return the BusinessArtifactCreateParams object itself.
     */
    @Generated
    public BusinessArtifactCreateParams setBusinessArtifactDefinitionCode(String businessArtifactDefinitionCode) {
        this.businessArtifactDefinitionCode = businessArtifactDefinitionCode;
        return this;
    }

    /**
     * Get the data property: Json value.
     *
     * @return the data value.
     */
    @Generated
    public JsonValue getData() {
        return this.data;
    }

    /**
     * Set the data property: Json value.
     *
     * @param data the data value to set.
     * @return the BusinessArtifactCreateParams object itself.
     */
    @Generated
    public BusinessArtifactCreateParams setData(JsonValue data) {
        this.data = data;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("businessArtifactDefinitionId", Objects.toString(this.businessArtifactDefinitionId, null));
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("businessArtifactDefinitionCode", this.businessArtifactDefinitionCode);
        jsonWriter.writeJsonField("data", this.data);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactCreateParams if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactCreateParams.
     */
    @Generated
    public static BusinessArtifactCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactCreateParams deserializedBusinessArtifactCreateParams = new BusinessArtifactCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedBusinessArtifactCreateParams.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactDefinitionId".equals(fieldName)) {
                    deserializedBusinessArtifactCreateParams.businessArtifactDefinitionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("tenantId".equals(fieldName)) {
                    deserializedBusinessArtifactCreateParams.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactDefinitionCode".equals(fieldName)) {
                    deserializedBusinessArtifactCreateParams.businessArtifactDefinitionCode = reader.getString();
                } else if ("data".equals(fieldName)) {
                    deserializedBusinessArtifactCreateParams.data = JsonValue.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactCreateParams;
        });
    }
}
