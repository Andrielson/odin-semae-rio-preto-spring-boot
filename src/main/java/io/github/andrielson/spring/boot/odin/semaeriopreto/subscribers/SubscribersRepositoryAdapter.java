package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers;

import org.springframework.lang.NonNull;

import java.util.Optional;

public interface SubscribersRepositoryAdapter {
    Optional<Subscriber> findByEmail(@NonNull String email);
    Subscriber insert(@NonNull Subscriber subscriber);
}
