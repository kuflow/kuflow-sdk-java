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
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

/**
 * A whole Process Definition at one of its versions. Its parts refer to each other by code: the forms of the
 * definition and of its tasks, and the task or thread of an action.
 */
@Fluent
public final class ProcessDefinition extends AbstractAudited {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

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
     * The description property.
     */
    @Generated
    private String description;

    /*
     * The tenantId property.
     */
    @Generated
    private UUID tenantId;

    /*
     * State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes can be created from it,
     * and UNPUBLISHED if no process can be created from it anymore.
     */
    @Generated
    private ProcessDefinitionState state;

    /*
     * The version property.
     */
    @Generated
    private ProcessDefinitionVersion version;

    /*
     * A hash of the whole definition at this version. Any change in it gives another one, including a change
     * in its permissions or its schedulers.
     */
    @Generated
    private String revision;

    /*
     * The encrypted property.
     */
    @Generated
    private Boolean encrypted;

    /*
     * The idSequentialSupport property.
     */
    @Generated
    private Boolean idSequentialSupport;

    /*
     * The startAvailableDate property.
     */
    @Generated
    private OffsetDateTime startAvailableDate;

    /*
     * The endAvailableDate property.
     */
    @Generated
    private OffsetDateTime endAvailableDate;

    /*
     * What runs the processes of a Process Definition.
     */
    @Generated
    private ProcessDefinitionWorkflowEngine workflowEngine;

    /*
     * The workflowTaskQueue property.
     */
    @Generated
    private String workflowTaskQueue;

    /*
     * The workflowType property.
     */
    @Generated
    private String workflowType;

    /*
     * The serverless workflow, as JSON. Only with a SERVERLESS_WORKFLOW engine.
     */
    @Generated
    private Map<String, Object> workflowDefinition;

    /*
     * The code of the form of the metadata of its processes.
     */
    @Generated
    private String metadataStructureDataDefinitionCode;

    /*
     * The code of the form of the entity of its processes.
     */
    @Generated
    private String entityStructureDataDefinitionCode;

    /*
     * The rbacEntries property.
     */
    @Generated
    private List<ProcessDefinitionRbacEntry> rbacEntries;

    /*
     * The actionDefinitions property.
     */
    @Generated
    private List<ProcessDefinitionActionDefinition> actionDefinitions;

    /*
     * The schedulers property.
     */
    @Generated
    private List<ProcessDefinitionScheduler> schedulers;

    /*
     * The workflowCompleteRoles property.
     */
    @Generated
    private List<String> workflowCompleteRoles;

    /*
     * The forms of the definition and of its tasks.
     */
    @Generated
    private List<ProcessDefinitionStructureDataDefinitionEntry> structureDataDefinitionEntries;

    /*
     * Its tasks and threads.
     */
    @Generated
    private List<ProcessItemDefinition> processItemDefinitions;

