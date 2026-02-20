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
 * Params to assign a process item task, only one option is required.
 */
@Fluent
public final class ProcessItemTaskAssignParams implements JsonSerializable<ProcessItemTaskAssignParams> {

    /*
     * The ownerId property.
     */
    @Generated
    private UUID ownerId;

    /*
     * The ownerEmail property.
     */
    @Generated
    private String ownerEmail;

    /**
     * Creates an instance of ProcessItemTaskAssignParams class.
     */
    @Generated
    public ProcessItemTaskAssignParams() {}

    /**
     * Get the ownerId property: The ownerId property.
     *
     * @return the ownerId value.
     */
    @Generated
    public UUID getOwnerId() {
        return this.ownerId;
    }

    /**
     * Set the ownerId property: The ownerId property.
     *
     * @param ownerId the ownerId value to set.
     * @return the ProcessItemTaskAssignParams object itself.
     */
    @Generated
    public ProcessItemTaskAssignParams setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    /**
     * Get the ownerEmail property: The ownerEmail property.
     *
     * @return the ownerEmail value.
     */
    @Generated
    public String getOwnerEmail() {
        return this.ownerEmail;
    }

    /**
     * Set the ownerEmail property: The ownerEmail property.
     *
     * @param ownerEmail the ownerEmail value to set.
     * @return the ProcessItemTaskAssignParams object itself.
     */
    @Generated
    public ProcessItemTaskAssignParams setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("ownerId", Objects.toString(this.ownerId, null));
        jsonWriter.writeStringField("ownerEmail", this.ownerEmail);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskAssignParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskAssignParams if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemTaskAssignParams.
     */
    @Generated
    public static ProcessItemTaskAssignParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskAssignParams deserializedProcessItemTaskAssignParams = new ProcessItemTaskAssignParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("ownerId".equals(fieldName)) {
                    deserializedProcessItemTaskAssignParams.ownerId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("ownerEmail".equals(fieldName)) {
                    deserializedProcessItemTaskAssignParams.ownerEmail = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskAssignParams;
        });
    }
}
