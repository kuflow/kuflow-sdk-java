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
public final class ProcessActionDownloadable implements JsonSerializable<ProcessActionDownloadable> {

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
     * Creates an instance of ProcessActionDownloadable class.
     */
    @Generated
    public ProcessActionDownloadable() {}

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
     * @return the ProcessActionDownloadable object itself.
     */
    @Generated
    public ProcessActionDownloadable setDocumentUri(String documentUri) {
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
     * @return the ProcessActionDownloadable object itself.
     */
    @Generated
    public ProcessActionDownloadable setDocumentExpired(Boolean documentExpired) {
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
        jsonWriter.writeStringField("documentUri", this.documentUri);
        jsonWriter.writeBooleanField("documentExpired", this.documentExpired);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessActionDownloadable from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessActionDownloadable if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessActionDownloadable.
     */
    @Generated
    public static ProcessActionDownloadable fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessActionDownloadable deserializedProcessActionDownloadable = new ProcessActionDownloadable();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("documentUri".equals(fieldName)) {
                    deserializedProcessActionDownloadable.documentUri = reader.getString();
                } else if ("documentExpired".equals(fieldName)) {
                    deserializedProcessActionDownloadable.documentExpired = reader.getNullable(JsonReader::getBoolean);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessActionDownloadable;
        });
    }
}
