/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.impl.email.config;

import com.kuflow.engine.client.activity.impl.email.KuFlowActivityEmailMarker;
import com.kuflow.engine.client.common.config.KuFlowCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = { KuFlowActivityEmailMarker.class })
@Import(KuFlowCommonConfiguration.class)
public class KuFlowActivityEmailConfiguration {}
