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
import java.util.List;

/**
 * The ProcessPage model.
 */
@Fluent
public final class ProcessPage extends Page {

    /*
     * The content property.
     */
    @JsonProperty(value = "content", required = true)
    private List<ProcessPageItem> content;

    /**
     * Creates an instance of ProcessPage class.
     */
    public ProcessPage() {}

    /**
     * Get the content property: The content property.
     *
     * @return the content value.
     */
    public List<ProcessPageItem> getContent() {
        return this.content;
    }

    /**
     * Set the content property: The content property.
     *
     * @param content the content value to set.
     * @return the ProcessPage object itself.
     */
    public ProcessPage setContent(List<ProcessPageItem> content) {
        this.content = content;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPage setObjectType(PagedObjectType objectType) {
        super.setObjectType(objectType);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ProcessPage setMetadata(PageMetadata metadata) {
        super.setMetadata(metadata);
        return this;
    }
}
