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

/**
 * Robot source type.
 */
public enum RobotSourceType {
    /**
     * Enum value PACKAGE.
     */
    PACKAGE("PACKAGE"),

    /**
     * Enum value UNKNOWN.
     */
    UNKNOWN("UNKNOWN");

    /**
     * The actual serialized value for a RobotSourceType instance.
     */
    private final String value;

    RobotSourceType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a RobotSourceType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed RobotSourceType object, or null if unable to parse.
     */
    public static RobotSourceType fromString(String value) {
        if (value == null) {
            return null;
        }
        RobotSourceType[] items = RobotSourceType.values();
        for (RobotSourceType item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return this.value;
    }
}
