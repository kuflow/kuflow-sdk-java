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
package com.kuflow.temporal.activity.kuflow;

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.FindProcessesOptions;
import com.kuflow.rest.model.FindTasksOptions;
import com.kuflow.rest.model.Principal;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessChangeInitiatorCommand;
import com.kuflow.rest.model.ProcessDeleteElementCommand;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.ProcessSaveElementCommand;
import com.kuflow.rest.model.Task;
import com.kuflow.rest.model.TaskAssignCommand;
import com.kuflow.rest.model.TaskDeleteElementCommand;
import com.kuflow.rest.model.TaskDeleteElementValueDocumentCommand;
import com.kuflow.rest.model.TaskPage;
import com.kuflow.rest.model.TaskSaveElementCommand;
import com.kuflow.rest.operation.PrincipalOperations;
import com.kuflow.rest.operation.ProcessOperations;
import com.kuflow.rest.operation.TaskOperations;
import com.kuflow.temporal.activity.kuflow.model.AppendTaskLogRequest;
import com.kuflow.temporal.activity.kuflow.model.AppendTaskLogResponse;
import com.kuflow.temporal.activity.kuflow.model.AssignTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.AssignTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.ChangeProcessInitiatorRequest;
import com.kuflow.temporal.activity.kuflow.model.ChangeProcessInitiatorResponse;
import com.kuflow.temporal.activity.kuflow.model.ClaimTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.ClaimTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.CompleteProcessRequest;
import com.kuflow.temporal.activity.kuflow.model.CompleteProcessResponse;
import com.kuflow.temporal.activity.kuflow.model.CompleteTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.CompleteTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.CreateTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.CreateTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.DeleteProcessElementRequest;
import com.kuflow.temporal.activity.kuflow.model.DeleteProcessElementResponse;
import com.kuflow.temporal.activity.kuflow.model.DeleteTaskElementRequest;
import com.kuflow.temporal.activity.kuflow.model.DeleteTaskElementResponse;
import com.kuflow.temporal.activity.kuflow.model.DeleteTaskElementValueDocumentRequest;
import com.kuflow.temporal.activity.kuflow.model.DeleteTaskElementValueDocumentResponse;
import com.kuflow.temporal.activity.kuflow.model.FindProcessesRequest;
import com.kuflow.temporal.activity.kuflow.model.FindProcessesResponse;
import com.kuflow.temporal.activity.kuflow.model.FindTaskRequestModel;
import com.kuflow.temporal.activity.kuflow.model.FindTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.RetrievePrincipalRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrievePrincipalResponse;
import com.kuflow.temporal.activity.kuflow.model.RetrieveProcessRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrieveProcessResponse;
import com.kuflow.temporal.activity.kuflow.model.RetrieveTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrieveTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessElementRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessElementResponse;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskElementRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskElementResponse;
import com.kuflow.temporal.common.util.TemporalUtils;
import io.temporal.activity.Activity;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.failure.ApplicationFailure;
import javax.annotation.Nonnull;

public class KuFlowActivitiesImpl implements KuFlowActivities {

    private final PrincipalOperations principalOperations;

    private final ProcessOperations processOperations;

    private final TaskOperations taskOperations;

    public KuFlowActivitiesImpl(KuFlowRestClient kuFlowRestClient) {
        this.principalOperations = kuFlowRestClient.getPrincipalOperations();
        this.processOperations = kuFlowRestClient.getProcessOperations();
        this.taskOperations = kuFlowRestClient.getTaskOperations();
    }

    @Nonnull
    @Override
    public RetrievePrincipalResponse retrievePrincipal(@Nonnull RetrievePrincipalRequest request) {
        this.validateRetrievePrincipalRequest(request);

        Principal principal = this.principalOperations.retrievePrincipal(request.getPrincipalId());

        RetrievePrincipalResponse response = new RetrievePrincipalResponse();
        response.setPrincipal(principal);

        return response;
    }

    @Nonnull
    @Override
    public FindProcessesResponse findProcesses(FindProcessesRequest request) {
        FindProcessesOptions options = new FindProcessesOptions()
            .setPage(request.getPage())
            .setSize(request.getSize())
            .setSorts(request.getSorts());

        ProcessPage processes = this.processOperations.findProcesses(options);

        FindProcessesResponse response = new FindProcessesResponse();
        response.setProcesses(processes);

        return response;
    }

    @Nonnull
    @Override
    public RetrieveProcessResponse retrieveProcess(@Nonnull RetrieveProcessRequest request) {
        this.validateRetrieveProcessRequest(request);

        Process process = this.processOperations.retrieveProcess(request.getProcessId());

        RetrieveProcessResponse response = new RetrieveProcessResponse();
        response.setProcess(process);

        return response;
    }

