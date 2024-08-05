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

/**
 * The ProcessEntityUpdateParams model.
 */
@Fluent
public final class ProcessEntityUpdateParams implements JsonSerializable<ProcessEntityUpdateParams> {

    /*
     * Json value.
     */
    private JsonValue entity;

    /**
     * Creates an instance of ProcessEntityUpdateParams class.
     */
    public ProcessEntityUpdateParams() {}

    /**
     * Get the entity property: Json value.
     *
     * @return the entity value.
     */
    public JsonValue getEntity() {
        return this.entity;
    }

    /**
     * Set the entity property: Json value.
     *
     * @param entity the entity value to set.
     * @return the ProcessEntityUpdateParams object itself.
     */
    public ProcessEntityUpdateParams setEntity(JsonValue entity) {
        this.entity = entity;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("entity", this.entity);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessEntityUpdateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessEntityUpdateParams if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessEntityUpdateParams.
     */
    public static ProcessEntityUpdateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessEntityUpdateParams deserializedProcessEntityUpdateParams = new ProcessEntityUpdateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("entity".equals(fieldName)) {
                    deserializedProcessEntityUpdateParams.entity = JsonValue.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessEntityUpdateParams;
        });
    }
}
