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

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Helpers for building "value"/"metadata" search-criteria filter expressions
 * ({@code "code operation value1 value2..."}) used by the KuFlow REST API's search/sort query parameters
 * (e.g. {@code BusinessArtifactFindOptions}, {@code ProcessFindOptions}).
 */
public final class SearchCriteriaUtils {

    private SearchCriteriaUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Builds a "code operation value1 value2..." filter expression.
     *
     * <p>The server splits each expression on spaces, then decodes each resulting token (code, operation,
     * each value) individually. So {@code code}, {@code operation} and each value are percent-encoded
     * individually here (space becomes {@code %20}) before joining them with the literal space separator,
     * so that a space or any other reserved character inside one of them doesn't collide with the
     * separator. The HTTP client is responsible for percent-encoding the resulting expression as a whole
     * when it places it on the wire as a query-parameter value; this method must not do that itself.
     *
     * @param code the field code to filter/sort by
     * @param operation the operation code, e.g. "eq", "le", "ge", "between", "contains", "in"
     * @param values one or more values for the operation
     * @return the encoded filter expression, ready to be passed as a "value"/"metadata" query parameter
     */
    public static String encodeFilterExpression(String code, String operation, String... values) {
        Objects.requireNonNull(code, "'code' is required");
        Objects.requireNonNull(operation, "'operation' is required");
        Objects.requireNonNull(values, "'values' is required");
        if (values.length == 0) {
            throw new IllegalArgumentException("At least one value is required");
        }

        StringBuilder sb = new StringBuilder();
        sb.append(encode(code));
        sb.append(" ");
        sb.append(encode(operation));

        for (String value : values) {
            Objects.requireNonNull(value, "'value' is required");
            sb.append(" ");
            sb.append(encode(value));
        }

        return sb.toString();
    }

    private static String encode(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
    }
}
