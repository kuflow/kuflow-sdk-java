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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.temporal.workflow.kuflow.model.SignalProcessItem;
import com.kuflow.temporal.workflow.kuflow.model.SignalProcessItemPayload;
import com.kuflow.temporal.workflow.kuflow.model.SignalProcessItemType;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactActionDefinitionType;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactActionRequest;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactActionResponse;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowBusinessArtifactActionResponseDownloadable;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowProcessUserActionDefinitionType;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowProcessUserActionRequest;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Pins the wire contract of the workflow payload models that used to implement azure-json's
 * {@code JsonSerializable} and are now plain beans. The property names and their string form must stay exactly the
 * ones the engine writes and reads, because the models travel through the worker object mapper, which is what
 * {@code JacksonJsonPayloadConverter} uses.
 */
public class WorkflowPayloadModelWireContractTest {

    private static final UUID AN_ID = UUID.fromString("a3f1d8e0-0000-4000-8000-000000000001");

    private static final UUID ANOTHER_ID = UUID.fromString("a3f1d8e0-0000-4000-8000-000000000002");

    private static final String A_REQUEST_TIME = "2026-08-28T10:15:30+01:00";

    private final ObjectMapper objectMapper = KuFlowObjectMapperFactory.createObjectMapper();

    @Test
    @DisplayName("GIVEN a business artifact action request WHEN it is serialized THEN the engine property names are written")
    public void givenABusinessArtifactActionRequestWhenItIsSerializedThenTheEnginePropertyNamesAreWritten() throws IOException {
        WorkflowBusinessArtifactActionRequest request = new WorkflowBusinessArtifactActionRequest();
        request.setBusinessArtifactId(AN_ID);
        request.setBusinessArtifactActionDefinitionType(WorkflowBusinessArtifactActionDefinitionType.DOWNLOADABLE);
        request.setBusinessArtifactActionDefinitionCode("generate_invoice");
        request.setBusinessArtifactActionValueId(ANOTHER_ID);
        request.setRequestorPrincipalId(AN_ID);
        request.setRequestTime(OffsetDateTime.parse(A_REQUEST_TIME));
        request.setRequestTimeZone(ZoneId.of("Europe/Madrid"));

        JsonNode json = this.objectMapper.readTree(this.objectMapper.writeValueAsBytes(request));

        assertThat(json.get("businessArtifactId").asText()).isEqualTo(AN_ID.toString());
        assertThat(json.get("businessArtifactActionDefinitionType").asText()).isEqualTo("DOWNLOADABLE");
        assertThat(json.get("businessArtifactActionDefinitionCode").asText()).isEqualTo("generate_invoice");
        assertThat(json.get("businessArtifactActionValueId").asText()).isEqualTo(ANOTHER_ID.toString());
        assertThat(json.get("requestorPrincipalId").asText()).isEqualTo(AN_ID.toString());
        assertThat(json.get("requestTime").asText()).isEqualTo(A_REQUEST_TIME);
        assertThat(json.get("requestTimeZone").asText()).isEqualTo("Europe/Madrid");
    }

