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

import com.kuflow.rest.util.UUIDUtils;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KuFlowDataSource {

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowDataSource.class);

    public static final String PREFIX = "kuflow-data-source:";

    private static final String METADATA_ID = "id";
    private static final String METADATA_NAME = "name";

    public static KuFlowDataSource from(UUID id, String name) {
        String source = generateValue(id, name);

        return new KuFlowDataSource(source, id, name);
    }

    public static Optional<KuFlowDataSource> from(String source) {
        if (source == null || !source.toLowerCase().startsWith(PREFIX)) {
            LOGGER.debug("Input string '{}' does not start with the expected prefix '{}'", source, PREFIX);

            return Optional.empty();
        }

        String sourceWithoutPrefix = source.substring(PREFIX.length());
        String[] keyValuePairs = sourceWithoutPrefix.split(";");
        Map<String, String> keyValueMap = new HashMap<>();

        for (String pair : keyValuePairs) {
            if (pair == null || pair.trim().isEmpty()) {
                continue;
            }

            int indexOfEquals = pair.indexOf("=");
            if (indexOfEquals == -1) {
                LOGGER.debug("Invalid format in key-value pair '{}' on value '{}' ", pair, source);

                return Optional.empty();
            }

            String key = pair.substring(0, indexOfEquals).toLowerCase();
            String value = pair.substring(indexOfEquals + 1);
            value = Optional.of(value)
                .map(it -> URLDecoder.decode(it, StandardCharsets.UTF_8))
                .orElse(null);
            keyValueMap.put(key, value);
        }

        UUID id = UUIDUtils.parseUuid(keyValueMap.remove(METADATA_ID));
        String name = keyValueMap.remove(METADATA_NAME);

        if (id == null) {
            LOGGER.debug("Wrong format some parts are missing 'id={}'", id);

            return Optional.empty();
        }

        return Optional.of(new KuFlowDataSource(source, id, name));
    }

    private final String source;

    private final UUID id;

    private final String name;

    private KuFlowDataSource(String source, UUID id, String name) {
        this.source = source;
        this.id = id;
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.source;
    }

    private static String generateValue(UUID id, String name) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX).append(METADATA_ID).append("=").append(encode(id.toString())).append(";");

        if (name != null) {
            sb.append(METADATA_NAME).append("=").append(encode(name)).append(";");
        }

        return sb.toString();
    }

    private static String encode(String value) {
        return URLEncoder.encode(value.trim(), StandardCharsets.UTF_8).replace("+", "%20");
    }
}
