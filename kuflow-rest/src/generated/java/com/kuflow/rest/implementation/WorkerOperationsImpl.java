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
import com.kuflow.rest.model.Worker;
import com.kuflow.rest.model.WorkerCreateParams;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in WorkerOperations.
 */
public final class WorkerOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final WorkerOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of WorkerOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    WorkerOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(WorkerOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientWorkerOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientWorkerOp")
    public interface WorkerOperationsService {
        @Post("/workers")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<Worker>> createWorker(
            @HostParam("$host") String host,
            @BodyParam("application/json") WorkerCreateParams workerCreateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/workers")
        @ExpectedResponses({ 200, 201 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<Worker> createWorkerSync(
            @HostParam("$host") String host,
            @BodyParam("application/json") WorkerCreateParams workerCreateParams,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Create or update a worker
     *
     * Register a worker in KuFlow, this allows the platform to have a catalogue of all registered workers.
     *
     * If already exist a worker for the same identity, the worker will be updated.
     *
     * @param workerCreateParams Worker to create or update.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Worker>> createWorkerWithResponseAsync(WorkerCreateParams workerCreateParams) {
        return FluxUtil.withContext(context -> createWorkerWithResponseAsync(workerCreateParams, context));
    }

    /**
     * Create or update a worker
     *
     * Register a worker in KuFlow, this allows the platform to have a catalogue of all registered workers.
     *
     * If already exist a worker for the same identity, the worker will be updated.
     *
     * @param workerCreateParams Worker to create or update.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<Worker>> createWorkerWithResponseAsync(WorkerCreateParams workerCreateParams, Context context) {
        final String accept = "application/json";
        return service.createWorker(this.client.getHost(), workerCreateParams, accept, context);
    }

    /**
     * Create or update a worker
     *
     * Register a worker in KuFlow, this allows the platform to have a catalogue of all registered workers.
     *
     * If already exist a worker for the same identity, the worker will be updated.
     *
     * @param workerCreateParams Worker to create or update.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Worker> createWorkerAsync(WorkerCreateParams workerCreateParams) {
        return createWorkerWithResponseAsync(workerCreateParams).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create or update a worker
     *
     * Register a worker in KuFlow, this allows the platform to have a catalogue of all registered workers.
     *
     * If already exist a worker for the same identity, the worker will be updated.
     *
     * @param workerCreateParams Worker to create or update.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Worker> createWorkerAsync(WorkerCreateParams workerCreateParams, Context context) {
        return createWorkerWithResponseAsync(workerCreateParams, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Create or update a worker
     *
     * Register a worker in KuFlow, this allows the platform to have a catalogue of all registered workers.
     *
     * If already exist a worker for the same identity, the worker will be updated.
     *
     * @param workerCreateParams Worker to create or update.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Worker> createWorkerWithResponse(WorkerCreateParams workerCreateParams, Context context) {
        final String accept = "application/json";
        return service.createWorkerSync(this.client.getHost(), workerCreateParams, accept, context);
    }

    /**
     * Create or update a worker
     *
     * Register a worker in KuFlow, this allows the platform to have a catalogue of all registered workers.
     *
     * If already exist a worker for the same identity, the worker will be updated.
     *
     * @param workerCreateParams Worker to create or update.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Worker createWorker(WorkerCreateParams workerCreateParams) {
        return createWorkerWithResponse(workerCreateParams, Context.NONE).getValue();
    }
}
