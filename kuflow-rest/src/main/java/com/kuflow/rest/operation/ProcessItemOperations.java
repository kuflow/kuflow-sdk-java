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
import com.kuflow.rest.implementation.ProcessItemOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.DocumentReference;
import com.kuflow.rest.model.JsonPatchOperation;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemCreateParams;
import com.kuflow.rest.model.ProcessItemFindOptions;
import com.kuflow.rest.model.ProcessItemPage;
import com.kuflow.rest.model.ProcessItemTaskAppendLogParams;
import com.kuflow.rest.model.ProcessItemTaskAssignParams;
import com.kuflow.rest.model.ProcessItemTaskDataDocumentUploadParams;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import com.kuflow.rest.model.ProcessItemTaskState;
import com.kuflow.rest.model.ProcessItemType;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in ProcessItemOperations. */
public class ProcessItemOperations {

    /** The service. */
    private final ProcessItemOperationsImpl service;

    /**
     * Initializes an instance of TaskOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public ProcessItemOperations(KuFlowClientImpl client) {
        this.service = client.getProcessItemOperations();
    }

    /**
     * Find all accessible Process Items
     * <p>
     * List all Process Items that have been created and the credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItemPage> findProcessItemsWithResponse(ProcessItemFindOptions options, Context context) {
        options = options != null ? options : new ProcessItemFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> processId = !options.getProcessIds().isEmpty() ? options.getProcessIds() : null;
        List<ProcessItemType> type = !options.getTypes().isEmpty() ? options.getTypes() : null;
        List<ProcessItemTaskState> taskState = !options.getTaskStates().isEmpty() ? options.getTaskStates() : null;
        List<String> taskDefinitionCode = !options.getTaskDefinitionCodes().isEmpty() ? options.getTaskDefinitionCodes() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findProcessItemsWithResponse(
                size,
                page,
                sort,
                processId,
                type,
                taskState,
                taskDefinitionCode,
                tenantId,
                context
            );
    }

    /**
     * Find all accessible Process Items
     * <p>
     * List all Process Items that have been created and the credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItemPage findProcessItems(ProcessItemFindOptions options) {
        return this.findProcessItemsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Process Items
     * <p>
     * List all Process Items that have been created and the credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItemPage findProcessItems() {
        return this.findProcessItemsWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Create a new Process Item in the selected Process
     * <p>
     * Create a Process Item and optionally fill its value.
     * <p>
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     * <p>
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process Item to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> createProcessItemWithResponse(ProcessItemCreateParams params, Context context) {
        return this.service.createProcessItemWithResponse(params, context);
    }

    /**
     * Create a new Process Item in the selected Process
     * <p>
     * Create a Process Item and optionally fill its value.
     * <p>
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     * <p>
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process Item to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem createProcessItem(ProcessItemCreateParams params) {
        return this.createProcessItemWithResponse(params, Context.NONE).getValue();
    }

    /**
     * Get a process item given it ID
     * <p>
     * Allow to get a process item by ID.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> retrieveProcessItemWithResponse(UUID id, Context context) {
        return this.service.retrieveProcessItemWithResponse(id, context);
    }

    /**
     * Get a process item given it ID
     * <p>
     * Allow to get a process item by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem retrieveProcessItem(UUID id) {
        return this.retrieveProcessItemWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Claim a process item task
     * <p>
     * Allow to claim a task.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> claimProcessItemTaskWithResponse(UUID id, Context context) {
        return this.service.claimProcessItemTaskWithResponse(id, context);
    }

    /**
     * Claim a process item task
     * <p>
     * Allow to claim a task.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem claimProcessItemTask(UUID id) {
        return this.claimProcessItemTaskWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Assign a process item task
     * <p>
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param params Params to change the process item task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> assignProcessItemTaskWithResponse(UUID id, ProcessItemTaskAssignParams params, Context context) {
        return this.service.assignProcessItemTaskWithResponse(id, params, context);
    }

    /**
     * Assign a process item task
     * <p>
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param params Params to change the process item task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem assignProcessItemTask(UUID id, ProcessItemTaskAssignParams params) {
        return this.assignProcessItemTaskWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Complete a process item task
     * <p>
     * Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> completeProcessItemTaskWithResponse(UUID id, Context context) {
        return this.service.completeProcessItemTaskWithResponse(id, context);
    }

    /**
     * Complete a process item task
     * <p>
     * Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem completeProcessItemTask(UUID id) {
        return this.completeProcessItemTaskWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Append a log to the process item task
     * <p>
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param params Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> appendProcessItemTaskLogWithResponse(UUID id, ProcessItemTaskAppendLogParams params, Context context) {
        return this.service.appendProcessItemTaskLogWithResponse(id, params, context);
    }

    /**
     * Append a log to the process item task
     * <p>
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param params Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem appendProcessItemTaskLog(UUID id, ProcessItemTaskAppendLogParams params) {
        return this.appendProcessItemTaskLogWithResponse(id, params, Context.NONE).getValue();
    }

    // *******************
    // *******************
    // *******************
    // *******************

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> updateProcessItemTaskDataWithResponse(UUID id, ProcessItemTaskDataUpdateParams params, Context context) {
        return this.service.updateProcessItemTaskDataWithResponse(id, params, context);
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem updateProcessItemTaskData(UUID id, ProcessItemTaskDataUpdateParams params) {
        return this.updateProcessItemTaskDataWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Patch JSON data
     * <p>
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Array of JsonPatchOperation.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> patchProcessItemTaskDataWithResponse(UUID id, List<JsonPatchOperation> params, Context context) {
        return this.service.patchProcessItemTaskDataWithResponse(id, params, context);
    }

    /**
     * Patch JSON data
     * <p>
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Array of JsonPatchOperation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem patchProcessItemTaskData(UUID id, List<JsonPatchOperation> params) {
        return this.patchProcessItemTaskDataWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Upload a document associated with the process item task data
     * <p>
     * Save a document in the task to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param params Params info.
     * @param document Document to upload.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DocumentReference> uploadProcessItemTaskDataDocumentWithResponse(
        UUID id,
        ProcessItemTaskDataDocumentUploadParams params,
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
        String schemaPath = params.getSchemaPath();
        BinaryData file = document.getFileContent();
        long contentLength = file.getLength();
        return this.service.uploadProcessItemTaskDataDocumentWithResponse(
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
     * Upload a document associated with the process item task data
     * <p>
     * Save a document in the task to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param params Params info.
     * @param document Document to upload.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DocumentReference uploadProcessItemTaskDataDocument(UUID id, ProcessItemTaskDataDocumentUploadParams params, Document document) {
        return this.uploadProcessItemTaskDataDocumentWithResponse(id, params, document, Context.NONE).getValue();
    }

    /**
     * Download document
     * <p>
     * Given a task, download a document from a json form data.
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
    public Response<BinaryData> downloadProcessItemTaskDataDocumentWithResponse(UUID id, String documentUri, Context context) {
        return this.service.downloadProcessItemTaskDataDocumentWithResponse(id, documentUri, context);
    }

    /**
     * Download document
     * <p>
     * Given a task, download a document from a json form data.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadProcessItemTaskDataDocument(UUID id, String documentUri) {
        return this.downloadProcessItemTaskDataDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     * <p>
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     * <p>
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See: <a href="https://datatracker.ietf.org/doc/html/rfc6901">...</a>
     * <p>
     * ie: /user/name or /users/1/name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadProcessItemTaskDataWebformsAsDocumentWithResponse(UUID id, String propertyPath, Context context) {
        return this.service.downloadProcessItemTaskDataWebformsAsDocumentWithResponse(id, propertyPath, context);
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     * <p>
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     * <p>
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See: <a href="https://datatracker.ietf.org/doc/html/rfc6901">...</a>
     * <p>
     * ie: /user/name or /users/1/name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadProcessItemTaskDataWebformsAsDocumentWithResponse(UUID id, String propertyPath) {
        return this.downloadProcessItemTaskDataWebformsAsDocumentWithResponse(id, propertyPath, Context.NONE).getValue();
    }
}
