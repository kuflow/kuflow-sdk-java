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

/**
 * The TaskElementValueDocumentItem model.
 */
@Fluent
public final class TaskElementValueDocumentItem {

    /*
     * The id property.
     */
    @JsonProperty(value = "id")
    private UUID id;

    /*
     * The uri property.
     */
    @JsonProperty(value = "uri")
    private String uri;

    /*
     * The name property.
     */
    @JsonProperty(value = "name")
    private String name;

    /*
     * The contentPath property.
     */
    @JsonProperty(value = "contentPath")
    private String contentPath;

    /*
     * The contentType property.
     */
    @JsonProperty(value = "contentType")
    private String contentType;

    /*
     * The contentLength property.
     */
    @JsonProperty(value = "contentLength")
    private Long contentLength;

    /**
     * Creates an instance of TaskElementValueDocumentItem class.
     */
    public TaskElementValueDocumentItem() {}

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
     * @return the TaskElementValueDocumentItem object itself.
     */
    public TaskElementValueDocumentItem setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the uri property: The uri property.
     *
     * @return the uri value.
     */
    public String getUri() {
        return this.uri;
    }

    /**
     * Set the uri property: The uri property.
     *
     * @param uri the uri value to set.
     * @return the TaskElementValueDocumentItem object itself.
     */
    public TaskElementValueDocumentItem setUri(String uri) {
        this.uri = uri;
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
     * @return the TaskElementValueDocumentItem object itself.
     */
    public TaskElementValueDocumentItem setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the contentPath property: The contentPath property.
     *
     * @return the contentPath value.
     */
    public String getContentPath() {
        return this.contentPath;
    }

    /**
     * Set the contentPath property: The contentPath property.
     *
     * @param contentPath the contentPath value to set.
     * @return the TaskElementValueDocumentItem object itself.
     */
    public TaskElementValueDocumentItem setContentPath(String contentPath) {
        this.contentPath = contentPath;
        return this;
    }

    /**
     * Get the contentType property: The contentType property.
     *
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: The contentType property.
     *
     * @param contentType the contentType value to set.
     * @return the TaskElementValueDocumentItem object itself.
     */
    public TaskElementValueDocumentItem setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the contentLength property: The contentLength property.
     *
     * @return the contentLength value.
     */
    public Long getContentLength() {
        return this.contentLength;
    }

    /**
     * Set the contentLength property: The contentLength property.
     *
     * @param contentLength the contentLength value to set.
     * @return the TaskElementValueDocumentItem object itself.
     */
    public TaskElementValueDocumentItem setContentLength(Long contentLength) {
        this.contentLength = contentLength;
        return this;
    }
}
