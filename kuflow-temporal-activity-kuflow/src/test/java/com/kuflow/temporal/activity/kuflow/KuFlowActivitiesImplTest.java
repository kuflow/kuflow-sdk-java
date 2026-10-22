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

import static com.kuflow.temporal.activity.kuflow.KuFlowFailureType.ACTIVITIES_FAILURE;
import static com.kuflow.temporal.activity.kuflow.KuFlowFailureType.ACTIVITIES_REST_FAILURE;
import static com.kuflow.temporal.activity.kuflow.KuFlowFailureType.ACTIVITIES_VALIDATION_FAILURE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.model.BusinessArtifact;
import com.kuflow.rest.model.BusinessArtifactAction;
import com.kuflow.rest.model.BusinessArtifactActionCreateArtifactPrepare;
import com.kuflow.rest.model.BusinessArtifactActionCreateArtifactPrepareParams;
import com.kuflow.rest.model.BusinessArtifactActionCreateParams;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsCreateArtifact;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsDownloadable;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsStartProcess;
import com.kuflow.rest.model.BusinessArtifactActionCreateParamsStartWorkflow;
import com.kuflow.rest.model.BusinessArtifactActionStartProcessPrepare;
import com.kuflow.rest.model.BusinessArtifactActionStartProcessPrepareParams;
import com.kuflow.rest.model.BusinessArtifactCreateParams;
import com.kuflow.rest.model.BusinessArtifactDataUpdateParams;
import com.kuflow.rest.model.BusinessArtifactFindOptions;
import com.kuflow.rest.model.BusinessArtifactPage;
import com.kuflow.rest.model.DefaultError;
import com.kuflow.rest.model.DefaultErrorException;
import com.kuflow.rest.model.DeletedQueryParam;
import com.kuflow.rest.model.JsonPatchOperation;
import com.kuflow.rest.model.JsonPatchOperationType;
import com.kuflow.rest.model.JsonValue;
import com.kuflow.rest.model.Principal;
import com.kuflow.rest.model.Process;
import com.kuflow.rest.model.ProcessAction;
import com.kuflow.rest.model.ProcessActionCreateParams;
import com.kuflow.rest.model.ProcessActionCreateParamsCreateProcessItemMessage;
import com.kuflow.rest.model.ProcessActionCreateParamsCreateTask;
import com.kuflow.rest.model.ProcessActionCreateParamsSendWorkflowSignal;
import com.kuflow.rest.model.ProcessActionCreateParamsStartRelatedProcess;
import com.kuflow.rest.model.ProcessChangeInitiatorParams;
import com.kuflow.rest.model.ProcessEntityUpdateParams;
import com.kuflow.rest.model.ProcessFindOptions;
import com.kuflow.rest.model.ProcessItem;
import com.kuflow.rest.model.ProcessItemAiAssistance;
import com.kuflow.rest.model.ProcessItemAiAssistanceGenerateParams;
import com.kuflow.rest.model.ProcessItemCreateParams;
import com.kuflow.rest.model.ProcessItemFindOptions;
import com.kuflow.rest.model.ProcessItemMessageCreateParams;
import com.kuflow.rest.model.ProcessItemPage;
import com.kuflow.rest.model.ProcessItemTaskAppendLogParams;
import com.kuflow.rest.model.ProcessItemTaskAssignParams;
import com.kuflow.rest.model.ProcessItemTaskContextDataUpdateParams;
import com.kuflow.rest.model.ProcessItemTaskCreateParams;
import com.kuflow.rest.model.ProcessItemTaskDataUpdateParams;
import com.kuflow.rest.model.ProcessItemTaskLogLevel;
import com.kuflow.rest.model.ProcessItemTaskState;
import com.kuflow.rest.model.ProcessItemType;
import com.kuflow.rest.model.ProcessMetadataUpdateParams;
import com.kuflow.rest.model.ProcessPage;
import com.kuflow.rest.model.ProcessState;
import com.kuflow.rest.model.TenantUser;
import com.kuflow.rest.operation.BusinessArtifactOperations;
import com.kuflow.rest.operation.PrincipalOperations;
import com.kuflow.rest.operation.ProcessItemOperations;
import com.kuflow.rest.operation.ProcessOperations;
import com.kuflow.rest.operation.TenantUserOperations;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionCancelRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionCancelResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionCreateArtifactPrepareRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionCreateArtifactPrepareResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionCreateRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionCreateResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionStartProcessPrepareRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactActionStartProcessPrepareResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactCreateRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactCreateResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactDeleteRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactDeleteResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactFindRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactFindResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.PrincipalRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.PrincipalRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessActionCancelRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessActionCancelResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessActionCreateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessActionCreateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessActionRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessActionRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessDeleteRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessDeleteResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessFindRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessFindResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessInitiatorChangeRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessInitiatorChangeResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemAiAssistanceGenerateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemAiAssistanceGenerateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemAiAssistanceRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemAiAssistanceRetrieveResponse;
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
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskContextDataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskContextDataUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskLoggAppendRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskLoggAppendResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemsCancelRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemsCancelResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataUpdateResponse;
import com.kuflow.temporal.activity.kuflow.model.ProcessRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.TenantUserRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.TenantUserRetrieveResponse;
import io.temporal.failure.ApplicationFailure;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.stream.Stream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
@DisplayName("KuFlowActivitiesImpl")
public class KuFlowActivitiesImplTest {

    private static final UUID ID_1 = UUID.fromString("a3f1d8e0-0000-4000-8000-000000000001");

    private static final UUID ID_2 = UUID.fromString("a3f1d8e0-0000-4000-8000-000000000002");

    private static final UUID ID_3 = UUID.fromString("a3f1d8e0-0000-4000-8000-000000000003");

    private static final UUID ID_4 = UUID.fromString("a3f1d8e0-0000-4000-8000-000000000004");

    private static final JsonValue JSON_VALUE = new JsonValue().setValue(Map.of("key", "value"));

    private static final List<JsonPatchOperation> JSON_PATCH = List.of(
        new JsonPatchOperation().setOp(JsonPatchOperationType.REPLACE).setPath("/key").setValue("value")
    );

    @Mock
    private KuFlowRestClient kuFlowRestClient;

    @Mock
    private PrincipalOperations principalOperations;

    @Mock
    private TenantUserOperations tenantUserOperations;

    @Mock
    private ProcessOperations processOperations;

    @Mock
    private ProcessItemOperations processItemOperations;

    @Mock
    private BusinessArtifactOperations businessArtifactOperations;

    private KuFlowActivities kuFlowActivities;

