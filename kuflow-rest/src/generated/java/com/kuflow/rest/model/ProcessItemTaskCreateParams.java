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
 * The ProcessItemTaskCreateParams model.
 */
@Fluent
public final class ProcessItemTaskCreateParams implements JsonSerializable<ProcessItemTaskCreateParams> {

    /*
     * Json value.
     */
    private JsonValue data;

    /**
     * Creates an instance of ProcessItemTaskCreateParams class.
     */
    public ProcessItemTaskCreateParams() {}

    /**
     * Get the data property: Json value.
     *
     * @return the data value.
     */
    public JsonValue getData() {
        return this.data;
    }

    /**
     * Set the data property: Json value.
     *
     * @param data the data value to set.
     * @return the ProcessItemTaskCreateParams object itself.
     */
    public ProcessItemTaskCreateParams setData(JsonValue data) {
        this.data = data;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("data", this.data);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskCreateParams if the JsonReader was pointing to an instance of it, or null
     * if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemTaskCreateParams.
     */
    public static ProcessItemTaskCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskCreateParams deserializedProcessItemTaskCreateParams = new ProcessItemTaskCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("data".equals(fieldName)) {
                    deserializedProcessItemTaskCreateParams.data = JsonValue.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskCreateParams;
        });
    }
}
