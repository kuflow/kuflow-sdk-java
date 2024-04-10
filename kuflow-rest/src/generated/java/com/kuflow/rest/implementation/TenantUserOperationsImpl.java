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
import com.kuflow.rest.model.TenantUser;
import com.kuflow.rest.model.TenantUserPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in TenantUserOperations.
 */
public final class TenantUserOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final TenantUserOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of TenantUserOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    TenantUserOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(TenantUserOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientTenantUserOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientTenantUs")
    public interface TenantUserOperationsService {
        @Get("/tenant-users")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<TenantUserPage>> findTenantUsers(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "groupId", multipleQueryParams = true) List<String> groupId,
            @QueryParam(value = "email", multipleQueryParams = true) List<String> email,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tenant-users")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<TenantUserPage> findTenantUsersSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "groupId", multipleQueryParams = true) List<String> groupId,
            @QueryParam(value = "email", multipleQueryParams = true) List<String> email,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tenant-users/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<TenantUser>> retrieveTenantUser(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/tenant-users/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<TenantUser> retrieveTenantUserSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
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
     * @param groupId Filter by group ids.
     * @param email Filter by email.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TenantUserPage>> findTenantUsersWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> groupId,
        List<String> email,
        List<UUID> tenantId
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> groupIdConverted = (groupId == null)
            ? new ArrayList<>()
            : groupId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> emailConverted = (email == null)
            ? new ArrayList<>()
            : email.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return FluxUtil.withContext(
            context ->
                service.findTenantUsers(
                    this.client.getHost(),
                    size,
                    page,
                    sortConverted,
                    groupIdConverted,
                    emailConverted,
                    tenantIdConverted,
                    accept,
                    context
                )
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
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
     * @param groupId Filter by group ids.
     * @param email Filter by email.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TenantUserPage>> findTenantUsersWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> groupId,
        List<String> email,
        List<UUID> tenantId,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> groupIdConverted = (groupId == null)
            ? new ArrayList<>()
            : groupId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> emailConverted = (email == null)
            ? new ArrayList<>()
            : email.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findTenantUsers(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            groupIdConverted,
            emailConverted,
            tenantIdConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
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
     * @param groupId Filter by group ids.
     * @param email Filter by email.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TenantUserPage> findTenantUsersAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> groupId,
        List<String> email,
        List<UUID> tenantId
    ) {
        return findTenantUsersWithResponseAsync(size, page, sort, groupId, email, tenantId).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TenantUserPage> findTenantUsersAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> groupId = null;
        final List<String> email = null;
        final List<UUID> tenantId = null;
        return findTenantUsersWithResponseAsync(size, page, sort, groupId, email, tenantId).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
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
     * @param groupId Filter by group ids.
     * @param email Filter by email.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TenantUserPage> findTenantUsersAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> groupId,
        List<String> email,
        List<UUID> tenantId,
        Context context
    ) {
        return findTenantUsersWithResponseAsync(size, page, sort, groupId, email, tenantId, context).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
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
     * @param groupId Filter by group ids.
     * @param email Filter by email.
     * @param tenantId Filter by tenantId.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TenantUserPage> findTenantUsersWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> groupId,
        List<String> email,
        List<UUID> tenantId,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> groupIdConverted = (groupId == null)
            ? new ArrayList<>()
            : groupId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> emailConverted = (email == null)
            ? new ArrayList<>()
            : email.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findTenantUsersSync(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            groupIdConverted,
            emailConverted,
            tenantIdConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
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
     * @param groupId Filter by group ids.
     * @param email Filter by email.
     * @param tenantId Filter by tenantId.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantUserPage findTenantUsers(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> groupId,
        List<String> email,
        List<UUID> tenantId
    ) {
        return findTenantUsersWithResponse(size, page, sort, groupId, email, tenantId, Context.NONE).getValue();
    }

    /**
     * Find all accessible Tenant Users
     *
     * List all the Tenant Users that have been created and the used credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantUserPage findTenantUsers() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> groupId = null;
        final List<String> email = null;
        final List<UUID> tenantId = null;
        return findTenantUsersWithResponse(size, page, sort, groupId, email, tenantId, Context.NONE).getValue();
    }

    /**
     * Get a Tenant User by ID
     *
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TenantUser>> retrieveTenantUserWithResponseAsync(UUID id) {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.retrieveTenantUser(this.client.getHost(), id, accept, context));
    }

    /**
     * Get a Tenant User by ID
     *
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<TenantUser>> retrieveTenantUserWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveTenantUser(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Tenant User by ID
     *
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TenantUser> retrieveTenantUserAsync(UUID id) {
        return retrieveTenantUserWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Tenant User by ID
     *
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<TenantUser> retrieveTenantUserAsync(UUID id, Context context) {
        return retrieveTenantUserWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Tenant User by ID
     *
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TenantUser> retrieveTenantUserWithResponse(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveTenantUserSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Tenant User by ID
     *
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantUser retrieveTenantUser(UUID id) {
        return retrieveTenantUserWithResponse(id, Context.NONE).getValue();
    }
}