    /**
     * Creates an instance of ProcessDefinition class.
     */
    @Generated
    public ProcessDefinition() {}

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
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setId(UUID id) {
        this.id = id;
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
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setCode(String code) {
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
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the description property: The description property.
     *
     * @return the description value.
     */
    @Generated
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: The description property.
     *
     * @param description the description value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the tenantId property: The tenantId property.
     *
     * @return the tenantId value.
     */
    @Generated
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: The tenantId property.
     *
     * @param tenantId the tenantId value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * Get the state property: State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes
     * can be created from it,
     * and UNPUBLISHED if no process can be created from it anymore.
     *
     * @return the state value.
     */
    @Generated
    public ProcessDefinitionState getState() {
        return this.state;
    }

    /**
     * Set the state property: State of a Process Definition: DRAFT if it was never published, PUBLISHED if processes
     * can be created from it,
     * and UNPUBLISHED if no process can be created from it anymore.
     *
     * @param state the state value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setState(ProcessDefinitionState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the version property: The version property.
     *
     * @return the version value.
     */
    @Generated
    public ProcessDefinitionVersion getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version property.
     *
     * @param version the version value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setVersion(ProcessDefinitionVersion version) {
        this.version = version;
        return this;
    }

    /**
     * Get the revision property: A hash of the whole definition at this version. Any change in it gives another one,
     * including a change
     * in its permissions or its schedulers.
     *
     * @return the revision value.
     */
    @Generated
    public String getRevision() {
        return this.revision;
    }

    /**
     * Set the revision property: A hash of the whole definition at this version. Any change in it gives another one,
     * including a change
     * in its permissions or its schedulers.
     *
     * @param revision the revision value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setRevision(String revision) {
        this.revision = revision;
        return this;
    }

    /**
     * Get the encrypted property: The encrypted property.
     *
     * @return the encrypted value.
     */
    @Generated
    public Boolean isEncrypted() {
        return this.encrypted;
    }

    /**
     * Set the encrypted property: The encrypted property.
     *
     * @param encrypted the encrypted value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setEncrypted(Boolean encrypted) {
        this.encrypted = encrypted;
        return this;
    }

    /**
     * Get the idSequentialSupport property: The idSequentialSupport property.
     *
     * @return the idSequentialSupport value.
     */
    @Generated
    public Boolean isIdSequentialSupport() {
        return this.idSequentialSupport;
    }

    /**
     * Set the idSequentialSupport property: The idSequentialSupport property.
     *
     * @param idSequentialSupport the idSequentialSupport value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setIdSequentialSupport(Boolean idSequentialSupport) {
        this.idSequentialSupport = idSequentialSupport;
        return this;
    }

    /**
     * Get the startAvailableDate property: The startAvailableDate property.
     *
     * @return the startAvailableDate value.
     */
    @Generated
    public OffsetDateTime getStartAvailableDate() {
        return this.startAvailableDate;
    }

    /**
     * Set the startAvailableDate property: The startAvailableDate property.
     *
     * @param startAvailableDate the startAvailableDate value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setStartAvailableDate(OffsetDateTime startAvailableDate) {
        this.startAvailableDate = startAvailableDate;
        return this;
    }

    /**
     * Get the endAvailableDate property: The endAvailableDate property.
     *
     * @return the endAvailableDate value.
     */
    @Generated
    public OffsetDateTime getEndAvailableDate() {
        return this.endAvailableDate;
    }

    /**
     * Set the endAvailableDate property: The endAvailableDate property.
     *
     * @param endAvailableDate the endAvailableDate value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setEndAvailableDate(OffsetDateTime endAvailableDate) {
        this.endAvailableDate = endAvailableDate;
        return this;
    }

    /**
     * Get the workflowEngine property: What runs the processes of a Process Definition.
     *
     * @return the workflowEngine value.
     */
    @Generated
    public ProcessDefinitionWorkflowEngine getWorkflowEngine() {
        return this.workflowEngine;
    }

    /**
     * Set the workflowEngine property: What runs the processes of a Process Definition.
     *
     * @param workflowEngine the workflowEngine value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setWorkflowEngine(ProcessDefinitionWorkflowEngine workflowEngine) {
        this.workflowEngine = workflowEngine;
        return this;
    }

    /**
     * Get the workflowTaskQueue property: The workflowTaskQueue property.
     *
     * @return the workflowTaskQueue value.
     */
    @Generated
    public String getWorkflowTaskQueue() {
        return this.workflowTaskQueue;
    }

    /**
     * Set the workflowTaskQueue property: The workflowTaskQueue property.
     *
     * @param workflowTaskQueue the workflowTaskQueue value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setWorkflowTaskQueue(String workflowTaskQueue) {
        this.workflowTaskQueue = workflowTaskQueue;
        return this;
    }

    /**
     * Get the workflowType property: The workflowType property.
     *
     * @return the workflowType value.
     */
    @Generated
    public String getWorkflowType() {
        return this.workflowType;
    }

    /**
     * Set the workflowType property: The workflowType property.
     *
     * @param workflowType the workflowType value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setWorkflowType(String workflowType) {
        this.workflowType = workflowType;
        return this;
    }

    /**
     * Get the workflowDefinition property: The serverless workflow, as JSON. Only with a SERVERLESS_WORKFLOW engine.
     *
     * @return the workflowDefinition value.
     */
    @Generated
    public Map<String, Object> getWorkflowDefinition() {
        return this.workflowDefinition;
    }

    /**
     * Set the workflowDefinition property: The serverless workflow, as JSON. Only with a SERVERLESS_WORKFLOW engine.
     *
     * @param workflowDefinition the workflowDefinition value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setWorkflowDefinition(Map<String, Object> workflowDefinition) {
        this.workflowDefinition = workflowDefinition;
        return this;
    }

    /**
     * Get the metadataStructureDataDefinitionCode property: The code of the form of the metadata of its processes.
     *
     * @return the metadataStructureDataDefinitionCode value.
     */
    @Generated
    public String getMetadataStructureDataDefinitionCode() {
        return this.metadataStructureDataDefinitionCode;
    }

    /**
     * Set the metadataStructureDataDefinitionCode property: The code of the form of the metadata of its processes.
     *
     * @param metadataStructureDataDefinitionCode the metadataStructureDataDefinitionCode value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setMetadataStructureDataDefinitionCode(String metadataStructureDataDefinitionCode) {
        this.metadataStructureDataDefinitionCode = metadataStructureDataDefinitionCode;
        return this;
    }

    /**
     * Get the entityStructureDataDefinitionCode property: The code of the form of the entity of its processes.
     *
     * @return the entityStructureDataDefinitionCode value.
     */
    @Generated
    public String getEntityStructureDataDefinitionCode() {
        return this.entityStructureDataDefinitionCode;
    }

    /**
     * Set the entityStructureDataDefinitionCode property: The code of the form of the entity of its processes.
     *
     * @param entityStructureDataDefinitionCode the entityStructureDataDefinitionCode value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setEntityStructureDataDefinitionCode(String entityStructureDataDefinitionCode) {
        this.entityStructureDataDefinitionCode = entityStructureDataDefinitionCode;
        return this;
    }

    /**
     * Get the rbacEntries property: The rbacEntries property.
     *
     * @return the rbacEntries value.
     */
    @Generated
    public List<ProcessDefinitionRbacEntry> getRbacEntries() {
        return this.rbacEntries;
    }

    /**
     * Set the rbacEntries property: The rbacEntries property.
     *
     * @param rbacEntries the rbacEntries value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setRbacEntries(List<ProcessDefinitionRbacEntry> rbacEntries) {
        this.rbacEntries = rbacEntries;
        return this;
    }

    /**
     * Get the actionDefinitions property: The actionDefinitions property.
     *
     * @return the actionDefinitions value.
     */
    @Generated
    public List<ProcessDefinitionActionDefinition> getActionDefinitions() {
        return this.actionDefinitions;
    }

    /**
     * Set the actionDefinitions property: The actionDefinitions property.
     *
     * @param actionDefinitions the actionDefinitions value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setActionDefinitions(List<ProcessDefinitionActionDefinition> actionDefinitions) {
        this.actionDefinitions = actionDefinitions;
        return this;
    }

    /**
     * Get the schedulers property: The schedulers property.
     *
     * @return the schedulers value.
     */
    @Generated
    public List<ProcessDefinitionScheduler> getSchedulers() {
        return this.schedulers;
    }

    /**
     * Set the schedulers property: The schedulers property.
     *
     * @param schedulers the schedulers value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setSchedulers(List<ProcessDefinitionScheduler> schedulers) {
        this.schedulers = schedulers;
        return this;
    }

    /**
     * Get the workflowCompleteRoles property: The workflowCompleteRoles property.
     *
     * @return the workflowCompleteRoles value.
     */
    @Generated
    public List<String> getWorkflowCompleteRoles() {
        return this.workflowCompleteRoles;
    }

    /**
     * Set the workflowCompleteRoles property: The workflowCompleteRoles property.
     *
     * @param workflowCompleteRoles the workflowCompleteRoles value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setWorkflowCompleteRoles(List<String> workflowCompleteRoles) {
        this.workflowCompleteRoles = workflowCompleteRoles;
        return this;
    }

    /**
     * Get the structureDataDefinitionEntries property: The forms of the definition and of its tasks.
     *
     * @return the structureDataDefinitionEntries value.
     */
    @Generated
    public List<ProcessDefinitionStructureDataDefinitionEntry> getStructureDataDefinitionEntries() {
        return this.structureDataDefinitionEntries;
    }

    /**
     * Set the structureDataDefinitionEntries property: The forms of the definition and of its tasks.
     *
     * @param structureDataDefinitionEntries the structureDataDefinitionEntries value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setStructureDataDefinitionEntries(
        List<ProcessDefinitionStructureDataDefinitionEntry> structureDataDefinitionEntries
    ) {
        this.structureDataDefinitionEntries = structureDataDefinitionEntries;
        return this;
    }

    /**
     * Get the processItemDefinitions property: Its tasks and threads.
     *
     * @return the processItemDefinitions value.
     */
    @Generated
    public List<ProcessItemDefinition> getProcessItemDefinitions() {
        return this.processItemDefinitions;
    }

    /**
     * Set the processItemDefinitions property: Its tasks and threads.
     *
     * @param processItemDefinitions the processItemDefinitions value to set.
     * @return the ProcessDefinition object itself.
     */
    @Generated
    public ProcessDefinition setProcessItemDefinitions(List<ProcessItemDefinition> processItemDefinitions) {
        this.processItemDefinitions = processItemDefinitions;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessDefinition setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessDefinition setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessDefinition setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public ProcessDefinition setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeStringField("code", this.code);
        jsonWriter.writeStringField("name", this.name);
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        jsonWriter.writeStringField("state", this.state == null ? null : this.state.toString());
        jsonWriter.writeJsonField("version", this.version);
        jsonWriter.writeStringField("revision", this.revision);
        jsonWriter.writeStringField("workflowEngine", this.workflowEngine == null ? null : this.workflowEngine.toString());
        jsonWriter.writeArrayField("rbacEntries", this.rbacEntries, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("actionDefinitions", this.actionDefinitions, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("schedulers", this.schedulers, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeArrayField("workflowCompleteRoles", this.workflowCompleteRoles, (writer, element) -> writer.writeString(element));
        jsonWriter.writeArrayField("structureDataDefinitionEntries", this.structureDataDefinitionEntries, (writer, element) ->
            writer.writeJson(element)
        );
        jsonWriter.writeArrayField("processItemDefinitions", this.processItemDefinitions, (writer, element) -> writer.writeJson(element));
        jsonWriter.writeStringField("description", this.description);
        jsonWriter.writeBooleanField("encrypted", this.encrypted);
        jsonWriter.writeBooleanField("idSequentialSupport", this.idSequentialSupport);
        jsonWriter.writeStringField(
            "startAvailableDate",
            this.startAvailableDate == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.startAvailableDate)
        );
        jsonWriter.writeStringField(
            "endAvailableDate",
            this.endAvailableDate == null ? null : DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(this.endAvailableDate)
        );
        jsonWriter.writeStringField("workflowTaskQueue", this.workflowTaskQueue);
        jsonWriter.writeStringField("workflowType", this.workflowType);
        jsonWriter.writeMapField("workflowDefinition", this.workflowDefinition, (writer, element) -> writer.writeUntyped(element));
        jsonWriter.writeStringField("metadataStructureDataDefinitionCode", this.metadataStructureDataDefinitionCode);
        jsonWriter.writeStringField("entityStructureDataDefinitionCode", this.entityStructureDataDefinitionCode);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinition from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinition if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinition.
     */
    @Generated
    public static ProcessDefinition fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinition deserializedProcessDefinition = new ProcessDefinition();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedProcessDefinition.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedProcessDefinition.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedProcessDefinition.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedProcessDefinition.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedProcessDefinition.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("code".equals(fieldName)) {
                    deserializedProcessDefinition.code = reader.getString();
                } else if ("name".equals(fieldName)) {
                    deserializedProcessDefinition.name = reader.getString();
                } else if ("tenantId".equals(fieldName)) {
                    deserializedProcessDefinition.tenantId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("state".equals(fieldName)) {
                    deserializedProcessDefinition.state = ProcessDefinitionState.fromString(reader.getString());
                } else if ("version".equals(fieldName)) {
                    deserializedProcessDefinition.version = ProcessDefinitionVersion.fromJson(reader);
                } else if ("revision".equals(fieldName)) {
                    deserializedProcessDefinition.revision = reader.getString();
                } else if ("workflowEngine".equals(fieldName)) {
                    deserializedProcessDefinition.workflowEngine = ProcessDefinitionWorkflowEngine.fromString(reader.getString());
                } else if ("rbacEntries".equals(fieldName)) {
                    List<ProcessDefinitionRbacEntry> rbacEntries = reader.readArray(reader1 ->
                        ProcessDefinitionRbacEntry.fromJson(reader1)
                    );
                    deserializedProcessDefinition.rbacEntries = rbacEntries;
                } else if ("actionDefinitions".equals(fieldName)) {
                    List<ProcessDefinitionActionDefinition> actionDefinitions = reader.readArray(reader1 ->
                        ProcessDefinitionActionDefinition.fromJson(reader1)
                    );
                    deserializedProcessDefinition.actionDefinitions = actionDefinitions;
                } else if ("schedulers".equals(fieldName)) {
                    List<ProcessDefinitionScheduler> schedulers = reader.readArray(reader1 -> ProcessDefinitionScheduler.fromJson(reader1));
                    deserializedProcessDefinition.schedulers = schedulers;
                } else if ("workflowCompleteRoles".equals(fieldName)) {
                    List<String> workflowCompleteRoles = reader.readArray(reader1 -> reader1.getString());
                    deserializedProcessDefinition.workflowCompleteRoles = workflowCompleteRoles;
                } else if ("structureDataDefinitionEntries".equals(fieldName)) {
                    List<ProcessDefinitionStructureDataDefinitionEntry> structureDataDefinitionEntries = reader.readArray(reader1 ->
                        ProcessDefinitionStructureDataDefinitionEntry.fromJson(reader1)
                    );
                    deserializedProcessDefinition.structureDataDefinitionEntries = structureDataDefinitionEntries;
                } else if ("processItemDefinitions".equals(fieldName)) {
                    List<ProcessItemDefinition> processItemDefinitions = reader.readArray(reader1 ->
                        ProcessItemDefinition.fromJson(reader1)
                    );
                    deserializedProcessDefinition.processItemDefinitions = processItemDefinitions;
                } else if ("description".equals(fieldName)) {
                    deserializedProcessDefinition.description = reader.getString();
                } else if ("encrypted".equals(fieldName)) {
                    deserializedProcessDefinition.encrypted = reader.getNullable(JsonReader::getBoolean);
                } else if ("idSequentialSupport".equals(fieldName)) {
                    deserializedProcessDefinition.idSequentialSupport = reader.getNullable(JsonReader::getBoolean);
                } else if ("startAvailableDate".equals(fieldName)) {
                    deserializedProcessDefinition.startAvailableDate = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("endAvailableDate".equals(fieldName)) {
                    deserializedProcessDefinition.endAvailableDate = reader.getNullable(nonNullReader ->
                        CoreUtils.parseBestOffsetDateTime(nonNullReader.getString())
                    );
                } else if ("workflowTaskQueue".equals(fieldName)) {
                    deserializedProcessDefinition.workflowTaskQueue = reader.getString();
                } else if ("workflowType".equals(fieldName)) {
                    deserializedProcessDefinition.workflowType = reader.getString();
                } else if ("workflowDefinition".equals(fieldName)) {
                    Map<String, Object> workflowDefinition = reader.readMap(reader1 -> reader1.readUntyped());
                    deserializedProcessDefinition.workflowDefinition = workflowDefinition;
                } else if ("metadataStructureDataDefinitionCode".equals(fieldName)) {
                    deserializedProcessDefinition.metadataStructureDataDefinitionCode = reader.getString();
                } else if ("entityStructureDataDefinitionCode".equals(fieldName)) {
                    deserializedProcessDefinition.entityStructureDataDefinitionCode = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinition;
        });
    }
}
