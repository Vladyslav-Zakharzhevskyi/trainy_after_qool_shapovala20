package com.example.demo.service;

import com.example.demo.dto.NewPersonDto;
import com.example.demo.entity.Person;
import com.example.demo.exception.BaseSystemException;

import java.util.Map;

public interface PersonService {

    Person createPersonWithRegistration(Person person);

    Person updatePerson(NewPersonDto person) throws BaseSystemException;

    Map<String, String> checkUsernameIsPresent(Map<String, String> state, String username);

    Person doEmailConfirmationWithToken(String token) throws BaseSystemException;
}
