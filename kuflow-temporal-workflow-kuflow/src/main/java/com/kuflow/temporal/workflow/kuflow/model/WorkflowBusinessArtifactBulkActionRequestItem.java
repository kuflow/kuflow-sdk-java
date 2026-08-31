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

import java.util.UUID;

/**
 * One artifact of the materialized selection of a BULK batch action: the business artifact and the action value the
 * bulk invocation created for it. With the pair, the workflow can retrieve the validated input
 * ({@code KuFlowRestClient.getBusinessArtifactOperations().retrieveBusinessArtifactAction(businessArtifactId,
 * businessArtifactActionValueId)}) and download its per-artifact document copies
 * ({@code downloadBusinessArtifactDocument(businessArtifactId, documentUri)}).
 */
public class WorkflowBusinessArtifactBulkActionRequestItem {

    /**
     * The unique identifier of the business artifact.
     */
    private UUID businessArtifactId;

    /**
     * The unique identifier of the action value the bulk invocation created for this artifact. All the action values
     * of the selection share this workflow's instance id.
     */
    private UUID businessArtifactActionValueId;

    public UUID getBusinessArtifactId() {
        return this.businessArtifactId;
    }

    public void setBusinessArtifactId(UUID businessArtifactId) {
        this.businessArtifactId = businessArtifactId;
    }

    public UUID getBusinessArtifactActionValueId() {
        return this.businessArtifactActionValueId;
    }

    public void setBusinessArtifactActionValueId(UUID businessArtifactActionValueId) {
        this.businessArtifactActionValueId = businessArtifactActionValueId;
    }
}
