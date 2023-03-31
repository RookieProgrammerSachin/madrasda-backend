package com.example.madrasdaapi.utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Encryptor {
    public static final String ENCRYPTION_ALGORITHM = "AES";
    public static final String SECRET_KEY = "thisisasecretkey";
    public static String encrypt(String plainText) throws Exception {
        byte[] key = SECRET_KEY.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encrypted);
    }
    public static String decrypt(String encryptedText) throws Exception {
        byte[] key = SECRET_KEY.getBytes();
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, ENCRYPTION_ALGORITHM);
        Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        byte[] decoded = Base64.getDecoder().decode(encryptedText.getBytes());
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted);
    }
}
