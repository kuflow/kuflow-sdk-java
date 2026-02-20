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
 * The PrincipalUser model.
 */
@Fluent
public final class PrincipalUser implements JsonSerializable<PrincipalUser> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The email property.
     */
    @Generated
    private String email;

    /**
     * Creates an instance of PrincipalUser class.
     */
    @Generated
    public PrincipalUser() {}

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
     * @return the PrincipalUser object itself.
     */
    @Generated
    public PrincipalUser setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the email property: The email property.
     *
     * @return the email value.
     */
    @Generated
    public String getEmail() {
        return this.email;
    }

    /**
     * Set the email property: The email property.
     *
     * @param email the email value to set.
     * @return the PrincipalUser object itself.
     */
    @Generated
    public PrincipalUser setEmail(String email) {
        this.email = email;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("email", this.email);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PrincipalUser from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of PrincipalUser if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the PrincipalUser.
     */
    @Generated
    public static PrincipalUser fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PrincipalUser deserializedPrincipalUser = new PrincipalUser();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedPrincipalUser.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("email".equals(fieldName)) {
                    deserializedPrincipalUser.email = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPrincipalUser;
        });
    }
}
