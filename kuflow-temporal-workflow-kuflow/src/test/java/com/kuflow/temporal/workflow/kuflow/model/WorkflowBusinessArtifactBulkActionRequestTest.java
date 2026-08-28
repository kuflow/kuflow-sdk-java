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

package com.kuflow.temporal.workflow.kuflow.model;

import static org.assertj.core.api.Assertions.assertThat;

import com.azure.json.JsonProviders;
import com.azure.json.JsonReader;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class WorkflowBusinessArtifactBulkActionRequestTest {

    @Test
    @DisplayName("GIVEN a bulk action request WHEN serialized and deserialized THEN the items survive but extras do not")
    public void givenABulkActionRequestWhenRoundTripThenTheItemsSurviveButExtrasDoNot() throws IOException {
        List<WorkflowBusinessArtifactBulkActionRequestItem> items = List.of(
            bulkActionItem(UUID.randomUUID(), UUID.randomUUID()),
            bulkActionItem(UUID.randomUUID(), UUID.randomUUID())
        );
        UUID businessArtifactBatchExecutionId = UUID.randomUUID();
        UUID requestorPrincipalId = UUID.randomUUID();

        WorkflowBusinessArtifactBulkActionRequest request = new WorkflowBusinessArtifactBulkActionRequest();
        request.setItems(items);
        request.setBusinessArtifactActionDefinitionType(WorkflowBusinessArtifactActionDefinitionType.START_WORKFLOW);
        request.setBusinessArtifactActionDefinitionCode("bulk_start_invoice_workflow");
        request.setBusinessArtifactBatchExecutionId(businessArtifactBatchExecutionId);
        request.setRequestorPrincipalId(requestorPrincipalId);
        request.setRequestTime(OffsetDateTime.parse("2026-08-28T10:15:30+01:00"));
        request.setRequestTimeZone(ZoneId.of("Europe/Madrid"));
        // The extras bag is worker-local context, never part of the wire payload.
        request.putExtraItem("localOnly", "value");

        WorkflowBusinessArtifactBulkActionRequest read = roundTrip(request);

        assertThat(read.getItems())
            .extracting(
                WorkflowBusinessArtifactBulkActionRequestItem::getBusinessArtifactId,
                WorkflowBusinessArtifactBulkActionRequestItem::getBusinessArtifactActionValueId
            )
            .containsExactlyElementsOf(
                items
                    .stream()
                    .map(item -> org.assertj.core.groups.Tuple.tuple(item.getBusinessArtifactId(), item.getBusinessArtifactActionValueId()))
                    .toList()
            );
        assertThat(read.getBusinessArtifactActionDefinitionType()).isEqualTo(WorkflowBusinessArtifactActionDefinitionType.START_WORKFLOW);
        assertThat(read.getBusinessArtifactActionDefinitionCode()).isEqualTo("bulk_start_invoice_workflow");
        assertThat(read.getBusinessArtifactBatchExecutionId()).isEqualTo(businessArtifactBatchExecutionId);
        assertThat(read.getRequestorPrincipalId()).isEqualTo(requestorPrincipalId);
        assertThat(read.getRequestTime()).isEqualTo(OffsetDateTime.parse("2026-08-28T10:15:30+01:00"));
        assertThat(read.getRequestTimeZone()).isEqualTo(ZoneId.of("Europe/Madrid"));
        assertThat(read.getExtras()).isEmpty();
    }

    @Test
    @DisplayName("GIVEN a request without items WHEN serialized and deserialized THEN the items come back empty")
    public void givenARequestWithoutItemsWhenRoundTripThenTheItemsComeBackEmpty() throws IOException {
        WorkflowBusinessArtifactBulkActionRequest request = new WorkflowBusinessArtifactBulkActionRequest();
        request.setBusinessArtifactActionDefinitionCode("bulk_start_invoice_workflow");

        WorkflowBusinessArtifactBulkActionRequest read = roundTrip(request);

        assertThat(read.getItems()).isEmpty();
    }

    @Test
    @DisplayName("GIVEN a payload with unknown fields WHEN deserialized THEN they are skipped, keeping old workers forward-compatible")
    public void givenAPayloadWithUnknownFieldsWhenDeserializedThenTheyAreSkipped() throws IOException {
        String json = """
        {"businessArtifactActionDefinitionCode":"bulk_start_invoice_workflow","futureField":{"nested":[1,2,3]}}""";

        try (JsonReader jsonReader = JsonProviders.createReader(json)) {
            WorkflowBusinessArtifactBulkActionRequest read = WorkflowBusinessArtifactBulkActionRequest.fromJson(jsonReader);

            assertThat(read.getBusinessArtifactActionDefinitionCode()).isEqualTo("bulk_start_invoice_workflow");
        }
    }

    @Test
    @DisplayName("GIVEN a bulk action response WHEN serialized and deserialized THEN the message survives")
    public void givenABulkActionResponseWhenRoundTripThenTheMessageSurvives() throws IOException {
        WorkflowBusinessArtifactBulkActionResponse response = new WorkflowBusinessArtifactBulkActionResponse();
        response.setMessage("done");

        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = JsonProviders.createWriter(stringWriter)) {
            response.toJson(jsonWriter);
        }
        try (JsonReader jsonReader = JsonProviders.createReader(stringWriter.toString())) {
            WorkflowBusinessArtifactBulkActionResponse read = WorkflowBusinessArtifactBulkActionResponse.fromJson(jsonReader);

            assertThat(read.getMessage()).isEqualTo("done");
        }
    }

    private static WorkflowBusinessArtifactBulkActionRequestItem bulkActionItem(
        UUID businessArtifactId,
        UUID businessArtifactActionValueId
    ) {
        WorkflowBusinessArtifactBulkActionRequestItem item = new WorkflowBusinessArtifactBulkActionRequestItem();
        item.setBusinessArtifactId(businessArtifactId);
        item.setBusinessArtifactActionValueId(businessArtifactActionValueId);

        return item;
    }

    private static WorkflowBusinessArtifactBulkActionRequest roundTrip(WorkflowBusinessArtifactBulkActionRequest request)
        throws IOException {
        StringWriter stringWriter = new StringWriter();
        try (JsonWriter jsonWriter = JsonProviders.createWriter(stringWriter)) {
            request.toJson(jsonWriter);
        }

        try (JsonReader jsonReader = JsonProviders.createReader(stringWriter.toString())) {
            return WorkflowBusinessArtifactBulkActionRequest.fromJson(jsonReader);
        }
    }
}
