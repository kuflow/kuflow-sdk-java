/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.common.resource;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import org.springframework.util.Assert;

public abstract class AbstractResource {

    private Map<String, Serializable> payloads = new HashMap<>();

    public Map<String, Serializable> getPayloads() {
        return Collections.unmodifiableMap(this.payloads);
    }

    public void setPayload(Map<String, Serializable> payloads) {
        Assert.notNull(payloads, "payloads is required");
        this.payloads.clear();
        this.payloads.putAll(payloads);
    }

    public void putPayload(String name, Serializable value) {
        Assert.notNull(name, "name is required");
        Assert.notNull(value, "value is required");
        this.payloads.put(name, value);
    }

    public <T> T getPayload(String name, Class<T> clazz) {
        return this.getPayloadOptional(name, clazz).orElseThrow();
    }

    public <T> Optional<T> getPayloadOptional(String name, Class<T> clazz) {
        if (!this.payloads.containsKey(name)) {
            return Optional.empty();
        }

        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(this.payloads.get(name));
        T value = gson.fromJson(jsonElement, clazz);

        return Optional.of(value);
    }
}
