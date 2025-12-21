package com.lunghwan.user.common.crypto;

public interface CryptoService {

    String encrypt(String value);

    String decrypt(String value);
}
