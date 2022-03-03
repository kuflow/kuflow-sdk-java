/*
 * Copyright (c) 2021-present KuFlow S.L.
 *
 * All rights reserved.
 */

package com.kuflow.engine.client.activity.email.service;

import com.kuflow.engine.client.activity.email.config.EmailActivitiesProperties;
import com.kuflow.engine.client.activity.email.dto.EmailDto;
import com.kuflow.engine.client.common.error.KuFlowEngineClientException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.mail.internet.MimeMessage;
import org.apache.commons.io.FilenameUtils;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.boot.autoconfigure.thymeleaf.ThymeleafProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;

@Service
public class EmailService {

    private static final Pattern PATTERN_IMG_TAG = Pattern.compile("<img.*src\\s*=\\s*(.*?)[^>]*?>", Pattern.CASE_INSENSITIVE);

    private static final Pattern PATTERN_SRC_ATTRIBUTE = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)");

    private final JavaMailSender mailSender;

    private final ITemplateEngine templateEngine;

    private final MailProperties mailProperties;

    private final EmailActivitiesProperties emailActivitiesProperties;

    private final ApplicationContext applicationContext;

    public EmailService(
        JavaMailSender mailSender,
        ITemplateEngine templateEngine,
        MailProperties mailProperties,
        EmailActivitiesProperties emailActivitiesProperties,
        ApplicationContext applicationContext
    ) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.mailProperties = mailProperties;
        this.emailActivitiesProperties = emailActivitiesProperties;
        this.applicationContext = applicationContext;
    }

    public void sendEmail(EmailDto email) {
        try {
            Context context = new Context();
            context.setLocale(email.getLocale());
            if (this.emailActivitiesProperties.getTemplateVariables().containsKey(email.getTemplate())) {
                context.setVariables(this.emailActivitiesProperties.getTemplateVariables().get(email.getTemplate()));
            }
            context.setVariables(email.getVariables());

            TemplateSpec subjectSpec = new TemplateSpec(email.getTemplate() + ".subject", TemplateMode.TEXT);
            String subject = this.templateEngine.process(subjectSpec, context);
            TemplateSpec htmlSpec = new TemplateSpec(email.getTemplate() + ".html", TemplateMode.HTML);
            String html = this.templateEngine.process(htmlSpec, context);

            EmailResources emailResources = this.extractResources(html);

            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true/* multipart */, StandardCharsets.UTF_8.name());
            message.setFrom(this.mailProperties.getUsername());
            message.setTo(email.getTo());
            message.setSubject(subject.trim());
            message.setText(emailResources.html, true/* isHtml */);

            for (Map.Entry<String, Resource> entry : emailResources.images.entrySet()) {
                message.addInline(entry.getKey(), entry.getValue());
            }

            this.mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new KuFlowEngineClientException("Unable to send email", ex);
        }
    }

    public EmailResources extractResources(@Nonnull String html) {
        Set<String> imageSources = this.findHtmlImagesLocalPath(html);

        EmailResources emailResources = new EmailResources();
        for (String imageSource : imageSources) {
            String contentId = UUID.nameUUIDFromBytes(imageSource.getBytes(StandardCharsets.UTF_8)).toString();
            html = html.replaceAll(imageSource, "cid:" + contentId);

            String imageLocation = FilenameUtils.concat(ThymeleafProperties.DEFAULT_PREFIX, imageSource);
            Resource resource = this.applicationContext.getResource(imageLocation);

            emailResources.images.put(contentId, resource);
        }
        emailResources.html = html;

        return emailResources;
    }

    @Nonnull
    private Set<String> findHtmlImagesLocalPath(@Nonnull String html) {
        Set<String> imageSources = new HashSet<>();

        Matcher matcherImg = PATTERN_IMG_TAG.matcher(html);
        while (matcherImg.find()) {
            String image = matcherImg.group();
            Matcher matcherSrc = PATTERN_SRC_ATTRIBUTE.matcher(image);
            while (matcherSrc.find()) {
                String imagePath = matcherSrc.group(1);
                if (!imagePath.startsWith("http://") && !imagePath.startsWith("https://")) {
                    imageSources.add(matcherSrc.group(1));
                }
            }
        }

        return imageSources;
    }

    private static class EmailResources {

        private String html;

        private final Map<String, Resource> images = new HashMap<>();
    }
}
