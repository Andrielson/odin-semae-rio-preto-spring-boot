package io.github.andrielson.spring.boot.odin.semaeriopreto.mailer;

import org.springframework.lang.NonNull;

public interface EmailMessage {
    @NonNull
    String getHtml();

    @NonNull
    String getText();

    @NonNull
    String getTo();

    @NonNull
    String getSubject();
}
