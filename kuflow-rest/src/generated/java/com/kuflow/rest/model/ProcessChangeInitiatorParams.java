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
import java.util.Objects;
import java.util.UUID;

/**
 * Params to change the process initiator, only one option is required.
 */
@Fluent
public final class ProcessChangeInitiatorParams implements JsonSerializable<ProcessChangeInitiatorParams> {

    /*
     * The initiatorId property.
     */
    private UUID initiatorId;

    /*
     * The initiatorEmail property.
     */
    private String initiatorEmail;

    /**
     * Creates an instance of ProcessChangeInitiatorParams class.
     */
    public ProcessChangeInitiatorParams() {}

    /**
     * Get the initiatorId property: The initiatorId property.
     *
     * @return the initiatorId value.
     */
    public UUID getInitiatorId() {
        return this.initiatorId;
    }

    /**
     * Set the initiatorId property: The initiatorId property.
     *
     * @param initiatorId the initiatorId value to set.
     * @return the ProcessChangeInitiatorParams object itself.
     */
    public ProcessChangeInitiatorParams setInitiatorId(UUID initiatorId) {
        this.initiatorId = initiatorId;
        return this;
    }

    /**
     * Get the initiatorEmail property: The initiatorEmail property.
     *
     * @return the initiatorEmail value.
     */
    public String getInitiatorEmail() {
        return this.initiatorEmail;
    }

    /**
     * Set the initiatorEmail property: The initiatorEmail property.
     *
     * @param initiatorEmail the initiatorEmail value to set.
     * @return the ProcessChangeInitiatorParams object itself.
     */
    public ProcessChangeInitiatorParams setInitiatorEmail(String initiatorEmail) {
        this.initiatorEmail = initiatorEmail;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("initiatorId", Objects.toString(this.initiatorId, null));
        jsonWriter.writeStringField("initiatorEmail", this.initiatorEmail);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessChangeInitiatorParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessChangeInitiatorParams if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessChangeInitiatorParams.
     */
    public static ProcessChangeInitiatorParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessChangeInitiatorParams deserializedProcessChangeInitiatorParams = new ProcessChangeInitiatorParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("initiatorId".equals(fieldName)) {
                    deserializedProcessChangeInitiatorParams.initiatorId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("initiatorEmail".equals(fieldName)) {
                    deserializedProcessChangeInitiatorParams.initiatorEmail = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessChangeInitiatorParams;
        });
    }
}
