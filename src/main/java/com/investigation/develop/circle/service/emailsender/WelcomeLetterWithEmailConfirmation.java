package com.investigation.develop.circle.service.emailsender;

import com.investigation.develop.circle.entity.EmailConfirmationToken;
import com.investigation.develop.circle.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.text.SimpleDateFormat;

@Service
public class WelcomeLetterWithEmailConfirmation implements EMailSender {

    private Logger logger = LogManager.getLogger(WelcomeLetterWithEmailConfirmation.class);

    @Value("${host}")
    private String host;

    @Value("classpath:public/logo.png")
    private Resource mainLogoResource;

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendEmailLetter(Person person, EmailConfirmationToken confirmationToken) {
        String emailTo = person.getEmail();
        logger.info("Sending E-mail to: " + emailTo);

        try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailTo);
            helper.setSubject(String.format(EMailSender.WELCOME_LETTER_SUBJECT, person.getFirstName()));
            helper.setText(getEmailBody(person.getFirstName(), confirmationToken),true);
            addResources(helper);

            emailSender.send(message);
        } catch (MessagingException e) {
            logger.info("Error when sending mail to:" + emailTo);
        }
    }

    private String getEmailBody(String firstName, EmailConfirmationToken confirmationToken) {
        String pattern = "E HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String availableTillDate = format.format(confirmationToken.getDateExpired());

        return String.format(EMailSender.WELCOME_LETTER_CONTENT, firstName, availableTillDate, getConfirmEmailUrl(confirmationToken));
    }

    private String getConfirmEmailUrl(EmailConfirmationToken confirmationToken) {
        return this.host + "login?token=" + confirmationToken.getToken();
    }

    private void addResources(MimeMessageHelper helper) throws MessagingException {
        helper.addInline("main_logo", mainLogoResource);
    }

}
