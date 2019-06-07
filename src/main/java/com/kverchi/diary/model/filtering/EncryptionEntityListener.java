package com.kverchi.diary.model.filtering;

import com.kverchi.diary.model.entity.OauthClientCredentials;
import com.kverchi.diary.service.security.EncryptionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

/**
 * Created by Liudmyla Melnychuk on 14.5.2019.
 */
public class EncryptionEntityListener {
    private static final Logger logger = LoggerFactory.getLogger(EncryptionEntityListener.class);

    @Value( "${aes.encryption.key}" )
    private String encryptionKey;

    @Autowired
    EncryptionService encryptionService;

    @PrePersist
    @PreUpdate
    public void encryptEntity(Object entity) {
        if(entity instanceof OauthClientCredentials) {
            OauthClientCredentials credentials = (OauthClientCredentials)entity;

            credentials.setAccessToken(
                    encryptionService.encryptText(credentials.getAccessToken(), encryptionKey));
            credentials.setOauthClientId(
                    encryptionService.encryptText(credentials.getOauthClientId(), encryptionKey));
            credentials.setOauthSecret(
                    encryptionService.encryptText(credentials.getOauthSecret(), encryptionKey));
            credentials.setRefreshToken(
                    encryptionService.encryptText(credentials.getRefreshToken(), encryptionKey));
            credentials.setCredentialsEmail(
                    encryptionService.encryptText(credentials.getCredentialsEmail(), encryptionKey));
        }
    }

    @PostLoad
    public void decryptEntity(Object entity) {
        if(entity instanceof OauthClientCredentials) {
            OauthClientCredentials credentials = (OauthClientCredentials)entity;

            credentials.setAccessToken(
                    encryptionService.decryptText(credentials.getAccessToken(), encryptionKey));
            credentials.setOauthClientId(
                    encryptionService.decryptText(credentials.getOauthClientId(), encryptionKey));
            credentials.setOauthSecret(
                    encryptionService.decryptText(credentials.getOauthSecret(), encryptionKey));
            credentials.setRefreshToken(
                    encryptionService.decryptText(credentials.getRefreshToken(), encryptionKey));
            credentials.setCredentialsEmail(
                    encryptionService.decryptText(credentials.getCredentialsEmail(), encryptionKey));
        }
    }

}
