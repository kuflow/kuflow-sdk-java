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
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * The WorkerCreateParams model.
 */
@Fluent
public final class WorkerCreateParams implements JsonSerializable<WorkerCreateParams> {

    /*
     * The identity property.
     */
    private String identity;

    /*
     * The taskQueue property.
     */
    private String taskQueue;

    /*
     * The workflowTypes property.
     */
    private List<String> workflowTypes;

    /*
     * The activityTypes property.
     */
    private List<String> activityTypes;

    /*
     * The hostname property.
     */
    private String hostname;

    /*
     * The ip property.
     */
    private String ip;

    /*
     * Installation Id.
     */
    private UUID installationId;

    /*
     * Robot Ids that this worker implements.
     */
    private List<UUID> robotIds;

    /*
     * Tenant ID.
     */
    private UUID tenantId;

    /**
     * Creates an instance of WorkerCreateParams class.
     */
    public WorkerCreateParams() {}

    /**
     * Get the identity property: The identity property.
     *
     * @return the identity value.
     */
    public String getIdentity() {
        return this.identity;
    }

    /**
     * Set the identity property: The identity property.
     *
     * @param identity the identity value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setIdentity(String identity) {
        this.identity = identity;
        return this;
    }

    /**
     * Get the taskQueue property: The taskQueue property.
     *
     * @return the taskQueue value.
     */
    public String getTaskQueue() {
        return this.taskQueue;
    }

    /**
     * Set the taskQueue property: The taskQueue property.
     *
     * @param taskQueue the taskQueue value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setTaskQueue(String taskQueue) {
        this.taskQueue = taskQueue;
        return this;
    }

    /**
     * Get the workflowTypes property: The workflowTypes property.
     *
     * @return the workflowTypes value.
     */
    public List<String> getWorkflowTypes() {
        return this.workflowTypes;
    }

    /**
     * Set the workflowTypes property: The workflowTypes property.
     *
     * @param workflowTypes the workflowTypes value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setWorkflowTypes(List<String> workflowTypes) {
        this.workflowTypes = workflowTypes;
        return this;
    }

    /**
     * Get the activityTypes property: The activityTypes property.
     *
     * @return the activityTypes value.
     */
    public List<String> getActivityTypes() {
        return this.activityTypes;
    }

    /**
     * Set the activityTypes property: The activityTypes property.
     *
     * @param activityTypes the activityTypes value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setActivityTypes(List<String> activityTypes) {
        this.activityTypes = activityTypes;
        return this;
    }

    /**
     * Get the hostname property: The hostname property.
     *
     * @return the hostname value.
     */
    public String getHostname() {
        return this.hostname;
    }

    /**
     * Set the hostname property: The hostname property.
     *
     * @param hostname the hostname value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setHostname(String hostname) {
        this.hostname = hostname;
        return this;
    }

    /**
     * Get the ip property: The ip property.
     *
     * @return the ip value.
     */
    public String getIp() {
        return this.ip;
    }

    /**
     * Set the ip property: The ip property.
     *
     * @param ip the ip value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setIp(String ip) {
        this.ip = ip;
        return this;
    }

    /**
     * Get the installationId property: Installation Id.
     *
     * @return the installationId value.
     */
    public UUID getInstallationId() {
        return this.installationId;
    }

    /**
     * Set the installationId property: Installation Id.
     *
     * @param installationId the installationId value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setInstallationId(UUID installationId) {
        this.installationId = installationId;
        return this;
    }

    /**
     * Get the robotIds property: Robot Ids that this worker implements.
     *
     * @return the robotIds value.
     */
    public List<UUID> getRobotIds() {
        return this.robotIds;
    }

    /**
     * Set the robotIds property: Robot Ids that this worker implements.
     *
     * @param robotIds the robotIds value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setRobotIds(List<UUID> robotIds) {
        this.robotIds = robotIds;
        return this;
    }

    /**
     * Get the tenantId property: Tenant ID.
     *
     * @return the tenantId value.
     */
    public UUID getTenantId() {
        return this.tenantId;
    }

    /**
     * Set the tenantId property: Tenant ID.
     *
     * @param tenantId the tenantId value to set.
     * @return the WorkerCreateParams object itself.
     */
    public WorkerCreateParams setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("identity", this.identity);
        jsonWriter.writeStringField("taskQueue", this.taskQueue);
        jsonWriter.writeStringField("hostname", this.hostname);
        jsonWriter.writeStringField("ip", this.ip);
        jsonWriter.writeArrayField("workflowTypes", this.workflowTypes, (writer, element) -> writer.writeString(element));
        jsonWriter.writeArrayField("activityTypes", this.activityTypes, (writer, element) -> writer.writeString(element));
        jsonWriter.writeStringField("installationId", Objects.toString(this.installationId, null));
        jsonWriter.writeArrayField("robotIds", this.robotIds, (writer, element) -> writer.writeString(Objects.toString(element, null)));
        jsonWriter.writeStringField("tenantId", Objects.toString(this.tenantId, null));
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of WorkerCreateParams from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of WorkerCreateParams if the JsonReader was pointing to an instance of it, or null if it was
     * pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the WorkerCreateParams.
     */
    public static WorkerCreateParams fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            WorkerCreateParams deserializedWorkerCreateParams = new WorkerCreateParams();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("identity".equals(fieldName)) {
                    deserializedWorkerCreateParams.identity = reader.getString();
                } else if ("taskQueue".equals(fieldName)) {
                    deserializedWorkerCreateParams.taskQueue = reader.getString();
                } else if ("hostname".equals(fieldName)) {
                    deserializedWorkerCreateParams.hostname = reader.getString();
                } else if ("ip".equals(fieldName)) {
                    deserializedWorkerCreateParams.ip = reader.getString();
                } else if ("workflowTypes".equals(fieldName)) {
                    List<String> workflowTypes = reader.readArray(reader1 -> reader1.getString());
                    deserializedWorkerCreateParams.workflowTypes = workflowTypes;
                } else if ("activityTypes".equals(fieldName)) {
                    List<String> activityTypes = reader.readArray(reader1 -> reader1.getString());
                    deserializedWorkerCreateParams.activityTypes = activityTypes;
                } else if ("installationId".equals(fieldName)) {
                    deserializedWorkerCreateParams.installationId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("robotIds".equals(fieldName)) {
                    List<UUID> robotIds = reader.readArray(reader1 ->
                        reader1.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString()))
                    );
                    deserializedWorkerCreateParams.robotIds = robotIds;
                } else if ("tenantId".equals(fieldName)) {
                    deserializedWorkerCreateParams.tenantId = reader.getNullable(nonNullReader -> UUID.fromString(nonNullReader.getString())
                    );
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedWorkerCreateParams;
        });
    }
}
