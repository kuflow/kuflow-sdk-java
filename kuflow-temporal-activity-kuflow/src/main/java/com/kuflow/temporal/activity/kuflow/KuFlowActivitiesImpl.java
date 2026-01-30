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
package com.kuflow.temporal.activity.kuflow;

import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesFailure.createApplicationFailure;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateClaimTaskRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validatePrincipalRetrieveRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessEntityPatchRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessEntityUpdateRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessInitiatorChangeRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemCreateRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemRetrieve;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemTaskAssignRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemTaskCompleteRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemTaskDataPatchRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemTaskDataUpdateRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessItemTaskLoggAppendRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessMetadataPatchRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessMetadataUpdateRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateProcessRetrieveRequest;
import static com.kuflow.temporal.activity.kuflow.util.KuFlowActivitiesValidation.validateTenantUserRetrieveRequest;

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.Principal;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessChangeInitiatorParams;
import com.kuflow.rest.model.ProcessEntityUpdateParams;
import com.kuflow.rest.model.ProcessFindOptions;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemCreateParams;
import com.kuflow.rest.model.ProcessItemFindOptions;
import com.kuflow.rest.model.ProcessItemPage;
import com.kuflow.rest.model.ProcessItemTaskAppendLogParams;
import com.kuflow.rest.model.ProcessItemTaskAssignParams;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import com.kuflow.rest.model.ProcessMetadataUpdateParams;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.TenantUser;
import com.kuflow.rest.operation.PrincipalOperations;
import com.kuflow.rest.operation.ProcessItemOperations;
import com.kuflow.rest.operation.ProcessOperations;
import com.kuflow.rest.operation.TenantUserOperations;
import com.kuflow.temporal.activity.kuflow.model.PrincipalRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.PrincipalRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessFindRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessFindResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessInitiatorChangeRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessInitiatorChangeResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemCreateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemCreateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemFindRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemFindResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskAssignRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskAssignResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskClaimRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskClaimResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskCompleteRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskCompleteResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskLoggAppendRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskLoggAppendResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.TenantUserRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.TenantUserRetrieveResponse;
import javax.annotation.Nonnull;

public class KuFlowActivitiesImpl implements KuFlowActivities {

    private final PrincipalOperations principalOperations;

    private final TenantUserOperations tenantUserOperations;

    private final ProcessOperations processOperations;

    private final ProcessItemOperations processItemOperations;

    public KuFlowActivitiesImpl(KuFlowRestClient kuFlowRestClient) {
        this.principalOperations = kuFlowRestClient.getPrincipalOperations();
        this.tenantUserOperations = kuFlowRestClient.getTenantUserOperations();
        this.processOperations = kuFlowRestClient.getProcessOperations();
        this.processItemOperations = kuFlowRestClient.getProcessItemOperations();
    }

