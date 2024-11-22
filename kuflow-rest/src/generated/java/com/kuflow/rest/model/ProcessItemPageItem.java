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
 * The ProcessItemPageItem model.
 */
@Fluent
public final class ProcessItemPageItem extends AbstractAudited {

    /*
     * The id property.
     */
    private UUID id;

    /*
     * Process Item Type
     */
    private ProcessItemType type;

    /*
     * The processId property.
     */
    private UUID processId;

    /*
     * Principal ID.
     */
    private UUID ownerId;

    /*
     * Tenant ID.
     */
    private UUID tenantId;

    /*
     * The processItemDefinitionRef property.
     */
    private ProcessItemDefinitionRef processItemDefinitionRef;

    /*
     * The task property.
     */
    private ProcessItemTaskPageItem task;

    /*
     * The message property.
     */
    private ProcessItemMessagePageItem message;

    /**
     * Creates an instance of ProcessItemPageItem class.
     */
    public ProcessItemPageItem() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: Process Item Type.
     *
     * @return the type value.
     */
    public ProcessItemType getType() {
        return this.type;
    }

    /**
     * Set the type property: Process Item Type.
     *
     * @param type the type value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setType(ProcessItemType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the processId property: The processId property.
     *
     * @return the processId value.
     */
    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property: The processId property.
     *
     * @param processId the processId value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the ownerId property: Principal ID.
     *
     * @return the ownerId value.
     */
    public UUID getOwnerId() {
        return this.ownerId;
    }

    /**
     * Set the ownerId property: Principal ID.
     *
     * @param ownerId the ownerId value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    /**
     * Get the tenantId property: Tenant ID.
     *
     * @return the tenantId value.
     */
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant ID.
     *
     * @param tenantId the tenantId value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the processItemDefinitionRef property: The processItemDefinitionRef property.
     *
     * @return the processItemDefinitionRef value.
     */
    public ProcessItemDefinitionRef getProcessItemDefinitionRef() {
        return this.processItemDefinitionRef;
    }

    /**
     * Set the processItemDefinitionRef property: The processItemDefinitionRef property.
     *
     * @param processItemDefinitionRef the processItemDefinitionRef value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setProcessItemDefinitionRef(ProcessItemDefinitionRef processItemDefinitionRef) {
        this.processItemDefinitionRef = processItemDefinitionRef;
        return this;
    }

    /**
     * Get the task property: The task property.
     *
     * @return the task value.
     */
    public ProcessItemTaskPageItem getTask() {
        return this.task;
    }

    /**
     * Set the task property: The task property.
     *
     * @param task the task value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setTask(ProcessItemTaskPageItem task) {
        this.task = task;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public ProcessItemMessagePageItem getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the ProcessItemPageItem object itself.
     */
    public ProcessItemPageItem setMessage(ProcessItemMessagePageItem message) {
        this.message = message;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessItemPageItem setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessItemPageItem setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessItemPageItem setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessItemPageItem setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
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
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("processId", Objects.toString(this.processId, null));
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("ownerId", Objects.toString(this.ownerId, null));
        jsonWriter.writeJsonField("processItemDefinitionRef", this.processItemDefinitionRef);
        jsonWriter.writeJsonField("task", this.task);
        jsonWriter.writeJsonField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemPageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemPageItem if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemPageItem.
     */
    public static ProcessItemPageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemPageItem deserializedProcessItemPageItem = new ProcessItemPageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedProcessItemPageItem.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedProcessItemPageItem.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedProcessItemPageItem.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedProcessItemPageItem.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedProcessItemPageItem.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("type".equals(fieldName)) {
                    deserializedProcessItemPageItem.type = ProcessItemType.fromString(reader.getString());
                } else if ("processId".equals(fieldName)) {
                    deserializedProcessItemPageItem.processId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcessItemPageItem.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("ownerId".equals(fieldName)) {
                    deserializedProcessItemPageItem.ownerId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else if ("processItemDefinitionRef".equals(fieldName)) {
                    deserializedProcessItemPageItem.processItemDefinitionRef = ProcessItemDefinitionRef.fromJson(reader);
                } else if ("task".equals(fieldName)) {
                    deserializedProcessItemPageItem.task = ProcessItemTaskPageItem.fromJson(reader);
                } else if ("message".equals(fieldName)) {
                    deserializedProcessItemPageItem.message = ProcessItemMessagePageItem.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemPageItem;
        });
    }
}
