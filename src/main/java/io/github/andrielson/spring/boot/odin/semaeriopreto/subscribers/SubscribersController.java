package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping(path = "/subscribers")
@RequiredArgsConstructor
@RestController
public class SubscribersController {

    private final SubscribersSelfRegistrationUseCase subscribersSelfRegistrationUseCase;

    @NonNull
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@NonNull @RequestBody @Valid SubscribersSelfRegistrationRequest request) {
        this.subscribersSelfRegistrationUseCase.registerSubscriber(request.getEmail());
        return ResponseEntity.accepted().build();
    }
}
