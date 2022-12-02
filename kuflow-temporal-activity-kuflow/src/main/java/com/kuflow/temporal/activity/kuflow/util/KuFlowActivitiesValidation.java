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

package com.kuflow.temporal.activity.kuflow.util;

import com.kuflow.temporal.activity.kuflow.model.AppendTaskLogRequest;
import com.kuflow.temporal.activity.kuflow.model.AssignTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.ChangeProcessInitiatorRequest;
import com.kuflow.temporal.activity.kuflow.model.ClaimTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.CompleteProcessRequest;
import com.kuflow.temporal.activity.kuflow.model.CompleteTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.CreateTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.DeleteTaskElementRequest;
import com.kuflow.temporal.activity.kuflow.model.DeleteTaskElementValueDocumentRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrievePrincipalRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrieveProcessRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrieveTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessElementRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskElementRequest;
import io.temporal.failure.ApplicationFailure;

public class KuFlowActivitiesValidation {

    public static void validateRetrievePrincipalRequest(RetrievePrincipalRequest request) {
        if (request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("principalId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateRetrieveProcessRequest(RetrieveProcessRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateSaveProcessElementRequest(SaveProcessElementRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getElementDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("elementDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    public static void validateCompleteProcessRequest(CompleteProcessRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateChangeProcessInitiatorRequest(ChangeProcessInitiatorRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("processId is required", "KuFlowActivities.validation");
        }
        if (request.getEmail() == null && request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("email or principalId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateRetrieveTaskRequest(RetrieveTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateTaskRequest(CreateTaskRequest request) {
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

    public static void validateTaskCompleteRequest(CompleteTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateTaskClaimRequest(ClaimTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateTaskAssignRequest(AssignTaskRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getEmail() == null && request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("email or principalId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateSaveTaskElementRequest(SaveTaskElementRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getElementDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("elementDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    public static void validateDeleteTaskElementRequest(DeleteTaskElementRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getElementDefinitionCode() == null) {
            throw ApplicationFailure.newNonRetryableFailure("elementDefinitionCode is required", "KuFlowActivities.validation");
        }
    }

    public static void validateDeleteTaskElementValueDocumentRequest(DeleteTaskElementValueDocumentRequest request) {
        if (request.getTaskId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("taskId is required", "KuFlowActivities.validation");
        }
        if (request.getDocumentId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("documentId is required", "KuFlowActivities.validation");
        }
    }

    public static void validateLogRequest(AppendTaskLogRequest request) {
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
