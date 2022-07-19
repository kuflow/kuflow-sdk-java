/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.kuflow.resource;

import com.kuflow.engine.client.common.resource.AbstractResource;
import java.util.Collections;
import java.util.List;
import org.springframework.util.Assert;

public class FindProcessesRequestResource extends AbstractResource {

    private Integer page;

    private Integer size;

    private List<String> sort;

    public Integer getPage() {
        return this.page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return this.size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public List<String> getSort() {
        return Collections.unmodifiableList(this.sort);
    }

    public void setSort(List<String> sort) {
        Assert.notNull(sort, "Sort is required");
        this.sort.clear();
        this.sort.addAll(sort);
    }
}
