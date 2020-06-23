package com.investigation.develop.circle.service.emailsender;

import com.investigation.develop.circle.config.JWTUtils;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.text.SimpleDateFormat;
import java.util.Map;

@Service
public class WriteProposalEmailLetter extends AbstractEmailSender implements EMailSender {

    private Logger logger = LogManager.getLogger(WriteProposalEmailLetter.class);

    public static final int TOKEN_AVAILIABILITY_TIME = 24 * 10;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public void sendEmailLetter(Person person, Map<String, Object> data) {
        String emailTo = person.getEmail();
        logger.info("Sending Proposal an E-mail to: " + person);

        try {
            sendEmail(emailTo, "Investigation portal need you an idea!", getBodyText(person));
        } catch (MessagingException e) {
            logger.info("Error when sending Proposal Email to: " + emailTo);
        }
    }

    private String getBodyText(Person person) {
        String addProposalLink = context.getHost() + "applications?externalLoginToken=" + jwtUtils.generateJwtToken(person, TOKEN_AVAILIABILITY_TIME);
        return String.format(PROPOSAL_ADVICE_QUESTION_CONTENT, person.getFirstName(), addProposalLink, addProposalLink, getExpiredTime());
    }

    private String getExpiredTime() {
        String pattern = "dd MMM E HH:mm:ss";
        SimpleDateFormat format = new SimpleDateFormat(pattern);
        return format.format(DateUtil.currentDateWithIncreasedHours(TOKEN_AVAILIABILITY_TIME));
    }


}
