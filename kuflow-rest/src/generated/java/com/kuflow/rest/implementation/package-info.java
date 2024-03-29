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
/**
 * Package containing the implementations for KuFlowClient.
 * # Introduction
 *
 * This document contains the KuFlow REST API reference. This API is a fundamental part in the integration of external
 * systems with KuFlow and is used, among others, by the different implementations of the Workers that connect to our
 * network.
 *
 * # API Versioning
 *
 * A versioning strategy allows our clients to continue using the existing REST API and migrate their applications to
 * the newer API when they are ready.
 *
 * The scheme followed is a simplification of *Semver* where only MAJOR versions are differentiated from MINOR or PATCH
 * versions, i.e. a version number of only two levels is used. With this approach, you only have to migrate your
 * applications if you want to upgrade to a MAJOR version of the KuFlow API. In case you want to upgrade to a MINOR
 * version, you can do so without any incompatibility issues.
 *
 * The versioning of the api is done through the URI Path, that is, the version number is included in the URI Path. The
 * URL structure would be as follows:
 *
 * ```bash
 * https://{endpoint}/v{VERSION}/{api-path}
 * ```
 *
 * # Idempotency
 *
 * The API is designed to support idempotency in order to achieve a correct resilience in the implementation of its
 * clients. The way to achieve this is very simple, in the methods that create resources, you simply have to specify a
 * UUID in the input data and the API will respond by creating or returning the resource if it previously existed. With
 * this mechanism, your systems can implement retry logic without worrying about performing data tradeoffs.
 *
 * # OpenAPI Specification
 *
 * This API is documented in OpenAPI format. This file allows you to create REST clients with the technology of your
 * choice automatically.
 */
package com.kuflow.rest.implementation;
