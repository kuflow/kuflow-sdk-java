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
import java.util.Map;

/**
 * A form, with its parts as JSON.
 */
@Fluent
public final class ProcessDefinitionJsonDefinition implements JsonSerializable<ProcessDefinitionJsonDefinition> {

    /*
     * The type property.
     */
    @Generated
    private ProcessDefinitionFormType type;

    /*
     * Dictionary of <any>
     */
    @Generated
    private Map<String, Object> schema;

    /*
     * Dictionary of <any>
     */
    @Generated
    private Map<String, Object> uiSchema;

    /*
     * Dictionary of <any>
     */
    @Generated
    private Map<String, Object> sampleData;

    /*
     * Dictionary of <any>
     */
    @Generated
    private Map<String, Object> i18N;

    /*
     * A Handlebars template.
     */
    @Generated
    private String template;

    /**
     * Creates an instance of ProcessDefinitionJsonDefinition class.
     */
    @Generated
    public ProcessDefinitionJsonDefinition() {}

    /**
     * Get the type property: The type property.
     *
     * @return the type value.
     */
    @Generated
    public ProcessDefinitionFormType getType() {
        return this.type;
    }

    /**
     * Set the type property: The type property.
     *
     * @param type the type value to set.
     * @return the ProcessDefinitionJsonDefinition object itself.
     */
    @Generated
    public ProcessDefinitionJsonDefinition setType(ProcessDefinitionFormType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the schema property: Dictionary of &lt;any&gt;.
     *
     * @return the schema value.
     */
    @Generated
    public Map<String, Object> getSchema() {
        return this.schema;
    }

    /**
     * Set the schema property: Dictionary of &lt;any&gt;.
     *
     * @param schema the schema value to set.
     * @return the ProcessDefinitionJsonDefinition object itself.
     */
    @Generated
    public ProcessDefinitionJsonDefinition setSchema(Map<String, Object> schema) {
        this.schema = schema;
        return this;
    }

    /**
     * Get the uiSchema property: Dictionary of &lt;any&gt;.
     *
     * @return the uiSchema value.
     */
    @Generated
    public Map<String, Object> getUiSchema() {
        return this.uiSchema;
    }

    /**
     * Set the uiSchema property: Dictionary of &lt;any&gt;.
     *
     * @param uiSchema the uiSchema value to set.
     * @return the ProcessDefinitionJsonDefinition object itself.
     */
    @Generated
    public ProcessDefinitionJsonDefinition setUiSchema(Map<String, Object> uiSchema) {
        this.uiSchema = uiSchema;
        return this;
    }

    /**
     * Get the sampleData property: Dictionary of &lt;any&gt;.
     *
     * @return the sampleData value.
     */
    @Generated
    public Map<String, Object> getSampleData() {
        return this.sampleData;
    }

    /**
     * Set the sampleData property: Dictionary of &lt;any&gt;.
     *
     * @param sampleData the sampleData value to set.
     * @return the ProcessDefinitionJsonDefinition object itself.
     */
    @Generated
    public ProcessDefinitionJsonDefinition setSampleData(Map<String, Object> sampleData) {
        this.sampleData = sampleData;
        return this;
    }

    /**
     * Get the i18N property: Dictionary of &lt;any&gt;.
     *
     * @return the i18N value.
     */
    @Generated
    public Map<String, Object> getI18N() {
        return this.i18N;
    }

    /**
     * Set the i18N property: Dictionary of &lt;any&gt;.
     *
     * @param i18N the i18N value to set.
     * @return the ProcessDefinitionJsonDefinition object itself.
     */
    @Generated
    public ProcessDefinitionJsonDefinition setI18N(Map<String, Object> i18N) {
        this.i18N = i18N;
        return this;
    }

    /**
     * Get the template property: A Handlebars template.
     *
     * @return the template value.
     */
    @Generated
    public String getTemplate() {
        return this.template;
    }

    /**
     * Set the template property: A Handlebars template.
     *
     * @param template the template value to set.
     * @return the ProcessDefinitionJsonDefinition object itself.
     */
    @Generated
    public ProcessDefinitionJsonDefinition setTemplate(String template) {
        this.template = template;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeMapField("schema", this.schema, (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeMapField("uiSchema", this.uiSchema, (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeMapField("sampleData", this.sampleData, (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeMapField("i18n", this.i18N, (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeStringField("template", this.template);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionJsonDefinition from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionJsonDefinition if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionJsonDefinition.
     */
    @Generated
    public static ProcessDefinitionJsonDefinition fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionJsonDefinition deserializedProcessDefinitionJsonDefinition = new ProcessDefinitionJsonDefinition();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("type".equals(fieldName)) {
                    deserializedProcessDefinitionJsonDefinition.type = ProcessDefinitionFormType.fromString(reader.getString());
                } else if ("schema".equals(fieldName)) {
                    Map<String, Object> schema = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedProcessDefinitionJsonDefinition.schema = schema;
                } else if ("uiSchema".equals(fieldName)) {
                    Map<String, Object> uiSchema = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedProcessDefinitionJsonDefinition.uiSchema = uiSchema;
                } else if ("sampleData".equals(fieldName)) {
                    Map<String, Object> sampleData = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedProcessDefinitionJsonDefinition.sampleData = sampleData;
                } else if ("i18n".equals(fieldName)) {
                    Map<String, Object> i18N = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedProcessDefinitionJsonDefinition.i18N = i18N;
                } else if ("template".equals(fieldName)) {
                    deserializedProcessDefinitionJsonDefinition.template = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionJsonDefinition;
        });
    }
}
