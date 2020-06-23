package com.investigation.develop.circle.service.emailsender;

import com.google.common.base.Preconditions;
import com.investigation.develop.circle.entity.EmailConfirmationToken;
import com.investigation.develop.circle.entity.Person;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class WelcomeWithEmailConfirmationEmailLetter extends AbstractEmailSender implements EMailSender {

    private Logger logger = LogManager.getLogger(WelcomeWithEmailConfirmationEmailLetter.class);


    @Override
    public void sendEmailLetter(Person person, Map<String, Object> data) {
        String emailTo = person.getEmail();
        logger.info("Sending Welcome E-mail to: " + emailTo);

        EmailConfirmationToken token = (EmailConfirmationToken) data.get("token");
        Preconditions.checkNotNull(token);

        try {
            sendEmail(emailTo,
                    String.format(EMailSender.WELCOME_LETTER_SUBJECT, person.getFirstName()),
                    getEmailBody(person.getFirstName(), token));
        } catch (MessagingException e) {
            logger.info("Error when sending Welcome Email to:" + emailTo);
        }
    }

    private String getEmailBody(String firstName, EmailConfirmationToken confirmationToken) {
        String pattern = "E HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        String availableTillDate = format.format(confirmationToken.getDateExpired());

        return String.format(WELCOME_LETTER_CONTENT, firstName, availableTillDate, getConfirmEmailUrl(confirmationToken));
    }

    private String getConfirmEmailUrl(EmailConfirmationToken confirmationToken) {
        return context.getHost() + "login?token=" + confirmationToken.getToken();
    }



}
