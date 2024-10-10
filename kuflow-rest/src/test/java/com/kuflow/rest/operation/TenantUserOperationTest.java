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

import com.kuflow.rest.model.TenantUser;
import com.kuflow.rest.model.TenantUserFindOptions;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TenantUserOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tenants users THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListTenantUsersThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2024-06-14/tenant-users")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tenant-users-api.list.ok.json"))
        );

        this.kuFlowRestClient.getTenantUserOperations().findTenantUsers();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tenant users using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListTenantUsersUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID groupId1 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/tenant-users"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("groupId", equalTo(groupId1.toString()))
                .withQueryParam("email", equalTo("email@email.com"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tenant-users-api.list.ok.json"))
        );

        TenantUserFindOptions options = new TenantUserFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addTenantId(tenantId1)
            .addGroupId(groupId1)
            .addEmail("email@email.com");

        this.kuFlowRestClient.getTenantUserOperations().findTenantUsers(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tenant users using query params multivalued THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListTenantUsersUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();
        UUID groupId1 = UUID.randomUUID();
        UUID groupId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/tenant-users"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .withQueryParam("groupId", equalTo(groupId1.toString()))
                .withQueryParam("groupId", equalTo(groupId2.toString()))
                .withQueryParam("email", equalTo("email@email.com"))
                .withQueryParam("email", equalTo("email2@email.com"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tenant-users-api.list.ok.json"))
        );

        TenantUserFindOptions options = new TenantUserFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2)
            .addGroupId(groupId1)
            .addGroupId(groupId2)
            .addEmail("email@email.com")
            .addEmail("email2@email.com");

        this.kuFlowRestClient.getTenantUserOperations().findTenantUsers(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve a robot THEN body is parsed correctly")
    public void givenAnAuthenticatedUserWhenRetrieveARobotThenBodyIsParsedCorrectly() {
        UUID tenantUserId = UUID.fromString("8a68bef6-3e54-466f-8d08-b9b1f5c551fc");

        givenThat(
            get("/v2024-06-14/tenant-users/" + tenantUserId).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("tenant-users-api.retrieve.ok.json")
            )
        );

        TenantUser tenantUser = this.kuFlowRestClient.getTenantUserOperations().retrieveTenantUser(tenantUserId);

        assertThat(tenantUser.getId()).isEqualTo(tenantUserId);
        assertThat(tenantUser.getMetadata()).isNotNull();
        assertThat(tenantUser.getMetadata().getValue().get("Organizations")).isNotNull();
        assertThat(tenantUser.getPrincipal()).isNotNull();
        assertThat(tenantUser.getPrincipal().getName()).isEqualTo("Paula Smith Cook");
    }
}
