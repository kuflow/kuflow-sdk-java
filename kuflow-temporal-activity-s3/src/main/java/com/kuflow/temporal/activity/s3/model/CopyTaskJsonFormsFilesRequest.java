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
package com.kuflow.temporal.activity.s3.model;

import com.kuflow.temporal.common.model.AbstractModel;
import java.util.UUID;

/**
 * Class used to copy JSON Forms files from KuFlow to S3. If the {@link #sourceJsonFormsPropertyPath} is multivalued the activity add a suffix
 * to the bucket key, ie:
 * <pre>
 * key:
 *   a/b/c --&gt; a/b/c
 *             a/b/c_1
 * </pre>
 */
public class CopyTaskJsonFormsFilesRequest extends AbstractModel {

    private UUID sourceTaskId;

    private String sourceJsonFormsPropertyPath;

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

    public String getSourceJsonFormsPropertyPath() {
        return this.sourceJsonFormsPropertyPath;
    }

    /**
     * JSON Forms property path code from we want copy the values. <strong>Required</strong>
     *
     * @param sourceJsonFormsPropertyPath The source JSON Forms property path
     */
    public void setSourceJsonFormsPropertyPath(String sourceJsonFormsPropertyPath) {
        this.sourceJsonFormsPropertyPath = sourceJsonFormsPropertyPath;
    }

    public String getTargetBucket() {
        return this.targetBucket;
    }

    /**
     * Target bucket to copy all the related files associated to {@link #sourceJsonFormsPropertyPath}.By default, it the
     * <strong>default</strong> bucket defined in the activity configuration
     *
     * @param targetBucket The target bucket
     */
    public void setTargetBucket(String targetBucket) {
        this.targetBucket = targetBucket;
    }

    public String getTargetKey() {
        return this.targetKey;
    }

    /**
     * Target bucket to copy all the related files associated to {@link #sourceJsonFormsPropertyPath}.By default, if this is missing the
     * original element value path will be used
     *
     * @param targetKey The target key
     */
    public void setTargetKey(String targetKey) {
        this.targetKey = targetKey;
    }
}
