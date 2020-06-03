package com.investigation.develop.circle.service.emailsender;

import com.investigation.develop.circle.entity.Person;

import java.util.Map;


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

    String PROPOSAL_ADVICE_QUESTION_CONTENT = "<html>"
            + "<body>"
            + "<div id='content-wrapper' style='background-color: rgba(33,121,43,0.2); padding: 10px; line-height: 2.5; font-family: Arial, Helvetica, sans-serif;'>"
            +   "<div>"
            +       "<img style='height: 80px; float: right;' src='cid:main_logo'>"
            +   "</div>"
            +   "<div>Hi <strong>%s</strong>! We want kindly ask you 1 question:</div>"
            +   "<div>We need your an advice what functionality you want to be added to our site.</div>"
            +   "<div><strong>Please go to this link and leave your an idea:</strong> <a href='%s' title='Send Proposal to review'>%s</a></div>"
            +   "<div>This link is available to use until: %s </div>"
            +   "<div><small>This message has been generated automatically and sends to you at 9AM. until you don't leave at least one proposal!</small></div>"
            + "</div>"
            + "</body>"
            + "</html>";

    void sendEmailLetter(Person person, Map<String, Object> data);

}
