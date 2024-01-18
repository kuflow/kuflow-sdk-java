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

/** The TaskDeleteElementValueDocumentCommand model. */
@Fluent
public final class TaskDeleteElementValueDocumentCommand {

    /*
     * Document ID to delete.
     */
    @JsonProperty(value = "documentId", required = true)
    private UUID documentId;

    /** Creates an instance of TaskDeleteElementValueDocumentCommand class. */
    public TaskDeleteElementValueDocumentCommand() {}

    /**
     * Get the documentId property: Document ID to delete.
     *
     * @return the documentId value.
     */
    public UUID getDocumentId() {
        return this.documentId;
    }

    /**
     * Set the documentId property: Document ID to delete.
     *
     * @param documentId the documentId value to set.
     * @return the TaskDeleteElementValueDocumentCommand object itself.
     */
    public TaskDeleteElementValueDocumentCommand setDocumentId(UUID documentId) {
        this.documentId = documentId;
        return this;
    }
}
