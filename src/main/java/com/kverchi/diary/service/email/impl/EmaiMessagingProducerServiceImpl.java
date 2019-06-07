package com.kverchi.diary.service.email.impl;

import com.kverchi.diary.model.Email;
import com.kverchi.diary.service.email.EmailMessagingProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Liudmyla Melnychuk on 30.1.2019.
 */
@Service
public class EmaiMessagingProducerServiceImpl implements EmailMessagingProducerService {
    @Autowired
    private JmsTemplate jmsTemplate;

    @Override
    public void sendEmail(Email email) {
        jmsTemplate.convertAndSend("diary.email.queue", email);
    }

}
