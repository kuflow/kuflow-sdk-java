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

import java.util.Objects;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonFormsFile {

    private static final Pattern PATTERN = Pattern.compile(
        "kuflow-file:uri=(ku:[a-zA-Z0-9-/]+);type=([a-zA-Z]+/[a-zA-Z]+);size=([0-9]+);name=(.*);"
    );

    public static Optional<JsonFormsFile> from(String value) {
        Matcher matcher = PATTERN.matcher(value);
        if (!matcher.matches()) {
            return Optional.empty();
        }

        String uri = matcher.group(1);
        String type = matcher.group(2);
        Long size = Long.valueOf(matcher.group(3));
        String name = matcher.group(4);

        return Optional.of(new JsonFormsFile(uri, type, name, size));
    }

    private final String uri;
    private final String type;
    private final String name;
    private final Long size;

    public JsonFormsFile(String uri, String type, String name, Long size) {
        this.uri = uri;
        this.type = type;
        this.name = name;
        this.size = size;
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

    public String generateValue() {
        return String.format("kuflow-file:uri=%s;type=%s;size=%d;name=%s;", this.uri, this.type, this.size, this.name);
    }

    @Override
    public String toString() {
        return this.generateValue();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonFormsFile that = (JsonFormsFile) o;
        return (
            Objects.equals(this.uri, that.uri) &&
            Objects.equals(this.type, that.type) &&
            Objects.equals(this.name, that.name) &&
            Objects.equals(this.size, that.size)
        );
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uri, this.type, this.name, this.size);
    }
}
