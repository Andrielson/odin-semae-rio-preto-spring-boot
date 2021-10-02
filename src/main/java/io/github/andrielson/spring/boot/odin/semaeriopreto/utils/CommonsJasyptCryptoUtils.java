package io.github.andrielson.spring.boot.odin.semaeriopreto.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.jasypt.util.text.AES256TextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class CommonsJasyptCryptoUtils implements CryptoDigestStringUtils {

    private final AES256TextEncryptor textEncryptor;

    @Autowired
    public CommonsJasyptCryptoUtils(@Value("${odin.text.encryptor.password}") final String password) {
        textEncryptor = new AES256TextEncryptor();
        textEncryptor.setPassword(password);
    }

    @Override
    public String decrypt(String encryptedMessage) {
        return textEncryptor.decrypt(encryptedMessage);
    }

    @Override
    public String digest(String message) {
        return DigestUtils.sha256Hex(message);
    }

    @Override
    public String encrypt(String message) {
        return textEncryptor.encrypt(message);
    }
}
