package com.wreel.retail.models;

import javax.validation.constraints.NotNull;

public class ConfirmationEmail {

    @NotNull
    private final String toName;

    @NotNull
    private final String toEmail;

    @NotNull
    private final String fromName;

    @NotNull
    private final String fromEmail;

    @NotNull
    private final String emailSubject;

    @NotNull
    private final String message;

    public final String getToName() {
        return toName;
    }

    public ConfirmationEmail(String toName, String toEmail, String fromName,
                             String fromEmail, String emailSubject, String message) {
        this.toName = toName;
        this.toEmail = toEmail;
        this.fromName = fromName;
        this.fromEmail = fromEmail;
        this.emailSubject = emailSubject;
        this.message = message;
    }

    public String getToEmail() {
        return toEmail;
    }

    public String getFromName() {
        return fromName;
    }

    public String getFromEmail() {
        return fromEmail;
    }

    public String getEmailSubject() {
        return emailSubject;
    }

    public String getMessage() {
        return message;
    }
}
