package com.kverchi.diary.repository.oauthclientcredentials;

import com.kverchi.diary.model.entity.OauthClientCredentials;
import com.kverchi.diary.repository.OauthClientCredentialsRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 14.5.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class OauthClientCredentialsRepositoryTest {
    @Value( "${credentials.email}" )
    private String credentialsEmail;

    @Autowired
    OauthClientCredentialsRepository oauthClientCredentialsRepository;

    @Ignore
    @Test
    public void testUpdateCredentials() throws Exception {
        OauthClientCredentials credentials =
                oauthClientCredentialsRepository.findById(1).get();
        if(credentials != null) {
            credentials.setCredentialsEmail(credentialsEmail);
            oauthClientCredentialsRepository.save(credentials);
        }
        assertNotNull(credentials);
    }
    @Ignore
    @Test
    public void testFindByCredentialsEmail() throws Exception {
        OauthClientCredentials credentials =
                oauthClientCredentialsRepository.findById(1).get();
        assertEquals(credentials.getCredentialsId(), 1);
    }
}