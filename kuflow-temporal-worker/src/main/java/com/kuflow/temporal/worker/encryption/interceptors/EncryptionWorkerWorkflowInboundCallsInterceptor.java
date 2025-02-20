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
package com.kuflow.temporal.worker.encryption.interceptors;

import com.kuflow.temporal.worker.encryption.EncryptionState;
import com.kuflow.temporal.worker.encryption.converter.EncryptionWrapper;
import io.temporal.common.interceptors.WorkflowInboundCallsInterceptor;
import io.temporal.common.interceptors.WorkflowInboundCallsInterceptorBase;
import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptor;

public class EncryptionWorkerWorkflowInboundCallsInterceptor extends WorkflowInboundCallsInterceptorBase {

    private final EncryptionState encryptionState = EncryptionState.of(false);

    public EncryptionWorkerWorkflowInboundCallsInterceptor(WorkflowInboundCallsInterceptor next) {
        super(next);
    }

    @Override
    public void init(WorkflowOutboundCallsInterceptor next) {
        super.init(new EncryptionWorkerWorkflowOutboundCallsInterceptor(next, this.encryptionState));
    }

    @Override
    public WorkflowOutput execute(WorkflowInput input) {
        boolean needEncryption = EncryptionUtils.isEncryptionRequired(input.getHeader());

        this.encryptionState.setEncryptionNeeded(needEncryption);

        WorkflowOutput output = super.execute(input);

        if (this.encryptionState.isEncryptionNeeded()) {
            output = new WorkflowOutput(EncryptionWrapper.of(output.getResult()));
        }

        return output;
    }

    @Override
    public QueryOutput handleQuery(QueryInput input) {
        QueryOutput output = super.handleQuery(input);

        if (this.encryptionState.isEncryptionNeeded()) {
            output = new QueryOutput(EncryptionWrapper.of(output.getResult()));
        }

        return output;
    }

    @Override
    public UpdateOutput executeUpdate(UpdateInput input) {
        UpdateOutput output = super.executeUpdate(input);

        if (this.encryptionState.isEncryptionNeeded()) {
            output = new UpdateOutput(EncryptionWrapper.of(output.getResult()));
        }

        return output;
    }
}
