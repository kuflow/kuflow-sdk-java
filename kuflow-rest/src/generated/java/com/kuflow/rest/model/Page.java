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

/**
 * The Page model.
 */
@Fluent
public class Page {

    /*
     * Paged Model types.
     */
    @JsonProperty(value = "objectType")
    private PagedObjectType objectType;

    /*
     * The metadata property.
     */
    @JsonProperty(value = "metadata", required = true)
    private PageMetadata metadata;

    /**
     * Creates an instance of Page class.
     */
    public Page() {}

    /**
     * Get the objectType property: Paged Model types.
     *
     * @return the objectType value.
     */
    public PagedObjectType getObjectType() {
        return this.objectType;
    }

    /**
     * Set the objectType property: Paged Model types.
     *
     * @param objectType the objectType value to set.
     * @return the Page object itself.
     */
    public Page setObjectType(PagedObjectType objectType) {
        this.objectType = objectType;
        return this;
    }

    /**
     * Get the metadata property: The metadata property.
     *
     * @return the metadata value.
     */
    public PageMetadata getMetadata() {
        return this.metadata;
    }

    /**
     * Set the metadata property: The metadata property.
     *
     * @param metadata the metadata value to set.
     * @return the Page object itself.
     */
    public Page setMetadata(PageMetadata metadata) {
        this.metadata = metadata;
        return this;
    }
}
