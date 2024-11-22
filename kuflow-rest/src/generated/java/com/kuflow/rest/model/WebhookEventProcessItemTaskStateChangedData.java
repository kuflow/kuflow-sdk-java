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
 * The WebhookEventProcessItemTaskStateChangedData model.
 */
@Fluent
public final class WebhookEventProcessItemTaskStateChangedData implements JsonSerializable<WebhookEventProcessItemTaskStateChangedData> {

    /*
     * The processId property.
     */
    private UUID processId;

    /*
     * The processItemId property.
     */
    private UUID processItemId;

    /*
     * Process Item Type
     */
    private ProcessItemType processItemType;

    /*
     * Process Item Task state
     */
    private ProcessItemTaskState processItemState;

    /*
     * The processItemDefinitionCode property.
     */
    private String processItemDefinitionCode;

    /**
     * Creates an instance of WebhookEventProcessItemTaskStateChangedData class.
     */
    public WebhookEventProcessItemTaskStateChangedData() {}

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
     * @return the WebhookEventProcessItemTaskStateChangedData object itself.
     */
    public WebhookEventProcessItemTaskStateChangedData setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the processItemId property: The processItemId property.
     *
     * @return the processItemId value.
     */
    public UUID getProcessItemId() {
        return this.processItemId;
    }

    /**
     * Set the processItemId property: The processItemId property.
     *
     * @param processItemId the processItemId value to set.
     * @return the WebhookEventProcessItemTaskStateChangedData object itself.
     */
    public WebhookEventProcessItemTaskStateChangedData setProcessItemId(UUID processItemId) {
        this.processItemId = processItemId;
        return this;
    }

    /**
     * Get the processItemType property: Process Item Type.
     *
     * @return the processItemType value.
     */
    public ProcessItemType getProcessItemType() {
        return this.processItemType;
    }

    /**
     * Set the processItemType property: Process Item Type.
     *
     * @param processItemType the processItemType value to set.
     * @return the WebhookEventProcessItemTaskStateChangedData object itself.
     */
    public WebhookEventProcessItemTaskStateChangedData setProcessItemType(ProcessItemType processItemType) {
        this.processItemType = processItemType;
        return this;
    }

    /**
     * Get the processItemState property: Process Item Task state.
     *
     * @return the processItemState value.
     */
    public ProcessItemTaskState getProcessItemState() {
        return this.processItemState;
    }

    /**
     * Set the processItemState property: Process Item Task state.
     *
     * @param processItemState the processItemState value to set.
     * @return the WebhookEventProcessItemTaskStateChangedData object itself.
     */
    public WebhookEventProcessItemTaskStateChangedData setProcessItemState(ProcessItemTaskState processItemState) {
        this.processItemState = processItemState;
        return this;
    }

    /**
     * Get the processItemDefinitionCode property: The processItemDefinitionCode property.
     *
     * @return the processItemDefinitionCode value.
     */
    public String getProcessItemDefinitionCode() {
        return this.processItemDefinitionCode;
    }

    /**
     * Set the processItemDefinitionCode property: The processItemDefinitionCode property.
     *
     * @param processItemDefinitionCode the processItemDefinitionCode value to set.
     * @return the WebhookEventProcessItemTaskStateChangedData object itself.
     */
    public WebhookEventProcessItemTaskStateChangedData setProcessItemDefinitionCode(String processItemDefinitionCode) {
        this.processItemDefinitionCode = processItemDefinitionCode;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processId", Objects.toString(this.processId, null));
        jsonWriter.writeStringField("processItemId", Objects.toString(this.processItemId, null));
        jsonWriter.writeStringField("processItemType", this.processItemType == null ? null : this.processItemType.toString());
        jsonWriter.writeStringField("processItemState", this.processItemState == null ? null : this.processItemState.toString());
        jsonWriter.writeStringField("processItemDefinitionCode", this.processItemDefinitionCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WebhookEventProcessItemTaskStateChangedData from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WebhookEventProcessItemTaskStateChangedData if the JsonReader was pointing to an instance
     * of it, or null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WebhookEventProcessItemTaskStateChangedData.
     */
    public static WebhookEventProcessItemTaskStateChangedData fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WebhookEventProcessItemTaskStateChangedData deserializedWebhookEventProcessItemTaskStateChangedData =
                new WebhookEventProcessItemTaskStateChangedData();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processId".equals(fieldName)) {
                    deserializedWebhookEventProcessItemTaskStateChangedData.processId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("processItemId".equals(fieldName)) {
                    deserializedWebhookEventProcessItemTaskStateChangedData.processItemId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("processItemType".equals(fieldName)) {
                    deserializedWebhookEventProcessItemTaskStateChangedData.processItemType = ProcessItemType.fromString(
                        reader.getString()
                    );
                } else if ("processItemState".equals(fieldName)) {
                    deserializedWebhookEventProcessItemTaskStateChangedData.processItemState = ProcessItemTaskState.fromString(
                        reader.getString()
                    );
                } else if ("processItemDefinitionCode".equals(fieldName)) {
                    deserializedWebhookEventProcessItemTaskStateChangedData.processItemDefinitionCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWebhookEventProcessItemTaskStateChangedData;
        });
    }
}
