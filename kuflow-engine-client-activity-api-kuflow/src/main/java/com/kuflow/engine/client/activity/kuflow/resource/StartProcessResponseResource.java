/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.api.resource.ProcessResource;
import com.kuflow.engine.client.common.resource.AbstractResource;

public class StartProcessResponseResource extends AbstractResource {

    private ProcessResource process;

    public ProcessResource getProcess() {
        return this.process;
    }

    public void setProcess(ProcessResource process) {
        this.process = process;
    }
}
