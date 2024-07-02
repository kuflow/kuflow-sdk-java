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

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class JsonFormsFileTest {

    @Test
    @DisplayName("GIVEN now function WHEN is called THEN seconds from 1070 are returned")
    public void givenSomeKuflowFileValuesWhenParseValueThenSpecificPartsAreReturned() {
        Optional<JsonFormsFile> parsedOpt = null;
        JsonFormsFile parsedFile = null;

        // Empty test
        parsedOpt = JsonFormsFile.from("");
        assertThat(parsedOpt).isEmpty();

        // Missing mandatory metadata test
        parsedOpt = JsonFormsFile.from("kuflow-file:type=application/pdf;size=ABC;name=dummy.pdf;");
        assertThat(parsedOpt).isEmpty();

        // Mandatory metadata test
        parsedOpt = JsonFormsFile.from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;");
        parsedFile = parsedOpt.orElseThrow();
        assertThat(parsedFile.getUri()).isEqualTo("ku:dummy/xxx-ssss-yyyy");
        assertThat(parsedFile.getType()).isEqualTo("application/pdf");
        assertThat(parsedFile.getSize()).isEqualTo(11111);
        assertThat(parsedFile.getName()).isEqualTo("dummy.pdf");

        // Malformed test: missing =
        parsedOpt = JsonFormsFile.from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;typeapplication/pdf;size=11111;name=dummy.pdf;");
        assertThat(parsedOpt).isEmpty();

        // Malformed test: missing last semicolon
        parsedOpt = JsonFormsFile.from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;typeapplication/pdf;size=11111;name=dummy.pdf");
        assertThat(parsedOpt).isEmpty();

        // Invalid size expression test
        parsedOpt = JsonFormsFile.from("kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=ABC;name=dummy.pdf;");
        assertThat(parsedOpt).isEmpty();

        // Unordered test
        parsedOpt = JsonFormsFile.from("kuflow-file:name=dummy.pdf;type=application/pdf;size=11111;uri=ku:dummy/xxx-ssss-yyyy;");
        parsedFile = parsedOpt.orElseThrow();
        assertThat(parsedFile.getUri()).isEqualTo("ku:dummy/xxx-ssss-yyyy");
        assertThat(parsedFile.getType()).isEqualTo("application/pdf");
        assertThat(parsedFile.getSize()).isEqualTo(11111);
        assertThat(parsedFile.getName()).isEqualTo("dummy.pdf");

        // Mandatory original name test
        parsedOpt =
            JsonFormsFile.from(
                "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;original-name=scanner.pdf;"
            );
        parsedFile = parsedOpt.orElseThrow();
        assertThat(parsedFile.getUri()).isEqualTo("ku:dummy/xxx-ssss-yyyy");
        assertThat(parsedFile.getType()).isEqualTo("application/pdf");
        assertThat(parsedFile.getSize()).isEqualTo(11111);
        assertThat(parsedFile.getName()).isEqualTo("dummy.pdf");
        assertThat(parsedFile.getOriginalName().get()).isEqualTo("scanner.pdf");

        // With unknows parts
        parsedOpt =
            JsonFormsFile.from(
                "kuflow-file:uri=ku:dummy/xxx-ssss-yyyy;type=application/pdf;size=11111;name=dummy.pdf;unknown-key1=unknown-value1;unknown-key2=unknown-value2;"
            );
        parsedFile = parsedOpt.orElseThrow();
        assertThat(parsedFile.getUri()).isEqualTo("ku:dummy/xxx-ssss-yyyy");
        assertThat(parsedFile.getType()).isEqualTo("application/pdf");
        assertThat(parsedFile.getSize()).isEqualTo(11111);
        assertThat(parsedFile.getName()).isEqualTo("dummy.pdf");
        assertThat(parsedFile.getUnknownMetadata("unknown-key1")).isEqualTo("unknown-value1");
        assertThat(parsedFile.getUnknownMetadata("unknown-key2")).isEqualTo("unknown-value2");
    }
}
