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

import com.azure.core.annotation.Fluent;
import com.azure.core.annotation.Generated;
import com.azure.json.JsonReader;
import com.azure.json.JsonSerializable;
import com.azure.json.JsonToken;
import com.azure.json.JsonWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

/**
 * The assignment of a role of a Process Definition, or of one of its tasks or threads, to a principal. A role
 * with no principal comes once, without principalType.
 */
@Fluent
public final class ProcessDefinitionRbacEntry implements JsonSerializable<ProcessDefinitionRbacEntry> {

    /*
     * The code of the role, or of the role that a custom role belongs to.
     */
    @Generated
    private String role;

    /*
     * The roleName property.
     */
    @Generated
    private String roleName;

    /*
     * The roleArchived property.
     */
    @Generated
    private Boolean roleArchived;

    /*
     * The principalType property.
     */
    @Generated
    private ProcessDefinitionRbacPrincipalType principalType;

    /*
     * The id of the principal, also when it no longer exists.
     */
    @Generated
    private UUID principalId;

    /*
     * With a GROUP principal that exists.
     */
    @Generated
    private String groupCode;

    /*
     * With an API_KEY principal that exists.
     */
    @Generated
    private String apiKeyCode;

    /*
     * With a TENANT_USER principal that exists.
     */
    @Generated
    private String tenantUserEmail;

    /*
     * With an EXPRESSION principal.
     */
    @Generated
    private String expression;

    /**
     * Creates an instance of ProcessDefinitionRbacEntry class.
     */
    @Generated
    public ProcessDefinitionRbacEntry() {}

    /**
     * Get the role property: The code of the role, or of the role that a custom role belongs to.
     *
     * @return the role value.
     */
    @Generated
    public String getRole() {
        return this.role;
    }

    /**
     * Set the role property: The code of the role, or of the role that a custom role belongs to.
     *
     * @param role the role value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setRole(String role) {
        this.role = role;
        return this;
    }

    /**
     * Get the roleName property: The roleName property.
     *
     * @return the roleName value.
     */
    @Generated
    public String getRoleName() {
        return this.roleName;
    }

    /**
     * Set the roleName property: The roleName property.
     *
     * @param roleName the roleName value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    /**
     * Get the roleArchived property: The roleArchived property.
     *
     * @return the roleArchived value.
     */
    @Generated
    public Boolean isRoleArchived() {
        return this.roleArchived;
    }

    /**
     * Set the roleArchived property: The roleArchived property.
     *
     * @param roleArchived the roleArchived value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setRoleArchived(Boolean roleArchived) {
        this.roleArchived = roleArchived;
        return this;
    }

    /**
     * Get the principalType property: The principalType property.
     *
     * @return the principalType value.
     */
    @Generated
    public ProcessDefinitionRbacPrincipalType getPrincipalType() {
        return this.principalType;
    }

    /**
     * Set the principalType property: The principalType property.
     *
     * @param principalType the principalType value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setPrincipalType(ProcessDefinitionRbacPrincipalType principalType) {
        this.principalType = principalType;
        return this;
    }

    /**
     * Get the principalId property: The id of the principal, also when it no longer exists.
     *
     * @return the principalId value.
     */
    @Generated
    public UUID getPrincipalId() {
        return this.principalId;
    }

    /**
     * Set the principalId property: The id of the principal, also when it no longer exists.
     *
     * @param principalId the principalId value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setPrincipalId(UUID principalId) {
        this.principalId = principalId;
        return this;
    }

    /**
     * Get the groupCode property: With a GROUP principal that exists.
     *
     * @return the groupCode value.
     */
    @Generated
    public String getGroupCode() {
        return this.groupCode;
    }

    /**
     * Set the groupCode property: With a GROUP principal that exists.
     *
     * @param groupCode the groupCode value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setGroupCode(String groupCode) {
        this.groupCode = groupCode;
        return this;
    }

    /**
     * Get the apiKeyCode property: With an API_KEY principal that exists.
     *
     * @return the apiKeyCode value.
     */
    @Generated
    public String getApiKeyCode() {
        return this.apiKeyCode;
    }

