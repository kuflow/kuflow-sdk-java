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
 * Type of a Business Artifact action.
 */
public final class BusinessArtifactActionType extends ExpandableStringEnum<BusinessArtifactActionType> {

    /**
     * Static value START_WORKFLOW for BusinessArtifactActionType.
     */
    @Generated
    public static final BusinessArtifactActionType START_WORKFLOW = fromString("START_WORKFLOW");

    /**
     * Static value START_PROCESS for BusinessArtifactActionType.
     */
    @Generated
    public static final BusinessArtifactActionType START_PROCESS = fromString("START_PROCESS");

    /**
     * Static value DOWNLOADABLE for BusinessArtifactActionType.
     */
    @Generated
    public static final BusinessArtifactActionType DOWNLOADABLE = fromString("DOWNLOADABLE");

    /**
     * Static value CREATE_BUSINESS_ARTIFACT for BusinessArtifactActionType.
     */
    @Generated
    public static final BusinessArtifactActionType CREATE_BUSINESS_ARTIFACT = fromString("CREATE_BUSINESS_ARTIFACT");

    /**
     * Creates a new instance of BusinessArtifactActionType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Generated
    @Deprecated
    public BusinessArtifactActionType() {}

    /**
     * Creates or finds a BusinessArtifactActionType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding BusinessArtifactActionType.
     */
    @Generated
    public static BusinessArtifactActionType fromString(String name) {
        return fromString(name, BusinessArtifactActionType.class);
    }

    /**
     * Gets known BusinessArtifactActionType values.
     *
     * @return known BusinessArtifactActionType values.
     */
    @Generated
    public static Collection<BusinessArtifactActionType> values() {
        return values(BusinessArtifactActionType.class);
    }
}
