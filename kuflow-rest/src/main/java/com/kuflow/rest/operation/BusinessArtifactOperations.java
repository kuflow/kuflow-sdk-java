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
import com.azure.core.util.Context;
import com.kuflow.rest.implementation.BusinessArtifactOperationsImpl;
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.model.BusinessArtifact;
import com.kuflow.rest.model.BusinessArtifactCreateParams;
import com.kuflow.rest.model.BusinessArtifactDataUpdateParams;
import com.kuflow.rest.model.BusinessArtifactFindOptions;
import com.kuflow.rest.model.BusinessArtifactPage;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.JsonPatchOperation;
import java.util.List;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in BusinessArtifactOperations. */
public class BusinessArtifactOperations {

    /** The service. */
    private final BusinessArtifactOperationsImpl service;

    /**
     * Initializes an instance of BusinessArtifactOperations.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public BusinessArtifactOperations(KuFlowClientImpl client) {
        this.service = client.getBusinessArtifactOperations();
    }

    /**
     * Find all accessible Business Artifacts
     * <p>
     * List all the Business Artifacts that have been created and the credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifactPage> findBusinessArtifactsWithResponse(BusinessArtifactFindOptions options, Context context) {
        options = options != null ? options : new BusinessArtifactFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;
        List<UUID> businessArtifactDefinitionId = !options.getBusinessArtifactDefinitionIds().isEmpty()
            ? options.getBusinessArtifactDefinitionIds()
            : null;
        List<String> businessArtifactDefinitionCode = !options.getBusinessArtifactDefinitionCodes().isEmpty()
            ? options.getBusinessArtifactDefinitionCodes()
            : null;

        return this.service.findBusinessArtifactsWithResponse(
            size,
            page,
            sort,
            tenantId,
            businessArtifactDefinitionId,
            businessArtifactDefinitionCode,
            context
        );
    }

    /**
     * Find all accessible Business Artifacts
     * <p>
     * List all the Business Artifacts that have been created and the credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifactPage findBusinessArtifacts(BusinessArtifactFindOptions options) {
        return this.findBusinessArtifactsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Business Artifacts
     * <p>
     * List all the Business Artifacts that have been created and the credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifactPage findBusinessArtifacts() {
        return this.findBusinessArtifactsWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Create a new Business Artifact
     * <p>
     * Creates a Business Artifact.
     * <p>
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Business Artifact to create.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> createBusinessArtifactWithResponse(BusinessArtifactCreateParams params, Context context) {
        return this.service.createBusinessArtifactWithResponse(params, context);
    }

    /**
     * Create a new Business Artifact
     * <p>
     * Creates a Business Artifact.
     * <p>
     * If you want the method to be idempotent, please specify the `id` field in the request body.
     *
     * @param params Business Artifact to create.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact createBusinessArtifact(BusinessArtifactCreateParams params) {
        return this.createBusinessArtifactWithResponse(params, Context.NONE).getValue();
    }

    /**
     * Get a Business Artifact by ID
     * <p>
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> retrieveBusinessArtifactWithResponse(UUID id, Context context) {
        return this.service.retrieveBusinessArtifactWithResponse(id, context);
    }

    /**
     * Get a Business Artifact by ID
     * <p>
     * Returns the requested Business Artifact when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact retrieveBusinessArtifact(UUID id) {
        return this.retrieveBusinessArtifactWithResponse(id, Context.NONE).getValue();
    }

    /**
     * Delete a Business Artifact by ID
     * <p>
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Void> deleteBusinessArtifactWithResponse(UUID id, Context context) {
        return this.service.deleteBusinessArtifactWithResponse(id, context);
    }

    /**
     * Delete a Business Artifact by ID
     * <p>
     * Deletes the requested Business Artifact.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public void deleteBusinessArtifact(UUID id) {
        this.deleteBusinessArtifactWithResponse(id, Context.NONE);
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> updateBusinessArtifactDataWithResponse(
        UUID id,
        BusinessArtifactDataUpdateParams params,
        Context context
    ) {
        return this.service.updateBusinessArtifactDataWithResponse(id, params, context);
    }

    /**
     * Save JSON data
     * <p>
     * Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param params Params used to update the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact updateBusinessArtifactData(UUID id, BusinessArtifactDataUpdateParams params) {
        return this.updateBusinessArtifactDataWithResponse(id, params, Context.NONE).getValue();
    }

    /**
     * Patch JSON data
     * <p>
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<BusinessArtifact> patchBusinessArtifactDataWithResponse(UUID id, List<JsonPatchOperation> jsonPatch, Context context) {
        return this.service.patchBusinessArtifactDataWithResponse(id, jsonPatch, context);
    }

    /**
     * Patch JSON data
     * <p>
     * Allow to patch a JSON data validating that the data follow the related schema. If the data is invalid, then
     * the json is marked as invalid.
     *
     * @param id The resource ID.
     * @param jsonPatch Params to save the JSON value.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public BusinessArtifact patchBusinessArtifactData(UUID id, List<JsonPatchOperation> jsonPatch) {
        return this.patchBusinessArtifactDataWithResponse(id, jsonPatch, Context.NONE).getValue();
    }
}