    @BeforeEach
    public void setupTest() {
        given(this.kuFlowRestClient.getPrincipalOperations()).willReturn(this.principalOperations);
        given(this.kuFlowRestClient.getTenantUserOperations()).willReturn(this.tenantUserOperations);
        given(this.kuFlowRestClient.getProcessOperations()).willReturn(this.processOperations);
        given(this.kuFlowRestClient.getProcessItemOperations()).willReturn(this.processItemOperations);
        given(this.kuFlowRestClient.getBusinessArtifactOperations()).willReturn(this.businessArtifactOperations);

        this.kuFlowActivities = new KuFlowActivitiesImpl(this.kuFlowRestClient);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Principal and tenant user
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("GIVEN a principal id WHEN retrieve principal THEN the principal is retrieved and returned")
    public void givenAPrincipalIdWhenRetrievePrincipalThenThePrincipalIsRetrievedAndReturned() {
        Principal principal = new Principal();
        given(this.principalOperations.retrievePrincipal(ID_1)).willReturn(principal);

        PrincipalRetrieveResponse response = this.kuFlowActivities.retrievePrincipal(
            with(new PrincipalRetrieveRequest(), r -> r.setPrincipalId(ID_1))
        );

        assertThat(response.getPrincipal()).isSameAs(principal);
    }

    @Test
    @DisplayName("GIVEN a tenant user id WHEN retrieve tenant user THEN the tenant user is retrieved and returned")
    public void givenATenantUserIdWhenRetrieveTenantUserThenTheTenantUserIsRetrievedAndReturned() {
        TenantUser tenantUser = new TenantUser();
        given(this.tenantUserOperations.retrieveTenantUser(ID_1)).willReturn(tenantUser);

        TenantUserRetrieveResponse response = this.kuFlowActivities.retrieveTenantUser(
            with(new TenantUserRetrieveRequest(), r -> r.setTenantUserId(ID_1))
        );

        assertThat(response.getTenantUser()).isSameAs(tenantUser);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Processes
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("GIVEN every filter WHEN find processes THEN all the filters are mapped to the find options")
    public void givenEveryFilterWhenFindProcessesThenAllTheFiltersAreMappedToTheFindOptions() {
        ProcessPage processPage = new ProcessPage();
        given(this.processOperations.findProcesses(any(ProcessFindOptions.class))).willReturn(processPage);

        ProcessFindRequest request = new ProcessFindRequest()
            .setPage(2)
            .setSize(30)
            .setSort("createdAt,desc")
            .setTenantId(ID_1)
            .setProcessDefinitionId(ID_2)
            .setProcessDefinitionCode("PD_CODE")
            .setState(ProcessState.RUNNING)
            .setInitiatorId(ID_3)
            .setInitiatorEmail("jdoe@example.com")
            .setMetadata(List.of("code eq value"))
            .setDeleted(DeletedQueryParam.ONLY);

        ProcessFindResponse response = this.kuFlowActivities.findProcesses(request);

        ArgumentCaptor<ProcessFindOptions> captor = ArgumentCaptor.forClass(ProcessFindOptions.class);
        then(this.processOperations).should().findProcesses(captor.capture());
        ProcessFindOptions options = captor.getValue();
        assertThat(options.getPage()).isEqualTo(2);
        assertThat(options.getSize()).isEqualTo(30);
        assertThat(options.getSorts()).containsExactly("createdAt,desc");
        assertThat(options.getTenantIds()).containsExactly(ID_1);
        assertThat(options.getProcessDefinitionIds()).containsExactly(ID_2);
        assertThat(options.getProcessDefinitionCodes()).containsExactly("PD_CODE");
        assertThat(options.getStates()).containsExactly(ProcessState.RUNNING);
        assertThat(options.getInitiatorIds()).containsExactly(ID_3);
        assertThat(options.getInitiatorEmails()).containsExactly("jdoe@example.com");
        assertThat(options.getMetadata()).containsExactly("code eq value");
        assertThat(options.getDeleted()).isEqualTo(DeletedQueryParam.ONLY);
        assertThat(response.getProcesses()).isSameAs(processPage);
    }

    @Test
    @DisplayName("GIVEN a process id WHEN retrieve process THEN the process is retrieved and returned")
    public void givenAProcessIdWhenRetrieveProcessThenTheProcessIsRetrievedAndReturned() {
        Process process = new Process();
        given(this.processOperations.retrieveProcess(ID_1)).willReturn(process);

        ProcessRetrieveResponse response = this.kuFlowActivities.retrieveProcess(
            with(new ProcessRetrieveRequest(), r -> r.setProcessId(ID_1))
        );

        assertThat(response.getProcess()).isSameAs(process);
    }

    @Test
    @DisplayName("GIVEN a process id WHEN delete process THEN the process is deleted and an empty response is returned")
    public void givenAProcessIdWhenDeleteProcessThenTheProcessIsDeletedAndAnEmptyResponseIsReturned() {
        ProcessDeleteResponse response = this.kuFlowActivities.deleteProcess(with(new ProcessDeleteRequest(), r -> r.setProcessId(ID_1)));

        then(this.processOperations).should().deleteProcess(ID_1);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("GIVEN an entity WHEN update process entity THEN the entity is sent and the process is returned")
    public void givenAnEntityWhenUpdateProcessEntityThenTheEntityIsSentAndTheProcessIsReturned() {
        Process process = new Process();
        given(this.processOperations.updateProcessEntity(eq(ID_1), any(ProcessEntityUpdateParams.class))).willReturn(process);

        ProcessEntityUpdateResponse response = this.kuFlowActivities.updateProcessEntity(
            with(new ProcessEntityUpdateRequest(), r -> {
                r.setProcessId(ID_1);
                r.setEntity(JSON_VALUE);
            })
        );

        ArgumentCaptor<ProcessEntityUpdateParams> captor = ArgumentCaptor.forClass(ProcessEntityUpdateParams.class);
        then(this.processOperations).should().updateProcessEntity(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getEntity()).isSameAs(JSON_VALUE);
        assertThat(response.getProcess()).isSameAs(process);
    }

    @Test
    @DisplayName("GIVEN a json patch WHEN patch process entity THEN the patch is sent and the process is returned")
    public void givenAJsonPatchWhenPatchProcessEntityThenThePatchIsSentAndTheProcessIsReturned() {
        Process process = new Process();
        given(this.processOperations.patchProcessEntity(ID_1, JSON_PATCH)).willReturn(process);

        ProcessEntityPatchResponse response = this.kuFlowActivities.patchProcessEntity(
            with(new ProcessEntityPatchRequest(), r -> {
                r.setProcessId(ID_1);
                r.setJsonPatch(JSON_PATCH);
            })
        );

        assertThat(response.getProcess()).isSameAs(process);
    }

    @Test
    @DisplayName("GIVEN metadata WHEN update process metadata THEN the metadata is sent and the process is returned")
    public void givenMetadataWhenUpdateProcessMetadataThenTheMetadataIsSentAndTheProcessIsReturned() {
        Process process = new Process();
        given(this.processOperations.updateProcessMetadata(eq(ID_1), any(ProcessMetadataUpdateParams.class))).willReturn(process);

        ProcessMetadataUpdateResponse response = this.kuFlowActivities.updateProcessMetadata(
            with(new ProcessMetadataUpdateRequest(), r -> {
                r.setProcessId(ID_1);
                r.setMetadata(JSON_VALUE);
            })
        );

        ArgumentCaptor<ProcessMetadataUpdateParams> captor = ArgumentCaptor.forClass(ProcessMetadataUpdateParams.class);
        then(this.processOperations).should().updateProcessMetadata(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getMetadata()).isSameAs(JSON_VALUE);
        assertThat(response.getProcess()).isSameAs(process);
    }

    @Test
    @DisplayName("GIVEN a json patch WHEN patch process metadata THEN the patch is sent and the process is returned")
    public void givenAJsonPatchWhenPatchProcessMetadataThenThePatchIsSentAndTheProcessIsReturned() {
        Process process = new Process();
        given(this.processOperations.patchProcessMetadata(ID_1, JSON_PATCH)).willReturn(process);

        ProcessMetadataPatchResponse response = this.kuFlowActivities.patchProcessMetadata(
            with(new ProcessMetadataPatchRequest(), r -> {
                r.setProcessId(ID_1);
                r.setJsonPatch(JSON_PATCH);
            })
        );

        assertThat(response.getProcess()).isSameAs(process);
    }

    @Test
    @DisplayName("GIVEN a new initiator WHEN change process initiator THEN the initiator is sent and the process is returned")
    public void givenANewInitiatorWhenChangeProcessInitiatorThenTheInitiatorIsSentAndTheProcessIsReturned() {
        Process process = new Process();
        given(this.processOperations.changeProcessInitiator(eq(ID_1), any(ProcessChangeInitiatorParams.class))).willReturn(process);

        ProcessInitiatorChangeResponse response = this.kuFlowActivities.changeProcessInitiator(
            with(new ProcessInitiatorChangeRequest(), r -> {
                r.setProcessId(ID_1);
                r.setInitiatorId(ID_2);
                r.setInitiatorEmail("jdoe@example.com");
            })
        );

        ArgumentCaptor<ProcessChangeInitiatorParams> captor = ArgumentCaptor.forClass(ProcessChangeInitiatorParams.class);
        then(this.processOperations).should().changeProcessInitiator(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getInitiatorId()).isEqualTo(ID_2);
        assertThat(captor.getValue().getInitiatorEmail()).isEqualTo("jdoe@example.com");
        assertThat(response.getProcess()).isSameAs(process);
    }

    @Test
    @DisplayName("GIVEN every action field WHEN create process action THEN all the fields are sent and the action is returned")
    public void givenEveryActionFieldWhenCreateProcessActionThenAllTheFieldsAreSentAndTheActionIsReturned() {
        ProcessAction processAction = new ProcessAction();
        ProcessActionCreateParamsCreateTask createTask = new ProcessActionCreateParamsCreateTask();
        ProcessActionCreateParamsCreateProcessItemMessage createProcessItemMessage =
            new ProcessActionCreateParamsCreateProcessItemMessage();
        ProcessActionCreateParamsStartRelatedProcess startRelatedProcess = new ProcessActionCreateParamsStartRelatedProcess();
        ProcessActionCreateParamsSendWorkflowSignal sendWorkflowSignal = new ProcessActionCreateParamsSendWorkflowSignal();
        given(this.processOperations.createProcessAction(eq(ID_1), any(ProcessActionCreateParams.class))).willReturn(processAction);

        ProcessActionCreateResponse response = this.kuFlowActivities.createProcessAction(
            with(new ProcessActionCreateRequest(), r -> {
                r.setProcessId(ID_1);
                r.setId(ID_2);
                r.setProcessActionDefinitionCode("ACTION_CODE");
                r.setCreateTask(createTask);
                r.setCreateProcessItemMessage(createProcessItemMessage);
                r.setStartRelatedProcess(startRelatedProcess);
                r.setSendWorkflowSignal(sendWorkflowSignal);
            })
        );

        ArgumentCaptor<ProcessActionCreateParams> captor = ArgumentCaptor.forClass(ProcessActionCreateParams.class);
        then(this.processOperations).should().createProcessAction(eq(ID_1), captor.capture());
        ProcessActionCreateParams params = captor.getValue();
        assertThat(params.getId()).isEqualTo(ID_2);
        assertThat(params.getProcessActionDefinitionCode()).isEqualTo("ACTION_CODE");
        assertThat(params.getCreateTask()).isSameAs(createTask);
        assertThat(params.getCreateProcessItemMessage()).isSameAs(createProcessItemMessage);
        assertThat(params.getStartRelatedProcess()).isSameAs(startRelatedProcess);
        assertThat(params.getSendWorkflowSignal()).isSameAs(sendWorkflowSignal);
        assertThat(response.getProcessAction()).isSameAs(processAction);
    }

    @Test
    @DisplayName("GIVEN a process action id WHEN retrieve process action THEN the action is retrieved and returned")
    public void givenAProcessActionIdWhenRetrieveProcessActionThenTheActionIsRetrievedAndReturned() {
        ProcessAction processAction = new ProcessAction();
        given(this.processOperations.retrieveProcessAction(ID_1, ID_2)).willReturn(processAction);

        ProcessActionRetrieveResponse response = this.kuFlowActivities.retrieveProcessAction(
            with(new ProcessActionRetrieveRequest(), r -> {
                r.setProcessId(ID_1);
                r.setProcessActionId(ID_2);
            })
        );

        assertThat(response.getProcessAction()).isSameAs(processAction);
    }

    @Test
    @DisplayName("GIVEN a process action id WHEN cancel process action THEN the action is cancelled and returned")
    public void givenAProcessActionIdWhenCancelProcessActionThenTheActionIsCancelledAndReturned() {
        ProcessAction processAction = new ProcessAction();
        given(this.processOperations.cancelProcessAction(ID_1, ID_2)).willReturn(processAction);

        ProcessActionCancelResponse response = this.kuFlowActivities.cancelProcessAction(
            with(new ProcessActionCancelRequest(), r -> {
                r.setProcessId(ID_1);
                r.setProcessActionId(ID_2);
            })
        );

        assertThat(response.getProcessAction()).isSameAs(processAction);
    }

    @Test
    @DisplayName("GIVEN some process item ids WHEN cancel process items THEN the ids are sent and the process is returned")
    public void givenSomeProcessItemIdsWhenCancelProcessItemsThenTheIdsAreSentAndTheProcessIsReturned() {
        Process process = new Process();
        given(this.processOperations.cancelProcessItems(ID_1, List.of(ID_2, ID_3))).willReturn(process);

        ProcessItemsCancelResponse response = this.kuFlowActivities.cancelProcessItems(
            with(new ProcessItemsCancelRequest(), r -> {
                r.setProcessId(ID_1);
                r.setProcessItemIds(List.of(ID_2, ID_3));
            })
        );

        assertThat(response.getProcess()).isSameAs(process);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Process items
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("GIVEN every filter WHEN find process items THEN all the filters are mapped to the find options")
    public void givenEveryFilterWhenFindProcessItemsThenAllTheFiltersAreMappedToTheFindOptions() {
        ProcessItemPage processItemPage = new ProcessItemPage();
        given(this.processItemOperations.findProcessItems(any(ProcessItemFindOptions.class))).willReturn(processItemPage);

        ProcessItemFindRequest request = new ProcessItemFindRequest()
            .setSize(30)
            .setPage(2)
            .setSort("createdAt,desc")
            .setTenantId(ID_1)
            .setProcessId(ID_2)
            .setType(ProcessItemType.TASK)
            .setTaskState(ProcessItemTaskState.READY)
            .setTaskDefinitionCode("TASK_CODE")
            .setProcessDefinitionId(ID_3)
            .setProcessDefinitionCode("PD_CODE")
            .setDeleted(DeletedQueryParam.INCLUDE);

        ProcessItemFindResponse response = this.kuFlowActivities.findProcessItems(request);

        ArgumentCaptor<ProcessItemFindOptions> captor = ArgumentCaptor.forClass(ProcessItemFindOptions.class);
        then(this.processItemOperations).should().findProcessItems(captor.capture());
        ProcessItemFindOptions options = captor.getValue();
        assertThat(options.getSize()).isEqualTo(30);
        assertThat(options.getPage()).isEqualTo(2);
        assertThat(options.getSorts()).containsExactly("createdAt,desc");
        assertThat(options.getTenantIds()).containsExactly(ID_1);
        assertThat(options.getProcessIds()).containsExactly(ID_2);
        assertThat(options.getTypes()).containsExactly(ProcessItemType.TASK);
        assertThat(options.getTaskStates()).containsExactly(ProcessItemTaskState.READY);
        assertThat(options.getProcessItemDefinitionCodes()).containsExactly("TASK_CODE");
        assertThat(options.getProcessDefinitionIds()).containsExactly(ID_3);
        assertThat(options.getProcessDefinitionCodes()).containsExactly("PD_CODE");
        assertThat(options.getDeleted()).isEqualTo(DeletedQueryParam.INCLUDE);
        assertThat(response.getProcessItems()).isSameAs(processItemPage);
    }

    @Test
    @DisplayName("GIVEN a process item id WHEN retrieve process item THEN the process item is retrieved and returned")
    public void givenAProcessItemIdWhenRetrieveProcessItemThenTheProcessItemIsRetrievedAndReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.retrieveProcessItem(ID_1)).willReturn(processItem);

        ProcessItemRetrieveResponse response = this.kuFlowActivities.retrieveProcessItem(
            with(new ProcessItemRetrieveRequest(), r -> r.setProcessItemId(ID_1))
        );

        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN every task field WHEN create process item THEN all the fields are sent and the process item is returned")
    public void givenEveryTaskFieldWhenCreateProcessItemThenAllTheFieldsAreSentAndTheProcessItemIsReturned() {
        ProcessItem processItem = new ProcessItem();
        ProcessItemTaskCreateParams task = new ProcessItemTaskCreateParams().setData(JSON_VALUE);
        ProcessItemMessageCreateParams message = new ProcessItemMessageCreateParams().setText("A message");
        given(this.processItemOperations.createProcessItem(any(ProcessItemCreateParams.class))).willReturn(processItem);

        ProcessItemCreateResponse response = this.kuFlowActivities.createProcessItem(
            with(new ProcessItemCreateRequest(), r -> {
                r.setId(ID_1);
                r.setType(ProcessItemType.TASK);
                r.setProcessId(ID_2);
                r.setProcessItemThreadId(ID_3);
                r.setOwnerId(ID_4);
                r.setOwnerEmail("jdoe@example.com");
                r.setProcessItemDefinitionCode("TASK_CODE");
                r.setTask(task);
                r.setMessage(message);
            })
        );

        ArgumentCaptor<ProcessItemCreateParams> captor = ArgumentCaptor.forClass(ProcessItemCreateParams.class);
        then(this.processItemOperations).should().createProcessItem(captor.capture());
        ProcessItemCreateParams params = captor.getValue();
        assertThat(params.getId()).isEqualTo(ID_1);
        assertThat(params.getType()).isEqualTo(ProcessItemType.TASK);
        assertThat(params.getProcessId()).isEqualTo(ID_2);
        assertThat(params.getProcessItemThreadId()).isEqualTo(ID_3);
        assertThat(params.getOwnerId()).isEqualTo(ID_4);
        assertThat(params.getOwnerEmail()).isEqualTo("jdoe@example.com");
        assertThat(params.getProcessItemDefinitionCode()).isEqualTo("TASK_CODE");
        assertThat(params.getTask()).isSameAs(task);
        assertThat(params.getMessage()).isSameAs(message);
        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN a process item id WHEN complete process item task THEN the task is completed and returned")
    public void givenAProcessItemIdWhenCompleteProcessItemTaskThenTheTaskIsCompletedAndReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.completeProcessItemTask(ID_1)).willReturn(processItem);

        ProcessItemTaskCompleteResponse response = this.kuFlowActivities.completeProcessItemTask(
            with(new ProcessItemTaskCompleteRequest(), r -> r.setProcessItemId(ID_1))
        );

        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN a process item id WHEN claim process item task THEN the task is claimed and returned")
    public void givenAProcessItemIdWhenClaimProcessItemTaskThenTheTaskIsClaimedAndReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.claimProcessItemTask(ID_1)).willReturn(processItem);

        ProcessItemTaskClaimResponse response = this.kuFlowActivities.claimProcessItemTask(
            with(new ProcessItemTaskClaimRequest(), r -> r.setProcessItemId(ID_1))
        );

        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN an owner WHEN assign process item task THEN the owner is sent and the task is returned")
    public void givenAnOwnerWhenAssignProcessItemTaskThenTheOwnerIsSentAndTheTaskIsReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.assignProcessItemTask(eq(ID_1), any(ProcessItemTaskAssignParams.class))).willReturn(processItem);

        ProcessItemTaskAssignResponse response = this.kuFlowActivities.assignProcessItemTask(
            with(new ProcessItemTaskAssignRequest(), r -> {
                r.setProcessItemId(ID_1);
                r.setOwnerId(ID_2);
                r.setOwnerEmail("jdoe@example.com");
            })
        );

        ArgumentCaptor<ProcessItemTaskAssignParams> captor = ArgumentCaptor.forClass(ProcessItemTaskAssignParams.class);
        then(this.processItemOperations).should().assignProcessItemTask(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getOwnerId()).isEqualTo(ID_2);
        assertThat(captor.getValue().getOwnerEmail()).isEqualTo("jdoe@example.com");
        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN task data WHEN update process item task data THEN the data is sent and the task is returned")
    public void givenTaskDataWhenUpdateProcessItemTaskDataThenTheDataIsSentAndTheTaskIsReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.updateProcessItemTaskData(eq(ID_1), any(ProcessItemTaskDataUpdateParams.class))).willReturn(
            processItem
        );

