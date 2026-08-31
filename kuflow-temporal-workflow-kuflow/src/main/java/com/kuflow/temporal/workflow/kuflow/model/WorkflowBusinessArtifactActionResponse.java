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

package com.kuflow.temporal.workflow.kuflow.model;

/**
 * Result returned by the workflow that handles a business artifact action. Carries an optional
 * human-readable message and, depending on the action definition type that triggered it, exactly
 * one of the type-specific payloads ({@link WorkflowBusinessArtifactActionResponseDownloadable}).
 */
public class WorkflowBusinessArtifactActionResponse {

    /**
     * Optional human-readable message describing the outcome of the action. Surfaced to the
     * end user that triggered it.
     */
    private String message;

    /**
     * Payload populated when the action was of type {@code DOWNLOADABLE}; {@code null} otherwise.
     */
    private WorkflowBusinessArtifactActionResponseDownloadable downloadable;

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public WorkflowBusinessArtifactActionResponseDownloadable getDownloadable() {
        return this.downloadable;
    }

    public void setDownloadable(WorkflowBusinessArtifactActionResponseDownloadable downloadable) {
        this.downloadable = downloadable;
    }
}
