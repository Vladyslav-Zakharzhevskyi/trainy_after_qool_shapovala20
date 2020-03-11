package com.example.demo.controllers;

import com.example.demo.converter.PersonDtoConverter;
import com.example.demo.dto.PersonDto;
import com.example.demo.entities.Person;
import com.example.demo.repository.PersonRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api")
public class PersonsController {

    private Logger logger = LogManager.getLogger(PersonsController.class);

    @Autowired
    private PersonDtoConverter personDtoConverter;
    @Autowired
    private PersonRepository personRepository;


    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    @Transactional
    public List<PersonDto> getPersons(){

        // Retrieve persons to show only purpose
        List<Person> persons = personRepository.findAll();
        // Print to console
        logger.info(persons);

        return personDtoConverter.convert(persons);

    }

}
