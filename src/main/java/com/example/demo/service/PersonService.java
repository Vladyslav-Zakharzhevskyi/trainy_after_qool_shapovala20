package com.example.demo.service;

import com.example.demo.entity.Person;

import java.util.Map;

public interface PersonService {

    Person save(Person person);

    Map<String, String> checkUsernameIsPresent(Map<String, String> state, String username);

}
