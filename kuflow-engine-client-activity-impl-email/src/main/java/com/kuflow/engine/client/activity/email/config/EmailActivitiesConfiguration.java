/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.email.config;

import com.kuflow.engine.client.activity.email.EmailActivitiesMarker;
import com.kuflow.engine.client.common.config.KuFlowCommonConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = { EmailActivitiesMarker.class })
@Import(KuFlowCommonConfiguration.class)
public class EmailActivitiesConfiguration {}
