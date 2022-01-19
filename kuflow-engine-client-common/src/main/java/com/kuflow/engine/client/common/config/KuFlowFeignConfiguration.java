/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.engine.client.common.config.property.ClientKuFlowProperties;
import com.kuflow.engine.client.common.feign.KuFlowFormEncoder;
import feign.Contract;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Encoder;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringEncoder;
import org.springframework.context.annotation.Bean;

public class KuFlowFeignConfiguration {

    private final ClientKuFlowProperties clientKuflowProperties;

    public KuFlowFeignConfiguration(ClientKuFlowProperties clientKuflowProperties) {
        this.clientKuflowProperties = clientKuflowProperties;
    }

    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor(this.clientKuflowProperties.getApplicationId(), this.clientKuflowProperties.getToken());
    }

    @Bean
    public Encoder multipartFormEncoder(ObjectMapper objectMapper, ObjectFactory<HttpMessageConverters> messageConverters) {
        return new KuFlowFormEncoder(objectMapper, new SpringEncoder(messageConverters));
    }
}
