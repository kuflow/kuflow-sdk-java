/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.impl.email.dto;

import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import org.springframework.util.Assert;

public final class EmailDto {

    public static EmailDtoBuilder builder() {
        return new EmailDtoBuilder();
    }

    @Nonnull
    @NotNull
    private final String to;

    @Nonnull
    @NotNull
    private final String template;

    @Nonnull
    @NotNull
    private final Map<String, Object> variables;

    @Nonnull
    @NotNull
    private final Locale locale;

    private EmailDto(@Nonnull String to, @Nonnull String template, @Nonnull Map<String, Object> variables, @Nullable Locale locale) {
        Assert.notNull(to, "to is required");
        Assert.notNull(template, "template is required");

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
