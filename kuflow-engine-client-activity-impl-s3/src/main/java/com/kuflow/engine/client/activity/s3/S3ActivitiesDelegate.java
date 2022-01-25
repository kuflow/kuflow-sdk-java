/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3;

import com.kuflow.engine.client.activity.s3.resource.CopyTaskElementFilesRequestResource;
import com.kuflow.engine.client.activity.s3.resource.CopyTaskElementFilesResponseResource;
import javax.annotation.Nonnull;

public class S3ActivitiesDelegate implements S3Activities {

    private final S3Activities delegate;

    public S3ActivitiesDelegate(S3Activities delegate) {
        this.delegate = delegate;
    }

    @Nonnull
    @Override
    public CopyTaskElementFilesResponseResource copyTaskElementFiles(@Nonnull CopyTaskElementFilesRequestResource request) {
        return this.delegate.copyTaskElementFiles(request);
    }
}
