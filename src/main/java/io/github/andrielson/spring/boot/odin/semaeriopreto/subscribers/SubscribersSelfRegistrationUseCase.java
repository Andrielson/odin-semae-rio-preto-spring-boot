package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers;

import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import java.time.Instant;

@RequiredArgsConstructor
@Service
public class SubscribersSelfRegistrationUseCase {
    private final SubscribersRepositoryAdapter subscribersRepository;

    public void registerSubscriber(@NonNull String email) {
        if (subscribersRepository.findByEmail(email).isPresent()) return;
        subscribersRepository.insert(new Subscriber(email, Instant.now()));
    }
}