    @Nonnull
    @Override
    public PrincipalRetrieveResponse retrievePrincipal(@Nonnull PrincipalRetrieveRequest request) {
        try {
            validatePrincipalRetrieveRequest(request);

            Principal principal = this.principalOperations.retrievePrincipal(request.getPrincipalId());

            PrincipalRetrieveResponse response = new PrincipalRetrieveResponse();
            response.setPrincipal(principal);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public TenantUserRetrieveResponse retrieveTenantUser(@Nonnull TenantUserRetrieveRequest request) {
        try {
            validateTenantUserRetrieveRequest(request);

            TenantUser tenantUser = this.tenantUserOperations.retrieveTenantUser(request.getTenantUserId());

            TenantUserRetrieveResponse response = new TenantUserRetrieveResponse();
            response.setTenantUser(tenantUser);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessFindResponse findProcesses(ProcessFindRequest request) {
        try {
            ProcessFindOptions options = new ProcessFindOptions()
                .setPage(request.getPage())
                .setSize(request.getSize())
                .setSorts(request.getSorts());

            ProcessPage processes = this.processOperations.findProcesses(options);

            ProcessFindResponse response = new ProcessFindResponse();
            response.setProcesses(processes);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessRetrieveResponse retrieveProcess(@Nonnull ProcessRetrieveRequest request) {
        try {
            validateProcessRetrieveRequest(request);

            Process process = this.processOperations.retrieveProcess(request.getProcessId());

            ProcessRetrieveResponse response = new ProcessRetrieveResponse();
            response.setProcess(process);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessEntityUpdateResponse updateProcessEntity(@Nonnull ProcessEntityUpdateRequest request) {
        try {
            validateProcessEntityUpdateRequest(request);

            ProcessEntityUpdateParams params = new ProcessEntityUpdateParams().setEntity(request.getEntity());

            Process process = this.processOperations.updateProcessEntity(request.getProcessId(), params);

            ProcessEntityUpdateResponse response = new ProcessEntityUpdateResponse();
            response.setProcess(process);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessEntityPatchResponse patchProcessEntity(@Nonnull ProcessEntityPatchRequest request) {
        try {
            validateProcessEntityPatchRequest(request);

            Process process = this.processOperations.patchProcessEntity(request.getProcessId(), request.getJsonPatch());

            ProcessEntityPatchResponse response = new ProcessEntityPatchResponse();
            response.setProcess(process);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessMetadataUpdateResponse updateProcessMetadata(@Nonnull ProcessMetadataUpdateRequest request) {
        try {
            validateProcessMetadataUpdateRequest(request);

            ProcessMetadataUpdateParams params = new ProcessMetadataUpdateParams().setMetadata(request.getMetadata());

            Process process = this.processOperations.updateProcessMetadata(request.getProcessId(), params);

            ProcessMetadataUpdateResponse response = new ProcessMetadataUpdateResponse();
            response.setProcess(process);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessMetadataPatchResponse patchProcessMetadata(@Nonnull ProcessMetadataPatchRequest request) {
        try {
            validateProcessMetadataPatchRequest(request);

            Process process = this.processOperations.patchProcessMetadata(request.getProcessId(), request.getJsonPatch());

            ProcessMetadataPatchResponse response = new ProcessMetadataPatchResponse();
            response.setProcess(process);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessInitiatorChangeResponse changeProcessInitiator(@Nonnull ProcessInitiatorChangeRequest request) {
        try {
            validateProcessInitiatorChangeRequest(request);

            ProcessChangeInitiatorParams params = new ProcessChangeInitiatorParams()
                .setInitiatorId(request.getInitiatorId())
                .setInitiatorEmail(request.getInitiatorEmail());

            Process process = this.processOperations.changeProcessInitiator(request.getProcessId(), params);

            ProcessInitiatorChangeResponse response = new ProcessInitiatorChangeResponse();
            response.setProcess(process);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemFindResponse findProcessItems(ProcessItemFindRequest request) {
        try {
            ProcessItemFindOptions options = new ProcessItemFindOptions()
                .setSize(request.getSize())
                .setPage(request.getPage())
                .setSorts(request.getSorts())
                .setTenantIds(request.getTenantIds())
                .setProcessIds(request.getProcessIds())
                .setTypes(request.getTypes())
                .setTaskStates(request.getTaskStates())
                .setProcessItemDefinitionCode(request.getProcessItemDefinitionCodes());

            ProcessItemPage processItems = this.processItemOperations.findProcessItems(options);

            ProcessItemFindResponse response = new ProcessItemFindResponse();
            response.setProcessItems(processItems);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemRetrieveResponse retrieveProcessItem(@Nonnull ProcessItemRetrieveRequest request) {
        try {
            validateProcessItemRetrieve(request);

            ProcessItem processItem = this.processItemOperations.retrieveProcessItem(request.getProcessItemId());

            ProcessItemRetrieveResponse response = new ProcessItemRetrieveResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemCreateResponse createProcessItem(@Nonnull ProcessItemCreateRequest request) {
        try {
            validateProcessItemCreateRequest(request);

            ProcessItemCreateParams params = new ProcessItemCreateParams()
                .setId(request.getId())
                .setType(request.getType())
                .setProcessId(request.getProcessId())
                .setOwnerId(request.getOwnerId())
                .setOwnerEmail(request.getOwnerEmail())
                .setProcessItemDefinitionCode(request.getProcessItemDefinitionCode())
                .setTask(request.getTask())
                .setMessage(request.getMessage());

            ProcessItem processItem = this.processItemOperations.createProcessItem(params);

            ProcessItemCreateResponse response = new ProcessItemCreateResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemTaskCompleteResponse completeProcessItemTask(@Nonnull ProcessItemTaskCompleteRequest request) {
        try {
            validateProcessItemTaskCompleteRequest(request);

            ProcessItem processItem = this.processItemOperations.completeProcessItemTask(request.getProcessItemId());

            ProcessItemTaskCompleteResponse response = new ProcessItemTaskCompleteResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemTaskClaimResponse claimProcessItemTask(@Nonnull ProcessItemTaskClaimRequest request) {
        try {
            validateClaimTaskRequest(request);

            ProcessItem processItem = this.processItemOperations.claimProcessItemTask(request.getProcessItemId());

            ProcessItemTaskClaimResponse response = new ProcessItemTaskClaimResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemTaskAssignResponse assignProcessItemTask(@Nonnull ProcessItemTaskAssignRequest request) {
        try {
            validateProcessItemTaskAssignRequest(request);

            ProcessItemTaskAssignParams command = new ProcessItemTaskAssignParams()
                .setOwnerEmail(request.getOwnerEmail())
                .setOwnerId(request.getOwnerId());

            ProcessItem processItem = this.processItemOperations.assignProcessItemTask(request.getProcessItemId(), command);

            ProcessItemTaskAssignResponse response = new ProcessItemTaskAssignResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemTaskDataUpdateResponse updateProcessItemTaskData(@Nonnull ProcessItemTaskDataUpdateRequest request) {
        try {
            validateProcessItemTaskDataUpdateRequest(request);

            ProcessItemTaskDataUpdateParams params = new ProcessItemTaskDataUpdateParams().setData(request.getData());

            ProcessItem processItem = this.processItemOperations.updateProcessItemTaskData(request.getProcessItemId(), params);

            ProcessItemTaskDataUpdateResponse response = new ProcessItemTaskDataUpdateResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemTaskDataPatchResponse patchProcessItemTaskData(@Nonnull ProcessItemTaskDataPatchRequest request) {
        try {
            validateProcessItemTaskDataPatchRequest(request);

            ProcessItem processItem = this.processItemOperations.patchProcessItemTaskData(
                request.getProcessItemId(),
                request.getJsonPatch()
            );

            ProcessItemTaskDataPatchResponse response = new ProcessItemTaskDataPatchResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }

    @Nonnull
    @Override
    public ProcessItemTaskLoggAppendResponse appendProcessItemTaskLog(@Nonnull ProcessItemTaskLoggAppendRequest request) {
        try {
            validateProcessItemTaskLoggAppendRequest(request);

            ProcessItemTaskAppendLogParams params = new ProcessItemTaskAppendLogParams()
                .setMessage(request.getMessage())
                .setLevel(request.getLevel());

            ProcessItem processItem = this.processItemOperations.appendProcessItemTaskLog(request.getProcessItemId(), params);

            ProcessItemTaskLoggAppendResponse response = new ProcessItemTaskLoggAppendResponse();
            response.setProcessItem(processItem);

            return response;
        } catch (Exception e) {
            throw createApplicationFailure(e);
        }
    }
}
