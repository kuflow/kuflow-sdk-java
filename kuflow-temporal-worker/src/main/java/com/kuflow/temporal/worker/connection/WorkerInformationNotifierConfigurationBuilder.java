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
package com.kuflow.temporal.worker.connection;

import java.time.Duration;

public class WorkerInformationNotifierConfigurationBuilder {

    private Duration backoffSleep;

    private Double backoffExponentialRate;

    private WorkerInformationNotifierConfigurationBuilder() {}

    public static WorkerInformationNotifierConfigurationBuilder instance() {
        return new WorkerInformationNotifierConfigurationBuilder();
    }

    /**
     * Set the duration between errors, default 1 second
     * @param backoffSleep duration
     * @return the current builder
     */
    public WorkerInformationNotifierConfigurationBuilder withBackoffSleep(Duration backoffSleep) {
        this.backoffSleep = backoffSleep;

        return this;
    }

    /**
     * Set the exponential rate applied if the error persists, default 2.5
     * @param backoffExponentialRate exponential rate
     * @return the current builder
     */
    public WorkerInformationNotifierConfigurationBuilder withBackoffExponentialRate(Double backoffExponentialRate) {
        this.backoffExponentialRate = backoffExponentialRate;
        return this;
    }

    public WorkerInformationNotifierConfiguration build() {
        if (this.backoffSleep == null) {
            this.backoffSleep = Duration.ofSeconds(1);
        }
        if (this.backoffExponentialRate == null || this.backoffExponentialRate <= 0) {
            this.backoffExponentialRate = 2.5;
        }

        return new WorkerInformationNotifierConfiguration(this.backoffSleep, this.backoffExponentialRate);
    }
}
