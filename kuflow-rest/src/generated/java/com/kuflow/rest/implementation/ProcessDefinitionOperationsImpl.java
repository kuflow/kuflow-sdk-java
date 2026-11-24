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
import com.azure.core.annotation.Put;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.ProcessDefinition;
import com.kuflow.rest.model.ProcessDefinitionPage;
import com.kuflow.rest.model.ProcessDefinitionState;
import com.kuflow.rest.model.ProcessDefinitionUpdateResult;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in ProcessDefinitionOperations.
 */
public final class ProcessDefinitionOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final ProcessDefinitionOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of ProcessDefinitionOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    ProcessDefinitionOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(ProcessDefinitionOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientProcessDefinitionOperations to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientProcessDefinitionOperations")
    public interface ProcessDefinitionOperationsService {
        @Get("/process-definitions")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessDefinitionPage>> findProcessDefinitions(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @QueryParam(value = "processDefinitionCode", multipleQueryParams = true) List<String> processDefinitionCode,
            @QueryParam(value = "state", multipleQueryParams = true) List<String> state,
            @QueryParam("name") String name,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-definitions")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessDefinitionPage> findProcessDefinitionsSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @QueryParam(value = "processDefinitionCode", multipleQueryParams = true) List<String> processDefinitionCode,
            @QueryParam(value = "state", multipleQueryParams = true) List<String> state,
            @QueryParam("name") String name,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-definitions/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessDefinition>> retrieveProcessDefinition(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("version") UUID version,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/process-definitions/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessDefinition> retrieveProcessDefinitionSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("version") UUID version,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/process-definitions/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<ProcessDefinitionUpdateResult>> updateProcessDefinition(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("dryRun") Boolean dryRun,
            @BodyParam("application/json") ProcessDefinition processDefinition,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/process-definitions/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<ProcessDefinitionUpdateResult> updateProcessDefinitionSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("dryRun") Boolean dryRun,
            @BodyParam("application/json") ProcessDefinition processDefinition,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param processDefinitionCode Filter by process definition codes.
     * @param state Filter by process definition states.
     * @param name Filter by the process definitions whose name has every word of this text, in any order, without
     * regard to
     * case or accents.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessDefinitionPage>> findProcessDefinitionsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<String> processDefinitionCode,
        List<ProcessDefinitionState> state,
        String name
    ) {
        return FluxUtil.withContext(context ->
            findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state, name, context)
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param processDefinitionCode Filter by process definition codes.
     * @param state Filter by process definition states.
     * @param name Filter by the process definitions whose name has every word of this text, in any order, without
     * regard to
     * case or accents.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessDefinitionPage>> findProcessDefinitionsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<String> processDefinitionCode,
        List<ProcessDefinitionState> state,
        String name,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted =
            sort == null
                ? new ArrayList<>()
                : sort
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        List<String> tenantIdConverted =
            tenantId == null
                ? new ArrayList<>()
                : tenantId
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        List<String> processDefinitionCodeConverted =
            processDefinitionCode == null
                ? new ArrayList<>()
                : processDefinitionCode
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        List<String> stateConverted =
            state == null
                ? new ArrayList<>()
                : state
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        return service.findProcessDefinitions(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            tenantIdConverted,
            processDefinitionCodeConverted,
            stateConverted,
            name,
            accept,
            context
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param processDefinitionCode Filter by process definition codes.
     * @param state Filter by process definition states.
     * @param name Filter by the process definitions whose name has every word of this text, in any order, without
     * regard to
     * case or accents.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinitionPage> findProcessDefinitionsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<String> processDefinitionCode,
        List<ProcessDefinitionState> state,
        String name
    ) {
        return findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state, name).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinitionPage> findProcessDefinitionsAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        final List<String> processDefinitionCode = null;
        final List<ProcessDefinitionState> state = null;
        final String name = null;
        return findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state, name).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param processDefinitionCode Filter by process definition codes.
     * @param state Filter by process definition states.
     * @param name Filter by the process definitions whose name has every word of this text, in any order, without
     * regard to
     * case or accents.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinitionPage> findProcessDefinitionsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<String> processDefinitionCode,
        List<ProcessDefinitionState> state,
        String name,
        Context context
    ) {
        return findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state, name, context).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param processDefinitionCode Filter by process definition codes.
     * @param state Filter by process definition states.
     * @param name Filter by the process definitions whose name has every word of this text, in any order, without
     * regard to
     * case or accents.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessDefinitionPage> findProcessDefinitionsWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<String> processDefinitionCode,
        List<ProcessDefinitionState> state,
        String name,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted =
            sort == null
                ? new ArrayList<>()
                : sort
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        List<String> tenantIdConverted =
            tenantId == null
                ? new ArrayList<>()
                : tenantId
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        List<String> processDefinitionCodeConverted =
            processDefinitionCode == null
                ? new ArrayList<>()
                : processDefinitionCode
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        List<String> stateConverted =
            state == null
                ? new ArrayList<>()
                : state
                      .stream()
                      .map(item -> Objects.toString(item, ""))
                      .collect(Collectors.toList());
        return service.findProcessDefinitionsSync(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            tenantIdConverted,
            processDefinitionCodeConverted,
            stateConverted,
            name,
            accept,
            context
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param processDefinitionCode Filter by process definition codes.
     * @param state Filter by process definition states.
     * @param name Filter by the process definitions whose name has every word of this text, in any order, without
     * regard to
     * case or accents.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinitionPage findProcessDefinitions(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<String> processDefinitionCode,
        List<ProcessDefinitionState> state,
        String name
    ) {
        return findProcessDefinitionsWithResponse(size, page, sort, tenantId, processDefinitionCode, state, name, Context.NONE).getValue();
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinitionPage findProcessDefinitions() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        final List<String> processDefinitionCode = null;
        final List<ProcessDefinitionState> state = null;
        final String name = null;
        return findProcessDefinitionsWithResponse(size, page, sort, tenantId, processDefinitionCode, state, name, Context.NONE).getValue();
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessDefinition>> retrieveProcessDefinitionWithResponseAsync(UUID id, UUID version) {
        return FluxUtil.withContext(context -> retrieveProcessDefinitionWithResponseAsync(id, version, context));
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions along with {@link Response} on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessDefinition>> retrieveProcessDefinitionWithResponseAsync(UUID id, UUID version, Context context) {
        final String accept = "application/json";
        return service.retrieveProcessDefinition(this.client.getHost(), id, version, accept, context);
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinition> retrieveProcessDefinitionAsync(UUID id, UUID version) {
        return retrieveProcessDefinitionWithResponseAsync(id, version).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinition> retrieveProcessDefinitionAsync(UUID id) {
        final UUID version = null;
        return retrieveProcessDefinitionWithResponseAsync(id, version).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinition> retrieveProcessDefinitionAsync(UUID id, UUID version, Context context) {
        return retrieveProcessDefinitionWithResponseAsync(id, version, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessDefinition> retrieveProcessDefinitionWithResponse(UUID id, UUID version, Context context) {
        final String accept = "application/json";
        return service.retrieveProcessDefinitionSync(this.client.getHost(), id, version, accept, context);
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinition retrieveProcessDefinition(UUID id, UUID version) {
        return retrieveProcessDefinitionWithResponse(id, version, Context.NONE).getValue();
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return a whole Process Definition at one of its versions.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinition retrieveProcessDefinition(UUID id) {
        final UUID version = null;
        return retrieveProcessDefinitionWithResponse(id, version, Context.NONE).getValue();
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer says what would change.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun` along with {@link Response}
     * on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessDefinitionUpdateResult>> updateProcessDefinitionWithResponseAsync(
        UUID id,
        ProcessDefinition processDefinition,
        Boolean dryRun
    ) {
        return FluxUtil.withContext(context -> updateProcessDefinitionWithResponseAsync(id, processDefinition, dryRun, context));
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer says what would change.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun` along with {@link Response}
     * on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<ProcessDefinitionUpdateResult>> updateProcessDefinitionWithResponseAsync(
        UUID id,
        ProcessDefinition processDefinition,
        Boolean dryRun,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessDefinition(this.client.getHost(), id, dryRun, processDefinition, accept, context);
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer says what would change.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun` on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinitionUpdateResult> updateProcessDefinitionAsync(UUID id, ProcessDefinition processDefinition, Boolean dryRun) {
        return updateProcessDefinitionWithResponseAsync(id, processDefinition, dryRun).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun` on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinitionUpdateResult> updateProcessDefinitionAsync(UUID id, ProcessDefinition processDefinition) {
        final Boolean dryRun = null;
        return updateProcessDefinitionWithResponseAsync(id, processDefinition, dryRun).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer says what would change.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun` on successful completion of
     * {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<ProcessDefinitionUpdateResult> updateProcessDefinitionAsync(
        UUID id,
        ProcessDefinition processDefinition,
        Boolean dryRun,
        Context context
    ) {
        return updateProcessDefinitionWithResponseAsync(id, processDefinition, dryRun, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer says what would change.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun` along with
     * {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessDefinitionUpdateResult> updateProcessDefinitionWithResponse(
        UUID id,
        ProcessDefinition processDefinition,
        Boolean dryRun,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateProcessDefinitionSync(this.client.getHost(), id, dryRun, processDefinition, accept, context);
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer says what would change.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun`.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinitionUpdateResult updateProcessDefinition(UUID id, ProcessDefinition processDefinition, Boolean dryRun) {
        return updateProcessDefinitionWithResponse(id, processDefinition, dryRun, Context.NONE).getValue();
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, a whole document as `GET` returns it, when the credentials
     * used can update it. Its `revision` and its `version` have to be the ones that the definition has now, at its
     * latest version: otherwise the answer is a conflict (409), because it changed after it was read. A document equal
     * to the one that the definition has changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what
     * can be changed yet is a bad request (400) with one error for each, whose location is the JSON Pointer of the
     * change in the document, and nothing is applied.
     *
     * What can be changed yet: the content of the forms that the definition has (their `jsonDefinition`, but its
     * `type`). The changes go to the draft, which a published definition gets when it has none. Nothing is published.
     *
     * With `dryRun`, nothing is applied: the answer says what would change.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return what an update of a Process Definition changed, or would change with `dryRun`.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinitionUpdateResult updateProcessDefinition(UUID id, ProcessDefinition processDefinition) {
        final Boolean dryRun = null;
        return updateProcessDefinitionWithResponse(id, processDefinition, dryRun, Context.NONE).getValue();
    }
}
