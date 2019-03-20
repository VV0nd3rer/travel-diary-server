package com.kverchi.diary.service;

import com.kverchi.diary.model.Email;

/**
 * Created by Liudmyla Melnychuk on 4.2.2019.
 */
public interface EmailMessagingConsumerListener {
    public void receiveEmail(Email email);
}
