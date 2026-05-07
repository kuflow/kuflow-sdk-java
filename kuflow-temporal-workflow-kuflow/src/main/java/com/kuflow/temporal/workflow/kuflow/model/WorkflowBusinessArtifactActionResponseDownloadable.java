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

package com.kuflow.temporal.workflow.kuflow.model;

import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Objects;

/**
 * Type-specific result for actions of type {@code DOWNLOADABLE}. The user's workflow uploads the generated document via
 * the public {@code uploadBusinessArtifactDocument} endpoint and returns its URI here so the launcher links it to the
 * action value.
 */
public class WorkflowBusinessArtifactActionResponseDownloadable
    implements JsonSerializable<WorkflowBusinessArtifactActionResponseDownloadable>
{

    /**
     * URI of the document previously uploaded via the public {@code uploadBusinessArtifactDocument}
     * endpoint. The launcher uses it to link the document to the business artifact action.
     */
    private String documentUri;

    public String getDocumentUri() {
        return this.documentUri;
    }

    public void setDocumentUri(String documentUri) {
        this.documentUri = documentUri;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("documentUri", Objects.toString(this.documentUri, null));

        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WorkflowBusinessArtifactActionResponseDownloadable from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WorkflowBusinessArtifactActionResponseDownloadable if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the WorkflowBusinessArtifactActionResponseDownloadable.
     */
    public static WorkflowBusinessArtifactActionResponseDownloadable fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WorkflowBusinessArtifactActionResponseDownloadable value = new WorkflowBusinessArtifactActionResponseDownloadable();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("documentUri".equals(fieldName)) {
                    value.documentUri = reader.getNullable(JsonReader::getString);
                } else {
                    reader.skipChildren();
                }
            }

            return value;
        });
    }
}
