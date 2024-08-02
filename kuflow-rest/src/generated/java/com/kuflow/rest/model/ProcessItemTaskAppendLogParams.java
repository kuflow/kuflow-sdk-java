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
 * The ProcessItemTaskAppendLogParams model.
 */
@Fluent
public final class ProcessItemTaskAppendLogParams implements JsonSerializable<ProcessItemTaskAppendLogParams> {

    /*
     * The message property.
     */
    private String message;

    /*
     * The level property.
     */
    private ProcessItemTaskLogLevel level;

    /**
     * Creates an instance of ProcessItemTaskAppendLogParams class.
     */
    public ProcessItemTaskAppendLogParams() {}

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the ProcessItemTaskAppendLogParams object itself.
     */
    public ProcessItemTaskAppendLogParams setMessage(String message) {
        this.message = message;
        return this;
    }

    /**
     * Get the level property: The level property.
     *
     * @return the level value.
     */
    public ProcessItemTaskLogLevel getLevel() {
        return this.level;
    }

    /**
     * Set the level property: The level property.
     *
     * @param level the level value to set.
     * @return the ProcessItemTaskAppendLogParams object itself.
     */
    public ProcessItemTaskAppendLogParams setLevel(ProcessItemTaskLogLevel level) {
        this.level = level;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("message", this.message);
        jsonWriter.writeStringField("level", this.level == null ? null : this.level.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskAppendLogParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskAppendLogParams if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemTaskAppendLogParams.
     */
    public static ProcessItemTaskAppendLogParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskAppendLogParams deserializedProcessItemTaskAppendLogParams = new ProcessItemTaskAppendLogParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("message".equals(fieldName)) {
                    deserializedProcessItemTaskAppendLogParams.message = reader.getString();
                } else if ("level".equals(fieldName)) {
                    deserializedProcessItemTaskAppendLogParams.level = ProcessItemTaskLogLevel.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskAppendLogParams;
        });
    }
}
