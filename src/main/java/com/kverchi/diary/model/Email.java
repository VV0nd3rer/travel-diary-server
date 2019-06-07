package com.kverchi.diary.model;

import com.kverchi.diary.service.email.impl.EmailTemplate;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Liudmyla Melnychuk on 30.1.2019.
 */
public class Email implements Serializable {
    private EmailTemplate emailTemplate;
    private List<String> recipientsAddress;
    private Map<String, Object> textVariables;


    public Email() {

    }

    public Email(EmailTemplate emailTemplate, List<String> recipientsAddress, Map<String, Object> textVariables) {
        this.emailTemplate = emailTemplate;
        this.recipientsAddress = recipientsAddress;
        this.textVariables = textVariables;
    }

    public EmailTemplate getEmailTemplate() {
        return emailTemplate;
    }

    public void setEmailTemplate(EmailTemplate emailTemplate) {
        this.emailTemplate = emailTemplate;
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