    @Nonnull
    @Override
    public SaveProcessElementResponse saveProcessElement(@Nonnull SaveProcessElementRequest request) {
        this.validateSaveProcessElementRequest(request);

        ProcessSaveElementCommand command = new ProcessSaveElementCommand()
            .setElementDefinitionCode(request.getElementDefinitionCode())
            .setElementValues(request.getElementValues());

        Process process = this.processOperations.actionsProcessSaveElement(request.getProcessId(), command);

        SaveProcessElementResponse response = new SaveProcessElementResponse();
        response.setProcess(process);

        return response;
    }

    @Nonnull
    @Override
    public DeleteProcessElementResponse deleteProcessElement(@Nonnull DeleteProcessElementRequest request) {
        ProcessDeleteElementCommand command = new ProcessDeleteElementCommand()
            .setElementDefinitionCode(request.getElementDefinitionCode());

        Process process = this.processOperations.actionsProcessDeleteElement(request.getProcessId(), command);

        DeleteProcessElementResponse response = new DeleteProcessElementResponse();
        response.setProcess(process);

        return response;
    }

    @Nonnull
    @Override
    public CompleteProcessResponse completeProcess(@Nonnull CompleteProcessRequest request) {
        this.validateCompleteProcessRequest(request);

        Process process = this.processOperations.actionsProcessComplete(request.getProcessId());

        CompleteProcessResponse response = new CompleteProcessResponse();
        response.setProcess(process);

        return response;
    }

    @Nonnull
    @Override
    public ChangeProcessInitiatorResponse changeProcessInitiator(@Nonnull ChangeProcessInitiatorRequest request) {
        this.validateChangeProcessInitiatorRequest(request);

        ProcessChangeInitiatorCommand command = new ProcessChangeInitiatorCommand()
            .setPrincipalId(request.getPrincipalId())
            .setEmail(request.getEmail());

        Process process = this.processOperations.actionsProcessChangeInitiator(request.getProcessId(), command);

        ChangeProcessInitiatorResponse response = new ChangeProcessInitiatorResponse();
        response.setProcess(process);

        return response;
    }

    @Nonnull
    @Override
    public FindTaskResponse findTasks(FindTaskRequestModel request) {
        FindTasksOptions options = new FindTasksOptions()
            .setSize(request.getSize())
            .setPage(request.getPage())
            .setSorts(request.getSort())
            .setProcessIds(request.getProcessIds())
            .setStates(request.getStates())
            .setTaskDefinitionCodes(request.getTaskDefinitionCodes());

        TaskPage tasks = this.taskOperations.findTasks(options);

        FindTaskResponse response = new FindTaskResponse();
        response.setTasks(tasks);

        return response;
    }

    @Nonnull
    @Override
    public RetrieveTaskResponse retrieveTask(@Nonnull RetrieveTaskRequest request) {
        this.validateRetrieveTaskRequest(request);

        Task task = this.taskOperations.retrieveTask(request.getTaskId());

        RetrieveTaskResponse response = new RetrieveTaskResponse();
        response.setTask(task);

        return response;
    }

    @Nonnull
    @Override
    public CreateTaskResponse createTask(@Nonnull CreateTaskRequest request) {
        this.validateTaskRequest(request);

        Task task = this.taskOperations.createTask(request.getTask());

        CreateTaskResponse response = new CreateTaskResponse();
        response.setTask(task);

        return response;
    }

    @Override
    public void createTaskAndWaitFinished(@Nonnull CreateTaskRequest request) {
        this.validateTaskRequest(request);

        ActivityExecutionContext context = Activity.getExecutionContext();
        String activityToken = TemporalUtils.getTemporalTokenAsString(context);

        this.taskOperations.createTask(request.getTask(), activityToken);

        context.doNotCompleteOnReturn();
    }

    @Nonnull
    @Override
    public CompleteTaskResponse completeTask(@Nonnull CompleteTaskRequest request) {
        this.validateTaskCompleteRequest(request);

        Task task = this.taskOperations.actionsTaskComplete(request.getTaskId());

        CompleteTaskResponse response = new CompleteTaskResponse();
        response.setTask(task);

        return response;
    }

    @Nonnull
    @Override
    public ClaimTaskResponse claimTask(@Nonnull ClaimTaskRequest request) {
        this.validateTaskClaimRequest(request);

        Task task = this.taskOperations.actionsTaskClaim(request.getTaskId());

        ClaimTaskResponse response = new ClaimTaskResponse();
        response.setTask(task);

        return response;
    }

