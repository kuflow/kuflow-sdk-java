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
package com.kuflow.temporal.activity.email.service;

import com.kuflow.temporal.activity.email.dto.EmailDto;
import com.kuflow.temporal.activity.email.model.EmailAttachment;
import com.kuflow.temporal.activity.email.model.EmailPriority;
import com.kuflow.temporal.common.error.KuFlowTemporalException;
import jakarta.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import org.apache.commons.io.FilenameUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.ITemplateEngine;
import org.thymeleaf.TemplateSpec;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;

public class EmailService {

    public static EmailServiceBuilder builder() {
        return new EmailServiceBuilder();
    }

    public static final String DEFAULT_PREFIX = "classpath:/templates/";

    private static final Pattern PATTERN_IMG_TAG = Pattern.compile("<img.*src\\s*=\\s*(.*?)[^>]*?>", Pattern.CASE_INSENSITIVE);

    private static final Pattern PATTERN_SRC_ATTRIBUTE = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)");

    private final JavaMailSender mailSender;

    private final ITemplateEngine templateEngine;

    private final Map<String, Map<String, Object>> templateVariables;

    private final ApplicationContext applicationContext;

    private final String fromEmail;

    private EmailService(
        JavaMailSender mailSender,
        ITemplateEngine templateEngine,
        Map<String, Map<String, Object>> templateVariables,
        ApplicationContext applicationContext,
        String fromEmail
    ) {
        Objects.requireNonNull(mailSender, "'mailSender' is required");
        Objects.requireNonNull(templateEngine, "'templateEngine' is required");
        Objects.requireNonNull(templateVariables, "'templateVariables' is required");
        Objects.requireNonNull(applicationContext, "'applicationContext' is required");
        Objects.requireNonNull(fromEmail, "'fromEmail' is required");

        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
        this.templateVariables = templateVariables;
        this.applicationContext = applicationContext;
        this.fromEmail = fromEmail;
    }

    public void sendEmail(EmailDto email) {
        try {
            Context context = new Context();
            context.setLocale(email.getLocale());
            if (this.templateVariables.containsKey(email.getTemplate())) {
                context.setVariables(this.templateVariables.get(email.getTemplate()));
            }
            context.setVariables(email.getVariables());

            String subject = this.calculateSubject(email, context);

            TemplateSpec htmlSpec = new TemplateSpec(email.getTemplate() + ".html", TemplateMode.HTML);
            String html = this.templateEngine.process(htmlSpec, context);

            EmailResources emailResources = this.extractResources(html);

            MimeMessage mimeMessage = this.mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true /* multipart */, StandardCharsets.UTF_8.name());
            message.setFrom(this.fromEmail);

            // Set recipients
            if (!email.getToAddresses().isEmpty()) {
                message.setTo(email.getToAddresses().toArray(new String[0]));
            }
            if (!email.getCcAddresses().isEmpty()) {
                message.setCc(email.getCcAddresses().toArray(new String[0]));
            }
            if (!email.getBccAddresses().isEmpty()) {
                message.setBcc(email.getBccAddresses().toArray(new String[0]));
            }

            // Set reply-to if provided
            String replyTo = email.getReplyTo();
            if (replyTo != null && !replyTo.trim().isEmpty()) {
                message.setReplyTo(replyTo.trim());
            }

            // Set priority if provided
            EmailPriority priority = email.getPriority();
            if (priority != null) {
                message.setPriority(priority.getValue());
            }

            message.setSubject(subject);
            message.setText(emailResources.html, true /* isHtml */);

            // Add inline images
            for (Map.Entry<String, Resource> entry : emailResources.images.entrySet()) {
                message.addInline(entry.getKey(), entry.getValue());
            }

            // Add attachments
            for (EmailAttachment attachment : email.getAttachments()) {
                message.addAttachment(attachment.getFilename(), attachment.getResource());
            }

            this.mailSender.send(mimeMessage);
        } catch (Exception ex) {
            throw new KuFlowTemporalException("Unable to send email", ex);
        }
    }

    private String calculateSubject(EmailDto email, Context context) {
        // Handle subject - use override if provided, otherwise use template
        String subjectOverride = email.getSubjectOverride();
        if (subjectOverride != null && !subjectOverride.trim().isEmpty()) {
            return subjectOverride.trim();
        } else {
            TemplateSpec subjectSpec = new TemplateSpec(email.getTemplate() + ".subject", TemplateMode.TEXT);
            return this.templateEngine.process(subjectSpec, context).trim();
        }
    }

    private EmailResources extractResources(@Nonnull String html) {
        Set<String> imageSources = this.findHtmlImagesLocalPath(html);

        EmailResources emailResources = new EmailResources();
        for (String imageSource : imageSources) {
            String contentId = UUID.nameUUIDFromBytes(imageSource.getBytes(StandardCharsets.UTF_8)).toString();
            html = html.replaceAll(imageSource, "cid:" + contentId);

            String imageLocation = FilenameUtils.concat(DEFAULT_PREFIX, imageSource);
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

    public static final class EmailServiceBuilder {

        private JavaMailSender mailSender;
        private ITemplateEngine templateEngine;
        private Map<String, Map<String, Object>> templateVariables = new HashMap<>();
        private ApplicationContext applicationContext;
        private String fromEmail;

        private EmailServiceBuilder() {}

        public EmailServiceBuilder withMailSender(JavaMailSender mailSender) {
            this.mailSender = mailSender;

            return this;
        }

        public EmailServiceBuilder withTemplateEngine(ITemplateEngine templateEngine) {
            this.templateEngine = templateEngine;

            return this;
        }

        public EmailServiceBuilder withTemplateVariables(Map<String, Map<String, Object>> templateVariables) {
            this.templateVariables = templateVariables;

            return this;
        }

        public EmailServiceBuilder withApplicationContext(ApplicationContext applicationContext) {
            this.applicationContext = applicationContext;

            return this;
        }

        public EmailServiceBuilder withFromEmail(String fromEmail) {
            this.fromEmail = fromEmail;

            return this;
        }

        public EmailService build() {
            return new EmailService(this.mailSender, this.templateEngine, this.templateVariables, this.applicationContext, this.fromEmail);
        }
    }
}
