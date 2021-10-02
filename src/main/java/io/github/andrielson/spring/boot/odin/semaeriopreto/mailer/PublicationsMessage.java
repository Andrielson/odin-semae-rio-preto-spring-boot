package io.github.andrielson.spring.boot.odin.semaeriopreto.mailer;

import io.github.andrielson.spring.boot.odin.semaeriopreto.publications.Publication;
import org.springframework.lang.NonNull;
import org.thymeleaf.context.Context;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class PublicationsMessage implements EmailMessage {
    private final String subject;
    private final String to;
    private final Context context = new Context();

    public PublicationsMessage(@NonNull String to, @NonNull LocalDate localDate, @NonNull Set<Publication> publications) {
        this.to = to;
        String datePattern = "dd/MM/yyyy";
        String formattedDate = localDate.format(DateTimeFormatter.ofPattern(datePattern));
        subject = "Publicações do dia " + formattedDate;
        context.setVariable("date", formattedDate);
        context.setVariable("publications", publications);
    }

    @Override
    public String getHtml() {
        return EmailBuilder.HTML.getTemplateEngine().process("publications-message.html", context);
    }

    @Override
    public String getText() {
        return EmailBuilder.TEXT.getTemplateEngine().process("publications-message.txt", context);
    }

    @Override
    public String getTo() {
        return to;
    }

    @Override
    public String getSubject() {
        return subject;
    }
}
