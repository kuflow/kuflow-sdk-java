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

import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.delete;
import static com.github.tomakehurst.wiremock.client.WireMock.deleteRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.givenThat;
import static com.github.tomakehurst.wiremock.client.WireMock.matchingJsonPath;
import static com.github.tomakehurst.wiremock.client.WireMock.noContent;
import static com.github.tomakehurst.wiremock.client.WireMock.ok;
import static com.github.tomakehurst.wiremock.client.WireMock.patch;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.put;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.azure.core.util.BinaryData;
import com.kuflow.rest.model.BusinessArtifact;
import com.kuflow.rest.model.BusinessArtifactAction;
import com.kuflow.rest.model.BusinessArtifactActionCreateArtifactPrepare;
import com.kuflow.rest.model.BusinessArtifactActionCreateArtifactPrepareParams;
import com.kuflow.rest.model.BusinessArtifactActionCreateParams;
import com.kuflow.rest.model.BusinessArtifactActionStartProcessPrepare;
import com.kuflow.rest.model.BusinessArtifactActionStartProcessPrepareParams;
import com.kuflow.rest.model.BusinessArtifactActionStatus;
import com.kuflow.rest.model.BusinessArtifactActionType;
import com.kuflow.rest.model.BusinessArtifactCreateParams;
import com.kuflow.rest.model.BusinessArtifactDataUpdateParams;
import com.kuflow.rest.model.BusinessArtifactFindOptions;
import com.kuflow.rest.model.BusinessArtifactPage;
import com.kuflow.rest.model.DeletedQueryParam;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.DocumentReference;
import com.kuflow.rest.model.JsonPatchOperation;
import com.kuflow.rest.model.JsonPatchOperationType;
import com.kuflow.rest.model.JsonValue;
import com.kuflow.rest.util.SearchCriteriaUtils;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
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
    @DisplayName("GIVEN the deleted filter option WHEN list business artifacts THEN the query parameter is sent")
    public void givenTheDeletedQueryParamOptionWhenListBusinessArtifactsThenTheQueryParameterIsSent() {
        UUID businessArtifactDefinitionId = UUID.randomUUID();

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/business-artifacts"))
                .withQueryParam("businessArtifactDefinitionId", equalTo(businessArtifactDefinitionId.toString()))
                .withQueryParam("deleted", equalTo("INCLUDE"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.list.ok.json"))
        );

        BusinessArtifactFindOptions options = new BusinessArtifactFindOptions()
            .setBusinessArtifactDefinitionId(businessArtifactDefinitionId)
            .setDeleted(DeletedQueryParam.INCLUDE);

        this.kuFlowRestClient.getBusinessArtifactOperations().findBusinessArtifacts(options);
    }

    @Test
    @DisplayName("GIVEN a deleted business artifact WHEN restore THEN the restored artifact is returned")
    public void givenADeletedBusinessArtifactWhenRestoreThenTheRestoredArtifactIsReturned() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            post(urlPathEqualTo("/v2024-06-14/business-artifacts/" + businessArtifactId + "/~actions/restore")).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.retrieve.ok.json")
            )
        );

        BusinessArtifact businessArtifact = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .restoreBusinessArtifact(businessArtifactId);

        assertThat(businessArtifact.getId()).isEqualTo(businessArtifactId);
        assertThat(businessArtifact.getDeletedAt()).isNull();
    }

    @Test
    @DisplayName("GIVEN a value filter built from parts with a space WHEN list business artifacts THEN the encoded query parameter is sent")
    public void givenValueFilterBuiltFromPartsWithASpaceWhenListBusinessArtifactsThenTheEncodedQueryParameterIsSent() {
        String expectedValueParam = SearchCriteriaUtils.encodeFilterExpression("invoiceNumber", "eq", "INV 001");

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/business-artifacts"))
                .withQueryParam("value", equalTo(expectedValueParam))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.list.ok.json"))
        );

        BusinessArtifactFindOptions options = new BusinessArtifactFindOptions().addValue("invoiceNumber", "eq", "INV 001");

        this.kuFlowRestClient.getBusinessArtifactOperations().findBusinessArtifacts(options);
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

        BusinessArtifact businessArtifact = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .retrieveBusinessArtifact(businessArtifactId);

        assertThat(businessArtifact.getId()).isEqualTo(businessArtifactId);
        assertThat(businessArtifact.getTenantId()).isEqualTo(UUID.fromString("00a9f1d4-3698-45a4-951c-66a468846aad"));
        assertThat(businessArtifact.getBusinessArtifactDefinitionRef()).isNotNull();
        assertThat(businessArtifact.getBusinessArtifactDefinitionRef().getCode()).isEqualTo("ARTIFACT_DEF_CODE");
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN upload document THEN the query parameters are sent and response is parsed")
    public void givenAnAuthenticatedUserWhenUploadDocumentThenTheQueryParametersAreSentAndResponseIsParsed() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            post(urlPathEqualTo("/v2024-06-14/business-artifacts/" + businessArtifactId + "/~actions/upload-document"))
                .withQueryParam("fileContentType", equalTo("text/plain"))
                .withQueryParam("fileName", equalTo("test.txt"))
                .willReturn(
                    ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.upload-document.ok.json")
                )
        );

        BinaryData fileContent = BinaryData.fromBytes("test content".getBytes(StandardCharsets.UTF_8));
        Document document = new Document().setFileContent(fileContent).setFileName("test.txt").setContentType("text/plain");

        DocumentReference documentReference = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .uploadBusinessArtifactDocument(businessArtifactId, document);

        assertThat(documentReference.getDocumentUri()).isEqualTo("kuflow-file:uri=aaa-bbb-ccc;type=text/plain;size=12;name=test.txt;");
    }

    @Test
    @DisplayName("GIVEN a null document WHEN upload document THEN a NullPointerException is thrown")
    public void givenANullDocumentWhenUploadDocumentThenANullPointerExceptionIsThrown() {
        UUID businessArtifactId = UUID.randomUUID();

        assertThatThrownBy(() ->
            this.kuFlowRestClient.getBusinessArtifactOperations().uploadBusinessArtifactDocument(businessArtifactId, null)
        )
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("'document' is required");
    }

    @Test
    @DisplayName("GIVEN an empty file WHEN upload document THEN an IllegalArgumentException is thrown")
    public void givenAnEmptyFileWhenUploadDocumentThenAnIllegalArgumentExceptionIsThrown() {
        UUID businessArtifactId = UUID.randomUUID();

        BinaryData emptyContent = BinaryData.fromBytes(new byte[0]);
        Document document = new Document().setFileContent(emptyContent).setFileName("empty.txt").setContentType("text/plain");

        assertThatThrownBy(() ->
            this.kuFlowRestClient.getBusinessArtifactOperations().uploadBusinessArtifactDocument(businessArtifactId, document)
        )
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("File size must be greater that 0");
    }

    @Test
    @DisplayName("GIVEN an authenticated user WHEN download document THEN the query parameters are sent and response is parsed")
    public void givenAnAuthenticatedUserWhenDownloadDocumentThenTheQueryParametersAreSentAndResponseIsParsed() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");
        String documentUri = "kuflow-file:uri=aaa-bbb-ccc;type=text/plain;size=12;name=test.txt;";

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/business-artifacts/" + businessArtifactId + "/~actions/download-document"))
                .withQueryParam("documentUri", equalTo(documentUri))
                .willReturn(ok().withHeader("Content-Type", "application/octet-stream").withBody("test content"))
        );

        BinaryData result = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .downloadBusinessArtifactDocument(businessArtifactId, documentUri);

        assertThat(result.toString()).isEqualTo("test content");
    }

    @Test
    @DisplayName("GIVEN business artifact create params WHEN create business artifact THEN the params are sent and the body is parsed")
    public void givenBusinessArtifactCreateParamsWhenCreateBusinessArtifactThenTheParamsAreSentAndTheBodyIsParsed() {
        UUID tenantId = UUID.fromString("00a9f1d4-3698-45a4-951c-66a468846aad");

        givenThat(
            post("/v2024-06-14/business-artifacts")
                .withRequestBody(matchingJsonPath("$.businessArtifactDefinitionCode", equalTo("ARTIFACT_DEF_CODE")))
                .withRequestBody(matchingJsonPath("$.tenantId", equalTo(tenantId.toString())))
                .withRequestBody(matchingJsonPath("$.data.value.key", equalTo("value")))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.retrieve.ok.json"))
        );

        BusinessArtifactCreateParams businessArtifactCreateParams = new BusinessArtifactCreateParams()
            .setBusinessArtifactDefinitionCode("ARTIFACT_DEF_CODE")
            .setTenantId(tenantId)
            .setData(new JsonValue().setValue(Map.of("key", "value")));

        BusinessArtifact businessArtifact = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .createBusinessArtifact(businessArtifactCreateParams);

        assertThat(businessArtifact.getId()).isEqualTo(UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950"));
    }

    @Test
    @DisplayName("GIVEN a business artifact WHEN delete business artifact THEN the delete request is sent")
    public void givenABusinessArtifactWhenDeleteBusinessArtifactThenTheDeleteRequestIsSent() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(delete("/v2024-06-14/business-artifacts/" + businessArtifactId).willReturn(noContent()));

        this.kuFlowRestClient.getBusinessArtifactOperations().deleteBusinessArtifact(businessArtifactId);

        verify(deleteRequestedFor(urlPathEqualTo("/v2024-06-14/business-artifacts/" + businessArtifactId)));
    }

    @Test
    @DisplayName("GIVEN a data value WHEN update business artifact data THEN the data is sent")
    public void givenADataValueWhenUpdateBusinessArtifactDataThenTheDataIsSent() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            put("/v2024-06-14/business-artifacts/" + businessArtifactId + "/data")
                .withRequestBody(matchingJsonPath("$.data.value.key", equalTo("value")))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.retrieve.ok.json"))
        );

        BusinessArtifactDataUpdateParams businessArtifactDataUpdateParams = new BusinessArtifactDataUpdateParams().setData(
            new JsonValue().setValue(Map.of("key", "value"))
        );

        BusinessArtifact businessArtifact = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .updateBusinessArtifactData(businessArtifactId, businessArtifactDataUpdateParams);

        assertThat(businessArtifact.getId()).isEqualTo(businessArtifactId);
    }

    @Test
    @DisplayName("GIVEN a json patch WHEN patch business artifact data THEN the patch is sent as json patch")
    public void givenAJsonPatchWhenPatchBusinessArtifactDataThenThePatchIsSentAsJsonPatch() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            patch(urlPathEqualTo("/v2024-06-14/business-artifacts/" + businessArtifactId + "/data"))
                .withHeader("Content-Type", containing("application/json-patch+json"))
                .withRequestBody(matchingJsonPath("$[0].op", equalTo("replace")))
                .withRequestBody(matchingJsonPath("$[0].path", equalTo("/key")))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.retrieve.ok.json"))
        );

        List<JsonPatchOperation> jsonPatch = List.of(
            new JsonPatchOperation().setOp(JsonPatchOperationType.REPLACE).setPath("/key").setValue("value")
        );

        BusinessArtifact businessArtifact = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .patchBusinessArtifactData(businessArtifactId, jsonPatch);

        assertThat(businessArtifact.getId()).isEqualTo(businessArtifactId);
    }

    @Test
    @DisplayName("GIVEN action create params WHEN create business artifact action THEN the params are sent and the body is parsed")
    public void givenActionCreateParamsWhenCreateBusinessArtifactActionThenTheParamsAreSentAndTheBodyIsParsed() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            post("/v2024-06-14/business-artifacts/" + businessArtifactId + "/actions")
                .withRequestBody(matchingJsonPath("$.businessArtifactActionDefinitionCode", equalTo("ACTION_DOWNLOADABLE")))
                .willReturn(
                    ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.action-retrieve.ok.json")
                )
        );

        BusinessArtifactActionCreateParams businessArtifactActionCreateParams =
            new BusinessArtifactActionCreateParams().setBusinessArtifactActionDefinitionCode("ACTION_DOWNLOADABLE");

        BusinessArtifactAction businessArtifactAction = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .createBusinessArtifactAction(businessArtifactId, businessArtifactActionCreateParams);

        assertThat(businessArtifactAction.getId()).isEqualTo(UUID.fromString("c1a2b3c4-d5e6-4f70-8192-a3b4c5d6e7f8"));
        assertThat(businessArtifactAction.getType()).isEqualTo(BusinessArtifactActionType.DOWNLOADABLE);
        assertThat(businessArtifactAction.getStatus()).isEqualTo(BusinessArtifactActionStatus.COMPLETED);
    }

    @Test
    @DisplayName("GIVEN a business artifact action WHEN retrieve business artifact action THEN the body is parsed")
    public void givenABusinessArtifactActionWhenRetrieveBusinessArtifactActionThenTheBodyIsParsed() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");
        UUID actionId = UUID.fromString("c1a2b3c4-d5e6-4f70-8192-a3b4c5d6e7f8");

        givenThat(
            get("/v2024-06-14/business-artifacts/" + businessArtifactId + "/actions/" + actionId).willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.action-retrieve.ok.json")
            )
        );

        BusinessArtifactAction businessArtifactAction = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .retrieveBusinessArtifactAction(businessArtifactId, actionId);

        assertThat(businessArtifactAction.getId()).isEqualTo(actionId);
        assertThat(businessArtifactAction.getBusinessArtifactActionDefinitionRef().getCode()).isEqualTo("ACTION_DOWNLOADABLE");
        assertThat(businessArtifactAction.getDownloadable().getDocumentUri()).startsWith(
            "kuflow-file:uri=ku:business-artifact-action-value/"
        );
    }

    @Test
    @DisplayName("GIVEN a business artifact action WHEN cancel business artifact action THEN the cancel action is sent")
    public void givenABusinessArtifactActionWhenCancelBusinessArtifactActionThenTheCancelActionIsSent() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");
        UUID actionId = UUID.fromString("c1a2b3c4-d5e6-4f70-8192-a3b4c5d6e7f8");

        givenThat(
            post("/v2024-06-14/business-artifacts/" + businessArtifactId + "/actions/" + actionId + "/~actions/cancel").willReturn(
                ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.action-retrieve.ok.json")
            )
        );

        BusinessArtifactAction businessArtifactAction = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .cancelBusinessArtifactAction(businessArtifactId, actionId);

        assertThat(businessArtifactAction.getId()).isEqualTo(actionId);
    }

    @Test
    @DisplayName("GIVEN an action definition code WHEN prepare business artifact action create artifact THEN the prepared value is parsed")
    public void givenAnActionDefinitionCodeWhenPrepareBusinessArtifactActionCreateArtifactThenThePreparedValueIsParsed() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            post("/v2024-06-14/business-artifacts/" + businessArtifactId + "/~actions/prepare-create-artifact")
                .withRequestBody(matchingJsonPath("$.businessArtifactActionDefinitionCode", equalTo("ACTION_CREATE_ARTIFACT")))
                .willReturn(
                    ok()
                        .withHeader("Content-Type", "application/json")
                        .withBodyFile("business-artifacts-api.prepare-create-artifact.ok.json")
                )
        );

        BusinessArtifactActionCreateArtifactPrepareParams businessArtifactActionCreateArtifactPrepareParams =
            new BusinessArtifactActionCreateArtifactPrepareParams().setBusinessArtifactActionDefinitionCode("ACTION_CREATE_ARTIFACT");

        BusinessArtifactActionCreateArtifactPrepare prepare = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .prepareBusinessArtifactActionCreateArtifact(businessArtifactId, businessArtifactActionCreateArtifactPrepareParams);

        assertThat(prepare.getBusinessArtifactDefinitionRef().getCode()).isEqualTo("INVOICE");
        assertThat(prepare.getValue()).containsEntry("invoiceNumber", "INV-001");
    }

    @Test
    @DisplayName("GIVEN an action definition code WHEN prepare business artifact action start process THEN the prepared value is parsed")
    public void givenAnActionDefinitionCodeWhenPrepareBusinessArtifactActionStartProcessThenThePreparedValueIsParsed() {
        UUID businessArtifactId = UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            post("/v2024-06-14/business-artifacts/" + businessArtifactId + "/~actions/prepare-start-process")
                .withRequestBody(matchingJsonPath("$.businessArtifactActionDefinitionCode", equalTo("ACTION_START_PROCESS")))
                .willReturn(
                    ok().withHeader("Content-Type", "application/json").withBodyFile("business-artifacts-api.prepare-start-process.ok.json")
                )
        );

        BusinessArtifactActionStartProcessPrepareParams businessArtifactActionStartProcessPrepareParams =
            new BusinessArtifactActionStartProcessPrepareParams().setBusinessArtifactActionDefinitionCode("ACTION_START_PROCESS");

        BusinessArtifactActionStartProcessPrepare prepare = this.kuFlowRestClient
            .getBusinessArtifactOperations()
            .prepareBusinessArtifactActionStartProcess(businessArtifactId, businessArtifactActionStartProcessPrepareParams);

        assertThat(prepare.getProcessDefinitionId()).isEqualTo(UUID.fromString("7b8c9d0e-1f2a-4b3c-9d4e-5f6a7b8c9d0e"));
        assertThat(prepare.getMetadata()).containsEntry("customer", "ACME");
    }
}
