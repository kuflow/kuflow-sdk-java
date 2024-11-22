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

import com.azure.core.util.ExpandableStringEnum;
import java.util.Collection;

/**
 * Type of the Event.
 */
public final class WebhookType extends ExpandableStringEnum<WebhookType> {

    /**
     * Static value PROCESS.CREATED for WebhookType.
     */
    public static final WebhookType PROCESS_CREATED = fromString("PROCESS.CREATED");

    /**
     * Static value PROCESS.STATE_CHANGED for WebhookType.
     */
    public static final WebhookType PROCESS_STATE_CHANGED = fromString("PROCESS.STATE_CHANGED");

    /**
     * Static value PROCESS_ITEM.CREATED for WebhookType.
     */
    public static final WebhookType PROCESS_ITEM_CREATED = fromString("PROCESS_ITEM.CREATED");

    /**
     * Static value PROCESS_ITEM.TASK_STATE_CHANGED for WebhookType.
     */
    public static final WebhookType PROCESS_ITEM_TASK_STATE_CHANGED = fromString("PROCESS_ITEM.TASK_STATE_CHANGED");

    /**
     * Creates a new instance of WebhookType value.
     *
     * @deprecated Use the {@link #fromString(String)} factory method.
     */
    @Deprecated
    public WebhookType() {}

    /**
     * Creates or finds a WebhookType from its string representation.
     *
     * @param name a name to look for.
     * @return the corresponding WebhookType.
     */
    public static WebhookType fromString(String name) {
        return fromString(name, WebhookType.class);
    }

    /**
     * Gets known WebhookType values.
     *
     * @return known WebhookType values.
     */
    public static Collection<WebhookType> values() {
        return values(WebhookType.class);
    }
}
