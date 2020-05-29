package com.investigation.develop.circle.service;

import com.investigation.develop.circle.dto.NewPersonDto;
import com.investigation.develop.circle.entity.EmailConfirmationToken;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.exception.ApplicationException;
import com.investigation.develop.circle.exception.Code;
import com.investigation.develop.circle.repository.EmailTokenRepository;
import com.investigation.develop.circle.repository.PersonRepository;
import com.investigation.develop.circle.repository.PositionRepository;
import com.investigation.develop.circle.service.emailsender.EMailSender;
import com.investigation.develop.circle.system.SystemPropertyKeys;
import com.investigation.develop.circle.util.DateUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import static com.google.common.collect.Maps.newHashMap;

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
    @Qualifier("welcomeWithEmailConfirmationEmailLetter")
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
            Map<String, Object> data = newHashMap();
            data.put("token", token);
            eMailSender.sendEmailLetter(person, data);
            // Update person
            savedPerson = personRepository.save(person);
        }

        return savedPerson;
    }

    @Override
    public Person updatePerson(NewPersonDto personDto) throws ApplicationException {
        UUID personId = personDto.getId();
        if (personId == null) {
            throw new ApplicationException("There is request to update person without id", Code.GENERAL_EXCEPTION);
        }
        Person person = personRepository.findById(personId).map(originalPerson -> {
            originalPerson.setFirstName(personDto.getFirstName());
            originalPerson.setLastName(personDto.getLastName());
            originalPerson.setEmail(personDto.getEmail());
            originalPerson.setAge(personDto.getAge());
            originalPerson.setSalary(personDto.getSalary());
            originalPerson.setCurrency(personDto.getCurrency());
            originalPerson.setPosition(positionRepository.getOne(personDto.getPositionId()));
            originalPerson.setAddress(personDto.getAddress());
            return originalPerson;
        }).orElseThrow(() -> new ApplicationException(String.format("Person with id %s not found", personDto.getId()), Code.GENERAL_EXCEPTION));

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
    public Person doEmailConfirmationWithToken(String token) throws ApplicationException {
        EmailConfirmationToken correctToken = this.emailTokenRepository.findByTokenAndDateExpiredAfter(token, Date.from(Instant.now()));
        if (correctToken == null) {
            processWithTokenConfirmationException(token);
        }

        Person person = correctToken.getPerson();
        person.setEmailConfirmed(true);
        personRepository.save(person);
        return person;
    }

    public void processWithTokenConfirmationException(String token) throws ApplicationException {
        Map<String, Object> responseData = new HashMap<>();
        responseData.put("token", token);


        EmailConfirmationToken expiredToken = emailTokenRepository.findByToken(token);
        if (expiredToken != null) {
            responseData.put("expiredDate", expiredToken.getDateExpired());
            throw new ApplicationException("Email token expired at: " + expiredToken.getDateExpired(), Code.EMAIL_TOKEN_HAS_EXPIRED, responseData);
        } else {
            throw new ApplicationException("Email token doesn't exist", Code.EMAIL_TOKEN_NOT_FOUND, responseData);
        }
    }
}
