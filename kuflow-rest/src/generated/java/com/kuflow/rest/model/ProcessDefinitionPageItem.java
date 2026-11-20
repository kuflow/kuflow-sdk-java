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
 * The ProcessDefinitionPageItem model.
 */
@Fluent
public final class ProcessDefinitionPageItem implements JsonSerializable<ProcessDefinitionPageItem> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The code property.
     */
    @Generated
    private String code;

    /*
     * The name property.
     */
    @Generated
    private String name;

    /*
     * The tenantId property.
     */
    @Generated
    private UUID tenantId;

    /*
     * State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes can be created from it,
     * and UNPUBLISHED if no process can be created from it anymore.
     */
    @Generated
    private ProcessDefinitionState state;

    /*
     * The version property.
     */
    @Generated
    private ProcessDefinitionVersion version;

    /**
     * Creates an instance of ProcessDefinitionPageItem class.
     */
    @Generated
    public ProcessDefinitionPageItem() {}

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
     * @return the ProcessDefinitionPageItem object itself.
     */
    @Generated
    public ProcessDefinitionPageItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the ProcessDefinitionPageItem object itself.
     */
    @Generated
    public ProcessDefinitionPageItem setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the ProcessDefinitionPageItem object itself.
     */
    @Generated
    public ProcessDefinitionPageItem setName(String name) {
        this.name = name;
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
     * @return the ProcessDefinitionPageItem object itself.
     */
    @Generated
    public ProcessDefinitionPageItem setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the state property: State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes
     * can be created from it,
     * and UNPUBLISHED if no process can be created from it anymore.
     *
     * @return the state value.
     */
    @Generated
    public ProcessDefinitionState getState() {
        return this.state;
    }

    /**
     * Set the state property: State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes
     * can be created from it,
     * and UNPUBLISHED if no process can be created from it anymore.
     *
     * @param state the state value to set.
     * @return the ProcessDefinitionPageItem object itself.
     */
    @Generated
    public ProcessDefinitionPageItem setState(ProcessDefinitionState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the version property: The version property.
     *
     * @return the version value.
     */
    @Generated
    public ProcessDefinitionVersion getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version property.
     *
     * @param version the version value to set.
     * @return the ProcessDefinitionPageItem object itself.
     */
    @Generated
    public ProcessDefinitionPageItem setVersion(ProcessDefinitionVersion version) {
        this.version = version;
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
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeJsonField("version", this.version);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionPageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionPageItem if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionPageItem.
     */
    @Generated
    public static ProcessDefinitionPageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionPageItem deserializedProcessDefinitionPageItem = new ProcessDefinitionPageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessDefinitionPageItem.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("code".equals(fieldName)) {
                    deserializedProcessDefinitionPageItem.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedProcessDefinitionPageItem.name = reader.getString();
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcessDefinitionPageItem.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("state".equals(fieldName)) {
                    deserializedProcessDefinitionPageItem.state = ProcessDefinitionState.fromString(reader.getString());
                } else if ("version".equals(fieldName)) {
                    deserializedProcessDefinitionPageItem.version = ProcessDefinitionVersion.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionPageItem;
        });
    }
}
