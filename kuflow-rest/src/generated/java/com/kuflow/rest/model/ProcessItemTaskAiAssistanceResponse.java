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
 * Response of an AI assistance action applied to a process item task.
 */
@Fluent
public final class ProcessItemTaskAiAssistanceResponse implements JsonSerializable<ProcessItemTaskAiAssistanceResponse> {

    /*
     * The processItem property.
     */
    @Generated
    private ProcessItem processItem;

    /*
     * The AI model used for the assistance.
     */
    @Generated
    private String model;

    /*
     * The reason the AI model stopped generating.
     */
    @Generated
    private String finishReason;

    /*
     * The number of tokens in the prompt.
     */
    @Generated
    private Integer promptTokens;

    /*
     * The number of tokens in the AI completion.
     */
    @Generated
    private Integer completionTokens;

    /*
     * The total number of tokens used.
     */
    @Generated
    private Integer totalTokens;

    /**
     * Creates an instance of ProcessItemTaskAiAssistanceResponse class.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse() {}

    /**
     * Get the processItem property: The processItem property.
     *
     * @return the processItem value.
     */
    @Generated
    public ProcessItem getProcessItem() {
        return this.processItem;
    }

    /**
     * Set the processItem property: The processItem property.
     *
     * @param processItem the processItem value to set.
     * @return the ProcessItemTaskAiAssistanceResponse object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse setProcessItem(ProcessItem processItem) {
        this.processItem = processItem;
        return this;
    }

    /**
     * Get the model property: The AI model used for the assistance.
     *
     * @return the model value.
     */
    @Generated
    public String getModel() {
        return this.model;
    }

    /**
     * Set the model property: The AI model used for the assistance.
     *
     * @param model the model value to set.
     * @return the ProcessItemTaskAiAssistanceResponse object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse setModel(String model) {
        this.model = model;
        return this;
    }

    /**
     * Get the finishReason property: The reason the AI model stopped generating.
     *
     * @return the finishReason value.
     */
    @Generated
    public String getFinishReason() {
        return this.finishReason;
    }

    /**
     * Set the finishReason property: The reason the AI model stopped generating.
     *
     * @param finishReason the finishReason value to set.
     * @return the ProcessItemTaskAiAssistanceResponse object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse setFinishReason(String finishReason) {
        this.finishReason = finishReason;
        return this;
    }

    /**
     * Get the promptTokens property: The number of tokens in the prompt.
     *
     * @return the promptTokens value.
     */
    @Generated
    public Integer getPromptTokens() {
        return this.promptTokens;
    }

    /**
     * Set the promptTokens property: The number of tokens in the prompt.
     *
     * @param promptTokens the promptTokens value to set.
     * @return the ProcessItemTaskAiAssistanceResponse object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse setPromptTokens(Integer promptTokens) {
        this.promptTokens = promptTokens;
        return this;
    }

    /**
     * Get the completionTokens property: The number of tokens in the AI completion.
     *
     * @return the completionTokens value.
     */
    @Generated
    public Integer getCompletionTokens() {
        return this.completionTokens;
    }

    /**
     * Set the completionTokens property: The number of tokens in the AI completion.
     *
     * @param completionTokens the completionTokens value to set.
     * @return the ProcessItemTaskAiAssistanceResponse object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse setCompletionTokens(Integer completionTokens) {
        this.completionTokens = completionTokens;
        return this;
    }

    /**
     * Get the totalTokens property: The total number of tokens used.
     *
     * @return the totalTokens value.
     */
    @Generated
    public Integer getTotalTokens() {
        return this.totalTokens;
    }

    /**
     * Set the totalTokens property: The total number of tokens used.
     *
     * @param totalTokens the totalTokens value to set.
     * @return the ProcessItemTaskAiAssistanceResponse object itself.
     */
    @Generated
    public ProcessItemTaskAiAssistanceResponse setTotalTokens(Integer totalTokens) {
        this.totalTokens = totalTokens;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeJsonField("processItem", this.processItem);
        jsonWriter.writeStringField("model", this.model);
        jsonWriter.writeStringField("finishReason", this.finishReason);
        jsonWriter.writeNumberField("promptTokens", this.promptTokens);
        jsonWriter.writeNumberField("completionTokens", this.completionTokens);
        jsonWriter.writeNumberField("totalTokens", this.totalTokens);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemTaskAiAssistanceResponse from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemTaskAiAssistanceResponse if the JsonReader was pointing to an instance of it,
     * or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemTaskAiAssistanceResponse.
     */
    @Generated
    public static ProcessItemTaskAiAssistanceResponse fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemTaskAiAssistanceResponse deserializedProcessItemTaskAiAssistanceResponse = new ProcessItemTaskAiAssistanceResponse();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processItem".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceResponse.processItem = ProcessItem.fromJson(reader);
                } else if ("model".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceResponse.model = reader.getString();
                } else if ("finishReason".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceResponse.finishReason = reader.getString();
                } else if ("promptTokens".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceResponse.promptTokens = reader.getNullable(JsonReader::getInt);
                } else if ("completionTokens".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceResponse.completionTokens = reader.getNullable(JsonReader::getInt);
                } else if ("totalTokens".equals(fieldName)) {
                    deserializedProcessItemTaskAiAssistanceResponse.totalTokens = reader.getNullable(JsonReader::getInt);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemTaskAiAssistanceResponse;
        });
    }
}
