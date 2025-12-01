package com.lunghwan.user;

import org.jasypt.encryption.StringEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class JasyptConfigTest {

    @Autowired
    @Qualifier("jasyptStringEncryptor")
    private StringEncryptor encryptor;

    @Test
    void encryptTest() throws Exception {
        // given
        String plainText = "오정환";

        // when
        String encrypted = encryptor.encrypt(plainText);
        String decrypted = encryptor.decrypt(encrypted);

        // then
        System.out.println("원문: " + plainText);
        System.out.println("암호화: " + encrypted);
        System.out.println("복호화: " + decrypted);

        assertThat(decrypted).isEqualTo(plainText);
    }
}