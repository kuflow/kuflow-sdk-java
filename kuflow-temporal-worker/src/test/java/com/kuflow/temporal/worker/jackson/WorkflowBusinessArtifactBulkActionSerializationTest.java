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

package com.kuflow.temporal.worker.jackson;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactActionDefinitionType;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactBulkActionRequest;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactBulkActionRequestItem;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactBulkActionResponse;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Guards the wire contract of the BULK business artifact action payloads. The models are plain beans, so they travel
 * through the worker object mapper, which is what {@code JacksonJsonPayloadConverter} uses: the engine writes the
 * payload with its own mapper and the worker reads exactly those property names.
 */
public class WorkflowBusinessArtifactBulkActionSerializationTest {

    private final ObjectMapper objectMapper = KuFlowObjectMapperFactory.createObjectMapper();

    @Test
    @DisplayName("GIVEN a bulk action request WHEN it is serialized and deserialized THEN the request is restored")
    public void givenABulkActionRequestWhenItIsSerializedAndDeserializedThenTheRequestIsRestored() throws IOException {
        UUID businessArtifactId = UUID.randomUUID();
        UUID businessArtifactActionValueId = UUID.randomUUID();
        UUID businessArtifactBatchExecutionId = UUID.randomUUID();
        UUID requestorPrincipalId = UUID.randomUUID();

        WorkflowBusinessArtifactBulkActionRequest request = new WorkflowBusinessArtifactBulkActionRequest();
        request.setItems(List.of(bulkActionItem(businessArtifactId, businessArtifactActionValueId)));
        request.setBusinessArtifactActionDefinitionType(WorkflowBusinessArtifactActionDefinitionType.START_WORKFLOW);
        request.setBusinessArtifactActionDefinitionCode("bulk_start_invoice_workflow");
        request.setBusinessArtifactBatchExecutionId(businessArtifactBatchExecutionId);
        request.setRequestorPrincipalId(requestorPrincipalId);
        request.setRequestTime(OffsetDateTime.parse("2026-08-28T10:15:30+01:00"));
        request.setRequestTimeZone(ZoneId.of("Europe/Madrid"));
        request.putExtraItem("aName", "aValue");

        WorkflowBusinessArtifactBulkActionRequest result = this.roundTrip(request);

        assertThat(result.getItems())
            .extracting(
                WorkflowBusinessArtifactBulkActionRequestItem::getBusinessArtifactId,
                WorkflowBusinessArtifactBulkActionRequestItem::getBusinessArtifactActionValueId
            )
            .containsExactly(tuple(businessArtifactId, businessArtifactActionValueId));
        assertThat(result.getBusinessArtifactActionDefinitionType()).isEqualTo(WorkflowBusinessArtifactActionDefinitionType.START_WORKFLOW);
        assertThat(result.getBusinessArtifactActionDefinitionCode()).isEqualTo("bulk_start_invoice_workflow");
        assertThat(result.getBusinessArtifactBatchExecutionId()).isEqualTo(businessArtifactBatchExecutionId);
        assertThat(result.getRequestorPrincipalId()).isEqualTo(requestorPrincipalId);
        assertThat(result.getRequestTime()).isEqualTo(OffsetDateTime.parse("2026-08-28T10:15:30+01:00"));
        assertThat(result.getRequestTimeZone()).isEqualTo(ZoneId.of("Europe/Madrid"));
        assertThat(result.getExtraItem("aName")).isEqualTo("aValue");
    }

    @Test
    @DisplayName("GIVEN a request without items WHEN it is serialized and deserialized THEN the items come back empty")
    public void givenARequestWithoutItemsWhenItIsSerializedAndDeserializedThenTheItemsComeBackEmpty() throws IOException {
        WorkflowBusinessArtifactBulkActionRequest request = new WorkflowBusinessArtifactBulkActionRequest();
        request.setBusinessArtifactActionDefinitionCode("bulk_start_invoice_workflow");

        WorkflowBusinessArtifactBulkActionRequest result = this.roundTrip(request);

        assertThat(result.getItems()).isEmpty();
        assertThat(result.getExtras()).isEmpty();
    }

