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
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;

/**
 * The KmsKey model.
 */
@Fluent
public final class KmsKey implements JsonSerializable<KmsKey> {

    /*
     * Key Id
     */
    private String id;

    /*
     * Encryption/decryption key
     */
    private byte[] value;

    /**
     * Creates an instance of KmsKey class.
     */
    public KmsKey() {}

    /**
     * Get the id property: Key Id.
     *
     * @return the id value.
     */
    public String getId() {
        return this.id;
    }

    /**
     * Set the id property: Key Id.
     *
     * @param id the id value to set.
     * @return the KmsKey object itself.
     */
    public KmsKey setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the value property: Encryption/decryption key.
     *
     * @return the value value.
     */
    public byte[] getValue() {
        return CoreUtils.clone(this.value);
    }

    /**
     * Set the value property: Encryption/decryption key.
     *
     * @param value the value value to set.
     * @return the KmsKey object itself.
     */
    public KmsKey setValue(byte[] value) {
        this.value = CoreUtils.clone(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", this.id);
        jsonWriter.writeBinaryField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of KmsKey from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of KmsKey if the JsonReader was pointing to an instance of it, or null if it was pointing to
     * JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the KmsKey.
     */
    public static KmsKey fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            KmsKey deserializedKmsKey = new KmsKey();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedKmsKey.id = reader.getString();
                } else if ("value".equals(fieldName)) {
                    deserializedKmsKey.value = reader.getBinary();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedKmsKey;
        });
    }
}
