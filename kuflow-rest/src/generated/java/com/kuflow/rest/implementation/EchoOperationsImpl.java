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
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.Authentication;
import com.kuflow.rest.model.DefaultErrorException;
import reactor.core.publisher.Mono;

/** An instance of this class provides access to all the operations defined in EchoOperations. */
public final class EchoOperationsImpl {
    /** The proxy service used to perform REST calls. */
    private final EchoOperationsService service;

    /** The service client containing this operation class. */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of EchoOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    EchoOperationsImpl(KuFlowClientImpl client) {
        this.service =
                RestProxy.create(EchoOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientEchoOperations to be used by the proxy service to perform
     * REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientEchoOper")
    public interface EchoOperationsService {
        @Get("/echo")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Authentication>> requestEcho(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);

        @Get("/echo")
        @ExpectedResponses({200})
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Authentication> requestEchoSync(
                @HostParam("$host") String host, @HeaderParam("Accept") String accept, Context context);
    }

    /**
     * Echo endpoint to test integration, return a DUMMY authentication token.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Authentication>> requestEchoWithResponseAsync() {
        final String accept = "application/json";
        return FluxUtil.withContext(context -> service.requestEcho(this.client.getHost(), accept, context));
    }

    /**
     * Echo endpoint to test integration, return a DUMMY authentication token.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Authentication>> requestEchoWithResponseAsync(Context context) {
        final String accept = "application/json";
        return service.requestEcho(this.client.getHost(), accept, context);
    }

    /**
     * Echo endpoint to test integration, return a DUMMY authentication token.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Authentication> requestEchoAsync() {
        return requestEchoWithResponseAsync().flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Echo endpoint to test integration, return a DUMMY authentication token.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Authentication> requestEchoAsync(Context context) {
        return requestEchoWithResponseAsync(context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Echo endpoint to test integration, return a DUMMY authentication token.
     *
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Authentication> requestEchoWithResponse(Context context) {
        final String accept = "application/json";
        return service.requestEchoSync(this.client.getHost(), accept, context);
    }

    /**
     * Echo endpoint to test integration, return a DUMMY authentication token.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Authentication requestEcho() {
        return requestEchoWithResponse(Context.NONE).getValue();
    }
}
