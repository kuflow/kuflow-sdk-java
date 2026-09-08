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
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import com.azure.core.util.BinaryData;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.DocumentReference;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DocumentOperationTest extends AbstractOperationTest {

    @Test
    @DisplayName("GIVEN an authenticated user WHEN upload a document THEN the query parameters are sent and response is parsed")
    public void givenAnAuthenticatedUserWhenUploadADocumentThenTheQueryParametersAreSentAndResponseIsParsed() {
        String targetUri = "ku:process/" + UUID.fromString("80d8c9a1-e3d2-4c35-a0a9-77ec21d28950");

        givenThat(
            post(urlPathEqualTo("/v2024-06-14/documents/~actions/upload"))
                .withQueryParam("targetUri", equalTo(targetUri))
                .withQueryParam("fileContentType", equalTo("text/plain"))
                .withQueryParam("fileName", equalTo("test.txt"))
                .willReturn(ok().withHeader("Content-Type", "application/json").withBodyFile("documents-api.upload-document.ok.json"))
        );

        BinaryData fileContent = BinaryData.fromBytes("test content".getBytes(StandardCharsets.UTF_8));
        Document document = new Document().setFileContent(fileContent).setFileName("test.txt").setContentType("text/plain");

        DocumentReference documentReference = this.kuFlowRestClient.getDocumentOperations().uploadDocument(targetUri, document);

        assertThat(documentReference.getDocumentUri()).isEqualTo("kuflow-file:uri=aaa-bbb-ccc;type=text/plain;size=12;name=test.txt;");
    }

    @Test
    @DisplayName("GIVEN a null document WHEN upload a document THEN a NullPointerException is thrown")
    public void givenANullDocumentWhenUploadADocumentThenANullPointerExceptionIsThrown() {
        String targetUri = "ku:process/" + UUID.randomUUID();

        assertThatThrownBy(() -> this.kuFlowRestClient.getDocumentOperations().uploadDocument(targetUri, null))
            .isInstanceOf(NullPointerException.class)
            .hasMessageContaining("'document' is required");
    }

    @Test
    @DisplayName("GIVEN an empty file WHEN upload a document THEN an IllegalArgumentException is thrown")
    public void givenAnEmptyFileWhenUploadADocumentThenAnIllegalArgumentExceptionIsThrown() {
        String targetUri = "ku:business-artifact/" + UUID.randomUUID();

        BinaryData emptyContent = BinaryData.fromBytes(new byte[0]);
        Document document = new Document().setFileContent(emptyContent).setFileName("empty.txt").setContentType("text/plain");

        assertThatThrownBy(() -> this.kuFlowRestClient.getDocumentOperations().uploadDocument(targetUri, document))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessageContaining("File size must be greater that 0");
    }

    @Test
    @DisplayName("GIVEN a document reference WHEN download a document THEN the query parameters are sent and content is returned")
    public void givenADocumentReferenceWhenDownloadADocumentThenTheQueryParametersAreSentAndContentIsReturned() {
        String documentUri = "kuflow-file:uri=ku:process/aaa/document/bbb;type=text/plain;size=12;name=test.txt;";

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/documents/~actions/download"))
                .withQueryParam("documentUri", equalTo(documentUri))
                .willReturn(ok().withHeader("Content-Type", "application/octet-stream").withBody("test content"))
        );

        BinaryData result = this.kuFlowRestClient.getDocumentOperations().downloadDocument(documentUri);

        assertThat(result.toString()).isEqualTo("test content");
    }

    @Test
    @DisplayName("GIVEN a plain ku uri WHEN download a document THEN the query parameters are sent and content is returned")
    public void givenAPlainKuUriWhenDownloadADocumentThenTheQueryParametersAreSentAndContentIsReturned() {
        String documentUri = "ku:business-artifact/aaa/document/bbb";

        givenThat(
            get(urlPathEqualTo("/v2024-06-14/documents/~actions/download"))
                .withQueryParam("documentUri", equalTo(documentUri))
                .willReturn(ok().withHeader("Content-Type", "application/octet-stream").withBody("test content"))
        );

        BinaryData result = this.kuFlowRestClient.getDocumentOperations().downloadDocument(documentUri);

        assertThat(result.toString()).isEqualTo("test content");
    }
}
