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
import com.kuflow.rest.implementation.GroupOperationsImpl;
import com.kuflow.rest.implementation.KuFlowClientImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.GroupFindOptions;
import com.kuflow.rest.model.GroupPage;
import com.kuflow.rest.model.PrincipalFindOptions;
import java.util.List;
import java.util.UUID;

/** An instance of this class provides access to all the operations defined in GroupOperations. */
public class GroupOperations {

    /** The service. */
    private final GroupOperationsImpl service;

    /**
     * Initializes an instance of GroupOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public GroupOperations(KuFlowClientImpl client) {
        this.service = client.getGroupOperations();
    }

    /**
     * Find all accessible Groups
     *
     * List all the Groups that have been created and the used credentials has access.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.

     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<GroupPage> findGroupsWithResponse(GroupFindOptions options, Context context) {
        options = options != null ? options : new GroupFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        UUID principalId = options.getPrincipalId();
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;

        return this.service.findGroupsWithResponse(size, page, sort, tenantId, principalId, context);
    }

    /**
     * Find all accessible Groups
     *
     * List all the Groups that have been created and the used credentials has access.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.

     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public GroupPage findGroups(GroupFindOptions options) {
        return this.findGroupsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find all accessible Groups
     *
     * List all the Groups that have been created and the used credentials has access.
     *
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.

     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public GroupPage findGroups() {
        return this.findGroupsWithResponse(null, Context.NONE).getValue();
    }
}
