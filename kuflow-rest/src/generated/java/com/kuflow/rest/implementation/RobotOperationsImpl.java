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
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Robot;
import com.kuflow.rest.model.RobotAssetArchitecture;
import com.kuflow.rest.model.RobotAssetPlatform;
import com.kuflow.rest.model.RobotAssetType;
import com.kuflow.rest.model.RobotFilterContext;
import com.kuflow.rest.model.RobotPage;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in RobotOperations.
 */
public final class RobotOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final RobotOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of RobotOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    RobotOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(RobotOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientRobotOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientRobotOpe")
    public interface RobotOperationsService {
        @Get("/robots")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<RobotPage>> findRobots(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @QueryParam("filterContext") RobotFilterContext filterContext,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<RobotPage> findRobotsSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @QueryParam("filterContext") RobotFilterContext filterContext,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Robot>> retrieveRobot(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Robot> retrieveRobotSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots/{id}/~actions/download-source-code")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> actionsRobotDownloadSourceCode(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots/{id}/~actions/download-source-code")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> actionsRobotDownloadSourceCodeSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots/{id}/~actions/download-asset")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> actionsRobotDownloadAsset(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("type") RobotAssetType type,
            @QueryParam("version") String version,
            @QueryParam("platform") RobotAssetPlatform platform,
            @QueryParam("architecture") RobotAssetArchitecture architecture,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/robots/{id}/~actions/download-asset")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> actionsRobotDownloadAssetSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @QueryParam("type") RobotAssetType type,
            @QueryParam("version") String version,
            @QueryParam("platform") RobotAssetPlatform platform,
            @QueryParam("architecture") RobotAssetArchitecture architecture,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param filterContext Filter by the specified context.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<RobotPage>> findRobotsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        RobotFilterContext filterContext
    ) {
        return FluxUtil.withContext(context -> findRobotsWithResponseAsync(size, page, sort, tenantId, filterContext, context));
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param filterContext Filter by the specified context.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<RobotPage>> findRobotsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        RobotFilterContext filterContext,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findRobots(this.client.getHost(), size, page, sortConverted, tenantIdConverted, filterContext, accept, context);
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param filterContext Filter by the specified context.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RobotPage> findRobotsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        RobotFilterContext filterContext
    ) {
        return findRobotsWithResponseAsync(size, page, sort, tenantId, filterContext).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RobotPage> findRobotsAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        final RobotFilterContext filterContext = null;
        return findRobotsWithResponseAsync(size, page, sort, tenantId, filterContext).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param filterContext Filter by the specified context.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<RobotPage> findRobotsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        RobotFilterContext filterContext,
        Context context
    ) {
        return findRobotsWithResponseAsync(size, page, sort, tenantId, filterContext, context).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param filterContext Filter by the specified context.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<RobotPage> findRobotsWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        RobotFilterContext filterContext,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId.stream().map(item -> Objects.toString(item, "")).collect(Collectors.toList());
        return service.findRobotsSync(this.client.getHost(), size, page, sortConverted, tenantIdConverted, filterContext, accept, context);
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by tenantId.
     * @param filterContext Filter by the specified context.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RobotPage findRobots(Integer size, Integer page, List<String> sort, List<UUID> tenantId, RobotFilterContext filterContext) {
        return findRobotsWithResponse(size, page, sort, tenantId, filterContext, Context.NONE).getValue();
    }

    /**
     * Find all accessible Robots
     *
     * List all the Robots that have been created and the credentials has access.
     *
     * Available sort query values: createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public RobotPage findRobots() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        final RobotFilterContext filterContext = null;
        return findRobotsWithResponse(size, page, sort, tenantId, filterContext, Context.NONE).getValue();
    }

    /**
     * Get a Robot by ID
     *
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Robot>> retrieveRobotWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> retrieveRobotWithResponseAsync(id, context));
    }

    /**
     * Get a Robot by ID
     *
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Robot>> retrieveRobotWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveRobot(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Robot by ID
     *
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Robot> retrieveRobotAsync(UUID id) {
        return retrieveRobotWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Robot by ID
     *
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Robot> retrieveRobotAsync(UUID id, Context context) {
        return retrieveRobotWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Robot by ID
     *
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Robot> retrieveRobotWithResponse(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveRobotSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Robot by ID
     *
     * Returns the requested Robot when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Robot retrieveRobot(UUID id) {
        return retrieveRobotWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Download robot code
     *
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsRobotDownloadSourceCodeWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> actionsRobotDownloadSourceCodeWithResponseAsync(id, context));
    }

    /**
     * Download robot code
     *
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsRobotDownloadSourceCodeWithResponseAsync(UUID id, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsRobotDownloadSourceCode(this.client.getHost(), id, accept, context);
    }

    /**
     * Download robot code
     *
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsRobotDownloadSourceCodeAsync(UUID id) {
        return actionsRobotDownloadSourceCodeWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download robot code
     *
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsRobotDownloadSourceCodeAsync(UUID id, Context context) {
        return actionsRobotDownloadSourceCodeWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download robot code
     *
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> actionsRobotDownloadSourceCodeWithResponse(UUID id, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsRobotDownloadSourceCodeSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Download robot code
     *
     * Given a robot, download the source code.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData actionsRobotDownloadSourceCode(UUID id) {
        return actionsRobotDownloadSourceCodeWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Download robot asset
     *
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsRobotDownloadAssetWithResponseAsync(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture
    ) {
        return FluxUtil.withContext(
            context -> actionsRobotDownloadAssetWithResponseAsync(id, type, version, platform, architecture, context)
        );
    }

    /**
     * Download robot asset
     *
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> actionsRobotDownloadAssetWithResponseAsync(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture,
        Context context
    ) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsRobotDownloadAsset(this.client.getHost(), id, type, version, platform, architecture, accept, context);
    }

    /**
     * Download robot asset
     *
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsRobotDownloadAssetAsync(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture
    ) {
        return actionsRobotDownloadAssetWithResponseAsync(id, type, version, platform, architecture).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Download robot asset
     *
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> actionsRobotDownloadAssetAsync(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture,
        Context context
    ) {
        return actionsRobotDownloadAssetWithResponseAsync(id, type, version, platform, architecture, context).flatMap(
            res -> Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Download robot asset
     *
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> actionsRobotDownloadAssetWithResponse(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture,
        Context context
    ) {
        final String accept = "application/octet-stream, application/json";
        return service.actionsRobotDownloadAssetSync(this.client.getHost(), id, type, version, platform, architecture, accept, context);
    }

    /**
     * Download robot asset
     *
     * Given a robot, download the requested asset.
     *
     * @param id The resource ID.
     * @param type The asset type.
     * @param version The asset version.
     * @param platform The asset platform.
     * @param architecture The asset platform architecture.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData actionsRobotDownloadAsset(
        UUID id,
        RobotAssetType type,
        String version,
        RobotAssetPlatform platform,
        RobotAssetArchitecture architecture
    ) {
        return actionsRobotDownloadAssetWithResponse(id, type, version, platform, architecture, Context.NONE).getValue();
    }
}
