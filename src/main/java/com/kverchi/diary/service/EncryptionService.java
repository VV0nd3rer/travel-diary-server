package com.kverchi.diary.service;

/**
 * Created by Liudmyla Melnychuk on 14.5.2019.
 */
public interface EncryptionService {
    String encryptText(String clearText, String key);
    String decryptText(String encryptedText, String key);
}
