/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import com.kuflow.rest.client.resource.ProcessPageResource;

public class FindProcesseResponseResource extends AbstractResource {

    private ProcessPageResource processPage;

    public ProcessPageResource getProcessPage() {
        return this.processPage;
    }

    public void setProcessPage(ProcessPageResource processPage) {
        this.processPage = processPage;
    }
}