    @Nonnull
    @Override
    public AssignTaskResponse assignTask(@Nonnull AssignTaskRequest request) {
        this.validateTaskAssignRequest(request);

        TaskAssignCommand command = new TaskAssignCommand().setEmail(request.getEmail()).setPrincipalId(request.getPrincipalId());

        Task task = this.taskOperations.actionsTaskAssign(request.getTaskId(), command);

        AssignTaskResponse response = new AssignTaskResponse();
        response.setTask(task);

        return response;
    }

    @Nonnull
    @Override
    public SaveTaskElementResponse saveTaskElement(@Nonnull SaveTaskElementRequest request) {
        this.validateSaveTaskElementRequest(request);

        TaskSaveElementCommand command = new TaskSaveElementCommand()
            .setElementDefinitionCode(request.getElementDefinitionCode())
            .setElementValues(request.getElementValues());

        Task task = this.taskOperations.actionsTaskSaveElement(request.getTaskId(), command);

        SaveTaskElementResponse response = new SaveTaskElementResponse();
        response.setTask(task);

        return response;
    }

    @Override
    public DeleteTaskElementResponse deleteTaskElement(@Nonnull DeleteTaskElementRequest request) {
        this.validateDeleteTaskElementRequest(request);

        TaskDeleteElementCommand command = new TaskDeleteElementCommand().setElementDefinitionCode(request.getElementDefinitionCode());

        Task task = this.taskOperations.actionsTaskDeleteElement(request.getTaskId(), command);

        DeleteTaskElementResponse response = new DeleteTaskElementResponse();
        response.setTask(task);

        return response;
    }

    @Override
    public DeleteTaskElementValueDocumentResponse deleteTaskElementValueDocument(@Nonnull DeleteTaskElementValueDocumentRequest request) {
        this.validateDeleteTaskElementValueDocumentRequest(request);

        TaskDeleteElementValueDocumentCommand command = new TaskDeleteElementValueDocumentCommand().setDocumentId(request.getDocumentId());

        Task task = this.taskOperations.actionsTaskDeleteElementValueDocument(request.getTaskId(), command);

        DeleteTaskElementValueDocumentResponse response = new DeleteTaskElementValueDocumentResponse();
        response.setTask(task);

        return response;
    }

    @Nonnull
    @Override
    public AppendTaskLogResponse appendTaskLog(@Nonnull AppendTaskLogRequest request) {
        this.validateLogRequest(request);

        Task task = this.taskOperations.actionsTaskAppendLog(request.getTaskId(), request.getLog());

        AppendTaskLogResponse response = new AppendTaskLogResponse();
        response.setTask(task);

        return response;
    }

    private void validateRetrievePrincipalRequest(RetrievePrincipalRequest request) {
        if (request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("principalId is required", "KuFlowActivities.validation");
        }
    }

    private void validateRetrieveProcessRequest(RetrieveProcessRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
    }

    private void validateSaveProcessElementRequest(SaveProcessElementRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getElementDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("elementDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    private void validateCompleteProcessRequest(CompleteProcessRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
    }

    private void validateChangeProcessInitiatorRequest(ChangeProcessInitiatorRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getEmail() == null && request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("email or principalId is required", "KuFlowActivities.validation");
        }
    }

    private void validateRetrieveTaskRequest(RetrieveTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskRequest(CreateTaskRequest request) {
        if (request.getTask().getId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("task.id is required", "KuFlowActivities.validation");
        }
        if (request.getTask().getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("task.processId is required", "KuFlowActivities.validation");
        }
        if (request.getTask().getTaskDefinition().getCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("task.taskDefinition.code is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskCompleteRequest(CompleteTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskClaimRequest(ClaimTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    private void validateTaskAssignRequest(AssignTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getEmail() == null && request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("email or principalId is required", "KuFlowActivities.validation");
        }
    }

    private void validateSaveTaskElementRequest(SaveTaskElementRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getElementDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("elementDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    private void validateDeleteTaskElementRequest(DeleteTaskElementRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getElementDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("elementDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    private void validateDeleteTaskElementValueDocumentRequest(DeleteTaskElementValueDocumentRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getDocumentId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("documentId is required", "KuFlowActivities.validation");
        }
    }

    private void validateLogRequest(AppendTaskLogRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getLog().getLevel() == null) {
            throw ApplicationFailure.newNonRetryableFailure("log.level is required", "KuFlowActivities.validation");
        }
        if (request.getLog().getMessage() == null) {
            throw ApplicationFailure.newNonRetryableFailure("log.message is required", "KuFlowActivities.validation");
        }
    }
}
