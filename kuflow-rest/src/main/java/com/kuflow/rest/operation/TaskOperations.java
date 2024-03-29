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
package com.kuflow.rest.operation;

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.implementation.TaskOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.FindTasksOptions;
import com.kuflow.rest.model.Log;
import com.kuflow.rest.model.Task;
import com.kuflow.rest.model.TaskAssignCommand;
import com.kuflow.rest.model.TaskDeleteElementCommand;
import com.kuflow.rest.model.TaskDeleteElementValueDocumentCommand;
import com.kuflow.rest.model.TaskPage;
import com.kuflow.rest.model.TaskSaveElementCommand;
import com.kuflow.rest.model.TaskSaveElementValueDocumentCommand;
import com.kuflow.rest.model.TaskSaveJsonFormsValueDataCommand;
import com.kuflow.rest.model.TaskSaveJsonFormsValueDocumentRequestCommand;
import com.kuflow.rest.model.TaskSaveJsonFormsValueDocumentResponseCommand;
import com.kuflow.rest.model.TaskState;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in TaskOperations. */
public class TaskOperations {

    /** The service. */
    private final TaskOperationsImpl service;

    /**
     * Initializes an instance of TaskOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public TaskOperations(KuFlowClientImpl client) {
        this.service = client.getTaskOperations();
    }

    /**
     * Find all accessible Tasks
     *
     * <p>List all Tasks that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TaskPage> findTasksWithResponse(FindTasksOptions options, Context context) {
        options = options != null ? options : new FindTasksOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> processId = !options.getProcessIds().isEmpty() ? options.getProcessIds() : null;
        List<TaskState> state = !options.getStates().isEmpty() ? options.getStates() : null;
        List<String> taskDefinitionCode = !options.getTaskDefinitionCodes().isEmpty() ? options.getTaskDefinitionCodes() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findTasksWithResponse(size, page, sort, processId, state, taskDefinitionCode, tenantId, context);
    }

    /**
     * Find all accessible Tasks
     *
     * <p>List all Tasks that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TaskPage findTasks(FindTasksOptions options) {
        return this.findTasksWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Tasks
     *
     * <p>List all Tasks that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TaskPage findTasks() {
        return this.findTasksWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Create a new Task in the selected Process
     *
     * <p>Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * <p>If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * <p>If you want that the task created is claimed you can a valid owner using the following options: * If you know
     * the `principal ID` you can assign it to `owner.id` * If you know the `user ID` you can assign it to
     * `owner.user.id` * If you know the `user email` you can assign it to `owner.user.email` * If you know the
     * `application ID` you can assign it to `owner.application.id`
     *
     * <p>If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> createTaskWithResponse(Task task, Context context) {
        return this.service.createTaskWithResponse(task, null, context);
    }

    /**
     * Create a new Task in the selected Process
     *
     * <p>Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * <p>If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * <p>If you want that the task created is claimed you can a valid owner using the following options: * If you know
     * the `principal ID` you can assign it to `owner.id` * If you know the `user ID` you can assign it to
     * `owner.user.id` * If you know the `user email` you can assign it to `owner.user.email` * If you know the
     * `application ID` you can assign it to `owner.application.id`
     *
     * <p>If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task createTask(Task task) {
        return this.createTaskWithResponse(task, Context.NONE).getValue();
    }

    /**
     * Get a task given it ID
     *
     * <p>Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> retrieveTaskWithResponse(UUID id, Context context) {
        return this.service.retrieveTaskWithResponse(id, context);
    }

    /**
     * Get a task given it ID
     *
     * <p>Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task retrieveTask(UUID id) {
        return this.retrieveTaskWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Claim a task
     *
     * <p>Allow to claim a task.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskClaimWithResponse(UUID id, Context context) {
        return this.service.actionsTaskClaimWithResponse(id, context);
    }

    /**
     * Claim a task
     *
     * <p>Allow to claim a task.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskClaim(UUID id) {
        return this.actionsTaskClaimWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Assign a task
     *
     * <p>Allow to assign a task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskAssignWithResponse(UUID id, TaskAssignCommand command, Context context) {
        return this.service.actionsTaskAssignWithResponse(id, command, context);
    }

    /**
     * Assign a task
     *
     * <p>Allow to assign a task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskAssign(UUID id, TaskAssignCommand command) {
        return this.actionsTaskAssignWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save an element
     *
     * <p>Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * <p>In the case of document type elements, this method only allows references to be made to other existing
     * document type elements for the purpose of copying that file into the element. To do this you need to pass a
     * reference to the document using the 'uri' attribute. In case you want to add a new document, please use the
     * corresponding API method. If values already exist for the provided element code, it replaces them with the new
     * ones, otherwise it creates them. The values of the previous elements that no longer exist will be deleted. To
     * remove an element, use the appropriate API method.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskSaveElementWithResponse(UUID id, TaskSaveElementCommand command, Context context) {
        return this.service.actionsTaskSaveElementWithResponse(id, command, context);
    }

    /**
     * Save an element
     *
     * <p>Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * <p>In the case of document type elements, this method only allows references to be made to other existing
     * document type elements for the purpose of copying that file into the element. To do this you need to pass a
     * reference to the document using the 'uri' attribute. In case you want to add a new document, please use the
     * corresponding API method. If values already exist for the provided element code, it replaces them with the new
     * ones, otherwise it creates them. The values of the previous elements that no longer exist will be deleted. To
     * remove an element, use the appropriate API method.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskSaveElement(UUID id, TaskSaveElementCommand command) {
        return this.actionsTaskSaveElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save an element document
     *
     * <p>Allow to save an element document uploading the content.
     *
     * <p>If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will
     * be added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param command Command info.
     * @param document Document to upload.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskSaveElementValueDocumentWithResponse(
        UUID id,
        TaskSaveElementValueDocumentCommand command,
        Document document,
        Context context
    ) {
        Objects.requireNonNull(document, "'document' is required");
        Objects.requireNonNull(document.getFileContent(), "'document.fileContent' is required");
        Objects.requireNonNull(document.getFileContent().getLength(), "'document.fileContent.length' is required");
        Objects.requireNonNull(document.getFileName(), "'document.fileName' is required");
        Objects.requireNonNull(document.getContentType(), "'document.contentType' is required");
        if (document.getFileContent().getLength() == 0) {
            throw new IllegalArgumentException("File size must be greater that 0");
        }

        String fileContentType = document.getContentType();
        String fileName = document.getFileName();
        String elementDefinitionCode = command.getElementDefinitionCode();
        UUID elementValueId = command.getElementValueId();
        Boolean elementValueValid = command.isElementValueValid();
        BinaryData file = document.getFileContent();
        long contentLength = file.getLength();

        return this.service.actionsTaskSaveElementValueDocumentWithResponse(
                id,
                fileContentType,
                fileName,
                elementDefinitionCode,
                file,
                contentLength,
                elementValueId,
                elementValueValid,
                context
            );
    }

    /**
     * Save an element document
     *
     * <p>Allow to save an element document uploading the content.
     *
     * <p>If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will
     * be added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param command Command info.
     * @param document Document to upload.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskSaveElementValueDocument(UUID id, TaskSaveElementValueDocumentCommand command, Document document) {
        return this.actionsTaskSaveElementValueDocumentWithResponse(id, command, document, Context.NONE).getValue();
    }

    /**
     * Delete an element by code
     *
     * <p>Allow to delete task element by specifying the item definition code.
     *
     * <p>Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskDeleteElementWithResponse(UUID id, TaskDeleteElementCommand command, Context context) {
        return this.service.actionsTaskDeleteElementWithResponse(id, command, context);
    }

    /**
     * Delete an element by code
     *
     * <p>Allow to delete task element by specifying the item definition code.
     *
     * <p>Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskDeleteElement(UUID id, TaskDeleteElementCommand command) {
        return this.actionsTaskDeleteElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Delete an element document value
     *
     * <p>Allow to delete a specific document from an element of document type using its id.
     *
     * <p>Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
     * addition to the document, it will also delete the element.
     *
     * @param id The resource ID.
     * @param command Command to delete a document element value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskDeleteElementValueDocumentWithResponse(
        UUID id,
        TaskDeleteElementValueDocumentCommand command,
        Context context
    ) {
        return this.service.actionsTaskDeleteElementValueDocumentWithResponse(id, command, context);
    }

    /**
     * Delete an element document value
     *
     * <p>Allow to delete a specific document from an element of document type using its id.
     *
     * <p>Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
     * addition to the document, it will also delete the element.
     *
     * @param id The resource ID.
     * @param command Command to delete a document element value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskDeleteElementValueDocument(UUID id, TaskDeleteElementValueDocumentCommand command) {
        return this.actionsTaskDeleteElementValueDocumentWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Download document
     *
     * <p>Given a task, download a document from an element of document type.
     *
     * @param id The resource ID.
     * @param documentId Document ID to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> actionsTaskDownloadElementValueDocumentWithResponse(UUID id, UUID documentId, Context context) {
        return this.service.actionsTaskDownloadElementValueDocumentWithResponse(id, documentId, context);
    }

    /**
     * Download document
     *
     * <p>Given a task, download a document from an element of document type.
     *
     * @param id The resource ID.
     * @param documentId Document ID to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData actionsTaskDownloadElementValueDocument(UUID id, UUID documentId) {
        return this.actionsTaskDownloadElementValueDocumentWithResponse(id, documentId, Context.NONE).getValue();
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * <p>Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple
     * form values, they are packed into a ZIP.
     *
     * <p>Important!: To use this feature, please contact to kuflow@kuflow.com.
     *
     * @param id The resource ID.
     * @param elementDefinitionCode Element definition code of a Form Element to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> actionsTaskDownloadElementValueRenderedWithResponse(
        UUID id,
        String elementDefinitionCode,
        Context context
    ) {
        return this.service.actionsTaskDownloadElementValueRenderedWithResponse(id, elementDefinitionCode, context);
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * <p>Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple
     * form values, they are packed into a ZIP.
     *
     * <p>Important!: To use this feature, please contact to kuflow@kuflow.com.
     *
     * @param id The resource ID.
     * @param elementDefinitionCode Element definition code of a Form Element to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData actionsTaskDownloadElementValueRendered(UUID id, String elementDefinitionCode) {
        return this.actionsTaskDownloadElementValueRenderedWithResponse(id, elementDefinitionCode, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskSaveJsonFormsValueDataWithResponse(
        UUID id,
        TaskSaveJsonFormsValueDataCommand command,
        Context context
    ) {
        return this.service.actionsTaskSaveJsonFormsValueDataWithResponse(id, command, context);
    }

    /**
     * Save JSON data
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskSaveJsonFormsValueData(UUID id, TaskSaveJsonFormsValueDataCommand command) {
        return this.actionsTaskSaveJsonFormsValueDataWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save a JSON Forms document
     *
     * <p>Save a document in the task to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param command Command info.
     * @param document Document to upload.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentWithResponse(
        UUID id,
        TaskSaveJsonFormsValueDocumentRequestCommand command,
        Document document,
        Context context
    ) {
        Objects.requireNonNull(document, "'document' is required");
        Objects.requireNonNull(document.getFileContent(), "'document.fileContent' is required");
        Objects.requireNonNull(document.getFileContent().getLength(), "'document.fileContent.length' is required");
        Objects.requireNonNull(document.getFileName(), "'document.fileName' is required");
        Objects.requireNonNull(document.getContentType(), "'document.contentType' is required");
        if (document.getFileContent().getLength() == 0) {
            throw new IllegalArgumentException("File size must be greater that 0");
        }

        String fileContentType = document.getContentType();
        String fileName = document.getFileName();
        String schemaPath = command.getSchemaPath();
        BinaryData file = document.getFileContent();
        long contentLength = file.getLength();

        return this.service.actionsTaskSaveJsonFormsValueDocumentWithResponse(
                id,
                fileContentType,
                fileName,
                schemaPath,
                file,
                contentLength,
                context
            );
    }

    /**
     * Save a JSON Forms document
     *
     * <p>Save a document in the task to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param command Document content type.
     * @param document Document name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TaskSaveJsonFormsValueDocumentResponseCommand actionsTaskSaveJsonFormsValueDocument(
        UUID id,
        TaskSaveJsonFormsValueDocumentRequestCommand command,
        Document document
    ) {
        return this.actionsTaskSaveJsonFormsValueDocumentWithResponse(id, command, document, Context.NONE).getValue();
    }

    /**
     * Download document
     *
     * <p>Given a task, download a document from a json form data.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> actionsTaskDownloadJsonFormsValueDocumentWithResponse(UUID id, String documentUri, Context context) {
        return this.service.actionsTaskDownloadJsonFormsValueDocumentWithResponse(id, documentUri, context);
    }

    /**
     * Download document
     *
     * <p>Given a task, download a document from a json form data.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData actionsTaskDownloadJsonFormsValueDocument(UUID id, String documentUri) {
        return this.actionsTaskDownloadJsonFormsValueDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }

    /**
     * Complete a task
     *
     * <p>Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskCompleteWithResponse(UUID id, Context context) {
        return this.service.actionsTaskCompleteWithResponse(id, context);
    }

    /**
     * Complete a task
     *
     * <p>Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskComplete(UUID id) {
        return this.actionsTaskCompleteWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Append a log to the task
     *
     * <p>A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param log Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskAppendLogWithResponse(UUID id, Log log, Context context) {
        return this.service.actionsTaskAppendLogWithResponse(id, log, context);
    }

    /**
     * Append a log to the task
     *
     * <p>A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param log Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskAppendLog(UUID id, Log log) {
        return this.actionsTaskAppendLogWithResponse(id, log, Context.NONE).getValue();
    }
}
