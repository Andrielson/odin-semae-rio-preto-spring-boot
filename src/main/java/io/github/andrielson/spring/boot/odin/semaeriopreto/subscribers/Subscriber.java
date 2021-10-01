package io.github.andrielson.spring.boot.odin.semaeriopreto.subscribers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Subscriber {

    private String email;
    private Instant createdAt;
}
