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
import com.kuflow.temporal.common.model.AbstractModel;
import java.util.UUID;

/**
 * Replaces the content, status and progress of a Business Artifact Feed Item: a field left out is cleared. The
 * populated sub-field ({@code thread}, {@code message}) must match the type of the item.
 */
public class BusinessArtifactFeedItemUpdateRequest extends AbstractModel {

    private UUID businessArtifactFeedItemId;

    private BusinessArtifactFeedItemStatus status;

    private BusinessArtifactFeedItemProgress progress;

    private BusinessArtifactFeedItemThreadParams thread;

    private BusinessArtifactFeedItemMessageParams message;

    public UUID getBusinessArtifactFeedItemId() {
        return this.businessArtifactFeedItemId;
    }

    public void setBusinessArtifactFeedItemId(UUID businessArtifactFeedItemId) {
        this.businessArtifactFeedItemId = businessArtifactFeedItemId;
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
