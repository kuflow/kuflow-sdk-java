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
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class KuFlowProcessTest {

    @Test
    @DisplayName("GIVEN process values WHEN generating and parsing THEN the same parts are returned")
    public void givenProcessValuesWhenGeneratingAndParsingThenTheSamePartsAreReturned() {
        UUID id = UUID.fromString("019f3bd5-23f3-7b27-8971-1493dbc4d361");

        // Round trip, id only
        KuFlowProcess process = KuFlowProcess.from(id);
        assertThat(process.getSource()).isEqualTo("kuflow-process:id=019f3bd5-23f3-7b27-8971-1493dbc4d361;");
        KuFlowProcess parsed = KuFlowProcess.from(process.getSource()).orElseThrow();
        assertThat(parsed.getId()).isEqualTo(id);
        assertThat(parsed.getName()).isNull();

        // Round trip with name, spaces encoded as %20
        process = KuFlowProcess.from(id, "Valoración individual");
        assertThat(process.getSource()).contains("name=Valoraci%C3%B3n%20individual;");
        parsed = KuFlowProcess.from(process.getSource()).orElseThrow();
        assertThat(parsed.getId()).isEqualTo(id);
        assertThat(parsed.getName()).isEqualTo("Valoración individual");
    }

    @Test
    @DisplayName("GIVEN malformed sources WHEN parsing THEN empty is returned")
    public void givenMalformedSourcesWhenParsingThenEmptyIsReturned() {
        // Empty and null
        assertThat(KuFlowProcess.from("")).isEmpty();
        assertThat(KuFlowProcess.from((String) null)).isEmpty();

        // Wrong prefix
        assertThat(KuFlowProcess.from("kuflow-principal:id=019f3bd5-23f3-7b27-8971-1493dbc4d361;")).isEmpty();

        // Missing mandatory id
        assertThat(KuFlowProcess.from("kuflow-process:name=dummy;")).isEmpty();

        // Invalid id
        assertThat(KuFlowProcess.from("kuflow-process:id=not-a-uuid;")).isEmpty();

        // Missing =
        assertThat(KuFlowProcess.from("kuflow-process:id019f3bd5-23f3-7b27-8971-1493dbc4d361;")).isEmpty();
    }

    @Test
    @DisplayName("GIVEN unordered or unknown parts WHEN parsing THEN known parts are returned")
    public void givenUnorderedOrUnknownPartsWhenParsingThenKnownPartsAreReturned() {
        Optional<KuFlowProcess> parsedOpt = KuFlowProcess.from(
            "kuflow-process:name=dummy;id=019f3bd5-23f3-7b27-8971-1493dbc4d361;unknown-key=unknown-value;"
        );
        KuFlowProcess parsed = parsedOpt.orElseThrow();
        assertThat(parsed.getId()).isEqualTo(UUID.fromString("019f3bd5-23f3-7b27-8971-1493dbc4d361"));
        assertThat(parsed.getName()).isEqualTo("dummy");
    }
}
