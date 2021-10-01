package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers.mongo;

import io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers.Subscriber;
import io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers.SubscribersRepositoryAdapter;
import io.github.andrielson.spring.boot.odin.semaeriopreto.utils.CryptoDigestStringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SubscribersMongoRepositoryAdapter implements SubscribersRepositoryAdapter {
    private final SubscribersMongoRepository repository;
    private final CryptoDigestStringUtils cryptoUtils;

    @Override
    public Optional<Subscriber> findByEmail(String email) {
        return repository.findByEmailHash(cryptoUtils.digest(email))
                .map(this::mapFromDocument);
    }

    @Override
    public Subscriber insert(Subscriber subscriber) {
        return mapFromDocument(repository.insert(mapToDocument(subscriber)));
    }

    @NonNull
    private Subscriber mapFromDocument(@NonNull SubscribersDocument document) {
        return new Subscriber(
                cryptoUtils.decrypt(document.getEncryptedEmail()),
                document.getCreatedAt()
        );
    }

    @NonNull
    private SubscribersDocument mapToDocument(@NonNull Subscriber subscriber) {
        SubscribersDocument document = new SubscribersDocument();
        document.setCreatedAt(subscriber.getCreatedAt());
        document.setEncryptedEmail(cryptoUtils.encrypt(subscriber.getEmail()));
        document.setEmailHash(cryptoUtils.digest(subscriber.getEmail()));
        return document;
    }
}
