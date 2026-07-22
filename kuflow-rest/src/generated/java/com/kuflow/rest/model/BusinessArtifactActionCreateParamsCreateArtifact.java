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

/**
 * Params for invoking an action of type CREATE_BUSINESS_ARTIFACT. The `value` is
 * the (potentially user-edited) form previously obtained via the
 * `prepareBusinessArtifactCreateArtifact` operation.
 */
@Fluent
public final class BusinessArtifactActionCreateParamsCreateArtifact
    implements JsonSerializable<BusinessArtifactActionCreateParamsCreateArtifact>
{

    /*
     * Form value to be applied to the new Business Artifact.
     */
    @Generated
    private Map<String, Object> value;

    /**
     * Creates an instance of BusinessArtifactActionCreateParamsCreateArtifact class.
     */
    @Generated
    public BusinessArtifactActionCreateParamsCreateArtifact() {}

    /**
     * Get the value property: Form value to be applied to the new Business Artifact.
     *
     * @return the value value.
     */
    @Generated
    public Map<String, Object> getValue() {
        return this.value;
    }

    /**
     * Set the value property: Form value to be applied to the new Business Artifact.
     *
     * @param value the value value to set.
     * @return the BusinessArtifactActionCreateParamsCreateArtifact object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParamsCreateArtifact setValue(Map<String, Object> value) {
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
        jsonWriter.writeMapField("value", this.value, (writer, element) -> writer.writeUntyped(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionCreateParamsCreateArtifact from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionCreateParamsCreateArtifact if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionCreateParamsCreateArtifact.
     */
    @Generated
    public static BusinessArtifactActionCreateParamsCreateArtifact fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionCreateParamsCreateArtifact deserializedBusinessArtifactActionCreateParamsCreateArtifact =
                new BusinessArtifactActionCreateParamsCreateArtifact();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("value".equals(fieldName)) {
                    Map<String, Object> value = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedBusinessArtifactActionCreateParamsCreateArtifact.value = value;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionCreateParamsCreateArtifact;
        });
    }
}
