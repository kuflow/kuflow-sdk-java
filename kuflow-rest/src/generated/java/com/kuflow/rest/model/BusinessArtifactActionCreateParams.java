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
 * Params to invoke an action on a Business Artifact. The populated
 * sub-field (`startWorkflow`, `downloadable`, `startProcess`,
 * `createArtifact`) must match the type of the action identified by
 * `businessArtifactActionDefinitionCode`.
 */
@Fluent
public final class BusinessArtifactActionCreateParams implements JsonSerializable<BusinessArtifactActionCreateParams> {

    /*
     * The id property.
     */
    @Generated
    private UUID id;

    /*
     * The businessArtifactActionDefinitionCode property.
     */
    @Generated
    private String businessArtifactActionDefinitionCode;

    /*
     * Params for invoking an action of type START_WORKFLOW.
     */
    @Generated
    private BusinessArtifactActionCreateParamsStartWorkflow startWorkflow;

    /*
     * Params for invoking an action of type DOWNLOADABLE.
     */
    @Generated
    private BusinessArtifactActionCreateParamsDownloadable downloadable;

    /*
     * Params for invoking an action of type START_PROCESS.
     */
    @Generated
    private BusinessArtifactActionCreateParamsStartProcess startProcess;

    /*
     * Params for invoking an action of type CREATE_BUSINESS_ARTIFACT. The `value` is
     * the (potentially user-edited) form previously obtained via the
     * `prepareBusinessArtifactCreateArtifact` operation.
     */
    @Generated
    private BusinessArtifactActionCreateParamsCreateArtifact createArtifact;

    /**
     * Creates an instance of BusinessArtifactActionCreateParams class.
     */
    @Generated
    public BusinessArtifactActionCreateParams() {}

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
     * @return the BusinessArtifactActionCreateParams object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParams setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the businessArtifactActionDefinitionCode property: The businessArtifactActionDefinitionCode property.
     *
     * @return the businessArtifactActionDefinitionCode value.
     */
    @Generated
    public String getBusinessArtifactActionDefinitionCode() {
        return this.businessArtifactActionDefinitionCode;
    }

    /**
     * Set the businessArtifactActionDefinitionCode property: The businessArtifactActionDefinitionCode property.
     *
     * @param businessArtifactActionDefinitionCode the businessArtifactActionDefinitionCode value to set.
     * @return the BusinessArtifactActionCreateParams object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParams setBusinessArtifactActionDefinitionCode(String businessArtifactActionDefinitionCode) {
        this.businessArtifactActionDefinitionCode = businessArtifactActionDefinitionCode;
        return this;
    }

    /**
     * Get the startWorkflow property: Params for invoking an action of type START_WORKFLOW.
     *
     * @return the startWorkflow value.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartWorkflow getStartWorkflow() {
        return this.startWorkflow;
    }

    /**
     * Set the startWorkflow property: Params for invoking an action of type START_WORKFLOW.
     *
     * @param startWorkflow the startWorkflow value to set.
     * @return the BusinessArtifactActionCreateParams object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParams setStartWorkflow(BusinessArtifactActionCreateParamsStartWorkflow startWorkflow) {
        this.startWorkflow = startWorkflow;
        return this;
    }

    /**
     * Get the downloadable property: Params for invoking an action of type DOWNLOADABLE.
     *
     * @return the downloadable value.
     */
    @Generated
    public BusinessArtifactActionCreateParamsDownloadable getDownloadable() {
        return this.downloadable;
    }

    /**
     * Set the downloadable property: Params for invoking an action of type DOWNLOADABLE.
     *
     * @param downloadable the downloadable value to set.
     * @return the BusinessArtifactActionCreateParams object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParams setDownloadable(BusinessArtifactActionCreateParamsDownloadable downloadable) {
        this.downloadable = downloadable;
        return this;
    }

    /**
     * Get the startProcess property: Params for invoking an action of type START_PROCESS.
     *
     * @return the startProcess value.
     */
    @Generated
    public BusinessArtifactActionCreateParamsStartProcess getStartProcess() {
        return this.startProcess;
    }

    /**
     * Set the startProcess property: Params for invoking an action of type START_PROCESS.
     *
     * @param startProcess the startProcess value to set.
     * @return the BusinessArtifactActionCreateParams object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParams setStartProcess(BusinessArtifactActionCreateParamsStartProcess startProcess) {
        this.startProcess = startProcess;
        return this;
    }

    /**
     * Get the createArtifact property: Params for invoking an action of type CREATE_BUSINESS_ARTIFACT. The `value` is
     * the (potentially user-edited) form previously obtained via the
     * `prepareBusinessArtifactCreateArtifact` operation.
     *
     * @return the createArtifact value.
     */
    @Generated
    public BusinessArtifactActionCreateParamsCreateArtifact getCreateArtifact() {
        return this.createArtifact;
    }

    /**
     * Set the createArtifact property: Params for invoking an action of type CREATE_BUSINESS_ARTIFACT. The `value` is
     * the (potentially user-edited) form previously obtained via the
     * `prepareBusinessArtifactCreateArtifact` operation.
     *
     * @param createArtifact the createArtifact value to set.
     * @return the BusinessArtifactActionCreateParams object itself.
     */
    @Generated
    public BusinessArtifactActionCreateParams setCreateArtifact(BusinessArtifactActionCreateParamsCreateArtifact createArtifact) {
        this.createArtifact = createArtifact;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("businessArtifactActionDefinitionCode", this.businessArtifactActionDefinitionCode);
        jsonWriter.writeStringField("id", Objects.toString(this.id, null));
        jsonWriter.writeJsonField("startWorkflow", this.startWorkflow);
        jsonWriter.writeJsonField("downloadable", this.downloadable);
        jsonWriter.writeJsonField("startProcess", this.startProcess);
        jsonWriter.writeJsonField("createArtifact", this.createArtifact);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of BusinessArtifactActionCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of BusinessArtifactActionCreateParams if the JsonReader was pointing to an instance of it, or
     * null if it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the BusinessArtifactActionCreateParams.
     */
    @Generated
    public static BusinessArtifactActionCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            BusinessArtifactActionCreateParams deserializedBusinessArtifactActionCreateParams = new BusinessArtifactActionCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("businessArtifactActionDefinitionCode".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateParams.businessArtifactActionDefinitionCode = reader.getString();
                } else if ("id".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateParams.id = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("startWorkflow".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateParams.startWorkflow = BusinessArtifactActionCreateParamsStartWorkflow.fromJson(
                        reader
                    );
                } else if ("downloadable".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateParams.downloadable = BusinessArtifactActionCreateParamsDownloadable.fromJson(
                        reader
                    );
                } else if ("startProcess".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateParams.startProcess = BusinessArtifactActionCreateParamsStartProcess.fromJson(
                        reader
                    );
                } else if ("createArtifact".equals(fieldName)) {
                    deserializedBusinessArtifactActionCreateParams.createArtifact =
                        BusinessArtifactActionCreateParamsCreateArtifact.fromJson(reader);
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedBusinessArtifactActionCreateParams;
        });
    }
}
