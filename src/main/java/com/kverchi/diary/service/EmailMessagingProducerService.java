package com.kverchi.diary.service;

import com.kverchi.diary.model.Email;

/**
 * Created by Liudmyla Melnychuk on 30.1.2019.
 */
public interface EmailMessagingProducerService {
    public void sendEmail(Email email);

}
