# KuFlow Rest API client

> see https://aka.ms/autorest

This is the AutoRest configuration file for KuFlow.

---

## Getting Started

To build the SDK for KuFlow, simply do the following in this folder:

```bash
$> npm ci
$> npm run generate
```

---

## Configuration

### Basic Information

```yaml
v4: true
java: true
title: KuFlow
override-client-name: KuFlowClient

# input-file: https://raw.githubusercontent.com/kuflow/kuflow-openapi/2ee94645d20e3a0771db6ac0c9195dade8fabdc4/specs/api.kuflow.com/v2022-10-08/openapi.yaml
input-file: file:///home/jrodped/Projects/kuflow-openapi/specs/api.kuflow.com/v2022-10-08/openapi.yaml
output-folder: ../target/openapi-generated

openapi-type: data-plane
add-credential: true
namespace: com.kuflow.rest
artifact-id: kuflow-rest-client

generate-client-as-impl: true
optional-constant-as-enum: true
enable-sync-stack: true
required-parameter-client-methods: true
pass-discriminator-to-child-deserialization: false
models-subpackage: model
# See: https://github.com/Azure/autorest.java/tree/main/customization-base
customization-class: src/main/java/KuFlowClientCustomization.java

use-extension:
  "@autorest/java": "4.1.17"

modelerfour:
  seal-single-value-enum-by-default: false
```

### Group operations using tag
```yaml
directive:
  - from: openapi-document
    where: $.paths[*][*]
    transform: |
      if ($.operationId.indexOf($.tags[1] + 'Operations_') === -1) {
        $.operationId = $.tags[1] + 'Operations_' + $.operationId;
      }
```

