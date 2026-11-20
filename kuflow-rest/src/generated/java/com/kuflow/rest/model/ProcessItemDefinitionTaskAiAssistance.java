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
 * The ProcessItemDefinitionTaskAiAssistance model.
 */
@Fluent
public final class ProcessItemDefinitionTaskAiAssistance implements JsonSerializable<ProcessItemDefinitionTaskAiAssistance> {

    /*
     * The enabled property.
     */
    @Generated
    private Boolean enabled;

    /*
     * The prompt property.
     */
    @Generated
    private String prompt;

    /**
     * Creates an instance of ProcessItemDefinitionTaskAiAssistance class.
     */
    @Generated
    public ProcessItemDefinitionTaskAiAssistance() {}

    /**
     * Get the enabled property: The enabled property.
     *
     * @return the enabled value.
     */
    @Generated
    public Boolean isEnabled() {
        return this.enabled;
    }

    /**
     * Set the enabled property: The enabled property.
     *
     * @param enabled the enabled value to set.
     * @return the ProcessItemDefinitionTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskAiAssistance setEnabled(Boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    /**
     * Get the prompt property: The prompt property.
     *
     * @return the prompt value.
     */
    @Generated
    public String getPrompt() {
        return this.prompt;
    }

    /**
     * Set the prompt property: The prompt property.
     *
     * @param prompt the prompt value to set.
     * @return the ProcessItemDefinitionTaskAiAssistance object itself.
     */
    @Generated
    public ProcessItemDefinitionTaskAiAssistance setPrompt(String prompt) {
        this.prompt = prompt;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeBooleanField("enabled", this.enabled);
        jsonWriter.writeStringField("prompt", this.prompt);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemDefinitionTaskAiAssistance from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemDefinitionTaskAiAssistance if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IOException If an error occurs while reading the ProcessItemDefinitionTaskAiAssistance.
     */
    @Generated
    public static ProcessItemDefinitionTaskAiAssistance fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemDefinitionTaskAiAssistance deserializedProcessItemDefinitionTaskAiAssistance =
                new ProcessItemDefinitionTaskAiAssistance();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("enabled".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskAiAssistance.enabled = reader.getNullable(JsonReader::getBoolean);
                } else if ("prompt".equals(fieldName)) {
                    deserializedProcessItemDefinitionTaskAiAssistance.prompt = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemDefinitionTaskAiAssistance;
        });
    }
}
