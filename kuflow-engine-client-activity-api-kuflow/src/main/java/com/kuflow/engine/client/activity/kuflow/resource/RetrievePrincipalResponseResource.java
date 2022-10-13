/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.PrincipalResource;

public class RetrievePrincipalResponseResource extends AbstractResource {

    private PrincipalResource principal;

    public PrincipalResource getPrincipal() {
        return this.principal;
    }

    public void setPrincipal(PrincipalResource principal) {
        this.principal = principal;
    }
}
