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
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class KuFlowGroup {

    public static final String PREFIX = "kuflow-group:";

    private static final String METADATA_ID = "id";

    private static final String METADATA_TYPE = "type";

    private static final String METADATA_NAME = "name";

    public static KuFlowGroup from(UUID id, String type, String name) {
        String source = generateValue(id, type, name);

        return new KuFlowGroup(source, id, type, name);
    }

    public static Optional<KuFlowGroup> from(String source) {
        if (!source.toLowerCase().startsWith(PREFIX)) {
            return Optional.empty();
        }

        String sourceWithoutPrefix = source.substring(PREFIX.length());
        String[] keyValuePairs = sourceWithoutPrefix.split(";");
        Map<String, String> keyValueMap = new HashMap<>();

        for (String pair : keyValuePairs) {
            int indexOfEquals = pair.indexOf("=");
            if (indexOfEquals == -1) {
                return Optional.empty();
            }

            String key = pair.substring(0, indexOfEquals).toLowerCase();
            String value = pair.substring(indexOfEquals + 1);
            value = Optional.of(value).map(it -> URLDecoder.decode(it, StandardCharsets.UTF_8)).orElse(null);
            keyValueMap.put(key, value);
        }

        UUID id = UUID.fromString(keyValueMap.remove(METADATA_ID));

        String type = keyValueMap.remove(METADATA_TYPE);

        String name = keyValueMap.remove(METADATA_NAME);

        return Optional.of(new KuFlowGroup(source, id, type, name));
    }

    private final String source;

    private final UUID id;

    private final String type;

    private final String name;

    private KuFlowGroup(String source, UUID id, String type, String name) {
        this.source = source;
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public UUID getId() {
        return this.id;
    }

    public String getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return this.source;
    }

    private static String generateValue(UUID id, String type, String name) {
        return (
            PREFIX +
            METADATA_ID +
            "=" +
            encode(id.toString()) +
            ";" +
            METADATA_TYPE +
            "=" +
            encode(type) +
            ";" +
            METADATA_NAME +
            "=" +
            encode(name) +
            ";"
        );
    }

    private static String encode(String value) {
        return URLEncoder.encode(value.trim(), StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }
}
