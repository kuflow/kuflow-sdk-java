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
import com.kuflow.rest.implementation.TenantOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.TenantFindOptions;
import com.kuflow.rest.model.TenantPage;
import java.util.List;
import java.util.UUID;

/**
 * An instance of this class provides access to all the operations defined in TenantOperations.
 */
public class TenantOperations {

    /** The service. */
    private final TenantOperationsImpl service;

    /**
     * Initializes an instance of TenantOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public TenantOperations(KuFlowClientImpl client) {
        this.service = client.getTenantOperations();
    }

    /**
     * Find all accessible Tenants
     *
     * List all the Tenants that the credentials used has access to.
     *
     * Available sort query values: id, name.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by an array of tenant ids.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<TenantPage> findTenantsWithResponse(TenantFindOptions options, Context context) {
        options = options != null ? options : new TenantFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findTenantsWithResponse(size, page, sort, tenantId, context);
    }

    /**
     * Find all accessible Tenants
     *
     * List all the Tenants that the credentials used has access to.
     *
     * Available sort query values: id, name.
     *
     * @param size The number of records returned within a single API call.
     * @param page The page number of the current page in the returned records, 0 is the first page.
     * @param sort Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     *
     * Default sort order is ascending. Multiple sort criteria are supported.
     *
     * Please refer to the method description for supported properties.
     * @param tenantId Filter by an array of tenant ids.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantPage findTenants(TenantFindOptions options) {
        return this.findTenantsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Tenants
     *
     * List all the Tenants that the credentials used has access to.
     *
     * Available sort query values: id, name.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public TenantPage findTenants() {
        return this.findTenantsWithResponse(null, Context.NONE).getValue();
    }
}
