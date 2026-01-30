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

import com.kuflow.rest.model.BusinessArtifact;
import com.kuflow.rest.model.BusinessArtifactFindOptions;
import com.kuflow.rest.model.BusinessArtifactPage;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class BusinessArtifactOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list business artifacts THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListBusinessArtifactsThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2024-06-14/business-artifacts")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.list.ok.json"))
        );

        this.kuFlowRestClient.getBusinessArtifactOperations().findBusinessArtifacts();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list business artifacts using query params THEN the query parameters are sent")
    public void givenAnAuthenticatedUserWhenListBusinessArtifactsUsingQueryParamsThenTheQueryParametersAreSent() {
        UUID tenantId = UUID.randomUUID();
        UUID businessArtifactDefinitionId = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/business-artifacts"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("tenantId", equalTo(tenantId.toString()))
                .withQueryParam("businessArtifactDefinitionId", equalTo(businessArtifactDefinitionId.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.list.ok.json"))
        );

        BusinessArtifactFindOptions options = new BusinessArtifactFindOptions()
            .setSize(30)
            .setPage(2)
            .setSort("order1")
            .setTenantId(tenantId)
            .setBusinessArtifactDefinitionId(businessArtifactDefinitionId);

        this.kuFlowRestClient.getBusinessArtifactOperations().findBusinessArtifacts(options);
    }

    @Test
    @DisplayName(
        "GIVEN an authenticated user WHEN list business artifacts using query params multivalued THEN the query parameters are sent"
    )
    public void givenAnAuthenticatedUserWhenListBusinessArtifactsUsingQueryParamsMultivaluedThenTheQueryParametersAreSent() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();
        UUID businessArtifactDefinitionId1 = UUID.randomUUID();
        UUID businessArtifactDefinitionId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/business-artifacts"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .withQueryParam("businessArtifactDefinitionId", equalTo(businessArtifactDefinitionId1.toString()))
                .withQueryParam("businessArtifactDefinitionId", equalTo(businessArtifactDefinitionId2.toString()))
                .withQueryParam("businessArtifactDefinitionCode", equalTo("CODE1"))
                .withQueryParam("businessArtifactDefinitionCode", equalTo("CODE2"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.list.ok.json"))
        );

        BusinessArtifactFindOptions options = new BusinessArtifactFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2)
            .addBusinessArtifactDefinitionId(businessArtifactDefinitionId1)
            .addBusinessArtifactDefinitionId(businessArtifactDefinitionId2)
            .addBusinessArtifactDefinitionCode("CODE1")
            .addBusinessArtifactDefinitionCode("CODE2");

        this.kuFlowRestClient.getBusinessArtifactOperations().findBusinessArtifacts(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list business artifacts THEN expected obtain the correct value")
    public void givenAnAuthenticatedUserWhenListBusinessArtifactsThenExpectedObtainTheCorrectValue() {
        givenThat(
            get(urlPathEqualTo("/v2024-06-14/business-artifacts")).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.list.ok.json")
            )
        );

        BusinessArtifactPage businessArtifacts = this.kuFlowRestClient.getBusinessArtifactOperations().findBusinessArtifacts();

        assertThat(businessArtifacts.getMetadata().getSize()).isEqualTo(25);
        assertThat(businessArtifacts.getMetadata().getPage()).isEqualTo(0);
        assertThat(businessArtifacts.getMetadata().getTotalElements()).isEqualTo(2);
        assertThat(businessArtifacts.getMetadata().getTotalPages()).isEqualTo(1);
        assertThat(businessArtifacts.getContent()).hasSize(2);
        assertThat(businessArtifacts.getContent().get(0).getId()).isEqualTo(UUID.fromString("4f2467f1-00fa-4a6c-88ca-16ae8eac7b56"));
        assertThat(businessArtifacts.getContent().get(1).getId()).isEqualTo(UUID.fromString("2fd90b51-e1d8-44e3-89bd-8c4c9fc4164b"));
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve a business artifact THEN body is parsed correctly")
    public void givenAnAuthenticatedUserWhenRetrieveABusinessArtifactThenBodyIsParsedCorrectly() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            get("/v2024-06-14/business-artifacts/" + businessArtifactId).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.retrieve.ok.json")
            )
        );

        BusinessArtifact businessArtifact =
            this.kuFlowRestClient.getBusinessArtifactOperations().retrieveBusinessArtifact(businessArtifactId);

        assertThat(businessArtifact.getId()).isEqualTo(businessArtifactId);
        assertThat(businessArtifact.getTenantId()).isEqualTo(UUID.fromString("00a9f1d4-3698-45a4-951c-66a468846aad"));
        assertThat(businessArtifact.getBusinessArtifactDefinitionRef()).isNotNull();
        assertThat(businessArtifact.getBusinessArtifactDefinitionRef().getCode()).isEqualTo("ARTIFACT_DEF_CODE");
    }
}
