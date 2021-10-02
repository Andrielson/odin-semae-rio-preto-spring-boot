package io.github.andrielson.spring.boot.odin.semaeriopreto.utils;

import org.springframework.lang.NonNull;

public interface CryptoDigestStringUtils {
    @NonNull
    String decrypt(@NonNull String crypto);

    @NonNull
    String digest(@NonNull String message);

    @NonNull
    String encrypt(@NonNull String message);
}
