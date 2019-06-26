package com.kverchi.diary.converter;

import com.kverchi.diary.service.security.EncryptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Created by Liudmyla Melnychuk on 26.6.2019.
 */
@Converter
public class CryptoConverter implements AttributeConverter<String, String> {

    @Value( "${aes.encryption.key}" )
    private String encryptionKey;

    @Autowired
    EncryptionService encryptionService;

    @Override
    public String convertToDatabaseColumn(String attribute) {
        return encryptionService.encryptText(attribute, encryptionKey);
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return encryptionService.decryptText(dbData, encryptionKey);
    }
}
