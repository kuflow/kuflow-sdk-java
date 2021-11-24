/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.activity.impl.task.config;

import com.kuflow.activity.impl.task.KuFlowActivityTaskMarker;
import com.kuflow.common.config.KuFlowCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = { KuFlowActivityTaskMarker.class })
@Import(KuFlowCommonConfiguration.class)
public class KuFlowActivityTaskConfiguration {}
