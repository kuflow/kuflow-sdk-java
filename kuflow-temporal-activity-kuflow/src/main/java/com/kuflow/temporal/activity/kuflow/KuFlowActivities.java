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

import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactPatchResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactRetrieveResponse;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactUpdateResponse;
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
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import javax.annotation.Nonnull;

/**
 * KuFlow activities to be used in Temporal.
 *
 */
@ActivityInterface(namePrefix = "KuFlow_Engine_")
public interface KuFlowActivities {
    /**
     * Retrieve a Principal.
     *
     * @param request must not be {@literal null}.
     * @return principal
     */
    @ActivityMethod
    @Nonnull
    PrincipalRetrieveResponse retrievePrincipal(@Nonnull PrincipalRetrieveRequest request);

    /**
     * Retrieve a Tenant User.
     *
     * @param request must not be {@literal null}.
     * @return tenant user
     */
    @ActivityMethod
    @Nonnull
    TenantUserRetrieveResponse retrieveTenantUser(@Nonnull TenantUserRetrieveRequest request);

    /**
     * Find all accessible Processes
     *
     * @param request must not be {@literal null}.
     * @return processes
     */
    @ActivityMethod
    @Nonnull
    ProcessFindResponse findProcesses(ProcessFindRequest request);

    /**
     * Retrieve a Process.
     *
     * @param request must not be {@literal null}.
     * @return process
     */
    @ActivityMethod
    @Nonnull
    ProcessRetrieveResponse retrieveProcess(@Nonnull ProcessRetrieveRequest request);

    /**
     * Update a Process Entity.
     *
     * @param request must not be {@literal null}.
     * @return process
     */
    @ActivityMethod
    @Nonnull
    ProcessEntityUpdateResponse updateProcessEntity(@Nonnull ProcessEntityUpdateRequest request);

    /**
     * Patch a Process Entity.
     *
     * @param request must not be {@literal null}.
     * @return process
     */
    @ActivityMethod
    @Nonnull
    ProcessEntityPatchResponse patchProcessEntity(@Nonnull ProcessEntityPatchRequest request);

    /**
     * Update a Process Metadata.
     *
     * @param request must not be {@literal null}.
     * @return process
     */
    @ActivityMethod
    @Nonnull
    ProcessMetadataUpdateResponse updateProcessMetadata(@Nonnull ProcessMetadataUpdateRequest request);

    /**
     * Patch a Process Metadata.
     *
     * @param request must not be {@literal null}.
     * @return process
     */
    @ActivityMethod
    @Nonnull
    ProcessMetadataPatchResponse patchProcessMetadata(@Nonnull ProcessMetadataPatchRequest request);

    /**
     * Change the current initiator of a process. Allows you to choose a user (by email or principal
     * identifier) or an application (principal identifier).
     *
     * @param request must not be {@literal null}.
     * @return task assigned
     */
    @ActivityMethod
    @Nonnull
    ProcessInitiatorChangeResponse changeProcessInitiator(@Nonnull ProcessInitiatorChangeRequest request);

    /**
     * List all the Process Items that have been created and the credentials has access.
     *
     * @param request must not be {@literal null}.
     * @return Processes found paginated
     */
    @ActivityMethod
    @Nonnull
    ProcessItemFindResponse findProcessItems(ProcessItemFindRequest request);

    /**
     * Retrieve a Process Item Task.
     *
     * @param request must not be {@literal null}.
     * @return process completed
     */
    @ActivityMethod
    @Nonnull
    ProcessItemRetrieveResponse retrieveProcessItem(@Nonnull ProcessItemRetrieveRequest request);

    /**
     * Create a Process Item.
     *
     * @param request must not be {@literal null}.
     * @return task created
     */
    @ActivityMethod
    @Nonnull
    ProcessItemCreateResponse createProcessItem(@Nonnull ProcessItemCreateRequest request);

    /**
     * Complete a Process Item Task.
     *
     * <p>Allow to complete a claimed task by the principal.
     *
     * @param request must not be {@literal null}.
     * @return process item with task completed
     */
    @ActivityMethod
    @Nonnull
    ProcessItemTaskCompleteResponse completeProcessItemTask(@Nonnull ProcessItemTaskCompleteRequest request);

    /**
     * Claim a Process Item Task.
     *
     * @param request must not be {@literal null}.
     * @return process item with task claimed
     */
    @ActivityMethod
    @Nonnull
    ProcessItemTaskClaimResponse claimProcessItemTask(@Nonnull ProcessItemTaskClaimRequest request);

    /**
     * Assign a Process Item Task to a user or application using their email or principalId
     *
     * @param request must not be {@literal null}.
     * @return process item with task assigned
     */
    @ActivityMethod
    @Nonnull
    ProcessItemTaskAssignResponse assignProcessItemTask(@Nonnull ProcessItemTaskAssignRequest request);

    /**
     * Save JSON data
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param request must not be {@literal null}.
     * @return process item with task updated
     */
    @ActivityMethod
    @Nonnull
    ProcessItemTaskDataUpdateResponse updateProcessItemTaskData(@Nonnull ProcessItemTaskDataUpdateRequest request);

    /**
     * Patch JSON data
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param request must not be {@literal null}.
     * @return process item with task updated
     */
    @ActivityMethod
    @Nonnull
    ProcessItemTaskDataPatchResponse patchProcessItemTaskData(@Nonnull ProcessItemTaskDataPatchRequest request);

    /**
     * Append a log to the Process Item Task.
     *
     * <p>A log entry is added to the task. If the number of log entries is reached, the oldest log entry is removed.
     *
     * @param request must not be {@literal null}.
     * @return process item with task log appended
     */
    @ActivityMethod
    @Nonnull
    ProcessItemTaskLoggAppendResponse appendProcessItemTaskLog(@Nonnull ProcessItemTaskLoggAppendRequest request);

    /**
     * Retrieve a Business Artifact.
     *
     * @param request must not be {@literal null}.
     * @return business artifact
     */
    @ActivityMethod
    @Nonnull
    BusinessArtifactRetrieveResponse retrieveBusinessArtifact(@Nonnull BusinessArtifactRetrieveRequest request);

    /**
     * Update a Business Artifact data.
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param request must not be {@literal null}.
     * @return business artifact updated
     */
    @ActivityMethod
    @Nonnull
    BusinessArtifactUpdateResponse updateBusinessArtifact(@Nonnull BusinessArtifactUpdateRequest request);

    /**
     * Patch a Business Artifact data.
     *
     * <p>Allow to save a JSON data validating that the data follow the related schema. If the data is invalid, then the
     * json form is marked as invalid.
     *
     * @param request must not be {@literal null}.
     * @return business artifact patched
     */
    @ActivityMethod
    @Nonnull
    BusinessArtifactPatchResponse patchBusinessArtifact(@Nonnull BusinessArtifactPatchRequest request);
}
