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
 * The PageMetadata model.
 */
@Fluent
public final class PageMetadata implements JsonSerializable<PageMetadata> {

    /*
     * The size property.
     */
    private int size;

    /*
     * The page property.
     */
    private int page;

    /*
     * The totalElements property.
     */
    private long totalElements;

    /*
     * The totalPages property.
     */
    private int totalPages;

    /**
     * Creates an instance of PageMetadata class.
     */
    public PageMetadata() {}

    /**
     * Get the size property: The size property.
     *
     * @return the size value.
     */
    public int getSize() {
        return this.size;
    }

    /**
     * Set the size property: The size property.
     *
     * @param size the size value to set.
     * @return the PageMetadata object itself.
     */
    public PageMetadata setSize(int size) {
        this.size = size;
        return this;
    }

    /**
     * Get the page property: The page property.
     *
     * @return the page value.
     */
    public int getPage() {
        return this.page;
    }

    /**
     * Set the page property: The page property.
     *
     * @param page the page value to set.
     * @return the PageMetadata object itself.
     */
    public PageMetadata setPage(int page) {
        this.page = page;
        return this;
    }

    /**
     * Get the totalElements property: The totalElements property.
     *
     * @return the totalElements value.
     */
    public long getTotalElements() {
        return this.totalElements;
    }

    /**
     * Set the totalElements property: The totalElements property.
     *
     * @param totalElements the totalElements value to set.
     * @return the PageMetadata object itself.
     */
    public PageMetadata setTotalElements(long totalElements) {
        this.totalElements = totalElements;
        return this;
    }

    /**
     * Get the totalPages property: The totalPages property.
     *
     * @return the totalPages value.
     */
    public int getTotalPages() {
        return this.totalPages;
    }

    /**
     * Set the totalPages property: The totalPages property.
     *
     * @param totalPages the totalPages value to set.
     * @return the PageMetadata object itself.
     */
    public PageMetadata setTotalPages(int totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeIntField("size", this.size);
        jsonWriter.writeIntField("page", this.page);
        jsonWriter.writeLongField("totalElements", this.totalElements);
        jsonWriter.writeIntField("totalPages", this.totalPages);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PageMetadata from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of PageMetadata if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the PageMetadata.
     */
    public static PageMetadata fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PageMetadata deserializedPageMetadata = new PageMetadata();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("size".equals(fieldName)) {
                    deserializedPageMetadata.size = reader.getInt();
                } else if ("page".equals(fieldName)) {
                    deserializedPageMetadata.page = reader.getInt();
                } else if ("totalElements".equals(fieldName)) {
                    deserializedPageMetadata.totalElements = reader.getLong();
                } else if ("totalPages".equals(fieldName)) {
                    deserializedPageMetadata.totalPages = reader.getInt();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPageMetadata;
        });
    }
}
