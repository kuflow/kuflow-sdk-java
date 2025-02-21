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

import com.kuflow.temporal.worker.encryption.EncryptionConstant;
import com.kuflow.temporal.worker.encryption.EncryptionState;
import com.kuflow.temporal.worker.encryption.converter.EncryptionWrapper;
import io.temporal.common.interceptors.Header;
import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptor;
import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptorBase;
import java.util.Map;

public class EncryptionWorkerWorkflowOutboundCallsInterceptor extends WorkflowOutboundCallsInterceptorBase {

    private final EncryptionState encryptionState;

    public EncryptionWorkerWorkflowOutboundCallsInterceptor(WorkflowOutboundCallsInterceptor next, EncryptionState encryptionState) {
        super(next);
        this.encryptionState = encryptionState;
    }

    @Override
    public <R> ActivityOutput<R> executeActivity(ActivityInput<R> input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArgs();

        if (this.encryptionState.isEncryptionNeeded()) {
            header = EncryptionUtils.addEncryptionEncoding(header);
            arguments = EncryptionUtils.markObjectsToBeEncrypted(arguments);
        }

        return super.executeActivity(
            new ActivityInput<>(
                input.getActivityName(),
                input.getResultClass(),
                input.getResultType(),
                arguments,
                input.getOptions(),
                header
            )
        );
    }

    @Override
    public <R> LocalActivityOutput<R> executeLocalActivity(LocalActivityInput<R> input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArgs();

        if (this.encryptionState.isEncryptionNeeded()) {
            header = EncryptionUtils.addEncryptionEncoding(header);
            arguments = EncryptionUtils.markObjectsToBeEncrypted(arguments);
        }

        return super.executeLocalActivity(
            new LocalActivityInput<>(
                input.getActivityName(),
                input.getResultClass(),
                input.getResultType(),
                arguments,
                input.getOptions(),
                header
            )
        );
    }

    @Override
    public <R> ChildWorkflowOutput<R> executeChildWorkflow(ChildWorkflowInput<R> input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArgs();

        if (this.encryptionState.isEncryptionNeeded()) {
            header = EncryptionUtils.addEncryptionEncoding(header);
            arguments = EncryptionUtils.markObjectsToBeEncrypted(arguments);
        }

        return super.executeChildWorkflow(
            new ChildWorkflowInput<>(
                input.getWorkflowId(),
                input.getWorkflowType(),
                input.getResultClass(),
                input.getResultType(),
                arguments,
                input.getOptions(),
                header
            )
        );
    }

    @Override
    public <R> ExecuteNexusOperationOutput<R> executeNexusOperation(ExecuteNexusOperationInput<R> input) {
        Map<String, String> headers = input.getHeaders();
        Object argument = input.getArg();

        if (this.encryptionState.isEncryptionNeeded()) {
            headers.put(EncryptionConstant.METADATA_KUFLOW_ENCODING_KEY, EncryptionConstant.METADATA_KUFLOW_ENCODING_ENCRYPTED_NAME);
            argument = EncryptionWrapper.of(argument);
        }

        return super.executeNexusOperation(
            new WorkflowOutboundCallsInterceptor.ExecuteNexusOperationInput<>(
                input.getEndpoint(),
                input.getService(),
                input.getOperation(),
                input.getResultClass(),
                input.getResultType(),
                argument,
                input.getOptions(),
                headers
            )
        );
    }

    @Override
    public void continueAsNew(ContinueAsNewInput input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArgs();

        if (this.encryptionState.isEncryptionNeeded()) {
            header = EncryptionUtils.addEncryptionEncoding(header);
            arguments = EncryptionUtils.markObjectsToBeEncrypted(arguments);
        }

        super.continueAsNew(new ContinueAsNewInput(input.getWorkflowType(), input.getOptions(), arguments, header));
    }

    @Override
    public SignalExternalOutput signalExternalWorkflow(SignalExternalInput input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArgs();

        if (this.encryptionState.isEncryptionNeeded()) {
            header = EncryptionUtils.addEncryptionEncoding(header);
            arguments = EncryptionUtils.markObjectsToBeEncrypted(arguments);
        }

        return super.signalExternalWorkflow(new SignalExternalInput(input.getExecution(), input.getSignalName(), header, arguments));
    }
}
