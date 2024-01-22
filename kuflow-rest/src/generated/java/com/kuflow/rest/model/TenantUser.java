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
 * The TenantUser model.
 */
@Fluent
public final class TenantUser extends AbstractAudited {

    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * The metadata property.
     */
    @JsonProperty(value = "metadata")
    private TenantUserMetadata metadata;

    /*
     * The principal property.
     */
    @JsonProperty(value = "principal", required = true)
    private Principal principal;

    /**
     * Creates an instance of TenantUser class.
     */
    public TenantUser() {}

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
     * @return the TenantUser object itself.
     */
    public TenantUser setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the metadata property: The metadata property.
     *
     * @return the metadata value.
     */
    public TenantUserMetadata getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: The metadata property.
     *
     * @param metadata the metadata value to set.
     * @return the TenantUser object itself.
     */
    public TenantUser setMetadata(TenantUserMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the principal property: The principal property.
     *
     * @return the principal value.
     */
    public Principal getPrincipal() {
        return this.principal;
    }

    /**
     * Set the principal property: The principal property.
     *
     * @param principal the principal value to set.
     * @return the TenantUser object itself.
     */
    public TenantUser setPrincipal(Principal principal) {
        this.principal = principal;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TenantUser setObjectType(AuditedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TenantUser setCreatedBy(UUID createdBy) {
        super.setCreatedBy(createdBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TenantUser setCreatedAt(OffsetDateTime createdAt) {
        super.setCreatedAt(createdAt);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TenantUser setLastModifiedBy(UUID lastModifiedBy) {
        super.setLastModifiedBy(lastModifiedBy);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public TenantUser setLastModifiedAt(OffsetDateTime lastModifiedAt) {
        super.setLastModifiedAt(lastModifiedAt);
        return this;
    }
}
