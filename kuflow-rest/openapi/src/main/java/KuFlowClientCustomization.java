
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
import static java.util.stream.Collectors.joining;

import com.azure.autorest.customization.ClassCustomization;
import com.azure.autorest.customization.Customization;
import com.azure.autorest.customization.JavadocCustomization;
import com.azure.autorest.customization.LibraryCustomization;
import com.azure.autorest.customization.MethodCustomization;
import com.azure.autorest.customization.PackageCustomization;
import org.slf4j.Logger;

import java.util.stream.Stream;

/**
 * Customizations for KuFlow OpenAPI code generation.
 */
public class KuFlowClientCustomization extends Customization {

    private static final String MODELS_PACKAGE = "com.kuflow.rest.model";

    @Override
    public void customize(LibraryCustomization libraryCustomization, Logger logger) {
        PackageCustomization modelPackageCustomization = libraryCustomization.getPackage(MODELS_PACKAGE);

        this.customizeProcessModel(modelPackageCustomization);
        this.customizeProcessPageItemModel(modelPackageCustomization);
        this.customizeProcessProcessSaveElementCommand(modelPackageCustomization);

        this.customizeTaskModel(modelPackageCustomization);
        this.customizeTaskPageItemModel(modelPackageCustomization);
        this.customizeTaskSaveElementCommandModel(modelPackageCustomization);
    }

    private void customizeProcessModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("Process");

        this.addMethodGetElementValueValid(modelClassCustomization, "ProcessUtils", true);
        this.addMethodGetElementValueValidAt(modelClassCustomization, "ProcessUtils", true);
        this.addMethodSetElementValueValid(modelClassCustomization, "ProcessUtils", true);
        this.addMethodSetElementValueValidAt(modelClassCustomization, "ProcessUtils", true);

        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessUtils", true, "String", "String");
        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessUtils", true, "Double", "Double");
        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessUtils", true, "LocalDate", "LocalDate");

