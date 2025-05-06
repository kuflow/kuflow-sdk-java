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

import com.kuflow.rest.model.GroupFindOptions;
import com.kuflow.rest.model.GroupPage;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class GroupOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list groups THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListGroupsThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2024-06-14/groups")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("groups-api.list.ok.json"))
        );

        this.kuFlowRestClient.getGroupOperations().findGroups();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list groups THEN result is correctly parsed")
    public void givenAnAuthenticatedUserWhenListGroupsThenResultIsCorrectlyParsed() {
        givenThat(
            get("/v2024-06-14/groups").willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("groups-api.list.ok.json")
            )
        );

        GroupPage groups = this.kuFlowRestClient.getGroupOperations().findGroups();
        assertThat(groups.getMetadata().getTotalElements()).isEqualTo(4);
        assertThat(groups.getContent().get(0).getName()).isEqualTo("My group");
        assertThat(groups.getContent().get(1).getName()).isEqualTo("Organization Administrators");
        assertThat(groups.getContent().get(2).getName()).isEqualTo("Users");
        assertThat(groups.getContent().get(3).getName()).isEqualTo("Organization Owners");
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list groups using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListGroupsUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID princiaplId = UUID.randomUUID();
        UUID tenantId = UUID.randomUUID();
        UUID groupId1 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/groups"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("principalId", equalTo(princiaplId.toString()))
                .withQueryParam("tenantId", equalTo(tenantId.toString()))
                .withQueryParam("groupId", equalTo(groupId1.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("groups-api.list.ok.json"))
        );

        GroupFindOptions options = new GroupFindOptions()
            .setSize(30)
            .setPage(2)
            .setSort("order1")
            .setPrincipalId(princiaplId)
            .setTenantId(tenantId)
            .setGroupId(groupId1);

        this.kuFlowRestClient.getGroupOperations().findGroups(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list groups using query params multivalued THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListGroupsUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID princiaplId = UUID.randomUUID();
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();
        UUID groupId1 = UUID.randomUUID();
        UUID groupId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/groups"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("principalId", equalTo(princiaplId.toString()))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .withQueryParam("groupId", equalTo(groupId1.toString()))
                .withQueryParam("groupId", equalTo(groupId2.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("groups-api.list.ok.json"))
        );

        GroupFindOptions options = new GroupFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .setPrincipalId(princiaplId)
            .addTenantId(tenantId1)
            .addTenantId(tenantId2)
            .setGroupId(groupId1)
            .setGroupId(groupId2);

        this.kuFlowRestClient.getGroupOperations().findGroups(options);
    }
}
