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

/** The ProcessUserActionDocumentUploadParams model. */
@Fluent
public final class ProcessUserActionDocumentUploadParams {

    /*
     * The userActionValueId property.
     */
    @JsonProperty(value = "userActionValueId", required = true)
    private UUID userActionValueId;

    /** Creates an instance of ProcessUserActionDocumentUploadParams class. */
    public ProcessUserActionDocumentUploadParams() {}

    /**
     * Get the userActionValueId property: The userActionValueId property.
     *
     * @return the userActionValueId value.
     */
    public UUID getUserActionValueId() {
        return this.userActionValueId;
    }

    /**
     * Set the userActionValueId property: The userActionValueId property.
     *
     * @param userActionValueId the userActionValueId value to set.
     * @return the ProcessUserActionDocumentUploadParams object itself.
     */
    public ProcessUserActionDocumentUploadParams setUserActionValueId(UUID userActionValueId) {
        this.userActionValueId = userActionValueId;
        return this;
    }
}
