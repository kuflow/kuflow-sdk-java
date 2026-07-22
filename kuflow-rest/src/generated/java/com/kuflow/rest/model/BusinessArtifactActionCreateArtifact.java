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
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * Action details for actions of type CREATE_BUSINESS_ARTIFACT.
 * Actions of this type complete synchronously, so the populated value and
 * the created Business Artifact ID are present in the invoke response.
 */
@Fluent
public final class BusinessArtifactActionCreateArtifact implements JsonSerializable<BusinessArtifactActionCreateArtifact> {

    /*
     * The businessArtifactDefinitionRef property.
     */
    @Generated
    private BusinessArtifactDefinitionRef businessArtifactDefinitionRef;

    /*
     * ID of the Business Artifact created by this action. Only present when status is COMPLETED.
     */
    @Generated
    private UUID businessArtifactId;

    /*
     * Form value applied to the new Business Artifact.
     */
    @Generated
    private Map<String, Object> value;

    /**
     * Creates an instance of BusinessArtifactActionCreateArtifact class.
     */
    @Generated
    public BusinessArtifactActionCreateArtifact() {}

    /**
     * Get the businessArtifactDefinitionRef property: The businessArtifactDefinitionRef property.
     *
     * @return the businessArtifactDefinitionRef value.
     */
    @Generated
    public BusinessArtifactDefinitionRef getBusinessArtifactDefinitionRef() {
        return this.businessArtifactDefinitionRef;
    }

    /**
     * Set the businessArtifactDefinitionRef property: The businessArtifactDefinitionRef property.
     *
     * @param businessArtifactDefinitionRef the businessArtifactDefinitionRef value to set.
     * @return the BusinessArtifactActionCreateArtifact object itself.
     */
    @Generated
    public BusinessArtifactActionCreateArtifact setBusinessArtifactDefinitionRef(
        BusinessArtifactDefinitionRef businessArtifactDefinitionRef
    ) {
        this.businessArtifactDefinitionRef = businessArtifactDefinitionRef;
        return this;
    }

    /**
     * Get the businessArtifactId property: ID of the Business Artifact created by this action. Only present when status
     * is COMPLETED.
     *
     * @return the businessArtifactId value.
     */
    @Generated
    public UUID getBusinessArtifactId() {
        return this.businessArtifactId;
    }

    /**
     * Set the businessArtifactId property: ID of the Business Artifact created by this action. Only present when status
     * is COMPLETED.
     *
     * @param businessArtifactId the businessArtifactId value to set.
     * @return the BusinessArtifactActionCreateArtifact object itself.
     */
    @Generated
    public BusinessArtifactActionCreateArtifact setBusinessArtifactId(UUID businessArtifactId) {
        this.businessArtifactId = businessArtifactId;
        return this;
    }

    /**
     * Get the value property: Form value applied to the new Business Artifact.
     *
     * @return the value value.
     */
    @Generated
    public Map<String, Object> getValue() {
        return this.value;
    }

    /**
     * Set the value property: Form value applied to the new Business Artifact.
     *
     * @param value the value value to set.
     * @return the BusinessArtifactActionCreateArtifact object itself.
     */
    @Generated
    public BusinessArtifactActionCreateArtifact setValue(Map<String, Object> value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("businessArtifactDefinitionRef", this.businessArtifactDefinitionRef);
        jsonWriter.writeStringField("businessArtifactId", Objects.toString(this.businessArtifactId, null));
        jsonWriter.writeMapField("value", this.value, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionCreateArtifact from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionCreateArtifact if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionCreateArtifact.
     */
    @Generated
    public static BusinessArtifactActionCreateArtifact fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionCreateArtifact deserializedBusinessArtifactActionCreateArtifact =
                new BusinessArtifactActionCreateArtifact();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("businessArtifactDefinitionRef".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateArtifact.businessArtifactDefinitionRef = BusinessArtifactDefinitionRef.fromJson(
                        reader
                    );
                } else if ("businessArtifactId".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateArtifact.businessArtifactId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("value".equals(fieldName)) {
                    Map<String, Object> value = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedBusinessArtifactActionCreateArtifact.value = value;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionCreateArtifact;
        });
    }
}
