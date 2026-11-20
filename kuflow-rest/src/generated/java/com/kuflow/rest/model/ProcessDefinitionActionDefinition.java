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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * An action that the users of a process can run. The populated sub-field (`downloadable`, `startWorkflow`,
 * `startRelatedProcess`, `createTask`, `createProcessItemThread`, `sendWorkflowSignal`) is dictated by `type`.
 * Actions of type CREATE_PROCESS_ITEM_MESSAGE carry no additional details.
 */
@Fluent
public final class ProcessDefinitionActionDefinition implements JsonSerializable<ProcessDefinitionActionDefinition> {

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
     * The code property.
     */
    @Generated
    private String code;

    /*
     * The name property.
     */
    @Generated
    private String name;

    /*
     * The executeAtStartup property.
     */
    @Generated
    private Boolean executeAtStartup;

    /*
     * The requireConfirmation property.
     */
    @Generated
    private Boolean requireConfirmation;

    /*
     * The confirmationDescription property.
     */
    @Generated
    private String confirmationDescription;

    /*
     * A jq expression over the process that says when the action is available.
     */
    @Generated
    private String availabilityExpression;

    /*
     * The icon property.
     */
    @Generated
    private String icon;

    /*
     * The roles that can run it.
     */
    @Generated
    private List<String> roles;

    /*
     * The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
     */
    @Generated
    private ProcessDefinitionActionDefinitionWorkflow downloadable;

    /*
     * The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
     */
    @Generated
    private ProcessDefinitionActionDefinitionWorkflow startWorkflow;

    /*
     * The startRelatedProcess property.
     */
    @Generated
    private ProcessDefinitionActionDefinitionStartRelatedProcess startRelatedProcess;

    /*
     * The task or thread that the action creates, by its code. When the version does not have it, by its id: KuFlow
     * does not offer the action in the processes of that version. Actions belong to the process definition, and
     * tasks and threads to each version.
     */
    @Generated
    private ProcessDefinitionActionDefinitionProcessItem createTask;

    /*
     * The task or thread that the action creates, by its code. When the version does not have it, by its id: KuFlow
     * does not offer the action in the processes of that version. Actions belong to the process definition, and
     * tasks and threads to each version.
     */
    @Generated
    private ProcessDefinitionActionDefinitionProcessItem createProcessItemThread;

    /*
     * The sendWorkflowSignal property.
     */
    @Generated
    private ProcessDefinitionActionDefinitionSendWorkflowSignal sendWorkflowSignal;

