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
import com.azure.core.util.CoreUtils;
import com.azure.json.JsonReader;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.UUID;

/**
 * A Process action invocation. The populated sub-field (`downloadable`,
 * `startRelatedProcess`, `createTask`, `createProcessItemMessage`,
 * `createProcessItemThread`, `sendWorkflowSignal`) is dictated by `type`.
 * Actions of type START_WORKFLOW carry no additional details.
 */
@Fluent
public final class ProcessAction extends AbstractAudited {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * Type of a Process action.
     */
    @Generated
    private ProcessActionType type;

    /*
     * Status of a Process action.
     */
    @Generated
    private ProcessActionStatus status;

    /*
     * The processActionDefinitionRef property.
     */
    @Generated
    private ProcessActionDefinitionRef processActionDefinitionRef;

    /*
     * Action details for actions of type DOWNLOADABLE.
     */
    @Generated
    private ProcessActionDownloadable downloadable;

    /*
     * Action details for actions of type START_RELATED_PROCESS. Actions of
     * this type complete synchronously, so all fields are present in the
     * invoke response.
     */
    @Generated
    private ProcessActionStartRelatedProcess startRelatedProcess;

    /*
     * Action details for actions of type CREATE_TASK. Actions of this type
     * complete synchronously, so the created Process Item ID is present in
     * the invoke response.
     */
    @Generated
    private ProcessActionCreateTask createTask;

    /*
     * Action details for actions of type CREATE_PROCESS_ITEM_MESSAGE. Actions
     * of this type complete synchronously, so the created Process Item ID is
     * present in the invoke response.
     */
    @Generated
    private ProcessActionCreateProcessItemMessage createProcessItemMessage;

    /*
     * Action details for actions of type CREATE_PROCESS_ITEM_THREAD. Actions
     * of this type complete synchronously, so the created Process Item ID is
     * present in the invoke response.
     */
    @Generated
    private ProcessActionCreateProcessItemThread createProcessItemThread;

    /*
     * Action details for actions of type SEND_WORKFLOW_SIGNAL.
     */
    @Generated
    private ProcessActionSendWorkflowSignal sendWorkflowSignal;

    /**
     * Creates an instance of ProcessAction class.
     */
    @Generated
    public ProcessAction() {}

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
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: Type of a Process action.
     *
     * @return the type value.
     */
    @Generated
    public ProcessActionType getType() {
        return this.type;
    }

