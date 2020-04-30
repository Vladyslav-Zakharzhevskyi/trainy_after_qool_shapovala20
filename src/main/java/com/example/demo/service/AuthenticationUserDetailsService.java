package com.example.demo.service;

import com.example.demo.entity.HomeBusinessUser;
import com.example.demo.entity.Person;
import com.example.demo.exception.CustomExceptionStatus;
import com.example.demo.exception.UserEmailNotConfirmedException;
import com.example.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;
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

        if (!person.getEmailConfirmed()) {
            throw new UserEmailNotConfirmedException("User's Email hasn't confirmed", CustomExceptionStatus.EMAIL_TOKEN_NOT_FOUND);
        }


        return new HomeBusinessUser(person.getId(), username, person.getPassword(),
                                    Collections.emptyList(), person.getFirstName(), person.getLastName());
    }

}
