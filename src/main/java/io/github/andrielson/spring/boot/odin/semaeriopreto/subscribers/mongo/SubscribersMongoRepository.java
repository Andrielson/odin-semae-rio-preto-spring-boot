package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SubscribersMongoRepository extends MongoRepository<SubscribersDocument, String> {
    Optional<SubscribersDocument> findByEmailHash(@NonNull String email);
}
