package com.saulocn.microprofile;

public class Email {

    private String mail;

    public static Email of(String str) {
        Email email = new Email();
        email.mail = str;
        return email;
    }

    public String getMail() {
        return mail;
    }
}
