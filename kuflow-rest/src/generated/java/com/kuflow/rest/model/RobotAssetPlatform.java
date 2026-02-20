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
 * Robot asset platform.
 */
public final class RobotAssetPlatform extends ExpandableStringEnum<RobotAssetPlatform> {

    /**
     * Static value WINDOWS for RobotAssetPlatform.
     */
    @Generated
    public static final RobotAssetPlatform WINDOWS = fromString("WINDOWS");

    /**
     * Static value MAC_OS for RobotAssetPlatform.
     */
    @Generated
    public static final RobotAssetPlatform MACOS = fromString("MAC_OS");

    /**
     * Static value LINUX for RobotAssetPlatform.
     */
    @Generated
    public static final RobotAssetPlatform LINUX = fromString("LINUX");

    /**
     * Creates a new instance of RobotAssetPlatform value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public RobotAssetPlatform() {}

    /**
     * Creates or finds a RobotAssetPlatform from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding RobotAssetPlatform.
     */
    @Generated
    public static RobotAssetPlatform fromString(String name) {
        return fromString(name, RobotAssetPlatform.class);
    }

    /**
     * Gets known RobotAssetPlatform values.
     *
     * @return known RobotAssetPlatform values.
     */
    @Generated
    public static Collection<RobotAssetPlatform> values() {
        return values(RobotAssetPlatform.class);
    }
}
