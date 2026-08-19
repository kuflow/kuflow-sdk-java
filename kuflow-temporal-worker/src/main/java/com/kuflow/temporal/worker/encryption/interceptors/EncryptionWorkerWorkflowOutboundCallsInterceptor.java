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
import io.temporal.common.interceptors.Header;
import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptor;
import io.temporal.common.interceptors.WorkflowOutboundCallsInterceptorBase;
import java.util.Map;

/**
 * Marks the payloads sent from workflow code so that EncryptionPayloadConverter and EncryptionPayloadCodec encrypt them.
 *
 * <p>Overridden, because they carry workflow payloads: executeActivity, executeLocalActivity, executeChildWorkflow,
 * executeNexusOperation, continueAsNew and signalExternalWorkflow.
 *
 * <p>Not overridden: the remaining calls carry no payload (sleep, await, newTimer, newRandom, randomUUID, getVersion,
 * currentTimeMillis, cancelWorkflow, the handler registrations...), with three deliberate exceptions that are NOT
 * encrypted today:
 *
 * <ul>
 *   <li>sideEffect and mutableSideEffect: their result is written to and read back from the workflow history using the
 *       result type declared by the caller, and EncryptionWrapper is only unwrapped when serializing, never restored
 *       when deserializing, so marking the value would break the replay.
 *   <li>upsertMemo: memos would be encryptable, but they do not go through this marking mechanism.
 *   <li>upsertSearchAttributes and upsertTypedSearchAttributes: they must stay in clear text, the server indexes them.
 * </ul>
 *
 * <p>Keep sensitive computations inside an activity, whose arguments and result do go through the encryption path.
 */
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

        header = EncryptionUtils.addEncryptionEncoding(this.encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(this.encryptionState, arguments);

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

        header = EncryptionUtils.addEncryptionEncoding(this.encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(this.encryptionState, arguments);

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

        header = EncryptionUtils.addEncryptionEncoding(this.encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(this.encryptionState, arguments);

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

        headers = EncryptionUtils.addEncryptionEncoding(this.encryptionState, headers);
        argument = EncryptionWrapper.of(this.encryptionState, argument);

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

        header = EncryptionUtils.addEncryptionEncoding(this.encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(this.encryptionState, arguments);

        super.continueAsNew(new ContinueAsNewInput(input.getWorkflowType(), input.getOptions(), arguments, header));
    }

    @Override
    public SignalExternalOutput signalExternalWorkflow(SignalExternalInput input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArgs();

        header = EncryptionUtils.addEncryptionEncoding(this.encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(this.encryptionState, arguments);

        return super.signalExternalWorkflow(new SignalExternalInput(input.getExecution(), input.getSignalName(), header, arguments));
    }
}
