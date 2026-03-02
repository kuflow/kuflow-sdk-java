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

package com.kuflow.temporal.activity.kuflow.util;

import static com.kuflow.temporal.activity.kuflow.KuFlowFailureType.ACTIVITIES_VALIDATION_FAILURE;

import com.kuflow.rest.model.ProcessItemType;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.BusinessArtifactUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.PrincipalRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessEntityUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessInitiatorChangeRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemCreateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskAssignRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskClaimRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskCompleteRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskDataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessItemTaskLoggAppendRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataPatchRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessMetadataUpdateRequest;
import com.kuflow.temporal.activity.kuflow.model.ProcessRetrieveRequest;
import com.kuflow.temporal.activity.kuflow.model.TenantUserRetrieveRequest;
import io.temporal.failure.ApplicationFailure;

public class KuFlowActivitiesValidation {

    public static void validateBusinessArtifactRetrieveRequest(BusinessArtifactRetrieveRequest request) {
        if (request.getBusinessArtifactId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'businessArtifactId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateBusinessArtifactUpdateRequest(BusinessArtifactUpdateRequest request) {
        if (request.getBusinessArtifactId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'businessArtifactId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
        if (request.getData() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'data' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateBusinessArtifactPatchRequest(BusinessArtifactPatchRequest request) {
        if (request.getBusinessArtifactId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'businessArtifactId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validatePrincipalRetrieveRequest(PrincipalRetrieveRequest request) {
        if (request.getPrincipalId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'principalId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateTenantUserRetrieveRequest(TenantUserRetrieveRequest request) {
        if (request.getTenantUserId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'tenantUserId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessRetrieveRequest(ProcessRetrieveRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessEntityUpdateRequest(ProcessEntityUpdateRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
        if (request.getEntity() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'entity' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessEntityPatchRequest(ProcessEntityPatchRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessMetadataUpdateRequest(ProcessMetadataUpdateRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
        if (request.getMetadata() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'metadata' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessMetadataPatchRequest(ProcessMetadataPatchRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessInitiatorChangeRequest(ProcessInitiatorChangeRequest request) {
        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }

        if (request.getInitiatorId() == null && request.getInitiatorEmail() == null) {
            throw ApplicationFailure.newNonRetryableFailure(
                "'initiatorId' or 'initiatorEmail' is required",
                ACTIVITIES_VALIDATION_FAILURE.getType()
            );
        }
    }

    public static void validateProcessItemRetrieve(ProcessItemRetrieveRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessItemCreateRequest(ProcessItemCreateRequest request) {
        if (request.getId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'id' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }

        if (request.getProcessId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }

        if (request.getType() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'type' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }

        if (ProcessItemType.TASK.equals(request.getType())) {
            if (request.getProcessItemDefinitionCode() == null) {
                throw ApplicationFailure.newNonRetryableFailure(
                    "'processItemDefinitionCode' is required for task items",
                    ACTIVITIES_VALIDATION_FAILURE.getType()
                );
            }
        }

        if (ProcessItemType.MESSAGE.equals(request.getType())) {
            if (request.getMessage() == null) {
                throw ApplicationFailure.newNonRetryableFailure("'message' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
            }
            if (request.getMessage().getText() == null && request.getMessage().getData() == null) {
                throw ApplicationFailure.newNonRetryableFailure(
                    "'message.text' and/or 'message.data' is required for message items",
                    ACTIVITIES_VALIDATION_FAILURE.getType()
                );
            }
            if (request.getMessage().getData() != null && request.getMessage().getDataStructureDataDefinitionCode() == null) {
                throw ApplicationFailure.newNonRetryableFailure(
                    "'message.dataStructureDataDefinitionCode' is required for message items when 'message.data' is set",
                    ACTIVITIES_VALIDATION_FAILURE.getType()
                );
            }
        }

        if (ProcessItemType.THREAD.equals(request.getType())) {
            if (request.getProcessItemDefinitionCode() == null) {
                throw ApplicationFailure.newNonRetryableFailure(
                    "'processItemDefinitionCode' is required for thread items",
                    ACTIVITIES_VALIDATION_FAILURE.getType()
                );
            }
        }
    }

    public static void validateProcessItemTaskCompleteRequest(ProcessItemTaskCompleteRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateClaimTaskRequest(ProcessItemTaskClaimRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessItemTaskAssignRequest(ProcessItemTaskAssignRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
        if (request.getOwnerEmail() == null && request.getOwnerId() == null) {
            throw ApplicationFailure.newNonRetryableFailure(
                "'email' or 'principalId' is required",
                ACTIVITIES_VALIDATION_FAILURE.getType()
            );
        }
    }

    public static void validateProcessItemTaskDataUpdateRequest(ProcessItemTaskDataUpdateRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessItemTaskDataPatchRequest(ProcessItemTaskDataPatchRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }

    public static void validateProcessItemTaskLoggAppendRequest(ProcessItemTaskLoggAppendRequest request) {
        if (request.getProcessItemId() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'processItemId' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
        if (request.getLevel() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'level' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
        if (request.getMessage() == null) {
            throw ApplicationFailure.newNonRetryableFailure("'message' is required", ACTIVITIES_VALIDATION_FAILURE.getType());
        }
    }
}
