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
package com.kuflow.temporal.activity.email.model;

import java.util.Objects;
import org.springframework.core.io.Resource;

/**
 * Represents an email attachment with a filename and resource.
 */
public class EmailAttachment {

    private String filename;

    private Resource resource;

    /**
     * Gets the filename for the attachment.
     *
     * @return the filename
     */
    public String getFilename() {
        return this.filename;
    }

    /**
     * Sets the filename for the attachment.
     *
     * @param filename the filename to set
     * @return this EmailAttachment for method chaining
     */
    public EmailAttachment setFilename(String filename) {
        this.filename = filename;
        return this;
    }

    /**
     * Gets the resource containing the attachment data.
     *
     * @return the resource
     */
    public Resource getResource() {
        return this.resource;
    }

    /**
     * Sets the resource containing the attachment data.
     *
     * @param resource the resource to set
     * @return this EmailAttachment for method chaining
     */
    public EmailAttachment setResource(Resource resource) {
        this.resource = resource;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmailAttachment that = (EmailAttachment) o;
        return Objects.equals(this.filename, that.filename) && Objects.equals(this.resource, that.resource);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.filename, this.resource);
    }

    @Override
    public String toString() {
        return "EmailAttachment{" + "filename='" + this.filename + '\'' + ", resource=" + this.resource + '}';
    }
}
