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
 * # Resource URIs
 *
 * Some operations identify resources and documents through URIs instead of plain IDs:
 *
 * * `ku:` URIs identify KuFlow resources, e.g. `ku:process/{processId}` or
 * `ku:business-artifact/{businessArtifactId}`. Resources that can be referenced this way expose their
 * URI in a read-only `uri` field. Documents owned by a resource extend its URI, e.g.
 * `ku:process/{processId}/document/{documentId}`.
 * * `kuflow-file:` values are document references: they wrap a `ku:` document URI together with its
 * metadata using the format
 * `kuflow-file:uri=&lt;ku-uri&gt;;type=&lt;media-type&gt;;size=&lt;bytes&gt;;name=&lt;file-name&gt;;`.
 *
 * Operations that receive a document URI accept both the full `kuflow-file:` reference and the inner
 * `ku:` document URI.
 *
 * # OpenAPI Specification
 *
 * This API is documented in OpenAPI format. This file allows you to create REST clients with the technology of your
 * choice automatically.
 */
package com.kuflow.rest.implementation;
