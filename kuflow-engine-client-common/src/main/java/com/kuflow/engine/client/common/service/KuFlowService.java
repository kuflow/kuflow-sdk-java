/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.service;

import com.kuflow.rest.client.controller.ProcessApi;
import com.kuflow.rest.client.controller.ProcessApi.FindProcessesQueryParams;
import com.kuflow.rest.client.controller.TaskApi;
import com.kuflow.rest.client.controller.TaskApi.ActionsDownloadTaskElementValueDocumentQueryParams;
import com.kuflow.rest.client.controller.TaskApi.ActionsDownloadTaskElementValueRenderedQueryParams;
import com.kuflow.rest.client.controller.TaskApi.FindTasksQueryParams;
import com.kuflow.rest.client.resource.AssignTaskCommandResource;
import com.kuflow.rest.client.resource.ChangeProcessInitiatorCommandResource;
import com.kuflow.rest.client.resource.DeleteElementCommandResource;
import com.kuflow.rest.client.resource.DeleteElementValueDocumentCommandResource;
import com.kuflow.rest.client.resource.LogResource;
import com.kuflow.rest.client.resource.ProcessPageResource;
import com.kuflow.rest.client.resource.ProcessResource;
import com.kuflow.rest.client.resource.ProcessSaveElementCommandResource;
import com.kuflow.rest.client.resource.SaveElementValueDocumentCommandResource;
import com.kuflow.rest.client.resource.SaveProcessUserActionValueDocumentCommandResource;
import com.kuflow.rest.client.resource.TaskPageResource;
import com.kuflow.rest.client.resource.TaskResource;
import com.kuflow.rest.client.resource.TaskSaveElementCommandResource;
import com.kuflow.rest.client.resource.TaskStateResource;
import feign.Response;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.springframework.stereotype.Service;

@Service
public class KuFlowService {

    private final ProcessApi processApi;

    private final TaskApi taskApi;

    public KuFlowService(TaskApi taskApi, ProcessApi processApi) {
        this.taskApi = taskApi;
        this.processApi = processApi;
    }

    public ProcessPageResource findProcesses(@Nullable Integer page, @Nullable Integer size, @Nullable List<String> sort) {
        FindProcessesQueryParams queryParams = new FindProcessesQueryParams();
        if (page != null) {
            queryParams.page(page);
        }

        if (size != null) {
            queryParams.size(size);
        }

        if (sort != null && !sort.isEmpty()) {
            queryParams.sort(sort);
        }

        return this.processApi.findProcesses(queryParams);
    }

    public ProcessResource retrieveProcess(@Nonnull UUID processId) {
        return this.processApi.retrieveProcess(processId);
    }

    public ProcessResource completeProcess(@Nonnull UUID processId) {
        return this.processApi.actionsCompleteProcess(processId);
    }

    public ProcessResource changeProcessInitiator(@Nonnull UUID processId, @Nullable String email, @Nullable UUID principalId) {
        ChangeProcessInitiatorCommandResource command = new ChangeProcessInitiatorCommandResource();
        command.setEmail(email);
        command.setPrincipalId(principalId);

        return this.processApi.actionsChangeProcessInitiator(processId, command);
    }

    public ProcessResource saveProcessUserActionValueDocument(
        @Nonnull UUID processId,
        @Nonnull SaveProcessUserActionValueDocumentCommandResource json,
        @Nonnull File file
    ) {
        return this.processApi.actionsSaveProcessUserActionValueDocument(processId, json, file);
    }

    public ProcessResource saveProcessElement(@Nonnull UUID processId, @Nonnull ProcessSaveElementCommandResource command) {
        return this.processApi.actionsSaveProcessElement(processId, command);
    }

    public ProcessResource deleteProcessElement(@Nonnull UUID processId, @Nonnull String code) {
        DeleteElementCommandResource command = new DeleteElementCommandResource();
        command.setCode(code);

        return this.processApi.actionsDeleteProcessElement(processId, command);
    }

