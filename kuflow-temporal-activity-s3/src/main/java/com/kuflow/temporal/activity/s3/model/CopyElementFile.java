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

import java.util.UUID;

/**
 * File details copied.
 */
public class CopyElementFile {

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
