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
 * A Business Artifact action invocation. The populated sub-field
 * (`startWorkflow`, `startProcess`, `downloadable`, `createArtifact`) is
 * dictated by `type`.
 */
@Fluent
public final class BusinessArtifactAction extends AbstractAudited {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * Type of a Business Artifact action.
     */
    @Generated
    private BusinessArtifactActionType type;

    /*
     * Status of a Business Artifact action.
     */
    @Generated
    private BusinessArtifactActionStatus status;

    /*
     * The businessArtifactActionDefinitionRef property.
     */
    @Generated
    private BusinessArtifactActionDefinitionRef businessArtifactActionDefinitionRef;

    /*
     * Action details for actions of type START_WORKFLOW.
     */
    @Generated
    private BusinessArtifactActionStartWorkflow startWorkflow;

    /*
     * Action details for actions of type START_PROCESS. Actions of this
     * type complete synchronously, so all fields are present in the invoke
     * response.
     */
    @Generated
    private BusinessArtifactActionStartProcess startProcess;

    /*
     * Action details for actions of type DOWNLOADABLE.
     */
    @Generated
    private BusinessArtifactActionDownloadable downloadable;

    /*
     * Action details for actions of type CREATE_BUSINESS_ARTIFACT.
     * Actions of this type complete synchronously, so the populated value and
     * the created Business Artifact ID are present in the invoke response.
     */
    @Generated
    private BusinessArtifactActionCreateArtifact createArtifact;

    /**
     * Creates an instance of BusinessArtifactAction class.
     */
    @Generated
    public BusinessArtifactAction() {}

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
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the type property: Type of a Business Artifact action.
     *
     * @return the type value.
     */
    @Generated
    public BusinessArtifactActionType getType() {
        return this.type;
    }

    /**
     * Set the type property: Type of a Business Artifact action.
     *
     * @param type the type value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setType(BusinessArtifactActionType type) {
        this.type = type;
        return this;
    }

    /**
     * Get the status property: Status of a Business Artifact action.
     *
     * @return the status value.
     */
    @Generated
    public BusinessArtifactActionStatus getStatus() {
        return this.status;
    }

    /**
     * Set the status property: Status of a Business Artifact action.
     *
     * @param status the status value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setStatus(BusinessArtifactActionStatus status) {
        this.status = status;
        return this;
    }

    /**
     * Get the businessArtifactActionDefinitionRef property: The businessArtifactActionDefinitionRef property.
     *
     * @return the businessArtifactActionDefinitionRef value.
     */
    @Generated
    public BusinessArtifactActionDefinitionRef getBusinessArtifactActionDefinitionRef() {
        return this.businessArtifactActionDefinitionRef;
    }

    /**
     * Set the businessArtifactActionDefinitionRef property: The businessArtifactActionDefinitionRef property.
     *
     * @param businessArtifactActionDefinitionRef the businessArtifactActionDefinitionRef value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setBusinessArtifactActionDefinitionRef(
        BusinessArtifactActionDefinitionRef businessArtifactActionDefinitionRef
    ) {
        this.businessArtifactActionDefinitionRef = businessArtifactActionDefinitionRef;
        return this;
    }

    /**
     * Get the startWorkflow property: Action details for actions of type START_WORKFLOW.
     *
     * @return the startWorkflow value.
     */
    @Generated
    public BusinessArtifactActionStartWorkflow getStartWorkflow() {
        return this.startWorkflow;
    }

    /**
     * Set the startWorkflow property: Action details for actions of type START_WORKFLOW.
     *
     * @param startWorkflow the startWorkflow value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setStartWorkflow(BusinessArtifactActionStartWorkflow startWorkflow) {
        this.startWorkflow = startWorkflow;
        return this;
    }

    /**
     * Get the startProcess property: Action details for actions of type START_PROCESS. Actions of this
     * type complete synchronously, so all fields are present in the invoke
     * response.
     *
     * @return the startProcess value.
     */
    @Generated
    public BusinessArtifactActionStartProcess getStartProcess() {
        return this.startProcess;
    }

