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

import com.azure.core.http.HttpPipeline;
import com.azure.core.http.HttpPipelineBuilder;
import com.azure.core.http.policy.RetryPolicy;
import com.azure.core.http.policy.UserAgentPolicy;
import com.azure.core.util.serializer.JacksonAdapter;
import com.azure.core.util.serializer.SerializerAdapter;

/** Initializes a new instance of the KuFlowClient type. */
public final class KuFlowClientImpl {

    /** server parameter. */
    private final String host;

    /**
     * Gets server parameter.
     *
     * @return the host value.
     */
    public String getHost() {
        return this.host;
    }

    /** The HTTP pipeline to send requests through. */
    private final HttpPipeline httpPipeline;

    /**
     * Gets The HTTP pipeline to send requests through.
     *
     * @return the httpPipeline value.
     */
    public HttpPipeline getHttpPipeline() {
        return this.httpPipeline;
    }

    /** The serializer to serialize an object into a string. */
    private final SerializerAdapter serializerAdapter;

    /**
     * Gets The serializer to serialize an object into a string.
     *
     * @return the serializerAdapter value.
     */
    public SerializerAdapter getSerializerAdapter() {
        return this.serializerAdapter;
    }

    /** The AuthenticationOperationsImpl object to access its operations. */
    private final AuthenticationOperationsImpl authenticationOperations;

    /**
     * Gets the AuthenticationOperationsImpl object to access its operations.
     *
     * @return the AuthenticationOperationsImpl object.
     */
    public AuthenticationOperationsImpl getAuthenticationOperations() {
        return this.authenticationOperations;
    }

    /** The PrincipalOperationsImpl object to access its operations. */
    private final PrincipalOperationsImpl principalOperations;

    /**
     * Gets the PrincipalOperationsImpl object to access its operations.
     *
     * @return the PrincipalOperationsImpl object.
     */
    public PrincipalOperationsImpl getPrincipalOperations() {
        return this.principalOperations;
    }

    /** The ProcessOperationsImpl object to access its operations. */
    private final ProcessOperationsImpl processOperations;

    /**
     * Gets the ProcessOperationsImpl object to access its operations.
     *
     * @return the ProcessOperationsImpl object.
     */
    public ProcessOperationsImpl getProcessOperations() {
        return this.processOperations;
    }

    /** The TaskOperationsImpl object to access its operations. */
    private final TaskOperationsImpl taskOperations;

    /**
     * Gets the TaskOperationsImpl object to access its operations.
     *
     * @return the TaskOperationsImpl object.
     */
    public TaskOperationsImpl getTaskOperations() {
        return this.taskOperations;
    }

    /** The WorkerOperationsImpl object to access its operations. */
    private final WorkerOperationsImpl workerOperations;

    /**
     * Gets the WorkerOperationsImpl object to access its operations.
     *
     * @return the WorkerOperationsImpl object.
     */
    public WorkerOperationsImpl getWorkerOperations() {
        return this.workerOperations;
    }

    /**
     * Initializes an instance of KuFlowClient client.
     *
     * @param host server parameter.
     */
    KuFlowClientImpl(String host) {
        this(
            new HttpPipelineBuilder().policies(new UserAgentPolicy(), new RetryPolicy()).build(),
            JacksonAdapter.createDefaultSerializerAdapter(),
            host
        );
    }

    /**
     * Initializes an instance of KuFlowClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param host server parameter.
     */
    KuFlowClientImpl(HttpPipeline httpPipeline, String host) {
        this(httpPipeline, JacksonAdapter.createDefaultSerializerAdapter(), host);
    }

    /**
     * Initializes an instance of KuFlowClient client.
     *
     * @param httpPipeline The HTTP pipeline to send requests through.
     * @param serializerAdapter The serializer to serialize an object into a string.
     * @param host server parameter.
     */
    KuFlowClientImpl(HttpPipeline httpPipeline, SerializerAdapter serializerAdapter, String host) {
        this.httpPipeline = httpPipeline;
        this.serializerAdapter = serializerAdapter;
        this.host = host;
        this.authenticationOperations = new AuthenticationOperationsImpl(this);
        this.principalOperations = new PrincipalOperationsImpl(this);
        this.processOperations = new ProcessOperationsImpl(this);
        this.taskOperations = new TaskOperationsImpl(this);
        this.workerOperations = new WorkerOperationsImpl(this);
    }
}
