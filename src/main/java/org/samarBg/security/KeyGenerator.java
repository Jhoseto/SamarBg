package org.samarBg.security;

import java.security.SecureRandom;
import java.util.Base64;

public class KeyGenerator {
    private static final int KEY_LENGTH = 32; // Дължина на ключа в байтове

    public static String generateKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] keyBytes = new byte[KEY_LENGTH];
        secureRandom.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }


}


