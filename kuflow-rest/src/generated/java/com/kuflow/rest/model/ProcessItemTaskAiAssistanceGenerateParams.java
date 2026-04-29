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
import java.util.Objects;
import java.util.UUID;

/**
 * Params identifying a logical AI assistance attempt for a process item task.
 */
@Fluent
public final class ProcessItemTaskAiAssistanceGenerateParams implements JsonSerializable<ProcessItemTaskAiAssistanceGenerateParams> {

    /*
     * Client-supplied UUID identifying this logical AI assistance attempt. Use the same
     * `requestId` to poll an in-flight or completed run. Use a new `requestId` to start a
     * fresh run once the previous one has reached a final state. While a run is PENDING,
     * calling this endpoint with a different `requestId` is rejected with 409.
     */
    @Generated
    private UUID requestId;

    /**
     * Creates an instance of ProcessItemTaskAiAssistanceGenerateParams class.
     */
    @Generated
    public ProcessItemTaskAiAssistanceGenerateParams() {}

    /**
     * Get the requestId property: Client-supplied UUID identifying this logical AI assistance attempt. Use the same
     * `requestId` to poll an in-flight or completed run. Use a new `requestId` to start a
     * fresh run once the previous one has reached a final state. While a run is PENDING,
     * calling this endpoint with a different `requestId` is rejected with 409.
     *
     * @return the requestId value.
     */
    @Generated
    public UUID getRequestId() {
        return this.requestId;
    }

    /**
     * Set the requestId property: Client-supplied UUID identifying this logical AI assistance attempt. Use the same
     * `requestId` to poll an in-flight or completed run. Use a new `requestId` to start a
     * fresh run once the previous one has reached a final state. While a run is PENDING,
     * calling this endpoint with a different `requestId` is rejected with 409.
     *
     * @param requestId the requestId value to set.
     * @return the ProcessItemTaskAiAssistanceGenerateParams object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceGenerateParams setRequestId(UUID requestId) {
        this.requestId = requestId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("requestId", Objects.toString(this.requestId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskAiAssistanceGenerateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskAiAssistanceGenerateParams if the JsonReader was pointing to an instance of
     * it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemTaskAiAssistanceGenerateParams.
     */
    @Generated
    public static ProcessItemTaskAiAssistanceGenerateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskAiAssistanceGenerateParams deserializedProcessItemTaskAiAssistanceGenerateParams =
                new ProcessItemTaskAiAssistanceGenerateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("requestId".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceGenerateParams.requestId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskAiAssistanceGenerateParams;
        });
    }
}
