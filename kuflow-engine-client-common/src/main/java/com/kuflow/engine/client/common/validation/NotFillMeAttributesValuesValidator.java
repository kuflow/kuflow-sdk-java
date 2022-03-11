/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.common.validation;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotFillMeAttributesValuesValidator implements ConstraintValidator<NotFillMeAttributesValues, Object> {

    private static final String FILL_ME = "FILL_ME";

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        List<Field> fieldsErrors = new LinkedList<>();
        Arrays
            .asList(value.getClass().getDeclaredFields())
            .forEach(field -> {
                field.setAccessible(true);
                try {
                    Object property = field.get(value);
                    if (this.containsFillMe(property)) {
                        fieldsErrors.add(field);
                        context
                            .buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                            .addPropertyNode(field.getName())
                            .addConstraintViolation()
                            .disableDefaultConstraintViolation();
                    }
                } catch (IllegalAccessException ex) {
                    throw new IllegalStateException(ex);
                }
            });

        return fieldsErrors.isEmpty();
    }

    private boolean containsFillMe(Object value) {
        if (Objects.isNull(value) || !(value instanceof String)) {
            return false;
        }

        String valueString = (String) value;

        return (valueString.toUpperCase().contains(FILL_ME));
    }
}
