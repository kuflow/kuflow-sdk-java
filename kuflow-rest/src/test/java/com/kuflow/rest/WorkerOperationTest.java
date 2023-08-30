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

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static org.assertj.core.api.Assertions.assertThat;

import com.github.tomakehurst.wiremock.extension.responsetemplating.ResponseTemplateTransformer;
import com.kuflow.rest.model.Worker;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkerOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN create a worker THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenCreateAWorkerThenAuthenticationHeaderIsAdded() {
        givenThat(
            post("/v2022-10-08/workers")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("workers-api.create.ok.json.hbs")
                        .withTransformers(ResponseTemplateTransformer.NAME)
                )
        );

        Worker workerRequest = new Worker()
            .setIdentity("identity-" + UUID.randomUUID())
            .setTaskQueue("task-queue-" + UUID.randomUUID())
            .setWorkflowTypes(List.of("wt-" + UUID.randomUUID(), "wt-" + UUID.randomUUID()))
            .setActivityTypes(List.of("at-" + UUID.randomUUID(), "at-" + UUID.randomUUID()))
            .setHostname("home-laptop")
            .setIp("1.1.1.1");

        Worker workerResponse = this.kuFlowRestClient.getWorkerOperations().createWorker(workerRequest);

        assertThat(workerResponse.getIdentity()).isEqualTo(workerRequest.getIdentity());
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN create a worker THEN response is correctly parsed")
    public void givenAnAuthenticatedUserWhenCreateAWorkerThenResponseIsCorrectlyParsed() {
        givenThat(
            post("/v2022-10-08/workers")
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("workers-api.create.ok.json.hbs")
                        .withTransformers(ResponseTemplateTransformer.NAME)
                )
        );

        Worker workerRequest = new Worker()
            .setIdentity("identity-" + UUID.randomUUID())
            .setTaskQueue("task-queue-" + UUID.randomUUID())
            .setWorkflowTypes(List.of("wt-" + UUID.randomUUID(), "wt-" + UUID.randomUUID()))
            .setActivityTypes(List.of("at-" + UUID.randomUUID(), "at-" + UUID.randomUUID()))
            .setHostname("home-laptop")
            .setIp("1.1.1.1");

        Worker workerResponse = this.kuFlowRestClient.getWorkerOperations().createWorker(workerRequest);

        assertThat(workerResponse.getIdentity()).isEqualTo(workerRequest.getIdentity());
    }
}
