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

import static com.kuflow.rest.util.SearchCriteriaUtils.encodeFilterExpression;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Verifies that {@link SearchCriteriaUtils#encodeFilterExpression} produces an expression that the KuFlow
 * REST API server can parse back into the original code/operation/values.
 *
 * <p>The server's parser (kuflow-core's {@code SearchCriteriaParser}) is not on this module's classpath, so
 * these tests replicate its exact decode sequence: split the expression on spaces, then decode each
 * resulting token (code, operation, each value) individually. This is the same sequence used to design
 * {@link SearchCriteriaUtils#encodeFilterExpression} in the first place.
 */
public class SearchCriteriaUtilsTest {

    @Test
    @DisplayName("GIVEN plain values WHEN encoded THEN the server-side parser recovers them unchanged")
    public void givenPlainValuesWhenEncodedThenServerSideParserRecoversThemUnchanged() {
        String expression = encodeFilterExpression("invoiceNumber", "eq", "INV-001");

        ParsedCriterion parsed = parseSearchCriterion(expression);

        assertThat(parsed.code()).isEqualTo("invoiceNumber");
        assertThat(parsed.operation()).isEqualTo("eq");
        assertThat(parsed.values()).containsExactly("INV-001");
    }

    @Test
    @DisplayName("GIVEN a value with an embedded space WHEN encoded THEN the server-side parser recovers it intact")
    public void givenValueWithEmbeddedSpaceWhenEncodedThenServerSideParserRecoversItIntact() {
        String expression = encodeFilterExpression("FIELD_STRING", "eq", "Text value");

        ParsedCriterion parsed = parseSearchCriterion(expression);

        assertThat(parsed.code()).isEqualTo("FIELD_STRING");
        assertThat(parsed.operation()).isEqualTo("eq");
        assertThat(parsed.values()).containsExactly("Text value");
    }

    @Test
    @DisplayName("GIVEN a value with a literal '+' WHEN encoded THEN the server-side parser recovers it intact")
    public void givenValueWithLiteralPlusWhenEncodedThenServerSideParserRecoversItIntact() {
        String expression = encodeFilterExpression("phone", "eq", "+1234567890");

        ParsedCriterion parsed = parseSearchCriterion(expression);

        assertThat(parsed.values()).containsExactly("+1234567890");
    }

    @Test
    @DisplayName("GIVEN a value with a literal '%' WHEN encoded THEN the server-side parser recovers it intact")
    public void givenValueWithLiteralPercentWhenEncodedThenServerSideParserRecoversItIntact() {
        String expression = encodeFilterExpression("discount", "eq", "50%off");

        ParsedCriterion parsed = parseSearchCriterion(expression);

        assertThat(parsed.values()).containsExactly("50%off");
    }

    @Test
    @DisplayName("GIVEN a multi-value operator WHEN encoded THEN the server-side parser recovers every value in order")
    public void givenMultiValueOperatorWhenEncodedThenServerSideParserRecoversEveryValueInOrder() {
        String expression = encodeFilterExpression("amount", "between", "10", "20");

        ParsedCriterion parsed = parseSearchCriterion(expression);

        assertThat(parsed.operation()).isEqualTo("between");
        assertThat(parsed.values()).containsExactly("10", "20");
    }

    @Test
    @DisplayName("GIVEN a multi-value operator with embedded spaces WHEN encoded THEN every value survives intact")
    public void givenMultiValueOperatorWithEmbeddedSpacesWhenEncodedThenEveryValueSurvivesIntact() {
        String expression = encodeFilterExpression("status", "in", "active now", "inactive later");

        ParsedCriterion parsed = parseSearchCriterion(expression);

        assertThat(parsed.operation()).isEqualTo("in");
        assertThat(parsed.values()).containsExactly("active now", "inactive later");
    }

    @Test
    @DisplayName("GIVEN no values WHEN building an expression THEN an exception is thrown")
    public void givenNoValuesWhenBuildingAnExpressionThenAnExceptionIsThrown() {
        assertThatThrownBy(() -> encodeFilterExpression("code", "eq")).isInstanceOf(IllegalArgumentException.class);
    }

    private record ParsedCriterion(String code, String operation, List<String> values) {}

    /**
     * Mirrors kuflow-core's {@code SearchCriteriaParser.parseSearchCriterion} exactly: split on spaces,
     * then decode each resulting token individually.
     */
    private static ParsedCriterion parseSearchCriterion(String filter) {
        List<String> parts = List.of(filter.split(" "));

        String code = URLDecoder.decode(parts.get(0), StandardCharsets.UTF_8);
        String operation = URLDecoder.decode(parts.get(1), StandardCharsets.UTF_8);
        List<String> values = parts
            .subList(2, parts.size())
            .stream()
            .map(it -> URLDecoder.decode(it, StandardCharsets.UTF_8))
            .toList();

        return new ParsedCriterion(code, operation, values);
    }
}
