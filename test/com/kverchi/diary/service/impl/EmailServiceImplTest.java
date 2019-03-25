package com.kverchi.diary.service.impl;

import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.ServerSetup;
import com.kverchi.diary.model.Email;
import com.kverchi.diary.service.EmailService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 21.3.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {
    private GreenMail smtpServer;

    @Autowired
    EmailService emailService;

    @Before
    public void setUp() throws Exception {
        smtpServer = new GreenMail(new ServerSetup(25, null, "smtp"));
        smtpServer.start();
    }

    @After
    public void tearDown() throws Exception {
        smtpServer.stop();
    }
    @Test
    public void sentEmailTest() throws Exception {
        Email email = new Email("kverchi24@gmail.com", "Demo", "This is a demo message.");
        emailService.sendEmail(email);
        //assertReceivedMessageContains(email.getText());
    }

    private void assertReceivedMessageContains(String expected) throws IOException, MessagingException {
        MimeMessage[] receivedMessages = smtpServer.getReceivedMessages();
        assertEquals(1, receivedMessages.length);
        String content = (String) receivedMessages[0].getContent();
        assertTrue(content.contains(expected));
    }

}