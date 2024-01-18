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

/** The ProcessDefinitionSummary model. */
@Fluent
public final class ProcessDefinitionSummary {

    /*
     * The id property.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * The version property.
     */
    @JsonProperty(value = "version")
    private UUID version;

    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /** Creates an instance of ProcessDefinitionSummary class. */
    public ProcessDefinitionSummary() {}

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
     * @return the ProcessDefinitionSummary object itself.
     */
    public ProcessDefinitionSummary setId(UUID id) {
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
     * @return the ProcessDefinitionSummary object itself.
     */
    public ProcessDefinitionSummary setVersion(UUID version) {
        this.version = version;
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

    /**
     * Set the name property: The name property.
     *
     * @param name the name value to set.
     * @return the ProcessDefinitionSummary object itself.
     */
    public ProcessDefinitionSummary setName(String name) {
        this.name = name;
        return this;
    }
}
