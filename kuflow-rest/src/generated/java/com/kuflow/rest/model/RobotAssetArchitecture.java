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
 * Robot asset platform architecture.
 */
public final class RobotAssetArchitecture extends ExpandableStringEnum<RobotAssetArchitecture> {

    /**
     * Static value X86_32 for RobotAssetArchitecture.
     */
    @Generated
    public static final RobotAssetArchitecture X8632 = fromString("X86_32");

    /**
     * Static value X86_64 for RobotAssetArchitecture.
     */
    @Generated
    public static final RobotAssetArchitecture X8664 = fromString("X86_64");

    /**
     * Creates a new instance of RobotAssetArchitecture value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public RobotAssetArchitecture() {}

    /**
     * Creates or finds a RobotAssetArchitecture from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding RobotAssetArchitecture.
     */
    @Generated
    public static RobotAssetArchitecture fromString(String name) {
        return fromString(name, RobotAssetArchitecture.class);
    }

    /**
     * Gets known RobotAssetArchitecture values.
     *
     * @return known RobotAssetArchitecture values.
     */
    @Generated
    public static Collection<RobotAssetArchitecture> values() {
        return values(RobotAssetArchitecture.class);
    }
}
