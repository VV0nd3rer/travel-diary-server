package com.kverchi.diary.service.email.impl;

import com.icegreen.greenmail.junit.GreenMailRule;
import com.icegreen.greenmail.util.GreenMailUtil;
import com.icegreen.greenmail.util.ServerSetupTest;
import com.kverchi.diary.model.Email;
import com.kverchi.diary.service.email.impl.EmailTemplate;
import com.kverchi.diary.service.email.EmailService;
import com.kverchi.diary.service.security.SecurityService;
import com.kverchi.diary.service.user.UserService;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private HttpServletRequest httpServletRequest;

    @Rule
    public final GreenMailRule greenMail = new GreenMailRule(ServerSetupTest.SMTP);
    @Autowired
    SecurityService securityService;
    @Autowired
    EmailService emailService;

    //@Ignore
    @Test
    public void sentEmailTest() throws Exception {
        Map<String, Object> textVariables = new HashMap<>();

        String baseUrl = UserService.generateServerBaseUrl(httpServletRequest);
        String securityToken = securityService.generateSecurityToken();
        String confirmLink = baseUrl + "/user/confirm/" + securityToken;

        textVariables.put("confirmEmailLink", confirmLink);
        Email registrationEmail =
                new Email(EmailTemplate.REGISTRATION_EMAIL,
                        Arrays.asList("kverchi24@gmail.com"),
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