/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3.config;

import java.net.URI;
import java.util.Optional;
import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;
import software.amazon.awssdk.regions.Region;

@ConfigurationProperties(prefix = "kuflow.activity.s3")
@Validated
public class S3ActivitiesProperties {

    private URI endpoint;

    private String accessKeyId;

    private String secretAccessKey;

    @NotEmpty
    private String defaultBucket;

    private Boolean pathStyleAccessEnabled;

    private String region;

    public URI getEndpoint() {
        return this.endpoint;
    }

    public void setEndpoint(URI endpoint) {
        this.endpoint = endpoint;
    }

    @Nullable
    public String getAccessKeyId() {
        return this.accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    @Nullable
    public String getSecretAccessKey() {
        return this.secretAccessKey;
    }

    public void setSecretAccessKey(String secretAccessKey) {
        this.secretAccessKey = secretAccessKey;
    }

    public String getDefaultBucket() {
        return this.defaultBucket;
    }

    public void setDefaultBucket(String defaultBucket) {
        this.defaultBucket = defaultBucket;
    }

    public Boolean getPathStyleAccessEnabled() {
        return this.pathStyleAccessEnabled;
    }

    public void setPathStyleAccessEnabled(Boolean pathStyleAccessEnabled) {
        this.pathStyleAccessEnabled = pathStyleAccessEnabled;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Optional<Region> findRegion() {
        if (this.region == null) {
            return Optional.empty();
        }

        return Optional.ofNullable(
            Region
                .regions()
                .stream()
                .filter(r -> r.metadata().id().equals(this.region.toLowerCase()))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Region %s not found", this.region)))
        );
    }
}
