package io.github.andrielson.spring.boot.odin.semaeriopreto.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.stereotype.Component;

@Component
public class CommonsJasyptCryptoUtils implements CryptoDigestStringUtils {

    private final AES256TextEncryptor textEncryptor;

    public CommonsJasyptCryptoUtils() {
        textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword("myEncryptionPassword");
    }

    @Override
    public String decrypt(String encryptedMessage) {
        return textEncryptor.decrypt(encryptedMessage);
    }

    @Override
    public String digest(String message) {
        return DigestUtils.sha512Hex(message);
    }

    @Override
    public String encrypt(String message) {
        return textEncryptor.encrypt(message);
    }
}
