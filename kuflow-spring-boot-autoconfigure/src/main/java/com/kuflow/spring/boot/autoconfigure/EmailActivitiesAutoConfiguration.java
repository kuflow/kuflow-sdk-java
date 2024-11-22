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
package com.kuflow.spring.boot.autoconfigure;

import com.kuflow.spring.boot.autoconfigure.properties.EmailActivitiesProperties;
import com.kuflow.temporal.activity.email.EmailActivities;
import com.kuflow.temporal.activity.email.EmailActivitiesImpl;
import com.kuflow.temporal.activity.email.service.EmailService;
import java.nio.charset.StandardCharsets;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;

@AutoConfiguration
@ConditionalOnClass(EmailActivitiesImpl.class)
@EnableConfigurationProperties(EmailActivitiesProperties.class)
public class EmailActivitiesAutoConfiguration {

    private final ThymeleafProperties properties;

    private final ApplicationContext applicationContext;

    public EmailActivitiesAutoConfiguration(ThymeleafProperties properties, ApplicationContext applicationContext) {
        this.properties = properties;
        this.applicationContext = applicationContext;
    }

    @Bean
    public SpringResourceTemplateResolver kuflowEmailTemplateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        resolver.setApplicationContext(this.applicationContext);
        resolver.setPrefix(EmailService.DEFAULT_PREFIX);
        resolver.setSuffix(null);
        resolver.setTemplateMode(this.properties.getMode());
        resolver.setCharacterEncoding(StandardCharsets.UTF_8.name());
        resolver.setCacheable(true);
        resolver.setCheckExistence(true);

        return resolver;
    }

    @Bean
    public SpringTemplateEngine kuflowTemplateEngine(
        ThymeleafProperties properties,
        ObjectProvider<ITemplateResolver> templateResolvers,
        ObjectProvider<IDialect> dialects
    ) {
        SpringTemplateEngine engine = new SpringTemplateEngine() {
            @Override
            public void setMessageSource(final MessageSource messageSource) {
                // Overridden to force using StandardMessageResolver
            }
        };

        engine.setEnableSpringELCompiler(properties.isEnableSpringElCompiler());
        engine.setRenderHiddenMarkersBeforeCheckboxes(properties.isRenderHiddenMarkersBeforeCheckboxes());

        templateResolvers.orderedStream().forEach(engine::addTemplateResolver);
        dialects.orderedStream().forEach(engine::addDialect);

        return engine;
    }

    @Bean
    public EmailService kuflowEmailService(
        JavaMailSender mailSender,
        ITemplateEngine templateEngine,
        EmailActivitiesProperties emailActivitiesProperties,
        ApplicationContext applicationContext,
        MailProperties mailProperties
    ) {
        return EmailService.builder()
            .withMailSender(mailSender)
            .withTemplateEngine(templateEngine)
            .withApplicationContext(applicationContext)
            .withFromEmail(mailProperties.getUsername())
            .withTemplateVariables(emailActivitiesProperties.getTemplateVariables())
            .build();
    }

    @Bean
    public EmailActivities kuflowEmailActivities(EmailService emailService) {
        return new EmailActivitiesImpl(emailService);
    }
}
