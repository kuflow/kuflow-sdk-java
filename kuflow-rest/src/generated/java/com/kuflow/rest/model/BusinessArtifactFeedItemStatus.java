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

package com.kuflow.rest.model;

import com.azure.core.annotation.Generated;
import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Status of an item. `COMPLETED`, `FAILED` and `CANCELED` are terminal: they close the item, and a closed thread
 * closes its messages.
 */
public final class BusinessArtifactFeedItemStatus extends ExpandableStringEnum<BusinessArtifactFeedItemStatus> {

    /**
     * Static value PENDING for BusinessArtifactFeedItemStatus.
     */
    @Generated
    public static final BusinessArtifactFeedItemStatus PENDING = fromString("PENDING");

    /**
     * Static value RUNNING for BusinessArtifactFeedItemStatus.
     */
    @Generated
    public static final BusinessArtifactFeedItemStatus RUNNING = fromString("RUNNING");

    /**
     * Static value COMPLETED for BusinessArtifactFeedItemStatus.
     */
    @Generated
    public static final BusinessArtifactFeedItemStatus COMPLETED = fromString("COMPLETED");

    /**
     * Static value FAILED for BusinessArtifactFeedItemStatus.
     */
    @Generated
    public static final BusinessArtifactFeedItemStatus FAILED = fromString("FAILED");

    /**
     * Static value CANCELED for BusinessArtifactFeedItemStatus.
     */
    @Generated
    public static final BusinessArtifactFeedItemStatus CANCELED = fromString("CANCELED");

    /**
     * Creates a new instance of BusinessArtifactFeedItemStatus value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public BusinessArtifactFeedItemStatus() {}

    /**
     * Creates or finds a BusinessArtifactFeedItemStatus from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding BusinessArtifactFeedItemStatus.
     */
    @Generated
    public static BusinessArtifactFeedItemStatus fromString(String name) {
        return fromString(name, BusinessArtifactFeedItemStatus.class);
    }

    /**
     * Gets known BusinessArtifactFeedItemStatus values.
     *
     * @return known BusinessArtifactFeedItemStatus values.
     */
    @Generated
    public static Collection<BusinessArtifactFeedItemStatus> values() {
        return values(BusinessArtifactFeedItemStatus.class);
    }
}
