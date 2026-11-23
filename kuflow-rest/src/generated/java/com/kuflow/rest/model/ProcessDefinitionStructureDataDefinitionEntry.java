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
 * A form of the Process Definition.
 */
@Fluent
public final class ProcessDefinitionStructureDataDefinitionEntry
    implements JsonSerializable<ProcessDefinitionStructureDataDefinitionEntry>
{

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The code property.
     */
    @Generated
    private String code;

    /*
     * The name property.
     */
    @Generated
    private String name;

    /*
     * A form, with its parts as JSON.
     */
    @Generated
    private ProcessDefinitionJsonDefinition jsonDefinition;

    /**
     * Creates an instance of ProcessDefinitionStructureDataDefinitionEntry class.
     */
    @Generated
    public ProcessDefinitionStructureDataDefinitionEntry() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    @Generated
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the ProcessDefinitionStructureDataDefinitionEntry object itself.
     */
    @Generated
    public ProcessDefinitionStructureDataDefinitionEntry setId(UUID id) {
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
     * @return the ProcessDefinitionStructureDataDefinitionEntry object itself.
     */
    @Generated
    public ProcessDefinitionStructureDataDefinitionEntry setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the ProcessDefinitionStructureDataDefinitionEntry object itself.
     */
    @Generated
    public ProcessDefinitionStructureDataDefinitionEntry setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the jsonDefinition property: A form, with its parts as JSON.
     *
     * @return the jsonDefinition value.
     */
    @Generated
    public ProcessDefinitionJsonDefinition getJsonDefinition() {
        return this.jsonDefinition;
    }

    /**
     * Set the jsonDefinition property: A form, with its parts as JSON.
     *
     * @param jsonDefinition the jsonDefinition value to set.
     * @return the ProcessDefinitionStructureDataDefinitionEntry object itself.
     */
    @Generated
    public ProcessDefinitionStructureDataDefinitionEntry setJsonDefinition(ProcessDefinitionJsonDefinition jsonDefinition) {
        this.jsonDefinition = jsonDefinition;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeJsonField("jsonDefinition", this.jsonDefinition);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionStructureDataDefinitionEntry from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionStructureDataDefinitionEntry if the JsonReader was pointing to an
     * instance of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionStructureDataDefinitionEntry.
     */
    @Generated
    public static ProcessDefinitionStructureDataDefinitionEntry fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionStructureDataDefinitionEntry deserializedProcessDefinitionStructureDataDefinitionEntry =
                new ProcessDefinitionStructureDataDefinitionEntry();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessDefinitionStructureDataDefinitionEntry.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("code".equals(fieldName)) {
                    deserializedProcessDefinitionStructureDataDefinitionEntry.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedProcessDefinitionStructureDataDefinitionEntry.name = reader.getString();
                } else if ("jsonDefinition".equals(fieldName)) {
                    deserializedProcessDefinitionStructureDataDefinitionEntry.jsonDefinition = ProcessDefinitionJsonDefinition.fromJson(
                        reader
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionStructureDataDefinitionEntry;
        });
    }
}