    @Test
    @DisplayName("GIVEN a business artifact action request written by the engine WHEN it is deserialized THEN every property is bound")
    public void givenABusinessArtifactActionRequestWrittenByTheEngineWhenItIsDeserializedThenEveryPropertyIsBound() throws IOException {
        // A property a newer engine could add is ignored, so older workers keep reading the payload
        String payload = """
        {"businessArtifactId":"a3f1d8e0-0000-4000-8000-000000000001",\
        "businessArtifactActionDefinitionType":"DOWNLOADABLE",\
        "businessArtifactActionDefinitionCode":"generate_invoice",\
        "businessArtifactActionValueId":"a3f1d8e0-0000-4000-8000-000000000002",\
        "requestorPrincipalId":"a3f1d8e0-0000-4000-8000-000000000001",\
        "requestTime":"2026-08-28T10:15:30+01:00","requestTimeZone":"Europe/Madrid",\
        "extras":{"aName":"aValue"},"aFutureProperty":{"nested":[1,2,3]}}""";

        WorkflowBusinessArtifactActionRequest result = this.objectMapper.readValue(
            payload.getBytes(UTF_8),
            WorkflowBusinessArtifactActionRequest.class
        );

        assertThat(result.getBusinessArtifactId()).isEqualTo(AN_ID);
        assertThat(result.getBusinessArtifactActionDefinitionType()).isEqualTo(WorkflowBusinessArtifactActionDefinitionType.DOWNLOADABLE);
        assertThat(result.getBusinessArtifactActionDefinitionCode()).isEqualTo("generate_invoice");
        assertThat(result.getBusinessArtifactActionValueId()).isEqualTo(ANOTHER_ID);
        assertThat(result.getRequestorPrincipalId()).isEqualTo(AN_ID);
        assertThat(result.getRequestTime()).isEqualTo(OffsetDateTime.parse(A_REQUEST_TIME));
        assertThat(result.getRequestTimeZone()).isEqualTo(ZoneId.of("Europe/Madrid"));
        // As a plain bean the extras bag reaches the workflow, which is what its contract promises. The hand-written
        // toJson used to drop it
        assertThat(result.getExtraItem("aName")).isEqualTo("aValue");
    }

    @Test
    @DisplayName("GIVEN a business artifact action response WHEN it is serialized THEN the nested downloadable is written inline")
    public void givenABusinessArtifactActionResponseWhenItIsSerializedThenTheNestedDownloadableIsWrittenInline() throws IOException {
        WorkflowBusinessArtifactActionResponseDownloadable downloadable = new WorkflowBusinessArtifactActionResponseDownloadable();
        downloadable.setDocumentUri("kuflow-document://an-uri");

        WorkflowBusinessArtifactActionResponse response = new WorkflowBusinessArtifactActionResponse();
        response.setMessage("done");
        response.setDownloadable(downloadable);

        byte[] payload = this.objectMapper.writeValueAsBytes(response);
        JsonNode json = this.objectMapper.readTree(payload);

        assertThat(json.get("message").asText()).isEqualTo("done");
        assertThat(json.get("downloadable").get("documentUri").asText()).isEqualTo("kuflow-document://an-uri");

        WorkflowBusinessArtifactActionResponse result = this.objectMapper.readValue(payload, WorkflowBusinessArtifactActionResponse.class);

        assertThat(result.getMessage()).isEqualTo("done");
        assertThat(result.getDownloadable().getDocumentUri()).isEqualTo("kuflow-document://an-uri");
    }

