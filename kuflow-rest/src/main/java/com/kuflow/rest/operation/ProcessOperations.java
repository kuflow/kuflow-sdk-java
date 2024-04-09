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
import com.kuflow.rest.implementation.ProcessOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.FindProcessesOptions;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessChangeInitiatorCommand;
import com.kuflow.rest.model.ProcessDeleteElementCommand;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.ProcessSaveElementCommand;
import com.kuflow.rest.model.ProcessSaveEntityDataCommand;
import com.kuflow.rest.model.ProcessSaveEntityDocumentResponseCommand;
import com.kuflow.rest.model.ProcessSaveUserActionValueDocumentCommand;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in ProcessOperations. */
public class ProcessOperations {

    /** The service. */
    private final ProcessOperationsImpl service;

    /**
     * Initializes an instance of ProcessOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public ProcessOperations(KuFlowClientImpl client) {
        this.service = client.getProcessOperations();
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessPage> findProcessesWithResponse(FindProcessesOptions options, Context context) {
        options = options != null ? options : new FindProcessesOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findProcessesWithResponse(size, page, sort, tenantId, context);
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses(FindProcessesOptions options) {
        return this.findProcessesWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses() {
        return this.findProcessesWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Create a new process
     *
     * <p>Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow
     * GUI.
     *
     * <p>When a process is created, the current user is assigned as the process initiator, if you want to change it,
     * you can pass a valid initiator using the following options:
     *
     * <p>* If you know the `principal ID` you can assign it to `initiator.id` * If you know the `user ID` you can
     * assign it to `initiator.user.id` * If you know the `user email` you can assign it to `initiator.user.email` * If
     * you know the `application ID` you can assign it to `initiator.application.id`
     *
     * <p>If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param process Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> createProcessWithResponse(Process process, Context context) {
        return this.service.createProcessWithResponse(process, context);
    }

    /**
     * Create a new process
     *
     * <p>Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow
     * GUI.
     *
     * <p>When a process is created, the current user is assigned as the process initiator, if you want to change it,
     * you can pass a valid initiator using the following options:
     *
     * <p>* If you know the `principal ID` you can assign it to `initiator.id` * If you know the `user ID` you can
     * assign it to `initiator.user.id` * If you know the `user email` you can assign it to `initiator.user.email` * If
     * you know the `application ID` you can assign it to `initiator.application.id`
     *
     * <p>If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param process Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process createProcess(Process process) {
        return this.createProcessWithResponse(process, Context.NONE).getValue();
    }

    /**
     * Get a Process by ID
     *
     * <p>Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> retrieveProcessWithResponse(UUID id, Context context) {
        return this.service.retrieveProcessWithResponse(id, context);
    }

    /**
     * Get a Process by ID
     *
     * <p>Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process retrieveProcess(UUID id) {
        return this.retrieveProcessWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Change process initiator
     *
     * <p>Change the current initiator of a process.
     *
     * <p>Allows you to choose a user (by email or principal identifier) or an application (principal identifier). Only
     * one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessChangeInitiatorWithResponse(UUID id, ProcessChangeInitiatorCommand command, Context context) {
        return this.service.actionsProcessChangeInitiatorWithResponse(id, command, context);
    }

    /**
     * Change process initiator
     *
     * <p>Change the current initiator of a process.
     *
     * <p>Allows you to choose a user (by email or principal identifier) or an application (principal identifier). Only
     * one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessChangeInitiator(UUID id, ProcessChangeInitiatorCommand command) {
        return this.actionsProcessChangeInitiatorWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save a process element, aka: metadata
     *
     * <p>Allow to save an element.
     *
     * <p>If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted.
     *
     * <p>If the process is already finished the invocations fails with an error.
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
    public Response<Process> actionsProcessSaveElementWithResponse(UUID id, ProcessSaveElementCommand command, Context context) {
        return this.service.actionsProcessSaveElementWithResponse(id, command, context);
    }

    /**
     * Save a process element, aka: metadata
     *
     * <p>Allow to save an element.
     *
     * <p>If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted.
     *
     * <p>If the process is already finished the invocations fails with an error.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessSaveElement(UUID id, ProcessSaveElementCommand command) {
        return this.actionsProcessSaveElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Delete an element by code
     *
     * <p>Allow to delete a process element by specifying the item definition code.
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
    public Response<Process> actionsProcessDeleteElementWithResponse(UUID id, ProcessDeleteElementCommand command, Context context) {
        return this.service.actionsProcessDeleteElementWithResponse(id, command, context);
    }

    /**
     * Delete an element by code
     *
     * <p>Allow to delete a process element by specifying the item definition code.
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
    public Process actionsProcessDeleteElement(UUID id, ProcessDeleteElementCommand command) {
        return this.actionsProcessDeleteElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Complete a Process
     *
     * <p>Complete a Process. The state of Process is set to 'completed'.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessCompleteWithResponse(UUID id, Context context) {
        return this.service.actionsProcessCompleteWithResponse(id, context);
    }

    /**
     * Complete a Process
     *
     * <p>Complete a Process. The state of Process is set to 'completed'.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessComplete(UUID id) {
        return this.actionsProcessCompleteWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Cancel a Process
     *
     * <p>Cancel a Process. The Process state is set to 'cancelled'.
     *
     * <p>All the active tasks will be marked as cancelled too.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> actionsProcessCancelWithResponse(UUID id, Context context) {
        return this.service.actionsProcessCancelWithResponse(id, context);
    }

    /**
     * Cancel a Process
     *
     * <p>Cancel a Process. The Process state is set to 'cancelled'.
     *
     * <p>All the active tasks will be marked as cancelled too.
     *
     * <p>If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessCancel(UUID id) {
        return this.actionsProcessCancelWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Response<Process> actionsProcessSaveUserActionValueDocumentWithResponse(
        UUID id,
        ProcessSaveUserActionValueDocumentCommand command,
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
        UUID userActionValueId = command.getUserActionValueId();
        BinaryData file = document.getFileContent();
        long contentLength = file.getLength();

        return this.service.actionsProcessSaveUserActionValueDocumentWithResponse(
                id,
                fileContentType,
                fileName,
                userActionValueId,
                file,
                contentLength,
                context
            );
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Process actionsProcessSaveUserActionValueDocument(
        UUID id,
        ProcessSaveUserActionValueDocumentCommand command,
        Document document
    ) {
        return this.actionsProcessSaveUserActionValueDocumentWithResponse(id, command, document, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
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
    public Response<Process> actionsProcessSaveEntityDataWithResponse(UUID id, ProcessSaveEntityDataCommand command, Context context) {
        return this.service.actionsProcessSaveEntityDataWithResponse(id, command, context);
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process actionsProcessSaveEntityData(UUID id, ProcessSaveEntityDataCommand command) {
        return this.actionsProcessSaveEntityDataWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save an entity value document
     * <p>
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessSaveEntityDocumentResponseCommand> actionsProcessSaveEntityDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return this.service.actionsProcessSaveEntityDocumentWithResponse(
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
     * Save an entity value document
     * <p>
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessSaveEntityDocumentResponseCommand actionsProcessSaveEntityDocument(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return this.actionsProcessSaveEntityDocumentWithResponse(
                id,
                fileContentType,
                fileName,
                schemaPath,
                file,
                contentLength,
                Context.NONE
            )
            .getValue();
    }

    /**
     * Download document
     * <p>
     * Given a process and a documentUri, download a document.
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
    public Response<BinaryData> actionsProcessDownloadEntityDocumentWithResponse(UUID id, String documentUri, Context context) {
        return this.service.actionsProcessDownloadEntityDocumentWithResponse(id, documentUri, context);
    }

    /**
     * Download document
     * <p>
     * Given a process and a documentUri, download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData actionsProcessDownloadEntityDocument(UUID id, String documentUri) {
        return this.actionsProcessDownloadEntityDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }
}