    /**
     * Set the type property: Type of a Process action.
     *
     * @param type the type value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setType(ProcessActionType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the status property: Status of a Process action.
     *
     * @return the status value.
     */
    @Generated
    public ProcessActionStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Status of a Process action.
     *
     * @param status the status value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setStatus(ProcessActionStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the processActionDefinitionRef property: The processActionDefinitionRef property.
     *
     * @return the processActionDefinitionRef value.
     */
    @Generated
    public ProcessActionDefinitionRef getProcessActionDefinitionRef() {
        return this.processActionDefinitionRef;
    }

    /**
     * Set the processActionDefinitionRef property: The processActionDefinitionRef property.
     *
     * @param processActionDefinitionRef the processActionDefinitionRef value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setProcessActionDefinitionRef(ProcessActionDefinitionRef processActionDefinitionRef) {
        this.processActionDefinitionRef = processActionDefinitionRef;
        return this;
    }

    /**
     * Get the downloadable property: Action details for actions of type DOWNLOADABLE.
     *
     * @return the downloadable value.
     */
    @Generated
    public ProcessActionDownloadable getDownloadable() {
        return this.downloadable;
    }

    /**
     * Set the downloadable property: Action details for actions of type DOWNLOADABLE.
     *
     * @param downloadable the downloadable value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setDownloadable(ProcessActionDownloadable downloadable) {
        this.downloadable = downloadable;
        return this;
    }

    /**
     * Get the startRelatedProcess property: Action details for actions of type START_RELATED_PROCESS. Actions of
     * this type complete synchronously, so all fields are present in the
     * invoke response.
     *
     * @return the startRelatedProcess value.
     */
    @Generated
    public ProcessActionStartRelatedProcess getStartRelatedProcess() {
        return this.startRelatedProcess;
    }

    /**
     * Set the startRelatedProcess property: Action details for actions of type START_RELATED_PROCESS. Actions of
     * this type complete synchronously, so all fields are present in the
     * invoke response.
     *
     * @param startRelatedProcess the startRelatedProcess value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setStartRelatedProcess(ProcessActionStartRelatedProcess startRelatedProcess) {
        this.startRelatedProcess = startRelatedProcess;
        return this;
    }

    /**
     * Get the createTask property: Action details for actions of type CREATE_TASK. Actions of this type
     * complete synchronously, so the created Process Item ID is present in
     * the invoke response.
     *
     * @return the createTask value.
     */
    @Generated
    public ProcessActionCreateTask getCreateTask() {
        return this.createTask;
    }

    /**
     * Set the createTask property: Action details for actions of type CREATE_TASK. Actions of this type
     * complete synchronously, so the created Process Item ID is present in
     * the invoke response.
     *
     * @param createTask the createTask value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setCreateTask(ProcessActionCreateTask createTask) {
        this.createTask = createTask;
        return this;
    }

    /**
     * Get the createProcessItemMessage property: Action details for actions of type CREATE_PROCESS_ITEM_MESSAGE.
     * Actions
     * of this type complete synchronously, so the created Process Item ID is
     * present in the invoke response.
     *
     * @return the createProcessItemMessage value.
     */
    @Generated
    public ProcessActionCreateProcessItemMessage getCreateProcessItemMessage() {
        return this.createProcessItemMessage;
    }

    /**
     * Set the createProcessItemMessage property: Action details for actions of type CREATE_PROCESS_ITEM_MESSAGE.
     * Actions
     * of this type complete synchronously, so the created Process Item ID is
     * present in the invoke response.
     *
     * @param createProcessItemMessage the createProcessItemMessage value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setCreateProcessItemMessage(ProcessActionCreateProcessItemMessage createProcessItemMessage) {
        this.createProcessItemMessage = createProcessItemMessage;
        return this;
    }

    /**
     * Get the createProcessItemThread property: Action details for actions of type CREATE_PROCESS_ITEM_THREAD. Actions
     * of this type complete synchronously, so the created Process Item ID is
     * present in the invoke response.
     *
     * @return the createProcessItemThread value.
     */
    @Generated
    public ProcessActionCreateProcessItemThread getCreateProcessItemThread() {
        return this.createProcessItemThread;
    }

    /**
     * Set the createProcessItemThread property: Action details for actions of type CREATE_PROCESS_ITEM_THREAD. Actions
     * of this type complete synchronously, so the created Process Item ID is
     * present in the invoke response.
     *
     * @param createProcessItemThread the createProcessItemThread value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setCreateProcessItemThread(ProcessActionCreateProcessItemThread createProcessItemThread) {
        this.createProcessItemThread = createProcessItemThread;
        return this;
    }

    /**
     * Get the sendWorkflowSignal property: Action details for actions of type SEND_WORKFLOW_SIGNAL.
     *
     * @return the sendWorkflowSignal value.
     */
    @Generated
    public ProcessActionSendWorkflowSignal getSendWorkflowSignal() {
        return this.sendWorkflowSignal;
    }

    /**
     * Set the sendWorkflowSignal property: Action details for actions of type SEND_WORKFLOW_SIGNAL.
     *
     * @param sendWorkflowSignal the sendWorkflowSignal value to set.
     * @return the ProcessAction object itself.
     */
    @Generated
    public ProcessAction setSendWorkflowSignal(ProcessActionSendWorkflowSignal sendWorkflowSignal) {
        this.sendWorkflowSignal = sendWorkflowSignal;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessAction setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessAction setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessAction setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessAction setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("createdBy", Objects.toString(getCreatedBy(), null));
        jsonWriter.writeStringField(
            "createdAt",
            getCreatedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getCreatedAt())
        );
        jsonWriter.writeStringField("lastModifiedBy", Objects.toString(getLastModifiedBy(), null));
        jsonWriter.writeStringField(
            "lastModifiedAt",
            getLastModifiedAt() == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(getLastModifiedAt())
        );
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("status", this.status == null ? null : this.status.toString());
        jsonWriter.writeJsonField("processActionDefinitionRef", this.processActionDefinitionRef);
        jsonWriter.writeJsonField("downloadable", this.downloadable);
        jsonWriter.writeJsonField("startRelatedProcess", this.startRelatedProcess);
        jsonWriter.writeJsonField("createTask", this.createTask);
        jsonWriter.writeJsonField("createProcessItemMessage", this.createProcessItemMessage);
        jsonWriter.writeJsonField("createProcessItemThread", this.createProcessItemThread);
        jsonWriter.writeJsonField("sendWorkflowSignal", this.sendWorkflowSignal);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessAction from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessAction if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessAction.
     */
    @Generated
    public static ProcessAction fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessAction deserializedProcessAction = new ProcessAction();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedProcessAction.setCreatedBy(reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())));
                } else if ("createdAt".equals(fieldName)) {
                    deserializedProcessAction.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedProcessAction.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedProcessAction.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedProcessAction.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("type".equals(fieldName)) {
                    deserializedProcessAction.type = ProcessActionType.fromString(reader.getString());
                } else if ("status".equals(fieldName)) {
                    deserializedProcessAction.status = ProcessActionStatus.fromString(reader.getString());
                } else if ("processActionDefinitionRef".equals(fieldName)) {
                    deserializedProcessAction.processActionDefinitionRef = ProcessActionDefinitionRef.fromJson(reader);
                } else if ("downloadable".equals(fieldName)) {
                    deserializedProcessAction.downloadable = ProcessActionDownloadable.fromJson(reader);
                } else if ("startRelatedProcess".equals(fieldName)) {
                    deserializedProcessAction.startRelatedProcess = ProcessActionStartRelatedProcess.fromJson(reader);
                } else if ("createTask".equals(fieldName)) {
                    deserializedProcessAction.createTask = ProcessActionCreateTask.fromJson(reader);
                } else if ("createProcessItemMessage".equals(fieldName)) {
                    deserializedProcessAction.createProcessItemMessage = ProcessActionCreateProcessItemMessage.fromJson(reader);
                } else if ("createProcessItemThread".equals(fieldName)) {
                    deserializedProcessAction.createProcessItemThread = ProcessActionCreateProcessItemThread.fromJson(reader);
                } else if ("sendWorkflowSignal".equals(fieldName)) {
                    deserializedProcessAction.sendWorkflowSignal = ProcessActionSendWorkflowSignal.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessAction;
        });
    }
}
