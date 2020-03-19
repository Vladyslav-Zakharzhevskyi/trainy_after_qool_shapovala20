package com.example.demo.controller;

import com.example.demo.converter.PersonDtoConverter;
import com.example.demo.dto.NewPersonDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.example.demo.service.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class PersonsController {

    private Logger logger = LogManager.getLogger(PersonsController.class);

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/person", method = RequestMethod.POST)
    public ResponseEntity<PersonDto> registerPerson(@RequestBody NewPersonDto newPersonDto) {
        Person person = personDtoConverter.convert(newPersonDto);

        Person savedPerson = personService.save(person);

        return ResponseEntity.ok(personDtoConverter.convert(savedPerson));
    }

    @RequestMapping(value = "/person", method = RequestMethod.GET)
    @Transactional
    public List<PersonDto> getPersons(){

        // Retrieve persons to show only purpose
        List<Person> persons = personRepository.findAll();
        // Print to console
        logger.info(persons);

        return personDtoConverter.convert(persons);
    }

}
