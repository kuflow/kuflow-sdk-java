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

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KuFlowFile {

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowFile.class);

    private static final String PREFIX = "kuflow-file:";

    private static final String METADATA_URI = "uri";

    private static final String METADATA_TYPE = "type";

    private static final String METADATA_NAME = "name";

    private static final String METADATA_SIZE = "size";

    private static final String METADATA_ORIGINAL_NAME = "original-name";

    public static Optional<KuFlowFile> from(String source) {
        if (source == null || source.isEmpty()) {
            return Optional.empty();
        }

        if (!source.toLowerCase().startsWith(PREFIX)) {
            LOGGER.debug(String.format("Input string '%s' does not start with the expected prefix '%s'", source, PREFIX));

            return Optional.empty();
        }

        String sourceWithoutPrefix = source.substring(PREFIX.length());
        String[] keyValuePairs = sourceWithoutPrefix.split(";");
        Map<String, String> keyValueMap = new HashMap<>();

        for (String pair : keyValuePairs) {
            int indexOfEquals = pair.indexOf("=");
            if (indexOfEquals == -1) {
                LOGGER.debug(String.format("Invalid format in key-value pair '%s' on value '%s' ", pair, source));

                return Optional.empty();
            }

            String key = pair.substring(0, indexOfEquals).toLowerCase();
            String value = pair.substring(indexOfEquals + 1);
            value = Optional.of(value)
                .map(it -> URLDecoder.decode(it, StandardCharsets.UTF_8))
                .orElse(null);
            keyValueMap.put(key, value);
        }

        String uri = keyValueMap.remove(METADATA_URI);

        String type = keyValueMap.remove(METADATA_TYPE);

        String name = keyValueMap.remove(METADATA_NAME);

        Long size = parseLong(keyValueMap.remove(METADATA_SIZE));

        if (uri == null || type == null || name == null || size == null) {
            LOGGER.debug(
                String.format("Wrong format some parts are missing 'uri=%s' 'type=%s' 'name=%s' 'size=%s'", uri, type, name, size)
            );

            return Optional.empty();
        }

        String originalName = keyValueMap.remove(METADATA_ORIGINAL_NAME);

        KuFlowFile value = new KuFlowFile(source, uri, type, name, size, originalName, keyValueMap);

        return Optional.of(value);
    }

    private final String source;
    private final String uri;
    private final String type;
    private final String name;
    private final Long size;
    private final String originalName;
    private final Map<String, String> unknownMetadata;

    private KuFlowFile(
        String source,
        String uri,
        String type,
        String name,
        Long size,
        String originalName,
        Map<String, String> unknownMetadata
    ) {
        this.source = Objects.requireNonNull(source, "'source' must be not null");
        this.uri = Objects.requireNonNull(uri, "'uri' must be not null");
        this.type = Objects.requireNonNull(type, "'type' must be not null");
        this.name = Objects.requireNonNull(name, "'name' must be not null");
        this.size = Objects.requireNonNull(size, "'size' must be not null");

        this.originalName = originalName;

        this.unknownMetadata = Objects.requireNonNull(unknownMetadata, "'unknownMetadata' must be not null");
    }

    public String getSource() {
        return this.source;
    }

    public String getUri() {
        return this.uri;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    public Long getSize() {
        return this.size;
    }

    public Optional<String> getOriginalName() {
        return Optional.ofNullable(this.originalName);
    }

    public Map<String, String> getUnknownMetadata() {
        return Collections.unmodifiableMap(this.unknownMetadata);
    }

    public String getUnknownMetadataItem(String key) {
        if (key == null) {
            return null;
        }
        return this.unknownMetadata.get(key.toLowerCase());
    }

    @Override
    public String toString() {
        return this.source;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || this.getClass() != o.getClass()) {
            return false;
        }
        KuFlowFile that = (KuFlowFile) o;
        return Objects.equals(this.source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source);
    }

    private static Long parseLong(String value) {
        if (value == null) {
            return null;
        }

        try {
            return Long.valueOf(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }
}
