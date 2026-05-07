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

/**
 * Action details for actions of type DOWNLOADABLE.
 */
@Fluent
public final class BusinessArtifactActionDownloadable implements JsonSerializable<BusinessArtifactActionDownloadable> {

    /*
     * Json value.
     */
    @Generated
    private JsonValue input;

    /*
     * URI of the generated document. Only present when status is COMPLETED.
     */
    @Generated
    private String documentUri;

    /*
     * Whether the generated document has expired. Only present when status is COMPLETED.
     */
    @Generated
    private Boolean documentExpired;

    /**
     * Creates an instance of BusinessArtifactActionDownloadable class.
     */
    @Generated
    public BusinessArtifactActionDownloadable() {}

    /**
     * Get the input property: Json value.
     *
     * @return the input value.
     */
    @Generated
    public JsonValue getInput() {
        return this.input;
    }

    /**
     * Set the input property: Json value.
     *
     * @param input the input value to set.
     * @return the BusinessArtifactActionDownloadable object itself.
     */
    @Generated
    public BusinessArtifactActionDownloadable setInput(JsonValue input) {
        this.input = input;
        return this;
    }

    /**
     * Get the documentUri property: URI of the generated document. Only present when status is COMPLETED.
     *
     * @return the documentUri value.
     */
    @Generated
    public String getDocumentUri() {
        return this.documentUri;
    }

    /**
     * Set the documentUri property: URI of the generated document. Only present when status is COMPLETED.
     *
     * @param documentUri the documentUri value to set.
     * @return the BusinessArtifactActionDownloadable object itself.
     */
    @Generated
    public BusinessArtifactActionDownloadable setDocumentUri(String documentUri) {
        this.documentUri = documentUri;
        return this;
    }

    /**
     * Get the documentExpired property: Whether the generated document has expired. Only present when status is
     * COMPLETED.
     *
     * @return the documentExpired value.
     */
    @Generated
    public Boolean isDocumentExpired() {
        return this.documentExpired;
    }

    /**
     * Set the documentExpired property: Whether the generated document has expired. Only present when status is
     * COMPLETED.
     *
     * @param documentExpired the documentExpired value to set.
     * @return the BusinessArtifactActionDownloadable object itself.
     */
    @Generated
    public BusinessArtifactActionDownloadable setDocumentExpired(Boolean documentExpired) {
        this.documentExpired = documentExpired;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("input", this.input);
        jsonWriter.writeStringField("documentUri", this.documentUri);
        jsonWriter.writeBooleanField("documentExpired", this.documentExpired);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionDownloadable from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionDownloadable if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionDownloadable.
     */
    @Generated
    public static BusinessArtifactActionDownloadable fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionDownloadable deserializedBusinessArtifactActionDownloadable = new BusinessArtifactActionDownloadable();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("input".equals(fieldName)) {
                    deserializedBusinessArtifactActionDownloadable.input = JsonValue.fromJson(reader);
                } else if ("documentUri".equals(fieldName)) {
                    deserializedBusinessArtifactActionDownloadable.documentUri = reader.getString();
                } else if ("documentExpired".equals(fieldName)) {
                    deserializedBusinessArtifactActionDownloadable.documentExpired = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionDownloadable;
        });
    }
}
