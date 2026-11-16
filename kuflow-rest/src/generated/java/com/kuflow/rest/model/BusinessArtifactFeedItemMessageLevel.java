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
 * Level of a message, `INFO` when not given.
 */
public final class BusinessArtifactFeedItemMessageLevel extends ExpandableStringEnum<BusinessArtifactFeedItemMessageLevel> {

    /**
     * Static value INFO for BusinessArtifactFeedItemMessageLevel.
     */
    @Generated
    public static final BusinessArtifactFeedItemMessageLevel INFO = fromString("INFO");

    /**
     * Static value WARN for BusinessArtifactFeedItemMessageLevel.
     */
    @Generated
    public static final BusinessArtifactFeedItemMessageLevel WARN = fromString("WARN");

    /**
     * Static value ERROR for BusinessArtifactFeedItemMessageLevel.
     */
    @Generated
    public static final BusinessArtifactFeedItemMessageLevel ERROR = fromString("ERROR");

    /**
     * Creates a new instance of BusinessArtifactFeedItemMessageLevel value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public BusinessArtifactFeedItemMessageLevel() {}

    /**
     * Creates or finds a BusinessArtifactFeedItemMessageLevel from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding BusinessArtifactFeedItemMessageLevel.
     */
    @Generated
    public static BusinessArtifactFeedItemMessageLevel fromString(String name) {
        return fromString(name, BusinessArtifactFeedItemMessageLevel.class);
    }

    /**
     * Gets known BusinessArtifactFeedItemMessageLevel values.
     *
     * @return known BusinessArtifactFeedItemMessageLevel values.
     */
    @Generated
    public static Collection<BusinessArtifactFeedItemMessageLevel> values() {
        return values(BusinessArtifactFeedItemMessageLevel.class);
    }
}
