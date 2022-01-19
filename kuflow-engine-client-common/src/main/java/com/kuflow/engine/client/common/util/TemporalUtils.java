/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.util;

import io.temporal.activity.ActivityExecutionContext;
import java.util.Base64;

public final class TemporalUtils {

    private TemporalUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTemporalTokenAsString(ActivityExecutionContext context) {
        byte[] taskToken = context.getTaskToken();

        return (taskToken != null) ? Base64.getEncoder().encodeToString(taskToken) : null;
    }
}
