package com.kverchi.diary.model.enums;

/**
 * Created by Liudmyla Melnychuk on 21.3.2019.
 */
public enum EmailType {
    REGISTRATION_EMAIL("registrationMailTemplate.html"),
    RESET_PASSWORD_EMAIL("resetPasswordMailTemplate.html");

    private final String template;

    EmailType(final String template) {
        this.template = template;
    }

    @Override
    public String toString() {
        return template;
    }
}
