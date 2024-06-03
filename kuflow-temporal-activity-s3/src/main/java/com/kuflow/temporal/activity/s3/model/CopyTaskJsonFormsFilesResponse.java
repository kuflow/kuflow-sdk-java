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
package com.kuflow.temporal.activity.s3.model;

import static java.util.Collections.unmodifiableList;

import com.kuflow.temporal.common.model.AbstractModel;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * Class used as response when copy JSON Forms files from KuFlow to S3.
 */
public class CopyTaskJsonFormsFilesResponse extends AbstractModel {

    /**
     * Details of the copied files.
     */
    private final List<CopyJsonFormsFile> files = new LinkedList<>();

    public List<CopyJsonFormsFile> getFiles() {
        return unmodifiableList(this.files);
    }

    public void setFiles(List<CopyJsonFormsFile> files) {
        Objects.requireNonNull(files, "'files' is required");

        this.files.clear();
        this.files.addAll(files);
    }
}
