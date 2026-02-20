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
 * Robot filter context:
 * * READY: filters out robots ready for execution for the current credentials
 * * DEFAULT: filters out robots accessible for the current credentials (if no set this is the default option).
 */
public final class RobotFilterContext extends ExpandableStringEnum<RobotFilterContext> {

    /**
     * Static value READY for RobotFilterContext.
     */
    @Generated
    public static final RobotFilterContext READY = fromString("READY");

    /**
     * Static value DEFAULT for RobotFilterContext.
     */
    @Generated
    public static final RobotFilterContext DEFAULT = fromString("DEFAULT");

    /**
     * Creates a new instance of RobotFilterContext value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public RobotFilterContext() {}

    /**
     * Creates or finds a RobotFilterContext from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding RobotFilterContext.
     */
    @Generated
    public static RobotFilterContext fromString(String name) {
        return fromString(name, RobotFilterContext.class);
    }

    /**
     * Gets known RobotFilterContext values.
     *
     * @return known RobotFilterContext values.
     */
    @Generated
    public static Collection<RobotFilterContext> values() {
        return values(RobotFilterContext.class);
    }
}
