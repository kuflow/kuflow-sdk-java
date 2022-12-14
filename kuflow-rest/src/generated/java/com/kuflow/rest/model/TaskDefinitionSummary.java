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
import java.util.UUID;

/** In creation task, one of 'id, version or code' is mandatory. */
@Fluent
public final class TaskDefinitionSummary {
    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * The version property.
     */
    @JsonProperty(value = "version")
    private UUID version;

    /*
     * The code property.
     */
    @JsonProperty(value = "code")
    private String code;

    /*
     * The name property.
     */
    @JsonProperty(value = "name", access = JsonProperty.Access.WRITE_ONLY)
    private String name;

    /** Creates an instance of TaskDefinitionSummary class. */
    public TaskDefinitionSummary() {}

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
     * @return the TaskDefinitionSummary object itself.
     */
    public TaskDefinitionSummary setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the version property: The version property.
     *
     * @return the version value.
     */
    public UUID getVersion() {
        return this.version;
    }

    /**
     * Set the version property: The version property.
     *
     * @param version the version value to set.
     * @return the TaskDefinitionSummary object itself.
     */
    public TaskDefinitionSummary setVersion(UUID version) {
        this.version = version;
        return this;
    }

    /**
     * Get the code property: The code property.
     *
     * @return the code value.
     */
    public String getCode() {
        return this.code;
    }

    /**
     * Set the code property: The code property.
     *
     * @param code the code value to set.
     * @return the TaskDefinitionSummary object itself.
     */
    public TaskDefinitionSummary setCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the name property: The name property.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }
}