        ProcessItemTaskDataUpdateResponse response = this.kuFlowActivities.updateProcessItemTaskData(
            with(new ProcessItemTaskDataUpdateRequest(), r -> {
                r.setProcessItemId(ID_1);
                r.setData(JSON_VALUE);
            })
        );

        ArgumentCaptor<ProcessItemTaskDataUpdateParams> captor = ArgumentCaptor.forClass(ProcessItemTaskDataUpdateParams.class);
        then(this.processItemOperations).should().updateProcessItemTaskData(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getData()).isSameAs(JSON_VALUE);
        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN task context data WHEN update process item task context data THEN the data is sent and the task is returned")
    public void givenTaskContextDataWhenUpdateProcessItemTaskContextDataThenTheDataIsSentAndTheTaskIsReturned() {
        ProcessItem processItem = new ProcessItem();
        given(
            this.processItemOperations.updateProcessItemTaskContextData(eq(ID_1), any(ProcessItemTaskContextDataUpdateParams.class))
        ).willReturn(processItem);

        ProcessItemTaskContextDataUpdateResponse response = this.kuFlowActivities.updateProcessItemTaskContextData(
            with(new ProcessItemTaskContextDataUpdateRequest(), r -> {
                r.setProcessItemId(ID_1);
                r.setData(JSON_VALUE);
            })
        );

        ArgumentCaptor<ProcessItemTaskContextDataUpdateParams> captor = ArgumentCaptor.forClass(
            ProcessItemTaskContextDataUpdateParams.class
        );
        then(this.processItemOperations).should().updateProcessItemTaskContextData(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getData()).isSameAs(JSON_VALUE);
        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN a json patch WHEN patch process item task data THEN the patch is sent and the task is returned")
    public void givenAJsonPatchWhenPatchProcessItemTaskDataThenThePatchIsSentAndTheTaskIsReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.patchProcessItemTaskData(ID_1, JSON_PATCH)).willReturn(processItem);

