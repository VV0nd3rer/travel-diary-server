package com.kverchi.diary.service;

import com.kverchi.diary.model.Email;

import javax.mail.internet.MimeMessage;
import java.util.List;

/**
 * Created by Liudmyla Melnychuk on 5.2.2019.
 */
public interface EmailService {
    public void sendEmail(Email email);
}
