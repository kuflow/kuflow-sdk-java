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
import com.kuflow.rest.implementation.TenantUserOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.TenantUser;
import com.kuflow.rest.model.TenantUserFindOptions;
import com.kuflow.rest.model.TenantUserPage;
import java.util.List;
import java.util.UUID;
import reactor.core.publisher.Mono;

/**
 * An instance of this class provides access to all the operations defined in TenantUserOperations.
 */
public class TenantUserOperations {

    /** The service. */
    private final TenantUserOperationsImpl service;

    /**
     * Initializes an instance of PrincipalOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public TenantUserOperations(KuFlowClientImpl client) {
        this.service = client.getTenantUserOperations();
    }

    /**
     * Find all accessible Tenant Users
     * <p>
     * List all the Tenant Users that have been created and the used credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TenantUserPage> findTenantUsersWithResponse(TenantUserFindOptions options, Context context) {
        options = options != null ? options : new TenantUserFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> groupId = !options.getGroupIds().isEmpty() ? options.getGroupIds() : null;
        List<String> email = !options.getEmails().isEmpty() ? options.getEmails() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findTenantUsersWithResponse(size, page, sort, groupId, email, tenantId, context);
    }

    /**
     * Find all accessible Tenant Users
     * <p>
     * List all the Tenant Users that have been created and the used credentials has access.
     * <p>
     * Available sort query values: id, createdAt, lastModifiedAt.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantUserPage findTenantUsers(TenantUserFindOptions options) {
        return this.findTenantUsersWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Tenant Users
     * <p>
     * List all the Tenant Users that have been created and the used credentials has access.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantUserPage findTenantUsers() {
        return this.findTenantUsersWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Get a Tenant User by ID
     * <p>
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TenantUser> retrieveTenantUserWithResponse(UUID id, Context context) {
        return this.service.retrieveTenantUserWithResponse(id, context);
    }

    /**
     * Get a Tenant User by ID
     * <p>
     * Returns the requested TenantUser when has access to do it.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response} on successful completion of {@link Mono}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantUser retrieveTenantUser(UUID id) {
        return this.retrieveTenantUserWithResponse(id, Context.NONE).getValue();
    }
}