    /**
     * Set the startProcess property: Action details for actions of type START_PROCESS. Actions of this
     * type complete synchronously, so all fields are present in the invoke
     * response.
     *
     * @param startProcess the startProcess value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setStartProcess(BusinessArtifactActionStartProcess startProcess) {
        this.startProcess = startProcess;
        return this;
    }

    /**
     * Get the downloadable property: Action details for actions of type DOWNLOADABLE.
     *
     * @return the downloadable value.
     */
    @Generated
    public BusinessArtifactActionDownloadable getDownloadable() {
        return this.downloadable;
    }

    /**
     * Set the downloadable property: Action details for actions of type DOWNLOADABLE.
     *
     * @param downloadable the downloadable value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setDownloadable(BusinessArtifactActionDownloadable downloadable) {
        this.downloadable = downloadable;
        return this;
    }

    /**
     * Get the createArtifact property: Action details for actions of type CREATE_BUSINESS_ARTIFACT.
     * Actions of this type complete synchronously, so the populated value and
     * the created Business Artifact ID are present in the invoke response.
     *
     * @return the createArtifact value.
     */
    @Generated
    public BusinessArtifactActionCreateArtifact getCreateArtifact() {
        return this.createArtifact;
    }

    /**
     * Set the createArtifact property: Action details for actions of type CREATE_BUSINESS_ARTIFACT.
     * Actions of this type complete synchronously, so the populated value and
     * the created Business Artifact ID are present in the invoke response.
     *
     * @param createArtifact the createArtifact value to set.
     * @return the BusinessArtifactAction object itself.
     */
    @Generated
    public BusinessArtifactAction setCreateArtifact(BusinessArtifactActionCreateArtifact createArtifact) {
        this.createArtifact = createArtifact;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactAction setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactAction setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactAction setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public BusinessArtifactAction setLastModifiedAt(OffsetDateTime lastModifiedAt) {
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
        jsonWriter.writeJsonField("businessArtifactActionDefinitionRef", this.businessArtifactActionDefinitionRef);
        jsonWriter.writeJsonField("startWorkflow", this.startWorkflow);
        jsonWriter.writeJsonField("startProcess", this.startProcess);
        jsonWriter.writeJsonField("downloadable", this.downloadable);
        jsonWriter.writeJsonField("createArtifact", this.createArtifact);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactAction from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactAction if the JsonReader was pointing to an instance of it, or null if it
     * was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactAction.
     */
    @Generated
    public static BusinessArtifactAction fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactAction deserializedBusinessArtifactAction = new BusinessArtifactAction();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("createdBy".equals(fieldName)) {
                    deserializedBusinessArtifactAction.setCreatedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("createdAt".equals(fieldName)) {
                    deserializedBusinessArtifactAction.setCreatedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("lastModifiedBy".equals(fieldName)) {
                    deserializedBusinessArtifactAction.setLastModifiedBy(
                        reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                } else if ("lastModifiedAt".equals(fieldName)) {
                    deserializedBusinessArtifactAction.setLastModifiedAt(
                        reader.getNullable(nonNullReader -> CoreUtils.parseBestOffsetDateTime(nonNullReader.getString()))
                    );
                } else if ("id".equals(fieldName)) {
                    deserializedBusinessArtifactAction.id = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()));
                } else if ("type".equals(fieldName)) {
                    deserializedBusinessArtifactAction.type = BusinessArtifactActionType.fromString(reader.getString());
                } else if ("status".equals(fieldName)) {
                    deserializedBusinessArtifactAction.status = BusinessArtifactActionStatus.fromString(reader.getString());
                } else if ("businessArtifactActionDefinitionRef".equals(fieldName)) {
                    deserializedBusinessArtifactAction.businessArtifactActionDefinitionRef = BusinessArtifactActionDefinitionRef.fromJson(
                        reader
                    );
                } else if ("startWorkflow".equals(fieldName)) {
                    deserializedBusinessArtifactAction.startWorkflow = BusinessArtifactActionStartWorkflow.fromJson(reader);
                } else if ("startProcess".equals(fieldName)) {
                    deserializedBusinessArtifactAction.startProcess = BusinessArtifactActionStartProcess.fromJson(reader);
                } else if ("downloadable".equals(fieldName)) {
                    deserializedBusinessArtifactAction.downloadable = BusinessArtifactActionDownloadable.fromJson(reader);
                } else if ("createArtifact".equals(fieldName)) {
                    deserializedBusinessArtifactAction.createArtifact = BusinessArtifactActionCreateArtifact.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactAction;
        });
    }
}
