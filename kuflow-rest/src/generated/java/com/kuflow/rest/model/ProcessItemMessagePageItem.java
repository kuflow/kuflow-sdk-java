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
 * The ProcessItemMessagePageItem model.
 */
@Fluent
public final class ProcessItemMessagePageItem implements JsonSerializable<ProcessItemMessagePageItem> {

    /*
     * Message text in Markdown format according to the specification https://spec.commonmark.org/
     */
    private String text;

    /*
     * The dataStructureDataDefinitionCode property.
     */
    private String dataStructureDataDefinitionCode;

    /**
     * Creates an instance of ProcessItemMessagePageItem class.
     */
    public ProcessItemMessagePageItem() {}

    /**
     * Get the text property: Message text in Markdown format according to the specification
     * https://spec.commonmark.org/.
     *
     * @return the text value.
     */
    public String getText() {
        return this.text;
    }

    /**
     * Set the text property: Message text in Markdown format according to the specification
     * https://spec.commonmark.org/.
     *
     * @param text the text value to set.
     * @return the ProcessItemMessagePageItem object itself.
     */
    public ProcessItemMessagePageItem setText(String text) {
        this.text = text;
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
     * @return the ProcessItemMessagePageItem object itself.
     */
    public ProcessItemMessagePageItem setDataStructureDataDefinitionCode(String dataStructureDataDefinitionCode) {
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
        jsonWriter.writeStringField("dataStructureDataDefinitionCode", this.dataStructureDataDefinitionCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemMessagePageItem from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemMessagePageItem if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemMessagePageItem.
     */
    public static ProcessItemMessagePageItem fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemMessagePageItem deserializedProcessItemMessagePageItem = new ProcessItemMessagePageItem();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("text".equals(fieldName)) {
                    deserializedProcessItemMessagePageItem.text = reader.getString();
                } else if ("dataStructureDataDefinitionCode".equals(fieldName)) {
                    deserializedProcessItemMessagePageItem.dataStructureDataDefinitionCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemMessagePageItem;
        });
    }
}
