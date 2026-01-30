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
package com.kuflow.rest;

import com.azure.core.annotation.ServiceClient;
import com.azure.core.util.serializer.SerializerEncoding;
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.model.WebhookEvent;
import com.kuflow.rest.operation.AuthenticationOperations;
import com.kuflow.rest.operation.BusinessArtifactOperations;
import com.kuflow.rest.operation.GroupOperations;
import com.kuflow.rest.operation.KmsOperations;
import com.kuflow.rest.operation.PrincipalOperations;
import com.kuflow.rest.operation.ProcessItemOperations;
import com.kuflow.rest.operation.ProcessOperations;
import com.kuflow.rest.operation.RobotOperations;
import com.kuflow.rest.operation.TenantOperations;
import com.kuflow.rest.operation.TenantUserOperations;
import com.kuflow.rest.operation.WorkerOperations;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@ServiceClient(builder = KuFlowRestClientBuilder.class)
public class KuFlowRestClient {

    public static final String API_VERSION = "v2024-06-14";

    private final KuFlowClientImpl client;

    private final AuthenticationOperations authenticationOperations;

    private final PrincipalOperations principalOperations;

    private final GroupOperations groupOperations;

    private final TenantUserOperations tenantUserOperations;

    private final ProcessOperations processOperations;

    private final ProcessItemOperations processItemOperations;

    private final BusinessArtifactOperations businessArtifactOperations;

    private final WorkerOperations workerOperations;

    private final RobotOperations robotOperations;

    private final TenantOperations tenantOperations;

    private final KmsOperations kmsOperations;

    public KuFlowRestClient(KuFlowClientImpl client) {
        this.client = client;
        this.authenticationOperations = new AuthenticationOperations(client);
        this.principalOperations = new PrincipalOperations(client);
        this.groupOperations = new GroupOperations(client);
        this.tenantUserOperations = new TenantUserOperations(client);
        this.processOperations = new ProcessOperations(client);
        this.processItemOperations = new ProcessItemOperations(client);
        this.businessArtifactOperations = new BusinessArtifactOperations(client);
        this.workerOperations = new WorkerOperations(client);
        this.robotOperations = new RobotOperations(client);
        this.tenantOperations = new TenantOperations(client);
        this.kmsOperations = new KmsOperations(client);
    }

    public AuthenticationOperations getAuthenticationOperations() {
        return this.authenticationOperations;
    }

    public PrincipalOperations getPrincipalOperations() {
        return this.principalOperations;
    }

    public GroupOperations getGroupOperations() {
        return this.groupOperations;
    }

    public TenantUserOperations getTenantUserOperations() {
        return this.tenantUserOperations;
    }

    public ProcessOperations getProcessOperations() {
        return this.processOperations;
    }

    public ProcessItemOperations getProcessItemOperations() {
        return this.processItemOperations;
    }

    public BusinessArtifactOperations getBusinessArtifactOperations() {
        return this.businessArtifactOperations;
    }

    public WorkerOperations getWorkerOperations() {
        return this.workerOperations;
    }

    public RobotOperations getRobotOperations() {
        return this.robotOperations;
    }

    public TenantOperations getTenantOperations() {
        return this.tenantOperations;
    }

    public KmsOperations getKmsOperations() {
        return this.kmsOperations;
    }

    public WebhookEvent parseWebhookEvent(String payload) {
        try {
            InputStream is = new ByteArrayInputStream(payload.getBytes(StandardCharsets.UTF_8));

            return this.client.getSerializerAdapter().deserialize(is, WebhookEvent.class, SerializerEncoding.JSON);
        } catch (IOException e) {
            throw new KuFlowRestClientException("Error, parsing webhook event", e);
        }
    }
}
