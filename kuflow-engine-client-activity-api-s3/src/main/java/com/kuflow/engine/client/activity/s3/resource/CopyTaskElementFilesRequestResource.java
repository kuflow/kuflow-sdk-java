/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.UUID;

/**
 * Class used to copy elements from KuFlow to S3. If the {@link #sourceElementDefinitionCode} is multivalued the activity add a suffix
 * to the bucket key, ie:
 * <pre>
 * key:
 *   a/b/c --> a/b/c
 *             a/b/c_1
 * </pre>
 */
public class CopyTaskElementFilesRequestResource extends AbstractResource {

    private UUID sourceTaskId;

    private String sourceElementDefinitionCode;

    private String targetBucket;

    private String targetKey;

    public UUID getSourceTaskId() {
        return this.sourceTaskId;
    }

    /**
     * Source task id used. <strong>Required</strong>
     */
    public void setSourceTaskId(UUID sourceTaskId) {
        this.sourceTaskId = sourceTaskId;
    }

    public String getSourceElementDefinitionCode() {
        return this.sourceElementDefinitionCode;
    }

    /**
     * Slement definition code from we want copy the values. <strong>Required</strong>
     */
    public void setSourceElementDefinitionCode(String sourceElementDefinitionCode) {
        this.sourceElementDefinitionCode = sourceElementDefinitionCode;
    }

    public String getTargetBucket() {
        return this.targetBucket;
    }

    /**
     * Target bucket to copy all the related files associated to {@link #sourceElementDefinitionCode}.By default, it the
     * <strong>default</strong> bucket defined in the activity configuration
     */
    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    public String getTargetKey() {
        return this.targetKey;
    }

    /**
     * Target bucket to copy all the related files associated to {@link #sourceElementDefinitionCode}.By default, if this is missing the
     * original element value path will be used
     */
    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }
}
