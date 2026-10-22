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
import static org.assertj.core.api.Assertions.assertThatNoException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.rest.model.DeletedQueryParam;
import com.kuflow.rest.model.ProcessState;
import com.kuflow.rest.util.SearchCriteriaUtils;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactFindRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessFindRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemFindRequest;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessFindRequestSerializationTest {

    private final ObjectMapper objectMapper = KuFlowObjectMapperFactory.createObjectMapper();

    @Test
    @DisplayName("GIVEN an empty request WHEN it is serialized and deserialized THEN the request is restored")
    public void givenAnEmptyRequestWhenItIsSerializedAndDeserializedThenTheRequestIsRestored() throws IOException {
        ProcessFindRequest request = new ProcessFindRequest()
            .setProcessDefinitionCode("MyProcessDefinition")
            .setState(ProcessState.RUNNING);

        ProcessFindRequest result = this.roundTrip(request);

        assertThat(result.getSorts()).isEmpty();
        assertThat(result.getMetadata()).isEmpty();
        assertThat(result.getProcessDefinitionCodes()).containsExactly("MyProcessDefinition");
        assertThat(result.getStates()).containsExactly(ProcessState.RUNNING);
    }

    @Test
    @DisplayName("GIVEN a fully populated request WHEN it is serialized and deserialized THEN the request is restored")
    public void givenAFullyPopulatedRequestWhenItIsSerializedAndDeserializedThenTheRequestIsRestored() throws IOException {
        UUID tenantId = UUID.randomUUID();
        UUID processDefinitionId = UUID.randomUUID();
        UUID initiatorId = UUID.randomUUID();

        ProcessFindRequest request = new ProcessFindRequest()
            .setPage(2)
            .setSize(25)
            .addSort("createdAt,asc")
            .addSort("lastModifiedAt,desc")
            .addTenantId(tenantId)
            .addProcessDefinitionId(processDefinitionId)
            .addProcessDefinitionCode("MyProcessDefinition")
            .addState(ProcessState.RUNNING)
            .addState(ProcessState.COMPLETED)
            .addInitiatorId(initiatorId)
            .addInitiatorEmail("someone@example.com")
            .addMetadata("FIELD_STRING eq Text")
            .addMetadata("FIELD_OTHER", "eq", "Text value with spaces");
        request.putPayloadItem("name", "value");

        ProcessFindRequest result = this.roundTrip(request);

        assertThat(result.getPage()).isEqualTo(2);
        assertThat(result.getSize()).isEqualTo(25);
        assertThat(result.getSorts()).containsExactly("createdAt,asc", "lastModifiedAt,desc");
        assertThat(result.getTenantIds()).containsExactly(tenantId);
        assertThat(result.getProcessDefinitionIds()).containsExactly(processDefinitionId);
        assertThat(result.getProcessDefinitionCodes()).containsExactly("MyProcessDefinition");
        assertThat(result.getStates()).containsExactly(ProcessState.RUNNING, ProcessState.COMPLETED);
        assertThat(result.getInitiatorIds()).containsExactly(initiatorId);
        assertThat(result.getInitiatorEmails()).containsExactly("someone@example.com");
        assertThat(result.getMetadata()).containsExactly(
            "FIELD_STRING eq Text",
            SearchCriteriaUtils.encodeFilterExpression("FIELD_OTHER", "eq", "Text value with spaces")
        );
        assertThat(result.getPayloadItem("name")).isEqualTo("value");
    }

    @Test
    @DisplayName("GIVEN a new request WHEN a sort is mutated THEN no exception is thrown")
    public void givenANewRequestWhenASortIsMutatedThenNoExceptionIsThrown() {
        assertThatNoException().isThrownBy(() -> new ProcessFindRequest().addSort("createdAt,asc"));
        assertThatNoException().isThrownBy(() -> new ProcessFindRequest().setSort("createdAt,asc"));
        assertThatNoException().isThrownBy(() -> new ProcessFindRequest().setSorts(List.of("createdAt,asc")));
        assertThatNoException().isThrownBy(() -> new ProcessFindRequest().setSorts(null));
        assertThatNoException().isThrownBy(() -> new ProcessFindRequest().removeSort("createdAt,asc"));
    }

    @Test
    @DisplayName("GIVEN find requests with the deleted filter WHEN they are serialized THEN the deleted field is written as a string")
    public void givenFindRequestsWithTheDeletedFilterWhenTheyAreSerializedThenTheDeletedFieldIsWrittenAsAString() throws IOException {
        JsonNode processJson = this.objectMapper.readTree(
            this.objectMapper.writeValueAsBytes(new ProcessFindRequest().setDeleted(DeletedQueryParam.INCLUDE))
        );
        JsonNode processItemJson = this.objectMapper.readTree(
            this.objectMapper.writeValueAsBytes(new ProcessItemFindRequest().setDeleted(DeletedQueryParam.ONLY))
        );
        JsonNode businessArtifactJson = this.objectMapper.readTree(
            this.objectMapper.writeValueAsBytes(new BusinessArtifactFindRequest().setDeleted(DeletedQueryParam.EXCLUDE))
        );

        assertThat(processJson.get("deleted").asText()).isEqualTo("INCLUDE");
        assertThat(processItemJson.get("deleted").asText()).isEqualTo("ONLY");
        assertThat(businessArtifactJson.get("deleted").asText()).isEqualTo("EXCLUDE");
    }

    @Test
    @DisplayName("GIVEN find request payloads with the deleted filter WHEN they are deserialized THEN the deleted filter is bound")
    public void givenFindRequestPayloadsWithTheDeletedFilterWhenTheyAreDeserializedThenTheDeletedFilterIsBound() throws IOException {
        byte[] payload = "{\"deleted\":\"ONLY\"}".getBytes(UTF_8);

        ProcessFindRequest processFindRequest = this.objectMapper.readValue(payload, ProcessFindRequest.class);
        ProcessItemFindRequest processItemFindRequest = this.objectMapper.readValue(payload, ProcessItemFindRequest.class);
        BusinessArtifactFindRequest businessArtifactFindRequest = this.objectMapper.readValue(payload, BusinessArtifactFindRequest.class);

        assertThat(processFindRequest.getDeleted()).isEqualTo(DeletedQueryParam.ONLY);
        assertThat(processItemFindRequest.getDeleted()).isEqualTo(DeletedQueryParam.ONLY);
        assertThat(businessArtifactFindRequest.getDeleted()).isEqualTo(DeletedQueryParam.ONLY);
    }

    @Test
    @DisplayName(
        "GIVEN a find request without the deleted filter WHEN it is serialized and deserialized THEN the deleted filter stays null"
    )
    public void givenAFindRequestWithoutTheDeletedFilterWhenItIsSerializedAndDeserializedThenTheDeletedFilterStaysNull()
        throws IOException {
        ProcessFindRequest result = this.roundTrip(new ProcessFindRequest());

        assertThat(result.getDeleted()).isNull();
    }

    private ProcessFindRequest roundTrip(ProcessFindRequest request) throws IOException {
        // Bytes, and not a string, because that is what JacksonJsonPayloadConverter does
        byte[] payload = this.objectMapper.writeValueAsBytes(request);

        return this.objectMapper.readValue(payload, ProcessFindRequest.class);
    }
}
