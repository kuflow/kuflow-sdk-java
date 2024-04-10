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

import com.kuflow.rest.model.FindRobotsOptions;
import com.kuflow.rest.model.Robot;
import com.kuflow.rest.model.RobotPage;
import com.kuflow.rest.model.RobotSourceType;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RobotOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list robots THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListRobotsThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2022-10-08/robots")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("robots-api.list.ok.json"))
        );

        this.kuFlowRestClient.getRobotOperations().findRobots();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list robots using query params THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListRobotsUsingQueryParamsThenTheQueryParametersAreSend() {
        UUID tenantId = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2022-10-08/robots"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("tenantId", equalTo(tenantId.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("robots-api.list.ok.json"))
        );

        FindRobotsOptions options = new FindRobotsOptions().setSize(30).setPage(2).setSort("order1").setTenantId(tenantId);

        this.kuFlowRestClient.getRobotOperations().findRobots(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list robots using query params multivalued THEN the query parameters are send")
    public void givenAnAuthenticatedUserWhenListRobotsUsingQueryParamsMultivaluedThenTheQueryParametersAreSend() {
        UUID tenantId1 = UUID.randomUUID();
        UUID tenantId2 = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2022-10-08/robots"))
                .withQueryParam("size", equalTo("30"))
                .withQueryParam("page", equalTo("2"))
                .withQueryParam("sort", equalTo("order1"))
                .withQueryParam("sort", equalTo("order2"))
                .withQueryParam("tenantId", equalTo(tenantId1.toString()))
                .withQueryParam("tenantId", equalTo(tenantId2.toString()))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("robots-api.list.ok.json"))
        );

        FindRobotsOptions options = new FindRobotsOptions()
            .setSize(30)
            .setPage(2)
            .addSort("order1")
            .addSort("order2")
            .addTenantId(tenantId1)
            .addTenantId(tenantId2);

        this.kuFlowRestClient.getRobotOperations().findRobots(options);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list robots THEN expected obtain the correct value")
    public void givenAnAuthenticatedUserWhenListRobotsThenExpectedObtainTheCorrectValue() {
        givenThat(
            get(urlPathEqualTo("/v2022-10-08/robots")).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("robots-api.list.ok.json")
            )
        );

        RobotPage robots = this.kuFlowRestClient.getRobotOperations().findRobots();

        assertThat(robots.getMetadata().getSize()).isEqualTo(25);
        assertThat(robots.getMetadata().getPage()).isEqualTo(0);
        assertThat(robots.getMetadata().getTotalElements()).isEqualTo(2);
        assertThat(robots.getMetadata().getTotalPages()).isEqualTo(1);
        assertThat(robots.getContent()).hasSize(2);
        assertThat(robots.getContent().get(0).getId()).isEqualTo(UUID.fromString("4f2467f1-00fa-4a6c-88ca-16ae8eac7b56"));
        assertThat(robots.getContent().get(1).getId()).isEqualTo(UUID.fromString("2fd90b51-e1d8-44e3-89bd-8c4c9fc4164b"));
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN retrieve a robot THEN body is parsed correctly")
    public void givenAnAuthenticatedUserWhenRetrieveARobotThenBodyIsParsedCorrectly() {
        UUID robotId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            get("/v2022-10-08/robots/" + robotId).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("robots-api.retrieve.ok.json")
            )
        );

        Robot robot = this.kuFlowRestClient.getRobotOperations().retrieveRobot(robotId);

        assertThat(robot.getId()).isEqualTo(robotId);
        assertThat(robot.getName()).isEqualTo("NAME");
        assertThat(robot.getSourceType()).isEqualTo(RobotSourceType.ROBOT_FRAMEWORK_PYTHON_WHEEL);
        assertThat(robot.getEnvironmentVariables()).containsExactlyInAnyOrderEntriesOf(Map.of("env1", "value1", "env2", "value2"));
    }
}
