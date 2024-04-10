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

/**
 * The Page model.
 */
@Fluent
public class Page implements JsonSerializable<Page> {

    /*
     * Paged Model types.
     */
    private PagedObjectType objectType;

    /*
     * The metadata property.
     */
    private PageMetadata metadata;

    /**
     * Creates an instance of Page class.
     */
    public Page() {}

    /**
     * Get the objectType property: Paged Model types.
     *
     * @return the objectType value.
     */
    public PagedObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * Set the objectType property: Paged Model types.
     *
     * @param objectType the objectType value to set.
     * @return the Page object itself.
     */
    public Page setObjectType(PagedObjectType objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * Get the metadata property: The metadata property.
     *
     * @return the metadata value.
     */
    public PageMetadata getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: The metadata property.
     *
     * @param metadata the metadata value to set.
     * @return the Page object itself.
     */
    public Page setMetadata(PageMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("metadata", this.metadata);
        jsonWriter.writeStringField("objectType", this.objectType == null ? null : this.objectType.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of Page from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of Page if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the Page.
     */
    public static Page fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            Page deserializedPage = new Page();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("metadata".equals(fieldName)) {
                    deserializedPage.metadata = PageMetadata.fromJson(reader);
                } else if ("objectType".equals(fieldName)) {
                    deserializedPage.objectType = PagedObjectType.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPage;
        });
    }
}
