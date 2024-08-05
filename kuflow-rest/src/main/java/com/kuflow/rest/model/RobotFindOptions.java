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

import static java.util.Collections.unmodifiableList;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class RobotFindOptions {

    /**
     * The number of records returned within a single API call.
     */
    private Integer size;

    /**
     * The page number of the current page in the returned records, 0 is the first page.
     */
    private Integer page;

    /**
     * Sorting criteria in the format: property{,asc|desc}. Example: createdAt,desc
     * <p>Default sort order is ascending. Multiple sort criteria are supported.
     * <p>Please refer to the method description for supported properties.
     */
    private final List<String> sorts = new LinkedList<>();

    /**
     * Filter tenant users that exists in one of tenant ids.
     */
    private final List<UUID> tenantIds = new LinkedList<>();

    /**
     * Filter by the specified context.
     */
    private RobotFilterContext filterContext;

    public Integer getSize() {
        return this.size;
    }

    public RobotFindOptions setSize(Integer size) {
        this.size = size;

        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public RobotFindOptions setPage(Integer page) {
        this.page = page;

        return this;
    }

    public List<String> getSorts() {
        return unmodifiableList(this.sorts);
    }

    public RobotFindOptions setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public RobotFindOptions setSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");

        return this.setSorts(List.of(sort));
    }

    public RobotFindOptions addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public RobotFindOptions removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
    }

    public List<UUID> getTenantIds() {
        return unmodifiableList(this.tenantIds);
    }

    public RobotFindOptions setTenantIds(List<UUID> tenantIds) {
        this.tenantIds.clear();
        if (tenantIds != null) {
            this.tenantIds.addAll(tenantIds);
        }

        return this;
    }

    public RobotFindOptions setTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");

        return this.setTenantIds(List.of(tenantId));
    }

    public RobotFindOptions addTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        if (!this.tenantIds.contains(tenantId)) {
            this.tenantIds.add(tenantId);
        }

        return this;
    }

    public RobotFindOptions removeTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        this.tenantIds.remove(tenantId);

        return this;
    }

    public RobotFilterContext getFilterContext() {
        return this.filterContext;
    }

    public void setFilterContext(RobotFilterContext filterContext) {
        this.filterContext = filterContext;
    }
}
