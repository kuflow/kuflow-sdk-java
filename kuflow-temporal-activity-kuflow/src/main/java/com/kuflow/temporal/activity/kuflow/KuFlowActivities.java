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

import com.kuflow.temporal.activity.kuflow.model.AppendTaskLogRequest;
import com.kuflow.temporal.activity.kuflow.model.AppendTaskLogResponse;
import com.kuflow.temporal.activity.kuflow.model.AssignTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.AssignTaskResponse;
import com.kuflow.temporal.activity.kuflow.model.ChangeProcessInitiatorRequest;
import com.kuflow.temporal.activity.kuflow.model.ChangeProcessInitiatorResponse;
import com.kuflow.temporal.activity.kuflow.model.ClaimTaskRequest;
import com.kuflow.temporal.activity.kuflow.model.ClaimTaskResponse;
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
import com.kuflow.temporal.activity.kuflow.model.RetrieveTenantUserRequest;
import com.kuflow.temporal.activity.kuflow.model.RetrieveTenantUserResponse;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessElementRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveProcessElementResponse;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskElementRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskElementResponse;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskJsonFormsValueDataRequest;
import com.kuflow.temporal.activity.kuflow.model.SaveTaskJsonFormsValueDataResponse;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import javax.annotation.Nonnull;

/**
 * KuFlow activities to be used in Temporal.
 *
 */
@ActivityInterface(namePrefix = "KuFlow_Engine_")
public interface KuFlowActivities {
    /**
     * Retrieve a Principal.
     *
     * @param request must not be {@literal null}.
     * @return principal
     */
    @ActivityMethod
    @Nonnull
    RetrievePrincipalResponse retrievePrincipal(@Nonnull RetrievePrincipalRequest request);

    /**
     * Retrieve a Tenant User.
     *
     * @param request must not be {@literal null}.
     * @return tenant user
     */
    @ActivityMethod
    @Nonnull
    RetrieveTenantUserResponse retrieveTenantUser(@Nonnull RetrieveTenantUserRequest request);

    /**
     * Find all accessible Processes
     *
     * @param request must not be {@literal null}.
     * @return processes
     */
    @ActivityMethod
    @Nonnull
    FindProcessesResponse findProcesses(FindProcessesRequest request);

    /**
     * Retrieve a Process.
     *
     * @param request must not be {@literal null}.
     * @return process
     */
    @ActivityMethod
    @Nonnull
    RetrieveProcessResponse retrieveProcess(@Nonnull RetrieveProcessRequest request);

    /**
     *  Allow to save an element.
     *  If values already exist for the provided element code, it replaces them with the new ones, otherwise it creates them.
     *  The values of the previous elements that no longer exist will be deleted.
     *
     *  <p>If the process is already finished the invocations fails with an error.
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    SaveProcessElementResponse saveProcessElement(@Nonnull SaveProcessElementRequest request);

    /**
     *  Allow to delete a process element by specifying the item definition code.
     *  Remove all the element values.
     *
     * @param request must not be {@literal null}.
     * @return process deleted
     */
    DeleteProcessElementResponse deleteProcessElement(@Nonnull DeleteProcessElementRequest request);

    /**
     * Change the current initiator of a process. Allows you to choose a user (by email or principal
     * identifier) or an application (principal identifier).
     *
     * @param request must not be {@literal null}.
     * @return task assigned
     */
    @ActivityMethod
    @Nonnull
    ChangeProcessInitiatorResponse changeProcessInitiator(@Nonnull ChangeProcessInitiatorRequest request);

    /**
     * List all the Processes that have been created and the credentials has access.
     *
     * @param request must not be {@literal null}.
     * @return Processes found paginated
     */
    @ActivityMethod
    @Nonnull
    FindTaskResponse findTasks(FindTaskRequestModel request);

    /**
     * Retrieve a Task.
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    @ActivityMethod
    @Nonnull
    RetrieveTaskResponse retrieveTask(@Nonnull RetrieveTaskRequest request);

    /**
     * Create a Task and optionally fill its elements.
     *
     * @param request must not be {@literal null}.
     * @return task created
     */
    @ActivityMethod
    @Nonnull
    CreateTaskResponse createTask(@Nonnull CreateTaskRequest request);

    /**
     * Complete a task.
     *
     * <p>Allow to complete a claimed task by the principal.
     *
     * @param request must not be {@literal null}.
     * @return task completed
     */
    @ActivityMethod
    @Nonnull
    CompleteTaskResponse completeTask(@Nonnull CompleteTaskRequest request);

    /**
     * Claim a task.
     *
     * @param request must not be {@literal null}.
     * @return task claimed
     */
    @ActivityMethod
    @Nonnull
    ClaimTaskResponse claimTask(@Nonnull ClaimTaskRequest request);

    /**
     * Assign a task to a user or application using their email or principalId
     *
     * @param request must not be {@literal null}.
     * @return task assigned
     */
    @ActivityMethod
    @Nonnull
    AssignTaskResponse assignTask(@Nonnull AssignTaskRequest request);

    /**
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     * <br>
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to the
     * document using the 'uri' attribute. In case you want to add a new document, you should create a Temporal activity
     * specific to your needs and use our rest client to upload the document. This is because it is not recommended to save
     * binaries in the history of Temporal.
     * <br>
     * If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
     *
     * @param request must not be {@literal null}.
     * @return task updated
     */
    SaveTaskElementResponse saveTaskElement(@Nonnull SaveTaskElementRequest request);

    /**
     * Allow to delete task element by specifying the item definition code.
     * <br>
     * Remove all the element values.
     *
     * @param request must not be {@literal null}.
     * @return task updated
     */
    DeleteTaskElementResponse deleteTaskElement(@Nonnull DeleteTaskElementRequest request);

    /**
     * Allow to delete a specific document from an element of document type using its id.
     * <br>
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in addition to the document, it will also delete the element.
     *
     * @param request must not be {@literal null}.
     * @return task updated
     */
    DeleteTaskElementValueDocumentResponse deleteTaskElementValueDocument(@Nonnull DeleteTaskElementValueDocumentRequest request);

    /**
     * Save JSON data
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param request must not be {@literal null}.
     * @return task updated
     */
    SaveTaskJsonFormsValueDataResponse saveTaskJsonFormsValueData(@Nonnull SaveTaskJsonFormsValueDataRequest request);

    /**
     * Append a log to the task.
     *
     * <p>A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param request must not be {@literal null}.
     * @return log appended
     */
    @ActivityMethod
    @Nonnull
    AppendTaskLogResponse appendTaskLog(@Nonnull AppendTaskLogRequest request);
}
