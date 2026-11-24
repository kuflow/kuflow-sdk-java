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

import static com.github.tomakehurst.wiremock.client.WireMock.absent;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.havingExactly;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.kuflow.rest.model.ProcessActionType;
import com.kuflow.rest.model.ProcessDefinition;
import com.kuflow.rest.model.ProcessDefinitionFindOptions;
import com.kuflow.rest.model.ProcessDefinitionFormType;
import com.kuflow.rest.model.ProcessDefinitionPage;
import com.kuflow.rest.model.ProcessDefinitionPageItem;
import com.kuflow.rest.model.ProcessDefinitionRbacPrincipalType;
import com.kuflow.rest.model.ProcessDefinitionState;
import com.kuflow.rest.model.ProcessDefinitionStructureDataDefinitionEntry;
import com.kuflow.rest.model.ProcessDefinitionVersionState;
import com.kuflow.rest.model.ProcessItemDefinition;
import com.kuflow.rest.model.ProcessItemDefinitionType;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessDefinitionOperationTest extends AbstractOperationTest {

    private static final UUID PROCESS_DEFINITION_ID = UUID.fromString("5a0f6a3e-5a1b-4c4e-9a59-1e0b7c3a1d01");

    @Test
    @DisplayName("GIVEN an authenticated user WHEN find process definitions THEN authentication header is added and the page is parsed")
    public void givenAnAuthenticatedUserWhenFindProcessDefinitionsThenAuthenticationHeaderIsAddedAndThePageIsParsed() {
        givenThat(
            get("/v2024-06-14/process-definitions")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.list.ok.json"))
        );

        ProcessDefinitionPage page = this.kuFlowRestClient.getProcessDefinitionOperations().findProcessDefinitions();

        assertThat(page.getMetadata().getTotalElements()).isEqualTo(2);
        assertThat(page.getContent())
            .extracting(ProcessDefinitionPageItem::getCode, ProcessDefinitionPageItem::getState, it -> it.getVersion().getState())
            .containsExactly(
                tuple("EXPENSES", ProcessDefinitionState.PUBLISHED, ProcessDefinitionVersionState.DRAFT),
                tuple("HOLIDAYS", ProcessDefinitionState.UNPUBLISHED, ProcessDefinitionVersionState.CURRENT)
            );
    }

    @Test
    @DisplayName(
        "GIVEN an authenticated user WHEN find process definitions using query params multivalued THEN the query parameters are send"
    )
    public void givenAnAuthenticatedUserWhenFindProcessDefinitionsUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/process-definitions"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("name,asc"))
                .withQueryParam("tenantId", havingExactly(tenantId1.toString(), tenantId2.toString()))
                .withQueryParam("processDefinitionCode", havingExactly("EXPENSES", "HOLIDAYS"))
                .withQueryParam("state", havingExactly("PUBLISHED", "UNPUBLISHED"))
                .withQueryParam("name", equalTo("vacation request"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.list.ok.json"))
        );

        ProcessDefinitionFindOptions options = new ProcessDefinitionFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("name,asc")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2)
            .addProcessDefinitionCode("EXPENSES")
            .addProcessDefinitionCode("HOLIDAYS")
            .addState(ProcessDefinitionState.PUBLISHED)
            .addState(ProcessDefinitionState.UNPUBLISHED)
            .setName("vacation request");

        ProcessDefinitionPage page = this.kuFlowRestClient.getProcessDefinitionOperations().findProcessDefinitions(options);

        assertThat(page.getContent()).hasSize(2);
    }

    @Test
    @DisplayName(
        "GIVEN an authenticated user WHEN retrieve a process definition THEN its forms and workflow come as JSON and its parts by code"
    )
    public void givenAnAuthenticatedUserWhenRetrieveAProcessDefinitionThenItsFormsAndWorkflowComeAsJsonAndItsPartsByCode() {
        givenThat(
            get("/v2024-06-14/process-definitions/" + PROCESS_DEFINITION_ID).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.retrieve.ok.json")
            )
        );

        ProcessDefinition processDefinition = this.kuFlowRestClient
            .getProcessDefinitionOperations()
            .retrieveProcessDefinition(PROCESS_DEFINITION_ID);

        assertThat(processDefinition.getId()).isEqualTo(PROCESS_DEFINITION_ID);
        assertThat(processDefinition.getVersion().getState()).isEqualTo(ProcessDefinitionVersionState.DRAFT);
        assertThat(processDefinition.getRevision()).hasSize(64);
        assertThat(processDefinition.getWorkflowDefinition()).containsKey("document");

        ProcessDefinitionStructureDataDefinitionEntry expense = processDefinition.getStructureDataDefinitionEntries().get(0);
        assertThat(expense.getCode()).isEqualTo("EXPENSE");
        assertThat(expense.getJsonDefinition().getType()).isEqualTo(ProcessDefinitionFormType.FORMIO);
        assertThat(expense.getJsonDefinition().getSchema()).containsKey("components");
        assertThat((List<?>) expense.getJsonDefinition().getSchema().get("components"))
            .singleElement()
            .isInstanceOf(Map.class);
        assertThat(expense.getJsonDefinition().getTemplate()).isEqualTo("{{amount}}");

        ProcessItemDefinition task = processDefinition.getProcessItemDefinitions().get(0);
        assertThat(task.getType()).isEqualTo(ProcessItemDefinitionType.TASK);
        assertThat(task.getTask().getStructureDataDefinitionCode()).isEqualTo("EXPENSE");
        assertThat(task.getNotifications().isProcessItemTaskCreatedCandidates()).isTrue();
        assertThat(task.getNotifications().isProcessItemTaskAssignedOwner()).isFalse();

        assertThat(processDefinition.getActionDefinitions())
            .hasSize(2)
            .first()
            .satisfies(it -> {
                assertThat(it.getType()).isEqualTo(ProcessActionType.CREATE_TASK);
                assertThat(it.getCreateTask().getProcessItemDefinitionCode()).isEqualTo(task.getCode());
                assertThat(it.getCreateTask().getProcessItemDefinitionId()).isNull();
            });
        // A task that the version does not have comes by its id
        assertThat(processDefinition.getActionDefinitions().get(1).getCreateTask()).satisfies(it -> {
            assertThat(it.getProcessItemDefinitionCode()).isNull();
            assertThat(it.getProcessItemDefinitionId()).isEqualTo(UUID.fromString("2c3d4e5f-0000-4000-8000-0000000000aa"));
        });
        assertThat(processDefinition.getRbacEntries())
            .filteredOn(it -> ProcessDefinitionRbacPrincipalType.EXPRESSION.equals(it.getPrincipalType()))
            .singleElement()
            .satisfies(it -> assertThat(it.getExpression()).isEqualTo(".metadata.value.owner"));
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve a version of a process definition THEN the version is sent")
    public void givenAnAuthenticatedUserWhenRetrieveAVersionOfAProcessDefinitionThenTheVersionIsSent() {
        UUID version = UUID.fromString("7d1c2b30-8e4f-4f6a-9c1d-2b3e4f5a6b01");

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/process-definitions/" + PROCESS_DEFINITION_ID))
                .withQueryParam("version", equalTo(version.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.retrieve.ok.json"))
        );

        ProcessDefinition processDefinition = this.kuFlowRestClient
            .getProcessDefinitionOperations()
            .retrieveProcessDefinition(PROCESS_DEFINITION_ID, version);

        assertThat(processDefinition.getVersion().getId()).isEqualTo(version);
    }

    @Test
    @DisplayName(
        "GIVEN a process definition that was read WHEN update it with dryRun THEN the document and dryRun are sent and it comes as it would be"
    )
    public void givenAProcessDefinitionThatWasReadWhenUpdateItWithDryRunThenTheDocumentAndDryRunAreSentAndItComesAsItWouldBe() {
        ProcessDefinition processDefinition = this.retrieveProcessDefinition();
        givenThat(
            put(urlPathEqualTo("/v2024-06-14/process-definitions/" + PROCESS_DEFINITION_ID))
                .withQueryParam("dryRun", equalTo("true"))
                .withRequestBody(matchingJsonPath("$.revision", equalTo(processDefinition.getRevision())))
                .withRequestBody(matchingJsonPath("$.structureDataDefinitionEntries[0].jsonDefinition.schema.components[0]"))
                .willReturn(
                    ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.update.dry-run.ok.json")
                )
        );

        ProcessDefinition result = this.kuFlowRestClient
            .getProcessDefinitionOperations()
            .updateProcessDefinition(PROCESS_DEFINITION_ID, processDefinition, true);

        assertThat(result.getRevision()).hasSize(64).isNotEqualTo(processDefinition.getRevision());
        List<?> components = (List<?>) result.getStructureDataDefinitionEntries().get(0).getJsonDefinition().getSchema().get("components");
        assertThat(((Map<?, ?>) components.get(0)).get("label")).isEqualTo("Importe");
    }

    @Test
    @DisplayName("GIVEN a process definition that was read WHEN update it THEN no dryRun is sent and it comes with its new revision")
    public void givenAProcessDefinitionThatWasReadWhenUpdateItThenNoDryRunIsSentAndItComesWithItsNewRevision() {
        ProcessDefinition processDefinition = this.retrieveProcessDefinition();
        givenThat(
            put(urlPathEqualTo("/v2024-06-14/process-definitions/" + PROCESS_DEFINITION_ID))
                .withQueryParam("dryRun", absent())
                .withRequestBody(matchingJsonPath("$.revision", equalTo(processDefinition.getRevision())))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.update.ok.json"))
        );

        ProcessDefinition result = this.kuFlowRestClient
            .getProcessDefinitionOperations()
            .updateProcessDefinition(PROCESS_DEFINITION_ID, processDefinition);

        assertThat(result.getRevision()).hasSize(64).isNotEqualTo(processDefinition.getRevision());
    }

    private ProcessDefinition retrieveProcessDefinition() {
        givenThat(
            get("/v2024-06-14/process-definitions/" + PROCESS_DEFINITION_ID).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("process-definitions-api.retrieve.ok.json")
            )
        );

        return this.kuFlowRestClient.getProcessDefinitionOperations().retrieveProcessDefinition(PROCESS_DEFINITION_ID);
    }
}
