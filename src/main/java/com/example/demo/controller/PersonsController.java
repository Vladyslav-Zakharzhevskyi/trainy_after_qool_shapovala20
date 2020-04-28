package com.example.demo.controller;

import com.example.demo.converter.PersonDtoConverter;
import com.example.demo.dto.NewPersonDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.HomeBusinessUser;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

        Person savedPerson = personService.save(person);

        return ResponseEntity.ok(personDtoConverter.convert(savedPerson));
    }

    @RequestMapping(value = "current", method = RequestMethod.GET)
    public PersonDto getCurrentLoggedInUser(@AuthenticationPrincipal(errorOnInvalidType = true) HomeBusinessUser user) {
        Optional<Person> personByUserName = personRepository.findPersonByUserName(user.getUsername());
        Person person = personByUserName.orElseThrow(() -> new UsernameNotFoundException("User with username has not found"));
        return personDtoConverter.convert(person);
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    @Transactional
    public List<PersonDto> getPersons(){
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


}
