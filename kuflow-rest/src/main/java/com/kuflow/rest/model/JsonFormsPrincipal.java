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
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonFormsPrincipal {

    private static final Pattern PATTERN = Pattern.compile("kuflow-principal:id=([0-9a-f-]+);type=([a-zA-Z]+);name=(.*);");

    public static Optional<JsonFormsPrincipal> from(String value) {
        Matcher matcher = PATTERN.matcher(value);
        if (!matcher.matches()) {
            return Optional.empty();
        }

        UUID id = UUID.fromString(matcher.group(1));
        String type = matcher.group(2);
        String name = matcher.group(3);

        return Optional.of(new JsonFormsPrincipal(id, PrincipalType.fromString(type), name));
    }

    private final UUID id;

    private final PrincipalType type;

    private final String name;

    public JsonFormsPrincipal(UUID id, PrincipalType type, String name) {
        this.id = id;
        this.type = type;
        this.name = name;
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

    public String generateValue() {
        return String.format("kuflow-principal:id=%s;type=%s;name=%s;", this.id, this.type, this.name);
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
        JsonFormsPrincipal that = (JsonFormsPrincipal) o;

        return Objects.equals(this.id, that.id) && Objects.equals(this.type, that.type) && Objects.equals(this.name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.type, this.name);
    }
}
