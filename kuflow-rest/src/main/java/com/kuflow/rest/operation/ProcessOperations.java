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
import com.kuflow.rest.model.DocumentReference;
import com.kuflow.rest.model.JsonPatchOperation;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessChangeInitiatorParams;
import com.kuflow.rest.model.ProcessCreateParams;
import com.kuflow.rest.model.ProcessEntityDocumentUploadParams;
import com.kuflow.rest.model.ProcessEntityUpdateParams;
import com.kuflow.rest.model.ProcessFindOptions;
import com.kuflow.rest.model.ProcessMetadataUpdateParams;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.ProcessUserActionDocumentUploadParams;
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
    public Response<ProcessPage> findProcessesWithResponse(ProcessFindOptions options, Context context) {
        options = options != null ? options : new ProcessFindOptions();

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
    public ProcessPage findProcesses(ProcessFindOptions options) {
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
     * <p>
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     * <p>
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> createProcessWithResponse(ProcessCreateParams params, Context context) {
        return this.service.createProcessWithResponse(params, context);
    }

    /**
     * Create a new process
     * <p>
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     * <p>
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process createProcess(ProcessCreateParams params) {
        return this.createProcessWithResponse(params, Context.NONE).getValue();
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
    public Response<Process> completeProcessWithResponse(UUID id, Context context) {
        return this.service.completeProcessWithResponse(id, context);
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
    public Process completeProcess(UUID id) {
        return this.completeProcessWithResponse(id, Context.NONE).getValue();
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
    public Response<Process> cancelProcessWithResponse(UUID id, Context context) {
        return this.service.cancelProcessWithResponse(id, context);
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
    public Process cancelProcess(UUID id) {
        return this.cancelProcessWithResponse(id, Context.NONE).getValue();
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
     * @param params Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> changeProcessInitiatorWithResponse(UUID id, ProcessChangeInitiatorParams params, Context context) {
        return this.service.changeProcessInitiatorWithResponse(id, params, context);
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
     * @param params Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process changeProcessInitiator(UUID id, ProcessChangeInitiatorParams params) {
        return this.changeProcessInitiatorWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Response<Process> uploadProcessUserActionDocumentWithResponse(
        UUID id,
        ProcessUserActionDocumentUploadParams params,
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
        UUID userActionValueId = params.getUserActionValueId();
        BinaryData file = document.getFileContent();
        long contentLength = file.getLength();

        return this.service.uploadProcessUserActionDocumentWithResponse(
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
    public Process uploadProcessUserActionDocument(UUID id, ProcessUserActionDocumentUploadParams command, Document document) {
        return this.uploadProcessUserActionDocumentWithResponse(id, command, document, Context.NONE).getValue();
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param params Params to save de entity data.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> updateProcessMetadataWithResponse(UUID id, ProcessMetadataUpdateParams params, Context context) {
        return this.service.updateProcessMetadataWithResponse(id, params, context);
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param params Params to save de entity data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process updateProcessMetadata(UUID id, ProcessMetadataUpdateParams params) {
        return this.updateProcessMetadataWithResponse(id, params, Context.NONE).getValue();
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
    public Response<Process> patchProcessMetadataWithResponse(UUID id, List<JsonPatchOperation> params, Context context) {
        return this.service.patchProcessMetadataWithResponse(id, params, context);
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
    public Process patchProcessMetadata(UUID id, List<JsonPatchOperation> params) {
        return this.patchProcessMetadataWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> updateProcessEntityWithResponse(UUID id, ProcessEntityUpdateParams params, Context context) {
        return this.service.updateProcessEntityWithResponse(id, params, context);
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process updateProcessEntity(UUID id, ProcessEntityUpdateParams params) {
        return this.updateProcessEntityWithResponse(id, params, Context.NONE).getValue();
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
    public Response<Process> patchProcessEntityWithResponse(UUID id, List<JsonPatchOperation> params, Context context) {
        return this.service.patchProcessEntityWithResponse(id, params, context);
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
    public Process patchProcessEntity(UUID id, List<JsonPatchOperation> params) {
        return this.patchProcessEntityWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Upload an entity document
     * <p>
     * Save a document in the process to later be linked into the JSON data.
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
    public Response<DocumentReference> uploadProcessEntityDocumentWithResponse(
        UUID id,
        ProcessEntityDocumentUploadParams params,
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
        return this.service.uploadProcessEntityDocumentWithResponse(
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
     * Upload an entity document
     * <p>
     * Save a document in the process to later be linked into the JSON data.
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
    public DocumentReference uploadProcessEntityDocument(UUID id, ProcessEntityDocumentUploadParams params, Document document) {
        return this.uploadProcessEntityDocumentWithResponse(id, params, document, Context.NONE).getValue();
    }

    /**
     * Download entity document
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
    public Response<BinaryData> downloadProcessEntityDocumentWithResponse(UUID id, String documentUri, Context context) {
        return this.service.downloadProcessEntityDocumentWithResponse(id, documentUri, context);
    }

    /**
     * Download entity document
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
    public BinaryData downloadProcessEntityDocument(UUID id, String documentUri) {
        return this.downloadProcessEntityDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }
}
