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
import com.kuflow.rest.model.JsonPatchOperation;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemCreateParams;
import com.kuflow.rest.model.ProcessItemFindOptions;
import com.kuflow.rest.model.ProcessItemPage;
import com.kuflow.rest.model.ProcessItemTaskAppendLogParams;
import com.kuflow.rest.model.ProcessItemTaskAssignParams;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import com.kuflow.rest.model.ProcessItemTaskState;
import com.kuflow.rest.model.ProcessItemType;
import java.util.List;
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
     * @param processItemCreateParams Process Item to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> createProcessItemWithResponse(ProcessItemCreateParams processItemCreateParams, Context context) {
        return this.service.createProcessItemWithResponse(processItemCreateParams, context);
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
     * @param processItemCreateParams Process Item to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem createProcessItem(ProcessItemCreateParams processItemCreateParams) {
        return this.createProcessItemWithResponse(processItemCreateParams, Context.NONE).getValue();
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
     * @param processItemTaskAssignParams Params to change the process item task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> assignProcessItemTaskWithResponse(
        UUID id,
        ProcessItemTaskAssignParams processItemTaskAssignParams,
        Context context
    ) {
        return this.service.assignProcessItemTaskWithResponse(id, processItemTaskAssignParams, context);
    }

    /**
     * Assign a process item task
     * <p>
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processItemTaskAssignParams Params to change the process item task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem assignProcessItemTask(UUID id, ProcessItemTaskAssignParams processItemTaskAssignParams) {
        return this.assignProcessItemTaskWithResponse(id, processItemTaskAssignParams, Context.NONE).getValue();
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
     * @param processItemTaskAppendLogParams Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> appendProcessItemTaskLogWithResponse(
        UUID id,
        ProcessItemTaskAppendLogParams processItemTaskAppendLogParams,
        Context context
    ) {
        return this.service.appendProcessItemTaskLogWithResponse(id, processItemTaskAppendLogParams, context);
    }

    /**
     * Append a log to the process item task
     * <p>
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param processItemTaskAppendLogParams Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem appendProcessItemTaskLog(UUID id, ProcessItemTaskAppendLogParams processItemTaskAppendLogParams) {
        return this.appendProcessItemTaskLogWithResponse(id, processItemTaskAppendLogParams, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processItemTaskDataUpdateParams Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> updateProcessItemTaskDataWithResponse(
        UUID id,
        ProcessItemTaskDataUpdateParams processItemTaskDataUpdateParams,
        Context context
    ) {
        return this.service.updateProcessItemTaskDataWithResponse(id, processItemTaskDataUpdateParams, context);
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processItemTaskDataUpdateParams Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem updateProcessItemTaskData(UUID id, ProcessItemTaskDataUpdateParams processItemTaskDataUpdateParams) {
        return this.updateProcessItemTaskDataWithResponse(id, processItemTaskDataUpdateParams, Context.NONE).getValue();
    }

    /**
     * Patch JSON data
     * <p>
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Array of JsonPatchOperation.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItem> patchProcessItemTaskDataWithResponse(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        return this.service.patchProcessItemTaskDataWithResponse(id, jsonPatch, context);
    }

    /**
     * Patch JSON data
     * <p>
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Array of JsonPatchOperation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItem patchProcessItemTaskData(UUID id, List<JsonPatchOperation> jsonPatch) {
        return this.patchProcessItemTaskDataWithResponse(id, jsonPatch, Context.NONE).getValue();
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
