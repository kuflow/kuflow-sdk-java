/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.s3.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import org.springframework.util.Assert;

/**
 * Class used as response when copy elements from KuFlow to S3.
 */
public class CopyTaskElementFilesResponseResource extends AbstractResource {

    /**
     * Details of the copied files.
     */
    private List<CopyElementFileResource> files = new LinkedList<>();

    public List<CopyElementFileResource> getFiles() {
        return Collections.unmodifiableList(this.files);
    }

    public void setFiles(List<CopyElementFileResource> files) {
        Assert.notNull(files, "CopiedFiles is required");
        this.files.clear();
        this.files.addAll(files);
    }
}
