package com.example.demo.service.emailsender;

import com.example.demo.entity.EmailConfirmationToken;
import com.example.demo.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SpringEMailSenderService implements EMailSender {

    private Logger logger = LogManager.getLogger(SpringEMailSenderService.class);

    @Value("${host}")
    private String host;

    @Autowired
    public JavaMailSender emailSender;

    @Override
    public void sendConfirmEmailLetter(Person person, EmailConfirmationToken confirmationToken) {
        // Set confirmation flag to false
        person.setEmailConfirmed(Boolean.FALSE);

        String emailTo = person.getEmail();
        logger.info("Sending E-mail to: " + emailTo);

        try {
            MimeMessage message = emailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setTo(emailTo);
            helper.setSubject(String.format(EMailSender.WELCOME_SUBJECT, person.getFirstName()));
            helper.setText(getEmailBody(person.getFirstName(), confirmationToken),true);

            emailSender.send(message);
        } catch (MessagingException e) {
            logger.info("Error when sending mail to:" + emailTo);
        }
    }

    private String getEmailBody(String firstName, EmailConfirmationToken confirmationToken) {
        return "<html>"
                + "<body>"
                +   "<div><strong>Hi " + firstName +"!"  + "</strong></div>"
                +   "<div>Welcome to investigation portal.</div>"
                +   "<div>Please confirm your Email by the link below.</div>"
                +   "<div>This link is available to use till:" + confirmationToken.getDateExpired() + "</div>"
                +   "<div>Link: <a href=" + getConfirmEmailUrl(confirmationToken) + ">Confirm your E-Mail</a></div>"
                + "</body>"
                + "</html>";
    }

    private String getConfirmEmailUrl(EmailConfirmationToken confirmationToken) {
        return this.host + "login?token=" + confirmationToken.getToken();
    }

}
