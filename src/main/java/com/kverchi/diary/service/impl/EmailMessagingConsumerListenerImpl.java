package com.kverchi.diary.service.impl;

import com.kverchi.diary.model.Email;
import com.kverchi.diary.service.EmailMessagingConsumerListener;
import com.kverchi.diary.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Liudmyla Melnychuk on 30.1.2019.
 */
@Component
public class EmailMessagingConsumerListenerImpl implements EmailMessagingConsumerListener {

    private static final Logger logger = LoggerFactory.getLogger(EmailMessagingConsumerListenerImpl.class);

    @Autowired
    EmailService emailService;

    @JmsListener(destination = "diary.email.queue")
    public void receiveEmail(Email email) {
        logger.info("Email was received. " + email.getText());
        emailService.sendEmail(email);
    }
}
