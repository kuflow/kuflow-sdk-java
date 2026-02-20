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
 * The Authentication model.
 */
@Fluent
public final class Authentication extends AbstractAudited {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The type property.
     */
    @Generated
    private AuthenticationType type;

    /*
     * Tenant id. This attribute is required when an OAuth2 authentication is used.
     */
    @Generated
    private UUID tenantId;

    /*
     * The engineToken property.
     */
    @Generated
    private AuthenticationEngineToken engineToken;

    /*
     * The engineCertificate property.
     */
    @Generated
    private AuthenticationEngineCertificate engineCertificate;

    /**
     * Creates an instance of Authentication class.
     */
    @Generated
    public Authentication() {}

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
     * @return the Authentication object itself.
     */
    @Generated
    public Authentication setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    @Generated
    public AuthenticationType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the Authentication object itself.
     */
    @Generated
    public Authentication setType(AuthenticationType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the tenantId property: Tenant id. This attribute is required when an OAuth2 authentication is used.
     *
     * @return the tenantId value.
     */
    @Generated
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant id. This attribute is required when an OAuth2 authentication is used.
     *
     * @param tenantId the tenantId value to set.
     * @return the Authentication object itself.
     */
    @Generated
    public Authentication setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the engineToken property: The engineToken property.
     *
     * @return the engineToken value.
     */
    @Generated
    public AuthenticationEngineToken getEngineToken() {
        return this.engineToken;
    }

    /**
     * Set the engineToken property: The engineToken property.
     *
     * @param engineToken the engineToken value to set.
     * @return the Authentication object itself.
     */
    @Generated
    public Authentication setEngineToken(AuthenticationEngineToken engineToken) {
        this.engineToken = engineToken;
        return this;
    }

    /**
     * Get the engineCertificate property: The engineCertificate property.
     *
     * @return the engineCertificate value.
     */
    @Generated
    public AuthenticationEngineCertificate getEngineCertificate() {
        return this.engineCertificate;
    }

    /**
     * Set the engineCertificate property: The engineCertificate property.
     *
     * @param engineCertificate the engineCertificate value to set.
     * @return the Authentication object itself.
     */
    @Generated
    public Authentication setEngineCertificate(AuthenticationEngineCertificate engineCertificate) {
        this.engineCertificate = engineCertificate;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Authentication setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Authentication setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Authentication setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public Authentication setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeJsonField("engineToken", this.engineToken);
        jsonWriter.writeJsonField("engineCertificate", this.engineCertificate);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Authentication from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Authentication if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the Authentication.
     */
    @Generated
    public static Authentication fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Authentication deserializedAuthentication = new Authentication();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedAuthentication.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedAuthentication.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedAuthentication.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedAuthentication.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedAuthentication.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("type".equals(fieldName)) {
                    deserializedAuthentication.type = AuthenticationType.fromString(reader.getString());
                } else if ("tenantId".equals(fieldName)) {
                    deserializedAuthentication.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("engineToken".equals(fieldName)) {
                    deserializedAuthentication.engineToken = AuthenticationEngineToken.fromJson(reader);
                } else if ("engineCertificate".equals(fieldName)) {
                    deserializedAuthentication.engineCertificate = AuthenticationEngineCertificate.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAuthentication;
        });
    }
}
