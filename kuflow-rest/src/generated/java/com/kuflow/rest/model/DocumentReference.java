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
 * The DocumentReference model.
 */
@Fluent
public final class DocumentReference implements JsonSerializable<DocumentReference> {

    /*
     * JSON Schema path related to the document. The uploaded document will be validated by the passed schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name"
     */
    private String schemaPath;

    /*
     * JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`
     */
    private String documentUri;

    /**
     * Creates an instance of DocumentReference class.
     */
    public DocumentReference() {}

    /**
     * Get the schemaPath property: JSON Schema path related to the document. The uploaded document will be validated by
     * the passed schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     *
     * @return the schemaPath value.
     */
    public String getSchemaPath() {
        return this.schemaPath;
    }

    /**
     * Set the schemaPath property: JSON Schema path related to the document. The uploaded document will be validated by
     * the passed schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     *
     * @param schemaPath the schemaPath value to set.
     * @return the DocumentReference object itself.
     */
    public DocumentReference setSchemaPath(String schemaPath) {
        this.schemaPath = schemaPath;
        return this;
    }

    /**
     * Get the documentUri property: JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`.
     *
     * @return the documentUri value.
     */
    public String getDocumentUri() {
        return this.documentUri;
    }

    /**
     * Set the documentUri property: JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`.
     *
     * @param documentUri the documentUri value to set.
     * @return the DocumentReference object itself.
     */
    public DocumentReference setDocumentUri(String documentUri) {
        this.documentUri = documentUri;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("schemaPath", this.schemaPath);
        jsonWriter.writeStringField("documentUri", this.documentUri);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of DocumentReference from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of DocumentReference if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the DocumentReference.
     */
    public static DocumentReference fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            DocumentReference deserializedDocumentReference = new DocumentReference();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("schemaPath".equals(fieldName)) {
                    deserializedDocumentReference.schemaPath = reader.getString();
                } else if ("documentUri".equals(fieldName)) {
                    deserializedDocumentReference.documentUri = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedDocumentReference;
        });
    }
}
