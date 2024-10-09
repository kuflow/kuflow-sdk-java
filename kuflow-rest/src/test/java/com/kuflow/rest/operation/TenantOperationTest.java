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

import com.kuflow.rest.model.TenantFindOptions;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TenantOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tenants THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListTenantsThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2024-06-14/tenants")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tenants-api.list.ok.json"))
        );

        this.kuFlowRestClient.getTenantOperations().findTenants();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tenants using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListTenantsUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/tenants"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tenants-api.list.ok.json"))
        );

        TenantFindOptions options = new TenantFindOptions().setSize(30).setPage(2).addSort("order1").addTenantId(tenantId1);

        this.kuFlowRestClient.getTenantOperations().findTenants(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tenants using query params multivalued THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListTenantsUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/tenants"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tenants-api.list.ok.json"))
        );

        TenantFindOptions options = new TenantFindOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2);

        this.kuFlowRestClient.getTenantOperations().findTenants(options);
    }
}
