package com.kverchi.diary.service.encryption.impl;

import com.kverchi.diary.service.security.EncryptionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 14.5.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EncryptionServiceImplTest {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionServiceImplTest.class);
    @Value( "${aes.encryption.key}" )
    private String encryptionKey;

    @Autowired
    EncryptionService encryptionService;

    //@Ignore
    @Test
    public void testEncryption() throws Exception {
        String clearText = "hello";
        String encryptedText = "2mP7opdct1ZM1ljmug4NcAHBaTi68Okh7Br/9lIgW3cWg9Azes8y8eE/0qSSmIo2"; //encryptionService.encryptText(clearText, encryptionKey);
        logger.info("Encrypted text: " + encryptedText);
        String decryptedText = encryptionService.decryptText(encryptedText, encryptionKey);
        logger.info("Decrypted text: " + decryptedText);
        assertEquals(clearText, decryptedText);
    }


}