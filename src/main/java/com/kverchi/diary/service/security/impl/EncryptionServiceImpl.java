package com.kverchi.diary.service.security.impl;

import com.kverchi.diary.service.security.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * Created by Liudmyla Melnychuk on 14.5.2019.
 */
@Service
public class EncryptionServiceImpl implements EncryptionService {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionServiceImpl.class);

    private final static String ENCRYPTION_ALGORITHM = "AES/ECB/PKCS5Padding";

    @Override
    public String encryptText(String clearText, String key) {
        String encryptedText = null;
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            byte[] encodedByte = Base64.getEncoder().encode(cipher.doFinal(clearText.getBytes()));
            encryptedText = new String(encodedByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return encryptedText;
    }

    @Override
    public String decryptText(String encryptedText, String key) {
        String decryptedText = null;
        Key aesKey = new SecretKeySpec(key.getBytes(), "AES");
        try {
            Cipher cipher = Cipher.getInstance(ENCRYPTION_ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, aesKey);
            byte [] descryptedByte = cipher.doFinal(Base64.getDecoder().decode(encryptedText.getBytes()));
            decryptedText = new String(descryptedByte);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        }
        return decryptedText;
    }
}
