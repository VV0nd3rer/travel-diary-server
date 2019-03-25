package com.kverchi.diary.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kverchi.diary.model.Email;
import com.kverchi.diary.model.entity.OauthClientCredentials;
import com.kverchi.diary.repository.OauthClientCredentialsRepository;
import com.kverchi.diary.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

/**
 * Created by Liudmyla Melnychuk on 5.2.2019.
 */
@Service
public class EmailServiceImpl implements EmailService {
    private static final Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);

    @Value( "${credentials.email}" )
    private String credentialsEmail;

    @Autowired
    public OauthClientCredentialsRepository oauthClientCredentialsRepository;

    @Autowired
    public JavaMailSender emailSender;

    @Autowired
    private TemplateEngine templateEngine;


    private OauthClientCredentials getCredentials() {
        OauthClientCredentials oauthClientCredentials =
                oauthClientCredentialsRepository.findByCredentialsEmail(credentialsEmail).get(0);
        if(System.currentTimeMillis() > oauthClientCredentials.getTokenExpires()) {
            oauthClientCredentials = updateAccessToken(oauthClientCredentials);
        }
        return oauthClientCredentials;
    }
    private OauthClientCredentials updateAccessToken(OauthClientCredentials oauthClientCredentials) {
        try {
            String request = "client_id="+ URLEncoder.encode(oauthClientCredentials.getOauthClientId(), "UTF-8")
                    +"&client_secret="+URLEncoder.encode(oauthClientCredentials.getOauthSecret(), "UTF-8")
                    +"&refresh_token="+URLEncoder.encode(oauthClientCredentials.getRefreshToken(), "UTF-8")
                    +"&grant_type=refresh_token";
            HttpURLConnection conn = (HttpURLConnection) new URL(oauthClientCredentials.getTokenUrl()).openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            PrintWriter out = new PrintWriter(conn.getOutputStream());
            out.print(request);
            out.flush();
            out.close();
            conn.connect();
            try {
                HashMap<String,Object> result;
                result = new ObjectMapper().readValue(conn.getInputStream(), new TypeReference<HashMap<String,Object>>() {});
                oauthClientCredentials.setAccessToken((String) result.get("access_token"));
                oauthClientCredentials.setTokenExpires(
                        System.currentTimeMillis()+(((Number)result.get("expires_in")).intValue()*1000));
            } catch (IOException e) {
                String line;
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
                while((line = in.readLine()) != null) {
                    System.out.println(line);
                }
                System.out.flush();
                in.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        oauthClientCredentialsRepository.save(oauthClientCredentials);
        return oauthClientCredentials;
    }
    private MimeMessagePreparator prepeareEmail(Email email) {
        // Usual inline interface implementation
        /*MimeMessagePreparator messagePreparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                //...
            }
        }*/
        // Implementing an interface by a lambda expression
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("diaryofkverchi@gmail.com");
            messageHelper.setTo(email.getRecipientAddress());
            messageHelper.setSubject(email.getSubject());

            Context context = new Context();
            context.setVariable("message", email.getText());
            String emailMessage = templateEngine.process("registrationMailTemplate", context);

            messageHelper.setText(emailMessage, true);
        };
        return messagePreparator;
    }
    @Override
    public void sendEmail(Email email) {
        OauthClientCredentials oauthClientCredentials = getCredentials();
        ((JavaMailSenderImpl)emailSender).setPassword(oauthClientCredentials.getAccessToken());
        MimeMessagePreparator messagePreparator = prepeareEmail(email);
        try {
            emailSender.send(messagePreparator);
        } catch (MailException e) {
            // TODO log exception and/or repeat sending email
            logger.error("Error during sending email");
            e.printStackTrace();
        }


    }


}
