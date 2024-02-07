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
import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

/**
 * The Worker model.
 */
@Fluent
public final class Worker extends AbstractAudited {

    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * The identity property.
     */
    @JsonProperty(value = "identity", required = true)
    private String identity;

    /*
     * The taskQueue property.
     */
    @JsonProperty(value = "taskQueue", required = true)
    private String taskQueue;

    /*
     * The workflowTypes property.
     */
    @JsonProperty(value = "workflowTypes")
    private List<String> workflowTypes;

    /*
     * The activityTypes property.
     */
    @JsonProperty(value = "activityTypes")
    private List<String> activityTypes;

    /*
     * The hostname property.
     */
    @JsonProperty(value = "hostname", required = true)
    private String hostname;

    /*
     * The ip property.
     */
    @JsonProperty(value = "ip", required = true)
    private String ip;

    /*
     * Tenant ID.
     */
    @JsonProperty(value = "tenantId")
    private UUID tenantId;

    /**
     * Creates an instance of Worker class.
     */
    public Worker() {}

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
     * @return the Worker object itself.
     */
    public Worker setId(UUID id) {
        this.id = id;
        return this;
    }

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
     * @return the Worker object itself.
     */
    public Worker setIdentity(String identity) {
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
     * @return the Worker object itself.
     */
    public Worker setTaskQueue(String taskQueue) {
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
     * @return the Worker object itself.
     */
    public Worker setWorkflowTypes(List<String> workflowTypes) {
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
     * @return the Worker object itself.
     */
    public Worker setActivityTypes(List<String> activityTypes) {
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
     * @return the Worker object itself.
     */
    public Worker setHostname(String hostname) {
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
     * @return the Worker object itself.
     */
    public Worker setIp(String ip) {
        this.ip = ip;
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
     * @return the Worker object itself.
     */
    public Worker setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Worker setObjectType(AuditedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Worker setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Worker setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Worker setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Worker setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }
}