    public TaskPageResource findTasks(
        @Nullable Integer page,
        @Nullable Integer size,
        @Nullable List<String> sort,
        List<UUID> processIds,
        List<TaskStateResource> states,
        List<String> taskDefinitionCodes
    ) {
        FindTasksQueryParams queryParams = new FindTasksQueryParams();
        if (page != null) {
            queryParams.page(page);
        }

        if (size != null) {
            queryParams.size(size);
        }

        if (sort != null && !sort.isEmpty()) {
            queryParams.sort(sort);
        }

        if (processIds != null && !processIds.isEmpty()) {
            queryParams.processId(processIds);
        }

        if (states != null && !states.isEmpty()) {
            queryParams.state(states);
        }

        if (taskDefinitionCodes != null && !taskDefinitionCodes.isEmpty()) {
            queryParams.taskDefinitionCode(taskDefinitionCodes);
        }

        return this.taskApi.findTasks(queryParams);
    }

    public TaskResource retrieveTask(@Nonnull UUID taskId) {
        return this.taskApi.retrieveTask(taskId);
    }

    public TaskResource createTask(@Nonnull TaskResource taskResource) {
        return this.taskApi.createTask(taskResource);
    }

    public TaskResource claimTask(@Nonnull UUID taskId) {
        return this.taskApi.actionsClaimTask(taskId);
    }

    public TaskResource assignTask(@Nonnull UUID taskId, @Nullable String email, @Nullable UUID principalId) {
        AssignTaskCommandResource command = new AssignTaskCommandResource();
        command.setEmail(email);
        command.setPrincipalId(principalId);

        return this.taskApi.actionsAssignTask(taskId, command);
    }

    public TaskResource saveTaskElement(@Nonnull UUID taskId, TaskSaveElementCommandResource command) {
        return this.taskApi.actionsSaveTaskElement(taskId, command);
    }

    public TaskResource saveTaskElementValueDocument(@Nonnull UUID taskId, @Nonnull String code, @Nonnull File file) {
        return this.saveTaskElementValueDocument(taskId, code, Boolean.TRUE, file);
    }

    public TaskResource saveTaskElementValueDocument(
        @Nonnull UUID taskId,
        @Nonnull String code,
        @Nullable Boolean valid,
        @Nonnull File file
    ) {
        SaveElementValueDocumentCommandResource command = new SaveElementValueDocumentCommandResource();
        command.setCode(code);
        command.setValid(valid);

        return this.taskApi.actionsSaveTaskElementValueDocument(taskId, command, file);
    }

    public TaskResource saveTaskElementValueDocument(@Nonnull UUID taskId, SaveElementValueDocumentCommandResource json, File file) {
        return this.taskApi.actionsSaveTaskElementValueDocument(taskId, json, file);
    }

    public TaskResource deleteTaskElement(@Nonnull UUID taskId, @Nonnull String code) {
        DeleteElementCommandResource command = new DeleteElementCommandResource();
        command.setCode(code);

        return this.taskApi.actionsDeleteTaskElement(taskId, command);
    }

    public TaskResource deleteTaskElementValueDocument(@Nonnull UUID taskId, @Nonnull UUID documentId) {
        DeleteElementValueDocumentCommandResource command = new DeleteElementValueDocumentCommandResource();
        command.setDocumentId(documentId);

        return this.taskApi.actionsDeleteTaskElementValueDocument(taskId, command);
    }

    public InputStream downloadTaskDocument(@Nonnull UUID taskId, @Nonnull UUID documentId) throws IOException {
        ActionsDownloadTaskElementValueDocumentQueryParams queryParams = new ActionsDownloadTaskElementValueDocumentQueryParams()
            .documentId(documentId);
        Response reponse = this.taskApi.actionsDownloadTaskElementValueDocument(taskId, queryParams);

        return reponse.body().asInputStream();
    }

    public InputStream downloadTaskElementForm(@Nonnull UUID taskId, @Nonnull String code) throws IOException {
        ActionsDownloadTaskElementValueRenderedQueryParams queryParams = new ActionsDownloadTaskElementValueRenderedQueryParams()
            .code(code);

        Response reponse = this.taskApi.actionsDownloadTaskElementValueRendered(taskId, queryParams);

        return reponse.body().asInputStream();
    }

    public TaskResource completeTask(@Nonnull UUID taskId) {
        return this.taskApi.actionsCompleteTask(taskId);
    }

    public TaskResource appendTaskLog(UUID taskId, LogResource logResource) {
        return this.taskApi.actionsAppendTaskLog(taskId, logResource);
    }
}
