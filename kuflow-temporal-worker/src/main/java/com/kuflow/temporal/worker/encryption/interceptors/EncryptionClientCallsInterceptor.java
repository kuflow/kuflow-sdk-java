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
import com.kuflow.temporal.worker.encryption.EncryptionStateHolder;
import io.temporal.client.WorkflowUpdateHandle;
import io.temporal.common.interceptors.Header;
import io.temporal.common.interceptors.WorkflowClientCallsInterceptor;
import io.temporal.common.interceptors.WorkflowClientCallsInterceptorBase;
import java.util.concurrent.TimeoutException;

public class EncryptionClientCallsInterceptor extends WorkflowClientCallsInterceptorBase {

    public EncryptionClientCallsInterceptor(WorkflowClientCallsInterceptor next) {
        super(next);
    }

    @Override
    public WorkflowStartOutput start(WorkflowStartInput input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArguments();

        EncryptionState encryptionState = EncryptionStateHolder.getCurrentEncryptionState();

        header = EncryptionUtils.addEncryptionEncoding(encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, arguments);

        return super.start(new WorkflowStartInput(input.getWorkflowId(), input.getWorkflowType(), header, arguments, input.getOptions()));
    }

    @Override
    public WorkflowSignalOutput signal(WorkflowSignalInput input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArguments();

        EncryptionState encryptionState = EncryptionStateHolder.getCurrentEncryptionState();

        header = EncryptionUtils.addEncryptionEncoding(encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, arguments);

        return super.signal(new WorkflowSignalInput(input.getWorkflowExecution(), input.getSignalName(), header, arguments));
    }

    @Override
    public WorkflowSignalWithStartOutput signalWithStart(WorkflowSignalWithStartInput input) {
        Header workflowStartHeader = input.getWorkflowStartInput().getHeader();
        Object[] workflowStartArguments = input.getWorkflowStartInput().getArguments();
        Object[] signalArguments = input.getSignalArguments();

        EncryptionState encryptionState = EncryptionStateHolder.getCurrentEncryptionState();

        workflowStartHeader = EncryptionUtils.addEncryptionEncoding(encryptionState, workflowStartHeader);
        workflowStartArguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, workflowStartArguments);
        signalArguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, signalArguments);

        return super.signalWithStart(
            new WorkflowSignalWithStartInput(
                new WorkflowStartInput(
                    input.getWorkflowStartInput().getWorkflowId(),
                    input.getWorkflowStartInput().getWorkflowType(),
                    workflowStartHeader,
                    workflowStartArguments,
                    input.getWorkflowStartInput().getOptions()
                ),
                input.getSignalName(),
                signalArguments
            )
        );
    }

    @Override
    public <R> WorkflowUpdateWithStartOutput<R> updateWithStart(WorkflowUpdateWithStartInput<R> input) {
        Header workflowStartInputHeader = input.getWorkflowStartInput().getHeader();
        Object[] workflowStartInputArguments = input.getWorkflowStartInput().getArguments();
        Header startUpdateInputHeader = input.getStartUpdateInput().getHeader();
        Object[] startUpdateInputArguments = input.getStartUpdateInput().getArguments();

        EncryptionState encryptionState = EncryptionStateHolder.getCurrentEncryptionState();

        workflowStartInputHeader = EncryptionUtils.addEncryptionEncoding(encryptionState, workflowStartInputHeader);
        workflowStartInputArguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, workflowStartInputArguments);

        startUpdateInputHeader = EncryptionUtils.addEncryptionEncoding(encryptionState, startUpdateInputHeader);
        startUpdateInputArguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, startUpdateInputArguments);

        return super.updateWithStart(
            new WorkflowUpdateWithStartInput<>(
                new WorkflowStartInput(
                    input.getWorkflowStartInput().getWorkflowId(),
                    input.getWorkflowStartInput().getWorkflowType(),
                    workflowStartInputHeader,
                    workflowStartInputArguments,
                    input.getWorkflowStartInput().getOptions()
                ),
                new StartUpdateInput<>(
                    input.getStartUpdateInput().getWorkflowExecution(),
                    input.getStartUpdateInput().getWorkflowType(),
                    input.getStartUpdateInput().getUpdateName(),
                    startUpdateInputHeader,
                    input.getStartUpdateInput().getUpdateId(),
                    startUpdateInputArguments,
                    input.getStartUpdateInput().getResultClass(),
                    input.getStartUpdateInput().getResultType(),
                    input.getStartUpdateInput().getFirstExecutionRunId(),
                    input.getStartUpdateInput().getWaitPolicy()
                )
            )
        );
    }

    @Override
    public <R> GetResultOutput<R> getResult(GetResultInput<R> input) throws TimeoutException {
        return super.getResult(input);
    }

    @Override
    public <R> GetResultAsyncOutput<R> getResultAsync(GetResultInput<R> input) {
        return super.getResultAsync(input);
    }

    @Override
    public <R> QueryOutput<R> query(QueryInput<R> input) {
        Header header = input.getHeader();
        Object[] arguments = input.getArguments();

        EncryptionState encryptionState = EncryptionStateHolder.getCurrentEncryptionState();

        header = EncryptionUtils.addEncryptionEncoding(encryptionState, header);
        arguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, arguments);

        return super.query(
            new QueryInput<>(
                input.getWorkflowExecution(),
                input.getQueryType(),
                header,
                arguments,
                input.getResultClass(),
                input.getResultType()
            )
        );
    }

    @Override
    public <R> WorkflowUpdateHandle<R> startUpdate(StartUpdateInput<R> input) {
        Header startUpdateInputHeader = input.getHeader();
        Object[] startUpdateInputArguments = input.getArguments();

        EncryptionState encryptionState = EncryptionStateHolder.getCurrentEncryptionState();

        startUpdateInputHeader = EncryptionUtils.addEncryptionEncoding(encryptionState, startUpdateInputHeader);
        startUpdateInputArguments = EncryptionUtils.markObjectsToBeEncrypted(encryptionState, startUpdateInputArguments);

        return super.startUpdate(
            new StartUpdateInput<>(
                input.getWorkflowExecution(),
                input.getWorkflowType(),
                input.getUpdateName(),
                startUpdateInputHeader,
                input.getUpdateId(),
                startUpdateInputArguments,
                input.getResultClass(),
                input.getResultType(),
                input.getFirstExecutionRunId(),
                input.getWaitPolicy()
            )
        );
    }

    @Override
    public <R> PollWorkflowUpdateOutput<R> pollWorkflowUpdate(PollWorkflowUpdateInput<R> input) {
        return super.pollWorkflowUpdate(input);
    }

    @Override
    public CancelOutput cancel(CancelInput input) {
        return super.cancel(input);
    }

    @Override
    public TerminateOutput terminate(TerminateInput input) {
        return super.terminate(input);
    }

    @Override
    public DescribeWorkflowOutput describe(DescribeWorkflowInput input) {
        return super.describe(input);
    }
}
