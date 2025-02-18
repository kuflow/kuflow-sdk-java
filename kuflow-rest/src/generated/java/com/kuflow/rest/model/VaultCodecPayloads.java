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
import java.util.List;

/**
 * The VaultCodecPayloads model.
 */
@Fluent
public final class VaultCodecPayloads implements JsonSerializable<VaultCodecPayloads> {

    /*
     * The payloads property.
     */
    private List<VaultCodecPayload> payloads;

    /**
     * Creates an instance of VaultCodecPayloads class.
     */
    public VaultCodecPayloads() {}

    /**
     * Get the payloads property: The payloads property.
     *
     * @return the payloads value.
     */
    public List<VaultCodecPayload> getPayloads() {
        return this.payloads;
    }

    /**
     * Set the payloads property: The payloads property.
     *
     * @param payloads the payloads value to set.
     * @return the VaultCodecPayloads object itself.
     */
    public VaultCodecPayloads setPayloads(List<VaultCodecPayload> payloads) {
        this.payloads = payloads;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("payloads", this.payloads, (writer, element) -> writer.writeJson(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of VaultCodecPayloads from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of VaultCodecPayloads if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the VaultCodecPayloads.
     */
    public static VaultCodecPayloads fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            VaultCodecPayloads deserializedVaultCodecPayloads = new VaultCodecPayloads();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("payloads".equals(fieldName)) {
                    List<VaultCodecPayload> payloads = reader.readArray(reader1 -> VaultCodecPayload.fromJson(reader1));
                    deserializedVaultCodecPayloads.payloads = payloads;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedVaultCodecPayloads;
        });
    }
}
