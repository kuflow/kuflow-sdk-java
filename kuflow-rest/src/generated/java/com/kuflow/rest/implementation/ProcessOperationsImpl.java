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
            @BodyParam("application/json") ProcessCreateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> createProcessSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") ProcessCreateParams params,
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
            @BodyParam("application/json") ProcessChangeInitiatorParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/change-initiator")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> changeProcessInitiatorSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessChangeInitiatorParams params,
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
            @BodyParam("application/json") ProcessMetadataUpdateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/processes/{id}/metadata")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> updateProcessMetadataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessMetadataUpdateParams params,
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
            @BodyParam("application/json") ProcessEntityUpdateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> updateProcessEntitySync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessEntityUpdateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> patchProcessEntity(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/processes/{id}/entity")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> patchProcessEntitySync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/entity/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadProcessEntityDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("schemaPath") String schemaPath,
            @BodyParam("application/octet-stream") Flux<ByteBuffer> file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/entity/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadProcessEntityDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("schemaPath") String schemaPath,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/entity/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<DocumentReference> uploadProcessEntityDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("schemaPath") String schemaPath,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes/{id}/entity/~actions/download-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> downloadProcessEntityDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/processes/{id}/entity/~actions/download-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> downloadProcessEntityDocumentSync(
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
     * @param params Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> createProcessWithResponseAsync(ProcessCreateParams params) {
        return FluxUtil.withContext(context -> createProcessWithResponseAsync(params, context));
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> createProcessWithResponseAsync(ProcessCreateParams params, Context context) {
        final String accept = "application/json";
        return service.createProcess(this.client.getHost(), params, accept, context);
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> createProcessAsync(ProcessCreateParams params) {
        return createProcessWithResponseAsync(params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> createProcessAsync(ProcessCreateParams params, Context context) {
        return createProcessWithResponseAsync(params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
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
        final String accept = "application/json";
        return service.createProcessSync(this.client.getHost(), params, accept, context);
    }

    /**
     * Create a new process
     *
     * Creates a process. This option has direct correspondence to the action of starting a process in the Kuflow GUI.
     *
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
        return createProcessWithResponse(params, Context.NONE).getValue();
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
     * @param params Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> changeProcessInitiatorWithResponseAsync(UUID id, ProcessChangeInitiatorParams params) {
        return FluxUtil.withContext(context -> changeProcessInitiatorWithResponseAsync(id, params, context));
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
     * @param params Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> changeProcessInitiatorWithResponseAsync(UUID id, ProcessChangeInitiatorParams params, Context context) {
        final String accept = "application/json";
        return service.changeProcessInitiator(this.client.getHost(), id, params, accept, context);
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
     * @param params Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> changeProcessInitiatorAsync(UUID id, ProcessChangeInitiatorParams params) {
        return changeProcessInitiatorWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @param params Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> changeProcessInitiatorAsync(UUID id, ProcessChangeInitiatorParams params, Context context) {
        return changeProcessInitiatorWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @param params Params to change the process initiator.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Process> changeProcessInitiatorWithResponse(UUID id, ProcessChangeInitiatorParams params, Context context) {
        final String accept = "application/json";
        return service.changeProcessInitiatorSync(this.client.getHost(), id, params, accept, context);
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
     * @param params Params to change the process initiator.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process changeProcessInitiator(UUID id, ProcessChangeInitiatorParams params) {
        return changeProcessInitiatorWithResponse(id, params, Context.NONE).getValue();
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
     * @param params Params to save de entity data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessMetadataWithResponseAsync(UUID id, ProcessMetadataUpdateParams params) {
        return FluxUtil.withContext(context -> updateProcessMetadataWithResponseAsync(id, params, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessMetadataWithResponseAsync(UUID id, ProcessMetadataUpdateParams params, Context context) {
        final String accept = "application/json";
        return service.updateProcessMetadata(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Save process metadata.
     *
     * @param id The resource ID.
     * @param params Params to save de entity data.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessMetadataAsync(UUID id, ProcessMetadataUpdateParams params) {
        return updateProcessMetadataWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessMetadataAsync(UUID id, ProcessMetadataUpdateParams params, Context context) {
        return updateProcessMetadataWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.updateProcessMetadataSync(this.client.getHost(), id, params, accept, context);
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
        return updateProcessMetadataWithResponse(id, params, Context.NONE).getValue();
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
     * @param params Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessEntityWithResponseAsync(UUID id, ProcessEntityUpdateParams params) {
        return FluxUtil.withContext(context -> updateProcessEntityWithResponseAsync(id, params, context));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> updateProcessEntityWithResponseAsync(UUID id, ProcessEntityUpdateParams params, Context context) {
        final String accept = "application/json";
        return service.updateProcessEntity(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessEntityAsync(UUID id, ProcessEntityUpdateParams params) {
        return updateProcessEntityWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> updateProcessEntityAsync(UUID id, ProcessEntityUpdateParams params, Context context) {
        return updateProcessEntityWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
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
        final String accept = "application/json";
        return service.updateProcessEntitySync(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Save JSON data
     *
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
        return updateProcessEntityWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> patchProcessEntityWithResponseAsync(UUID id, List<JsonPatchOperation> params) {
        return FluxUtil.withContext(context -> patchProcessEntityWithResponseAsync(id, params, context));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> patchProcessEntityWithResponseAsync(UUID id, List<JsonPatchOperation> params, Context context) {
        final String accept = "application/json";
        return service.patchProcessEntity(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> patchProcessEntityAsync(UUID id, List<JsonPatchOperation> params) {
        return patchProcessEntityWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> patchProcessEntityAsync(UUID id, List<JsonPatchOperation> params, Context context) {
        return patchProcessEntityWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
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
    public Response<Process> patchProcessEntityWithResponse(UUID id, List<JsonPatchOperation> params, Context context) {
        final String accept = "application/json";
        return service.patchProcessEntitySync(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Patch JSON data
     *
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Process patchProcessEntity(UUID id, List<JsonPatchOperation> params) {
        return patchProcessEntityWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessEntityDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessEntityDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength, context)
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessEntityDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessEntityDocument(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessEntityDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return uploadProcessEntityDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessEntityDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return uploadProcessEntityDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            context
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessEntityDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessEntityDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength, context)
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadProcessEntityDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessEntityDocument(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessEntityDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessEntityDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadProcessEntityDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return uploadProcessEntityDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            context
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DocumentReference> uploadProcessEntityDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessEntityDocumentSync(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload an entity document
     *
     * Save a document in the process to later be linked into the JSON data.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param schemaPath JSON Schema path related to the document. The uploaded document will be validated by the passed
     * schema path.
     *
     * ie: "#/properties/file", "#/definitions/UserType/name".
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DocumentReference uploadProcessEntityDocument(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessEntityDocumentWithResponse(
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            Context.NONE
        ).getValue();
    }

    /**
     * Download entity document
     *
     * Given a process and a documentUri, download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadProcessEntityDocumentWithResponseAsync(UUID id, String documentUri) {
        return FluxUtil.withContext(context -> downloadProcessEntityDocumentWithResponseAsync(id, documentUri, context));
    }

    /**
     * Download entity document
     *
     * Given a process and a documentUri, download a document.
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
    public Mono<Response<BinaryData>> downloadProcessEntityDocumentWithResponseAsync(UUID id, String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.downloadProcessEntityDocument(this.client.getHost(), id, documentUri, accept, context);
    }

    /**
     * Download entity document
     *
     * Given a process and a documentUri, download a document.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadProcessEntityDocumentAsync(UUID id, String documentUri) {
        return downloadProcessEntityDocumentWithResponseAsync(id, documentUri).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download entity document
     *
     * Given a process and a documentUri, download a document.
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
    public Mono<BinaryData> downloadProcessEntityDocumentAsync(UUID id, String documentUri, Context context) {
        return downloadProcessEntityDocumentWithResponseAsync(id, documentUri, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download entity document
     *
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
        final String accept = "application/octet-stream, application/json";
        return service.downloadProcessEntityDocumentSync(this.client.getHost(), id, documentUri, accept, context);
    }

    /**
     * Download entity document
     *
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
        return downloadProcessEntityDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }
}