        ProcessItemTaskDataPatchResponse response = this.kuFlowActivities.patchProcessItemTaskData(
            with(new ProcessItemTaskDataPatchRequest(), r -> {
                r.setProcessItemId(ID_1);
                r.setJsonPatch(JSON_PATCH);
            })
        );

        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN a log entry WHEN append process item task log THEN the log is sent and the task is returned")
    public void givenALogEntryWhenAppendProcessItemTaskLogThenTheLogIsSentAndTheTaskIsReturned() {
        ProcessItem processItem = new ProcessItem();
        given(this.processItemOperations.appendProcessItemTaskLog(eq(ID_1), any(ProcessItemTaskAppendLogParams.class))).willReturn(
            processItem
        );

        ProcessItemTaskLoggAppendResponse response = this.kuFlowActivities.appendProcessItemTaskLog(
            with(new ProcessItemTaskLoggAppendRequest(), r -> {
                r.setProcessItemId(ID_1);
                r.setMessage("A message");
                r.setLevel(ProcessItemTaskLogLevel.ERROR);
            })
        );

        ArgumentCaptor<ProcessItemTaskAppendLogParams> captor = ArgumentCaptor.forClass(ProcessItemTaskAppendLogParams.class);
        then(this.processItemOperations).should().appendProcessItemTaskLog(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getMessage()).isEqualTo("A message");
        assertThat(captor.getValue().getLevel()).isEqualTo(ProcessItemTaskLogLevel.ERROR);
        assertThat(response.getProcessItem()).isSameAs(processItem);
    }

    @Test
    @DisplayName("GIVEN a request id WHEN generate process item ai assistance THEN the request id is sent and the assistance is returned")
    public void givenARequestIdWhenGenerateProcessItemAiAssistanceThenTheRequestIdIsSentAndTheAssistanceIsReturned() {
        ProcessItemAiAssistance aiAssistance = new ProcessItemAiAssistance();
        given(
            this.processItemOperations.generateProcessItemAiAssistance(eq(ID_1), any(ProcessItemAiAssistanceGenerateParams.class))
        ).willReturn(aiAssistance);

        ProcessItemAiAssistanceGenerateResponse response = this.kuFlowActivities.generateProcessItemAiAssistance(
            with(new ProcessItemAiAssistanceGenerateRequest(), r -> {
                r.setProcessItemId(ID_1);
                r.setRequestId(ID_2);
            })
        );

        ArgumentCaptor<ProcessItemAiAssistanceGenerateParams> captor = ArgumentCaptor.forClass(ProcessItemAiAssistanceGenerateParams.class);
        then(this.processItemOperations).should().generateProcessItemAiAssistance(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getRequestId()).isEqualTo(ID_2);
        assertThat(response.getProcessItemAiAssistance()).isSameAs(aiAssistance);
    }

