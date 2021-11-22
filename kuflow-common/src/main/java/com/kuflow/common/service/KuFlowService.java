/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.common.service;

import com.kuflow.api.controller.ProcessApi;
import com.kuflow.api.controller.TaskApi;
import com.kuflow.api.resource.DeleteElementsCommandResource;
import com.kuflow.api.resource.ElementDefinitionTypeResource;
import com.kuflow.api.resource.ElementValueDecisionResource;
import com.kuflow.api.resource.ElementValueDocumentResource;
import com.kuflow.api.resource.ElementValueFieldResource;
import com.kuflow.api.resource.ElementValueFormResource;
import com.kuflow.api.resource.LogResource;
import com.kuflow.api.resource.ProcessResource;
import com.kuflow.api.resource.TaskResource;
import java.io.File;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class KuFlowService {

    private final ProcessApi processApi;

    private final TaskApi taskApi;

    public KuFlowService(TaskApi taskApi, ProcessApi processApi) {
        this.taskApi = taskApi;
        this.processApi = processApi;
    }

    public ProcessResource startProcess(UUID processId) {
        return this.processApi.actionsStartProcess(processId);
    }

    public ProcessResource completeProcess(UUID processId) {
        return this.processApi.actionsCompleteProcess(processId);
    }

    public TaskResource createTask(TaskResource taskResource) {
        return this.processApi.actionsCreateTask(taskResource.getProcessId(), taskResource);
    }

    public TaskResource claimTask(UUID taskId) {
        return this.taskApi.actionsClaimTask(taskId);
    }

    public TaskResource completeTask(UUID taskId) {
        return this.taskApi.actionsCompleteTask(taskId);
    }

    public TaskResource completeTaskElementForm(UUID taskId, ElementValueFormResource elementValueFormResource) {
        elementValueFormResource.setElementDefinitionType(ElementDefinitionTypeResource.FORM);

        return this.taskApi.actionsCompleteTaskElementForm(taskId, elementValueFormResource);
    }

    public TaskResource completeTaskElementDocument(UUID taskId, ElementValueDocumentResource elementValueDocumentResource, File file) {
        elementValueDocumentResource.setElementDefinitionType(ElementDefinitionTypeResource.DOCUMENT);
        elementValueDocumentResource.setOriginalName(file.getName());

        return this.taskApi.actionsCompleteTaskElementDocument(taskId, elementValueDocumentResource, file);
    }

    public TaskResource completeTaskElementField(UUID taskId, ElementValueFieldResource elementValueFieldResource) {
        elementValueFieldResource.setElementDefinitionType(ElementDefinitionTypeResource.FIELD);

        return this.taskApi.actionsCompleteTaskElementField(taskId, elementValueFieldResource);
    }

    public TaskResource completeTaskElementFieldDecision(UUID taskId, ElementValueDecisionResource elementValueDecisionResource) {
        elementValueDecisionResource.setElementDefinitionType(ElementDefinitionTypeResource.DECISION);

        return this.taskApi.actionsCompleteTaskElementDecision(taskId, elementValueDecisionResource);
    }

    public TaskResource deleteTaskElements(UUID taskId, String elementDefinitionCode) {
        DeleteElementsCommandResource command = new DeleteElementsCommandResource();
        command.setElementDefinitionCode(elementDefinitionCode);

        return this.taskApi.actionsDeleteTaskElements(taskId, command);
    }

    public TaskResource appendLog(UUID taskId, LogResource logResource) {
        return this.taskApi.actionsAppendLog(taskId, logResource);
    }
}
