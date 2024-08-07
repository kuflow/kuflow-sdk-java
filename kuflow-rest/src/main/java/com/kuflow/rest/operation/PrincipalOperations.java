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
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.implementation.PrincipalOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.Principal;
import com.kuflow.rest.model.PrincipalFindOptions;
import com.kuflow.rest.model.PrincipalPage;
import com.kuflow.rest.model.PrincipalType;
import java.util.List;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in PrincipalOperations. */
public class PrincipalOperations {

    /** The service. */
    private final PrincipalOperationsImpl service;

    /**
     * Initializes an instance of PrincipalOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public PrincipalOperations(KuFlowClientImpl client) {
        this.service = client.getPrincipalOperations();
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<PrincipalPage> findPrincipalsWithResponse(PrincipalFindOptions options, Context context) {
        options = options != null ? options : new PrincipalFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        PrincipalType type = options.getType();
        List<UUID> groupId = !options.getGroupIds().isEmpty() ? options.getGroupIds() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findPrincipalsWithResponse(size, page, sort, type, groupId, tenantId, context);
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PrincipalPage findPrincipals(PrincipalFindOptions options) {
        return this.findPrincipalsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Principals
     *
     * <p>List all the Principals that have been created and the used credentials has access.
     *
     * <p>Available sort query values: id, name.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public PrincipalPage findPrincipals() {
        return this.findPrincipalsWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Get a Principal by ID
     *
     * <p>Returns the requested Principal when has access to do it.
     *
     * @param id The resource ID.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<Principal> retrievePrincipalWithResponse(UUID id, Context context) {
        return this.service.retrievePrincipalWithResponse(id, context);
    }

    /**
     * Get a Principal by ID
     *
     * <p>Returns the requested Principal when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Principal retrievePrincipal(UUID id) {
        return this.retrievePrincipalWithResponse(id, Context.NONE).getValue();
    }
}
