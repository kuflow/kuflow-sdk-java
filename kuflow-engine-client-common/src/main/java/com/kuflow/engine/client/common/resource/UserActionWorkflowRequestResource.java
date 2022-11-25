/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.resource;

import java.util.UUID;

public class UserActionWorkflowRequestResource extends AbstractResource {

    private UUID processId;

    private String userActionDefinitionCode;

    private UUID userActionId;

    private UUID requestorPrincipalId;

    public UUID getProcessId() {
        return this.processId;
    }

    public void setProcessId(UUID processId) {
        this.processId = processId;
    }

    public String getUserActionDefinitionCode() {
        return this.userActionDefinitionCode;
    }

    public void setUserActionDefinitionCode(String userActionDefinitionCode) {
        this.userActionDefinitionCode = userActionDefinitionCode;
    }

    public UUID getUserActionId() {
        return this.userActionId;
    }

    public void setUserActionId(UUID userActionId) {
        this.userActionId = userActionId;
    }

    public UUID getRequestorPrincipalId() {
        return this.requestorPrincipalId;
    }

    public void setRequestorPrincipalId(UUID requestorPrincipalId) {
        this.requestorPrincipalId = requestorPrincipalId;
    }
}
