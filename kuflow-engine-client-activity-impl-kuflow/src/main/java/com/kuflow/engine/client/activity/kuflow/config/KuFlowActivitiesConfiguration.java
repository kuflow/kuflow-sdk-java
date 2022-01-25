/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.config;

import com.kuflow.engine.client.activity.kuflow.KuFlowActivitiesMarker;
import com.kuflow.engine.client.common.config.KuFlowCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = { KuFlowActivitiesMarker.class })
@Import(KuFlowCommonConfiguration.class)
public class KuFlowActivitiesConfiguration {}
