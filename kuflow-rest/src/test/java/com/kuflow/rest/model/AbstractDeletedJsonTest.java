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

import com.azure.json.JsonProviders;
import com.azure.json.JsonReader;
import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("AbstractDeleted JSON serialization")
public class AbstractDeletedJsonTest {

    private static final UUID DELETED_BY = UUID.fromString("019f3bd5-23f3-7b27-8971-1493dbc4d361");

    private static final OffsetDateTime DELETED_AT = OffsetDateTime.parse("2026-10-22T10:15:30.123Z");

    @Test
    @DisplayName("GIVEN a deleted process WHEN serializing and deserializing THEN deletedBy and deletedAt are kept")
    public void givenADeletedProcessWhenSerializingAndDeserializingThenDeletedByAndDeletedAtAreKept() throws IOException {
        Process process = new Process()
            .setId(UUID.fromString("019f3bd5-23f3-7b27-8971-1493dbc4d362"))
            .setState(ProcessState.COMPLETED)
            .setDeletedBy(DELETED_BY)
            .setDeletedAt(DELETED_AT);

        String json = process.toJsonString();

        assertThat(json).contains("\"deletedBy\":\"" + DELETED_BY + "\"");
        assertThat(json).contains("\"deletedAt\":");

        Process parsed;
        try (JsonReader reader = JsonProviders.createReader(json)) {
            parsed = Process.fromJson(reader);
        }

        assertThat(parsed.getDeletedBy()).isEqualTo(DELETED_BY);
        assertThat(parsed.getDeletedAt()).isEqualTo(DELETED_AT);
    }

    @Test
    @DisplayName("GIVEN a deleted business artifact WHEN serializing and deserializing THEN deletedBy and deletedAt are kept")
    public void givenADeletedBusinessArtifactWhenSerializingAndDeserializingThenDeletedByAndDeletedAtAreKept() throws IOException {
        BusinessArtifact businessArtifact = new BusinessArtifact()
            .setId(UUID.fromString("019f3bd5-23f3-7b27-8971-1493dbc4d363"))
            .setDeletedBy(DELETED_BY)
            .setDeletedAt(DELETED_AT);

        String json = businessArtifact.toJsonString();

        assertThat(json).contains("\"deletedBy\":\"" + DELETED_BY + "\"");
        assertThat(json).contains("\"deletedAt\":");

        BusinessArtifact parsed;
        try (JsonReader reader = JsonProviders.createReader(json)) {
            parsed = BusinessArtifact.fromJson(reader);
        }

        assertThat(parsed.getDeletedBy()).isEqualTo(DELETED_BY);
        assertThat(parsed.getDeletedAt()).isEqualTo(DELETED_AT);
    }
}
