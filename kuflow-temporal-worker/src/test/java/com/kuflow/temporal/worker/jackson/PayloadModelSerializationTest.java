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

package com.kuflow.temporal.worker.jackson;

import static java.nio.charset.StandardCharsets.UTF_8;
import static org.assertj.core.api.Assertions.assertThat;

import com.azure.core.util.ExpandableStringEnum;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kuflow.temporal.activity.kuflow.model.ProcessFindRequest;
import com.kuflow.temporal.workflow.kuflow.model.SignalUserAction;
import com.kuflow.temporal.workflow.kuflow.model.WorkflowRequest;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.core.type.classreading.CachingMetadataReaderFactory;
import org.springframework.core.type.classreading.MetadataReaderFactory;

/**
 * Guards every model that travels as a Temporal workflow or activity payload against serialization mistakes.
 * <p>
 * A model is broken as soon as it cannot survive a serialize / deserialize / serialize cycle through the worker
 * object mapper, because the worker deserializes exactly what the caller serialized. Asserting that the JSON is
 * stable across the round trip catches a lazily initialized getter combined with an eager setter, a setter
 * overload stealing the property of another one, and any other getter / setter asymmetry.
 */
public class PayloadModelSerializationTest {

    private static final List<String> MODEL_PACKAGES = List.of(
        "com.kuflow.temporal.activity.kuflow.model",
        "com.kuflow.temporal.workflow.kuflow.model"
    );

    private final ObjectMapper objectMapper = KuFlowObjectMapperFactory.createObjectMapper();

    @Test
    @DisplayName("GIVEN a payload model WHEN it is serialized and deserialized THEN the json is preserved")
    public void givenAPayloadModelWhenItIsSerializedAndDeserializedThenTheJsonIsPreserved() throws IOException {
        List<Class<?>> models = this.findModels();

        // A silently empty or partial scan would turn this test into a no-op
        assertThat(models)
            .as("The scan does not reach every model package %s", MODEL_PACKAGES)
            .contains(ProcessFindRequest.class, WorkflowRequest.class)
            .hasSizeGreaterThan(50);

        Map<String, String> failures = new LinkedHashMap<>();
        for (Class<?> model : models) {
            try {
                Object source = model.getDeclaredConstructor().newInstance();

                // Bytes, and not a string, because that is what JacksonJsonPayloadConverter does, and the
                // Autorest deserializer only supports a byte[] source
                byte[] serialized = this.objectMapper.writeValueAsBytes(source);
                Object deserialized = this.objectMapper.readValue(serialized, model);
                byte[] reserialized = this.objectMapper.writeValueAsBytes(deserialized);

                if (!Arrays.equals(serialized, reserialized)) {
                    failures.put(model.getName(), "%s -> %s".formatted(new String(serialized, UTF_8), new String(reserialized, UTF_8)));
                }
            } catch (Exception e) {
                failures.put(model.getName(), "%s: %s".formatted(e.getClass().getSimpleName(), e.getMessage()));
            }
        }

        assertThat(failures).as("Models that do not survive a json round trip").isEmpty();
    }

    @Test
    @DisplayName("GIVEN a signal payload with input WHEN it is deserialized THEN the input survives the round trip")
    public void givenASignalPayloadWithInputWhenItIsDeserializedThenTheInputSurvivesTheRoundTrip() throws IOException {
        SignalUserAction source = new SignalUserAction();
        source.setUserActionDefinitionCode("MY_ACTION");
        source.setInput(Map.of("amount", 100));

        byte[] serialized = this.objectMapper.writeValueAsBytes(source);
        SignalUserAction deserialized = this.objectMapper.readValue(serialized, SignalUserAction.class);

        assertThat(deserialized.getUserActionDefinitionCode()).isEqualTo("MY_ACTION");
        assertThat(deserialized.getInput()).containsEntry("amount", 100);
    }

    @Test
    @DisplayName("GIVEN a signal payload from a newer engine WHEN it carries unknown properties THEN they are ignored")
    public void givenASignalPayloadFromANewerEngineWhenItCarriesUnknownPropertiesThenTheyAreIgnored() throws IOException {
        // Workers built against an older SDK must keep deserializing payloads that gained new properties
        String payload = """
        {"userActionDefinitionCode":"MY_ACTION","input":{"amount":100},"aFutureProperty":"ignored"}""";

        SignalUserAction deserialized = this.objectMapper.readValue(payload.getBytes(UTF_8), SignalUserAction.class);

        assertThat(deserialized.getUserActionDefinitionCode()).isEqualTo("MY_ACTION");
        assertThat(deserialized.getInput()).containsEntry("amount", 100);
    }

    private List<Class<?>> findModels() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        MetadataReaderFactory metadataReaderFactory = new CachingMetadataReaderFactory(resourcePatternResolver);

        List<Class<?>> models = new ArrayList<>();
        for (String modelPackage : MODEL_PACKAGES) {
            String locationPattern = "classpath*:%s/**/*.class".formatted(modelPackage.replace('.', '/'));
            for (Resource resource : resourcePatternResolver.getResources(locationPattern)) {
                String className = metadataReaderFactory.getMetadataReader(resource).getClassMetadata().getClassName();

                Class<?> model;
                try {
                    model = Class.forName(className);
                } catch (ClassNotFoundException | NoClassDefFoundError e) {
                    throw new IllegalStateException("Unable to load " + className, e);
                }

                if (this.isModel(model)) {
                    models.add(model);
                }
            }
        }

        return models;
    }

    /**
     * Keeps the plain beans: anything without a usable no-args constructor is not something Jackson maps as a
     * bean, and {@link ExpandableStringEnum} subclasses are value types serialized as a single string.
     */
    private boolean isModel(Class<?> candidate) {
        if (
            candidate.isInterface() ||
            candidate.isEnum() ||
            candidate.isAnonymousClass() ||
            Modifier.isAbstract(candidate.getModifiers()) ||
            ExpandableStringEnum.class.isAssignableFrom(candidate) ||
            (candidate.getEnclosingClass() != null && !Modifier.isStatic(candidate.getModifiers()))
        ) {
            return false;
        }

        try {
            candidate.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            return false;
        }

        return true;
    }
}
