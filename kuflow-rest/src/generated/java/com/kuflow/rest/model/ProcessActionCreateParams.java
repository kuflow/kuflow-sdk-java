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
 * Params to invoke an action on a Process. The populated sub-field
 * (`createTask`, `createProcessItemMessage`, `startRelatedProcess`) must
 * match the type of the action identified by
 * `processActionDefinitionCode`; the remaining action types take no
 * params.
 */
@Fluent
public final class ProcessActionCreateParams implements JsonSerializable<ProcessActionCreateParams> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The processActionDefinitionCode property.
     */
    @Generated
    private String processActionDefinitionCode;

    /*
     * Params for invoking an action of type CREATE_TASK.
     */
    @Generated
    private ProcessActionCreateParamsCreateTask createTask;

    /*
     * Params for invoking an action of type CREATE_PROCESS_ITEM_MESSAGE.
     * Messages are created through the built-in `__KF_MESSAGE__` action
     * definition code.
     */
    @Generated
    private ProcessActionCreateParamsCreateProcessItemMessage createProcessItemMessage;

    /*
     * Params for invoking an action of type START_RELATED_PROCESS.
     */
    @Generated
    private ProcessActionCreateParamsStartRelatedProcess startRelatedProcess;

    /**
     * Creates an instance of ProcessActionCreateParams class.
     */
    @Generated
    public ProcessActionCreateParams() {}

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
     * @return the ProcessActionCreateParams object itself.
     */
    @Generated
    public ProcessActionCreateParams setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the processActionDefinitionCode property: The processActionDefinitionCode property.
     *
     * @return the processActionDefinitionCode value.
     */
    @Generated
    public String getProcessActionDefinitionCode() {
        return this.processActionDefinitionCode;
    }

    /**
     * Set the processActionDefinitionCode property: The processActionDefinitionCode property.
     *
     * @param processActionDefinitionCode the processActionDefinitionCode value to set.
     * @return the ProcessActionCreateParams object itself.
     */
    @Generated
    public ProcessActionCreateParams setProcessActionDefinitionCode(String processActionDefinitionCode) {
        this.processActionDefinitionCode = processActionDefinitionCode;
        return this;
    }

    /**
     * Get the createTask property: Params for invoking an action of type CREATE_TASK.
     *
     * @return the createTask value.
     */
    @Generated
    public ProcessActionCreateParamsCreateTask getCreateTask() {
        return this.createTask;
    }

    /**
     * Set the createTask property: Params for invoking an action of type CREATE_TASK.
     *
     * @param createTask the createTask value to set.
     * @return the ProcessActionCreateParams object itself.
     */
    @Generated
    public ProcessActionCreateParams setCreateTask(ProcessActionCreateParamsCreateTask createTask) {
        this.createTask = createTask;
        return this;
    }

    /**
     * Get the createProcessItemMessage property: Params for invoking an action of type CREATE_PROCESS_ITEM_MESSAGE.
     * Messages are created through the built-in `__KF_MESSAGE__` action
     * definition code.
     *
     * @return the createProcessItemMessage value.
     */
    @Generated
    public ProcessActionCreateParamsCreateProcessItemMessage getCreateProcessItemMessage() {
        return this.createProcessItemMessage;
    }

    /**
     * Set the createProcessItemMessage property: Params for invoking an action of type CREATE_PROCESS_ITEM_MESSAGE.
     * Messages are created through the built-in `__KF_MESSAGE__` action
     * definition code.
     *
     * @param createProcessItemMessage the createProcessItemMessage value to set.
     * @return the ProcessActionCreateParams object itself.
     */
    @Generated
    public ProcessActionCreateParams setCreateProcessItemMessage(
        ProcessActionCreateParamsCreateProcessItemMessage createProcessItemMessage
    ) {
        this.createProcessItemMessage = createProcessItemMessage;
        return this;
    }

    /**
     * Get the startRelatedProcess property: Params for invoking an action of type START_RELATED_PROCESS.
     *
     * @return the startRelatedProcess value.
     */
    @Generated
    public ProcessActionCreateParamsStartRelatedProcess getStartRelatedProcess() {
        return this.startRelatedProcess;
    }

    /**
     * Set the startRelatedProcess property: Params for invoking an action of type START_RELATED_PROCESS.
     *
     * @param startRelatedProcess the startRelatedProcess value to set.
     * @return the ProcessActionCreateParams object itself.
     */
    @Generated
    public ProcessActionCreateParams setStartRelatedProcess(ProcessActionCreateParamsStartRelatedProcess startRelatedProcess) {
        this.startRelatedProcess = startRelatedProcess;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("processActionDefinitionCode", this.processActionDefinitionCode);
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeJsonField("createTask", this.createTask);
        jsonWriter.writeJsonField("createProcessItemMessage", this.createProcessItemMessage);
        jsonWriter.writeJsonField("startRelatedProcess", this.startRelatedProcess);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessActionCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessActionCreateParams if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessActionCreateParams.
     */
    @Generated
    public static ProcessActionCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessActionCreateParams deserializedProcessActionCreateParams = new ProcessActionCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("processActionDefinitionCode".equals(fieldName)) {
                    deserializedProcessActionCreateParams.processActionDefinitionCode = reader.getString();
                } else if ("id".equals(fieldName)) {
                    deserializedProcessActionCreateParams.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("createTask".equals(fieldName)) {
                    deserializedProcessActionCreateParams.createTask = ProcessActionCreateParamsCreateTask.fromJson(reader);
                } else if ("createProcessItemMessage".equals(fieldName)) {
                    deserializedProcessActionCreateParams.createProcessItemMessage =
                        ProcessActionCreateParamsCreateProcessItemMessage.fromJson(reader);
                } else if ("startRelatedProcess".equals(fieldName)) {
                    deserializedProcessActionCreateParams.startRelatedProcess = ProcessActionCreateParamsStartRelatedProcess.fromJson(
                        reader
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessActionCreateParams;
        });
    }
}
