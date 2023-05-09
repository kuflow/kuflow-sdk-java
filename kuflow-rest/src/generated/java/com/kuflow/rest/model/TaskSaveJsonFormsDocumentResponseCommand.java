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

/** The TaskSaveJsonFormsDocumentResponseCommand model. */
@Fluent
public final class TaskSaveJsonFormsDocumentResponseCommand {
    /*
     * JSON value representing the uploaded file.
     *
     * Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`
     *
     */
    @JsonProperty(value = "value", required = true)
    private String value;

    /** Creates an instance of TaskSaveJsonFormsDocumentResponseCommand class. */
    public TaskSaveJsonFormsDocumentResponseCommand() {}

    /**
     * Get the value property: JSON value representing the uploaded file.
     *
     * <p>Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`.
     *
     * @return the value value.
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Set the value property: JSON value representing the uploaded file.
     *
     * <p>Example: `kuflow-file:uri=xxx-yyy-zzz;type=application/json;size=500;name=file.json;`.
     *
     * @param value the value value to set.
     * @return the TaskSaveJsonFormsDocumentResponseCommand object itself.
     */
    public TaskSaveJsonFormsDocumentResponseCommand setValue(String value) {
        this.value = value;
        return this;
    }
}
