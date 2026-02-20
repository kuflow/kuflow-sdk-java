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
 * The Process model.
 */
@Fluent
public final class Process extends AbstractAudited {

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
     * Json value.
     */
    @Generated
    private JsonValue metadata;

    /*
     * Json value.
     */
    @Generated
    private JsonValue entity;

    /*
     * The processRelated property.
     */
    @Generated
    private ProcessRelated processRelated;

    /*
     * Process initiator id, Principal ID.
     */
    @Generated
    private UUID initiatorId;

    /*
     * Tenant ID.
     */
    @Generated
    private UUID tenantId;

    /**
     * Creates an instance of Process class.
     */
    @Generated
    public Process() {}

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
     * @return the Process object itself.
     */
    @Generated
    public Process setId(UUID id) {
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
     * @return the Process object itself.
     */
    @Generated
    public Process setState(ProcessState state) {
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
     * @return the Process object itself.
     */
    @Generated
    public Process setProcessDefinitionRef(ProcessDefinitionRef processDefinitionRef) {
        this.processDefinitionRef = processDefinitionRef;
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
     * @return the Process object itself.
     */
    @Generated
    public Process setMetadata(JsonValue metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the entity property: Json value.
     *
     * @return the entity value.
     */
    @Generated
    public JsonValue getEntity() {
        return this.entity;
    }

    /**
     * Set the entity property: Json value.
     *
     * @param entity the entity value to set.
     * @return the Process object itself.
     */
    @Generated
    public Process setEntity(JsonValue entity) {
        this.entity = entity;
        return this;
    }

    /**
     * Get the processRelated property: The processRelated property.
     *
     * @return the processRelated value.
     */
    @Generated
    public ProcessRelated getProcessRelated() {
        return this.processRelated;
    }

    /**
     * Set the processRelated property: The processRelated property.
     *
     * @param processRelated the processRelated value to set.
     * @return the Process object itself.
     */
    @Generated
    public Process setProcessRelated(ProcessRelated processRelated) {
        this.processRelated = processRelated;
        return this;
    }

    /**
     * Get the initiatorId property: Process initiator id, Principal ID.
     *
     * @return the initiatorId value.
     */
    @Generated
    public UUID getInitiatorId() {
        return this.initiatorId;
    }

    /**
     * Set the initiatorId property: Process initiator id, Principal ID.
     *
     * @param initiatorId the initiatorId value to set.
     * @return the Process object itself.
     */
    @Generated
    public Process setInitiatorId(UUID initiatorId) {
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
     * @return the Process object itself.
     */
    @Generated
    public Process setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Process setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Process setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Process setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Process setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeJsonField("processDefinitionRef", this.processDefinitionRef);
        jsonWriter.writeJsonField("metadata", this.metadata);
        jsonWriter.writeJsonField("entity", this.entity);
        jsonWriter.writeJsonField("processRelated", this.processRelated);
        jsonWriter.writeStringField("initiatorId", Objects.toString(this.initiatorId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Process from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Process if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Process.
     */
    @Generated
    public static Process fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Process deserializedProcess = new Process();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedProcess.setCreatedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("createdAt".equals(fieldName)) {
                    deserializedProcess.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedProcess.setLastModifiedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedProcess.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedProcess.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("state".equals(fieldName)) {
                    deserializedProcess.state = ProcessState.fromString(reader.getString());
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcess.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("processDefinitionRef".equals(fieldName)) {
                    deserializedProcess.processDefinitionRef = ProcessDefinitionRef.fromJson(reader);
                } else if ("metadata".equals(fieldName)) {
                    deserializedProcess.metadata = JsonValue.fromJson(reader);
                } else if ("entity".equals(fieldName)) {
                    deserializedProcess.entity = JsonValue.fromJson(reader);
                } else if ("processRelated".equals(fieldName)) {
                    deserializedProcess.processRelated = ProcessRelated.fromJson(reader);
                } else if ("initiatorId".equals(fieldName)) {
                    deserializedProcess.initiatorId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcess;
        });
    }
}
