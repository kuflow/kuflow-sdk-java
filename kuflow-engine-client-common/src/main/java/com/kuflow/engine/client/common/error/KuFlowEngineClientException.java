/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.error;

public class KuFlowEngineClientException extends RuntimeException {

    private static final long serialVersionUID = -5196736077043846135L;

    public KuFlowEngineClientException(String msg) {
        super(msg);
    }

    public KuFlowEngineClientException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
