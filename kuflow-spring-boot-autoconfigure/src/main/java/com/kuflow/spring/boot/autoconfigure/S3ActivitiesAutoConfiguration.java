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

import com.kuflow.rest.KuFlowRestClient;
import com.kuflow.spring.boot.autoconfigure.properties.S3ActivitiesProperties;
import com.kuflow.temporal.activity.s3.S3Activities;
import com.kuflow.temporal.activity.s3.S3ActivitiesImpl;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@AutoConfiguration
@ConditionalOnClass(S3ActivitiesImpl.class)
@EnableConfigurationProperties(S3ActivitiesProperties.class)
public class S3ActivitiesAutoConfiguration {

    @ConditionalOnMissingBean
    @Bean
    public S3Client s3Client(S3ActivitiesProperties s3ActivitiesProperties) {
        S3ClientBuilder builder = S3Client.builder();

        if (s3ActivitiesProperties.getAccessKeyId() != null && s3ActivitiesProperties.getSecretAccessKey() != null) {
            AwsBasicCredentials credentials = AwsBasicCredentials.create(
                s3ActivitiesProperties.getAccessKeyId(),
                s3ActivitiesProperties.getSecretAccessKey()
            );
            builder.credentialsProvider(StaticCredentialsProvider.create(credentials));
        }

        if (s3ActivitiesProperties.getPathStyleAccessEnabled() != null) {
            builder.serviceConfiguration(serviceConfiguration ->
                serviceConfiguration.pathStyleAccessEnabled(s3ActivitiesProperties.getPathStyleAccessEnabled())
            );
        }

        if (s3ActivitiesProperties.findRegion().isPresent()) {
            builder.region(s3ActivitiesProperties.findRegion().get());
        }

        if (s3ActivitiesProperties.getEndpoint() != null) {
            builder.endpointOverride(s3ActivitiesProperties.getEndpoint());
        }

        return builder.build();
    }

    @Bean
    public S3Activities s3activities(KuFlowRestClient kuFlowRestClient, S3Client s3Client, S3ActivitiesProperties s3ActivitiesProperties) {
        return S3ActivitiesImpl
            .builder()
            .withKuFlowRestClient(kuFlowRestClient)
            .withS3Client(s3Client)
            .withDefaultBucket(s3ActivitiesProperties.getDefaultBucket())
            .build();
    }
}
