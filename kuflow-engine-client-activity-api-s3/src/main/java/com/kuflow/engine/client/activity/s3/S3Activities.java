/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3;

import com.kuflow.engine.client.activity.s3.resource.CopyTaskElementFilesRequestResource;
import com.kuflow.engine.client.activity.s3.resource.CopyTaskElementFilesResponseResource;
import io.temporal.activity.ActivityInterface;
import io.temporal.activity.ActivityMethod;
import javax.annotation.Nonnull;

@ActivityInterface(namePrefix = "KuFlow_S3_")
public interface S3Activities {
    /**
     * Copy task element files into the target S3.
     *
     * @param request must not be {@literal null}.
     * @return the file copied
     */
    @ActivityMethod
    @Nonnull
    CopyTaskElementFilesResponseResource copyTaskElementFiles(@Nonnull CopyTaskElementFilesRequestResource request);
}