    @Test
    @DisplayName("GIVEN a payload written by the engine WHEN it is deserialized THEN every property is bound")
    public void givenAPayloadWrittenByTheEngineWhenItIsDeserializedThenEveryPropertyIsBound() throws IOException {
        // The property names the engine writes, plus a property a newer engine could add: workers built against an
        // older SDK must keep reading the payload
        String payload = """
        {"items":[{"businessArtifactId":"a3f1d8e0-0000-4000-8000-000000000001",\
        "businessArtifactActionValueId":"a3f1d8e0-0000-4000-8000-000000000002"}],\
        "businessArtifactActionDefinitionType":"START_WORKFLOW",\
        "businessArtifactActionDefinitionCode":"bulk_start_invoice_workflow",\
        "businessArtifactBatchExecutionId":"a3f1d8e0-0000-4000-8000-000000000003",\
        "requestorPrincipalId":"a3f1d8e0-0000-4000-8000-000000000004",\
        "requestTime":"2026-08-28T10:15:30+01:00","requestTimeZone":"Europe/Madrid",\
        "aFutureProperty":{"nested":[1,2,3]}}""";

        WorkflowBusinessArtifactBulkActionRequest result = this.objectMapper.readValue(
            payload.getBytes(UTF_8),
            WorkflowBusinessArtifactBulkActionRequest.class
        );

        assertThat(result.getItems())
            .extracting(
                WorkflowBusinessArtifactBulkActionRequestItem::getBusinessArtifactId,
                WorkflowBusinessArtifactBulkActionRequestItem::getBusinessArtifactActionValueId
            )
            .containsExactly(
                tuple(UUID.fromString("a3f1d8e0-0000-4000-8000-000000000001"), UUID.fromString("a3f1d8e0-0000-4000-8000-000000000002"))
            );
        assertThat(result.getBusinessArtifactActionDefinitionType()).isEqualTo(WorkflowBusinessArtifactActionDefinitionType.START_WORKFLOW);
        assertThat(result.getBusinessArtifactActionDefinitionCode()).isEqualTo("bulk_start_invoice_workflow");
        assertThat(result.getBusinessArtifactBatchExecutionId()).isEqualTo(UUID.fromString("a3f1d8e0-0000-4000-8000-000000000003"));
        assertThat(result.getRequestorPrincipalId()).isEqualTo(UUID.fromString("a3f1d8e0-0000-4000-8000-000000000004"));
        assertThat(result.getRequestTime()).isEqualTo(OffsetDateTime.parse("2026-08-28T10:15:30+01:00"));
        assertThat(result.getRequestTimeZone()).isEqualTo(ZoneId.of("Europe/Madrid"));
    }

    @Test
    @DisplayName("GIVEN a bulk action response WHEN it is serialized and deserialized THEN the message survives")
    public void givenABulkActionResponseWhenItIsSerializedAndDeserializedThenTheMessageSurvives() throws IOException {
        WorkflowBusinessArtifactBulkActionResponse response = new WorkflowBusinessArtifactBulkActionResponse();
        response.setMessage("done");

        byte[] payload = this.objectMapper.writeValueAsBytes(response);
        WorkflowBusinessArtifactBulkActionResponse result = this.objectMapper.readValue(
            payload,
            WorkflowBusinessArtifactBulkActionResponse.class
        );

        assertThat(result.getMessage()).isEqualTo("done");
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

    private WorkflowBusinessArtifactBulkActionRequest roundTrip(WorkflowBusinessArtifactBulkActionRequest request) throws IOException {
        // Bytes, and not a string, because that is what JacksonJsonPayloadConverter does
        byte[] payload = this.objectMapper.writeValueAsBytes(request);

        return this.objectMapper.readValue(payload, WorkflowBusinessArtifactBulkActionRequest.class);
    }
}
