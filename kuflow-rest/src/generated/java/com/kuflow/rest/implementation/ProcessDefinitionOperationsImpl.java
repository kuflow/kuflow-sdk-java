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

import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.PathParam;
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
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
        List<ProcessDefinitionState> state
    ) {
        return FluxUtil.withContext(context ->
            findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state, context)
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
            accept,
            context
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
        List<ProcessDefinitionState> state
    ) {
        return findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
        return findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
        Context context
    ) {
        return findProcessDefinitionsWithResponseAsync(size, page, sort, tenantId, processDefinitionCode, state, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
            accept,
            context
        );
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
        List<ProcessDefinitionState> state
    ) {
        return findProcessDefinitionsWithResponse(size, page, sort, tenantId, processDefinitionCode, state, Context.NONE).getValue();
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed.
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
        return findProcessDefinitionsWithResponse(size, page, sort, tenantId, processDefinitionCode, state, Context.NONE).getValue();
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
     * when there is one, otherwise the current one.
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
}
