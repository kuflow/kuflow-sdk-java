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
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.kuflow.rest.util.TaskSaveJsonFormsValueDataCommandUtils.updateJsonFormsProperty;
import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.model.JsonFormsFile;
import com.kuflow.rest.model.JsonFormsPrincipal;
import com.kuflow.rest.model.Task;
import com.kuflow.rest.model.TaskPageItem;
import com.kuflow.rest.model.TaskSaveElementCommand;
import com.kuflow.rest.model.TaskSaveJsonFormsValueDataCommand;
import com.kuflow.rest.util.TaskPageItemUtils;
import com.kuflow.rest.util.TaskSaveElementCommandUtils;
import com.kuflow.rest.util.TaskUtils;
import java.time.Instant;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TaskOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN list tasks THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenListTasksThenAuthenticationHeaderIsAdded() {
        givenThat(
            get("/v2022-10-08/tasks")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tasks-api.list.ok.json"))
        );

        this.kuFlowRestClient.getTaskOperations().findTasks();
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN save an element THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenSaveAnElementThenAuthenticationHeaderIsAdded() {
        UUID taskId = UUID.fromString("e2d0fdf9-0aae-4eed-9e07-8e4b76df733c");

        givenThat(
            post("/v2022-10-08/tasks/" + taskId + "/~actions/save-element")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tasks-api.retrieve.ok.json"))
        );
        TaskSaveElementCommand command = new TaskSaveElementCommand().setElementDefinitionCode("CODE");
        TaskSaveElementCommandUtils.setElementValueAsString(command, "value");

        this.kuFlowRestClient.getTaskOperations().actionsTaskSaveElement(taskId, command);
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN save a json forms value THEN authentication header is added")
    public void givenAnAuthenticatedUserWhenSaveAJsonFormsValueThenAuthenticationHeaderIsAdded() {
        UUID taskId = UUID.fromString("e2d0fdf9-0aae-4eed-9e07-8e4b76df733c");

        givenThat(
            post("/v2022-10-08/tasks/" + taskId + "/~actions/save-json-forms-value-data")
                .withHeader("Authorization", equalTo("Bearer Q0xJRU5UX0lEOkNMSUVOVF9TRUNSRVQ="))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tasks-api.retrieve.ok.json"))
        );
        TaskSaveJsonFormsValueDataCommand command = new TaskSaveJsonFormsValueDataCommand().setData(Map.of("key", "value"));

        this.kuFlowRestClient.getTaskOperations().actionsTaskSaveJsonFormsValueData(taskId, command);
    }

    @Test
    @DisplayName("GIVEN task requested WHEN get json forms data THEN expected obtain the correct values")
    public void givenTaskRequestedWhenGetJsonFormsDataThenExpectedObtainTheCorrectValues() {
        UUID taskId = UUID.fromString("e2d0fdf9-0aae-4eed-9e07-8e4b76df733c");

        givenThat(
            get("/v2022-10-08/tasks/" + taskId)
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("tasks-api-json-forms.retrieve.ok.json"))
        );

        Task task = this.kuFlowRestClient.getTaskOperations().retrieveTask(taskId);

        assertThat(TaskUtils.findJsonFormsProperty(task, "key2")).contains(List.of("value2_0", "value2_1", 505));
        assertThat(TaskUtils.findJsonFormsPropertyAsString(task, "key1")).contains("value1");
        assertThat(TaskUtils.findJsonFormsProperty(task, "key2")).contains(List.of("value2_0", "value2_1", 505));
        assertThat(TaskUtils.findJsonFormsPropertyAsString(task, "key2.0")).contains("value2_0");
        assertThat(TaskUtils.findJsonFormsPropertyAsString(task, "key2.1")).contains("value2_1");
        assertThat(TaskUtils.findJsonFormsPropertyAsInteger(task, "key2.2")).contains(505);
        assertThat(TaskUtils.findJsonFormsPropertyAsDouble(task, "key2.2")).contains(505.0);
        assertThat(TaskUtils.findJsonFormsPropertyAsString(task, "key3.0.key3_0")).contains("value3_0");
        assertThat(TaskUtils.findJsonFormsPropertyAsJsonFormsFile(task, "key6"))
            .hasValueSatisfying(value -> assertThat(value.getUri()).isEqualTo("ku:dummy/xxx-ssss-yyyy"));
        assertThat(TaskUtils.findJsonFormsPropertyAsJsonFormsPrincipal(task, "key7"))
            .hasValueSatisfying(value -> assertThat(value.getId()).isEqualTo(UUID.fromString("0e30a29f-469e-4c03-a3c5-f3286a7ac5c2")));
        assertThat(TaskUtils.findJsonFormsPropertyAsBoolean(task, "key8.0")).contains(true);
        assertThat(TaskUtils.findJsonFormsPropertyAsBoolean(task, "key8.1")).contains(false);
    }

    @Test
    @DisplayName("GIVEN a task WHEN update values THEN values are updated correctly")
    public void givenATaskWhenUpdateValuesThenValuesAreUpdatedCorrectly() {
        Task task = new Task();

        JsonFormsFile file = JsonFormsFile
            .from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;")
            .orElseThrow();
        JsonFormsPrincipal principalUser = JsonFormsPrincipal
            .from("kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;")
            .orElseThrow();

        TaskUtils.updateJsonFormsProperty(task, "key_1.0.key_2.0.key_1", "value");
        TaskUtils.updateJsonFormsProperty(task, "key_1.0.key_2.0.key_2", true);
        TaskUtils.updateJsonFormsProperty(task, "key_1.0.key_2.0.key_3", 100);
        TaskUtils.updateJsonFormsProperty(task, "key_1.0.key_2.0.key_4", file);
        TaskUtils.updateJsonFormsProperty(task, "key_1.0.key_2.0.key_5", principalUser);

        assertThat(task.getJsonFormsValue().getData())
            .isEqualTo(
                Map.of(
                    "key_1",
                    List.of(
                        Map.of(
                            "key_2",
                            List.of(
                                Map.of(
                                    "key_1",
                                    "value",
                                    "key_2",
                                    true,
                                    "key_3",
                                    100,
                                    "key_4",
                                    "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;",
                                    "key_5",
                                    "kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;"
                                )
                            )
                        )
                    )
                )
            );
    }

    @Test
    @DisplayName("GIVEN a taskPageItem WHEN update values THEN values are updated correctly")
    public void givenATaskPageItemWhenUpdateValuesThenValuesAreUpdatedCorrectly() {
        TaskPageItem taskPageItem = new TaskPageItem();

        JsonFormsFile file = JsonFormsFile
            .from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;")
            .orElseThrow();
        JsonFormsPrincipal principalUser = JsonFormsPrincipal
            .from("kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;")
            .orElseThrow();

        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_1.0.key_2.0.key_1", "value");
        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_1.0.key_2.0.key_2", true);
        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_1.0.key_2.0.key_3", 100);
        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_1.1.key_4", file);
        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_1.1.key_5", principalUser);
        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_2.0", LocalDate.parse("3000-01-01"));
        TaskPageItemUtils.updateJsonFormsProperty(taskPageItem, "key_2.1", Instant.parse("3003-01-01T00:00:00Z"));

        assertThat(taskPageItem.getJsonFormsValue().getData())
            .isEqualTo(
                Map.of(
                    "key_1",
                    List.of(
                        Map.of("key_2", List.of(Map.of("key_1", "value", "key_2", true, "key_3", 100))),
                        Map.of(
                            "key_4",
                            "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;",
                            "key_5",
                            "kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;"
                        )
                    ),
                    "key_2",
                    List.of("3000-01-01", "3003-01-01T00:00:00Z")
                )
            );
    }

    @Test
    @DisplayName("GIVEN a taskSaveJsonFormsValueCommand WHEN update values THEN values are updated correctly")
    public void givenATaskSaveJsonFormsValueCommandWhenUpdateValuesThenValuesAreUpdatedCorrectly() {
        TaskSaveJsonFormsValueDataCommand command = new TaskSaveJsonFormsValueDataCommand();

        JsonFormsFile file = JsonFormsFile
            .from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;")
            .orElseThrow();
        JsonFormsPrincipal principalUser = JsonFormsPrincipal
            .from("kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;")
            .orElseThrow();

        updateJsonFormsProperty(command, "key_1.0.key_2.0.key_1", "value");
        updateJsonFormsProperty(command, "key_1.0.key_2.0.key_2", true);
        updateJsonFormsProperty(command, "key_1.0.key_2.0.key_3", 100);
        updateJsonFormsProperty(command, "key_1.1.key_4", file);
        updateJsonFormsProperty(command, "key_1.1.key_5", principalUser);
        updateJsonFormsProperty(command, "key_2.0", LocalDate.parse("3000-01-01"));
        updateJsonFormsProperty(command, "key_2.1", OffsetDateTime.parse("3001-01-01T01:00:00+05:05"));
        updateJsonFormsProperty(command, "key_2.2", Instant.parse("3002-01-01T02:00:00Z"));

        assertThat(command.getData())
            .isEqualTo(
                Map.of(
                    "key_1",
                    List.of(
                        Map.of("key_2", List.of(Map.of("key_1", "value", "key_2", true, "key_3", 100))),
                        Map.of(
                            "key_4",
                            "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;",
                            "key_5",
                            "kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;"
                        )
                    ),
                    "key_2",
                    List.of("3000-01-01", "3001-01-01T01:00:00+05:05", "3002-01-01T02:00:00Z")
                )
            );

        updateJsonFormsProperty(command, "key_1.0", null);

        assertThat(command.getData())
            .isEqualTo(
                Map.of(
                    "key_1",
                    List.of(
                        Map.of(
                            "key_4",
                            "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;",
                            "key_5",
                            "kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;"
                        )
                    ),
                    "key_2",
                    List.of("3000-01-01", "3001-01-01T01:00:00+05:05", "3002-01-01T02:00:00Z")
                )
            );

        updateJsonFormsProperty(command, "key_2.1", null);

        assertThat(command.getData())
            .isEqualTo(
                Map.of(
                    "key_1",
                    List.of(
                        Map.of(
                            "key_4",
                            "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;",
                            "key_5",
                            "kuflow-principal:id=0e30a29f-469e-4c03-a3c5-f3286a7ac5c2;type=USER;name=Homer Simpsons;"
                        )
                    ),
                    "key_2",
                    List.of("3000-01-01", "3002-01-01T02:00:00Z")
                )
            );
    }

    @Test
    @DisplayName("GIVEN a task WHEN get date values THEN date are transformed correctly")
    public void givenATaskWhenGetDateValuesThenDateAreTransformedCorrectly() {
        Task task = new Task();

        TaskUtils.updateJsonFormsProperty(task, "key_1", LocalDate.parse("3000-01-01"));
        TaskUtils.updateJsonFormsProperty(task, "key_2", OffsetDateTime.parse("3001-01-01T01:00:00+05:05"));
        TaskUtils.updateJsonFormsProperty(task, "key_3", Instant.parse("3002-01-01T00:01:00Z"));

        LocalDate value1 = TaskUtils.getJsonFormsPropertyAsLocalDate(task, "key_1");
        assertThat(value1).isEqualTo(LocalDate.parse("3000-01-01"));

        OffsetDateTime value2 = TaskUtils.getJsonFormsPropertyAsOffsetDateTime(task, "key_2");
        assertThat(value2).isEqualTo(OffsetDateTime.parse("3001-01-01T01:00:00+05:05"));

        Instant value3 = TaskUtils.getJsonFormsPropertyAsInstant(task, "key_3");
        assertThat(value3).isEqualTo(Instant.parse("3002-01-01T00:01:00Z"));
    }
}
