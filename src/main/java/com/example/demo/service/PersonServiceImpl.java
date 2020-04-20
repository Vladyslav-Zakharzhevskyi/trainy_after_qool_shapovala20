package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public Person save(Person person) {
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
}
