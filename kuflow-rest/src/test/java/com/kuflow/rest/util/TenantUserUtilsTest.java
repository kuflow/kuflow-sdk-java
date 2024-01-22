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
package com.kuflow.rest.util;

import static org.assertj.core.api.Assertions.assertThat;

import com.kuflow.rest.model.TenantUser;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class TenantUserUtilsTest {

    @Test
    public void when_addElementValues_expect_newValuesAdded() {
        TenantUser tenantUser = new TenantUser();
        Optional<String> nameOptional = TenantUserUtils.findMetadataPropertyAsString(tenantUser, "name");
        Optional<Integer> ageOptional = TenantUserUtils.findMetadataPropertyAsInteger(tenantUser, "age");
        assertThat(nameOptional).isEmpty();
        assertThat(ageOptional).isEmpty();

        TenantUserUtils.updateJsonFormsProperty(tenantUser, "name", "value");
        TenantUserUtils.updateJsonFormsProperty(tenantUser, "age", 99);

        nameOptional = TenantUserUtils.findMetadataPropertyAsString(tenantUser, "name");
        ageOptional = TenantUserUtils.findMetadataPropertyAsInteger(tenantUser, "age");
        assertThat(nameOptional).contains("value");
        assertThat(ageOptional).contains(99);
    }
}
