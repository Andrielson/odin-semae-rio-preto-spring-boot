package io.github.andrielson.spring.boot.odin.semaeriopreto.mailer;

import org.springframework.lang.NonNull;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public enum EmailBuilder {
    HTML(TemplateMode.HTML),
    TEXT(TemplateMode.TEXT);

    private final TemplateEngine templateEngine;

    EmailBuilder(@NonNull TemplateMode templateMode) {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("/templates/");
        templateResolver.setTemplateMode(templateMode);
        templateEngine = new SpringTemplateEngine();
        templateEngine.addTemplateResolver(templateResolver);
    }

    public @NonNull TemplateEngine getTemplateEngine() {
        return templateEngine;
    }
}