    /**
     * Creates an instance of ProcessDefinitionActionDefinition class.
     */
    @Generated
    public ProcessDefinitionActionDefinition() {}

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
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setId(UUID id) {
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
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setType(ProcessActionType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    @Generated
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    @Generated
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the executeAtStartup property: The executeAtStartup property.
     *
     * @return the executeAtStartup value.
     */
    @Generated
    public Boolean isExecuteAtStartup() {
        return this.executeAtStartup;
    }

    /**
     * Set the executeAtStartup property: The executeAtStartup property.
     *
     * @param executeAtStartup the executeAtStartup value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setExecuteAtStartup(Boolean executeAtStartup) {
        this.executeAtStartup = executeAtStartup;
        return this;
    }

    /**
     * Get the requireConfirmation property: The requireConfirmation property.
     *
     * @return the requireConfirmation value.
     */
    @Generated
    public Boolean isRequireConfirmation() {
        return this.requireConfirmation;
    }

    /**
     * Set the requireConfirmation property: The requireConfirmation property.
     *
     * @param requireConfirmation the requireConfirmation value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setRequireConfirmation(Boolean requireConfirmation) {
        this.requireConfirmation = requireConfirmation;
        return this;
    }

    /**
     * Get the confirmationDescription property: The confirmationDescription property.
     *
     * @return the confirmationDescription value.
     */
    @Generated
    public String getConfirmationDescription() {
        return this.confirmationDescription;
    }

    /**
     * Set the confirmationDescription property: The confirmationDescription property.
     *
     * @param confirmationDescription the confirmationDescription value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setConfirmationDescription(String confirmationDescription) {
        this.confirmationDescription = confirmationDescription;
        return this;
    }

    /**
     * Get the availabilityExpression property: A jq expression over the process that says when the action is available.
     *
     * @return the availabilityExpression value.
     */
    @Generated
    public String getAvailabilityExpression() {
        return this.availabilityExpression;
    }

    /**
     * Set the availabilityExpression property: A jq expression over the process that says when the action is available.
     *
     * @param availabilityExpression the availabilityExpression value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setAvailabilityExpression(String availabilityExpression) {
        this.availabilityExpression = availabilityExpression;
        return this;
    }

    /**
     * Get the icon property: The icon property.
     *
     * @return the icon value.
     */
    @Generated
    public String getIcon() {
        return this.icon;
    }

    /**
     * Set the icon property: The icon property.
     *
     * @param icon the icon value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setIcon(String icon) {
        this.icon = icon;
        return this;
    }

    /**
     * Get the roles property: The roles that can run it.
     *
     * @return the roles value.
     */
    @Generated
    public List<String> getRoles() {
        return this.roles;
    }

    /**
     * Set the roles property: The roles that can run it.
     *
     * @param roles the roles value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setRoles(List<String> roles) {
        this.roles = roles;
        return this;
    }

    /**
     * Get the downloadable property: The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
     *
     * @return the downloadable value.
     */
    @Generated
    public ProcessDefinitionActionDefinitionWorkflow getDownloadable() {
        return this.downloadable;
    }

    /**
     * Set the downloadable property: The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
     *
     * @param downloadable the downloadable value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setDownloadable(ProcessDefinitionActionDefinitionWorkflow downloadable) {
        this.downloadable = downloadable;
        return this;
    }

    /**
     * Get the startWorkflow property: The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
     *
     * @return the startWorkflow value.
     */
    @Generated
    public ProcessDefinitionActionDefinitionWorkflow getStartWorkflow() {
        return this.startWorkflow;
    }

    /**
     * Set the startWorkflow property: The workflow that an action of type DOWNLOADABLE or START_WORKFLOW starts.
     *
     * @param startWorkflow the startWorkflow value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setStartWorkflow(ProcessDefinitionActionDefinitionWorkflow startWorkflow) {
        this.startWorkflow = startWorkflow;
        return this;
    }

    /**
     * Get the startRelatedProcess property: The startRelatedProcess property.
     *
     * @return the startRelatedProcess value.
     */
    @Generated
    public ProcessDefinitionActionDefinitionStartRelatedProcess getStartRelatedProcess() {
        return this.startRelatedProcess;
    }

    /**
     * Set the startRelatedProcess property: The startRelatedProcess property.
     *
     * @param startRelatedProcess the startRelatedProcess value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setStartRelatedProcess(
        ProcessDefinitionActionDefinitionStartRelatedProcess startRelatedProcess
    ) {
        this.startRelatedProcess = startRelatedProcess;
        return this;
    }

    /**
     * Get the createTask property: The task or thread that the action creates, by its code. When the version does not
     * have it, by its id: KuFlow
     * does not offer the action in the processes of that version. Actions belong to the process definition, and
     * tasks and threads to each version.
     *
     * @return the createTask value.
     */
    @Generated
    public ProcessDefinitionActionDefinitionProcessItem getCreateTask() {
        return this.createTask;
    }

    /**
     * Set the createTask property: The task or thread that the action creates, by its code. When the version does not
     * have it, by its id: KuFlow
     * does not offer the action in the processes of that version. Actions belong to the process definition, and
     * tasks and threads to each version.
     *
     * @param createTask the createTask value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setCreateTask(ProcessDefinitionActionDefinitionProcessItem createTask) {
        this.createTask = createTask;
        return this;
    }

    /**
     * Get the createProcessItemThread property: The task or thread that the action creates, by its code. When the
     * version does not have it, by its id: KuFlow
     * does not offer the action in the processes of that version. Actions belong to the process definition, and
     * tasks and threads to each version.
     *
     * @return the createProcessItemThread value.
     */
    @Generated
    public ProcessDefinitionActionDefinitionProcessItem getCreateProcessItemThread() {
        return this.createProcessItemThread;
    }

    /**
     * Set the createProcessItemThread property: The task or thread that the action creates, by its code. When the
     * version does not have it, by its id: KuFlow
     * does not offer the action in the processes of that version. Actions belong to the process definition, and
     * tasks and threads to each version.
     *
     * @param createProcessItemThread the createProcessItemThread value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setCreateProcessItemThread(
        ProcessDefinitionActionDefinitionProcessItem createProcessItemThread
    ) {
        this.createProcessItemThread = createProcessItemThread;
        return this;
    }

    /**
     * Get the sendWorkflowSignal property: The sendWorkflowSignal property.
     *
     * @return the sendWorkflowSignal value.
     */
    @Generated
    public ProcessDefinitionActionDefinitionSendWorkflowSignal getSendWorkflowSignal() {
        return this.sendWorkflowSignal;
    }

    /**
     * Set the sendWorkflowSignal property: The sendWorkflowSignal property.
     *
     * @param sendWorkflowSignal the sendWorkflowSignal value to set.
     * @return the ProcessDefinitionActionDefinition object itself.
     */
    @Generated
    public ProcessDefinitionActionDefinition setSendWorkflowSignal(ProcessDefinitionActionDefinitionSendWorkflowSignal sendWorkflowSignal) {
        this.sendWorkflowSignal = sendWorkflowSignal;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeStringField("type", this.type == null ? null : this.type.toString());
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeArrayField("roles", this.roles, (writer, element) -> writer.writeString(element));
        jsonWriter.writeBooleanField("executeAtStartup", this.executeAtStartup);
        jsonWriter.writeBooleanField("requireConfirmation", this.requireConfirmation);
        jsonWriter.writeStringField("confirmationDescription", this.confirmationDescription);
        jsonWriter.writeStringField("availabilityExpression", this.availabilityExpression);
        jsonWriter.writeStringField("icon", this.icon);
        jsonWriter.writeJsonField("downloadable", this.downloadable);
        jsonWriter.writeJsonField("startWorkflow", this.startWorkflow);
        jsonWriter.writeJsonField("startRelatedProcess", this.startRelatedProcess);
        jsonWriter.writeJsonField("createTask", this.createTask);
        jsonWriter.writeJsonField("createProcessItemThread", this.createProcessItemThread);
        jsonWriter.writeJsonField("sendWorkflowSignal", this.sendWorkflowSignal);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionActionDefinition from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionActionDefinition if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionActionDefinition.
     */
    @Generated
    public static ProcessDefinitionActionDefinition fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionActionDefinition deserializedProcessDefinitionActionDefinition = new ProcessDefinitionActionDefinition();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("id".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("type".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.type = ProcessActionType.fromString(reader.getString());
                } else if ("code".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.name = reader.getString();
                } else if ("roles".equals(fieldName)) {
                    List<String> roles = reader.readArray(reader1 -> reader1.getString());
                    deserializedProcessDefinitionActionDefinition.roles = roles;
                } else if ("executeAtStartup".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.executeAtStartup = reader.getNullable(JsonReader::getBoolean);
                } else if ("requireConfirmation".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.requireConfirmation = reader.getNullable(JsonReader::getBoolean);
                } else if ("confirmationDescription".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.confirmationDescription = reader.getString();
                } else if ("availabilityExpression".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.availabilityExpression = reader.getString();
                } else if ("icon".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.icon = reader.getString();
                } else if ("downloadable".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.downloadable = ProcessDefinitionActionDefinitionWorkflow.fromJson(reader);
                } else if ("startWorkflow".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.startWorkflow = ProcessDefinitionActionDefinitionWorkflow.fromJson(
                        reader
                    );
                } else if ("startRelatedProcess".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.startRelatedProcess =
                        ProcessDefinitionActionDefinitionStartRelatedProcess.fromJson(reader);
                } else if ("createTask".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.createTask = ProcessDefinitionActionDefinitionProcessItem.fromJson(
                        reader
                    );
                } else if ("createProcessItemThread".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.createProcessItemThread =
                        ProcessDefinitionActionDefinitionProcessItem.fromJson(reader);
                } else if ("sendWorkflowSignal".equals(fieldName)) {
                    deserializedProcessDefinitionActionDefinition.sendWorkflowSignal =
                        ProcessDefinitionActionDefinitionSendWorkflowSignal.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionActionDefinition;
        });
    }
}
