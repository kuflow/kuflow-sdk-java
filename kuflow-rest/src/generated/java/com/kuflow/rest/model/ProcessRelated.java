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
import java.util.Objects;
import java.util.UUID;

/**
 * The ProcessRelated model.
 */
@Fluent
public final class ProcessRelated implements JsonSerializable<ProcessRelated> {

    /*
     * Processes whose relationship target is the current process.
     */
    private List<UUID> incoming;

    /*
     * Processes to which the current process relates.
     */
    private List<UUID> outcoming;

    /**
     * Creates an instance of ProcessRelated class.
     */
    public ProcessRelated() {}

    /**
     * Get the incoming property: Processes whose relationship target is the current process.
     *
     * @return the incoming value.
     */
    public List<UUID> getIncoming() {
        return this.incoming;
    }

    /**
     * Set the incoming property: Processes whose relationship target is the current process.
     *
     * @param incoming the incoming value to set.
     * @return the ProcessRelated object itself.
     */
    public ProcessRelated setIncoming(List<UUID> incoming) {
        this.incoming = incoming;
        return this;
    }

    /**
     * Get the outcoming property: Processes to which the current process relates.
     *
     * @return the outcoming value.
     */
    public List<UUID> getOutcoming() {
        return this.outcoming;
    }

    /**
     * Set the outcoming property: Processes to which the current process relates.
     *
     * @param outcoming the outcoming value to set.
     * @return the ProcessRelated object itself.
     */
    public ProcessRelated setOutcoming(List<UUID> outcoming) {
        this.outcoming = outcoming;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeArrayField("incoming", this.incoming, (writer, element) -> writer.writeString(Objects.toString(element, null)));
        jsonWriter.writeArrayField("outcoming", this.outcoming, (writer, element) -> writer.writeString(Objects.toString(element, null)));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessRelated from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessRelated if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessRelated.
     */
    public static ProcessRelated fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessRelated deserializedProcessRelated = new ProcessRelated();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("incoming".equals(fieldName)) {
                    List<UUID> incoming = reader.readArray(
                        reader1 -> reader1.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                    deserializedProcessRelated.incoming = incoming;
                } else if ("outcoming".equals(fieldName)) {
                    List<UUID> outcoming = reader.readArray(
                        reader1 -> reader1.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                    deserializedProcessRelated.outcoming = outcoming;
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessRelated;
        });
    }
}
