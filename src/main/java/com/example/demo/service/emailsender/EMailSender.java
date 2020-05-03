package com.example.demo.service.emailsender;

import com.example.demo.entity.EmailConfirmationToken;
import com.example.demo.entity.Person;


public interface EMailSender {

    String WELCOME_SUBJECT = "Welcome to investigation portal, %s!";

    void sendConfirmEmailLetter(Person person, EmailConfirmationToken confirmationToken);

}
