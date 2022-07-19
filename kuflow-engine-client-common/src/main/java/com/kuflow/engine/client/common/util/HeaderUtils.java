/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.util;

import feign.Response;
import java.util.Collection;

public final class HeaderUtils {

    public static final int INDEX_NOT_FOUND = -1;

    private static final String CONTENT_TYPE = "Content-Type";

    private static final String CONTENT_LENGTH = "Content-Length";

    private static final String CONTENT_DISPOSITION = "Content-Disposition";

    private HeaderUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String extractContentType(Response response) {
        Collection<String> values = response.headers().get(CONTENT_TYPE);

        return values != null ? values.iterator().next() : null;
    }

    public static Long extractContentLength(Response response) {
        Collection<String> values = response.headers().get(CONTENT_LENGTH);

        return values != null ? Long.parseLong(values.iterator().next()) : null;
    }

    public static String extractFileName(Response response) {
        Collection<String> values = response.headers().get(CONTENT_DISPOSITION);

        if (values != null) {
            String value = values.iterator().next();
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
