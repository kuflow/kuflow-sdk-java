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
package com.kuflow.temporal.activity.email.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public final class EmailDto {

    public static EmailDtoBuilder builder() {
        return new EmailDtoBuilder();
    }

    @Nonnull
    private final String to;

    @Nonnull
    private final String template;

    @Nonnull
    private final Map<String, Object> variables;

    @Nonnull
    private final Locale locale;

    private EmailDto(@Nonnull String to, @Nonnull String template, @Nonnull Map<String, Object> variables, @Nullable Locale locale) {
        Objects.requireNonNull(to, "'to' is required");
        Objects.requireNonNull(template, "'template' is required");

        this.to = to;
        this.template = template;
        this.variables = Collections.unmodifiableMap(variables);
        this.locale = Optional.ofNullable(locale).orElse(Locale.ENGLISH);
    }

    @Nonnull
    public String getTo() {
        return this.to;
    }

    @Nonnull
    public String getTemplate() {
        return this.template;
    }

    @Nonnull
    public Locale getLocale() {
        return this.locale;
    }

    @Nonnull
    public Map<String, Object> getVariables() {
        return this.variables;
    }

    public static final class EmailDtoBuilder {

        private String to;

        private String template;

        private Locale locale;

        private final Map<String, Object> variables = new HashMap<>();

        private EmailDtoBuilder() {}

        public EmailDtoBuilder withTo(String to) {
            this.to = to;

            return this;
        }

        public EmailDtoBuilder withTemplate(String template) {
            this.template = template;

            return this;
        }

        public EmailDtoBuilder withLocale(Locale locale) {
            this.locale = locale;

            return this;
        }

        public EmailDtoBuilder withVariables(Map<String, Object> variables) {
            this.variables.clear();
            this.variables.putAll(variables);

            return this;
        }

        public EmailDtoBuilder addVariable(String name, Object value) {
            this.variables.put(name, value);

            return this;
        }

        public EmailDto build() {
            return new EmailDto(this.to, this.template, this.variables, this.locale);
        }
    }
}
