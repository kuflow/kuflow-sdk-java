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
 * Params for completing an action of type DOWNLOADABLE.
 */
@Fluent
public final class ProcessActionCompleteParamsDownloadable implements JsonSerializable<ProcessActionCompleteParamsDownloadable> {

    /*
     * Reference to the document produced by the action, previously uploaded with the
     * `uploadDocument` operation using the owning Process as `targetUri`.
     */
    @Generated
    private String documentUri;

    /**
     * Creates an instance of ProcessActionCompleteParamsDownloadable class.
     */
    @Generated
    public ProcessActionCompleteParamsDownloadable() {}

    /**
     * Get the documentUri property: Reference to the document produced by the action, previously uploaded with the
     * `uploadDocument` operation using the owning Process as `targetUri`.
     *
     * @return the documentUri value.
     */
    @Generated
    public String getDocumentUri() {
        return this.documentUri;
    }

    /**
     * Set the documentUri property: Reference to the document produced by the action, previously uploaded with the
     * `uploadDocument` operation using the owning Process as `targetUri`.
     *
     * @param documentUri the documentUri value to set.
     * @return the ProcessActionCompleteParamsDownloadable object itself.
     */
    @Generated
    public ProcessActionCompleteParamsDownloadable setDocumentUri(String documentUri) {
        this.documentUri = documentUri;
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
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessActionCompleteParamsDownloadable from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessActionCompleteParamsDownloadable if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessActionCompleteParamsDownloadable.
     */
    @Generated
    public static ProcessActionCompleteParamsDownloadable fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessActionCompleteParamsDownloadable deserializedProcessActionCompleteParamsDownloadable =
                new ProcessActionCompleteParamsDownloadable();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("documentUri".equals(fieldName)) {
                    deserializedProcessActionCompleteParamsDownloadable.documentUri = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessActionCompleteParamsDownloadable;
        });
    }
}
