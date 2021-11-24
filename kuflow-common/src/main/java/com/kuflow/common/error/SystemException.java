/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.common.error;

import org.springframework.core.NestedRuntimeException;

public class SystemException extends NestedRuntimeException {

    private static final long serialVersionUID = -5196736077043846135L;

    public SystemException(String msg) {
        super(msg);
    }

    public SystemException(String msg, Throwable ex) {
        super(msg, ex);
    }
}
