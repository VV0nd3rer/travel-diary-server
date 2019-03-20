package com.kverchi.diary.model;

import java.io.Serializable;

/**
 * Created by Liudmyla Melnychuk on 30.1.2019.
 */
public class Email implements Serializable {
    private String recipientAddress;
    private String subject;
    private String text;

    public Email() {
    }

    public Email(String recipientAddress, String subject, String text) {

        this.recipientAddress = recipientAddress;
        this.subject = subject;
        this.text = text;
    }

    public String getRecipientAddress() {
        return recipientAddress;
    }

    public void setRecipientAddress(String recipientAddress) {
        this.recipientAddress = recipientAddress;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
