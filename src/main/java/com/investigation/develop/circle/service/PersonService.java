package com.investigation.develop.circle.service;

import com.investigation.develop.circle.dto.NewPersonDto;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.exception.BaseSystemException;

import java.util.Map;

public interface PersonService {

    Person createPersonWithRegistration(Person person);

    Person updatePerson(NewPersonDto person) throws BaseSystemException;

    Map<String, String> checkUsernameIsPresent(Map<String, String> state, String username);

    Person doEmailConfirmationWithToken(String token) throws BaseSystemException;
}
