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
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KuFlowPrincipal {

    private static final Logger LOGGER = LoggerFactory.getLogger(KuFlowPrincipal.class);

    private static final String PREFIX = "kuflow-principal:";

    private static final String METADATA_ID = "id";

    private static final String METADATA_TYPE = "type";

    private static final String METADATA_NAME = "name";

    public static KuFlowPrincipal from(UUID id, PrincipalType type) {
        return from(id, type, null);
    }

    public static KuFlowPrincipal from(UUID id, PrincipalType type, String name) {
        String source = generateValue(id, type, name);

        return new KuFlowPrincipal(source, id, type, name);
    }

    public static Optional<KuFlowPrincipal> from(String source) {
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

        UUID id = parseUuid(keyValueMap.remove(METADATA_ID));
        String type = keyValueMap.remove(METADATA_TYPE);
        String name = keyValueMap.remove(METADATA_NAME);

        if (id == null || type == null || name == null) {
            LOGGER.debug(String.format("Wrong format some parts are missing 'id=%s' 'type=%s' 'name=%s'", id, type, name));

            return Optional.empty();
        }

        KuFlowPrincipal value = new KuFlowPrincipal(source, id, PrincipalType.fromString(type), name);

        return Optional.of(value);
    }

    private final String source;

    private final UUID id;

    private final PrincipalType type;

    private final String name;

    public KuFlowPrincipal(String source, UUID id, PrincipalType type, String name) {
        this.source = source;
        this.id = id;
        this.type = type;
        this.name = name;
    }

    public String getSource() {
        return this.source;
    }

    public UUID getId() {
        return this.id;
    }

    public PrincipalType getType() {
        return this.type;
    }

    public String getName() {
        return this.name;
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
        KuFlowPrincipal that = (KuFlowPrincipal) o;

        return Objects.equals(this.source, that.source);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.source);
    }

    private static UUID parseUuid(String value) {
        if (value == null) {
            return null;
        }

        try {
            return UUID.fromString(value);
        } catch (Exception ex) {
            return null;
        }
    }

    private static String generateValue(UUID id, PrincipalType type, String name) {
        return (
            PREFIX +
            METADATA_ID +
            "=" +
            encode(id.toString()) +
            ";" +
            METADATA_TYPE +
            "=" +
            encode(type.getValue()) +
            ";" +
            METADATA_NAME +
            "=" +
            encode(name) +
            ";"
        );
    }

    private static String encode(String value) {
        if (value == null) {
            return "";
        }

        return URLEncoder.encode(value.trim(), StandardCharsets.UTF_8).replaceAll("\\+", "%20");
    }
}
