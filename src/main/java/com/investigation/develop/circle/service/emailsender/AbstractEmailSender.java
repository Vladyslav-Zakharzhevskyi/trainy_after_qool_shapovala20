package com.investigation.develop.circle.service.emailsender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public abstract class AbstractEmailSender implements EMailSender {

    @Value("${host}")
    protected String host;

    @Value("classpath:public/logo.png")
    protected Resource mainLogoResource;

    @Autowired
    protected JavaMailSender emailSender;

    protected void sendEmail(String emailTo, String subject, String body) throws MessagingException {

        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(emailTo);
        helper.setSubject(subject);
        helper.setText(body,true);
        addResources(helper);

        emailSender.send(message);
    }

    private void addResources(MimeMessageHelper helper) throws MessagingException {
        helper.addInline("main_logo", mainLogoResource);

    }
}
