package com.lunghwan.user.common.crypto;

import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class JasyptEncryptor implements CryptoService {

    private final StringEncryptor encryptor;

    public JasyptEncryptor(@Qualifier("jasyptStringEncryptor") StringEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    @Override
    public String encrypt(String value) {
        return encryptor.encrypt(value);
    }

    @Override
    public String decrypt(String value) {
        return encryptor.decrypt(value);
    }
}
