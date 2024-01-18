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
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessChangeInitiatorCommand;
import com.kuflow.rest.model.ProcessDeleteElementCommand;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.ProcessSaveElementCommand;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in ProcessOperations. */
public final class ProcessOperationsImpl {

    /** The proxy service used to perform REST calls. */
    private final ProcessOperationsService service;

    /** The service client containing this operation class. */
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
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> createProcess(
            @HostParam("$host") String host,
            @BodyParam("application/json") Process process,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> createProcessSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") Process process,
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

        @Post("/processes/{id}/~actions/change-initiator")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessChangeInitiator(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessChangeInitiatorCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/change-initiator")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> actionsProcessChangeInitiatorSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessChangeInitiatorCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/save-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessSaveElement(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessSaveElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/save-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> actionsProcessSaveElementSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessSaveElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/delete-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessDeleteElement(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessDeleteElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/delete-element")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> actionsProcessDeleteElementSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") ProcessDeleteElementCommand command,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessComplete(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/complete")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> actionsProcessCompleteSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/cancel")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessCancel(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/cancel")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> actionsProcessCancelSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/processes/{id}/~actions/save-user-action-value-document")
        @ExpectedResponses({ 200, 304 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessSaveUserActionValueDocument(
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

        @Post("/processes/{id}/~actions/save-user-action-value-document")
        @ExpectedResponses({ 200, 304 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Process>> actionsProcessSaveUserActionValueDocument(
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

        @Post("/processes/{id}/~actions/save-user-action-value-document")
        @ExpectedResponses({ 200, 304 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Process> actionsProcessSaveUserActionValueDocumentSync(
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
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessPage>> findProcessesWithResponseAsync(Integer size, Integer page, List<String> sort) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return FluxUtil.withContext(context -> service.findProcesses(this.client.getHost(), size, page, sortConverted, accept, context));
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessPage>> findProcessesWithResponseAsync(Integer size, Integer page, List<String> sort, Context context) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findProcesses(this.client.getHost(), size, page, sortConverted, accept, context);
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessPage> findProcessesAsync(Integer size, Integer page, List<String> sort) {
        return findProcessesWithResponseAsync(size, page, sort).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessPage> findProcessesAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        return findProcessesWithResponseAsync(size, page, sort).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessPage> findProcessesAsync(Integer size, Integer page, List<String> sort, Context context) {
        return findProcessesWithResponseAsync(size, page, sort, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessPage> findProcessesWithResponse(Integer size, Integer page, List<String> sort, Context context) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findProcessesSync(this.client.getHost(), size, page, sortConverted, accept, context);
    }

    /**
     * Find all accessible Processes
     *
     * <p>List all the Processes that have been created and the credentials has access.
     *
     * <p>Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *     <p>Default sort order is ascending. Multiple sort criteria are supported.
     *     <p>Please refer to the method description for supported properties.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessPage findProcesses(Integer size, Integer page, List<String> sort) {
        return findProcessesWithResponse(size, page, sort, Context.NONE).getValue();
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
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        return findProcessesWithResponse(size, page, sort, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> createProcessWithResponseAsync(Process process) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.createProcess(this.client.getHost(), process, accept, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> createProcessWithResponseAsync(Process process, Context context) {
        final String accept = "application/json";
        return service.createProcess(this.client.getHost(), process, accept, context);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> createProcessAsync(Process process) {
        return createProcessWithResponseAsync(process).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> createProcessAsync(Process process, Context context) {
        return createProcessWithResponseAsync(process, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.createProcessSync(this.client.getHost(), process, accept, context);
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
        return createProcessWithResponse(process, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> retrieveProcessWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.retrieveProcess(this.client.getHost(), id, accept, context));
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
     * <p>Returns the requested Process when has access to do it.
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
     * <p>Returns the requested Process when has access to do it.
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
        final String accept = "application/json";
        return service.retrieveProcessSync(this.client.getHost(), id, accept, context);
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
        return retrieveProcessWithResponse(id, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessChangeInitiatorWithResponseAsync(UUID id, ProcessChangeInitiatorCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsProcessChangeInitiator(this.client.getHost(), id, command, accept, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessChangeInitiatorWithResponseAsync(
        UUID id,
        ProcessChangeInitiatorCommand command,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsProcessChangeInitiator(this.client.getHost(), id, command, accept, context);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessChangeInitiatorAsync(UUID id, ProcessChangeInitiatorCommand command) {
        return actionsProcessChangeInitiatorWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessChangeInitiatorAsync(UUID id, ProcessChangeInitiatorCommand command, Context context) {
        return actionsProcessChangeInitiatorWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.actionsProcessChangeInitiatorSync(this.client.getHost(), id, command, accept, context);
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
        return actionsProcessChangeInitiatorWithResponse(id, command, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessSaveElementWithResponseAsync(UUID id, ProcessSaveElementCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsProcessSaveElement(this.client.getHost(), id, command, accept, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessSaveElementWithResponseAsync(UUID id, ProcessSaveElementCommand command, Context context) {
        final String accept = "application/json";
        return service.actionsProcessSaveElement(this.client.getHost(), id, command, accept, context);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessSaveElementAsync(UUID id, ProcessSaveElementCommand command) {
        return actionsProcessSaveElementWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessSaveElementAsync(UUID id, ProcessSaveElementCommand command, Context context) {
        return actionsProcessSaveElementWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.actionsProcessSaveElementSync(this.client.getHost(), id, command, accept, context);
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
        return actionsProcessSaveElementWithResponse(id, command, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessDeleteElementWithResponseAsync(UUID id, ProcessDeleteElementCommand command) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsProcessDeleteElement(this.client.getHost(), id, command, accept, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessDeleteElementWithResponseAsync(
        UUID id,
        ProcessDeleteElementCommand command,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsProcessDeleteElement(this.client.getHost(), id, command, accept, context);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessDeleteElementAsync(UUID id, ProcessDeleteElementCommand command) {
        return actionsProcessDeleteElementWithResponseAsync(id, command).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessDeleteElementAsync(UUID id, ProcessDeleteElementCommand command, Context context) {
        return actionsProcessDeleteElementWithResponseAsync(id, command, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.actionsProcessDeleteElementSync(this.client.getHost(), id, command, accept, context);
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
        return actionsProcessDeleteElementWithResponse(id, command, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessCompleteWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsProcessComplete(this.client.getHost(), id, accept, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessCompleteWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.actionsProcessComplete(this.client.getHost(), id, accept, context);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessCompleteAsync(UUID id) {
        return actionsProcessCompleteWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessCompleteAsync(UUID id, Context context) {
        return actionsProcessCompleteWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.actionsProcessCompleteSync(this.client.getHost(), id, accept, context);
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
        return actionsProcessCompleteWithResponse(id, Context.NONE).getValue();
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessCancelWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.actionsProcessCancel(this.client.getHost(), id, accept, context));
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
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Process>> actionsProcessCancelWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.actionsProcessCancel(this.client.getHost(), id, accept, context);
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessCancelAsync(UUID id) {
        return actionsProcessCancelWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Process> actionsProcessCancelAsync(UUID id, Context context) {
        return actionsProcessCancelWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
        final String accept = "application/json";
        return service.actionsProcessCancelSync(this.client.getHost(), id, accept, context);
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
        return actionsProcessCancelWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Response<Process>> actionsProcessSaveUserActionValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsProcessSaveUserActionValueDocument(
                this.client.getHost(),
                id,
                fileContentType,
                fileName,
                userActionValueId,
                file,
                contentLength,
                accept,
                context
            )
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Response<Process>> actionsProcessSaveUserActionValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsProcessSaveUserActionValueDocument(
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
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Process> actionsProcessSaveUserActionValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return actionsProcessSaveUserActionValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Process> actionsProcessSaveUserActionValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return actionsProcessSaveUserActionValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            context
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Response<Process>> actionsProcessSaveUserActionValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength
    ) {
        final String accept = "application/json";
        return FluxUtil.withContext(context ->
            service.actionsProcessSaveUserActionValueDocument(
                this.client.getHost(),
                id,
                fileContentType,
                fileName,
                userActionValueId,
                file,
                contentLength,
                accept,
                context
            )
        );
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Response<Process>> actionsProcessSaveUserActionValueDocumentWithResponseAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsProcessSaveUserActionValueDocument(
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
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Process> actionsProcessSaveUserActionValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength
    ) {
        return actionsProcessSaveUserActionValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Mono<Process> actionsProcessSaveUserActionValueDocumentAsync(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return actionsProcessSaveUserActionValueDocumentWithResponseAsync(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            context
        )
            .flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Upload and save a document in a user action
     *
     * <p>Allow saving a user action document uploading the content.
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
    public Response<Process> actionsProcessSaveUserActionValueDocumentWithResponse(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.actionsProcessSaveUserActionValueDocumentSync(
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
     * <p>Allow saving a user action document uploading the content.
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
    public Process actionsProcessSaveUserActionValueDocument(
        UUID id,
        String fileContentType,
        String fileName,
        UUID userActionValueId,
        BinaryData file,
        long contentLength
    ) {
        return actionsProcessSaveUserActionValueDocumentWithResponse(
            id,
            fileContentType,
            fileName,
            userActionValueId,
            file,
            contentLength,
            Context.NONE
        )
            .getValue();
    }
}
