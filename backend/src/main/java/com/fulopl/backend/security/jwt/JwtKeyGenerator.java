package com.fulopl.backend.security.jwt;

import java.security.SecureRandom;
import java.util.Base64;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        String secretKey = generateSecretKey(32); // 32 bytes = 256 bits
        System.out.println("Generated JWT Secret Key: " + secretKey);
    }

    public static String generateSecretKey(int size) {
        byte[] key = new byte[size];
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.nextBytes(key);
        return Base64.getEncoder().encodeToString(key);
    }
}

