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
package com.kuflow.rest.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Patch;
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.DocumentReference;
import com.kuflow.rest.model.JsonPatchOperation;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessChangeInitiatorParams;
import com.kuflow.rest.model.ProcessCreateParams;
import com.kuflow.rest.model.ProcessEntityUpdateParams;
import com.kuflow.rest.model.ProcessMetadataUpdateParams;
import com.kuflow.rest.model.ProcessPage;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in ProcessOperations.
 */
public final class ProcessOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final ProcessOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of ProcessOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ProcessOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(ProcessOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientProcessOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientProcessO")
    public interface ProcessOperationsService {
        @Get("/processes")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessPage>> findProcesses(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessPage> findProcessesSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> createProcess(
            @HostParam("$host") String host,
            @BodyParam("application/json") ProcessCreateParams processCreateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> createProcessSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") ProcessCreateParams processCreateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> retrieveProcess(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> retrieveProcessSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> completeProcess(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> completeProcessSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/cancel")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> cancelProcess(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/cancel")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> cancelProcessSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/change-initiator")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> changeProcessInitiator(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessChangeInitiatorParams processChangeInitiatorParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/change-initiator")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> changeProcessInitiatorSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessChangeInitiatorParams processChangeInitiatorParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/upload-user-action-document")
        @ExpectedResponses({ 200, 304 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> uploadProcessUserActionDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("userActionValueId") UUID userActionValueId,
            @BodyParam("application/octet-stream") Flux<ByteBuffer> file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/upload-user-action-document")
        @ExpectedResponses({ 200, 304 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> uploadProcessUserActionDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("userActionValueId") UUID userActionValueId,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/upload-user-action-document")
        @ExpectedResponses({ 200, 304 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> uploadProcessUserActionDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("userActionValueId") UUID userActionValueId,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/processes/{id}/metadata")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> updateProcessMetadata(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessMetadataUpdateParams processMetadataUpdateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/processes/{id}/metadata")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> updateProcessMetadataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessMetadataUpdateParams processMetadataUpdateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/processes/{id}/metadata")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> patchProcessMetadata(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> jsonPatch,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/processes/{id}/metadata")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> patchProcessMetadataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> jsonPatch,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> updateProcessEntity(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessEntityUpdateParams processEntityUpdateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> updateProcessEntitySync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessEntityUpdateParams processEntityUpdateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> patchProcessEntity(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> jsonPatch,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> patchProcessEntitySync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> jsonPatch,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadProcessDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @BodyParam("application/octet-stream") Flux<ByteBuffer> file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadProcessDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<DocumentReference> uploadProcessDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes/{id}/~actions/download-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> downloadProcessDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes/{id}/~actions/download-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> downloadProcessDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessPage>> findProcessesWithResponseAsync(Integer size, Integer page, List<String> sort, List<UUID> tenantId) {
        return FluxUtil.withContext(context -> findProcessesWithResponseAsync(size, page, sort, tenantId, context));
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessPage>> findProcessesWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findProcesses(this.client.getHost(), size, page, sortConverted, tenantIdConverted, accept, context);
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessPage> findProcessesAsync(Integer size, Integer page, List<String> sort, List<UUID> tenantId) {
        return findProcessesWithResponseAsync(size, page, sort, tenantId).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessPage> findProcessesAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        return findProcessesWithResponseAsync(size, page, sort, tenantId).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessPage> findProcessesAsync(Integer size, Integer page, List<String> sort, List<UUID> tenantId, Context context) {
        return findProcessesWithResponseAsync(size, page, sort, tenantId, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessPage> findProcessesWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findProcessesSync(this.client.getHost(), size, page, sortConverted, tenantIdConverted, accept, context);
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses(Integer size, Integer page, List<String> sort, List<UUID> tenantId) {
        return findProcessesWithResponse(size, page, sort, tenantId, Context.NONE).getValue();
    }

    /**
     * Find all accessible Processes
     *
     * List all the Processes that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        return findProcessesWithResponse(size, page, sort, tenantId, Context.NONE).getValue();
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param processCreateParams Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> createProcessWithResponseAsync(ProcessCreateParams processCreateParams) {
        return FluxUtil.withContext(context -> createProcessWithResponseAsync(processCreateParams, context));
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param processCreateParams Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> createProcessWithResponseAsync(ProcessCreateParams processCreateParams, Context context) {
        final String accept = "application/json";
        return service.createProcess(this.client.getHost(), processCreateParams, accept, context);
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param processCreateParams Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> createProcessAsync(ProcessCreateParams processCreateParams) {
        return createProcessWithResponseAsync(processCreateParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param processCreateParams Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> createProcessAsync(ProcessCreateParams processCreateParams, Context context) {
        return createProcessWithResponseAsync(processCreateParams, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param processCreateParams Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> createProcessWithResponse(ProcessCreateParams processCreateParams, Context context) {
        final String accept = "application/json";
        return service.createProcessSync(this.client.getHost(), processCreateParams, accept, context);
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param processCreateParams Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process createProcess(ProcessCreateParams processCreateParams) {
        return createProcessWithResponse(processCreateParams, Context.NONE).getValue();
    }

    /**
     * Get a Process by ID
     *
     * Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> retrieveProcessWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> retrieveProcessWithResponseAsync(id, context));
    }

    /**
     * Get a Process by ID
     *
     * Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> retrieveProcessWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveProcess(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Process by ID
     *
     * Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> retrieveProcessAsync(UUID id) {
        return retrieveProcessWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Process by ID
     *
     * Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> retrieveProcessAsync(UUID id, Context context) {
        return retrieveProcessWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Process by ID
     *
     * Returns the requested Process when has access to do it.
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
        final String accept = "application/json";
        return service.retrieveProcessSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Process by ID
     *
     * Returns the requested Process when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process retrieveProcess(UUID id) {
        return retrieveProcessWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Complete a Process
     *
     * Complete a Process. The state of Process is set to 'completed'.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> completeProcessWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> completeProcessWithResponseAsync(id, context));
    }

    /**
     * Complete a Process
     *
     * Complete a Process. The state of Process is set to 'completed'.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> completeProcessWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.completeProcess(this.client.getHost(), id, accept, context);
    }

    /**
     * Complete a Process
     *
     * Complete a Process. The state of Process is set to 'completed'.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> completeProcessAsync(UUID id) {
        return completeProcessWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Complete a Process
     *
     * Complete a Process. The state of Process is set to 'completed'.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> completeProcessAsync(UUID id, Context context) {
        return completeProcessWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Complete a Process
     *
     * Complete a Process. The state of Process is set to 'completed'.
     *
     * If you are already in this state, no action is taken.
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
        final String accept = "application/json";
        return service.completeProcessSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Complete a Process
     *
     * Complete a Process. The state of Process is set to 'completed'.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process completeProcess(UUID id) {
        return completeProcessWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Cancel a Process
     *
     * Cancel a Process. The Process state is set to 'cancelled'.
     *
     * All the active process items will be marked as cancelled too.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> cancelProcessWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> cancelProcessWithResponseAsync(id, context));
    }

    /**
     * Cancel a Process
     *
     * Cancel a Process. The Process state is set to 'cancelled'.
     *
     * All the active process items will be marked as cancelled too.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> cancelProcessWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.cancelProcess(this.client.getHost(), id, accept, context);
    }

    /**
     * Cancel a Process
     *
     * Cancel a Process. The Process state is set to 'cancelled'.
     *
     * All the active process items will be marked as cancelled too.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> cancelProcessAsync(UUID id) {
        return cancelProcessWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Cancel a Process
     *
     * Cancel a Process. The Process state is set to 'cancelled'.
     *
     * All the active process items will be marked as cancelled too.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> cancelProcessAsync(UUID id, Context context) {
        return cancelProcessWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Cancel a Process
     *
     * Cancel a Process. The Process state is set to 'cancelled'.
     *
     * All the active process items will be marked as cancelled too.
     *
     * If you are already in this state, no action is taken.
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
        final String accept = "application/json";
        return service.cancelProcessSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Cancel a Process
     *
     * Cancel a Process. The Process state is set to 'cancelled'.
     *
     * All the active process items will be marked as cancelled too.
     *
     * If you are already in this state, no action is taken.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process cancelProcess(UUID id) {
        return cancelProcessWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Change process initiator
     *
     * Change the current initiator of a process.
     *
     * Allows you to choose a user (by email or principal identifier) or an application (principal identifier).
     * Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processChangeInitiatorParams Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> changeProcessInitiatorWithResponseAsync(
        UUID id,
        ProcessChangeInitiatorParams processChangeInitiatorParams
    ) {
        return FluxUtil.withContext(context -> changeProcessInitiatorWithResponseAsync(id, processChangeInitiatorParams, context));
    }

    /**
     * Change process initiator
     *
     * Change the current initiator of a process.
     *
     * Allows you to choose a user (by email or principal identifier) or an application (principal identifier).
     * Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processChangeInitiatorParams Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> changeProcessInitiatorWithResponseAsync(
        UUID id,
        ProcessChangeInitiatorParams processChangeInitiatorParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.changeProcessInitiator(this.client.getHost(), id, processChangeInitiatorParams, accept, context);
    }

    /**
     * Change process initiator
     *
     * Change the current initiator of a process.
     *
     * Allows you to choose a user (by email or principal identifier) or an application (principal identifier).
     * Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processChangeInitiatorParams Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> changeProcessInitiatorAsync(UUID id, ProcessChangeInitiatorParams processChangeInitiatorParams) {
        return changeProcessInitiatorWithResponseAsync(id, processChangeInitiatorParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Change process initiator
     *
     * Change the current initiator of a process.
     *
     * Allows you to choose a user (by email or principal identifier) or an application (principal identifier).
     * Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processChangeInitiatorParams Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> changeProcessInitiatorAsync(UUID id, ProcessChangeInitiatorParams processChangeInitiatorParams, Context context) {
        return changeProcessInitiatorWithResponseAsync(id, processChangeInitiatorParams, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Change process initiator
     *
     * Change the current initiator of a process.
     *
     * Allows you to choose a user (by email or principal identifier) or an application (principal identifier).
     * Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processChangeInitiatorParams Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> changeProcessInitiatorWithResponse(
        UUID id,
        ProcessChangeInitiatorParams processChangeInitiatorParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.changeProcessInitiatorSync(this.client.getHost(), id, processChangeInitiatorParams, accept, context);
    }

    /**
     * Change process initiator
     *
     * Change the current initiator of a process.
     *
     * Allows you to choose a user (by email or principal identifier) or an application (principal identifier).
     * Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param processChangeInitiatorParams Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process changeProcessInitiator(UUID id, ProcessChangeInitiatorParams processChangeInitiatorParams) {
        return changeProcessInitiatorWithResponse(id, processChangeInitiatorParams, Context.NONE).getValue();
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> uploadProcessUserActionDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessUserActionDocumentWithResponseAsync(id, fileContentType, fileName, userActionValueId, file, contentLength, context)
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> uploadProcessUserActionDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessUserActionDocument(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> uploadProcessUserActionDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return uploadProcessUserActionDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> uploadProcessUserActionDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return uploadProcessUserActionDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            context
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> uploadProcessUserActionDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessUserActionDocumentWithResponseAsync(id, fileContentType, fileName, userActionValueId, file, contentLength, context)
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> uploadProcessUserActionDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessUserActionDocument(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> uploadProcessUserActionDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessUserActionDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> uploadProcessUserActionDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return uploadProcessUserActionDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            context
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> uploadProcessUserActionDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessUserActionDocumentSync(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * Allow saving a user action document uploading the content.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param userActionValueId User action value ID related to de document.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process uploadProcessUserActionDocument(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessUserActionDocumentWithResponse(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            Context.NONE
        ).getValue();
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param processMetadataUpdateParams Params to save the metadata data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessMetadataWithResponseAsync(
        UUID id,
        ProcessMetadataUpdateParams processMetadataUpdateParams
    ) {
        return FluxUtil.withContext(context -> updateProcessMetadataWithResponseAsync(id, processMetadataUpdateParams, context));
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param processMetadataUpdateParams Params to save the metadata data.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessMetadataWithResponseAsync(
        UUID id,
        ProcessMetadataUpdateParams processMetadataUpdateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessMetadata(this.client.getHost(), id, processMetadataUpdateParams, accept, context);
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param processMetadataUpdateParams Params to save the metadata data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessMetadataAsync(UUID id, ProcessMetadataUpdateParams processMetadataUpdateParams) {
        return updateProcessMetadataWithResponseAsync(id, processMetadataUpdateParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param processMetadataUpdateParams Params to save the metadata data.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessMetadataAsync(UUID id, ProcessMetadataUpdateParams processMetadataUpdateParams, Context context) {
        return updateProcessMetadataWithResponseAsync(id, processMetadataUpdateParams, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param processMetadataUpdateParams Params to save the metadata data.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> updateProcessMetadataWithResponse(
        UUID id,
        ProcessMetadataUpdateParams processMetadataUpdateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessMetadataSync(this.client.getHost(), id, processMetadataUpdateParams, accept, context);
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param processMetadataUpdateParams Params to save the metadata data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process updateProcessMetadata(UUID id, ProcessMetadataUpdateParams processMetadataUpdateParams) {
        return updateProcessMetadataWithResponse(id, processMetadataUpdateParams, Context.NONE).getValue();
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> patchProcessMetadataWithResponseAsync(UUID id, List<JsonPatchOperation> jsonPatch) {
        return FluxUtil.withContext(context -> patchProcessMetadataWithResponseAsync(id, jsonPatch, context));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> patchProcessMetadataWithResponseAsync(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        final String accept = "application/json";
        return service.patchProcessMetadata(this.client.getHost(), id, jsonPatch, accept, context);
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> patchProcessMetadataAsync(UUID id, List<JsonPatchOperation> jsonPatch) {
        return patchProcessMetadataWithResponseAsync(id, jsonPatch).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> patchProcessMetadataAsync(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        return patchProcessMetadataWithResponseAsync(id, jsonPatch, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> patchProcessMetadataWithResponse(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        final String accept = "application/json";
        return service.patchProcessMetadataSync(this.client.getHost(), id, jsonPatch, accept, context);
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process patchProcessMetadata(UUID id, List<JsonPatchOperation> jsonPatch) {
        return patchProcessMetadataWithResponse(id, jsonPatch, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processEntityUpdateParams Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessEntityWithResponseAsync(UUID id, ProcessEntityUpdateParams processEntityUpdateParams) {
        return FluxUtil.withContext(context -> updateProcessEntityWithResponseAsync(id, processEntityUpdateParams, context));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processEntityUpdateParams Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessEntityWithResponseAsync(
        UUID id,
        ProcessEntityUpdateParams processEntityUpdateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessEntity(this.client.getHost(), id, processEntityUpdateParams, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processEntityUpdateParams Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessEntityAsync(UUID id, ProcessEntityUpdateParams processEntityUpdateParams) {
        return updateProcessEntityWithResponseAsync(id, processEntityUpdateParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processEntityUpdateParams Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessEntityAsync(UUID id, ProcessEntityUpdateParams processEntityUpdateParams, Context context) {
        return updateProcessEntityWithResponseAsync(id, processEntityUpdateParams, context).flatMap(res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processEntityUpdateParams Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> updateProcessEntityWithResponse(
        UUID id,
        ProcessEntityUpdateParams processEntityUpdateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessEntitySync(this.client.getHost(), id, processEntityUpdateParams, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param processEntityUpdateParams Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process updateProcessEntity(UUID id, ProcessEntityUpdateParams processEntityUpdateParams) {
        return updateProcessEntityWithResponse(id, processEntityUpdateParams, Context.NONE).getValue();
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> patchProcessEntityWithResponseAsync(UUID id, List<JsonPatchOperation> jsonPatch) {
        return FluxUtil.withContext(context -> patchProcessEntityWithResponseAsync(id, jsonPatch, context));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> patchProcessEntityWithResponseAsync(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        final String accept = "application/json";
        return service.patchProcessEntity(this.client.getHost(), id, jsonPatch, accept, context);
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> patchProcessEntityAsync(UUID id, List<JsonPatchOperation> jsonPatch) {
        return patchProcessEntityWithResponseAsync(id, jsonPatch).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> patchProcessEntityAsync(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        return patchProcessEntityWithResponseAsync(id, jsonPatch, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> patchProcessEntityWithResponse(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        final String accept = "application/json";
        return service.patchProcessEntitySync(this.client.getHost(), id, jsonPatch, accept, context);
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process patchProcessEntity(UUID id, List<JsonPatchOperation> jsonPatch) {
        return patchProcessEntityWithResponse(id, jsonPatch, Context.NONE).getValue();
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessDocumentWithResponseAsync(id, fileContentType, fileName, file, contentLength, context)
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessDocument(this.client.getHost(), id, fileContentType, fileName, file, contentLength, accept, context);
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return uploadProcessDocumentWithResponseAsync(id, fileContentType, fileName, file, contentLength).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return uploadProcessDocumentWithResponseAsync(id, fileContentType, fileName, file, contentLength, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessDocumentWithResponseAsync(id, fileContentType, fileName, file, contentLength, context)
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessDocument(this.client.getHost(), id, fileContentType, fileName, file, contentLength, accept, context);
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessDocumentWithResponseAsync(id, fileContentType, fileName, file, contentLength).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return uploadProcessDocumentWithResponseAsync(id, fileContentType, fileName, file, contentLength, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DocumentReference> uploadProcessDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessDocumentSync(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload a temporal document into the process that later on must be linked with a process domain resource
     *
     * Upload a temporal document into the process that later on must be linked with a process domain resource.
     *
     * Documents uploaded with this API will be deleted after 24 hours as long as they have not been linked to a
     * process or process item..
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DocumentReference uploadProcessDocument(UUID id, String fileContentType, String fileName, BinaryData file, long contentLength) {
        return uploadProcessDocumentWithResponse(id, fileContentType, fileName, file, contentLength, Context.NONE).getValue();
    }

    /**
     * Download document
     *
     * Given a document uri download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadProcessDocumentWithResponseAsync(UUID id, String documentUri) {
        return FluxUtil.withContext(context -> downloadProcessDocumentWithResponseAsync(id, documentUri, context));
    }

    /**
     * Download document
     *
     * Given a document uri download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadProcessDocumentWithResponseAsync(UUID id, String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.downloadProcessDocument(this.client.getHost(), id, documentUri, accept, context);
    }

    /**
     * Download document
     *
     * Given a document uri download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadProcessDocumentAsync(UUID id, String documentUri) {
        return downloadProcessDocumentWithResponseAsync(id, documentUri).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download document
     *
     * Given a document uri download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadProcessDocumentAsync(UUID id, String documentUri, Context context) {
        return downloadProcessDocumentWithResponseAsync(id, documentUri, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download document
     *
     * Given a document uri download a document.
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
    public Response<BinaryData> downloadProcessDocumentWithResponse(UUID id, String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.downloadProcessDocumentSync(this.client.getHost(), id, documentUri, accept, context);
    }

    /**
     * Download document
     *
     * Given a document uri download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadProcessDocument(UUID id, String documentUri) {
        return downloadProcessDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }
}
