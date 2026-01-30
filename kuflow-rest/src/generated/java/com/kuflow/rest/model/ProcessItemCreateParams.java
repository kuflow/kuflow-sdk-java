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
 * The ProcessItemCreateParams model.
 */
@Fluent
public final class ProcessItemCreateParams implements JsonSerializable<ProcessItemCreateParams> {

    /*
     * The id property.
     */
    private UUID id;

    /*
     * Process Item Type
     */
    private ProcessItemType type;

    /*
     * The processId property.
     */
    private UUID processId;

    /*
     * The ownerId property.
     */
    private UUID ownerId;

    /*
     * The ownerEmail property.
     */
    private String ownerEmail;

    /*
     * The processItemDefinitionCode property.
     */
    private String processItemDefinitionCode;

    /*
     * The task property.
     */
    private ProcessItemTaskCreateParams task;

    /*
     * The message property.
     */
    private ProcessItemMessageCreateParams message;

    /**
     * Creates an instance of ProcessItemCreateParams class.
     */
    public ProcessItemCreateParams() {}

    /**
     * Get the id property: The id property.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: The id property.
     *
     * @param id the id value to set.
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: Process Item Type.
     *
     * @return the type value.
     */
    public ProcessItemType getType() {
        return this.type;
    }

    /**
     * Set the type property: Process Item Type.
     *
     * @param type the type value to set.
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setType(ProcessItemType type) {
        this.type = type;
        return this;
    }

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
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setProcessId(UUID processId) {
        this.processId = processId;
        return this;
    }

    /**
     * Get the ownerId property: The ownerId property.
     *
     * @return the ownerId value.
     */
    public UUID getOwnerId() {
        return this.ownerId;
    }

    /**
     * Set the ownerId property: The ownerId property.
     *
     * @param ownerId the ownerId value to set.
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setOwnerId(UUID ownerId) {
        this.ownerId = ownerId;
        return this;
    }

    /**
     * Get the ownerEmail property: The ownerEmail property.
     *
     * @return the ownerEmail value.
     */
    public String getOwnerEmail() {
        return this.ownerEmail;
    }

    /**
     * Set the ownerEmail property: The ownerEmail property.
     *
     * @param ownerEmail the ownerEmail value to set.
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setOwnerEmail(String ownerEmail) {
        this.ownerEmail = ownerEmail;
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
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setProcessItemDefinitionCode(String processItemDefinitionCode) {
        this.processItemDefinitionCode = processItemDefinitionCode;
        return this;
    }

    /**
     * Get the task property: The task property.
     *
     * @return the task value.
     */
    public ProcessItemTaskCreateParams getTask() {
        return this.task;
    }

    /**
     * Set the task property: The task property.
     *
     * @param task the task value to set.
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setTask(ProcessItemTaskCreateParams task) {
        this.task = task;
        return this;
    }

    /**
     * Get the message property: The message property.
     *
     * @return the message value.
     */
    public ProcessItemMessageCreateParams getMessage() {
        return this.message;
    }

    /**
     * Set the message property: The message property.
     *
     * @param message the message value to set.
     * @return the ProcessItemCreateParams object itself.
     */
    public ProcessItemCreateParams setMessage(ProcessItemMessageCreateParams message) {
        this.message = message;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("processId", Objects.toString(this.processId, null));
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("ownerId", Objects.toString(this.ownerId, null));
        jsonWriter.writeStringField("ownerEmail", this.ownerEmail);
        jsonWriter.writeStringField("processItemDefinitionCode", this.processItemDefinitionCode);
        jsonWriter.writeJsonField("task", this.task);
        jsonWriter.writeJsonField("message", this.message);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessItemCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessItemCreateParams if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessItemCreateParams.
     */
    public static ProcessItemCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessItemCreateParams deserializedProcessItemCreateParams = new ProcessItemCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("type".equals(fieldName)) {
                    deserializedProcessItemCreateParams.type = ProcessItemType.fromString(reader.getString());
                } else if ("processId".equals(fieldName)) {
                    deserializedProcessItemCreateParams.processId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedProcessItemCreateParams.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("ownerId".equals(fieldName)) {
                    deserializedProcessItemCreateParams.ownerId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("ownerEmail".equals(fieldName)) {
                    deserializedProcessItemCreateParams.ownerEmail = reader.getString();
                } else if ("processItemDefinitionCode".equals(fieldName)) {
                    deserializedProcessItemCreateParams.processItemDefinitionCode = reader.getString();
                } else if ("task".equals(fieldName)) {
                    deserializedProcessItemCreateParams.task = ProcessItemTaskCreateParams.fromJson(reader);
                } else if ("message".equals(fieldName)) {
                    deserializedProcessItemCreateParams.message = ProcessItemMessageCreateParams.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessItemCreateParams;
        });
    }
}
