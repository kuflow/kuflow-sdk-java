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

/**
 * A change of a Process Definition. When the length of an array changes, the change is the whole array.
 */
@Fluent
public final class ProcessDefinitionChange implements JsonSerializable<ProcessDefinitionChange> {

    /*
     * The JSON Pointer of what changes in the document.
     */
    @Generated
    private String path;

    /*
     * The operation property.
     */
    @Generated
    private ProcessDefinitionChangeOperation operation;

    /**
     * Creates an instance of ProcessDefinitionChange class.
     */
    @Generated
    public ProcessDefinitionChange() {}

    /**
     * Get the path property: The JSON Pointer of what changes in the document.
     *
     * @return the path value.
     */
    @Generated
    public String getPath() {
        return this.path;
    }

    /**
     * Set the path property: The JSON Pointer of what changes in the document.
     *
     * @param path the path value to set.
     * @return the ProcessDefinitionChange object itself.
     */
    @Generated
    public ProcessDefinitionChange setPath(String path) {
        this.path = path;
        return this;
    }

    /**
     * Get the operation property: The operation property.
     *
     * @return the operation value.
     */
    @Generated
    public ProcessDefinitionChangeOperation getOperation() {
        return this.operation;
    }

    /**
     * Set the operation property: The operation property.
     *
     * @param operation the operation value to set.
     * @return the ProcessDefinitionChange object itself.
     */
    @Generated
    public ProcessDefinitionChange setOperation(ProcessDefinitionChangeOperation operation) {
        this.operation = operation;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("path", this.path);
        jsonWriter.writeStringField("operation", this.operation == null ? null : this.operation.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionChange from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionChange if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionChange.
     */
    @Generated
    public static ProcessDefinitionChange fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionChange deserializedProcessDefinitionChange = new ProcessDefinitionChange();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("path".equals(fieldName)) {
                    deserializedProcessDefinitionChange.path = reader.getString();
                } else if ("operation".equals(fieldName)) {
                    deserializedProcessDefinitionChange.operation = ProcessDefinitionChangeOperation.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionChange;
        });
    }
}
