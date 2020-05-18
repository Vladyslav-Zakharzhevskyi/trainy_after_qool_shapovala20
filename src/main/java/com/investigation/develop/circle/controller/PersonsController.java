package com.investigation.develop.circle.controller;

import com.investigation.develop.circle.entity.HomeBusinessUser;
import com.investigation.develop.circle.exception.BaseSystemException;
import com.investigation.develop.circle.service.PersonService;
import com.investigation.develop.circle.converter.PersonDtoConverter;
import com.investigation.develop.circle.dto.NewPersonDto;
import com.investigation.develop.circle.dto.PersonDto;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.repository.PersonRepository;
import com.investigation.develop.circle.response.BaseResponseEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/person/")
public class PersonsController {

    private Logger logger = LogManager.getLogger(PersonsController.class);

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public ResponseEntity<PersonDto> registerPerson(@RequestBody NewPersonDto newPersonDto) {
        Person person = personDtoConverter.convert(newPersonDto);

        Person savedPerson = personService.createPersonWithRegistration(person);

        return ResponseEntity.ok(personDtoConverter.convert(savedPerson));
    }

    @RequestMapping(method = RequestMethod.GET)
    public PersonDto getCurrentLoggedInUser(@AuthenticationPrincipal HomeBusinessUser user) {
        Optional<Person> personByUserName = personRepository.findPersonByUserName(user.getUsername());
        Person person = personByUserName.orElseThrow(() -> new UsernameNotFoundException("User with username has not found"));
        return personDtoConverter.convert(person);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public PersonDto updatePerson(@AuthenticationPrincipal HomeBusinessUser user, @RequestBody NewPersonDto personDto) throws BaseSystemException {
        Person updatedPerson  = personService.updatePerson(personDto);
        return personDtoConverter.convert(updatedPerson);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @Transactional
    public List<PersonDto> getPersons() {
        List<Person> persons = personRepository.findAll();
        for (Person person : persons) {
            if (persons.size() > 1) {
                persons.remove(person);
            }
        }
        return personDtoConverter.convert(persons);
    }

    @RequestMapping(value = "/checkUserNameAvailability/{username}", method = RequestMethod.POST)
    public ResponseEntity<Map<String, String>> checkUserNameAvailability(@PathVariable(value = "username") String userName) {
        Map<String, String> result = new HashMap<>();

        personService.checkUsernameIsPresent(result, userName.trim());

        return ResponseEntity.ok(result);
    }

    @RequestMapping(value = "/confirmEmail", method = RequestMethod.GET)
    public ResponseEntity<BaseResponseEntity> confirmEmail(@RequestParam(name = "token") String token) throws BaseSystemException {

        Person person = personService.doEmailConfirmationWithToken(token);

        Map<String, String> response = new HashMap<>();
        response.put("username", person.getUserName());

        BaseResponseEntity successfulConfirmation = new BaseResponseEntity("Email has been confirmed!", 200, response);
        return new ResponseEntity<BaseResponseEntity>(successfulConfirmation, HttpStatus.OK);
    }

}
