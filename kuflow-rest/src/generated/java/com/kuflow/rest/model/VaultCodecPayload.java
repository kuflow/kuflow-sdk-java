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
import java.util.Map;

/**
 * The VaultCodecPayload model.
 */
@Fluent
public final class VaultCodecPayload implements JsonSerializable<VaultCodecPayload> {

    /*
     * Payload data.
     */
    private Map<String, byte[]> metadata;

    /*
     * Payload data
     */
    private byte[] data;

    /**
     * Creates an instance of VaultCodecPayload class.
     */
    public VaultCodecPayload() {}

    /**
     * Get the metadata property: Payload data.
     *
     * @return the metadata value.
     */
    public Map<String, byte[]> getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: Payload data.
     *
     * @param metadata the metadata value to set.
     * @return the VaultCodecPayload object itself.
     */
    public VaultCodecPayload setMetadata(Map<String, byte[]> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the data property: Payload data.
     *
     * @return the data value.
     */
    public byte[] getData() {
        return CoreUtils.clone(this.data);
    }

    /**
     * Set the data property: Payload data.
     *
     * @param data the data value to set.
     * @return the VaultCodecPayload object itself.
     */
    public VaultCodecPayload setData(byte[] data) {
        this.data = CoreUtils.clone(data);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBinaryField("data", this.data);
        jsonWriter.writeMapField("metadata", this.metadata, (writer, element) -> writer.writeBinary(element));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of VaultCodecPayload from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of VaultCodecPayload if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the VaultCodecPayload.
     */
    public static VaultCodecPayload fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            VaultCodecPayload deserializedVaultCodecPayload = new VaultCodecPayload();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("data".equals(fieldName)) {
                    deserializedVaultCodecPayload.data = reader.getBinary();
                } else if ("metadata".equals(fieldName)) {
                    Map<String, byte[]> metadata = reader.readMap(reader1 -> reader1.getBinary());
                    deserializedVaultCodecPayload.metadata = metadata;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedVaultCodecPayload;
        });
    }
}
