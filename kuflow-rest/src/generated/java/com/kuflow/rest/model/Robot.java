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
import java.util.Map;
import java.util.UUID;

/**
 * The Robot model.
 */
@Fluent
public final class Robot extends AbstractAudited {

    /*
     * Robot ID.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * Robot Code.
     */
    @JsonProperty(value = "code", required = true)
    private String code;

    /*
     * Robot name.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /*
     * Robot description.
     */
    @JsonProperty(value = "description")
    private String description;

    /*
     * Robot source type
     */
    @JsonProperty(value = "sourceType", required = true)
    private RobotSourceType sourceType;

    /*
     * Robot source type
     */
    @JsonProperty(value = "sourceFile")
    private RobotSourceFile sourceFile;

    /*
     * Environment variables to load when the robot is executed.
     */
    @JsonProperty(value = "environmentVariables")
    private Map<String, String> environmentVariables;

    /*
     * Tenant ID.
     */
    @JsonProperty(value = "tenantId")
    private UUID tenantId;

    /**
     * Creates an instance of Robot class.
     */
    public Robot() {}

    /**
     * Get the id property: Robot ID.
     *
     * @return the id value.
     */
    public UUID getId() {
        return this.id;
    }

    /**
     * Set the id property: Robot ID.
     *
     * @param id the id value to set.
     * @return the Robot object itself.
     */
    public Robot setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the code property: Robot Code.
     *
     * @return the code value.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: Robot Code.
     *
     * @param code the code value to set.
     * @return the Robot object itself.
     */
    public Robot setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: Robot name.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Robot name.
     *
     * @param name the name value to set.
     * @return the Robot object itself.
     */
    public Robot setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the description property: Robot description.
     *
     * @return the description value.
     */
    public String getDescription() {
        return this.description;
    }

    /**
     * Set the description property: Robot description.
     *
     * @param description the description value to set.
     * @return the Robot object itself.
     */
    public Robot setDescription(String description) {
        this.description = description;
        return this;
    }

    /**
     * Get the sourceType property: Robot source type.
     *
     * @return the sourceType value.
     */
    public RobotSourceType getSourceType() {
        return this.sourceType;
    }

    /**
     * Set the sourceType property: Robot source type.
     *
     * @param sourceType the sourceType value to set.
     * @return the Robot object itself.
     */
    public Robot setSourceType(RobotSourceType sourceType) {
        this.sourceType = sourceType;
        return this;
    }

    /**
     * Get the sourceFile property: Robot source type.
     *
     * @return the sourceFile value.
     */
    public RobotSourceFile getSourceFile() {
        return this.sourceFile;
    }

    /**
     * Set the sourceFile property: Robot source type.
     *
     * @param sourceFile the sourceFile value to set.
     * @return the Robot object itself.
     */
    public Robot setSourceFile(RobotSourceFile sourceFile) {
        this.sourceFile = sourceFile;
        return this;
    }

    /**
     * Get the environmentVariables property: Environment variables to load when the robot is executed.
     *
     * @return the environmentVariables value.
     */
    public Map<String, String> getEnvironmentVariables() {
        return this.environmentVariables;
    }

    /**
     * Set the environmentVariables property: Environment variables to load when the robot is executed.
     *
     * @param environmentVariables the environmentVariables value to set.
     * @return the Robot object itself.
     */
    public Robot setEnvironmentVariables(Map<String, String> environmentVariables) {
        this.environmentVariables = environmentVariables;
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
     * @return the Robot object itself.
     */
    public Robot setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Robot setObjectType(AuditedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Robot setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Robot setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Robot setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Robot setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }
}
