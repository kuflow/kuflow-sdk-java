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
 * Params to create a Business Artifact Feed Item. Either `businessArtifactDefinitionId` or
 * `businessArtifactDefinitionCode` (with `tenantId`) must be provided. The populated sub-field (`thread`,
 * `message`) is dictated by `type`.
 */
@Fluent
public final class BusinessArtifactFeedItemCreateParams implements JsonSerializable<BusinessArtifactFeedItemCreateParams> {

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
     * The tenantId property.
     */
    @Generated
    private UUID tenantId;

    /*
     * The businessArtifactDefinitionId property.
     */
    @Generated
    private UUID businessArtifactDefinitionId;

    /*
     * The businessArtifactDefinitionCode property.
     */
    @Generated
    private String businessArtifactDefinitionCode;

    /*
     * ID of the thread in which the message is created; without it, the message is created at the top level of
     * the feed.
     * Only allowed for items of type `MESSAGE`; it must reference a thread of the same Business Artifact
     * Definition, written by the same credentials and not closed. It is not part of the idempotency key.
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
     * The thread property.
     */
    @Generated
    private BusinessArtifactFeedItemThreadParams thread;

    /*
     * The message property.
     */
    @Generated
    private BusinessArtifactFeedItemMessageParams message;

    /**
     * Creates an instance of BusinessArtifactFeedItemCreateParams class.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams() {}

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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setId(UUID id) {
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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setType(BusinessArtifactFeedItemType type) {
        this.type = type;
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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        this.businessArtifactDefinitionId = businessArtifactDefinitionId;
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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setBusinessArtifactDefinitionCode(String businessArtifactDefinitionCode) {
        this.businessArtifactDefinitionCode = businessArtifactDefinitionCode;
        return this;
    }

    /**
     * Get the businessArtifactFeedItemThreadId property: ID of the thread in which the message is created; without it,
     * the message is created at the top level of
     * the feed.
     * Only allowed for items of type `MESSAGE`; it must reference a thread of the same Business Artifact
     * Definition, written by the same credentials and not closed. It is not part of the idempotency key.
     *
     * @return the businessArtifactFeedItemThreadId value.
     */
    @Generated
    public UUID getBusinessArtifactFeedItemThreadId() {
        return this.businessArtifactFeedItemThreadId;
    }

    /**
     * Set the businessArtifactFeedItemThreadId property: ID of the thread in which the message is created; without it,
     * the message is created at the top level of
     * the feed.
     * Only allowed for items of type `MESSAGE`; it must reference a thread of the same Business Artifact
     * Definition, written by the same credentials and not closed. It is not part of the idempotency key.
     *
     * @param businessArtifactFeedItemThreadId the businessArtifactFeedItemThreadId value to set.
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setBusinessArtifactFeedItemThreadId(UUID businessArtifactFeedItemThreadId) {
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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setStatus(BusinessArtifactFeedItemStatus status) {
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
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setProgress(BusinessArtifactFeedItemProgress progress) {
        this.progress = progress;
        return this;
    }

    /**
     * Get the thread property: The thread property.
     *
     * @return the thread value.
     */
    @Generated
    public BusinessArtifactFeedItemThreadParams getThread() {
        return this.thread;
    }

    /**
     * Set the thread property: The thread property.
     *
     * @param thread the thread value to set.
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setThread(BusinessArtifactFeedItemThreadParams thread) {
        this.thread = thread;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    @Generated
    public BusinessArtifactFeedItemMessageParams getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the BusinessArtifactFeedItemCreateParams object itself.
     */
    @Generated
    public BusinessArtifactFeedItemCreateParams setMessage(BusinessArtifactFeedItemMessageParams message) {
        this.message = message;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("businessArtifactDefinitionId", Objects.toString(this.businessArtifactDefinitionId, null));
        jsonWriter.writeStringField("businessArtifactDefinitionCode", this.businessArtifactDefinitionCode);
        jsonWriter.writeStringField("businessArtifactFeedItemThreadId", Objects.toString(this.businessArtifactFeedItemThreadId, null));
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeJsonField("progress", this.progress);
        jsonWriter.writeJsonField("thread", this.thread);
        jsonWriter.writeJsonField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactFeedItemCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactFeedItemCreateParams if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactFeedItemCreateParams.
     */
    @Generated
    public static BusinessArtifactFeedItemCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactFeedItemCreateParams deserializedBusinessArtifactFeedItemCreateParams =
                new BusinessArtifactFeedItemCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("type".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.type = BusinessArtifactFeedItemType.fromString(reader.getString());
                } else if ("id".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("tenantId".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactDefinitionId".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.businessArtifactDefinitionId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("businessArtifactDefinitionCode".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.businessArtifactDefinitionCode = reader.getString();
                } else if ("businessArtifactFeedItemThreadId".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.businessArtifactFeedItemThreadId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("status".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.status = BusinessArtifactFeedItemStatus.fromString(reader.getString());
                } else if ("progress".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.progress = BusinessArtifactFeedItemProgress.fromJson(reader);
                } else if ("thread".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.thread = BusinessArtifactFeedItemThreadParams.fromJson(reader);
                } else if ("message".equals(fieldName)) {
                    deserializedBusinessArtifactFeedItemCreateParams.message = BusinessArtifactFeedItemMessageParams.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactFeedItemCreateParams;
        });
    }
}
