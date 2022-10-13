/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

public class RetrievePrincipalRequestResource extends AbstractResource {

    private UUID principalId;

    public UUID getPrincipalId() {
        return this.principalId;
    }

    public void setPrincipalId(UUID principalId) {
        this.principalId = principalId;
    }
}
