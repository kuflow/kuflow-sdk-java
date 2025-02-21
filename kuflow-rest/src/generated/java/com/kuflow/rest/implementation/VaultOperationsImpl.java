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
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.VaultCodecPayloads;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in VaultOperations.
 */
public final class VaultOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final VaultOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of VaultOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    VaultOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(VaultOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientVaultOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientVaultOpe")
    public interface VaultOperationsService {
        @Post("/vault/codec/~actions/encode")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<VaultCodecPayloads>> codecEncode(
            @HostParam("$host") String host,
            @BodyParam("application/json") VaultCodecPayloads vaultCodecEncodeParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/vault/codec/~actions/encode")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<VaultCodecPayloads> codecEncodeSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") VaultCodecPayloads vaultCodecEncodeParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/vault/codec/~actions/decode")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<VaultCodecPayloads>> codecDecode(
            @HostParam("$host") String host,
            @BodyParam("application/json") VaultCodecPayloads vaultCodecDecodeParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/vault/codec/~actions/decode")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<VaultCodecPayloads> codecDecodeSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") VaultCodecPayloads vaultCodecDecodeParams,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Encode the requested payloads.
     *
     * @param vaultCodecEncodeParams Payloads to encode.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<VaultCodecPayloads>> codecEncodeWithResponseAsync(VaultCodecPayloads vaultCodecEncodeParams) {
        return FluxUtil.withContext(context -> codecEncodeWithResponseAsync(vaultCodecEncodeParams, context));
    }

    /**
     * Encode the requested payloads.
     *
     * @param vaultCodecEncodeParams Payloads to encode.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<VaultCodecPayloads>> codecEncodeWithResponseAsync(VaultCodecPayloads vaultCodecEncodeParams, Context context) {
        final String accept = "application/json";
        return service.codecEncode(this.client.getHost(), vaultCodecEncodeParams, accept, context);
    }

    /**
     * Encode the requested payloads.
     *
     * @param vaultCodecEncodeParams Payloads to encode.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<VaultCodecPayloads> codecEncodeAsync(VaultCodecPayloads vaultCodecEncodeParams) {
        return codecEncodeWithResponseAsync(vaultCodecEncodeParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Encode the requested payloads.
     *
     * @param vaultCodecEncodeParams Payloads to encode.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<VaultCodecPayloads> codecEncodeAsync(VaultCodecPayloads vaultCodecEncodeParams, Context context) {
        return codecEncodeWithResponseAsync(vaultCodecEncodeParams, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Encode the requested payloads.
     *
     * @param vaultCodecEncodeParams Payloads to encode.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<VaultCodecPayloads> codecEncodeWithResponse(VaultCodecPayloads vaultCodecEncodeParams, Context context) {
        final String accept = "application/json";
        return service.codecEncodeSync(this.client.getHost(), vaultCodecEncodeParams, accept, context);
    }

    /**
     * Encode the requested payloads.
     *
     * @param vaultCodecEncodeParams Payloads to encode.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public VaultCodecPayloads codecEncode(VaultCodecPayloads vaultCodecEncodeParams) {
        return codecEncodeWithResponse(vaultCodecEncodeParams, Context.NONE).getValue();
    }

    /**
     * Decode the requested payloads.
     *
     * @param vaultCodecDecodeParams Payloads to decode.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<VaultCodecPayloads>> codecDecodeWithResponseAsync(VaultCodecPayloads vaultCodecDecodeParams) {
        return FluxUtil.withContext(context -> codecDecodeWithResponseAsync(vaultCodecDecodeParams, context));
    }

    /**
     * Decode the requested payloads.
     *
     * @param vaultCodecDecodeParams Payloads to decode.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<VaultCodecPayloads>> codecDecodeWithResponseAsync(VaultCodecPayloads vaultCodecDecodeParams, Context context) {
        final String accept = "application/json";
        return service.codecDecode(this.client.getHost(), vaultCodecDecodeParams, accept, context);
    }

    /**
     * Decode the requested payloads.
     *
     * @param vaultCodecDecodeParams Payloads to decode.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<VaultCodecPayloads> codecDecodeAsync(VaultCodecPayloads vaultCodecDecodeParams) {
        return codecDecodeWithResponseAsync(vaultCodecDecodeParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Decode the requested payloads.
     *
     * @param vaultCodecDecodeParams Payloads to decode.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<VaultCodecPayloads> codecDecodeAsync(VaultCodecPayloads vaultCodecDecodeParams, Context context) {
        return codecDecodeWithResponseAsync(vaultCodecDecodeParams, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Decode the requested payloads.
     *
     * @param vaultCodecDecodeParams Payloads to decode.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<VaultCodecPayloads> codecDecodeWithResponse(VaultCodecPayloads vaultCodecDecodeParams, Context context) {
        final String accept = "application/json";
        return service.codecDecodeSync(this.client.getHost(), vaultCodecDecodeParams, accept, context);
    }

    /**
     * Decode the requested payloads.
     *
     * @param vaultCodecDecodeParams Payloads to decode.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public VaultCodecPayloads codecDecode(VaultCodecPayloads vaultCodecDecodeParams) {
        return codecDecodeWithResponse(vaultCodecDecodeParams, Context.NONE).getValue();
    }
}
