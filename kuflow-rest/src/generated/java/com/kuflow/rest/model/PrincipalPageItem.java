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
 * The PrincipalPageItem model.
 */
@Fluent
public final class PrincipalPageItem implements JsonSerializable<PrincipalPageItem> {

    /*
     * The id property.
     */
    private UUID id;

    /*
     * The type property.
     */
    private PrincipalType type;

    /*
     * The name property.
     */
    private String name;

    /**
     * Creates an instance of PrincipalPageItem class.
     */
    public PrincipalPageItem() {}

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
     * @return the PrincipalPageItem object itself.
     */
    public PrincipalPageItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    public PrincipalType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the PrincipalPageItem object itself.
     */
    public PrincipalPageItem setType(PrincipalType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the PrincipalPageItem object itself.
     */
    public PrincipalPageItem setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("name", this.name);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PrincipalPageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of PrincipalPageItem if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the PrincipalPageItem.
     */
    public static PrincipalPageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PrincipalPageItem deserializedPrincipalPageItem = new PrincipalPageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedPrincipalPageItem.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("type".equals(fieldName)) {
                    deserializedPrincipalPageItem.type = PrincipalType.fromString(reader.getString());
                } else if ("name".equals(fieldName)) {
                    deserializedPrincipalPageItem.name = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPrincipalPageItem;
        });
    }
}
