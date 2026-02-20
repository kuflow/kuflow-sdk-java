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
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * The ProcessPageItem model.
 */
@Fluent
public final class ProcessPageItem extends AbstractAudited {

    /*
     * Process ID.
     */
    @Generated
    private UUID id;

    /*
     * Process state
     */
    @Generated
    private ProcessState state;

    /*
     * The processDefinitionRef property.
     */
    @Generated
    private ProcessDefinitionRef processDefinitionRef;

    /*
     * Principal ID.
     */
    @Generated
    private UUID initiatorId;

    /*
     * Tenant ID.
     */
    @Generated
    private UUID tenantId;

    /**
     * Creates an instance of ProcessPageItem class.
     */
    @Generated
    public ProcessPageItem() {}

    /**
     * Get the id property: Process ID.
     *
     * @return the id value.
     */
    @Generated
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Process ID.
     *
     * @param id the id value to set.
     * @return the ProcessPageItem object itself.
     */
    @Generated
    public ProcessPageItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the state property: Process state.
     *
     * @return the state value.
     */
    @Generated
    public ProcessState getState() {
        return this.state;
    }

    /**
     * Set the state property: Process state.
     *
     * @param state the state value to set.
     * @return the ProcessPageItem object itself.
     */
    @Generated
    public ProcessPageItem setState(ProcessState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the processDefinitionRef property: The processDefinitionRef property.
     *
     * @return the processDefinitionRef value.
     */
    @Generated
    public ProcessDefinitionRef getProcessDefinitionRef() {
        return this.processDefinitionRef;
    }

    /**
     * Set the processDefinitionRef property: The processDefinitionRef property.
     *
     * @param processDefinitionRef the processDefinitionRef value to set.
     * @return the ProcessPageItem object itself.
     */
    @Generated
    public ProcessPageItem setProcessDefinitionRef(ProcessDefinitionRef processDefinitionRef) {
        this.processDefinitionRef = processDefinitionRef;
        return this;
    }

    /**
     * Get the initiatorId property: Principal ID.
     *
     * @return the initiatorId value.
     */
    @Generated
    public UUID getInitiatorId() {
        return this.initiatorId;
    }

    /**
     * Set the initiatorId property: Principal ID.
     *
     * @param initiatorId the initiatorId value to set.
     * @return the ProcessPageItem object itself.
     */
    @Generated
    public ProcessPageItem setInitiatorId(UUID initiatorId) {
        this.initiatorId = initiatorId;
        return this;
    }

    /**
     * Get the tenantId property: Tenant ID.
     *
     * @return the tenantId value.
     */
    @Generated
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant ID.
     *
     * @param tenantId the tenantId value to set.
     * @return the ProcessPageItem object itself.
     */
    @Generated
    public ProcessPageItem setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessPageItem setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessPageItem setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessPageItem setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessPageItem setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("createdBy", Objects.toString(getCreatedBy(), null));
        jsonWriter.writeStringField(
            "createdAt",
            getCreatedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getCreatedAt())
        );
        jsonWriter.writeStringField("lastModifiedBy", Objects.toString(getLastModifiedBy(), null));
        jsonWriter.writeStringField(
            "lastModifiedAt",
            getLastModifiedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getLastModifiedAt())
        );
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeJsonField("processDefinitionRef", this.processDefinitionRef);
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("initiatorId", Objects.toString(this.initiatorId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessPageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessPageItem if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessPageItem.
     */
    @Generated
    public static ProcessPageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessPageItem deserializedProcessPageItem = new ProcessPageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedProcessPageItem.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedProcessPageItem.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedProcessPageItem.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedProcessPageItem.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedProcessPageItem.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("state".equals(fieldName)) {
                    deserializedProcessPageItem.state = ProcessState.fromString(reader.getString());
                } else if ("processDefinitionRef".equals(fieldName)) {
                    deserializedProcessPageItem.processDefinitionRef = ProcessDefinitionRef.fromJson(reader);
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcessPageItem.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("initiatorId".equals(fieldName)) {
                    deserializedProcessPageItem.initiatorId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessPageItem;
        });
    }
}
