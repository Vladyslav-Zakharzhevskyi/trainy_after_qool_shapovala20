package com.example.demo.service;

import com.example.demo.entity.EmailConfirmationToken;
import com.example.demo.entity.Person;
import com.example.demo.exception.BaseSystemException;
import com.example.demo.exception.CustomExceptionStatus;
import com.example.demo.repository.EmailTokenRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.emailsender.EMailSender;
import com.example.demo.system.SystemPropertyKeys;
import com.example.demo.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Autowired
    private SystemPropertyService propertyService;

    @Autowired
    private EMailSender eMailSender;

    @Override
    public Person doRegistration(Person person) {

        Person savedPerson = personRepository.save(person);

        if (propertyService.getSystemPropertyAsBoolean(SystemPropertyKeys.IS_EMAILS_CONFIRMATION_ENABLED_KEY)) {
            EmailConfirmationToken token = new EmailConfirmationToken(UUID.randomUUID().toString(), savedPerson, DateUtil.currentDateWithIncreasedHours(2));
            token = emailTokenRepository.save(token);
            // Send confirmation email to User
            eMailSender.sendConfirmEmailLetter(person, token);
            // Update person
            savedPerson = personRepository.save(person);
        }


        return savedPerson;
    }

    @Override
    public Map<String, String> checkUsernameIsPresent(Map<String, String> state, String username) {

        Optional<Person> personByUserName = personRepository.findPersonByUserName(username);

        if (personByUserName.isPresent()) {
            state.put("Status", "Error");
            state.put("Text", "User name already in use");
            state.put("Value", username);
        } else {
            state.put("Status", "Available");
        }

        return state;
    }

    @Override
    public Person doEmailConfirmationWithToken(String token) throws BaseSystemException {
        EmailConfirmationToken correctToken = this.emailTokenRepository.findByTokenAndDateExpiredAfter(token, Date.from(Instant.now()));
        if (correctToken == null) {
            processWithTokenConfirmationException(token);
        }

        Person person = correctToken.getPerson();
        person.setEmailConfirmed(true);
        personRepository.save(person);
        return person;
    }

    public void processWithTokenConfirmationException(String token) throws BaseSystemException {
        EmailConfirmationToken expiredToken = emailTokenRepository.findByToken(token);
        if (expiredToken != null) {
            throw new BaseSystemException("Email token expired at: " + expiredToken.getDateExpired(), CustomExceptionStatus.EMAIL_TOKEN_HAS_EXPIRED);
        } else {
            throw new BaseSystemException("Email token doesn't exist", CustomExceptionStatus.EMAIL_TOKEN_NOT_FOUND);
        }
    }
}
