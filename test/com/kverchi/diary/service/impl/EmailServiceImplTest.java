package com.kverchi.diary.service.impl;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMail;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetup;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.kverchi.diary.model.Email;
import com.kverchi.diary.model.enums.EmailType;
import com.kverchi.diary.service.EmailService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by Liudmyla Melnychuk on 21.3.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailServiceImplTest {

    @Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);

    @Autowired
    EmailService emailService;


    @Test
    public void sentEmailTest() throws Exception {
        Map<String, Object> textVariables = new HashMap<>();
        textVariables.put("confirmEmailLink", "some-nice-link");
        Email registrationEmail =
                new Email(EmailType.REGISTRATION_EMAIL,
                        Arrays.asList("someone@gmail.com"),
                        textVariables);

        emailService.sendEmail(registrationEmail);
    }

    @Ignore
    @Test
    public void testSendEmailGreenMail() throws MessagingException {
        GreenMailUtil.sendTextEmailTest("to@localhost.com", "from@localhost.com",
                "some subject", "some body"); // --- Place your sending code here instead
        assertEquals("some body", GreenMailUtil.getBody(greenMail.getReceivedMessages()[0]));
    }

}