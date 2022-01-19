/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.api.email.resource;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

public class EmailResource {

    @NotNull
    private Locale locale = Locale.getDefault();

    @NotNull
    private String to;

    @NotNull
    private String template;

    private final Map<String, Object> variables = new HashMap<>();

    public Locale getLocale() {
        return this.locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getTo() {
        return this.to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTemplate() {
        return this.template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public Map<String, Object> getVariables() {
        return Collections.unmodifiableMap(this.variables);
    }

    public void addVariables(String name, Object value) {
        Assert.notNull(name, "name is required");
        Assert.notNull(name, "value is required");

        this.variables.put(name, value);
    }
}
