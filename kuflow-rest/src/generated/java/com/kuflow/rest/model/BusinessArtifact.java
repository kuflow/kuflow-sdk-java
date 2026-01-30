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
 * The BusinessArtifact model.
 */
@Fluent
public final class BusinessArtifact extends AbstractAudited {

    /*
     * Business Artifact ID.
     */
    private UUID id;

    /*
     * Tenant ID.
     */
    private UUID tenantId;

    /*
     * The businessArtifactDefinitionRef property.
     */
    private BusinessArtifactDefinitionRef businessArtifactDefinitionRef;

    /*
     * Json value.
     */
    private JsonValue data;

    /**
     * Creates an instance of BusinessArtifact class.
     */
    public BusinessArtifact() {}

    /**
     * Get the id property: Business Artifact ID.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Business Artifact ID.
     *
     * @param id the id value to set.
     * @return the BusinessArtifact object itself.
     */
    public BusinessArtifact setId(UUID id) {
        this.id = id;
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
     * @return the BusinessArtifact object itself.
     */
    public BusinessArtifact setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the businessArtifactDefinitionRef property: The businessArtifactDefinitionRef property.
     *
     * @return the businessArtifactDefinitionRef value.
     */
    public BusinessArtifactDefinitionRef getBusinessArtifactDefinitionRef() {
        return this.businessArtifactDefinitionRef;
    }

    /**
     * Set the businessArtifactDefinitionRef property: The businessArtifactDefinitionRef property.
     *
     * @param businessArtifactDefinitionRef the businessArtifactDefinitionRef value to set.
     * @return the BusinessArtifact object itself.
     */
    public BusinessArtifact setBusinessArtifactDefinitionRef(BusinessArtifactDefinitionRef businessArtifactDefinitionRef) {
        this.businessArtifactDefinitionRef = businessArtifactDefinitionRef;
        return this;
    }

    /**
     * Get the data property: Json value.
     *
     * @return the data value.
     */
    public JsonValue getData() {
        return this.data;
    }

    /**
     * Set the data property: Json value.
     *
     * @param data the data value to set.
     * @return the BusinessArtifact object itself.
     */
    public BusinessArtifact setData(JsonValue data) {
        this.data = data;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusinessArtifact setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusinessArtifact setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusinessArtifact setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public BusinessArtifact setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeJsonField("businessArtifactDefinitionRef", this.businessArtifactDefinitionRef);
        jsonWriter.writeJsonField("data", this.data);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifact from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifact if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifact.
     */
    public static BusinessArtifact fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifact deserializedBusinessArtifact = new BusinessArtifact();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedBusinessArtifact.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedBusinessArtifact.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedBusinessArtifact.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedBusinessArtifact.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedBusinessArtifact.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("tenantId".equals(fieldName)) {
                    deserializedBusinessArtifact.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("businessArtifactDefinitionRef".equals(fieldName)) {
                    deserializedBusinessArtifact.businessArtifactDefinitionRef = BusinessArtifactDefinitionRef.fromJson(reader);
                } else if ("data".equals(fieldName)) {
                    deserializedBusinessArtifact.data = JsonValue.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifact;
        });
    }
}
