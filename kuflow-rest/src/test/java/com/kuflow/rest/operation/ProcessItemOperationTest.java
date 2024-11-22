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
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.model.JsonValue;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemFindOptions;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import com.kuflow.rest.model.ProcessItemTaskState;
import com.kuflow.rest.model.ProcessItemType;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessItemOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list process items THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListTasksThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2024-06-14/process-items")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-items-api.list.ok.json"))
        );

        this.kuFlowRestClient.getProcessItemOperations().findProcessItems();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list process items using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListTasksUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID tenantId = UUID.randomUUID();
        UUID processId = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/process-items"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("tenantId", equalTo(tenantId.toString()))
                .withQueryParam("processId", equalTo(processId.toString()))
                .withQueryParam("processItemDefinitionCode", equalTo("code1"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-items-api.list.ok.json"))
        );

        ProcessItemFindOptions options = new ProcessItemFindOptions()
            .setSize(30)
            .setPage(2)
            .setSort("order1")
            .setTenantId(tenantId)
            .setProcessId(processId)
            .setTaskState(ProcessItemTaskState.READY)
            .setProcessItemDefinitionCode("code1");

        this.kuFlowRestClient.getProcessItemOperations().findProcessItems(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list process items using query params multivalued THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListTasksUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();
        UUID processId1 = UUID.randomUUID();
        UUID processId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/process-items"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .withQueryParam("processId", equalTo(processId1.toString()))
                .withQueryParam("processId", equalTo(processId2.toString()))
                .withQueryParam("processItemDefinitionCode", equalTo("code1"))
                .withQueryParam("processItemDefinitionCode", equalTo("code2"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-items-api.list.ok.json"))
        );

        ProcessItemFindOptions options = new ProcessItemFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2)
            .addProcessId(processId1)
            .addProcessId(processId2)
            .addProcessItemDefinitionCode("code1")
            .addProcessItemDefinitionCode("code2");

        this.kuFlowRestClient.getProcessItemOperations().findProcessItems(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN save a data value THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenSaveAJsonFormsValueThenAuthenticationHeaderIsAdded() {
        UUID processItemId = UUID.fromString("e2d0fdf9-0aae-4eed-9e07-8e4b76df733c");

        givenThat(
            put("/v2024-06-14/process-items/" + processItemId + "/task/data")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-items-api.retrieve.ok.json"))
        );

        JsonValue jsonValue = new JsonValue().setValue(Map.of("key", "value"));
        ProcessItemTaskDataUpdateParams command = new ProcessItemTaskDataUpdateParams().setData(jsonValue);

        this.kuFlowRestClient.getProcessItemOperations().updateProcessItemTaskData(processItemId, command);
    }

    @Test
    @DisplayName("GIVEN task requested WHEN get json forms data THEN expected obtain the correct values")
    public void givenTaskRequestedWhenGetJsonFormsDataThenExpectedObtainTheCorrectValues() {
        UUID processItemId = UUID.fromString("e2d0fdf9-0aae-4eed-9e07-8e4b76df733c");

        givenThat(
            get("/v2024-06-14/process-items/" + processItemId).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("process-items-api-json-forms.retrieve.ok.json")
            )
        );

        ProcessItem processItem = this.kuFlowRestClient.getProcessItemOperations().retrieveProcessItem(processItemId);

        assertThat(processItem.getType()).isEqualTo(ProcessItemType.TASK);
        assertThat(processItem.getTask().getData().getValue()).containsExactlyInAnyOrderEntriesOf(
            Map.of(
                "key1",
                "value1",
                "key2",
                List.of("value2_0", "value2_1", 505),
                "key3",
                List.of(Map.of("key3_0", "value3_0"), Map.of("key3_1", "value3_1")),
                "key4",
                Map.of("key4_child1", "value4_child1", "key4_child2", List.of("key4_child2_0", "key4_child2_1")),
                "key5",
                "2022-05-05",
                "key6",
                "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;",
                "key7",
                "kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;",
                "key8",
                List.of(true, false)
            )
        );
    }
}
