package com.example.demo.service;

import com.example.demo.dto.NewPersonDto;
import com.example.demo.entity.EmailConfirmationToken;
import com.example.demo.entity.Person;
import com.example.demo.exception.BaseSystemException;
import com.example.demo.exception.CustomExceptionStatus;
import com.example.demo.repository.EmailTokenRepository;
import com.example.demo.repository.PersonRepository;
import com.example.demo.repository.PositionRepository;
import com.example.demo.service.emailsender.EMailSender;
import com.example.demo.system.SystemPropertyKeys;
import com.example.demo.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private Logger logger = LogManager.getLogger(PersonServiceImpl.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private EmailTokenRepository emailTokenRepository;

    @Autowired
    private SystemPropertyService propertyService;

    @Autowired
    private EMailSender eMailSender;

    @Override
    @Transactional
    public Person createPersonWithRegistration(Person person) {

        Person savedPerson = personRepository.save(person);

        if (propertyService.getSystemPropertyAsBoolean(SystemPropertyKeys.IS_EMAILS_CONFIRMATION_ENABLED_KEY)) {
            EmailConfirmationToken token = new EmailConfirmationToken(UUID.randomUUID().toString(), savedPerson, DateUtil.currentDateWithIncreasedHours(2));
            token = emailTokenRepository.save(token);
            // Set confirmation flag to false
            person.setEmailConfirmed(Boolean.FALSE);
            // Send confirmation email to User
            eMailSender.sendEmailLetter(person, token);
            // Update person
            savedPerson = personRepository.save(person);
        }

        return savedPerson;
    }

    @Override
    public Person updatePerson(NewPersonDto personDto) throws BaseSystemException {
        UUID personId = personDto.getId();
        if (personId == null) {
            throw new BaseSystemException("There is request to update person without id", CustomExceptionStatus.GENERAL_EXCEPTION);
        }
        Person person = personRepository.findById(personId).map(originalPerson -> {
            originalPerson.setFirstName(personDto.getFirstName());
            originalPerson.setLastName(personDto.getLastName());
            originalPerson.setEmail(personDto.getEmail());
            originalPerson.setAge(personDto.getAge());
            originalPerson.setSalary(personDto.getSalary());
            originalPerson.setCurrency(personDto.getCurrency());
            originalPerson.setPosition(positionRepository.getOne(personDto.getPositionId()));
            return originalPerson;
        }).orElseThrow(() -> new BaseSystemException(String.format("Person with id %s not found", personDto.getId()), CustomExceptionStatus.GENERAL_EXCEPTION));

        return personRepository.save(person);
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
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("token", token);


        EmailConfirmationToken expiredToken = emailTokenRepository.findByToken(token);
        if (expiredToken != null) {
            responseData.put("expiredDate", expiredToken.getDateExpired());
            throw new BaseSystemException("Email token expired at: " + expiredToken.getDateExpired(), CustomExceptionStatus.EMAIL_TOKEN_HAS_EXPIRED, responseData);
        } else {
            throw new BaseSystemException("Email token doesn't exist", CustomExceptionStatus.EMAIL_TOKEN_NOT_FOUND, responseData);
        }
    }
}
