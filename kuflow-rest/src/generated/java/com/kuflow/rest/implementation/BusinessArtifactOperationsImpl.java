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
import com.azure.core.annotation.Delete;
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
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.BusinessArtifact;
import com.kuflow.rest.model.BusinessArtifactCreateParams;
import com.kuflow.rest.model.BusinessArtifactDataUpdateParams;
import com.kuflow.rest.model.BusinessArtifactPage;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.JsonPatchOperation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in BusinessArtifactOperations.
 */
public final class BusinessArtifactOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final BusinessArtifactOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of BusinessArtifactOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    BusinessArtifactOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(BusinessArtifactOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientBusinessArtifactOperations to be used by the proxy
     * service to perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientBusiness")
    public interface BusinessArtifactOperationsService {
        @Get("/business-artifacts")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BusinessArtifactPage>> findBusinessArtifacts(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @QueryParam(value = "businessArtifactDefinitionId", multipleQueryParams = true) List<String> businessArtifactDefinitionId,
            @QueryParam(value = "businessArtifactDefinitionCode", multipleQueryParams = true) List<String> businessArtifactDefinitionCode,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/business-artifacts")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BusinessArtifactPage> findBusinessArtifactsSync(
            @HostParam("$host") String host,
            @QueryParam("size") Integer size,
            @QueryParam("page") Integer page,
            @QueryParam(value = "sort", multipleQueryParams = true) List<String> sort,
            @QueryParam(value = "tenantId", multipleQueryParams = true) List<String> tenantId,
            @QueryParam(value = "businessArtifactDefinitionId", multipleQueryParams = true) List<String> businessArtifactDefinitionId,
            @QueryParam(value = "businessArtifactDefinitionCode", multipleQueryParams = true) List<String> businessArtifactDefinitionCode,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/business-artifacts")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BusinessArtifact>> createBusinessArtifact(
            @HostParam("$host") String host,
            @BodyParam("application/json") BusinessArtifactCreateParams businessArtifactCreateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/business-artifacts")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BusinessArtifact> createBusinessArtifactSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") BusinessArtifactCreateParams businessArtifactCreateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/business-artifacts/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BusinessArtifact>> retrieveBusinessArtifact(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/business-artifacts/{id}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BusinessArtifact> retrieveBusinessArtifactSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Delete("/business-artifacts/{id}")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Void>> deleteBusinessArtifact(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Delete("/business-artifacts/{id}")
        @ExpectedResponses({ 204 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Void> deleteBusinessArtifactSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/business-artifacts/{id}/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BusinessArtifact>> updateBusinessArtifactData(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Put("/business-artifacts/{id}/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BusinessArtifact> updateBusinessArtifactDataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json") BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/business-artifacts/{id}/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BusinessArtifact>> patchBusinessArtifactData(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> jsonPatch,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Patch("/business-artifacts/{id}/data")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BusinessArtifact> patchBusinessArtifactDataSync(
            @HostParam("$host") String host,
            @PathParam("id") UUID id,
            @BodyParam("application/json-patch+json") List<JsonPatchOperation> jsonPatch,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
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
     * @param businessArtifactDefinitionId Filter by an array of business artifact definition ids.
     * @param businessArtifactDefinitionCode Filter by an array of business artifact definition codes.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifactPage>> findBusinessArtifactsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<UUID> businessArtifactDefinitionId,
        List<String> businessArtifactDefinitionCode
    ) {
        return FluxUtil.withContext(context ->
            findBusinessArtifactsWithResponseAsync(
                size,
                page,
                sort,
                tenantId,
                businessArtifactDefinitionId,
                businessArtifactDefinitionCode,
                context
            )
        );
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
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
     * @param businessArtifactDefinitionId Filter by an array of business artifact definition ids.
     * @param businessArtifactDefinitionCode Filter by an array of business artifact definition codes.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifactPage>> findBusinessArtifactsWithResponseAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<UUID> businessArtifactDefinitionId,
        List<String> businessArtifactDefinitionCode,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        List<String> businessArtifactDefinitionIdConverted = (businessArtifactDefinitionId == null)
            ? new ArrayList<>()
            : businessArtifactDefinitionId
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        List<String> businessArtifactDefinitionCodeConverted = (businessArtifactDefinitionCode == null)
            ? new ArrayList<>()
            : businessArtifactDefinitionCode
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        return service.findBusinessArtifacts(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            tenantIdConverted,
            businessArtifactDefinitionIdConverted,
            businessArtifactDefinitionCodeConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
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
     * @param businessArtifactDefinitionId Filter by an array of business artifact definition ids.
     * @param businessArtifactDefinitionCode Filter by an array of business artifact definition codes.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifactPage> findBusinessArtifactsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<UUID> businessArtifactDefinitionId,
        List<String> businessArtifactDefinitionCode
    ) {
        return findBusinessArtifactsWithResponseAsync(
            size,
            page,
            sort,
            tenantId,
            businessArtifactDefinitionId,
            businessArtifactDefinitionCode
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifactPage> findBusinessArtifactsAsync() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        final List<UUID> businessArtifactDefinitionId = null;
        final List<String> businessArtifactDefinitionCode = null;
        return findBusinessArtifactsWithResponseAsync(
            size,
            page,
            sort,
            tenantId,
            businessArtifactDefinitionId,
            businessArtifactDefinitionCode
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
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
     * @param businessArtifactDefinitionId Filter by an array of business artifact definition ids.
     * @param businessArtifactDefinitionCode Filter by an array of business artifact definition codes.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifactPage> findBusinessArtifactsAsync(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<UUID> businessArtifactDefinitionId,
        List<String> businessArtifactDefinitionCode,
        Context context
    ) {
        return findBusinessArtifactsWithResponseAsync(
            size,
            page,
            sort,
            tenantId,
            businessArtifactDefinitionId,
            businessArtifactDefinitionCode,
            context
        ).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
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
     * @param businessArtifactDefinitionId Filter by an array of business artifact definition ids.
     * @param businessArtifactDefinitionCode Filter by an array of business artifact definition codes.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifactPage> findBusinessArtifactsWithResponse(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<UUID> businessArtifactDefinitionId,
        List<String> businessArtifactDefinitionCode,
        Context context
    ) {
        final String accept = "application/json";
        List<String> sortConverted = (sort == null)
            ? new ArrayList<>()
            : sort
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        List<String> tenantIdConverted = (tenantId == null)
            ? new ArrayList<>()
            : tenantId
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        List<String> businessArtifactDefinitionIdConverted = (businessArtifactDefinitionId == null)
            ? new ArrayList<>()
            : businessArtifactDefinitionId
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        List<String> businessArtifactDefinitionCodeConverted = (businessArtifactDefinitionCode == null)
            ? new ArrayList<>()
            : businessArtifactDefinitionCode
                  .stream()
                  .map(item -> Objects.toString(item, ""))
                  .collect(Collectors.toList());
        return service.findBusinessArtifactsSync(
            this.client.getHost(),
            size,
            page,
            sortConverted,
            tenantIdConverted,
            businessArtifactDefinitionIdConverted,
            businessArtifactDefinitionCodeConverted,
            accept,
            context
        );
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
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
     * @param businessArtifactDefinitionId Filter by an array of business artifact definition ids.
     * @param businessArtifactDefinitionCode Filter by an array of business artifact definition codes.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifactPage findBusinessArtifacts(
        Integer size,
        Integer page,
        List<String> sort,
        List<UUID> tenantId,
        List<UUID> businessArtifactDefinitionId,
        List<String> businessArtifactDefinitionCode
    ) {
        return findBusinessArtifactsWithResponse(
            size,
            page,
            sort,
            tenantId,
            businessArtifactDefinitionId,
            businessArtifactDefinitionCode,
            Context.NONE
        ).getValue();
    }

    /**
     * Find all accessible Business Artifacts
     *
     * List all the Business Artifacts that have been created and the credentials has access.
     *
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifactPage findBusinessArtifacts() {
        final Integer size = null;
        final Integer page = null;
        final List<String> sort = null;
        final List<UUID> tenantId = null;
        final List<UUID> businessArtifactDefinitionId = null;
        final List<String> businessArtifactDefinitionCode = null;
        return findBusinessArtifactsWithResponse(
            size,
            page,
            sort,
            tenantId,
            businessArtifactDefinitionId,
            businessArtifactDefinitionCode,
            Context.NONE
        ).getValue();
    }

    /**
     * Create a new Business Artifact
     *
     * Creates a Business Artifact.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param businessArtifactCreateParams Business Artifact to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifact>> createBusinessArtifactWithResponseAsync(
        BusinessArtifactCreateParams businessArtifactCreateParams
    ) {
        return FluxUtil.withContext(context -> createBusinessArtifactWithResponseAsync(businessArtifactCreateParams, context));
    }

    /**
     * Create a new Business Artifact
     *
     * Creates a Business Artifact.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param businessArtifactCreateParams Business Artifact to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifact>> createBusinessArtifactWithResponseAsync(
        BusinessArtifactCreateParams businessArtifactCreateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.createBusinessArtifact(this.client.getHost(), businessArtifactCreateParams, accept, context);
    }

    /**
     * Create a new Business Artifact
     *
     * Creates a Business Artifact.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param businessArtifactCreateParams Business Artifact to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifact> createBusinessArtifactAsync(BusinessArtifactCreateParams businessArtifactCreateParams) {
        return createBusinessArtifactWithResponseAsync(businessArtifactCreateParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create a new Business Artifact
     *
     * Creates a Business Artifact.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param businessArtifactCreateParams Business Artifact to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifact> createBusinessArtifactAsync(BusinessArtifactCreateParams businessArtifactCreateParams, Context context) {
        return createBusinessArtifactWithResponseAsync(businessArtifactCreateParams, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Create a new Business Artifact
     *
     * Creates a Business Artifact.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param businessArtifactCreateParams Business Artifact to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> createBusinessArtifactWithResponse(
        BusinessArtifactCreateParams businessArtifactCreateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.createBusinessArtifactSync(this.client.getHost(), businessArtifactCreateParams, accept, context);
    }

    /**
     * Create a new Business Artifact
     *
     * Creates a Business Artifact.
     *
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param businessArtifactCreateParams Business Artifact to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact createBusinessArtifact(BusinessArtifactCreateParams businessArtifactCreateParams) {
        return createBusinessArtifactWithResponse(businessArtifactCreateParams, Context.NONE).getValue();
    }

    /**
     * Get a Business Artifact by ID
     *
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifact>> retrieveBusinessArtifactWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> retrieveBusinessArtifactWithResponseAsync(id, context));
    }

    /**
     * Get a Business Artifact by ID
     *
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifact>> retrieveBusinessArtifactWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveBusinessArtifact(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Business Artifact by ID
     *
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifact> retrieveBusinessArtifactAsync(UUID id) {
        return retrieveBusinessArtifactWithResponseAsync(id).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Business Artifact by ID
     *
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifact> retrieveBusinessArtifactAsync(UUID id, Context context) {
        return retrieveBusinessArtifactWithResponseAsync(id, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get a Business Artifact by ID
     *
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> retrieveBusinessArtifactWithResponse(UUID id, Context context) {
        final String accept = "application/json";
        return service.retrieveBusinessArtifactSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Get a Business Artifact by ID
     *
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact retrieveBusinessArtifact(UUID id) {
        return retrieveBusinessArtifactWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Delete a Business Artifact by ID
     *
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteBusinessArtifactWithResponseAsync(UUID id) {
        return FluxUtil.withContext(context -> deleteBusinessArtifactWithResponseAsync(id, context));
    }

    /**
     * Delete a Business Artifact by ID
     *
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Void>> deleteBusinessArtifactWithResponseAsync(UUID id, Context context) {
        final String accept = "application/json";
        return service.deleteBusinessArtifact(this.client.getHost(), id, accept, context);
    }

    /**
     * Delete a Business Artifact by ID
     *
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteBusinessArtifactAsync(UUID id) {
        return deleteBusinessArtifactWithResponseAsync(id).flatMap(ignored -> Mono.empty());
    }

    /**
     * Delete a Business Artifact by ID
     *
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return A {@link Mono} that completes when a successful response is received.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Void> deleteBusinessArtifactAsync(UUID id, Context context) {
        return deleteBusinessArtifactWithResponseAsync(id, context).flatMap(ignored -> Mono.empty());
    }

    /**
     * Delete a Business Artifact by ID
     *
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteBusinessArtifactWithResponse(UUID id, Context context) {
        final String accept = "application/json";
        return service.deleteBusinessArtifactSync(this.client.getHost(), id, accept, context);
    }

    /**
     * Delete a Business Artifact by ID
     *
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteBusinessArtifact(UUID id) {
        deleteBusinessArtifactWithResponse(id, Context.NONE);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param businessArtifactDataUpdateParams Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifact>> updateBusinessArtifactDataWithResponseAsync(
        UUID id,
        BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams
    ) {
        return FluxUtil.withContext(context -> updateBusinessArtifactDataWithResponseAsync(id, businessArtifactDataUpdateParams, context));
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param businessArtifactDataUpdateParams Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BusinessArtifact>> updateBusinessArtifactDataWithResponseAsync(
        UUID id,
        BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateBusinessArtifactData(this.client.getHost(), id, businessArtifactDataUpdateParams, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param businessArtifactDataUpdateParams Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifact> updateBusinessArtifactDataAsync(
        UUID id,
        BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams
    ) {
        return updateBusinessArtifactDataWithResponseAsync(id, businessArtifactDataUpdateParams).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param businessArtifactDataUpdateParams Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BusinessArtifact> updateBusinessArtifactDataAsync(
        UUID id,
        BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams,
        Context context
    ) {
        return updateBusinessArtifactDataWithResponseAsync(id, businessArtifactDataUpdateParams, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param businessArtifactDataUpdateParams Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> updateBusinessArtifactDataWithResponse(
        UUID id,
        BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams,
        Context context
    ) {
        final String accept = "application/json";
        return service.updateBusinessArtifactDataSync(this.client.getHost(), id, businessArtifactDataUpdateParams, accept, context);
    }

    /**
     * Save JSON data
     *
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param businessArtifactDataUpdateParams Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact updateBusinessArtifactData(UUID id, BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams) {
        return updateBusinessArtifactDataWithResponse(id, businessArtifactDataUpdateParams, Context.NONE).getValue();
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
    public Mono<Response<BusinessArtifact>> patchBusinessArtifactDataWithResponseAsync(UUID id, List<JsonPatchOperation> jsonPatch) {
        return FluxUtil.withContext(context -> patchBusinessArtifactDataWithResponseAsync(id, jsonPatch, context));
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
    public Mono<Response<BusinessArtifact>> patchBusinessArtifactDataWithResponseAsync(
        UUID id,
        List<JsonPatchOperation> jsonPatch,
        Context context
    ) {
        final String accept = "application/json";
        return service.patchBusinessArtifactData(this.client.getHost(), id, jsonPatch, accept, context);
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
    public Mono<BusinessArtifact> patchBusinessArtifactDataAsync(UUID id, List<JsonPatchOperation> jsonPatch) {
        return patchBusinessArtifactDataWithResponseAsync(id, jsonPatch).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
    public Mono<BusinessArtifact> patchBusinessArtifactDataAsync(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        return patchBusinessArtifactDataWithResponseAsync(id, jsonPatch, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
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
    public Response<BusinessArtifact> patchBusinessArtifactDataWithResponse(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        final String accept = "application/json";
        return service.patchBusinessArtifactDataSync(this.client.getHost(), id, jsonPatch, accept, context);
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
    public BusinessArtifact patchBusinessArtifactData(UUID id, List<JsonPatchOperation> jsonPatch) {
        return patchBusinessArtifactDataWithResponse(id, jsonPatch, Context.NONE).getValue();
    }
}
