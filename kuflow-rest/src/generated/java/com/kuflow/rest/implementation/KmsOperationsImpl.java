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
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.KmsKey;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in KmsOperations.
 */
public final class KmsOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final KmsOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of KmsOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    KmsOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(KmsOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientKmsOperations to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientKmsOpera")
    public interface KmsOperationsService {
        @Get("/kms/keys/{keyId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<KmsKey>> retrieveKmsKey(
            @HostParam("$host") String host,
            @PathParam("keyId") String keyId,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/kms/keys/{keyId}")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<KmsKey> retrieveKmsKeySync(
            @HostParam("$host") String host,
            @PathParam("keyId") String keyId,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Get the requested key id.
     *
     * @param keyId The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the requested key id along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<KmsKey>> retrieveKmsKeyWithResponseAsync(String keyId) {
        return FluxUtil.withContext(context -> retrieveKmsKeyWithResponseAsync(keyId, context));
    }

    /**
     * Get the requested key id.
     *
     * @param keyId The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the requested key id along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<KmsKey>> retrieveKmsKeyWithResponseAsync(String keyId, Context context) {
        final String accept = "application/json";
        return service.retrieveKmsKey(this.client.getHost(), keyId, accept, context);
    }

    /**
     * Get the requested key id.
     *
     * @param keyId The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the requested key id on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KmsKey> retrieveKmsKeyAsync(String keyId) {
        return retrieveKmsKeyWithResponseAsync(keyId).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get the requested key id.
     *
     * @param keyId The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the requested key id on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<KmsKey> retrieveKmsKeyAsync(String keyId, Context context) {
        return retrieveKmsKeyWithResponseAsync(keyId, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Get the requested key id.
     *
     * @param keyId The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the requested key id along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<KmsKey> retrieveKmsKeyWithResponse(String keyId, Context context) {
        final String accept = "application/json";
        return service.retrieveKmsKeySync(this.client.getHost(), keyId, accept, context);
    }

    /**
     * Get the requested key id.
     *
     * @param keyId The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the requested key id.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public KmsKey retrieveKmsKey(String keyId) {
        return retrieveKmsKeyWithResponse(keyId, Context.NONE).getValue();
    }
}
