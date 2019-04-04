package com.kverchi.diary.model;

import com.kverchi.diary.model.enums.EmailType;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Liudmyla Melnychuk on 30.1.2019.
 */
public class Email implements Serializable {
    private String recipientAddress;
    private String subject;
    private String text;

    private EmailType emailType;
    private List<String> recipientsAddress;
    private Map<String, Object> textVariables;


    public Email() {
    }
    public Email(EmailType emailType, List<String> recipientsAddress, Map<String, Object> textVariables) {
        this.emailType = emailType;
        this.recipientsAddress = recipientsAddress;
        this.textVariables = textVariables;
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

    public EmailType getEmailType() {
        return emailType;
    }

    public void setEmailType(EmailType emailType) {
        this.emailType = emailType;
    }

    public List<String> getRecipientsAddress() {
        return recipientsAddress;
    }

    public void setRecipientsAddress(List<String> recipientsAddress) {
        this.recipientsAddress = recipientsAddress;
    }

    public Map<String, Object> getTextVariables() {
        return textVariables;
    }

    public void setTextVariables(Map<String, Object> textVariables) {
        this.textVariables = textVariables;
    }
}
