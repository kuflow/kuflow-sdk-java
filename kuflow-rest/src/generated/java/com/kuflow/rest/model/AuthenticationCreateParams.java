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
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * The AuthenticationCreateParams model.
 */
@Fluent
public final class AuthenticationCreateParams implements JsonSerializable<AuthenticationCreateParams> {

    /*
     * The type property.
     */
    private AuthenticationType type;

    /*
     * Tenant id. This attribute is required when an OAuth2 authentication is used.
     */
    private UUID tenantId;

    /**
     * Creates an instance of AuthenticationCreateParams class.
     */
    public AuthenticationCreateParams() {}

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public AuthenticationType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the AuthenticationCreateParams object itself.
     */
    public AuthenticationCreateParams setType(AuthenticationType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the tenantId property: Tenant id. This attribute is required when an OAuth2 authentication is used.
     *
     * @return the tenantId value.
     */
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant id. This attribute is required when an OAuth2 authentication is used.
     *
     * @param tenantId the tenantId value to set.
     * @return the AuthenticationCreateParams object itself.
     */
    public AuthenticationCreateParams setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AuthenticationCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AuthenticationCreateParams if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AuthenticationCreateParams.
     */
    public static AuthenticationCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AuthenticationCreateParams deserializedAuthenticationCreateParams = new AuthenticationCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("type".equals(fieldName)) {
                    deserializedAuthenticationCreateParams.type = AuthenticationType.fromString(reader.getString());
                } else if ("tenantId".equals(fieldName)) {
                    deserializedAuthenticationCreateParams.tenantId = reader.getNullable(
                        nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAuthenticationCreateParams;
        });
    }
}