    @Test
    @DisplayName("GIVEN a process item id WHEN retrieve process item ai assistance THEN the assistance is retrieved and returned")
    public void givenAProcessItemIdWhenRetrieveProcessItemAiAssistanceThenTheAssistanceIsRetrievedAndReturned() {
        ProcessItemAiAssistance aiAssistance = new ProcessItemAiAssistance();
        given(this.processItemOperations.retrieveProcessItemAiAssistance(ID_1)).willReturn(aiAssistance);

        ProcessItemAiAssistanceRetrieveResponse response = this.kuFlowActivities.retrieveProcessItemAiAssistance(
            with(new ProcessItemAiAssistanceRetrieveRequest(), r -> r.setProcessItemId(ID_1))
        );

        assertThat(response.getProcessItemAiAssistance()).isSameAs(aiAssistance);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Business artifacts
    // ---------------------------------------------------------------------------------------------------------------

    @Test
    @DisplayName("GIVEN every filter WHEN find business artifacts THEN all the filters are mapped to the find options")
    public void givenEveryFilterWhenFindBusinessArtifactsThenAllTheFiltersAreMappedToTheFindOptions() {
        BusinessArtifactPage businessArtifactPage = new BusinessArtifactPage();
        given(this.businessArtifactOperations.findBusinessArtifacts(any(BusinessArtifactFindOptions.class))).willReturn(
            businessArtifactPage
        );

        BusinessArtifactFindRequest request = new BusinessArtifactFindRequest()
            .setSize(30)
            .setPage(2)
            .setSort("createdAt,desc")
            .setTenantId(ID_1)
            .setBusinessArtifactDefinitionId(ID_2)
            .setBusinessArtifactDefinitionCode("BAD_CODE")
            .setValues(List.of("code eq value"))
            .setDeleted(DeletedQueryParam.EXCLUDE);

        BusinessArtifactFindResponse response = this.kuFlowActivities.findBusinessArtifacts(request);

        ArgumentCaptor<BusinessArtifactFindOptions> captor = ArgumentCaptor.forClass(BusinessArtifactFindOptions.class);
        then(this.businessArtifactOperations).should().findBusinessArtifacts(captor.capture());
        BusinessArtifactFindOptions options = captor.getValue();
        assertThat(options.getSize()).isEqualTo(30);
        assertThat(options.getPage()).isEqualTo(2);
        assertThat(options.getSorts()).containsExactly("createdAt,desc");
        assertThat(options.getTenantIds()).containsExactly(ID_1);
        assertThat(options.getBusinessArtifactDefinitionIds()).containsExactly(ID_2);
        assertThat(options.getBusinessArtifactDefinitionCodes()).containsExactly("BAD_CODE");
        assertThat(options.getValues()).containsExactly("code eq value");
        assertThat(options.getDeleted()).isEqualTo(DeletedQueryParam.EXCLUDE);
        assertThat(response.getBusinessArtifacts()).isSameAs(businessArtifactPage);
    }

    @Test
    @DisplayName("GIVEN every field WHEN create business artifact THEN all the fields are sent and the business artifact is returned")
    public void givenEveryFieldWhenCreateBusinessArtifactThenAllTheFieldsAreSentAndTheBusinessArtifactIsReturned() {
        BusinessArtifact businessArtifact = new BusinessArtifact();
        given(this.businessArtifactOperations.createBusinessArtifact(any(BusinessArtifactCreateParams.class))).willReturn(businessArtifact);

        BusinessArtifactCreateResponse response = this.kuFlowActivities.createBusinessArtifact(
            with(new BusinessArtifactCreateRequest(), r -> {
                r.setId(ID_1);
                r.setBusinessArtifactDefinitionId(ID_2);
                r.setTenantId(ID_3);
                r.setBusinessArtifactDefinitionCode("BAD_CODE");
                r.setData(JSON_VALUE);
            })
        );

        ArgumentCaptor<BusinessArtifactCreateParams> captor = ArgumentCaptor.forClass(BusinessArtifactCreateParams.class);
        then(this.businessArtifactOperations).should().createBusinessArtifact(captor.capture());
        BusinessArtifactCreateParams params = captor.getValue();
        assertThat(params.getId()).isEqualTo(ID_1);
        assertThat(params.getBusinessArtifactDefinitionId()).isEqualTo(ID_2);
        assertThat(params.getTenantId()).isEqualTo(ID_3);
        assertThat(params.getBusinessArtifactDefinitionCode()).isEqualTo("BAD_CODE");
        assertThat(params.getData()).isSameAs(JSON_VALUE);
        assertThat(response.getBusinessArtifact()).isSameAs(businessArtifact);
    }

    @Test
    @DisplayName("GIVEN a business artifact id WHEN retrieve business artifact THEN the business artifact is retrieved and returned")
    public void givenABusinessArtifactIdWhenRetrieveBusinessArtifactThenTheBusinessArtifactIsRetrievedAndReturned() {
        BusinessArtifact businessArtifact = new BusinessArtifact();
        given(this.businessArtifactOperations.retrieveBusinessArtifact(ID_1)).willReturn(businessArtifact);

        BusinessArtifactRetrieveResponse response = this.kuFlowActivities.retrieveBusinessArtifact(
            with(new BusinessArtifactRetrieveRequest(), r -> r.setBusinessArtifactId(ID_1))
        );

        assertThat(response.getBusinessArtifact()).isSameAs(businessArtifact);
    }

    @Test
    @DisplayName("GIVEN a business artifact id WHEN delete business artifact THEN it is deleted and an empty response is returned")
    public void givenABusinessArtifactIdWhenDeleteBusinessArtifactThenItIsDeletedAndAnEmptyResponseIsReturned() {
        BusinessArtifactDeleteResponse response = this.kuFlowActivities.deleteBusinessArtifact(
            with(new BusinessArtifactDeleteRequest(), r -> r.setBusinessArtifactId(ID_1))
        );

        then(this.businessArtifactOperations).should().deleteBusinessArtifact(ID_1);
        assertThat(response).isNotNull();
    }

    @Test
    @DisplayName("GIVEN data WHEN update business artifact THEN the data is sent and the business artifact is returned")
    public void givenDataWhenUpdateBusinessArtifactThenTheDataIsSentAndTheBusinessArtifactIsReturned() {
        BusinessArtifact businessArtifact = new BusinessArtifact();
        given(this.businessArtifactOperations.updateBusinessArtifactData(eq(ID_1), any(BusinessArtifactDataUpdateParams.class))).willReturn(
            businessArtifact
        );

        BusinessArtifactUpdateResponse response = this.kuFlowActivities.updateBusinessArtifact(
            with(new BusinessArtifactUpdateRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setData(JSON_VALUE);
            })
        );

        ArgumentCaptor<BusinessArtifactDataUpdateParams> captor = ArgumentCaptor.forClass(BusinessArtifactDataUpdateParams.class);
        then(this.businessArtifactOperations).should().updateBusinessArtifactData(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getData()).isSameAs(JSON_VALUE);
        assertThat(response.getBusinessArtifact()).isSameAs(businessArtifact);
    }

    @Test
    @DisplayName("GIVEN a json patch WHEN patch business artifact THEN the patch is sent and the business artifact is returned")
    public void givenAJsonPatchWhenPatchBusinessArtifactThenThePatchIsSentAndTheBusinessArtifactIsReturned() {
        BusinessArtifact businessArtifact = new BusinessArtifact();
        given(this.businessArtifactOperations.patchBusinessArtifactData(ID_1, JSON_PATCH)).willReturn(businessArtifact);

        BusinessArtifactPatchResponse response = this.kuFlowActivities.patchBusinessArtifact(
            with(new BusinessArtifactPatchRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setJsonPatch(JSON_PATCH);
            })
        );

