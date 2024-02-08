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
 * Robot source type.
 */
@Fluent
public final class RobotSourceFile {

    /*
     * Robot ID.
     */
    @JsonProperty(value = "id", required = true)
    private UUID id;

    /*
     * Source file name.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /*
     * Source file content type.
     */
    @JsonProperty(value = "contentType", required = true)
    private String contentType;

    /*
     * Source file length.
     */
    @JsonProperty(value = "contentLength", required = true)
    private long contentLength;

    /*
     * Source file to check the integrity.
     */
    @JsonProperty(value = "contentHash", required = true)
    private String contentHash;

    /**
     * Creates an instance of RobotSourceFile class.
     */
    public RobotSourceFile() {}

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
     * @return the RobotSourceFile object itself.
     */
    public RobotSourceFile setId(UUID id) {
        this.id = id;
        return this;
    }

    /**
     * Get the name property: Source file name.
     *
     * @return the name value.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the name property: Source file name.
     *
     * @param name the name value to set.
     * @return the RobotSourceFile object itself.
     */
    public RobotSourceFile setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the contentType property: Source file content type.
     *
     * @return the contentType value.
     */
    public String getContentType() {
        return this.contentType;
    }

    /**
     * Set the contentType property: Source file content type.
     *
     * @param contentType the contentType value to set.
     * @return the RobotSourceFile object itself.
     */
    public RobotSourceFile setContentType(String contentType) {
        this.contentType = contentType;
        return this;
    }

    /**
     * Get the contentLength property: Source file length.
     *
     * @return the contentLength value.
     */
    public long getContentLength() {
        return this.contentLength;
    }

    /**
     * Set the contentLength property: Source file length.
     *
     * @param contentLength the contentLength value to set.
     * @return the RobotSourceFile object itself.
     */
    public RobotSourceFile setContentLength(long contentLength) {
        this.contentLength = contentLength;
        return this;
    }

    /**
     * Get the contentHash property: Source file to check the integrity.
     *
     * @return the contentHash value.
     */
    public String getContentHash() {
        return this.contentHash;
    }

    /**
     * Set the contentHash property: Source file to check the integrity.
     *
     * @param contentHash the contentHash value to set.
     * @return the RobotSourceFile object itself.
     */
    public RobotSourceFile setContentHash(String contentHash) {
        this.contentHash = contentHash;
        return this;
    }
}
