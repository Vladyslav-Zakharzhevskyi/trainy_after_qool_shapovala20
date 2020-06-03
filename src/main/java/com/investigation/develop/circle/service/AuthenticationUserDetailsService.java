package com.investigation.develop.circle.service;

import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.exception.Code;
import com.investigation.develop.circle.exception.UserEmailNotConfirmedException;
import com.investigation.develop.circle.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuthenticationUserDetailsService implements UserDetailsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Person> personByUserName = personRepository.findPersonByUserName(username);


        Person person = personByUserName.
                orElseThrow(() -> new UsernameNotFoundException("User with username has not found"));

        if (!person.isEmailConfirmed()) {
            throw new UserEmailNotConfirmedException("User's Email hasn't confirmed", Code.EMAIL_HAS_NOT_CONFIRMED);
        }

        return person;
    }

}
