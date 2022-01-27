/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.email.config;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "kuflow.activity.email")
@Validated
public class EmailActivitiesProperties {

    private Map<String, Map<String, Object>> templateVariables = new HashMap<>();

    public void setTemplateVariables(Map<String, Map<String, Object>> templateVariables) {
        this.templateVariables = templateVariables;
    }

    public Map<String, Map<String, Object>> getTemplateVariables() {
        return Collections.unmodifiableMap(this.templateVariables);
    }
}
