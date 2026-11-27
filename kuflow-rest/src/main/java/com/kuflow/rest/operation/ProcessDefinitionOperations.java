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
import com.kuflow.rest.implementation.ProcessDefinitionOperationsImpl;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.ProcessDefinition;
import com.kuflow.rest.model.ProcessDefinitionFindOptions;
import com.kuflow.rest.model.ProcessDefinitionPage;
import com.kuflow.rest.model.ProcessDefinitionState;
import java.util.List;
import java.util.UUID;

/**
 * An instance of this class provides access to all the operations defined in ProcessDefinitionOperations.
 */
public class ProcessDefinitionOperations {

    /** The service. */
    private final ProcessDefinitionOperationsImpl service;

    /**
     * Initializes an instance of ProcessDefinitionOperationsImpl.
     *
     * @param client the instance of the service client containing this operation class.
     */
    public ProcessDefinitionOperations(KuFlowClientImpl client) {
        this.service = client.getProcessDefinitionOperations();
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param options The options parameters.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessDefinitionPage> findProcessDefinitionsWithResponse(ProcessDefinitionFindOptions options, Context context) {
        options = options != null ? options : new ProcessDefinitionFindOptions();

        Integer size = options.getSize();
        Integer page = options.getPage();
        List<String> sort = !options.getSorts().isEmpty() ? options.getSorts() : null;
        List<UUID> tenantId = !options.getTenantIds().isEmpty() ? options.getTenantIds() : null;
        List<String> processDefinitionCode = !options.getProcessDefinitionCodes().isEmpty() ? options.getProcessDefinitionCodes() : null;
        List<ProcessDefinitionState> state = !options.getStates().isEmpty() ? options.getStates() : null;
        String name = options.getName();

        return this.service.findProcessDefinitionsWithResponse(size, page, sort, tenantId, processDefinitionCode, state, name, context);
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @param options The options parameters.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinitionPage findProcessDefinitions(ProcessDefinitionFindOptions options) {
        return this.findProcessDefinitionsWithResponse(options, Context.NONE).getValue();
    }

    /**
     * Find the Process Definitions that the credentials administer
     *
     * List the live Process Definitions that the credentials used can update, each one at its latest version: the
     * draft when there is one, otherwise the current one. Archived ones are not listed, nor the ones with a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * Available sort query values: name, code.
     *
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinitionPage findProcessDefinitions() {
        return this.findProcessDefinitionsWithResponse(null, Context.NONE).getValue();
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition when the credentials used can update it: its forms, its tasks and threads,
     * its actions, schedulers and permissions, and its workflow. Without `version`, at its latest version: the draft
     * when there is one, otherwise the current one. An archived one is not found, nor one whose version has a task
     * whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessDefinition> retrieveProcessDefinitionWithResponse(UUID id, UUID version, Context context) {
        return this.service.retrieveProcessDefinitionWithResponse(id, version, context);
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition at the version given, or at its latest version without it. An archived one is
     * not found, nor one whose version has a task whose form is made of elements, a deprecated kind of form.
     *
     * @param id The resource ID.
     * @param version The id of a version of the process definition. Without it, its latest version.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinition retrieveProcessDefinition(UUID id, UUID version) {
        return this.retrieveProcessDefinitionWithResponse(id, version, Context.NONE).getValue();
    }

    /**
     * Get a Process Definition by ID
     *
     * Returns the whole Process Definition, at its latest version: the draft when there is one, otherwise the current
     * one. An archived one is not found, nor one whose version has a task whose form is made of elements, a deprecated
     * kind of form.
     *
     * @param id The resource ID.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinition retrieveProcessDefinition(UUID id) {
        return this.retrieveProcessDefinitionWithResponse(id, null, Context.NONE).getValue();
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, whole, as `retrieveProcessDefinition` returns it, when the
     * credentials used can update it. Its `revision` and its `version` have to be the ones that the definition has now,
     * at its latest version: otherwise the answer is a conflict (409). One equal to the one that the definition has
     * changes nothing and is not a conflict, whatever its revision.
     *
     * Strict, and all or nothing: a property that is not known, a value that is not valid or a change outside what can
     * be changed yet is a bad request (400) with the JSON Pointer of each one, and nothing is applied.
     *
     * What can be changed yet, all of it in the draft, which a published definition gets when it has none:
     * - Its forms (`structureDataDefinitionEntries`) and its tasks and threads (`processItemDefinitions`): add, remove
     *   or change them. They are matched by their `id`, and a new one comes with a new UUID that the client gives.
     * - Of a form, its content (its `jsonDefinition`, but its `type`). Of a task, what it does (its `task`).
     * - The name of a form, and the name, description and notifications of a task or thread, while only the draft has
     *   it: in a published version they would change at once.
     *
     * Codes, types and permissions (`rbacEntries`) do not change. A new task or thread has no permissions, and it needs
     * a `description`, as every one has it. Removing what another part names (the form of a task, the form of the
     * metadata or of the entity, or the task or thread that an action creates) is a bad request that says who names it.
     * Nothing is published.
     *
     * With `dryRun`, the answer is the Process Definition as it would be. When the changes would start a draft, the
     * `id` of its version is not kept, nor is the `revision`.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer is what it would be.
     * @param context The context to associate with this operation.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response body along with {@link Response}.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public Response<ProcessDefinition> updateProcessDefinitionWithResponse(
        UUID id,
        ProcessDefinition processDefinition,
        Boolean dryRun,
        Context context
    ) {
        return this.service.updateProcessDefinitionWithResponse(id, processDefinition, dryRun, context);
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given, or, with `dryRun`, answers it as it would be without applying
     * anything.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @param dryRun When true, nothing is applied, and the answer is what it would be.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinition updateProcessDefinition(UUID id, ProcessDefinition processDefinition, boolean dryRun) {
        return this.updateProcessDefinitionWithResponse(id, processDefinition, dryRun, Context.NONE).getValue();
    }

    /**
     * Update a Process Definition
     *
     * Replaces the Process Definition with the one given.
     *
     * @param id The resource ID.
     * @param processDefinition The whole Process Definition, as it has to be.
     * @throws IllegalArgumentException thrown if parameters fail the validation.
     * @throws DefaultErrorException thrown if the request is rejected by server.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     * @return the response.
     */
    @ServiceMethod(returns = ReturnType.SINGLE)
    public ProcessDefinition updateProcessDefinition(UUID id, ProcessDefinition processDefinition) {
        return this.updateProcessDefinitionWithResponse(id, processDefinition, null, Context.NONE).getValue();
    }
}
