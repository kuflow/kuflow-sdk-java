/*
 * The MIT License
 * Copyright © 2021-present KuFlow S.L.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.kuflow.spring.boot.autoconfigure;

import com.azure.core.http.HttpClient;
import com.azure.core.http.policy.HttpLogOptions;
import com.azure.core.util.HttpClientOptions;
import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.rest.KuFlowRestClientBuilder;
import com.kuflow.spring.boot.autoconfigure.properties.KuFlowRestClientProperties;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(KuFlowRestClient.class)
@EnableConfigurationProperties({ KuFlowRestClientProperties.class })
public class KuFlowRestClientAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public KuFlowRestClient kuFlowRestClient(KuFlowRestClientProperties kuFlowRestClientProperties) {
        KuFlowRestClientBuilder builder = new KuFlowRestClientBuilder()
            .clientId(kuFlowRestClientProperties.getClientId())
            .clientSecret(kuFlowRestClientProperties.getClientSecret());

        if (kuFlowRestClientProperties.getEndpoint() != null) {
            builder.endpoint(kuFlowRestClientProperties.getEndpoint());
            builder.allowInsecureConnection(kuFlowRestClientProperties.getEndpoint().startsWith("http://"));
        }

        if (kuFlowRestClientProperties.getLoggerLevel() != null) {
            HttpLogOptions httpLogOptions = new HttpLogOptions();
            httpLogOptions.setLogLevel(kuFlowRestClientProperties.getLoggerLevel());
            builder.httpLogOptions(httpLogOptions);
        }

        if (kuFlowRestClientProperties.getConnectTimeout() != null || kuFlowRestClientProperties.getReadTimeout() != null) {
            HttpClientOptions httpClientOptions = new HttpClientOptions();
            httpClientOptions.setConnectTimeout(kuFlowRestClientProperties.getConnectTimeout());
            httpClientOptions.setReadTimeout(kuFlowRestClientProperties.getReadTimeout());
            HttpClient httpClient = HttpClient.createDefault(httpClientOptions);
            builder.httpClient(httpClient);
        }

        return builder.buildClient();
    }
}
