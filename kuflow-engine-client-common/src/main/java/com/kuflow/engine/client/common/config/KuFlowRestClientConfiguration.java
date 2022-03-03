/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.config;

import com.kuflow.rest.client.KuFlowRestClient;
import com.kuflow.rest.client.controller.AuthenticationApi;
import com.kuflow.rest.client.controller.ProcessApi;
import com.kuflow.rest.client.controller.TaskApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class KuFlowRestClientConfiguration {

    private final KuFlowRestClient kuFlowRestClient;

    public KuFlowRestClientConfiguration(KuFlowRestClientProperties kuflowRestClientProperties) {
        com.kuflow.rest.client.KuFlowRestClientProperties properties = new com.kuflow.rest.client.KuFlowRestClientProperties();
        properties.setEndpoint(kuflowRestClientProperties.getEndpoint());
        properties.setApplicationId(kuflowRestClientProperties.getApplicationId());
        properties.setToken(kuflowRestClientProperties.getToken());
        properties.setConnectTimeoutMillis(kuflowRestClientProperties.getConnectTimeoutMillis());
        properties.setReadTimeoutMillis(kuflowRestClientProperties.getReadTimeoutMillis());
        properties.setLoggerLevel(kuflowRestClientProperties.getLoggerLevel());

        this.kuFlowRestClient = new KuFlowRestClient(properties);
    }

    @Bean
    public AuthenticationApi kuflowRestClientAuthenticationApi() {
        return this.kuFlowRestClient.getAuthenticationApi();
    }

    @Bean
    public ProcessApi kuflowRestClientProcessApi() {
        return this.kuFlowRestClient.getProcessApi();
    }

    @Bean
    public TaskApi kuflowRestClientTaskApi() {
        return this.kuFlowRestClient.getTaskApi();
    }
}
