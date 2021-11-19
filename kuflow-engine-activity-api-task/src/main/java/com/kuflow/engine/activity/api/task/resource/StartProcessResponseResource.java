/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.activity.api.task.resource;

import com.kuflow.engine.api.resource.ProcessResource;
import com.kuflow.engine.common.resource.AbstractResource;

public class StartProcessResponseResource extends AbstractResource {

    private ProcessResource process;

    public ProcessResource getProcess() {
        return this.process;
    }

    public void setProcess(ProcessResource process) {
        this.process = process;
    }
}
