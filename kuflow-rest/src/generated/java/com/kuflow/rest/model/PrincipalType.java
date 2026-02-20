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
 * Defines values for PrincipalType.
 */
public final class PrincipalType extends ExpandableStringEnum<PrincipalType> {

    /**
     * Static value USER for PrincipalType.
     */
    @Generated
    public static final PrincipalType USER = fromString("USER");

    /**
     * Static value APPLICATION for PrincipalType.
     */
    @Generated
    public static final PrincipalType APPLICATION = fromString("APPLICATION");

    /**
     * Static value SYSTEM for PrincipalType.
     */
    @Generated
    public static final PrincipalType SYSTEM = fromString("SYSTEM");

    /**
     * Creates a new instance of PrincipalType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public PrincipalType() {}

    /**
     * Creates or finds a PrincipalType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding PrincipalType.
     */
    @Generated
    public static PrincipalType fromString(String name) {
        return fromString(name, PrincipalType.class);
    }

    /**
     * Gets known PrincipalType values.
     *
     * @return known PrincipalType values.
     */
    @Generated
    public static Collection<PrincipalType> values() {
        return values(PrincipalType.class);
    }
}
