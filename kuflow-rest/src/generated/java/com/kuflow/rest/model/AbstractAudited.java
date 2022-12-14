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

import com.azure.core.annotation.Immutable;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import java.time.OffsetDateTime;
import java.util.UUID;

/** The AbstractAudited model. */
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "objectType",
        defaultImpl = AbstractAudited.class)
@JsonTypeName("AbstractAudited")
@JsonSubTypes({
    @JsonSubTypes.Type(name = "AUTHENTICATION", value = Authentication.class),
    @JsonSubTypes.Type(name = "PROCESS", value = Process.class),
    @JsonSubTypes.Type(name = "TASK", value = Task.class)
})
@Immutable
public class AbstractAudited {
    /*
     * Who create this model.
     */
    @JsonProperty(value = "createdBy", access = JsonProperty.Access.WRITE_ONLY)
    private UUID createdBy;

    /*
     * When this model was created.
     */
    @JsonProperty(value = "createdAt", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime createdAt;

    /*
     * Who was last update this model.
     */
    @JsonProperty(value = "lastModifiedBy", access = JsonProperty.Access.WRITE_ONLY)
    private UUID lastModifiedBy;

    /*
     * When this model type was last updated.
     */
    @JsonProperty(value = "lastModifiedAt", access = JsonProperty.Access.WRITE_ONLY)
    private OffsetDateTime lastModifiedAt;

    /** Creates an instance of AbstractAudited class. */
    public AbstractAudited() {}

    /**
     * Get the createdBy property: Who create this model.
     *
     * @return the createdBy value.
     */
    public UUID getCreatedBy() {
        return this.createdBy;
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
     * Get the lastModifiedBy property: Who was last update this model.
     *
     * @return the lastModifiedBy value.
     */
    public UUID getLastModifiedBy() {
        return this.lastModifiedBy;
    }

    /**
     * Get the lastModifiedAt property: When this model type was last updated.
     *
     * @return the lastModifiedAt value.
     */
    public OffsetDateTime getLastModifiedAt() {
        return this.lastModifiedAt;
    }
}