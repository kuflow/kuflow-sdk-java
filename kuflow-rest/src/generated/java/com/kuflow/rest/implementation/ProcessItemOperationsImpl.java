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
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemCreateParams;
import com.kuflow.rest.model.ProcessItemPage;
import com.kuflow.rest.model.ProcessItemTaskAppendLogParams;
import com.kuflow.rest.model.ProcessItemTaskAssignParams;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import com.kuflow.rest.model.ProcessItemTaskState;
import com.kuflow.rest.model.ProcessItemType;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in ProcessItemOperations.
 */
public final class ProcessItemOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final ProcessItemOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of ProcessItemOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ProcessItemOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(ProcessItemOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientProcessItemOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientProcessI")
    public interface ProcessItemOperationsService {
        @Get("/process-items")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItemPage>> findProcessItems(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "processId", multipleQueryParams = true) List<String> processId,
            @QueryParam(value = "type", multipleQueryParams = true) List<String> type,
            @QueryParam(value = "taskState", multipleQueryParams = true) List<String> taskState,
            @QueryParam(value = "taskDefinitionCode", multipleQueryParams = true) List<String> taskDefinitionCode,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-items")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItemPage> findProcessItemsSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "processId", multipleQueryParams = true) List<String> processId,
            @QueryParam(value = "type", multipleQueryParams = true) List<String> type,
            @QueryParam(value = "taskState", multipleQueryParams = true) List<String> taskState,
            @QueryParam(value = "taskDefinitionCode", multipleQueryParams = true) List<String> taskDefinitionCode,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> createProcessItem(
            @HostParam("$host") String host,
            @BodyParam("application/json") ProcessItemCreateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> createProcessItemSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") ProcessItemCreateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-items/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> retrieveProcessItem(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-items/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> retrieveProcessItemSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/claim")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> claimProcessItemTask(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/claim")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> claimProcessItemTaskSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/assign")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> assignProcessItemTask(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessItemTaskAssignParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/assign")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> assignProcessItemTaskSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessItemTaskAssignParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> completeProcessItemTask(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> completeProcessItemTaskSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/append-log")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> appendProcessItemTaskLog(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessItemTaskAppendLogParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/~actions/append-log")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> appendProcessItemTaskLogSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessItemTaskAppendLogParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/process-items/{id}/task/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> updateProcessItemTaskData(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessItemTaskDataUpdateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/process-items/{id}/task/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> updateProcessItemTaskDataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessItemTaskDataUpdateParams params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/process-items/{id}/task/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessItem>> patchProcessItemTaskData(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/process-items/{id}/task/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessItem> patchProcessItemTaskDataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> params,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/process-items/{id}/task/data/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadProcessItemTaskDataDocument(
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

        @Post("/process-items/{id}/task/data/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadProcessItemTaskDataDocument(
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

        @Post("/process-items/{id}/task/data/~actions/upload-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<DocumentReference> uploadProcessItemTaskDataDocumentSync(
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

        @Get("/process-items/{id}/task/data/~actions/download-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> downloadProcessItemTaskDataDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-items/{id}/task/data/~actions/download-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> downloadProcessItemTaskDataDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-items/{id}/task/data/~actions/download-webforms-as-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> downloadProcessItemTaskDataWebformsAsDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("propertyPath") String propertyPath,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-items/{id}/task/data/~actions/download-webforms-as-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> downloadProcessItemTaskDataWebformsAsDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("propertyPath") String propertyPath,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param processId Filter by an array of process ids.
     * @param type Filter by an array of type.
     * @param taskState Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItemPage>> findProcessItemsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<ProcessItemType> type,
        List<ProcessItemTaskState> taskState,
        List<String> taskDefinitionCode,
        List<UUID> tenantId
    ) {
        return FluxUtil.withContext(context ->
            findProcessItemsWithResponseAsync(size, page, sort, processId, type, taskState, taskDefinitionCode, tenantId, context)
        );
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param processId Filter by an array of process ids.
     * @param type Filter by an array of type.
     * @param taskState Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItemPage>> findProcessItemsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<ProcessItemType> type,
        List<ProcessItemTaskState> taskState,
        List<String> taskDefinitionCode,
        List<UUID> tenantId,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> processIdConverted = (processId == null)
            ? new ArrayList<>()
            : processId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> typeConverted = (type == null)
            ? new ArrayList<>()
            : type.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskStateConverted = (taskState == null)
            ? new ArrayList<>()
            : taskState.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskDefinitionCodeConverted = (taskDefinitionCode == null)
            ? new ArrayList<>()
            : taskDefinitionCode.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findProcessItems(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            processIdConverted,
            typeConverted,
            taskStateConverted,
            taskDefinitionCodeConverted,
            tenantIdConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param processId Filter by an array of process ids.
     * @param type Filter by an array of type.
     * @param taskState Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItemPage> findProcessItemsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<ProcessItemType> type,
        List<ProcessItemTaskState> taskState,
        List<String> taskDefinitionCode,
        List<UUID> tenantId
    ) {
        return findProcessItemsWithResponseAsync(size, page, sort, processId, type, taskState, taskDefinitionCode, tenantId).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItemPage> findProcessItemsAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> processId = null;
        final List<ProcessItemType> type = null;
        final List<ProcessItemTaskState> taskState = null;
        final List<String> taskDefinitionCode = null;
        final List<UUID> tenantId = null;
        return findProcessItemsWithResponseAsync(size, page, sort, processId, type, taskState, taskDefinitionCode, tenantId).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param processId Filter by an array of process ids.
     * @param type Filter by an array of type.
     * @param taskState Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItemPage> findProcessItemsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<ProcessItemType> type,
        List<ProcessItemTaskState> taskState,
        List<String> taskDefinitionCode,
        List<UUID> tenantId,
        Context context
    ) {
        return findProcessItemsWithResponseAsync(
            size,
            page,
            sort,
            processId,
            type,
            taskState,
            taskDefinitionCode,
            tenantId,
            context
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param processId Filter by an array of process ids.
     * @param type Filter by an array of type.
     * @param taskState Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessItemPage> findProcessItemsWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<ProcessItemType> type,
        List<ProcessItemTaskState> taskState,
        List<String> taskDefinitionCode,
        List<UUID> tenantId,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> processIdConverted = (processId == null)
            ? new ArrayList<>()
            : processId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> typeConverted = (type == null)
            ? new ArrayList<>()
            : type.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskStateConverted = (taskState == null)
            ? new ArrayList<>()
            : taskState.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskDefinitionCodeConverted = (taskDefinitionCode == null)
            ? new ArrayList<>()
            : taskDefinitionCode.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findProcessItemsSync(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            processIdConverted,
            typeConverted,
            taskStateConverted,
            taskDefinitionCodeConverted,
            tenantIdConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param processId Filter by an array of process ids.
     * @param type Filter by an array of type.
     * @param taskState Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItemPage findProcessItems(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<ProcessItemType> type,
        List<ProcessItemTaskState> taskState,
        List<String> taskDefinitionCode,
        List<UUID> tenantId
    ) {
        return findProcessItemsWithResponse(
            size,
            page,
            sort,
            processId,
            type,
            taskState,
            taskDefinitionCode,
            tenantId,
            Context.NONE
        ).getValue();
    }

    /**
     * Find all accessible Process Items
     *
     * List all Process Items that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessItemPage findProcessItems() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> processId = null;
        final List<ProcessItemType> type = null;
        final List<ProcessItemTaskState> taskState = null;
        final List<String> taskDefinitionCode = null;
        final List<UUID> tenantId = null;
        return findProcessItemsWithResponse(
            size,
            page,
            sort,
            processId,
            type,
            taskState,
            taskDefinitionCode,
            tenantId,
            Context.NONE
        ).getValue();
    }

    /**
     * Create a new Process Item in the selected Process
     *
     * Create a Process Item and optionally fill its value.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process Item to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> createProcessItemWithResponseAsync(ProcessItemCreateParams params) {
        return FluxUtil.withContext(context -> createProcessItemWithResponseAsync(params, context));
    }

    /**
     * Create a new Process Item in the selected Process
     *
     * Create a Process Item and optionally fill its value.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process Item to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> createProcessItemWithResponseAsync(ProcessItemCreateParams params, Context context) {
        final String accept = "application/json";
        return service.createProcessItem(this.client.getHost(), params, accept, context);
    }

    /**
     * Create a new Process Item in the selected Process
     *
     * Create a Process Item and optionally fill its value.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process Item to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> createProcessItemAsync(ProcessItemCreateParams params) {
        return createProcessItemWithResponseAsync(params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new Process Item in the selected Process
     *
     * Create a Process Item and optionally fill its value.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Process Item to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> createProcessItemAsync(ProcessItemCreateParams params, Context context) {
        return createProcessItemWithResponseAsync(params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new Process Item in the selected Process
     *
     * Create a Process Item and optionally fill its value.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
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
        final String accept = "application/json";
        return service.createProcessItemSync(this.client.getHost(), params, accept, context);
    }

    /**
     * Create a new Process Item in the selected Process
     *
     * Create a Process Item and optionally fill its value.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
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
        return createProcessItemWithResponse(params, Context.NONE).getValue();
    }

    /**
     * Get a process item given it ID
     *
     * Allow to get a process item by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> retrieveProcessItemWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> retrieveProcessItemWithResponseAsync(id, context));
    }

    /**
     * Get a process item given it ID
     *
     * Allow to get a process item by ID.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> retrieveProcessItemWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveProcessItem(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a process item given it ID
     *
     * Allow to get a process item by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> retrieveProcessItemAsync(UUID id) {
        return retrieveProcessItemWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a process item given it ID
     *
     * Allow to get a process item by ID.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> retrieveProcessItemAsync(UUID id, Context context) {
        return retrieveProcessItemWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a process item given it ID
     *
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
        final String accept = "application/json";
        return service.retrieveProcessItemSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a process item given it ID
     *
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
        return retrieveProcessItemWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Claim a process item task
     *
     * Allow to claim a task.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> claimProcessItemTaskWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> claimProcessItemTaskWithResponseAsync(id, context));
    }

    /**
     * Claim a process item task
     *
     * Allow to claim a task.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> claimProcessItemTaskWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.claimProcessItemTask(this.client.getHost(), id, accept, context);
    }

    /**
     * Claim a process item task
     *
     * Allow to claim a task.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> claimProcessItemTaskAsync(UUID id) {
        return claimProcessItemTaskWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Claim a process item task
     *
     * Allow to claim a task.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> claimProcessItemTaskAsync(UUID id, Context context) {
        return claimProcessItemTaskWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Claim a process item task
     *
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
        final String accept = "application/json";
        return service.claimProcessItemTaskSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Claim a process item task
     *
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
        return claimProcessItemTaskWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Assign a process item task
     *
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param params Params to change the process item task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> assignProcessItemTaskWithResponseAsync(UUID id, ProcessItemTaskAssignParams params) {
        return FluxUtil.withContext(context -> assignProcessItemTaskWithResponseAsync(id, params, context));
    }

    /**
     * Assign a process item task
     *
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param params Params to change the process item task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> assignProcessItemTaskWithResponseAsync(
        UUID id,
        ProcessItemTaskAssignParams params,
        Context context
    ) {
        final String accept = "application/json";
        return service.assignProcessItemTask(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Assign a process item task
     *
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param params Params to change the process item task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> assignProcessItemTaskAsync(UUID id, ProcessItemTaskAssignParams params) {
        return assignProcessItemTaskWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Assign a process item task
     *
     * Allow to assign a process item task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param params Params to change the process item task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> assignProcessItemTaskAsync(UUID id, ProcessItemTaskAssignParams params, Context context) {
        return assignProcessItemTaskWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Assign a process item task
     *
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
        final String accept = "application/json";
        return service.assignProcessItemTaskSync(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Assign a process item task
     *
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
        return assignProcessItemTaskWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Complete a process item task
     *
     * Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> completeProcessItemTaskWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> completeProcessItemTaskWithResponseAsync(id, context));
    }

    /**
     * Complete a process item task
     *
     * Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> completeProcessItemTaskWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.completeProcessItemTask(this.client.getHost(), id, accept, context);
    }

    /**
     * Complete a process item task
     *
     * Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> completeProcessItemTaskAsync(UUID id) {
        return completeProcessItemTaskWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Complete a process item task
     *
     * Allow to complete a claimed task by the principal.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> completeProcessItemTaskAsync(UUID id, Context context) {
        return completeProcessItemTaskWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Complete a process item task
     *
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
        final String accept = "application/json";
        return service.completeProcessItemTaskSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Complete a process item task
     *
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
        return completeProcessItemTaskWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Append a log to the process item task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param params Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> appendProcessItemTaskLogWithResponseAsync(UUID id, ProcessItemTaskAppendLogParams params) {
        return FluxUtil.withContext(context -> appendProcessItemTaskLogWithResponseAsync(id, params, context));
    }

    /**
     * Append a log to the process item task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param params Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> appendProcessItemTaskLogWithResponseAsync(
        UUID id,
        ProcessItemTaskAppendLogParams params,
        Context context
    ) {
        final String accept = "application/json";
        return service.appendProcessItemTaskLog(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Append a log to the process item task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param params Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> appendProcessItemTaskLogAsync(UUID id, ProcessItemTaskAppendLogParams params) {
        return appendProcessItemTaskLogWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Append a log to the process item task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param params Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> appendProcessItemTaskLogAsync(UUID id, ProcessItemTaskAppendLogParams params, Context context) {
        return appendProcessItemTaskLogWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Append a log to the process item task
     *
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
        final String accept = "application/json";
        return service.appendProcessItemTaskLogSync(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Append a log to the process item task
     *
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
        return appendProcessItemTaskLogWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> updateProcessItemTaskDataWithResponseAsync(UUID id, ProcessItemTaskDataUpdateParams params) {
        return FluxUtil.withContext(context -> updateProcessItemTaskDataWithResponseAsync(id, params, context));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessItem>> updateProcessItemTaskDataWithResponseAsync(
        UUID id,
        ProcessItemTaskDataUpdateParams params,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessItemTaskData(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> updateProcessItemTaskDataAsync(UUID id, ProcessItemTaskDataUpdateParams params) {
        return updateProcessItemTaskDataWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessItem> updateProcessItemTaskDataAsync(UUID id, ProcessItemTaskDataUpdateParams params, Context context) {
        return updateProcessItemTaskDataWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
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
        final String accept = "application/json";
        return service.updateProcessItemTaskDataSync(this.client.getHost(), id, params, accept, context);
    }

    /**
     * Save JSON data
     *
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
        return updateProcessItemTaskDataWithResponse(id, params, Context.NONE).getValue();
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
    public Mono<Response<ProcessItem>> patchProcessItemTaskDataWithResponseAsync(UUID id, List<JsonPatchOperation> params) {
        return FluxUtil.withContext(context -> patchProcessItemTaskDataWithResponseAsync(id, params, context));
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
    public Mono<Response<ProcessItem>> patchProcessItemTaskDataWithResponseAsync(
        UUID id,
        List<JsonPatchOperation> params,
        Context context
    ) {
        final String accept = "application/json";
        return service.patchProcessItemTaskData(this.client.getHost(), id, params, accept, context);
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
    public Mono<ProcessItem> patchProcessItemTaskDataAsync(UUID id, List<JsonPatchOperation> params) {
        return patchProcessItemTaskDataWithResponseAsync(id, params).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
    public Mono<ProcessItem> patchProcessItemTaskDataAsync(UUID id, List<JsonPatchOperation> params, Context context) {
        return patchProcessItemTaskDataWithResponseAsync(id, params, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
    public Response<ProcessItem> patchProcessItemTaskDataWithResponse(UUID id, List<JsonPatchOperation> params, Context context) {
        final String accept = "application/json";
        return service.patchProcessItemTaskDataSync(this.client.getHost(), id, params, accept, context);
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
    public ProcessItem patchProcessItemTaskData(UUID id, List<JsonPatchOperation> params) {
        return patchProcessItemTaskDataWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<Response<DocumentReference>> uploadProcessItemTaskDataDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessItemTaskDataDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength, context)
        );
    }

    /**
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<Response<DocumentReference>> uploadProcessItemTaskDataDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessItemTaskDataDocument(
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
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<DocumentReference> uploadProcessItemTaskDataDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return uploadProcessItemTaskDataDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<DocumentReference> uploadProcessItemTaskDataDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return uploadProcessItemTaskDataDocumentWithResponseAsync(
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
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<Response<DocumentReference>> uploadProcessItemTaskDataDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadProcessItemTaskDataDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength, context)
        );
    }

    /**
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<Response<DocumentReference>> uploadProcessItemTaskDataDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessItemTaskDataDocument(
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
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<DocumentReference> uploadProcessItemTaskDataDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessItemTaskDataDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Mono<DocumentReference> uploadProcessItemTaskDataDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return uploadProcessItemTaskDataDocumentWithResponseAsync(
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
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Response<DocumentReference> uploadProcessItemTaskDataDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadProcessItemTaskDataDocumentSync(
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
     * Upload a document associated with the process item task data
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public DocumentReference uploadProcessItemTaskDataDocument(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return uploadProcessItemTaskDataDocumentWithResponse(
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
     * Download document
     *
     * Given a task, download a document from a json form data.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadProcessItemTaskDataDocumentWithResponseAsync(UUID id, String documentUri) {
        return FluxUtil.withContext(context -> downloadProcessItemTaskDataDocumentWithResponseAsync(id, documentUri, context));
    }

    /**
     * Download document
     *
     * Given a task, download a document from a json form data.
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
    public Mono<Response<BinaryData>> downloadProcessItemTaskDataDocumentWithResponseAsync(UUID id, String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.downloadProcessItemTaskDataDocument(this.client.getHost(), id, documentUri, accept, context);
    }

    /**
     * Download document
     *
     * Given a task, download a document from a json form data.
     *
     * @param id The resource ID.
     * @param documentUri Document URI to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadProcessItemTaskDataDocumentAsync(UUID id, String documentUri) {
        return downloadProcessItemTaskDataDocumentWithResponseAsync(id, documentUri).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download document
     *
     * Given a task, download a document from a json form data.
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
    public Mono<BinaryData> downloadProcessItemTaskDataDocumentAsync(UUID id, String documentUri, Context context) {
        return downloadProcessItemTaskDataDocumentWithResponseAsync(id, documentUri, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Download document
     *
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
        final String accept = "application/octet-stream, application/json";
        return service.downloadProcessItemTaskDataDocumentSync(this.client.getHost(), id, documentUri, accept, context);
    }

    /**
     * Download document
     *
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
        return downloadProcessItemTaskDataDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadProcessItemTaskDataWebformsAsDocumentWithResponseAsync(UUID id, String propertyPath) {
        return FluxUtil.withContext(context -> downloadProcessItemTaskDataWebformsAsDocumentWithResponseAsync(id, propertyPath, context));
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadProcessItemTaskDataWebformsAsDocumentWithResponseAsync(
        UUID id,
        String propertyPath,
        Context context
    ) {
        final String accept = "application/pdf, application/zip, application/json";
        return service.downloadProcessItemTaskDataWebformsAsDocument(this.client.getHost(), id, propertyPath, accept, context);
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadProcessItemTaskDataWebformsAsDocumentAsync(UUID id, String propertyPath) {
        return downloadProcessItemTaskDataWebformsAsDocumentWithResponseAsync(id, propertyPath).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadProcessItemTaskDataWebformsAsDocumentAsync(UUID id, String propertyPath, Context context) {
        return downloadProcessItemTaskDataWebformsAsDocumentWithResponseAsync(id, propertyPath, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadProcessItemTaskDataWebformsAsDocumentWithResponse(UUID id, String propertyPath, Context context) {
        final String accept = "application/pdf, application/zip, application/json";
        return service.downloadProcessItemTaskDataWebformsAsDocumentSync(this.client.getHost(), id, propertyPath, accept, context);
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow&#064;kuflow.com.
     *
     * @param id The resource ID.
     * @param propertyPath JSON pointer to the property with the error. See:
     * https://datatracker.ietf.org/doc/html/rfc6901
     *
     * ie: /user/name or /users/1/name.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadProcessItemTaskDataWebformsAsDocument(UUID id, String propertyPath) {
        return downloadProcessItemTaskDataWebformsAsDocumentWithResponse(id, propertyPath, Context.NONE).getValue();
    }
}
