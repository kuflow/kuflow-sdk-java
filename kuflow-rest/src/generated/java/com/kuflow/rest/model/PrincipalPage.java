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
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.List;

/**
 * The PrincipalPage model.
 */
@Fluent
public final class PrincipalPage extends Page {

    /*
     * The content property.
     */
    private List<PrincipalPageItem> content;

    /**
     * Creates an instance of PrincipalPage class.
     */
    public PrincipalPage() {}

    /**
     * Get the content property: The content property.
     *
     * @return the content value.
     */
    public List<PrincipalPageItem> getContent() {
        return this.content;
    }

    /**
     * Set the content property: The content property.
     *
     * @param content the content value to set.
     * @return the PrincipalPage object itself.
     */
    public PrincipalPage setContent(List<PrincipalPageItem> content) {
        this.content = content;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PrincipalPage setMetadata(PageMetadata metadata) {
        super.setMetadata(metadata);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("metadata", getMetadata());
        jsonWriter.writeArrayField("content", this.content, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of PrincipalPage from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of PrincipalPage if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the PrincipalPage.
     */
    public static PrincipalPage fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            PrincipalPage deserializedPrincipalPage = new PrincipalPage();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("metadata".equals(fieldName)) {
                    deserializedPrincipalPage.setMetadata(PageMetadata.fromJson(reader));
                } else if ("content".equals(fieldName)) {
                    List<PrincipalPageItem> content = reader.readArray(reader1 -> PrincipalPageItem.fromJson(reader1));
                    deserializedPrincipalPage.content = content;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedPrincipalPage;
        });
    }
}
