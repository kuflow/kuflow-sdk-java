/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.config;

import com.kuflow.engine.client.common.KuFlowCommonMarker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties({ KuFlowRestClientProperties.class })
@ComponentScan(basePackageClasses = { KuFlowCommonMarker.class })
public class KuFlowCommonConfiguration {}
