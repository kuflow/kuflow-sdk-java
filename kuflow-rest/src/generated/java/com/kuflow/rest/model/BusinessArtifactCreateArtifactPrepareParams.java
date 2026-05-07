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
 * Params identifying a CREATE_BUSINESS_ARTIFACT action to prepare.
 */
@Fluent
public final class BusinessArtifactCreateArtifactPrepareParams implements JsonSerializable<BusinessArtifactCreateArtifactPrepareParams> {

    /*
     * The businessArtifactActionDefinitionCode property.
     */
    @Generated
    private String businessArtifactActionDefinitionCode;

    /**
     * Creates an instance of BusinessArtifactCreateArtifactPrepareParams class.
     */
    @Generated
    public BusinessArtifactCreateArtifactPrepareParams() {}

    /**
     * Get the businessArtifactActionDefinitionCode property: The businessArtifactActionDefinitionCode property.
     *
     * @return the businessArtifactActionDefinitionCode value.
     */
    @Generated
    public String getBusinessArtifactActionDefinitionCode() {
        return this.businessArtifactActionDefinitionCode;
    }

    /**
     * Set the businessArtifactActionDefinitionCode property: The businessArtifactActionDefinitionCode property.
     *
     * @param businessArtifactActionDefinitionCode the businessArtifactActionDefinitionCode value to set.
     * @return the BusinessArtifactCreateArtifactPrepareParams object itself.
     */
    @Generated
    public BusinessArtifactCreateArtifactPrepareParams setBusinessArtifactActionDefinitionCode(
        String businessArtifactActionDefinitionCode
    ) {
        this.businessArtifactActionDefinitionCode = businessArtifactActionDefinitionCode;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("businessArtifactActionDefinitionCode", this.businessArtifactActionDefinitionCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactCreateArtifactPrepareParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactCreateArtifactPrepareParams if the JsonReader was pointing to an instance
     * of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactCreateArtifactPrepareParams.
     */
    @Generated
    public static BusinessArtifactCreateArtifactPrepareParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactCreateArtifactPrepareParams deserializedBusinessArtifactCreateArtifactPrepareParams =
                new BusinessArtifactCreateArtifactPrepareParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("businessArtifactActionDefinitionCode".equals(fieldName)) {
                    deserializedBusinessArtifactCreateArtifactPrepareParams.businessArtifactActionDefinitionCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactCreateArtifactPrepareParams;
        });
    }
}
