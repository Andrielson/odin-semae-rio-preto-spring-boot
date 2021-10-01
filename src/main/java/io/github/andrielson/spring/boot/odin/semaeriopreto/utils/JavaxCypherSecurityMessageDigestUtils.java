package io.github.andrielson.spring.boot.odin.semaeriopreto.utils;

import org.springframework.lang.NonNull;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

public class JavaxCypherSecurityMessageDigestUtils implements CryptoDigestStringUtils {

    private String encryptionKey = "SubscribersSelfRegistrationUseCase";

    @Override
    public String decrypt(String crypto) {
        return getCipher(Cipher.DECRYPT_MODE)
                .flatMap(cipher -> cipherFinal(cipher, crypto.getBytes(StandardCharsets.UTF_8)))
                .map(String::new)
                .orElse(crypto);
    }

    @Override
    public String digest(final String message) {
        return getMessageDigest()
                .map(messageDigest -> {
                    messageDigest.update(message.getBytes(StandardCharsets.UTF_8));
                    return String.format("%064x", new BigInteger(1, messageDigest.digest()));
                }).orElse(message);
    }

    @Override
    public String encrypt(final String message) {
        return getCipher(Cipher.ENCRYPT_MODE)
                .flatMap(cipher -> cipherFinal(cipher, message.getBytes(StandardCharsets.UTF_8)))
                .map(String::new)
                .orElse(message);
    }

    @NonNull
    private Optional<byte[]> cipherFinal(Cipher cipher, byte[] input) {
        try {
            return Optional.of(cipher.doFinal(input));
        } catch (IllegalBlockSizeException | BadPaddingException e) {
            return Optional.empty();
        }
    }

    @NonNull
    private Optional<Cipher> getCipher(int mode) {
        byte[] encryptionKeyBytes = encryptionKey.getBytes(StandardCharsets.UTF_8);
        SecretKey secretKey = new SecretKeySpec(encryptionKeyBytes, "AES");

        try {
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(mode, secretKey);
            return Optional.of(cipher);
        } catch (NoSuchAlgorithmException | NoSuchPaddingException | InvalidKeyException e) {
            return Optional.empty();
        }
    }

    @NonNull
    private Optional<MessageDigest> getMessageDigest() {
        try {
            return Optional.of(MessageDigest.getInstance("SHA-256"));
        } catch (NoSuchAlgorithmException e) {
            return Optional.empty();
        }
    }
}
