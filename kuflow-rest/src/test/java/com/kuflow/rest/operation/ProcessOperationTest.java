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
package com.kuflow.rest.operation;

import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.model.FindProcessesOptions;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.ProcessPageItem;
import com.kuflow.rest.model.ProcessState;
import com.kuflow.rest.util.ProcessPageItemUtils;
import com.kuflow.rest.util.ProcessUtils;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list processes THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListProcessesThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2022-10-08/processes")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("processes-api.list.ok.json"))
        );

        this.kuFlowRestClient.getProcessOperations().findProcesses();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list processes THEN body is parsed correctly")
    public void givenAnAuthenticatedUserWhenListProcessesThenBodyIsParsedCorrectly() {
        givenThat(
            get("/v2022-10-08/processes").willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("processes-api.list.ok.json")
            )
        );

        ProcessPage processes = this.kuFlowRestClient.getProcessOperations().findProcesses();

        assertThat(processes.getMetadata().getTotalElements()).isEqualTo(2);
        assertThat(processes.getContent()).hasSize(2);

        ProcessPageItem processPageItem = processes.getContent().get(0);
        List<String> code001Value = ProcessPageItemUtils.getElementValueAsStringList(processPageItem, "CODE_001");

        assertThat(processPageItem.getElementValues()).containsOnlyKeys("CODE_001", "CODE_002");
        assertThat(code001Value).containsOnly("Value as text 1", "Value as text 2");
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list processes using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListProcessesUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID tenantId = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2022-10-08/processes"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("tenantId", equalTo(tenantId.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("processes-api.list.ok.json"))
        );

        FindProcessesOptions options = new FindProcessesOptions().setSize(30).setPage(2).setSort("order1").setTenantId(tenantId);

        this.kuFlowRestClient.getProcessOperations().findProcesses(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list processes using query params multivalued THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListProcessesUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2022-10-08/processes"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("processes-api.list.ok.json"))
        );

        FindProcessesOptions options = new FindProcessesOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2);

        this.kuFlowRestClient.getProcessOperations().findProcesses(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve processes THEN body is parsed correctly")
    public void givenAnAuthenticatedUserWhenRetrieveProcessesThenBodyIsParsedCorrectly() {
        UUID processId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            get("/v2022-10-08/processes/" + processId).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("processes-api.retrieve.ok.json")
            )
        );

        Process process = this.kuFlowRestClient.getProcessOperations().retrieveProcess(processId);

        assertThat(process.getState()).isEqualTo(ProcessState.RUNNING);
        assertThat(process.getElementValues()).containsOnlyKeys("CODE_001", "CODE_002");

        List<String> code001Values = ProcessUtils.getElementValueAsStringList(process, "CODE_001");
        assertThat(code001Values).containsOnly("Value as text 1", "Value as text 2");
    }
}
