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
 * Type-specific result for actions of type {@code DOWNLOADABLE}. The user's workflow uploads the generated document
 * with the unified {@code uploadDocument} operation, using the owning process as {@code targetUri}, and returns its
 * URI here so the launcher links it to the action value. This is the only completion channel of the action.
 */
public class WorkflowProcessUserActionResponseDownloadable {

    /**
     * URI of the document previously uploaded with the unified {@code uploadDocument} operation.
     * The launcher uses it to link the document to the process action.
     */
    private String documentUri;

    public String getDocumentUri() {
        return this.documentUri;
    }

    public void setDocumentUri(String documentUri) {
        this.documentUri = documentUri;
    }
}
