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
import java.util.Objects;
import java.util.UUID;

/**
 * The WebhookEventTaskStateChangedData model.
 */
@Fluent
public final class WebhookEventTaskStateChangedData implements JsonSerializable<WebhookEventTaskStateChangedData> {

    /*
     * The processId property.
     */
    private UUID processId;

    /*
     * The taskId property.
     */
    private UUID taskId;

    /*
     * The taskCode property.
     */
    private String taskCode;

    /*
     * Task state
     */
    private TaskState taskState;

    /**
     * Creates an instance of WebhookEventTaskStateChangedData class.
     */
    public WebhookEventTaskStateChangedData() {}

    /**
     * Get the processId property: The processId property.
     *
     * @return the processId value.
     */
    public UUID getProcessId() {
        return this.processId;
    }

    /**
     * Set the processId property: The processId property.
     *
     * @param processId the processId value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the taskId property: The taskId property.
     *
     * @return the taskId value.
     */
    public UUID getTaskId() {
        return this.taskId;
    }

    /**
     * Set the taskId property: The taskId property.
     *
     * @param taskId the taskId value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setTaskId(UUID taskId) {
        this.taskId = taskId;
        return this;
    }

    /**
     * Get the taskCode property: The taskCode property.
     *
     * @return the taskCode value.
     */
    public String getTaskCode() {
        return this.taskCode;
    }

    /**
     * Set the taskCode property: The taskCode property.
     *
     * @param taskCode the taskCode value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setTaskCode(String taskCode) {
        this.taskCode = taskCode;
        return this;
    }

    /**
     * Get the taskState property: Task state.
     *
     * @return the taskState value.
     */
    public TaskState getTaskState() {
        return this.taskState;
    }

    /**
     * Set the taskState property: Task state.
     *
     * @param taskState the taskState value to set.
     * @return the WebhookEventTaskStateChangedData object itself.
     */
    public WebhookEventTaskStateChangedData setTaskState(TaskState taskState) {
        this.taskState = taskState;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processId", Objects.toString(this.processId, null));
        jsonWriter.writeStringField("taskId", Objects.toString(this.taskId, null));
        jsonWriter.writeStringField("taskCode", this.taskCode);
        jsonWriter.writeStringField("taskState", this.taskState == null ? null : this.taskState.toString());
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WebhookEventTaskStateChangedData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WebhookEventTaskStateChangedData if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WebhookEventTaskStateChangedData.
     */
    public static WebhookEventTaskStateChangedData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WebhookEventTaskStateChangedData deserializedWebhookEventTaskStateChangedData = new WebhookEventTaskStateChangedData();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processId".equals(fieldName)) {
                    deserializedWebhookEventTaskStateChangedData.processId = reader.getNullable(
                        nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else if ("taskId".equals(fieldName)) {
                    deserializedWebhookEventTaskStateChangedData.taskId = reader.getNullable(
                        nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else if ("taskCode".equals(fieldName)) {
                    deserializedWebhookEventTaskStateChangedData.taskCode = reader.getString();
                } else if ("taskState".equals(fieldName)) {
                    deserializedWebhookEventTaskStateChangedData.taskState = TaskState.fromString(reader.getString());
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWebhookEventTaskStateChangedData;
        });
    }
}
