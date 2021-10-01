package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class SubscribersSelfRegistrationRequest {
    @Email
    @NotNull(message = "O e-mail não pode ser nulo")
    private String email;
}
