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
package com.kuflow.temporal.common.util;

import com.azure.core.http.HttpHeaderName;
import com.azure.core.http.rest.Response;
import com.azure.core.util.BinaryData;

public final class HeaderUtils {

    public static final int INDEX_NOT_FOUND = -1;

    private HeaderUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String extractContentType(Response<BinaryData> response) {
        return response.getHeaders().getValue(HttpHeaderName.CONTENT_TYPE);
    }

    public static Long extractContentLength(Response<?> response) {
        String value = response.getHeaders().getValue(HttpHeaderName.CONTENT_LENGTH);

        return value != null ? Long.parseLong(value) : null;
    }

    public static String extractFileName(Response<?> response) {
        String value = response.getHeaders().getValue(HttpHeaderName.CONTENT_DISPOSITION);

        if (value != null) {
            return substringBetween(value, "filename=\"", "\"");
        }

        return null;
    }

    private static String substringBetween(final String str, final String open, final String close) {
        if (str == null || open == null || close == null) {
            return null;
        }

        final int start = str.indexOf(open);
        if (start != INDEX_NOT_FOUND) {
            final int end = str.indexOf(close, start + open.length());
            if (end != INDEX_NOT_FOUND) {
                return str.substring(start + open.length(), end);
            }
        }

        return null;
    }
}
