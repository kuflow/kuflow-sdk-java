/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.common.tracing;

import io.temporal.api.common.v1.Payload;
import io.temporal.common.context.ContextPropagator;
import io.temporal.common.converter.DataConverter;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.slf4j.MDC;

public class MDCContextPropagator implements ContextPropagator {

    private static final String TRACE_ID = "TRACEID";
    private static final String SPAN_ID = "SPANID";

    public String getName() {
        return this.getClass().getName();
    }

    public Object getCurrentContext() {
        Map<String, String> context = new HashMap<>();
        for (Map.Entry<String, String> entry : MDC.getCopyOfContextMap().entrySet()) {
            if (this.filterMetadata(entry.getKey())) {
                context.put(entry.getKey(), entry.getValue());
            }
        }
        return context;
    }

    @SuppressWarnings("unchecked")
    public void setCurrentContext(Object context) {
        Map<String, String> contextMap = (Map<String, String>) context;
        for (Map.Entry<String, String> entry : contextMap.entrySet()) {
            MDC.put(entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public Map<String, Payload> serializeContext(Object context) {
        Map<String, String> contextMap = (Map<String, String>) context;

        Map<String, Payload> collect = contextMap
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    e -> e.getKey(),
                    e -> {
                        return DataConverter.getDefaultInstance().toPayload(e.getValue()).get();
                    }
                )
            );

        return collect;
    }

    @Override
    public Object deserializeContext(Map<String, Payload> context) {
        Map<String, String> contextMap = context
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    e -> e.getKey(),
                    e -> DataConverter.getDefaultInstance().fromPayload(e.getValue(), String.class, String.class)
                )
            );

        return contextMap;
    }

    private boolean filterMetadata(String key) {
        if (key == null) {
            return false;
        }

        String candidate = key.toUpperCase();
        return TRACE_ID.equals(candidate) || SPAN_ID.equals(candidate);
    }
}
