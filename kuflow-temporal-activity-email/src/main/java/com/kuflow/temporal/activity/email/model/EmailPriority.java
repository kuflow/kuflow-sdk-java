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
package com.kuflow.temporal.activity.email.model;

/**
 * Email priority levels following the X-Priority header standard.
 * Used to set the importance of email messages.
 */
public enum EmailPriority {
    /**
     * Highest priority (X-Priority: 1)
     */
    HIGHEST(1),

    /**
     * High priority (X-Priority: 2)
     */
    HIGH(2),

    /**
     * Normal priority (X-Priority: 3)
     */
    NORMAL(3),

    /**
     * Low priority (X-Priority: 4)
     */
    LOW(4),

    /**
     * Lowest priority (X-Priority: 5)
     */
    LOWEST(5);

    private final int value;

    EmailPriority(int value) {
        this.value = value;
    }

    /**
     * Gets the numeric priority value for the X-Priority header.
     *
     * @return the priority value (1-5)
     */
    public int getValue() {
        return this.value;
    }
}
