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

package com.kuflow.temporal.activity.kuflow.model;

import com.kuflow.rest.model.BusinessArtifactFeedItemMessageParams;
import com.kuflow.rest.model.BusinessArtifactFeedItemProgress;
import com.kuflow.rest.model.BusinessArtifactFeedItemStatus;
import com.kuflow.rest.model.BusinessArtifactFeedItemThreadParams;
import com.kuflow.rest.model.BusinessArtifactFeedItemType;
import com.kuflow.temporal.common.model.AbstractModel;
import java.util.UUID;

public class BusinessArtifactFeedItemCreateRequest extends AbstractModel {

    private UUID id;

    private BusinessArtifactFeedItemType type;

    private UUID tenantId;

    private UUID businessArtifactDefinitionId;

    private String businessArtifactDefinitionCode;

    /**
     * ID of the thread in which the message is created; without it, the message is created at the top level of the
     * feed. Only allowed for items of type {@code MESSAGE}.
     */
    private UUID businessArtifactFeedItemThreadId;

    private BusinessArtifactFeedItemStatus status;

    private BusinessArtifactFeedItemProgress progress;

    private BusinessArtifactFeedItemThreadParams thread;

    private BusinessArtifactFeedItemMessageParams message;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public BusinessArtifactFeedItemType getType() {
        return this.type;
    }

    public void setType(BusinessArtifactFeedItemType type) {
        this.type = type;
    }

    public UUID getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(UUID tenantId) {
        this.tenantId = tenantId;
    }

    public UUID getBusinessArtifactDefinitionId() {
        return this.businessArtifactDefinitionId;
    }

    public void setBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        this.businessArtifactDefinitionId = businessArtifactDefinitionId;
    }

    public String getBusinessArtifactDefinitionCode() {
        return this.businessArtifactDefinitionCode;
    }

    public void setBusinessArtifactDefinitionCode(String businessArtifactDefinitionCode) {
        this.businessArtifactDefinitionCode = businessArtifactDefinitionCode;
    }

    public UUID getBusinessArtifactFeedItemThreadId() {
        return this.businessArtifactFeedItemThreadId;
    }

    public void setBusinessArtifactFeedItemThreadId(UUID businessArtifactFeedItemThreadId) {
        this.businessArtifactFeedItemThreadId = businessArtifactFeedItemThreadId;
    }

    public BusinessArtifactFeedItemStatus getStatus() {
        return this.status;
    }

    public void setStatus(BusinessArtifactFeedItemStatus status) {
        this.status = status;
    }

    public BusinessArtifactFeedItemProgress getProgress() {
        return this.progress;
    }

    public void setProgress(BusinessArtifactFeedItemProgress progress) {
        this.progress = progress;
    }

    public BusinessArtifactFeedItemThreadParams getThread() {
        return this.thread;
    }

    public void setThread(BusinessArtifactFeedItemThreadParams thread) {
        this.thread = thread;
    }

    public BusinessArtifactFeedItemMessageParams getMessage() {
        return this.message;
    }

    public void setMessage(BusinessArtifactFeedItemMessageParams message) {
        this.message = message;
    }
}
