package com.example.demo.service;

import com.example.demo.entity.Person;
import com.example.demo.exception.BaseSystemException;

import java.util.Map;

public interface PersonService {

    Person doRegistration(Person person);

    Map<String, String> checkUsernameIsPresent(Map<String, String> state, String username);

    Person doEmailConfirmationWithToken(String token) throws BaseSystemException;
}
