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
 * Result returned by the user's workflow when a BULK batch action finishes. Bulk executions are START_WORKFLOW-only,
 * so unlike {@link WorkflowBusinessArtifactActionResponse} there is no downloadable payload.
 */
public class WorkflowBusinessArtifactBulkActionResponse implements JsonSerializable<WorkflowBusinessArtifactBulkActionResponse> {

    /**
     * Optional human-readable message describing the outcome of the bulk action.
     */
    private String message;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("message", Objects.toString(this.message, null));

        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WorkflowBusinessArtifactBulkActionResponse from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WorkflowBusinessArtifactBulkActionResponse if the JsonReader was pointing to an instance
     * of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the WorkflowBusinessArtifactBulkActionResponse.
     */
    public static WorkflowBusinessArtifactBulkActionResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WorkflowBusinessArtifactBulkActionResponse value = new WorkflowBusinessArtifactBulkActionResponse();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("message".equals(fieldName)) {
                    value.message = reader.getNullable(JsonReader::getString);
                } else {
                    reader.skipChildren();
                }
            }

            return value;
        });
    }
}
