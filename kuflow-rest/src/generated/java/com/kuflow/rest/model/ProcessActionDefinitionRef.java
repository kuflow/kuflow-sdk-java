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
import java.util.Objects;
import java.util.UUID;

/**
 * The ProcessActionDefinitionRef model.
 */
@Fluent
public final class ProcessActionDefinitionRef implements JsonSerializable<ProcessActionDefinitionRef> {

    /*
     * Not present when the action definition was removed from the process definition after the invocation.
     */
    @Generated
    private UUID id;

    /*
     * The code property.
     */
    @Generated
    private String code;

    /**
     * Creates an instance of ProcessActionDefinitionRef class.
     */
    @Generated
    public ProcessActionDefinitionRef() {}

    /**
     * Get the id property: Not present when the action definition was removed from the process definition after the
     * invocation.
     *
     * @return the id value.
     */
    @Generated
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Not present when the action definition was removed from the process definition after the
     * invocation.
     *
     * @param id the id value to set.
     * @return the ProcessActionDefinitionRef object itself.
     */
    @Generated
    public ProcessActionDefinitionRef setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the ProcessActionDefinitionRef object itself.
     */
    @Generated
    public ProcessActionDefinitionRef setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessActionDefinitionRef from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessActionDefinitionRef if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessActionDefinitionRef.
     */
    @Generated
    public static ProcessActionDefinitionRef fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessActionDefinitionRef deserializedProcessActionDefinitionRef = new ProcessActionDefinitionRef();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("code".equals(fieldName)) {
                    deserializedProcessActionDefinitionRef.code = reader.getString();
                } else if ("id".equals(fieldName)) {
                    deserializedProcessActionDefinitionRef.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessActionDefinitionRef;
        });
    }
}
