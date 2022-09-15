package com.mypenbox.mpb.registration.email;

public interface EmailSender {
    void send(String to, String email, String subject);
}
