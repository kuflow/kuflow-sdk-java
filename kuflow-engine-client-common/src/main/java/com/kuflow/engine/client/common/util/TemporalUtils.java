/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.util;

import com.kuflow.engine.client.common.error.SystemException;
import io.temporal.activity.ActivityExecutionContext;
import io.temporal.common.metadata.POJOActivityInterfaceMetadata;
import io.temporal.common.metadata.POJOActivityMethodMetadata;
import java.util.Base64;

public final class TemporalUtils {

    private TemporalUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String getTemporalTokenAsString(ActivityExecutionContext context) {
        byte[] taskToken = context.getTaskToken();

        return (taskToken != null) ? Base64.getEncoder().encodeToString(taskToken) : null;
    }

    public static String getActivityType(Class<?> activityInterface, String activityMethod) {
        POJOActivityInterfaceMetadata pojoActivityInterfaceMetadata = POJOActivityInterfaceMetadata.newInstance(activityInterface);
        POJOActivityMethodMetadata pojoActivityMethodMetadata = pojoActivityInterfaceMetadata
            .getMethodsMetadata()
            .stream()
            .filter(it -> it.getMethod().getName().equals(activityMethod))
            .findFirst()
            .orElseThrow(() -> new SystemException("activityMethod " + activityMethod + " not found"));

        return pojoActivityMethodMetadata.getActivityTypeName();
    }
}
