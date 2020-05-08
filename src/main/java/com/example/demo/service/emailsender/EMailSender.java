package com.example.demo.service.emailsender;

import com.example.demo.entity.EmailConfirmationToken;
import com.example.demo.entity.Person;


public interface EMailSender {

    String WELCOME_LETTER_SUBJECT = "Welcome to investigation portal, %s!";

    String WELCOME_LETTER_CONTENT = "<html>"
            + "<body>"
            + "<div id='content-wrapper' style='background-color: rgba(33,121,43,0.2); padding: 10px; line-height: 2.5; font-family: Arial, Helvetica, sans-serif;'>"
            +   "<div>"
            +       "<img style='height: 80px; float: right;' src='cid:main_logo'>"
            +   "</div>"
            +   "<div>Hi <strong>%s</strong>!, welcome to investigation portal.</div>"
            +   "<div>Please confirm your Email by the link below.</div>"
            +   "<div>This link is available to use until: %s </div>"
            +   "<div>Link: <a href='%s'>Confirm your E-Mail</a></div>"
            + "</div>"
            + "<div id='footer-wrapper' style='height: 33px; background-color: rgba(130, 131, 152, 0.99); display: flex;'>"
            +   "<span style='margin: auto'>Designed for testing purposes only</span>"
            + "</div>"
            + "</body>"
            + "</html>";


    void sendEmailLetter(Person person, EmailConfirmationToken confirmationToken);

}
