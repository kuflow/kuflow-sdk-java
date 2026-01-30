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

public class BusinessArtifactFindOptions {

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
     * Filter by tenantId.
     */
    private final List<UUID> tenantIds = new LinkedList<>();

    /**
     * Filter by an array of business artifact definition ids.
     */
    private final List<UUID> businessArtifactDefinitionIds = new LinkedList<>();

    /**
     * Filter by an array of business artifact definition codes.
     */
    private final List<String> businessArtifactDefinitionCodes = new LinkedList<>();

    public Integer getSize() {
        return this.size;
    }

    public BusinessArtifactFindOptions setSize(Integer size) {
        this.size = size;

        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public BusinessArtifactFindOptions setPage(Integer page) {
        this.page = page;

        return this;
    }

    public List<String> getSorts() {
        return unmodifiableList(this.sorts);
    }

    public BusinessArtifactFindOptions setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public BusinessArtifactFindOptions setSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");

        return this.setSorts(List.of(sort));
    }

    public BusinessArtifactFindOptions addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public BusinessArtifactFindOptions removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
    }

    public List<UUID> getTenantIds() {
        return unmodifiableList(this.tenantIds);
    }

    public BusinessArtifactFindOptions setTenantIds(List<UUID> tenantIds) {
        this.tenantIds.clear();
        if (tenantIds != null) {
            this.tenantIds.addAll(tenantIds);
        }

        return this;
    }

    public BusinessArtifactFindOptions setTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");

        return this.setTenantIds(List.of(tenantId));
    }

    public BusinessArtifactFindOptions addTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        if (!this.tenantIds.contains(tenantId)) {
            this.tenantIds.add(tenantId);
        }

        return this;
    }

    public BusinessArtifactFindOptions removeTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        this.tenantIds.remove(tenantId);

        return this;
    }

    public List<UUID> getBusinessArtifactDefinitionIds() {
        return unmodifiableList(this.businessArtifactDefinitionIds);
    }

    public BusinessArtifactFindOptions setBusinessArtifactDefinitionIds(List<UUID> businessArtifactDefinitionIds) {
        this.businessArtifactDefinitionIds.clear();
        if (businessArtifactDefinitionIds != null) {
            this.businessArtifactDefinitionIds.addAll(businessArtifactDefinitionIds);
        }

        return this;
    }

    public BusinessArtifactFindOptions setBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        Objects.requireNonNull(businessArtifactDefinitionId, "'businessArtifactDefinitionId' is required");

        return this.setBusinessArtifactDefinitionIds(List.of(businessArtifactDefinitionId));
    }

    public BusinessArtifactFindOptions addBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        Objects.requireNonNull(businessArtifactDefinitionId, "'businessArtifactDefinitionId' is required");
        if (!this.businessArtifactDefinitionIds.contains(businessArtifactDefinitionId)) {
            this.businessArtifactDefinitionIds.add(businessArtifactDefinitionId);
        }

        return this;
    }

    public BusinessArtifactFindOptions removeBusinessArtifactDefinitionId(UUID businessArtifactDefinitionId) {
        Objects.requireNonNull(businessArtifactDefinitionId, "'businessArtifactDefinitionId' is required");
        this.businessArtifactDefinitionIds.remove(businessArtifactDefinitionId);

        return this;
    }

    public List<String> getBusinessArtifactDefinitionCodes() {
        return unmodifiableList(this.businessArtifactDefinitionCodes);
    }

    public BusinessArtifactFindOptions setBusinessArtifactDefinitionCodes(List<String> businessArtifactDefinitionCodes) {
        this.businessArtifactDefinitionCodes.clear();
        if (businessArtifactDefinitionCodes != null) {
            this.businessArtifactDefinitionCodes.addAll(businessArtifactDefinitionCodes);
        }

        return this;
    }

    public BusinessArtifactFindOptions setBusinessArtifactDefinitionCode(String businessArtifactDefinitionCode) {
        Objects.requireNonNull(businessArtifactDefinitionCode, "'businessArtifactDefinitionCode' is required");

        return this.setBusinessArtifactDefinitionCodes(List.of(businessArtifactDefinitionCode));
    }

    public BusinessArtifactFindOptions addBusinessArtifactDefinitionCode(String businessArtifactDefinitionCode) {
        Objects.requireNonNull(businessArtifactDefinitionCode, "'businessArtifactDefinitionCode' is required");
        if (!this.businessArtifactDefinitionCodes.contains(businessArtifactDefinitionCode)) {
            this.businessArtifactDefinitionCodes.add(businessArtifactDefinitionCode);
        }

        return this;
    }

    public BusinessArtifactFindOptions removeBusinessArtifactDefinitionCode(String businessArtifactDefinitionCode) {
        Objects.requireNonNull(businessArtifactDefinitionCode, "'businessArtifactDefinitionCode' is required");
        this.businessArtifactDefinitionCodes.remove(businessArtifactDefinitionCode);

        return this;
    }
}
