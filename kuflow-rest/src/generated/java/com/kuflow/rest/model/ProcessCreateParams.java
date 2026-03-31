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
 * Params to create a Process. Either `processDefinitionId` or
 * `processDefinitionCode` (with `tenantId`) must be provided.
 */
@Fluent
public final class ProcessCreateParams implements JsonSerializable<ProcessCreateParams> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The processDefinitionId property.
     */
    @Generated
    private UUID processDefinitionId;

    /*
     * The tenantId property.
     */
    @Generated
    private UUID tenantId;

    /*
     * The processDefinitionCode property.
     */
    @Generated
    private String processDefinitionCode;

    /*
     * Json value.
     */
    @Generated
    private JsonValue metadata;

    /*
     * The initiatorId property.
     */
    @Generated
    private UUID initiatorId;

    /*
     * The initiatorEmail property.
     */
    @Generated
    private String initiatorEmail;

    /**
     * Creates an instance of ProcessCreateParams class.
     */
    @Generated
    public ProcessCreateParams() {}

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
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the processDefinitionId property: The processDefinitionId property.
     *
     * @return the processDefinitionId value.
     */
    @Generated
    public UUID getProcessDefinitionId() {
        return this.processDefinitionId;
    }

    /**
     * Set the processDefinitionId property: The processDefinitionId property.
     *
     * @param processDefinitionId the processDefinitionId value to set.
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setProcessDefinitionId(UUID processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
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
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the processDefinitionCode property: The processDefinitionCode property.
     *
     * @return the processDefinitionCode value.
     */
    @Generated
    public String getProcessDefinitionCode() {
        return this.processDefinitionCode;
    }

    /**
     * Set the processDefinitionCode property: The processDefinitionCode property.
     *
     * @param processDefinitionCode the processDefinitionCode value to set.
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setProcessDefinitionCode(String processDefinitionCode) {
        this.processDefinitionCode = processDefinitionCode;
        return this;
    }

    /**
     * Get the metadata property: Json value.
     *
     * @return the metadata value.
     */
    @Generated
    public JsonValue getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Json value.
     *
     * @param metadata the metadata value to set.
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setMetadata(JsonValue metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the initiatorId property: The initiatorId property.
     *
     * @return the initiatorId value.
     */
    @Generated
    public UUID getInitiatorId() {
        return this.initiatorId;
    }

    /**
     * Set the initiatorId property: The initiatorId property.
     *
     * @param initiatorId the initiatorId value to set.
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setInitiatorId(UUID initiatorId) {
        this.initiatorId = initiatorId;
        return this;
    }

    /**
     * Get the initiatorEmail property: The initiatorEmail property.
     *
     * @return the initiatorEmail value.
     */
    @Generated
    public String getInitiatorEmail() {
        return this.initiatorEmail;
    }

    /**
     * Set the initiatorEmail property: The initiatorEmail property.
     *
     * @param initiatorEmail the initiatorEmail value to set.
     * @return the ProcessCreateParams object itself.
     */
    @Generated
    public ProcessCreateParams setInitiatorEmail(String initiatorEmail) {
        this.initiatorEmail = initiatorEmail;
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
        jsonWriter.writeStringField("processDefinitionId", Objects.toString(this.processDefinitionId, null));
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("processDefinitionCode", this.processDefinitionCode);
        jsonWriter.writeJsonField("metadata", this.metadata);
        jsonWriter.writeStringField("initiatorId", Objects.toString(this.initiatorId, null));
        jsonWriter.writeStringField("initiatorEmail", this.initiatorEmail);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessCreateParams if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessCreateParams.
     */
    @Generated
    public static ProcessCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessCreateParams deserializedProcessCreateParams = new ProcessCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessCreateParams.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("processDefinitionId".equals(fieldName)) {
                    deserializedProcessCreateParams.processDefinitionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcessCreateParams.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("processDefinitionCode".equals(fieldName)) {
                    deserializedProcessCreateParams.processDefinitionCode = reader.getString();
                } else if ("metadata".equals(fieldName)) {
                    deserializedProcessCreateParams.metadata = JsonValue.fromJson(reader);
                } else if ("initiatorId".equals(fieldName)) {
                    deserializedProcessCreateParams.initiatorId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("initiatorEmail".equals(fieldName)) {
                    deserializedProcessCreateParams.initiatorEmail = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessCreateParams;
        });
    }
}
