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

import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.kuflow.rest.implementation.DocumentOperationsImpl;
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Document;
import com.kuflow.rest.model.DocumentReference;
import com.kuflow.rest.util.Validation;

/** An instance of this class provides access to all the operations defined in DocumentOperations. */
public class DocumentOperations {

    /** The service. */
    private final DocumentOperationsImpl service;

    /**
     * Initializes an instance of DocumentOperations.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public DocumentOperations(KuFlowClientImpl client) {
        this.service = client.getDocumentOperations();
    }

    /**
     * Upload a temporal document
     * <p>
     * Upload a temporal document that later on must be linked with a domain resource of its target. The target
     * resource is identified by {@code targetUri}, e.g. {@code ku:process/{processId}} or
     * {@code ku:business-artifact/{businessArtifactId}}; resources expose their uri in their {@code uri} field.
     * <p>
     * Documents not linked to a domain resource are deleted after a retention period.
     *
     * @param targetUri URI of the resource the document is uploaded for.
     * @param document Document to upload.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DocumentReference> uploadDocumentWithResponse(String targetUri, Document document, Context context) {
        Validation.checkDocument(document);

        String fileContentType = document.getContentType();
        String fileName = document.getFileName();
        BinaryData file = document.getFileContent();
        long contentLength = file.getLength();

        return this.service.uploadDocumentWithResponse(targetUri, fileContentType, fileName, file, contentLength, context);
    }

    /**
     * Upload a temporal document
     * <p>
     * Upload a temporal document that later on must be linked with a domain resource of its target. The target
     * resource is identified by {@code targetUri}, e.g. {@code ku:process/{processId}} or
     * {@code ku:business-artifact/{businessArtifactId}}; resources expose their uri in their {@code uri} field.
     * <p>
     * Documents not linked to a domain resource are deleted after a retention period.
     *
     * @param targetUri URI of the resource the document is uploaded for.
     * @param document Document to upload.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DocumentReference uploadDocument(String targetUri, Document document) {
        return this.uploadDocumentWithResponse(targetUri, document, Context.NONE).getValue();
    }

    /**
     * Download a document
     * <p>
     * Given a document uri, download its content. Accepts both the {@code kuflow-file:} reference returned by the
     * upload operations and a plain {@code ku:} document uri. The credentials must be able to read the resource that
     * owns the document.
     *
     * @param documentUri Document URI to download, in {@code kuflow-file:} or {@code ku:} form.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadDocumentWithResponse(String documentUri, Context context) {
        return this.service.downloadDocumentWithResponse(documentUri, context);
    }

    /**
     * Download a document
     * <p>
     * Given a document uri, download its content. Accepts both the {@code kuflow-file:} reference returned by the
     * upload operations and a plain {@code ku:} document uri. The credentials must be able to read the resource that
     * owns the document.
     *
     * @param documentUri Document URI to download, in {@code kuflow-file:} or {@code ku:} form.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadDocument(String documentUri) {
        return this.downloadDocumentWithResponse(documentUri, Context.NONE).getValue();
    }
}
