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
package com.kuflow.spring.boot.autoconfigure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class NotFillMeAttributesValuesValidator implements ConstraintValidator<NotFillMeAttributesValues, Object> {

    private static final String FILL_ME = "FILL_ME";

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        List<Field> fieldsErrors = new LinkedList<>();
        Arrays.asList(value.getClass().getDeclaredFields()).forEach(field -> {
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

        return (value.toString().toUpperCase().contains(FILL_ME));
    }
}
