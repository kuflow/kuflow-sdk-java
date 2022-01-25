/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3.config;

import com.kuflow.engine.client.activity.s3.S3ActivitiesMarker;
import com.kuflow.engine.client.common.config.KuFlowCommonConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.S3ClientBuilder;

@Configuration(proxyBeanMethods = false)
@ComponentScan(basePackageClasses = { S3ActivitiesMarker.class })
@EnableConfigurationProperties({ S3ActivitiesProperties.class })
@Import(KuFlowCommonConfiguration.class)
public class S3ActivitiesConfiguration {

    private final S3ActivitiesProperties s3ActivitiesProperties;

    public S3ActivitiesConfiguration(S3ActivitiesProperties s3ActivitiesProperties) {
        this.s3ActivitiesProperties = s3ActivitiesProperties;
    }

    @Bean
    public S3Client s3Client() {
        S3ClientBuilder builder = S3Client.builder();

        if (this.s3ActivitiesProperties.getAccessKeyId() != null && this.s3ActivitiesProperties.getSecretAccessKey() != null) {
            AwsBasicCredentials credentials = AwsBasicCredentials.create(
                this.s3ActivitiesProperties.getAccessKeyId(),
                this.s3ActivitiesProperties.getSecretAccessKey()
            );
            builder.credentialsProvider(StaticCredentialsProvider.create(credentials));
        }

        if (this.s3ActivitiesProperties.getPathStyleAccessEnabled() != null) {
            builder.serviceConfiguration(serviceConfiguration ->
                serviceConfiguration.pathStyleAccessEnabled(this.s3ActivitiesProperties.getPathStyleAccessEnabled())
            );
        }

        if (this.s3ActivitiesProperties.findRegion().isPresent()) {
            builder.region(this.s3ActivitiesProperties.findRegion().get());
        }

        if (this.s3ActivitiesProperties.getEndpoint() != null) {
            builder.endpointOverride(this.s3ActivitiesProperties.getEndpoint());
        }

        return builder.build();
    }
}
