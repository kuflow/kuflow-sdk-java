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
 * The TenantUser model.
 */
@Fluent
public final class TenantUser extends AbstractAudited {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * Json value.
     */
    @Generated
    private JsonValue metadata;

    /*
     * The principal property.
     */
    @Generated
    private Principal principal;

    /*
     * The tenantId property.
     */
    @Generated
    private UUID tenantId;

    /**
     * Creates an instance of TenantUser class.
     */
    @Generated
    public TenantUser() {}

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
     * @return the TenantUser object itself.
     */
    @Generated
    public TenantUser setId(UUID id) {
        this.id = id;
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
     * @return the TenantUser object itself.
     */
    @Generated
    public TenantUser setMetadata(JsonValue metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the principal property: The principal property.
     *
     * @return the principal value.
     */
    @Generated
    public Principal getPrincipal() {
        return this.principal;
    }

    /**
     * Set the principal property: The principal property.
     *
     * @param principal the principal value to set.
     * @return the TenantUser object itself.
     */
    @Generated
    public TenantUser setPrincipal(Principal principal) {
        this.principal = principal;
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
     * {@inheritDoc}
     */
    @Generated
    @Override
    public TenantUser setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public TenantUser setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public TenantUser setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public TenantUser setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeJsonField("principal", this.principal);
        jsonWriter.writeJsonField("metadata", this.metadata);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of TenantUser from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of TenantUser if the JsonReader was pointing to an instance of it, or null if it was pointing
     * to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the TenantUser.
     */
    @Generated
    public static TenantUser fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            TenantUser deserializedTenantUser = new TenantUser();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedTenantUser.setCreatedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("createdAt".equals(fieldName)) {
                    deserializedTenantUser.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedTenantUser.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedTenantUser.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedTenantUser.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("principal".equals(fieldName)) {
                    deserializedTenantUser.principal = Principal.fromJson(reader);
                } else if ("tenantId".equals(fieldName)) {
                    deserializedTenantUser.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("metadata".equals(fieldName)) {
                    deserializedTenantUser.metadata = JsonValue.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedTenantUser;
        });
    }
}
