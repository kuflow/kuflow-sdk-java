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

package com.kuflow.rest.implementation;

import com.azure.core.annotation.BodyParam;
import com.azure.core.annotation.ExpectedResponses;
import com.azure.core.annotation.Get;
import com.azure.core.annotation.HeaderParam;
import com.azure.core.annotation.Host;
import com.azure.core.annotation.HostParam;
import com.azure.core.annotation.Post;
import com.azure.core.annotation.QueryParam;
import com.azure.core.annotation.ReturnType;
import com.azure.core.annotation.ServiceInterface;
import com.azure.core.annotation.ServiceMethod;
import com.azure.core.annotation.UnexpectedResponseExceptionType;
import com.azure.core.http.rest.Response;
import com.azure.core.http.rest.RestProxy;
import com.azure.core.util.BinaryData;
import com.azure.core.util.Context;
import com.azure.core.util.FluxUtil;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.DocumentReference;
import java.nio.ByteBuffer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in DocumentOperations.
 */
public final class DocumentOperationsImpl {

    /**
     * The proxy service used to perform REST calls.
     */
    private final DocumentOperationsService service;

    /**
     * The service client containing this operation class.
     */
    private final KuFlowClientImpl client;

    /**
     * Initializes an instance of DocumentOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    DocumentOperationsImpl(KuFlowClientImpl client) {
        this.service = RestProxy.create(DocumentOperationsService.class, client.getHttpPipeline(), client.getSerializerAdapter());
        this.client = client;
    }

    /**
     * The interface defining all the services for KuFlowClientDocumentOperations to be used by the proxy service to
     * perform REST calls.
     */
    @Host("{$host}")
    @ServiceInterface(name = "KuFlowClientDocumentOperations")
    public interface DocumentOperationsService {
        @Post("/documents/~actions/upload")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadDocument(
            @HostParam("$host") String host,
            @QueryParam("targetUri") String targetUri,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @BodyParam("application/octet-stream") Flux<ByteBuffer> file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/documents/~actions/upload")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<DocumentReference>> uploadDocument(
            @HostParam("$host") String host,
            @QueryParam("targetUri") String targetUri,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Post("/documents/~actions/upload")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<DocumentReference> uploadDocumentSync(
            @HostParam("$host") String host,
            @QueryParam("targetUri") String targetUri,
            @QueryParam("fileContentType") String fileContentType,
            @QueryParam("fileName") String fileName,
            @BodyParam("application/octet-stream") BinaryData file,
            @HeaderParam("Content-Length") long contentLength,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/documents/~actions/download")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Mono<Response<BinaryData>> downloadDocument(
            @HostParam("$host") String host,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );

        @Get("/documents/~actions/download")
        @ExpectedResponses({ 200 })
        @UnexpectedResponseExceptionType(DefaultErrorException.class)
        Response<BinaryData> downloadDocumentSync(
            @HostParam("$host") String host,
            @QueryParam("documentUri") String documentUri,
            @HeaderParam("Accept") String accept,
            Context context
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadDocumentWithResponseAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadDocumentWithResponseAsync(targetUri, fileContentType, fileName, file, contentLength, context)
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadDocumentWithResponseAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadDocument(this.client.getHost(), targetUri, fileContentType, fileName, file, contentLength, accept, context);
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadDocumentAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength
    ) {
        return uploadDocumentWithResponseAsync(targetUri, fileContentType, fileName, file, contentLength).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadDocumentAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        Flux<ByteBuffer> file,
        long contentLength,
        Context context
    ) {
        return uploadDocumentWithResponseAsync(targetUri, fileContentType, fileName, file, contentLength, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadDocumentWithResponseAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength
    ) {
        return FluxUtil.withContext(context ->
            uploadDocumentWithResponseAsync(targetUri, fileContentType, fileName, file, contentLength, context)
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<DocumentReference>> uploadDocumentWithResponseAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadDocument(this.client.getHost(), targetUri, fileContentType, fileName, file, contentLength, accept, context);
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadDocumentAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength
    ) {
        return uploadDocumentWithResponseAsync(targetUri, fileContentType, fileName, file, contentLength).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<DocumentReference> uploadDocumentAsync(
        String targetUri,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        return uploadDocumentWithResponseAsync(targetUri, fileContentType, fileName, file, contentLength, context).flatMap(res ->
            Mono.justOrEmpty(res.getValue())
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<DocumentReference> uploadDocumentWithResponse(
        String targetUri,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength,
        Context context
    ) {
        final String accept = "application/json";
        return service.uploadDocumentSync(
            this.client.getHost(),
            targetUri,
            fileContentType,
            fileName,
            file,
            contentLength,
            accept,
            context
        );
    }

    /**
     * Upload a temporal document
     *
     * Upload a temporal document that later on must be linked with a domain resource of its target.
     *
     * The target resource is identified by `targetUri`:
     *
     * * `ku:process/{processId}`: the document is deleted after 2 hours as long as it has not been
     * linked to the process or to one of its process items.
     * * `ku:business-artifact/{businessArtifactId}`: the document is deleted after 2 hours as long
     * as it has not been linked to the business artifact.
     *
     * A target the credentials cannot read answers `404`.
     *
     * @param targetUri URI of the resource the document is uploaded for. Supported forms are `ku:process/{id}`
     * and `ku:business-artifact/{id}`.
     * @param fileContentType Document content type.
     * @param fileName Document name.
     * @param file Document to save.
     * @param contentLength The Content-Length header for the request.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public DocumentReference uploadDocument(
        String targetUri,
        String fileContentType,
        String fileName,
        BinaryData file,
        long contentLength
    ) {
        return uploadDocumentWithResponse(targetUri, fileContentType, fileName, file, contentLength, Context.NONE).getValue();
    }

    /**
     * Download a document
     *
     * Given a document uri, download its content. Accepts both the `kuflow-file:` reference returned
     * by the upload operations and a plain `ku:` document uri.
     *
     * The credentials must be able to read the resource that owns the document; a document outside
     * their reach, or a uri that does not resolve to a document, answers `404`.
     *
     * @param documentUri Document URI to download, in `kuflow-file:` or `ku:` form.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadDocumentWithResponseAsync(String documentUri) {
        return FluxUtil.withContext(context -> downloadDocumentWithResponseAsync(documentUri, context));
    }

    /**
     * Download a document
     *
     * Given a document uri, download its content. Accepts both the `kuflow-file:` reference returned
     * by the upload operations and a plain `ku:` document uri.
     *
     * The credentials must be able to read the resource that owns the document; a document outside
     * their reach, or a uri that does not resolve to a document, answers `404`.
     *
     * @param documentUri Document URI to download, in `kuflow-file:` or `ku:` form.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<Response<BinaryData>> downloadDocumentWithResponseAsync(String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.downloadDocument(this.client.getHost(), documentUri, accept, context);
    }

    /**
     * Download a document
     *
     * Given a document uri, download its content. Accepts both the `kuflow-file:` reference returned
     * by the upload operations and a plain `ku:` document uri.
     *
     * The credentials must be able to read the resource that owns the document; a document outside
     * their reach, or a uri that does not resolve to a document, answers `404`.
     *
     * @param documentUri Document URI to download, in `kuflow-file:` or `ku:` form.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadDocumentAsync(String documentUri) {
        return downloadDocumentWithResponseAsync(documentUri).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download a document
     *
     * Given a document uri, download its content. Accepts both the `kuflow-file:` reference returned
     * by the upload operations and a plain `ku:` document uri.
     *
     * The credentials must be able to read the resource that owns the document; a document outside
     * their reach, or a uri that does not resolve to a document, answers `404`.
     *
     * @param documentUri Document URI to download, in `kuflow-file:` or `ku:` form.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<BinaryData> downloadDocumentAsync(String documentUri, Context context) {
        return downloadDocumentWithResponseAsync(documentUri, context).flatMap(res -> Mono.justOrEmpty(res.getValue()));
    }

    /**
     * Download a document
     *
     * Given a document uri, download its content. Accepts both the `kuflow-file:` reference returned
     * by the upload operations and a plain `ku:` document uri.
     *
     * The credentials must be able to read the resource that owns the document; a document outside
     * their reach, or a uri that does not resolve to a document, answers `404`.
     *
     * @param documentUri Document URI to download, in `kuflow-file:` or `ku:` form.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BinaryData> downloadDocumentWithResponse(String documentUri, Context context) {
        final String accept = "application/octet-stream, application/json";
        return service.downloadDocumentSync(this.client.getHost(), documentUri, accept, context);
    }

    /**
     * Download a document
     *
     * Given a document uri, download its content. Accepts both the `kuflow-file:` reference returned
     * by the upload operations and a plain `ku:` document uri.
     *
     * The credentials must be able to read the resource that owns the document; a document outside
     * their reach, or a uri that does not resolve to a document, answers `404`.
     *
     * @param documentUri Document URI to download, in `kuflow-file:` or `ku:` form.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BinaryData downloadDocument(String documentUri) {
        return downloadDocumentWithResponse(documentUri, Context.NONE).getValue();
    }
}