    @Test
    @DisplayName("GIVEN a signal process item WHEN it is serialized and deserialized THEN the nested payload survives")
    public void givenASignalProcessItemWhenItIsSerializedAndDeserializedThenTheNestedPayloadSurvives() throws IOException {
        SignalProcessItemPayload payload = new SignalProcessItemPayload();
        payload.setProcessItemDefinitionCode("MY_TASK");
        payload.setDataStructureDataDefinitionCode("MY_DATA_STRUCTURE");

        SignalProcessItem signalProcessItem = new SignalProcessItem();
        signalProcessItem.setId(AN_ID);
        signalProcessItem.setType(SignalProcessItemType.TASK);
        signalProcessItem.setPayload(payload);
        signalProcessItem.setRequestTime(OffsetDateTime.parse(A_REQUEST_TIME));
        signalProcessItem.setRequestTimeZone(ZoneId.of("Europe/Madrid"));

        byte[] serialized = this.objectMapper.writeValueAsBytes(signalProcessItem);
        JsonNode json = this.objectMapper.readTree(serialized);

        assertThat(json.get("id").asText()).isEqualTo(AN_ID.toString());
        assertThat(json.get("type").asText()).isEqualTo("TASK");
        assertThat(json.get("payload").get("processItemDefinitionCode").asText()).isEqualTo("MY_TASK");
        assertThat(json.get("payload").get("dataStructureDataDefinitionCode").asText()).isEqualTo("MY_DATA_STRUCTURE");
        assertThat(json.get("requestTime").asText()).isEqualTo(A_REQUEST_TIME);
        assertThat(json.get("requestTimeZone").asText()).isEqualTo("Europe/Madrid");

        SignalProcessItem result = this.objectMapper.readValue(serialized, SignalProcessItem.class);

        assertThat(result.getId()).isEqualTo(AN_ID);
        assertThat(result.getType()).isEqualTo(SignalProcessItemType.TASK);
        assertThat(result.getPayload().getProcessItemDefinitionCode()).isEqualTo("MY_TASK");
        assertThat(result.getPayload().getDataStructureDataDefinitionCode()).isEqualTo("MY_DATA_STRUCTURE");
        assertThat(result.getRequestTime()).isEqualTo(OffsetDateTime.parse(A_REQUEST_TIME));
        assertThat(result.getRequestTimeZone()).isEqualTo(ZoneId.of("Europe/Madrid"));
    }

    @Test
    @DisplayName("GIVEN a process user action request WHEN it is serialized and deserialized THEN the request is restored")
    public void givenAProcessUserActionRequestWhenItIsSerializedAndDeserializedThenTheRequestIsRestored() throws IOException {
        WorkflowProcessUserActionRequest request = new WorkflowProcessUserActionRequest();
        request.setProcessId(AN_ID);
        request.setUserActionDefinitionType(WorkflowProcessUserActionDefinitionType.START_WORKFLOW);
        request.setUserActionDefinitionCode("MY_ACTION");
        request.setUserActionId(ANOTHER_ID);
        request.setRequestorPrincipalId(AN_ID);
        request.setRequestTime(OffsetDateTime.parse(A_REQUEST_TIME));
        request.setRequestTimeZone(ZoneId.of("Europe/Madrid"));
        request.putExtraItem("aName", "aValue");

        byte[] serialized = this.objectMapper.writeValueAsBytes(request);
        JsonNode json = this.objectMapper.readTree(serialized);

        assertThat(json.get("processId").asText()).isEqualTo(AN_ID.toString());
        assertThat(json.get("userActionDefinitionType").asText()).isEqualTo("START_WORKFLOW");
        assertThat(json.get("userActionDefinitionCode").asText()).isEqualTo("MY_ACTION");
        assertThat(json.get("userActionId").asText()).isEqualTo(ANOTHER_ID.toString());
        assertThat(json.get("requestorPrincipalId").asText()).isEqualTo(AN_ID.toString());
        assertThat(json.get("requestTime").asText()).isEqualTo(A_REQUEST_TIME);
        assertThat(json.get("requestTimeZone").asText()).isEqualTo("Europe/Madrid");

        WorkflowProcessUserActionRequest result = this.objectMapper.readValue(serialized, WorkflowProcessUserActionRequest.class);

        assertThat(result.getProcessId()).isEqualTo(AN_ID);
        assertThat(result.getUserActionDefinitionType()).isEqualTo(WorkflowProcessUserActionDefinitionType.START_WORKFLOW);
        assertThat(result.getUserActionDefinitionCode()).isEqualTo("MY_ACTION");
        assertThat(result.getUserActionId()).isEqualTo(ANOTHER_ID);
        assertThat(result.getRequestorPrincipalId()).isEqualTo(AN_ID);
        assertThat(result.getRequestTime()).isEqualTo(OffsetDateTime.parse(A_REQUEST_TIME));
        assertThat(result.getRequestTimeZone()).isEqualTo(ZoneId.of("Europe/Madrid"));
        assertThat(result.getExtraItem("aName")).isEqualTo("aValue");
    }
}