        modelClassCustomization.addImports(
            "com.kuflow.rest.util.ProcessUtils",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );
    }

    private void customizeProcessPageItemModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("ProcessPageItem");

        this.addMethodGetElementValueValid(modelClassCustomization, "ProcessPageItemUtils", true);
        this.addMethodGetElementValueValidAt(modelClassCustomization, "ProcessPageItemUtils", true);
        this.addMethodSetElementValueValid(modelClassCustomization, "ProcessPageItemUtils", true);
        this.addMethodSetElementValueValidAt(modelClassCustomization, "ProcessPageItemUtils", true);

        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessPageItemUtils", true, "String", "String");
        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessPageItemUtils", true, "Double", "Double");
        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessPageItemUtils", true, "LocalDate", "LocalDate");

        modelClassCustomization.addImports(
            "com.kuflow.rest.util.ProcessPageItemUtils",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );
    }

    private void customizeProcessProcessSaveElementCommand(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("ProcessSaveElementCommand");

        this.addMethodGetElementValueValid(modelClassCustomization, "ProcessSaveElementCommandUtils", false);
        this.addMethodGetElementValueValidAt(modelClassCustomization, "ProcessSaveElementCommandUtils", false);
        this.addMethodSetElementValueValid(modelClassCustomization, "ProcessSaveElementCommandUtils", false);
        this.addMethodSetElementValueValidAt(modelClassCustomization, "ProcessSaveElementCommandUtils", false);

        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessSaveElementCommandUtils", false, "String", "String");
        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessSaveElementCommandUtils", false, "Double", "Double");
        this.addMethodsElementValuesByType(modelClassCustomization, "ProcessSaveElementCommandUtils", false, "LocalDate", "LocalDate");

        modelClassCustomization.addImports(
            "com.kuflow.rest.util.ProcessSaveElementCommandUtils",
            "java.time.LocalDate",
            "java.util.Map",
            "java.util.Optional"
        );
    }

    private void customizeTaskSaveElementCommandModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("TaskSaveElementCommand");

        this.addMethodGetElementValueValid(modelClassCustomization, "TaskSaveElementCommandUtils", false);
        this.addMethodGetElementValueValidAt(modelClassCustomization, "TaskSaveElementCommandUtils", false);
        this.addMethodSetElementValueValid(modelClassCustomization, "TaskSaveElementCommandUtils", false);
        this.addMethodSetElementValueValidAt(modelClassCustomization, "TaskSaveElementCommandUtils", false);

        this.addMethodsElementValuesByType(modelClassCustomization, "TaskSaveElementCommandUtils", false, "String", "String");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskSaveElementCommandUtils", false, "Double", "Double");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskSaveElementCommandUtils", false, "LocalDate", "LocalDate");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskSaveElementCommandUtils", false, "Map<String, Object>", "Map");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskSaveElementCommandUtils", false, "TaskElementValueDocumentItem", "Document");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskSaveElementCommandUtils", false, "TaskElementValuePrincipalItem", "Principal");

        modelClassCustomization.addImports(
                "com.kuflow.rest.util.TaskSaveElementCommandUtils",
                "java.time.LocalDate",
                "java.util.Map",
                "java.util.Optional"
        );
    }

    private void customizeTaskModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("Task");

        this.addMethodGetElementValueValid(modelClassCustomization, "TaskUtils", true);
        this.addMethodGetElementValueValidAt(modelClassCustomization, "TaskUtils", true);
        this.addMethodSetElementValueValid(modelClassCustomization, "TaskUtils", true);
        this.addMethodSetElementValueValidAt(modelClassCustomization, "TaskUtils", true);

        this.addMethodsElementValuesByType(modelClassCustomization, "TaskUtils", true, "String", "String");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskUtils", true, "Double", "Double");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskUtils", true, "LocalDate", "LocalDate");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskUtils", true, "Map<String, Object>", "Map");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskUtils", true, "TaskElementValueDocumentItem", "Document");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskUtils", true, "TaskElementValuePrincipalItem", "Principal");

        modelClassCustomization.addImports(
                "com.kuflow.rest.util.TaskUtils",
                "java.time.LocalDate",
                "java.util.Map",
                "java.util.Optional"
        );
    }

    private void customizeTaskPageItemModel(PackageCustomization modelPackageCustomization) {
        ClassCustomization modelClassCustomization = modelPackageCustomization.getClass("TaskPageItem");

        this.addMethodGetElementValueValid(modelClassCustomization, "TaskPageItemUtils", true);
        this.addMethodGetElementValueValidAt(modelClassCustomization, "TaskPageItemUtils", true);
        this.addMethodSetElementValueValid(modelClassCustomization, "TaskPageItemUtils", true);
        this.addMethodSetElementValueValidAt(modelClassCustomization, "TaskPageItemUtils", true);

        this.addMethodsElementValuesByType(modelClassCustomization, "TaskPageItemUtils", true, "String", "String");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskPageItemUtils", true, "Double", "Double");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskPageItemUtils", true, "LocalDate", "LocalDate");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskPageItemUtils", true, "Map<String, Object>", "Map");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskPageItemUtils", true, "TaskElementValueDocumentItem", "Document");
        this.addMethodsElementValuesByType(modelClassCustomization, "TaskPageItemUtils", true, "TaskElementValuePrincipalItem", "Principal");

        modelClassCustomization.addImports(
                "com.kuflow.rest.util.TaskPageItemUtils",
                "java.time.LocalDate",
                "java.util.Map",
                "java.util.Optional"
        );
    }

    private void addMethodsElementValuesByType(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        this.addMethodPutElementValueAs(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
        this.addMethodPutElementValueAsList(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
        this.addMethodAddElementValueAs(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
        this.addMethodAddElementValueAsList(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
        this.addMethodGetElementValueAs(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
        this.addMethodGetElementValueAsList(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
        this.addMethodFindElementValueAs(modelClassCustomization, helperClass, userCode, elementValueType, elementValueTypeName);
    }

    private void addMethodGetElementValueValid(ClassCustomization modelClassCustomization, String helperClass, boolean userCode) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public Boolean getElementValueValid(" + (userCode ? "String elementDefinitionCode" : "") + ") {",
                "    return " + helperClass + ".getElementValueValid(this" + (userCode ? ", elementDefinitionCode" : "") + ");",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Check if all related valid values are TRUE")
                .setReturn("TRUE if all related valid values are TRUE else FALSE.");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodGetElementValueValidAt(ClassCustomization modelClassCustomization, String helperClass, boolean userCode) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public Boolean getElementValueValidAt(" + (userCode ? "String elementDefinitionCode, " : "") + "int index) {",
                "    return " + helperClass + ".getElementValueValidAt(this" + (userCode ? ", elementDefinitionCode" : "") + ", index);",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Check if all related valid values are TRUE")
                .setReturn("The requested valid value");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .setParam("index", "Element value index")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodSetElementValueValid(ClassCustomization modelClassCustomization, String helperClass, boolean userCode) {
        String className = modelClassCustomization.getClassName();
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " setElementValueValid(" + (userCode ? "String elementDefinitionCode, " : "") + "Boolean valid) {",
                "    " + helperClass + ".setElementValueValid(this" + (userCode ? ", elementDefinitionCode" : "") + ", valid);",
                "    ",
                "    return this;",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Set valid to all values")
                .setReturn("the object itself");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
            .setParam("valid", "Valid value")
            .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodSetElementValueValidAt(ClassCustomization modelClassCustomization, String helperClass, boolean userCode) {
        String className = modelClassCustomization.getClassName();
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " setElementValueValidAt(" + (userCode ? "String elementDefinitionCode, " : "") + "Boolean valid, int index) {",
                "    " + helperClass + ".setElementValueValidAt(this" + (userCode ? ", elementDefinitionCode" : "") + ", valid, index);",
                "    ",
                "    return this;",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Set valid to the selected value")
                .setReturn("the object itself");
        if (userCode) {
            javadocCustomization
                    .setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .setParam("valid", "Valid value")
                .setParam("index", "Element value index")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodPutElementValueAs(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        String className = modelClassCustomization.getClassName();

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " setElementValueAs" + elementValueTypeName + "(" + (userCode ? "String elementDefinitionCode, " : "")
                    + elementValueType + " elementValue) {",
                "    " + helperClass + ".setElementValueAs" + elementValueTypeName + "(this" + (userCode ? ", elementDefinitionCode" : "") + ", elementValue);",
                "    ",
                "    return this;",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Set an element value")
                .setReturn("the object itself");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .setParam("elementValue", "Element value, if the value is null all current values are removed")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodPutElementValueAsList(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        String className = modelClassCustomization.getClassName();

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " setElementValueAs" + elementValueTypeName + "List("
                    + (userCode ? "String elementDefinitionCode, " : "") + "List<" + elementValueType
                    + "> elementValues) {",
                "    " + helperClass + ".setElementValueAs" + elementValueTypeName + "List(this" + (userCode ? ", elementDefinitionCode" : "") + ", elementValues);",
                "    ",
                "    return this;",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Set all element values passed, previews values will be removed")
                .setReturn("the object itself");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .setParam("elementValues", "Element values, if the values are null all current values are removed")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodAddElementValueAs(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        String className = modelClassCustomization.getClassName();
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " addElementValueAs" + elementValueTypeName + "("
                        + (userCode ? "String elementDefinitionCode, " : "") + elementValueType + " elementValue) {",
                "    " + helperClass + ".addElementValueAs" + elementValueTypeName + "(this" + (userCode ? ", elementDefinitionCode" : "") + ", elementValue);",
                "    ",
                "    return this;",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Add a new element value")
                .setReturn("the object itself");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .setParam("elementValue", "Element value, if the values is null the value is not added")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodAddElementValueAsList(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        String className = modelClassCustomization.getClassName();

        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + className + " addElementValueAs" + elementValueTypeName + "List("
                        + (userCode ? "String elementDefinitionCode, " : "") + "List<" + elementValueType
                        + "> elementValues) {",
                "    " + helperClass + ".addElementValueAs" + elementValueTypeName + "List(this" + (userCode ? ", elementDefinitionCode" : "") + ", elementValues);",
                "    ",
                "    return this;",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Add all element values passed")
                .setReturn("the object itself");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .setParam("elementValues", "Element values")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodGetElementValueAs(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public " + elementValueType + " getElementValueAs" + elementValueTypeName + "("
                        + (userCode ? "String elementDefinitionCode" : "") + ") {",
                "    return " + helperClass + ".getElementValueAs" + elementValueTypeName + "(this" + (userCode ? ", elementDefinitionCode" : "") + ");",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Get an element as " + elementValueTypeName)
                .setReturn("the element value.");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
                .addThrows("com.kuflow.rest.KuFlowRestClientException", "If element value doesn't exist")
                .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodGetElementValueAsList(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public List<" + elementValueType + "> getElementValueAs" + elementValueTypeName + "List("
                        + (userCode ? "String elementDefinitionCode" : "") + ") {",
                "    return " + helperClass + ".getElementValueAs" + elementValueTypeName + "List(this" + (userCode ? ", elementDefinitionCode" : "") + ");",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Get all elements as " + elementValueTypeName)
                .setReturn("the elements values.");
        if (userCode) {
            javadocCustomization = javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization
            .addThrows("com.kuflow.rest.KuFlowRestClientException", "If element value doesn't exist")
            .setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private void addMethodFindElementValueAs(
        ClassCustomization modelClassCustomization,
        String helperClass,
        boolean userCode,
        String elementValueType,
        String elementValueTypeName
    ) {
        MethodCustomization methodCustomization = modelClassCustomization.addMethod(joinWithNewline("",
                "public Optional<" + elementValueType + "> findElementValueAs" + elementValueTypeName + "("
                        + (userCode ? "String elementDefinitionCode" : "") + ") {",
                "    return " + helperClass + ".findElementValueAs" + elementValueTypeName + "(this" + (userCode ? ", elementDefinitionCode" : "") + ");",
                "}"));

        methodCustomization = methodCustomization.addAnnotation("@Deprecated");

        JavadocCustomization javadocCustomization = methodCustomization.getJavadoc()
                .setDescription("Try to get an element as " + elementValueTypeName)
                .setReturn("the element value if exists.");
        if (userCode) {
            javadocCustomization.setParam("elementDefinitionCode", "Element Definition Code");
        }
        javadocCustomization.setDeprecated("in favor of {@link " + helperClass + "}");
    }

    private String joinWithNewline(String... lines) {
        return Stream.of(lines).filter(line -> line.length() > 0).collect(joining("\n"));
    }

}
