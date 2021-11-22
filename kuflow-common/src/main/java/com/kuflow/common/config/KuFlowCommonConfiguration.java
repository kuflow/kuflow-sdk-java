/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.common.config;

import com.kuflow.common.KuFlowCommonMarker;
import com.kuflow.common.config.property.ClientKuFlowProperties;
import com.kuflow.common.service.ProcessFeignApi;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ ClientKuFlowProperties.class })
@ComponentScan(basePackageClasses = { KuFlowCommonMarker.class })
@EnableFeignClients(basePackageClasses = { ProcessFeignApi.class })
public class KuFlowCommonConfiguration {}
