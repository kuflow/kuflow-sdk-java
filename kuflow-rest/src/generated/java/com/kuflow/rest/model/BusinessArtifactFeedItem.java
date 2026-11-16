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
 * An item of the activity feed of a Business Artifact Definition. The populated sub-field (`thread`, `message`) is
 * dictated by `type`. `createdBy` is its author.
 */
@Fluent
public final class BusinessArtifactFeedItem extends AbstractAudited {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * Type of a Business Artifact Feed Item.
     */
    @Generated
    private BusinessArtifactFeedItemType type;

    /*
     * Tenant ID.
     */
    @Generated
    private UUID tenantId;

    /*
     * The businessArtifactDefinitionRef property.
     */
    @Generated
    private BusinessArtifactDefinitionRef businessArtifactDefinitionRef;

    /*
     * ID of the thread this message is in, if any.
     */
    @Generated
    private UUID businessArtifactFeedItemThreadId;

    /*
     * Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the item, and a closed thread
     * closes its messages.
     */
    @Generated
    private BusinessArtifactFeedItemStatus status;

    /*
     * Progress of an item. Without `total`, the progress is indeterminate.
     */
    @Generated
    private BusinessArtifactFeedItemProgress progress;

    /*
     * First time the item went `RUNNING`.
     */
    @Generated
    private OffsetDateTime startedAt;

    /*
     * When the item reached a terminal status.
     */
    @Generated
    private OffsetDateTime finishedAt;

    /*
     * The thread property.
     */
    @Generated
    private BusinessArtifactFeedItemThread thread;

    /*
     * The message property.
     */
    @Generated
    private BusinessArtifactFeedItemMessage message;

    /**
     * Creates an instance of BusinessArtifactFeedItem class.
     */
    @Generated
    public BusinessArtifactFeedItem() {}

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
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: Type of a Business Artifact Feed Item.
     *
     * @return the type value.
     */
    @Generated
    public BusinessArtifactFeedItemType getType() {
        return this.type;
    }

    /**
     * Set the type property: Type of a Business Artifact Feed Item.
     *
     * @param type the type value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setType(BusinessArtifactFeedItemType type) {
        this.type = type;
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
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the businessArtifactDefinitionRef property: The businessArtifactDefinitionRef property.
     *
     * @return the businessArtifactDefinitionRef value.
     */
    @Generated
    public BusinessArtifactDefinitionRef getBusinessArtifactDefinitionRef() {
        return this.businessArtifactDefinitionRef;
    }

    /**
     * Set the businessArtifactDefinitionRef property: The businessArtifactDefinitionRef property.
     *
     * @param businessArtifactDefinitionRef the businessArtifactDefinitionRef value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setBusinessArtifactDefinitionRef(BusinessArtifactDefinitionRef businessArtifactDefinitionRef) {
        this.businessArtifactDefinitionRef = businessArtifactDefinitionRef;
        return this;
    }

    /**
     * Get the businessArtifactFeedItemThreadId property: ID of the thread this message is in, if any.
     *
     * @return the businessArtifactFeedItemThreadId value.
     */
    @Generated
    public UUID getBusinessArtifactFeedItemThreadId() {
        return this.businessArtifactFeedItemThreadId;
    }

    /**
     * Set the businessArtifactFeedItemThreadId property: ID of the thread this message is in, if any.
     *
     * @param businessArtifactFeedItemThreadId the businessArtifactFeedItemThreadId value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setBusinessArtifactFeedItemThreadId(UUID businessArtifactFeedItemThreadId) {
        this.businessArtifactFeedItemThreadId = businessArtifactFeedItemThreadId;
        return this;
    }

    /**
     * Get the status property: Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the
     * item, and a closed thread
     * closes its messages.
     *
     * @return the status value.
     */
    @Generated
    public BusinessArtifactFeedItemStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the
     * item, and a closed thread
     * closes its messages.
     *
     * @param status the status value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setStatus(BusinessArtifactFeedItemStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the progress property: Progress of an item. Without `total`, the progress is indeterminate.
     *
     * @return the progress value.
     */
    @Generated
    public BusinessArtifactFeedItemProgress getProgress() {
        return this.progress;
    }

    /**
     * Set the progress property: Progress of an item. Without `total`, the progress is indeterminate.
     *
     * @param progress the progress value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setProgress(BusinessArtifactFeedItemProgress progress) {
        this.progress = progress;
        return this;
    }

    /**
     * Get the startedAt property: First time the item went `RUNNING`.
     *
     * @return the startedAt value.
     */
    @Generated
    public OffsetDateTime getStartedAt() {
        return this.startedAt;
    }

    /**
     * Set the startedAt property: First time the item went `RUNNING`.
     *
     * @param startedAt the startedAt value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setStartedAt(OffsetDateTime startedAt) {
        this.startedAt = startedAt;
        return this;
    }

    /**
     * Get the finishedAt property: When the item reached a terminal status.
     *
     * @return the finishedAt value.
     */
    @Generated
    public OffsetDateTime getFinishedAt() {
        return this.finishedAt;
    }

    /**
     * Set the finishedAt property: When the item reached a terminal status.
     *
     * @param finishedAt the finishedAt value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setFinishedAt(OffsetDateTime finishedAt) {
        this.finishedAt = finishedAt;
        return this;
    }

    /**
     * Get the thread property: The thread property.
     *
     * @return the thread value.
     */
    @Generated
    public BusinessArtifactFeedItemThread getThread() {
        return this.thread;
    }

    /**
     * Set the thread property: The thread property.
     *
     * @param thread the thread value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setThread(BusinessArtifactFeedItemThread thread) {
        this.thread = thread;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    @Generated
    public BusinessArtifactFeedItemMessage getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the BusinessArtifactFeedItem object itself.
     */
    @Generated
    public BusinessArtifactFeedItem setMessage(BusinessArtifactFeedItemMessage message) {
        this.message = message;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactFeedItem setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactFeedItem setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactFeedItem setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactFeedItem setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeJsonField("businessArtifactDefinitionRef", this.businessArtifactDefinitionRef);
        jsonWriter.writeStringField("businessArtifactFeedItemThreadId", Objects.toString(this.businessArtifactFeedItemThreadId, null));
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeJsonField("progress", this.progress);
        jsonWriter.writeStringField(
            "startedAt",
            this.startedAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.startedAt)
        );
        jsonWriter.writeStringField(
            "finishedAt",
            this.finishedAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.finishedAt)
        );
        jsonWriter.writeJsonField("thread", this.thread);
        jsonWriter.writeJsonField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactFeedItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactFeedItem if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactFeedItem.
     */
    @Generated
    public static BusinessArtifactFeedItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactFeedItem deserializedBusinessArtifactFeedItem = new BusinessArtifactFeedItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("type".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.type = BusinessArtifactFeedItemType.fromString(reader.getString());
                } else if ("tenantId".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactDefinitionRef".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.businessArtifactDefinitionRef = BusinessArtifactDefinitionRef.fromJson(reader);
                } else if ("businessArtifactFeedItemThreadId".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.businessArtifactFeedItemThreadId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("status".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.status = BusinessArtifactFeedItemStatus.fromString(reader.getString());
                } else if ("progress".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.progress = BusinessArtifactFeedItemProgress.fromJson(reader);
                } else if ("startedAt".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.startedAt = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("finishedAt".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.finishedAt = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("thread".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.thread = BusinessArtifactFeedItemThread.fromJson(reader);
                } else if ("message".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItem.message = BusinessArtifactFeedItemMessage.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactFeedItem;
        });
    }
}
