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
import java.util.UUID;

/**
 * The AbstractAudited model.
 */
@Fluent
public class AbstractAudited {

    /*
     * Audited object Types.
     */
    @JsonProperty(value = "objectType")
    private AuditedObjectType objectType;

    /*
     * Who create this model.
     */
    @JsonProperty(value = "createdBy")
    private UUID createdBy;

    /*
     * When this model was created.
     */
    @JsonProperty(value = "createdAt")
    private OffsetDateTime createdAt;

    /*
     * Who was last update this model.
     */
    @JsonProperty(value = "lastModifiedBy")
    private UUID lastModifiedBy;

    /*
     * When this model type was last updated.
     */
    @JsonProperty(value = "lastModifiedAt")
    private OffsetDateTime lastModifiedAt;

    /**
     * Creates an instance of AbstractAudited class.
     */
    public AbstractAudited() {}

    /**
     * Get the objectType property: Audited object Types.
     *
     * @return the objectType value.
     */
    public AuditedObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * Set the objectType property: Audited object Types.
     *
     * @param objectType the objectType value to set.
     * @return the AbstractAudited object itself.
     */
    public AbstractAudited setObjectType(AuditedObjectType objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * Get the createdBy property: Who create this model.
     *
     * @return the createdBy value.
     */
    public UUID getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Set the createdBy property: Who create this model.
     *
     * @param createdBy the createdBy value to set.
     * @return the AbstractAudited object itself.
     */
    public AbstractAudited setCreatedBy(UUID createdBy) {
        this.createdBy = createdBy;
        return this;
    }

    /**
     * Get the createdAt property: When this model was created.
     *
     * @return the createdAt value.
     */
    public OffsetDateTime getCreatedAt() {
        return this.createdAt;
    }

    /**
     * Set the createdAt property: When this model was created.
     *
     * @param createdAt the createdAt value to set.
     * @return the AbstractAudited object itself.
     */
    public AbstractAudited setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    /**
     * Get the lastModifiedBy property: Who was last update this model.
     *
     * @return the lastModifiedBy value.
     */
    public UUID getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Set the lastModifiedBy property: Who was last update this model.
     *
     * @param lastModifiedBy the lastModifiedBy value to set.
     * @return the AbstractAudited object itself.
     */
    public AbstractAudited setLastModifiedBy(UUID lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
        return this;
    }

    /**
     * Get the lastModifiedAt property: When this model type was last updated.
     *
     * @return the lastModifiedAt value.
     */
    public OffsetDateTime getLastModifiedAt() {
        return this.lastModifiedAt;
    }

    /**
     * Set the lastModifiedAt property: When this model type was last updated.
     *
     * @param lastModifiedAt the lastModifiedAt value to set.
     * @return the AbstractAudited object itself.
     */
    public AbstractAudited setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
        return this;
    }
}
