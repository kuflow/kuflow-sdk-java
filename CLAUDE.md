# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Overview

This is the **KuFlow SDK for Java**: a multi-module Maven project (`com.kuflow:kuflow-sdk`) providing libraries for building Workers that integrate with the KuFlow platform. Integration is built on top of [Temporal](https://temporal.io) — KuFlow runs business processes as Temporal workflows, and these modules wrap the KuFlow REST API as Temporal activities plus the connection/worker plumbing to register them.

Toolchain is pinned in `mise.toml` (Java 17 / Temurin, Maven 3.9.x, Node 24 + pnpm for the REST codegen). The POM targets `java.version=17` despite `CONTRIBUTING.md` still saying Java 11 — use 17.

## Common Commands

```bash
./mvnw test                          # run all tests
./mvnw install                       # full build + install to local repo
./mvnw test -pl kuflow-rest          # build/test a single module
./mvnw test -pl kuflow-rest -Dtest=ProcessOperationsTest          # single test class
./mvnw test -pl kuflow-rest -Dtest=ProcessOperationsTest#myMethod # single test method
```

Use `-am` (`./mvnw test -pl <module> -am`) to also build the modules a target depends on.

### Code style — runs in the `validate` phase of every build

The build **fails** on style violations. Three gates run during `validate`: Checkstyle (`etc/checkstyle/`), prettier-java, and license headers. To auto-fix instead of just checking:

```bash
./mvnw prettier:write     # format Java sources
./mvnw license:format     # apply/refresh MIT license headers
```

Every source file must carry the MIT header (`The MIT License / Copyright © 2021-present KuFlow S.L.`). New files without it will fail the build.

## Module Architecture

Modules are layered bottom-up; `kuflow-temporal-worker` and `kuflow-spring-boot-autoconfigure` sit at the top and pull the rest together.

- **kuflow-common** — minimal shared annotations (e.g. `@Experimental`).
- **kuflow-rest** — the KuFlow REST API client. See the generated-code note below; this is the most unusual module.
- **kuflow-temporal-common** — shared Temporal pieces: `AbstractModel`, `CipherUtils`, `TemporalUtils`, exception types.
- **kuflow-temporal-activity-\*** — Temporal activity implementations, one module per concern:
  - `activity-kuflow` — the core: wraps `KuFlowRestClient` operations as Temporal activities (`KuFlowActivities` interface + `KuFlowActivitiesImpl`), with a request/response model class per operation under `model/`.
  - `activity-email`, `activity-s3`, `activity-datasource`, `activity-robotframework`, `activity-uivision` — integration-specific activities.
- **kuflow-temporal-workflow-kuflow** — workflow-side contracts: `KuFlowWorkflow` helpers, signal/user-action/business-artifact models, search-attribute enums. Code here is Temporal **workflow** code (deterministic constraints apply).
- **kuflow-temporal-worker** — connection and worker lifecycle. `KuFlowTemporalConnection` is the fluent entry point (`instance(kuFlowRestClient)` → configure stubs/client/worker → build). Also houses:
  - `jackson/` — custom serializers bridging Autorest-generated models into Temporal's payload serialization.
  - `encryption/` — end-to-end payload encryption via a `PayloadCodec` + interceptors (`EncryptionPayloadConverter`, client/worker interceptors).
  - `ssl/`, `authorization/`, `tracing/` (MDC context propagation).
- **kuflow-spring-boot-autoconfigure** — Spring Boot starters. Auto-configurations are registered in `src/main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports` and bind `@ConfigurationProperties` from `properties/`. **When adding a new auto-configuration class, you must add it to that imports file** or Spring will not pick it up.

The parent `pom.xml` `<dependencyManagement>` pins every internal module to `${project.version}` and centralizes third-party versions (Temporal SDK, AWS SDK, Jackson, Azure core). Add new dependency versions there, not in child POMs.

## kuflow-rest: generated vs. hand-written code

This module mixes **generated** and **hand-written** sources, and the distinction matters:

- `src/generated/java/...` — produced by **Autorest** from the KuFlow OpenAPI spec. Contains `implementation/` (the low-level `KuFlowClientImpl` + per-tag `*OperationsImpl`) and ~100 model classes in `model/`. **Do not hand-edit these**; they are regenerated. The directory is added as an extra source root via `build-helper-maven-plugin` in `kuflow-rest/pom.xml`.
- `src/main/java/...` — hand-written public API layered over the generated impl: `KuFlowRestClient` / `KuFlowRestClientBuilder` (the public entry point), the `operation/` wrappers (`ProcessOperations`, `ProcessItemOperations`, `BusinessArtifactOperations`, etc.) that delegate to the generated `*OperationsImpl`, hand-written `model/` extensions, and `util/`.

### Regenerating the REST client

The Autorest config and pinned OpenAPI spec commit live in `kuflow-rest/openapi/readme.md`. From `kuflow-rest/openapi/`:

```bash
pnpm install
pnpm run generate     # autorest → copy into src/generated → prettier → license headers
```

To target a new API version, update the `input-file` spec URL and `API_VERSION` in `KuFlowRestClient`, regenerate, then reconcile the hand-written `operation/` wrappers and activity model classes against any signature changes.

## Conventions

- New activity operations follow the established pattern: a `model/XxxRequest` + `model/XxxResponse` pair, a method on the `KuFlowActivities` interface, an implementation in `KuFlowActivitiesImpl` delegating to `KuFlowRestClient`, and validation in `util/KuFlowActivitiesValidation`.
- Commits use **conventional commits** format (see git history: `feat:`, etc.).
- The released artifact is built from `.flattened-pom.xml` (flatten-maven-plugin, `ossrh` mode); publishing to Maven Central happens under the `release` profile (GPG signing + central-publishing). Don't edit flattened/backup POMs by hand.
