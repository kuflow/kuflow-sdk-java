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
 * The JsonPatchOperation model.
 */
@Fluent
public final class JsonPatchOperation implements JsonSerializable<JsonPatchOperation> {

    /*
     * The operation to perform.
     */
    private JsonPatchOperationType op;

    /*
     * A JSON Pointer path used when op is "copy" or "move".
     */
    private String from;

    /*
     * A JSON Pointer path.
     */
    private String path;

    /*
     * The value to "add", "replace" or "test".
     */
    private Object value;

    /**
     * Creates an instance of JsonPatchOperation class.
     */
    public JsonPatchOperation() {}

    /**
     * Get the op property: The operation to perform.
     *
     * @return the op value.
     */
    public JsonPatchOperationType getOp() {
        return this.op;
    }

    /**
     * Set the op property: The operation to perform.
     *
     * @param op the op value to set.
     * @return the JsonPatchOperation object itself.
     */
    public JsonPatchOperation setOp(JsonPatchOperationType op) {
        this.op = op;
        return this;
    }

    /**
     * Get the from property: A JSON Pointer path used when op is "copy" or "move".
     *
     * @return the from value.
     */
    public String getFrom() {
        return this.from;
    }

    /**
     * Set the from property: A JSON Pointer path used when op is "copy" or "move".
     *
     * @param from the from value to set.
     * @return the JsonPatchOperation object itself.
     */
    public JsonPatchOperation setFrom(String from) {
        this.from = from;
        return this;
    }

    /**
     * Get the path property: A JSON Pointer path.
     *
     * @return the path value.
     */
    public String getPath() {
        return this.path;
    }

    /**
     * Set the path property: A JSON Pointer path.
     *
     * @param path the path value to set.
     * @return the JsonPatchOperation object itself.
     */
    public JsonPatchOperation setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get the value property: The value to "add", "replace" or "test".
     *
     * @return the value value.
     */
    public Object getValue() {
        return this.value;
    }

    /**
     * Set the value property: The value to "add", "replace" or "test".
     *
     * @param value the value value to set.
     * @return the JsonPatchOperation object itself.
     */
    public JsonPatchOperation setValue(Object value) {
        this.value = value;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("op", this.op == null ? null : this.op.toString());
        jsonWriter.writeStringField("path", this.path);
        jsonWriter.writeStringField("from", this.from);
        jsonWriter.writeUntypedField("value", this.value);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of JsonPatchOperation from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of JsonPatchOperation if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the JsonPatchOperation.
     */
    public static JsonPatchOperation fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            JsonPatchOperation deserializedJsonPatchOperation = new JsonPatchOperation();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("op".equals(fieldName)) {
                    deserializedJsonPatchOperation.op = JsonPatchOperationType.fromString(reader.getString());
                } else if ("path".equals(fieldName)) {
                    deserializedJsonPatchOperation.path = reader.getString();
                } else if ("from".equals(fieldName)) {
                    deserializedJsonPatchOperation.from = reader.getString();
                } else if ("value".equals(fieldName)) {
                    deserializedJsonPatchOperation.value = reader.readUntyped();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedJsonPatchOperation;
        });
    }
}
