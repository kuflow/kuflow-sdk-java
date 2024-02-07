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

public class FindTenantUsersOptions {

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
     * Filter tenant users that exists in one of group ids.
     */
    private final List<UUID> groupIds = new LinkedList<>();

    /**
     * Filter tenant users that have one of the emails.
     */
    private final List<String> emails = new LinkedList<>();

    public Integer getSize() {
        return this.size;
    }

    public FindTenantUsersOptions setSize(Integer size) {
        this.size = size;

        return this;
    }

    public Integer getPage() {
        return this.page;
    }

    public FindTenantUsersOptions setPage(Integer page) {
        this.page = page;

        return this;
    }

    public List<String> getSorts() {
        return unmodifiableList(this.sorts);
    }

    public FindTenantUsersOptions setSorts(List<String> sorts) {
        this.sorts.clear();
        if (sorts != null) {
            this.sorts.addAll(sorts);
        }

        return this;
    }

    public FindTenantUsersOptions setSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");

        return this.setSorts(List.of(sort));
    }

    public FindTenantUsersOptions addSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        if (!this.sorts.contains(sort)) {
            this.sorts.add(sort);
        }

        return this;
    }

    public FindTenantUsersOptions removeSort(String sort) {
        Objects.requireNonNull(sort, "'sort' is required");
        this.sorts.remove(sort);

        return this;
    }

    public List<UUID> getTenantIds() {
        return unmodifiableList(this.tenantIds);
    }

    public FindTenantUsersOptions setTenantIds(List<UUID> tenantIds) {
        this.tenantIds.clear();
        if (tenantIds != null) {
            this.tenantIds.addAll(tenantIds);
        }

        return this;
    }

    public FindTenantUsersOptions setTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");

        return this.setTenantIds(List.of(tenantId));
    }

    public FindTenantUsersOptions addTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        if (!this.tenantIds.contains(tenantId)) {
            this.tenantIds.add(tenantId);
        }

        return this;
    }

    public FindTenantUsersOptions removeTenantId(UUID tenantId) {
        Objects.requireNonNull(tenantId, "'tenantId' is required");
        this.tenantIds.remove(tenantId);

        return this;
    }

    public List<UUID> getGroupIds() {
        return unmodifiableList(this.groupIds);
    }

    public FindTenantUsersOptions setGroupIds(List<UUID> groupIds) {
        this.groupIds.clear();
        if (groupIds != null) {
            this.groupIds.addAll(groupIds);
        }

        return this;
    }

    public FindTenantUsersOptions setGroupId(UUID groupId) {
        Objects.requireNonNull(groupId, "'groupId' is required");

        return this.setGroupIds(List.of(groupId));
    }

    public FindTenantUsersOptions addGroupId(UUID groupId) {
        Objects.requireNonNull(groupId, "'groupId' is required");
        if (!this.groupIds.contains(groupId)) {
            this.groupIds.add(groupId);
        }
        return this;
    }

    public FindTenantUsersOptions removeGroupId(UUID groupId) {
        Objects.requireNonNull(groupId, "'groupId' is required");
        this.groupIds.remove(groupId);

        return this;
    }

    public List<String> getEmails() {
        return unmodifiableList(this.emails);
    }

    public FindTenantUsersOptions setEmails(List<String> emails) {
        this.emails.clear();
        if (emails != null) {
            this.emails.addAll(emails);
        }

        return this;
    }

    public FindTenantUsersOptions setEmail(String email) {
        Objects.requireNonNull(email, "'email' is required");

        return this.setEmails(List.of(email));
    }

    public FindTenantUsersOptions addEmail(String email) {
        Objects.requireNonNull(email, "'email' is required");
        if (!this.emails.contains(email)) {
            this.emails.add(email);
        }
        return this;
    }

    public FindTenantUsersOptions removeEmail(String email) {
        Objects.requireNonNull(email, "'email' is required");
        this.emails.remove(email);

        return this;
    }
}