        assertThat(response.getBusinessArtifact()).isSameAs(businessArtifact);
    }

    @Test
    @DisplayName("GIVEN every action field WHEN create business artifact action THEN all the fields are sent and the action is returned")
    public void givenEveryActionFieldWhenCreateBusinessArtifactActionThenAllTheFieldsAreSentAndTheActionIsReturned() {
        BusinessArtifactAction businessArtifactAction = new BusinessArtifactAction();
        BusinessArtifactActionCreateParamsStartWorkflow startWorkflow = new BusinessArtifactActionCreateParamsStartWorkflow();
        BusinessArtifactActionCreateParamsDownloadable downloadable = new BusinessArtifactActionCreateParamsDownloadable();
        BusinessArtifactActionCreateParamsStartProcess startProcess = new BusinessArtifactActionCreateParamsStartProcess();
        BusinessArtifactActionCreateParamsCreateArtifact createArtifact = new BusinessArtifactActionCreateParamsCreateArtifact();
        given(
            this.businessArtifactOperations.createBusinessArtifactAction(eq(ID_1), any(BusinessArtifactActionCreateParams.class))
        ).willReturn(businessArtifactAction);

        BusinessArtifactActionCreateResponse response = this.kuFlowActivities.createBusinessArtifactAction(
            with(new BusinessArtifactActionCreateRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setId(ID_2);
                r.setBusinessArtifactActionDefinitionCode("ACTION_CODE");
                r.setStartWorkflow(startWorkflow);
                r.setDownloadable(downloadable);
                r.setStartProcess(startProcess);
                r.setCreateArtifact(createArtifact);
            })
        );

        ArgumentCaptor<BusinessArtifactActionCreateParams> captor = ArgumentCaptor.forClass(BusinessArtifactActionCreateParams.class);
        then(this.businessArtifactOperations).should().createBusinessArtifactAction(eq(ID_1), captor.capture());
        BusinessArtifactActionCreateParams params = captor.getValue();
        assertThat(params.getId()).isEqualTo(ID_2);
        assertThat(params.getBusinessArtifactActionDefinitionCode()).isEqualTo("ACTION_CODE");
        assertThat(params.getStartWorkflow()).isSameAs(startWorkflow);
        assertThat(params.getDownloadable()).isSameAs(downloadable);
        assertThat(params.getStartProcess()).isSameAs(startProcess);
        assertThat(params.getCreateArtifact()).isSameAs(createArtifact);
        assertThat(response.getBusinessArtifactAction()).isSameAs(businessArtifactAction);
    }

    @Test
    @DisplayName("GIVEN an action id WHEN retrieve business artifact action THEN the action is retrieved and returned")
    public void givenAnActionIdWhenRetrieveBusinessArtifactActionThenTheActionIsRetrievedAndReturned() {
        BusinessArtifactAction businessArtifactAction = new BusinessArtifactAction();
        given(this.businessArtifactOperations.retrieveBusinessArtifactAction(ID_1, ID_2)).willReturn(businessArtifactAction);

        BusinessArtifactActionRetrieveResponse response = this.kuFlowActivities.retrieveBusinessArtifactAction(
            with(new BusinessArtifactActionRetrieveRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setBusinessArtifactActionId(ID_2);
            })
        );

        assertThat(response.getBusinessArtifactAction()).isSameAs(businessArtifactAction);
    }

    @Test
    @DisplayName("GIVEN an action id WHEN cancel business artifact action THEN the action is cancelled and returned")
    public void givenAnActionIdWhenCancelBusinessArtifactActionThenTheActionIsCancelledAndReturned() {
        BusinessArtifactAction businessArtifactAction = new BusinessArtifactAction();
        given(this.businessArtifactOperations.cancelBusinessArtifactAction(ID_1, ID_2)).willReturn(businessArtifactAction);

        BusinessArtifactActionCancelResponse response = this.kuFlowActivities.cancelBusinessArtifactAction(
            with(new BusinessArtifactActionCancelRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setBusinessArtifactActionId(ID_2);
            })
        );

        assertThat(response.getBusinessArtifactAction()).isSameAs(businessArtifactAction);
    }

    @Test
    @DisplayName(
        "GIVEN an action definition code WHEN prepare create artifact action THEN the code is sent and the preparation is returned"
    )
    public void givenAnActionDefinitionCodeWhenPrepareCreateArtifactActionThenTheCodeIsSentAndThePreparationIsReturned() {
        BusinessArtifactActionCreateArtifactPrepare prepare = new BusinessArtifactActionCreateArtifactPrepare();
        given(
            this.businessArtifactOperations.prepareBusinessArtifactActionCreateArtifact(
                eq(ID_1),
                any(BusinessArtifactActionCreateArtifactPrepareParams.class)
            )
        ).willReturn(prepare);

        BusinessArtifactActionCreateArtifactPrepareResponse response = this.kuFlowActivities.prepareBusinessArtifactActionCreateArtifact(
            with(new BusinessArtifactActionCreateArtifactPrepareRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setBusinessArtifactActionDefinitionCode("ACTION_CODE");
            })
        );

        ArgumentCaptor<BusinessArtifactActionCreateArtifactPrepareParams> captor = ArgumentCaptor.forClass(
            BusinessArtifactActionCreateArtifactPrepareParams.class
        );
        then(this.businessArtifactOperations).should().prepareBusinessArtifactActionCreateArtifact(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getBusinessArtifactActionDefinitionCode()).isEqualTo("ACTION_CODE");
        assertThat(response.getBusinessArtifactActionCreateArtifactPrepare()).isSameAs(prepare);
    }

    @Test
    @DisplayName("GIVEN an action definition code WHEN prepare start process action THEN the code is sent and the preparation is returned")
    public void givenAnActionDefinitionCodeWhenPrepareStartProcessActionThenTheCodeIsSentAndThePreparationIsReturned() {
        BusinessArtifactActionStartProcessPrepare prepare = new BusinessArtifactActionStartProcessPrepare();
        given(
            this.businessArtifactOperations.prepareBusinessArtifactActionStartProcess(
                eq(ID_1),
                any(BusinessArtifactActionStartProcessPrepareParams.class)
            )
        ).willReturn(prepare);

        BusinessArtifactActionStartProcessPrepareResponse response = this.kuFlowActivities.prepareBusinessArtifactActionStartProcess(
            with(new BusinessArtifactActionStartProcessPrepareRequest(), r -> {
                r.setBusinessArtifactId(ID_1);
                r.setBusinessArtifactActionDefinitionCode("ACTION_CODE");
            })
        );

        ArgumentCaptor<BusinessArtifactActionStartProcessPrepareParams> captor = ArgumentCaptor.forClass(
            BusinessArtifactActionStartProcessPrepareParams.class
        );
        then(this.businessArtifactOperations).should().prepareBusinessArtifactActionStartProcess(eq(ID_1), captor.capture());
        assertThat(captor.getValue().getBusinessArtifactActionDefinitionCode()).isEqualTo("ACTION_CODE");
        assertThat(response.getBusinessArtifactActionStartProcessPrepare()).isSameAs(prepare);
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Failures
    // ---------------------------------------------------------------------------------------------------------------

    @ParameterizedTest(name = "{0}")
    @MethodSource("invalidRequests")
    @DisplayName("GIVEN a request without a required field WHEN the activity is invoked THEN a non retryable validation failure is thrown")
    public void givenARequestWithoutARequiredFieldWhenTheActivityIsInvokedThenANonRetryableValidationFailureIsThrown(
        String description,
        Consumer<KuFlowActivities> invocation,
        String expectedMessage
    ) {
        ApplicationFailure failure = catchThrowableOfType(() -> invocation.accept(this.kuFlowActivities), ApplicationFailure.class);

        assertThat(failure).as(description).isNotNull();
        assertThat(failure.getType()).isEqualTo(ACTIVITIES_VALIDATION_FAILURE.getType());
        assertThat(failure.getOriginalMessage()).isEqualTo(expectedMessage);
        assertThat(failure.isNonRetryable()).isTrue();
        then(this.principalOperations).shouldHaveNoInteractions();
        then(this.tenantUserOperations).shouldHaveNoInteractions();
        then(this.processOperations).shouldHaveNoInteractions();
        then(this.processItemOperations).shouldHaveNoInteractions();
        then(this.businessArtifactOperations).shouldHaveNoInteractions();
    }

    @Test
    @DisplayName("GIVEN the rest api rejects the request WHEN the activity is invoked THEN a rest failure is thrown")
    public void givenTheRestApiRejectsTheRequestWhenTheActivityIsInvokedThenARestFailureIsThrown() {
        given(this.processOperations.retrieveProcess(ID_1)).willThrow(new DefaultErrorException("Not found", null, new DefaultError()));

        ApplicationFailure failure = catchThrowableOfType(
            () -> this.kuFlowActivities.retrieveProcess(with(new ProcessRetrieveRequest(), r -> r.setProcessId(ID_1))),
            ApplicationFailure.class
        );

        assertThat(failure.getType()).isEqualTo(ACTIVITIES_REST_FAILURE.getType());
        assertThat(failure.getCause()).hasMessageContaining("Not found");
    }

    @Test
    @DisplayName("GIVEN an unexpected error WHEN the activity is invoked THEN a generic activities failure is thrown")
    public void givenAnUnexpectedErrorWhenTheActivityIsInvokedThenAGenericActivitiesFailureIsThrown() {
        given(this.processItemOperations.retrieveProcessItem(ID_1)).willThrow(new IllegalStateException("Boom"));

        ApplicationFailure failure = catchThrowableOfType(
            () -> this.kuFlowActivities.retrieveProcessItem(with(new ProcessItemRetrieveRequest(), r -> r.setProcessItemId(ID_1))),
            ApplicationFailure.class
        );

        assertThat(failure.getType()).isEqualTo(ACTIVITIES_FAILURE.getType());
        assertThat(failure.getCause()).hasMessageContaining("Boom");
    }

    private static Stream<Arguments> invalidRequests() {
        return Stream.of(
            invalid(
                "retrievePrincipal without principalId",
                a -> a.retrievePrincipal(new PrincipalRetrieveRequest()),
                "'principalId' is required"
            ),
            invalid(
                "retrieveTenantUser without tenantUserId",
                a -> a.retrieveTenantUser(new TenantUserRetrieveRequest()),
                "'tenantUserId' is required"
            ),
            invalid("retrieveProcess without processId", a -> a.retrieveProcess(new ProcessRetrieveRequest()), "'processId' is required"),
            invalid("deleteProcess without processId", a -> a.deleteProcess(new ProcessDeleteRequest()), "'processId' is required"),
            invalid(
                "updateProcessEntity without processId",
                a -> a.updateProcessEntity(with(new ProcessEntityUpdateRequest(), r -> r.setEntity(JSON_VALUE))),
                "'processId' is required"
            ),
            invalid(
                "updateProcessEntity without entity",
                a -> a.updateProcessEntity(with(new ProcessEntityUpdateRequest(), r -> r.setProcessId(ID_1))),
                "'entity' is required"
            ),
            invalid(
                "patchProcessEntity without processId",
                a -> a.patchProcessEntity(with(new ProcessEntityPatchRequest(), r -> r.setJsonPatch(JSON_PATCH))),
                "'processId' is required"
            ),
            invalid(
                "patchProcessEntity without jsonPatch",
                a -> a.patchProcessEntity(with(new ProcessEntityPatchRequest(), r -> r.setProcessId(ID_1))),
                "'jsonPatch' is required"
            ),
            invalid(
                "updateProcessMetadata without processId",
                a -> a.updateProcessMetadata(with(new ProcessMetadataUpdateRequest(), r -> r.setMetadata(JSON_VALUE))),
                "'processId' is required"
            ),
            invalid(
                "updateProcessMetadata without metadata",
                a -> a.updateProcessMetadata(with(new ProcessMetadataUpdateRequest(), r -> r.setProcessId(ID_1))),
                "'metadata' is required"
            ),
            invalid(
                "patchProcessMetadata without processId",
                a -> a.patchProcessMetadata(with(new ProcessMetadataPatchRequest(), r -> r.setJsonPatch(JSON_PATCH))),
                "'processId' is required"
            ),
            invalid(
                "patchProcessMetadata without jsonPatch",
                a -> a.patchProcessMetadata(with(new ProcessMetadataPatchRequest(), r -> r.setProcessId(ID_1))),
                "'jsonPatch' is required"
            ),
            invalid(
                "changeProcessInitiator without processId",
                a -> a.changeProcessInitiator(with(new ProcessInitiatorChangeRequest(), r -> r.setInitiatorId(ID_2))),
                "'processId' is required"
            ),
            invalid(
                "changeProcessInitiator without initiator",
                a -> a.changeProcessInitiator(with(new ProcessInitiatorChangeRequest(), r -> r.setProcessId(ID_1))),
                "'initiatorId' or 'initiatorEmail' is required"
            ),
            invalid(
                "createProcessAction without processId",
                a -> a.createProcessAction(with(new ProcessActionCreateRequest(), r -> r.setProcessActionDefinitionCode("ACTION_CODE"))),
                "'processId' is required"
            ),
            invalid(
                "createProcessAction without processActionDefinitionCode",
                a -> a.createProcessAction(with(new ProcessActionCreateRequest(), r -> r.setProcessId(ID_1))),
                "'processActionDefinitionCode' is required"
            ),
            invalid(
                "retrieveProcessAction without processId",
                a -> a.retrieveProcessAction(with(new ProcessActionRetrieveRequest(), r -> r.setProcessActionId(ID_2))),
                "'processId' is required"
            ),
            invalid(
                "retrieveProcessAction without processActionId",
                a -> a.retrieveProcessAction(with(new ProcessActionRetrieveRequest(), r -> r.setProcessId(ID_1))),
                "'processActionId' is required"
            ),
            invalid(
                "cancelProcessAction without processId",
                a -> a.cancelProcessAction(with(new ProcessActionCancelRequest(), r -> r.setProcessActionId(ID_2))),
                "'processId' is required"
            ),
            invalid(
                "cancelProcessAction without processActionId",
                a -> a.cancelProcessAction(with(new ProcessActionCancelRequest(), r -> r.setProcessId(ID_1))),
                "'processActionId' is required"
            ),
            invalid(
                "cancelProcessItems without processId",
                a -> a.cancelProcessItems(new ProcessItemsCancelRequest()),
                "'processId' is required"
            ),
            invalid(
                "retrieveProcessItem without processItemId",
                a -> a.retrieveProcessItem(new ProcessItemRetrieveRequest()),
                "'processItemId' is required"
            ),
            invalid(
                "createProcessItem without id",
                a ->
                    a.createProcessItem(
                        with(new ProcessItemCreateRequest(), r -> {
                            r.setProcessId(ID_2);
                            r.setType(ProcessItemType.TASK);
                            r.setProcessItemDefinitionCode("TASK_CODE");
                        })
                    ),
                "'id' is required"
            ),
            invalid(
                "createProcessItem without processId",
                a ->
                    a.createProcessItem(
                        with(new ProcessItemCreateRequest(), r -> {
                            r.setId(ID_1);
                            r.setType(ProcessItemType.TASK);
                            r.setProcessItemDefinitionCode("TASK_CODE");
                        })
                    ),
                "'processId' is required"
            ),
            invalid(
                "createProcessItem without type",
                a ->
                    a.createProcessItem(
                        with(new ProcessItemCreateRequest(), r -> {
                            r.setId(ID_1);
                            r.setProcessId(ID_2);
                        })
                    ),
                "'type' is required"
            ),
            invalid(
                "createProcessItem task without processItemDefinitionCode",
                a -> a.createProcessItem(processItemCreateRequest(ProcessItemType.TASK, r -> {})),
                "'processItemDefinitionCode' is required for task items"
            ),
            invalid(
                "createProcessItem message without message",
                a -> a.createProcessItem(processItemCreateRequest(ProcessItemType.MESSAGE, r -> {})),
                "'message' is required"
            ),
            invalid(
                "createProcessItem message without text nor data",
                a ->
                    a.createProcessItem(
                        processItemCreateRequest(ProcessItemType.MESSAGE, r -> r.setMessage(new ProcessItemMessageCreateParams()))
                    ),
                "'message.text' and/or 'message.data' is required for message items"
            ),
            invalid(
                "createProcessItem message with data but without dataStructureDataDefinitionCode",
                a ->
                    a.createProcessItem(
                        processItemCreateRequest(ProcessItemType.MESSAGE, r ->
                            r.setMessage(new ProcessItemMessageCreateParams().setData(JSON_VALUE))
                        )
                    ),
                "'message.dataStructureDataDefinitionCode' is required for message items when 'message.data' is set"
            ),
            invalid(
                "createProcessItem thread without processItemDefinitionCode",
                a -> a.createProcessItem(processItemCreateRequest(ProcessItemType.THREAD, r -> {})),
                "'processItemDefinitionCode' is required for thread items"
            ),
            invalid(
                "createProcessItem thread inside a thread",
                a ->
                    a.createProcessItem(
                        processItemCreateRequest(ProcessItemType.THREAD, r -> {
                            r.setProcessItemDefinitionCode("THREAD_CODE");
                            r.setProcessItemThreadId(ID_3);
                        })
                    ),
                "'processItemThreadId' is not allowed for thread items, threads cannot be nested"
            ),
            invalid(
                "completeProcessItemTask without processItemId",
                a -> a.completeProcessItemTask(new ProcessItemTaskCompleteRequest()),
                "'processItemId' is required"
            ),
            invalid(
                "claimProcessItemTask without processItemId",
                a -> a.claimProcessItemTask(new ProcessItemTaskClaimRequest()),
                "'processItemId' is required"
            ),
            invalid(
                "assignProcessItemTask without processItemId",
                a -> a.assignProcessItemTask(with(new ProcessItemTaskAssignRequest(), r -> r.setOwnerId(ID_2))),
                "'processItemId' is required"
            ),
            invalid(
                "assignProcessItemTask without owner",
                a -> a.assignProcessItemTask(with(new ProcessItemTaskAssignRequest(), r -> r.setProcessItemId(ID_1))),
                "'ownerEmail' or 'ownerId' is required"
            ),
            invalid(
                "updateProcessItemTaskData without processItemId",
                a -> a.updateProcessItemTaskData(with(new ProcessItemTaskDataUpdateRequest(), r -> r.setData(JSON_VALUE))),
                "'processItemId' is required"
            ),
            invalid(
                "updateProcessItemTaskData without data",
                a -> a.updateProcessItemTaskData(with(new ProcessItemTaskDataUpdateRequest(), r -> r.setProcessItemId(ID_1))),
                "'data' is required"
            ),
            invalid(
                "updateProcessItemTaskContextData without processItemId",
                a -> a.updateProcessItemTaskContextData(with(new ProcessItemTaskContextDataUpdateRequest(), r -> r.setData(JSON_VALUE))),
                "'processItemId' is required"
            ),
            invalid(
                "updateProcessItemTaskContextData without data",
                a -> a.updateProcessItemTaskContextData(with(new ProcessItemTaskContextDataUpdateRequest(), r -> r.setProcessItemId(ID_1))),
                "'data' is required"
            ),
            invalid(
                "patchProcessItemTaskData without processItemId",
                a -> a.patchProcessItemTaskData(with(new ProcessItemTaskDataPatchRequest(), r -> r.setJsonPatch(JSON_PATCH))),
                "'processItemId' is required"
            ),
            invalid(
                "patchProcessItemTaskData without jsonPatch",
                a -> a.patchProcessItemTaskData(with(new ProcessItemTaskDataPatchRequest(), r -> r.setProcessItemId(ID_1))),
                "'jsonPatch' is required"
            ),
            invalid(
                "appendProcessItemTaskLog without processItemId",
                a ->
                    a.appendProcessItemTaskLog(
                        with(new ProcessItemTaskLoggAppendRequest(), r -> {
                            r.setMessage("A message");
                            r.setLevel(ProcessItemTaskLogLevel.INFO);
                        })
                    ),
                "'processItemId' is required"
            ),
            invalid(
                "appendProcessItemTaskLog without level",
                a ->
                    a.appendProcessItemTaskLog(
                        with(new ProcessItemTaskLoggAppendRequest(), r -> {
                            r.setProcessItemId(ID_1);
                            r.setMessage("A message");
                        })
                    ),
                "'level' is required"
            ),
            invalid(
                "appendProcessItemTaskLog without message",
                a ->
                    a.appendProcessItemTaskLog(
                        with(new ProcessItemTaskLoggAppendRequest(), r -> {
                            r.setProcessItemId(ID_1);
                            r.setLevel(ProcessItemTaskLogLevel.INFO);
                        })
                    ),
                "'message' is required"
            ),
            invalid(
                "generateProcessItemAiAssistance without processItemId",
                a -> a.generateProcessItemAiAssistance(with(new ProcessItemAiAssistanceGenerateRequest(), r -> r.setRequestId(ID_2))),
                "'processItemId' is required"
            ),
            invalid(
                "generateProcessItemAiAssistance without requestId",
                a -> a.generateProcessItemAiAssistance(with(new ProcessItemAiAssistanceGenerateRequest(), r -> r.setProcessItemId(ID_1))),
                "'requestId' is required"
            ),
            invalid(
                "retrieveProcessItemAiAssistance without processItemId",
                a -> a.retrieveProcessItemAiAssistance(new ProcessItemAiAssistanceRetrieveRequest()),
                "'processItemId' is required"
            ),
            invalid(
                "createBusinessArtifact without definition",
                a -> a.createBusinessArtifact(with(new BusinessArtifactCreateRequest(), r -> r.setData(JSON_VALUE))),
                "'businessArtifactDefinitionId' or 'businessArtifactDefinitionCode' is required"
            ),
            invalid(
                "retrieveBusinessArtifact without businessArtifactId",
                a -> a.retrieveBusinessArtifact(new BusinessArtifactRetrieveRequest()),
                "'businessArtifactId' is required"
            ),
            invalid(
                "deleteBusinessArtifact without businessArtifactId",
                a -> a.deleteBusinessArtifact(new BusinessArtifactDeleteRequest()),
                "'businessArtifactId' is required"
            ),
            invalid(
                "updateBusinessArtifact without businessArtifactId",
                a -> a.updateBusinessArtifact(with(new BusinessArtifactUpdateRequest(), r -> r.setData(JSON_VALUE))),
                "'businessArtifactId' is required"
            ),
            invalid(
                "updateBusinessArtifact without data",
                a -> a.updateBusinessArtifact(with(new BusinessArtifactUpdateRequest(), r -> r.setBusinessArtifactId(ID_1))),
                "'data' is required"
            ),
            invalid(
                "patchBusinessArtifact without businessArtifactId",
                a -> a.patchBusinessArtifact(with(new BusinessArtifactPatchRequest(), r -> r.setJsonPatch(JSON_PATCH))),
                "'businessArtifactId' is required"
            ),
            invalid(
                "patchBusinessArtifact without jsonPatch",
                a -> a.patchBusinessArtifact(with(new BusinessArtifactPatchRequest(), r -> r.setBusinessArtifactId(ID_1))),
                "'jsonPatch' is required"
            ),
            invalid(
                "createBusinessArtifactAction without businessArtifactId",
                a ->
                    a.createBusinessArtifactAction(
                        with(new BusinessArtifactActionCreateRequest(), r -> r.setBusinessArtifactActionDefinitionCode("ACTION_CODE"))
                    ),
                "'businessArtifactId' is required"
            ),
            invalid(
                "createBusinessArtifactAction without businessArtifactActionDefinitionCode",
                a -> a.createBusinessArtifactAction(with(new BusinessArtifactActionCreateRequest(), r -> r.setBusinessArtifactId(ID_1))),
                "'businessArtifactActionDefinitionCode' is required"
            ),
            invalid(
                "retrieveBusinessArtifactAction without businessArtifactId",
                a ->
                    a.retrieveBusinessArtifactAction(
                        with(new BusinessArtifactActionRetrieveRequest(), r -> r.setBusinessArtifactActionId(ID_2))
                    ),
                "'businessArtifactId' is required"
            ),
            invalid(
                "retrieveBusinessArtifactAction without businessArtifactActionId",
                a ->
                    a.retrieveBusinessArtifactAction(with(new BusinessArtifactActionRetrieveRequest(), r -> r.setBusinessArtifactId(ID_1))),
                "'businessArtifactActionId' is required"
            ),
            invalid(
                "cancelBusinessArtifactAction without businessArtifactId",
                a ->
                    a.cancelBusinessArtifactAction(
                        with(new BusinessArtifactActionCancelRequest(), r -> r.setBusinessArtifactActionId(ID_2))
                    ),
                "'businessArtifactId' is required"
            ),
            invalid(
                "cancelBusinessArtifactAction without businessArtifactActionId",
                a -> a.cancelBusinessArtifactAction(with(new BusinessArtifactActionCancelRequest(), r -> r.setBusinessArtifactId(ID_1))),
                "'businessArtifactActionId' is required"
            ),
            invalid(
                "prepareBusinessArtifactActionCreateArtifact without businessArtifactId",
                a ->
                    a.prepareBusinessArtifactActionCreateArtifact(
                        with(new BusinessArtifactActionCreateArtifactPrepareRequest(), r ->
                            r.setBusinessArtifactActionDefinitionCode("ACTION_CODE")
                        )
                    ),
                "'businessArtifactId' is required"
            ),
            invalid(
                "prepareBusinessArtifactActionCreateArtifact without businessArtifactActionDefinitionCode",
                a ->
                    a.prepareBusinessArtifactActionCreateArtifact(
                        with(new BusinessArtifactActionCreateArtifactPrepareRequest(), r -> r.setBusinessArtifactId(ID_1))
                    ),
                "'businessArtifactActionDefinitionCode' is required"
            ),
            invalid(
                "prepareBusinessArtifactActionStartProcess without businessArtifactId",
                a ->
                    a.prepareBusinessArtifactActionStartProcess(
                        with(new BusinessArtifactActionStartProcessPrepareRequest(), r ->
                            r.setBusinessArtifactActionDefinitionCode("ACTION_CODE")
                        )
                    ),
                "'businessArtifactId' is required"
            ),
            invalid(
                "prepareBusinessArtifactActionStartProcess without businessArtifactActionDefinitionCode",
                a ->
                    a.prepareBusinessArtifactActionStartProcess(
                        with(new BusinessArtifactActionStartProcessPrepareRequest(), r -> r.setBusinessArtifactId(ID_1))
                    ),
                "'businessArtifactActionDefinitionCode' is required"
            )
        );
    }

    private static Arguments invalid(String description, Consumer<KuFlowActivities> invocation, String expectedMessage) {
        return Arguments.of(description, invocation, expectedMessage);
    }

    private static ProcessItemCreateRequest processItemCreateRequest(ProcessItemType type, Consumer<ProcessItemCreateRequest> customizer) {
        ProcessItemCreateRequest request = new ProcessItemCreateRequest();
        request.setId(ID_1);
        request.setProcessId(ID_2);
        request.setType(type);
        customizer.accept(request);

        return request;
    }

    private static <T> T with(T target, Consumer<T> customizer) {
        customizer.accept(target);

        return target;
    }
}
