/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.common.service;

import com.kuflow.api.controller.AuthenticationApi;
import com.kuflow.common.config.KuFlowFeignConfiguration;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "kuFlowAuthenticationApi", url = "${kuflow.activity.kuflow.endpoint}", configuration = KuFlowFeignConfiguration.class)
public interface AuthenticationFeignApi extends AuthenticationApi {}