    /**
     * Set the apiKeyCode property: With an API_KEY principal that exists.
     *
     * @param apiKeyCode the apiKeyCode value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setApiKeyCode(String apiKeyCode) {
        this.apiKeyCode = apiKeyCode;
        return this;
    }

    /**
     * Get the tenantUserEmail property: With a TENANT_USER principal that exists.
     *
     * @return the tenantUserEmail value.
     */
    @Generated
    public String getTenantUserEmail() {
        return this.tenantUserEmail;
    }

    /**
     * Set the tenantUserEmail property: With a TENANT_USER principal that exists.
     *
     * @param tenantUserEmail the tenantUserEmail value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setTenantUserEmail(String tenantUserEmail) {
        this.tenantUserEmail = tenantUserEmail;
        return this;
    }

    /**
     * Get the expression property: With an EXPRESSION principal.
     *
     * @return the expression value.
     */
    @Generated
    public String getExpression() {
        return this.expression;
    }

    /**
     * Set the expression property: With an EXPRESSION principal.
     *
     * @param expression the expression value to set.
     * @return the ProcessDefinitionRbacEntry object itself.
     */
    @Generated
    public ProcessDefinitionRbacEntry setExpression(String expression) {
        this.expression = expression;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Generated
    @Override
    public JsonWriter toJson(JsonWriter jsonWriter) throws IOException {
        jsonWriter.writeStartObject();
        jsonWriter.writeStringField("role", this.role);
        jsonWriter.writeStringField("roleName", this.roleName);
        jsonWriter.writeBooleanField("roleArchived", this.roleArchived);
        jsonWriter.writeStringField("principalType", this.principalType == null ? null : this.principalType.toString());
        jsonWriter.writeStringField("principalId", Objects.toString(this.principalId, null));
        jsonWriter.writeStringField("groupCode", this.groupCode);
        jsonWriter.writeStringField("apiKeyCode", this.apiKeyCode);
        jsonWriter.writeStringField("tenantUserEmail", this.tenantUserEmail);
        jsonWriter.writeStringField("expression", this.expression);
        return jsonWriter.writeEndObject();
    }

    /**
     * Reads an instance of ProcessDefinitionRbacEntry from the JsonReader.
     *
     * @param jsonReader The JsonReader being read.
     * @return An instance of ProcessDefinitionRbacEntry if the JsonReader was pointing to an instance of it, or null if
     * it was pointing to JSON null.
     * @throws IllegalStateException If the deserialized JSON object was missing any required properties.
     * @throws IOException If an error occurs while reading the ProcessDefinitionRbacEntry.
     */
    @Generated
    public static ProcessDefinitionRbacEntry fromJson(JsonReader jsonReader) throws IOException {
        return jsonReader.readObject(reader -> {
            ProcessDefinitionRbacEntry deserializedProcessDefinitionRbacEntry = new ProcessDefinitionRbacEntry();
            while (reader.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = reader.getFieldName();
                reader.nextToken();

                if ("role".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.role = reader.getString();
                } else if ("roleName".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.roleName = reader.getString();
                } else if ("roleArchived".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.roleArchived = reader.getNullable(JsonReader::getBoolean);
                } else if ("principalType".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.principalType = ProcessDefinitionRbacPrincipalType.fromString(
                        reader.getString()
                    );
                } else if ("principalId".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.principalId = reader.getNullable(nonNullReader ->
                        UUID.fromString(nonNullReader.getString())
                    );
                } else if ("groupCode".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.groupCode = reader.getString();
                } else if ("apiKeyCode".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.apiKeyCode = reader.getString();
                } else if ("tenantUserEmail".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.tenantUserEmail = reader.getString();
                } else if ("expression".equals(fieldName)) {
                    deserializedProcessDefinitionRbacEntry.expression = reader.getString();
                } else {
                    reader.skipChildren();
                }
            }

            return deserializedProcessDefinitionRbacEntry;
        });
    }
}
