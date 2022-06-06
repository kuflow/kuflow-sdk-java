/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3.resource;

import java.util.UUID;

/**
 * File details copied.
 */
public class CopyElementFileResource {

    private UUID elementValueId;

    private String elementDefinitionCode;

    /**
     * Bucket where the file was copied
     */
    private String bucket;

    /**
     * S3 Key where the file was copied
     */
    private String key;

    /**
     * Element value id copied
     *
     * @return The elementValueId
     */
    public UUID getElementValueId() {
        return this.elementValueId;
    }

    /**
     * @see #getElementValueId()
     *
     * @param elementValueId the element value id
     */
    public void setElementValueId(UUID elementValueId) {
        this.elementValueId = elementValueId;
    }

    /**
     * Element definition code related to {@link #getElementValueId()}
     *
     * @return the elementDefinitionCode
     */
    public String getElementDefinitionCode() {
        return this.elementDefinitionCode;
    }

    /**
     * @see #getElementDefinitionCode()
     *
     * @param elementDefinitionCode the element definition code
     */
    public void setElementDefinitionCode(String elementDefinitionCode) {
        this.elementDefinitionCode = elementDefinitionCode;
    }

    /**
     * Bucket where the file was copied
     *
     * @return the bucket name
     */
    public String getBucket() {
        return this.bucket;
    }

    /**
     * @see #getBucket()
     *
     * @param bucket the bucket
     */
    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    /**
     * S3 Key where the file was copied
     *
     * @return S3 key
     */
    public String getKey() {
        return this.key;
    }

    /**
     * @see #getKey()
     *
     * @param key the s3 key
     */
    public void setKey(String key) {
        this.key = key;
    }
}
