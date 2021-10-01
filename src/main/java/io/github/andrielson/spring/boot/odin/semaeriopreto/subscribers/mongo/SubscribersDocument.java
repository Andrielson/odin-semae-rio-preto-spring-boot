package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers.mongo;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Data
@Document("subscribers")
public class SubscribersDocument {

    @Id
    private ObjectId id;

    @Indexed(unique = true)
    private String emailHash;

    private String encryptedEmail;

    private Instant createdAt;
}
