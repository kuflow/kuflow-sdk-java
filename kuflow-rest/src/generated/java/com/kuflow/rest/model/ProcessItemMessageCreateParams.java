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
 * The ProcessItemMessageCreateParams model.
 */
@Fluent
public final class ProcessItemMessageCreateParams implements JsonSerializable<ProcessItemMessageCreateParams> {

    /*
     * The text property.
     */
    private String text;

    /*
     * Json value.
     */
    private JsonValue data;

    /*
     * The dataStructureDataDefinitionCode property.
     */
    private String dataStructureDataDefinitionCode;

    /**
     * Creates an instance of ProcessItemMessageCreateParams class.
     */
    public ProcessItemMessageCreateParams() {}

    /**
     * Get the text property: The text property.
     *
     * @return the text value.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text property: The text property.
     *
     * @param text the text value to set.
     * @return the ProcessItemMessageCreateParams object itself.
     */
    public ProcessItemMessageCreateParams setText(String text) {
        this.text = text;
        return this;
    }

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
     * @return the ProcessItemMessageCreateParams object itself.
     */
    public ProcessItemMessageCreateParams setData(JsonValue data) {
        this.data = data;
        return this;
    }

    /**
     * Get the dataStructureDataDefinitionCode property: The dataStructureDataDefinitionCode property.
     *
     * @return the dataStructureDataDefinitionCode value.
     */
    public String getDataStructureDataDefinitionCode() {
        return this.dataStructureDataDefinitionCode;
    }

    /**
     * Set the dataStructureDataDefinitionCode property: The dataStructureDataDefinitionCode property.
     *
     * @param dataStructureDataDefinitionCode the dataStructureDataDefinitionCode value to set.
     * @return the ProcessItemMessageCreateParams object itself.
     */
    public ProcessItemMessageCreateParams setDataStructureDataDefinitionCode(String dataStructureDataDefinitionCode) {
        this.dataStructureDataDefinitionCode = dataStructureDataDefinitionCode;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("text", this.text);
        jsonWriter.writeJsonField("data", this.data);
        jsonWriter.writeStringField("dataStructureDataDefinitionCode", this.dataStructureDataDefinitionCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemMessageCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemMessageCreateParams if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemMessageCreateParams.
     */
    public static ProcessItemMessageCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemMessageCreateParams deserializedProcessItemMessageCreateParams = new ProcessItemMessageCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("text".equals(fieldName)) {
                    deserializedProcessItemMessageCreateParams.text = reader.getString();
                } else if ("data".equals(fieldName)) {
                    deserializedProcessItemMessageCreateParams.data = JsonValue.fromJson(reader);
                } else if ("dataStructureDataDefinitionCode".equals(fieldName)) {
                    deserializedProcessItemMessageCreateParams.dataStructureDataDefinitionCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemMessageCreateParams;
        });
    }
}
