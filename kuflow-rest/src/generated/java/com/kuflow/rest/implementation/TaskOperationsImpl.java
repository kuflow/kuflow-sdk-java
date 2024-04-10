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
import com.azure.core.annotation.PathParam;
import com.azure.core.annotation.Post;
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
import com.kuflow.rest.model.Log;
import com.kuflow.rest.model.Task;
import com.kuflow.rest.model.TaskAssignCommand;
import com.kuflow.rest.model.TaskDeleteElementCommand;
import com.kuflow.rest.model.TaskDeleteElementValueDocumentCommand;
import com.kuflow.rest.model.TaskPage;
import com.kuflow.rest.model.TaskSaveElementCommand;
import com.kuflow.rest.model.TaskSaveJsonFormsValueDataCommand;
import com.kuflow.rest.model.TaskSaveJsonFormsValueDocumentResponseCommand;
import com.kuflow.rest.model.TaskState;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in TaskOperations.
 */
public final class TaskOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final TaskOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of TaskOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    TaskOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(TaskOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientTaskOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientTaskOper")
    public interface TaskOperationsService {
        @Get("/tasks")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<TaskPage>> findTasks(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "processId", multipleQueryParams = true) List<String> processId,
            @QueryParam(value = "state", multipleQueryParams = true) List<String> state,
            @QueryParam(value = "taskDefinitionCode", multipleQueryParams = true) List<String> taskDefinitionCode,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<TaskPage> findTasksSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "processId", multipleQueryParams = true) List<String> processId,
            @QueryParam(value = "state", multipleQueryParams = true) List<String> state,
            @QueryParam(value = "taskDefinitionCode", multipleQueryParams = true) List<String> taskDefinitionCode,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> createTask(
            @HostParam("$host") String host,
            @QueryParam("activityToken") String activityToken,
            @BodyParam("application/json") Task task,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> createTaskSync(
            @HostParam("$host") String host,
            @QueryParam("activityToken") String activityToken,
            @BodyParam("application/json") Task task,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> retrieveTask(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> retrieveTaskSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/claim")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskClaim(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/claim")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskClaimSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/assign")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskAssign(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskAssignCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/assign")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskAssignSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskAssignCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskSaveElement(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskSaveElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskSaveElementSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskSaveElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskSaveElementValueDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("elementDefinitionCode") String elementDefinitionCode,
            @QueryParam("elementValueId") UUID elementValueId,
            @QueryParam("elementValueValid") Boolean elementValueValid,
            @BodyParam("application/octet-stream") Flux<ByteBuffer> file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskSaveElementValueDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("elementDefinitionCode") String elementDefinitionCode,
            @QueryParam("elementValueId") UUID elementValueId,
            @QueryParam("elementValueValid") Boolean elementValueValid,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskSaveElementValueDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @QueryParam("elementDefinitionCode") String elementDefinitionCode,
            @QueryParam("elementValueId") UUID elementValueId,
            @QueryParam("elementValueValid") Boolean elementValueValid,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/delete-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskDeleteElement(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskDeleteElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/delete-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskDeleteElementSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskDeleteElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/delete-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskDeleteElementValueDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskDeleteElementValueDocumentCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/delete-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskDeleteElementValueDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskDeleteElementValueDocumentCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}/~actions/download-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> actionsTaskDownloadElementValueDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentId") UUID documentId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}/~actions/download-element-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> actionsTaskDownloadElementValueDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentId") UUID documentId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}/~actions/download-element-value-form-rendered")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> actionsTaskDownloadElementValueRendered(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("elementDefinitionCode") String elementDefinitionCode,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}/~actions/download-element-value-form-rendered")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> actionsTaskDownloadElementValueRenderedSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("elementDefinitionCode") String elementDefinitionCode,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-json-forms-value-data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskSaveJsonFormsValueData(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskSaveJsonFormsValueDataCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-json-forms-value-data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskSaveJsonFormsValueDataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") TaskSaveJsonFormsValueDataCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/save-json-forms-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<TaskSaveJsonFormsValueDocumentResponseCommand>> actionsTaskSaveJsonFormsValueDocument(
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

        @Post("/tasks/{id}/~actions/save-json-forms-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<TaskSaveJsonFormsValueDocumentResponseCommand>> actionsTaskSaveJsonFormsValueDocument(
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

        @Post("/tasks/{id}/~actions/save-json-forms-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentSync(
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

        @Get("/tasks/{id}/~actions/download-json-forms-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> actionsTaskDownloadJsonFormsValueDocument(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tasks/{id}/~actions/download-json-forms-value-document")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> actionsTaskDownloadJsonFormsValueDocumentSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskComplete(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskCompleteSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/append-log")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Task>> actionsTaskAppendLog(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") Log log,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/tasks/{id}/~actions/append-log")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Task> actionsTaskAppendLogSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") Log log,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
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
     * @param state Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TaskPage>> findTasksWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<TaskState> state,
        List<String> taskDefinitionCode,
        List<UUID> tenantId
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> processIdConverted = (processId == null)
            ? new ArrayList<>()
            : processId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> stateConverted = (state == null)
            ? new ArrayList<>()
            : state.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskDefinitionCodeConverted = (taskDefinitionCode == null)
            ? new ArrayList<>()
            : taskDefinitionCode.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return FluxUtil.withContext(context ->
            service.findTasks(
                this.client.getHost(),
                size,
                page,
                sortConverted,
                processIdConverted,
                stateConverted,
                taskDefinitionCodeConverted,
                tenantIdConverted,
                accept,
                context
            )
        );
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
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
     * @param state Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TaskPage>> findTasksWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<TaskState> state,
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
        List<String> stateConverted = (state == null)
            ? new ArrayList<>()
            : state.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskDefinitionCodeConverted = (taskDefinitionCode == null)
            ? new ArrayList<>()
            : taskDefinitionCode.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findTasks(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            processIdConverted,
            stateConverted,
            taskDefinitionCodeConverted,
            tenantIdConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
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
     * @param state Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskPage> findTasksAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<TaskState> state,
        List<String> taskDefinitionCode,
        List<UUID> tenantId
    ) {
        return findTasksWithResponseAsync(size, page, sort, processId, state, taskDefinitionCode, tenantId)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskPage> findTasksAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> processId = null;
        final List<TaskState> state = null;
        final List<String> taskDefinitionCode = null;
        final List<UUID> tenantId = null;
        return findTasksWithResponseAsync(size, page, sort, processId, state, taskDefinitionCode, tenantId)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
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
     * @param state Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskPage> findTasksAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<TaskState> state,
        List<String> taskDefinitionCode,
        List<UUID> tenantId,
        Context context
    ) {
        return findTasksWithResponseAsync(size, page, sort, processId, state, taskDefinitionCode, tenantId, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
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
     * @param state Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TaskPage> findTasksWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<TaskState> state,
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
        List<String> stateConverted = (state == null)
            ? new ArrayList<>()
            : state.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> taskDefinitionCodeConverted = (taskDefinitionCode == null)
            ? new ArrayList<>()
            : taskDefinitionCode.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findTasksSync(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            processIdConverted,
            stateConverted,
            taskDefinitionCodeConverted,
            tenantIdConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
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
     * @param state Filter by an array of task states.
     * @param taskDefinitionCode Filter by an array of task definition codes.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TaskPage findTasks(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> processId,
        List<TaskState> state,
        List<String> taskDefinitionCode,
        List<UUID> tenantId
    ) {
        return findTasksWithResponse(size, page, sort, processId, state, taskDefinitionCode, tenantId, Context.NONE).getValue();
    }

    /**
     * Find all accessible Tasks
     *
     * List all Tasks that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt, claimedAt, completedAt, cancelledAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TaskPage findTasks() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> processId = null;
        final List<TaskState> state = null;
        final List<String> taskDefinitionCode = null;
        final List<UUID> tenantId = null;
        return findTasksWithResponse(size, page, sort, processId, state, taskDefinitionCode, tenantId, Context.NONE).getValue();
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param activityToken [DEPRECATED] When create a KuFlow Task backed with a Temporal.io servers, this value is
     * required and must be
     * set with the context task token of Temporal.io activity. It is no longer necessary because it will be never
     * used for the latest SDKs versions.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> createTaskWithResponseAsync(Task task, String activityToken) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.createTask(this.client.getHost(), activityToken, task, accept, context));
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param activityToken [DEPRECATED] When create a KuFlow Task backed with a Temporal.io servers, this value is
     * required and must be
     * set with the context task token of Temporal.io activity. It is no longer necessary because it will be never
     * used for the latest SDKs versions.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> createTaskWithResponseAsync(Task task, String activityToken, Context context) {
        final String accept = "application/json";
        return service.createTask(this.client.getHost(), activityToken, task, accept, context);
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param activityToken [DEPRECATED] When create a KuFlow Task backed with a Temporal.io servers, this value is
     * required and must be
     * set with the context task token of Temporal.io activity. It is no longer necessary because it will be never
     * used for the latest SDKs versions.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> createTaskAsync(Task task, String activityToken) {
        return createTaskWithResponseAsync(task, activityToken).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> createTaskAsync(Task task) {
        final String activityToken = null;
        return createTaskWithResponseAsync(task, activityToken).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param activityToken [DEPRECATED] When create a KuFlow Task backed with a Temporal.io servers, this value is
     * required and must be
     * set with the context task token of Temporal.io activity. It is no longer necessary because it will be never
     * used for the latest SDKs versions.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> createTaskAsync(Task task, String activityToken, Context context) {
        return createTaskWithResponseAsync(task, activityToken, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param activityToken [DEPRECATED] When create a KuFlow Task backed with a Temporal.io servers, this value is
     * required and must be
     * set with the context task token of Temporal.io activity. It is no longer necessary because it will be never
     * used for the latest SDKs versions.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> createTaskWithResponse(Task task, String activityToken, Context context) {
        final String accept = "application/json";
        return service.createTaskSync(this.client.getHost(), activityToken, task, accept, context);
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @param activityToken [DEPRECATED] When create a KuFlow Task backed with a Temporal.io servers, this value is
     * required and must be
     * set with the context task token of Temporal.io activity. It is no longer necessary because it will be never
     * used for the latest SDKs versions.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task createTask(Task task, String activityToken) {
        return createTaskWithResponse(task, activityToken, Context.NONE).getValue();
    }

    /**
     * Create a new Task in the selected Process
     *
     * Create a Task and optionally fill its elements. We can fill in any type of element except documents.
     *
     * If you want to add document type elements, you can pass a reference to an existing document type element
     * indicating its 'uri'. This will copy that document into the element. In case you want to add a new document,
     * please use the corresponding API method.
     *
     * If you want that the task created is claimed you can a valid owner using the following options:
     * * If you know the `principal ID` you can assign it to `owner.id`
     * * If you know the `user ID` you can assign it to `owner.user.id`
     * * If you know the `user email` you can assign it to `owner.user.email`
     * * If you know the `application ID` you can assign it to `owner.application.id`
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param task Task to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task createTask(Task task) {
        final String activityToken = null;
        return createTaskWithResponse(task, activityToken, Context.NONE).getValue();
    }

    /**
     * Get a task given it ID
     *
     * Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> retrieveTaskWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.retrieveTask(this.client.getHost(), id, accept, context));
    }

    /**
     * Get a task given it ID
     *
     * Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> retrieveTaskWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveTask(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a task given it ID
     *
     * Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> retrieveTaskAsync(UUID id) {
        return retrieveTaskWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a task given it ID
     *
     * Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> retrieveTaskAsync(UUID id, Context context) {
        return retrieveTaskWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a task given it ID
     *
     * Allow to get a task by ID.
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
        final String accept = "application/json";
        return service.retrieveTaskSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a task given it ID
     *
     * Allow to get a task by ID.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task retrieveTask(UUID id) {
        return retrieveTaskWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Claim a task
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
    public Mono<Response<Task>> actionsTaskClaimWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsTaskClaim(this.client.getHost(), id, accept, context));
    }

    /**
     * Claim a task
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
    public Mono<Response<Task>> actionsTaskClaimWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.actionsTaskClaim(this.client.getHost(), id, accept, context);
    }

    /**
     * Claim a task
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
    public Mono<Task> actionsTaskClaimAsync(UUID id) {
        return actionsTaskClaimWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Claim a task
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
    public Mono<Task> actionsTaskClaimAsync(UUID id, Context context) {
        return actionsTaskClaimWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Claim a task
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
    public Response<Task> actionsTaskClaimWithResponse(UUID id, Context context) {
        final String accept = "application/json";
        return service.actionsTaskClaimSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Claim a task
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
    public Task actionsTaskClaim(UUID id) {
        return actionsTaskClaimWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Assign a task
     *
     * Allow to assign a task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskAssignWithResponseAsync(UUID id, TaskAssignCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsTaskAssign(this.client.getHost(), id, command, accept, context));
    }

    /**
     * Assign a task
     *
     * Allow to assign a task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskAssignWithResponseAsync(UUID id, TaskAssignCommand command, Context context) {
        final String accept = "application/json";
        return service.actionsTaskAssign(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Assign a task
     *
     * Allow to assign a task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the task owner.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskAssignAsync(UUID id, TaskAssignCommand command) {
        return actionsTaskAssignWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Assign a task
     *
     * Allow to assign a task to a user or application. Only one option will be necessary.
     *
     * @param id The resource ID.
     * @param command Command to change the task owner.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskAssignAsync(UUID id, TaskAssignCommand command, Context context) {
        return actionsTaskAssignWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Assign a task
     *
     * Allow to assign a task to a user or application. Only one option will be necessary.
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
        final String accept = "application/json";
        return service.actionsTaskAssignSync(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Assign a task
     *
     * Allow to assign a task to a user or application. Only one option will be necessary.
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
        return actionsTaskAssignWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save an element
     *
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to
     * the document using the 'uri' attribute. In case you want to add a new document, please use the corresponding API
     * method. If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveElementWithResponseAsync(UUID id, TaskSaveElementCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsTaskSaveElement(this.client.getHost(), id, command, accept, context));
    }

    /**
     * Save an element
     *
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to
     * the document using the 'uri' attribute. In case you want to add a new document, please use the corresponding API
     * method. If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveElementWithResponseAsync(UUID id, TaskSaveElementCommand command, Context context) {
        final String accept = "application/json";
        return service.actionsTaskSaveElement(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Save an element
     *
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to
     * the document using the 'uri' attribute. In case you want to add a new document, please use the corresponding API
     * method. If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementAsync(UUID id, TaskSaveElementCommand command) {
        return actionsTaskSaveElementWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element
     *
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to
     * the document using the 'uri' attribute. In case you want to add a new document, please use the corresponding API
     * method. If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
     *
     * @param id The resource ID.
     * @param command Command to save an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementAsync(UUID id, TaskSaveElementCommand command, Context context) {
        return actionsTaskSaveElementWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element
     *
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to
     * the document using the 'uri' attribute. In case you want to add a new document, please use the corresponding API
     * method. If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
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
        final String accept = "application/json";
        return service.actionsTaskSaveElementSync(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Save an element
     *
     * Allow to save an element i.e., a field, a decision, a form, a principal or document.
     *
     * In the case of document type elements, this method only allows references to be made to other existing document
     * type elements for the purpose of copying that file into the element. To do this you need to pass a reference to
     * the document using the 'uri' attribute. In case you want to add a new document, please use the corresponding API
     * method. If values already exist for the provided element code, it replaces them with the new ones, otherwise it
     * creates them. The values of the previous elements that no longer exist will be deleted. To remove an element, use
     * the appropriate API method.
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
        return actionsTaskSaveElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveElementValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        Flux<ByteBuffer> file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskSaveElementValueDocument(
                this.client.getHost(),
                id,
                fileContentType,
                fileName,
                elementDefinitionCode,
                elementValueId,
                elementValueValid,
                file,
                contentLength,
                accept,
                context
            )
        );
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveElementValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        Flux<ByteBuffer> file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveElementValueDocument(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            elementValueId,
            elementValueValid,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        Flux<ByteBuffer> file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid
    ) {
        return actionsTaskSaveElementValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        final UUID elementValueId = null;
        final Boolean elementValueValid = null;
        return actionsTaskSaveElementValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        Flux<ByteBuffer> file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid,
        Context context
    ) {
        return actionsTaskSaveElementValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid,
            context
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveElementValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskSaveElementValueDocument(
                this.client.getHost(),
                id,
                fileContentType,
                fileName,
                elementDefinitionCode,
                elementValueId,
                elementValueValid,
                file,
                contentLength,
                accept,
                context
            )
        );
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveElementValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveElementValueDocument(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            elementValueId,
            elementValueValid,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid
    ) {
        return actionsTaskSaveElementValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength
    ) {
        final UUID elementValueId = null;
        final Boolean elementValueValid = null;
        return actionsTaskSaveElementValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveElementValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid,
        Context context
    ) {
        return actionsTaskSaveElementValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid,
            context
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Task> actionsTaskSaveElementValueDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveElementValueDocumentSync(
            this.client.getHost(),
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            elementValueId,
            elementValueValid,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @param elementValueId Element Value ID.
     * @param elementValueValid Element Value ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskSaveElementValueDocument(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength,
        UUID elementValueId,
        Boolean elementValueValid
    ) {
        return actionsTaskSaveElementValueDocumentWithResponse(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid,
            Context.NONE
        )
            .getValue();
    }

    /**
     * Save an element document
     *
     * Allow to save an element document uploading the content.
     *
     * If it is a multiple element, and the ID referenced in the body does not exist or is empty, the document will be
     * added to the element. If the element already exists (the ID referenced in the body corresponds to an existing
     * one), it updates it.
     *
     * @param id The resource ID.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param elementDefinitionCode Element Definition Code.
     * @param file Command to save a document element value.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Task actionsTaskSaveElementValueDocument(
        UUID id,
        String fileContentType,
        String fileName,
        String elementDefinitionCode,
        BinaryData file,
        long contentLength
    ) {
        final UUID elementValueId = null;
        final Boolean elementValueValid = null;
        return actionsTaskSaveElementValueDocumentWithResponse(
            id,
            fileContentType,
            fileName,
            elementDefinitionCode,
            file,
            contentLength,
            elementValueId,
            elementValueValid,
            Context.NONE
        )
            .getValue();
    }

    /**
     * Delete an element by code
     *
     * Allow to delete task element by specifying the item definition code.
     *
     * Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskDeleteElementWithResponseAsync(UUID id, TaskDeleteElementCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsTaskDeleteElement(this.client.getHost(), id, command, accept, context));
    }

    /**
     * Delete an element by code
     *
     * Allow to delete task element by specifying the item definition code.
     *
     * Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskDeleteElementWithResponseAsync(UUID id, TaskDeleteElementCommand command, Context context) {
        final String accept = "application/json";
        return service.actionsTaskDeleteElement(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Delete an element by code
     *
     * Allow to delete task element by specifying the item definition code.
     *
     * Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskDeleteElementAsync(UUID id, TaskDeleteElementCommand command) {
        return actionsTaskDeleteElementWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Delete an element by code
     *
     * Allow to delete task element by specifying the item definition code.
     *
     * Remove all the element values.
     *
     * @param id The resource ID.
     * @param command Command to delete an element.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskDeleteElementAsync(UUID id, TaskDeleteElementCommand command, Context context) {
        return actionsTaskDeleteElementWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Delete an element by code
     *
     * Allow to delete task element by specifying the item definition code.
     *
     * Remove all the element values.
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
        final String accept = "application/json";
        return service.actionsTaskDeleteElementSync(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Delete an element by code
     *
     * Allow to delete task element by specifying the item definition code.
     *
     * Remove all the element values.
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
        return actionsTaskDeleteElementWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Delete an element document value
     *
     * Allow to delete a specific document from an element of document type using its id.
     *
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
     * addition to the document, it will also delete the element.
     *
     * @param id The resource ID.
     * @param command Command to delete a document element value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskDeleteElementValueDocumentWithResponseAsync(
        UUID id,
        TaskDeleteElementValueDocumentCommand command
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskDeleteElementValueDocument(this.client.getHost(), id, command, accept, context)
        );
    }

    /**
     * Delete an element document value
     *
     * Allow to delete a specific document from an element of document type using its id.
     *
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
     * addition to the document, it will also delete the element.
     *
     * @param id The resource ID.
     * @param command Command to delete a document element value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskDeleteElementValueDocumentWithResponseAsync(
        UUID id,
        TaskDeleteElementValueDocumentCommand command,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskDeleteElementValueDocument(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Delete an element document value
     *
     * Allow to delete a specific document from an element of document type using its id.
     *
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
     * addition to the document, it will also delete the element.
     *
     * @param id The resource ID.
     * @param command Command to delete a document element value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskDeleteElementValueDocumentAsync(UUID id, TaskDeleteElementValueDocumentCommand command) {
        return actionsTaskDeleteElementValueDocumentWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Delete an element document value
     *
     * Allow to delete a specific document from an element of document type using its id.
     *
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
     * addition to the document, it will also delete the element.
     *
     * @param id The resource ID.
     * @param command Command to delete a document element value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskDeleteElementValueDocumentAsync(UUID id, TaskDeleteElementValueDocumentCommand command, Context context) {
        return actionsTaskDeleteElementValueDocumentWithResponseAsync(id, command, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Delete an element document value
     *
     * Allow to delete a specific document from an element of document type using its id.
     *
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
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
        final String accept = "application/json";
        return service.actionsTaskDeleteElementValueDocumentSync(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Delete an element document value
     *
     * Allow to delete a specific document from an element of document type using its id.
     *
     * Note: If it is a multiple item, it will only delete the specified document. If it is a single element, in
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
        return actionsTaskDeleteElementValueDocumentWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Download document
     *
     * Given a task, download a document from an element of document type.
     *
     * @param id The resource ID.
     * @param documentId Document ID to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsTaskDownloadElementValueDocumentWithResponseAsync(UUID id, UUID documentId) {
        final String accept = "application/octet-stream, application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskDownloadElementValueDocument(this.client.getHost(), id, documentId, accept, context)
        );
    }

    /**
     * Download document
     *
     * Given a task, download a document from an element of document type.
     *
     * @param id The resource ID.
     * @param documentId Document ID to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsTaskDownloadElementValueDocumentWithResponseAsync(UUID id, UUID documentId, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsTaskDownloadElementValueDocument(this.client.getHost(), id, documentId, accept, context);
    }

    /**
     * Download document
     *
     * Given a task, download a document from an element of document type.
     *
     * @param id The resource ID.
     * @param documentId Document ID to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsTaskDownloadElementValueDocumentAsync(UUID id, UUID documentId) {
        return actionsTaskDownloadElementValueDocumentWithResponseAsync(id, documentId).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download document
     *
     * Given a task, download a document from an element of document type.
     *
     * @param id The resource ID.
     * @param documentId Document ID to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsTaskDownloadElementValueDocumentAsync(UUID id, UUID documentId, Context context) {
        return actionsTaskDownloadElementValueDocumentWithResponseAsync(id, documentId, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download document
     *
     * Given a task, download a document from an element of document type.
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
        final String accept = "application/octet-stream, application/json";
        return service.actionsTaskDownloadElementValueDocumentSync(this.client.getHost(), id, documentId, accept, context);
    }

    /**
     * Download document
     *
     * Given a task, download a document from an element of document type.
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
        return actionsTaskDownloadElementValueDocumentWithResponse(id, documentId, Context.NONE).getValue();
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow@kuflow.com.
     *
     * @param id The resource ID.
     * @param elementDefinitionCode Element definition code of a Form Element to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsTaskDownloadElementValueRenderedWithResponseAsync(UUID id, String elementDefinitionCode) {
        final String accept = "application/pdf, application/zip, application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskDownloadElementValueRendered(this.client.getHost(), id, elementDefinitionCode, accept, context)
        );
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow@kuflow.com.
     *
     * @param id The resource ID.
     * @param elementDefinitionCode Element definition code of a Form Element to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsTaskDownloadElementValueRenderedWithResponseAsync(
        UUID id,
        String elementDefinitionCode,
        Context context
    ) {
        final String accept = "application/pdf, application/zip, application/json";
        return service.actionsTaskDownloadElementValueRendered(this.client.getHost(), id, elementDefinitionCode, accept, context);
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow@kuflow.com.
     *
     * @param id The resource ID.
     * @param elementDefinitionCode Element definition code of a Form Element to download.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsTaskDownloadElementValueRenderedAsync(UUID id, String elementDefinitionCode) {
        return actionsTaskDownloadElementValueRenderedWithResponseAsync(id, elementDefinitionCode)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow@kuflow.com.
     *
     * @param id The resource ID.
     * @param elementDefinitionCode Element definition code of a Form Element to download.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsTaskDownloadElementValueRenderedAsync(UUID id, String elementDefinitionCode, Context context) {
        return actionsTaskDownloadElementValueRenderedWithResponseAsync(id, elementDefinitionCode, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow@kuflow.com.
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
        final String accept = "application/pdf, application/zip, application/json";
        return service.actionsTaskDownloadElementValueRenderedSync(this.client.getHost(), id, elementDefinitionCode, accept, context);
    }

    /**
     * Download a Form rendered as PDF or Zip of PDFs (when the element is multiple)
     *
     * Given a task, generate a PDF from a Form type element with the data filled in, if any. If there are multiple form
     * values, they are packed into a ZIP.
     *
     * Important!: To use this feature, please contact to kuflow@kuflow.com.
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
        return actionsTaskDownloadElementValueRenderedWithResponse(id, elementDefinitionCode, Context.NONE).getValue();
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveJsonFormsValueDataWithResponseAsync(UUID id, TaskSaveJsonFormsValueDataCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskSaveJsonFormsValueData(this.client.getHost(), id, command, accept, context)
        );
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskSaveJsonFormsValueDataWithResponseAsync(
        UUID id,
        TaskSaveJsonFormsValueDataCommand command,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveJsonFormsValueData(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveJsonFormsValueDataAsync(UUID id, TaskSaveJsonFormsValueDataCommand command) {
        return actionsTaskSaveJsonFormsValueDataWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json form is marked as invalid.
     *
     * @param id The resource ID.
     * @param command Command to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskSaveJsonFormsValueDataAsync(UUID id, TaskSaveJsonFormsValueDataCommand command, Context context) {
        return actionsTaskSaveJsonFormsValueDataWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
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
    public Response<Task> actionsTaskSaveJsonFormsValueDataWithResponse(
        UUID id,
        TaskSaveJsonFormsValueDataCommand command,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveJsonFormsValueDataSync(this.client.getHost(), id, command, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
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
    public Task actionsTaskSaveJsonFormsValueData(UUID id, TaskSaveJsonFormsValueDataCommand command) {
        return actionsTaskSaveJsonFormsValueDataWithResponse(id, command, Context.NONE).getValue();
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TaskSaveJsonFormsValueDocumentResponseCommand>> actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskSaveJsonFormsValueDocument(
                this.client.getHost(),
                id,
                fileContentType,
                fileName,
                schemaPath,
                file,
                contentLength,
                accept,
                context
            )
        );
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TaskSaveJsonFormsValueDocumentResponseCommand>> actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveJsonFormsValueDocument(
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
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            context
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TaskSaveJsonFormsValueDocumentResponseCommand>> actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskSaveJsonFormsValueDocument(
                this.client.getHost(),
                id,
                fileContentType,
                fileName,
                schemaPath,
                file,
                contentLength,
                accept,
                context
            )
        );
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TaskSaveJsonFormsValueDocumentResponseCommand>> actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveJsonFormsValueDocument(
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
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(id, fileContentType, fileName, schemaPath, file, contentLength)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return actionsTaskSaveJsonFormsValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            schemaPath,
            file,
            contentLength,
            context
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public Response<TaskSaveJsonFormsValueDocumentResponseCommand> actionsTaskSaveJsonFormsValueDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsTaskSaveJsonFormsValueDocumentSync(
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
     * Save a JSON Forms document
     *
     * Save a document in the task to later be linked into the JSON data.
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
    public TaskSaveJsonFormsValueDocumentResponseCommand actionsTaskSaveJsonFormsValueDocument(
        UUID id,
        String fileContentType,
        String fileName,
        String schemaPath,
        BinaryData file,
        long contentLength
    ) {
        return actionsTaskSaveJsonFormsValueDocumentWithResponse(
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
    public Mono<Response<BinaryData>> actionsTaskDownloadJsonFormsValueDocumentWithResponseAsync(UUID id, String documentUri) {
        final String accept = "application/octet-stream, application/json";
        return FluxUtil.withContext(context ->
            service.actionsTaskDownloadJsonFormsValueDocument(this.client.getHost(), id, documentUri, accept, context)
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsTaskDownloadJsonFormsValueDocumentWithResponseAsync(
        UUID id,
        String documentUri,
        Context context
    ) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsTaskDownloadJsonFormsValueDocument(this.client.getHost(), id, documentUri, accept, context);
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
    public Mono<BinaryData> actionsTaskDownloadJsonFormsValueDocumentAsync(UUID id, String documentUri) {
        return actionsTaskDownloadJsonFormsValueDocumentWithResponseAsync(id, documentUri).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
    public Mono<BinaryData> actionsTaskDownloadJsonFormsValueDocumentAsync(UUID id, String documentUri, Context context) {
        return actionsTaskDownloadJsonFormsValueDocumentWithResponseAsync(id, documentUri, context)
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
    public Response<BinaryData> actionsTaskDownloadJsonFormsValueDocumentWithResponse(UUID id, String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsTaskDownloadJsonFormsValueDocumentSync(this.client.getHost(), id, documentUri, accept, context);
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
    public BinaryData actionsTaskDownloadJsonFormsValueDocument(UUID id, String documentUri) {
        return actionsTaskDownloadJsonFormsValueDocumentWithResponse(id, documentUri, Context.NONE).getValue();
    }

    /**
     * Complete a task
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
    public Mono<Response<Task>> actionsTaskCompleteWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsTaskComplete(this.client.getHost(), id, accept, context));
    }

    /**
     * Complete a task
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
    public Mono<Response<Task>> actionsTaskCompleteWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.actionsTaskComplete(this.client.getHost(), id, accept, context);
    }

    /**
     * Complete a task
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
    public Mono<Task> actionsTaskCompleteAsync(UUID id) {
        return actionsTaskCompleteWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Complete a task
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
    public Mono<Task> actionsTaskCompleteAsync(UUID id, Context context) {
        return actionsTaskCompleteWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Complete a task
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
    public Response<Task> actionsTaskCompleteWithResponse(UUID id, Context context) {
        final String accept = "application/json";
        return service.actionsTaskCompleteSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Complete a task
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
    public Task actionsTaskComplete(UUID id) {
        return actionsTaskCompleteWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Append a log to the task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param log Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskAppendLogWithResponseAsync(UUID id, Log log) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsTaskAppendLog(this.client.getHost(), id, log, accept, context));
    }

    /**
     * Append a log to the task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param log Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Task>> actionsTaskAppendLogWithResponseAsync(UUID id, Log log, Context context) {
        final String accept = "application/json";
        return service.actionsTaskAppendLog(this.client.getHost(), id, log, accept, context);
    }

    /**
     * Append a log to the task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param log Log to be created.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskAppendLogAsync(UUID id, Log log) {
        return actionsTaskAppendLogWithResponseAsync(id, log).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Append a log to the task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param id The resource ID.
     * @param log Log to be created.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Task> actionsTaskAppendLogAsync(UUID id, Log log, Context context) {
        return actionsTaskAppendLogWithResponseAsync(id, log, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Append a log to the task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
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
        final String accept = "application/json";
        return service.actionsTaskAppendLogSync(this.client.getHost(), id, log, accept, context);
    }

    /**
     * Append a log to the task
     *
     * A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
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
        return actionsTaskAppendLogWithResponse(id, log, Context.NONE).getValue();
    }
}
