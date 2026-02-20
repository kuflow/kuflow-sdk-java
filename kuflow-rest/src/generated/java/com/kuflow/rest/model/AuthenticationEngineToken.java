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
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The AuthenticationEngineToken model.
 */
@Fluent
public final class AuthenticationEngineToken implements JsonSerializable<AuthenticationEngineToken> {

    /*
     * Engine authentication token
     */
    @Generated
    private String token;

    /*
     * The expiredAt property.
     */
    @Generated
    private OffsetDateTime expiredAt;

    /**
     * Creates an instance of AuthenticationEngineToken class.
     */
    @Generated
    public AuthenticationEngineToken() {}

    /**
     * Get the token property: Engine authentication token.
     *
     * @return the token value.
     */
    @Generated
    public String getToken() {
        return this.token;
    }

    /**
     * Set the token property: Engine authentication token.
     *
     * @param token the token value to set.
     * @return the AuthenticationEngineToken object itself.
     */
    @Generated
    public AuthenticationEngineToken setToken(String token) {
        this.token = token;
        return this;
    }

    /**
     * Get the expiredAt property: The expiredAt property.
     *
     * @return the expiredAt value.
     */
    @Generated
    public OffsetDateTime getExpiredAt() {
        return this.expiredAt;
    }

    /**
     * Set the expiredAt property: The expiredAt property.
     *
     * @param expiredAt the expiredAt value to set.
     * @return the AuthenticationEngineToken object itself.
     */
    @Generated
    public AuthenticationEngineToken setExpiredAt(OffsetDateTime expiredAt) {
        this.expiredAt = expiredAt;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("token", this.token);
        jsonWriter.writeStringField(
            "expiredAt",
            this.expiredAt == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.expiredAt)
        );
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of AuthenticationEngineToken from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of AuthenticationEngineToken if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the AuthenticationEngineToken.
     */
    @Generated
    public static AuthenticationEngineToken fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            AuthenticationEngineToken deserializedAuthenticationEngineToken = new AuthenticationEngineToken();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("token".equals(fieldName)) {
                    deserializedAuthenticationEngineToken.token = reader.getString();
                } else if ("expiredAt".equals(fieldName)) {
                    deserializedAuthenticationEngineToken.expiredAt = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedAuthenticationEngineToken;
        });
    }
}
