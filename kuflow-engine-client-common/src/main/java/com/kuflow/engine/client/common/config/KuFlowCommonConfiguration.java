/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.config;

import com.kuflow.engine.client.common.KuFlowCommonMarker;
import com.kuflow.engine.client.common.config.property.ClientKuFlowProperties;
import com.kuflow.engine.client.common.service.ProcessFeignApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ ClientKuFlowProperties.class })
@ComponentScan(basePackageClasses = { KuFlowCommonMarker.class })
@EnableFeignClients(basePackageClasses = { ProcessFeignApi.class })
public class KuFlowCommonConfiguration {}
