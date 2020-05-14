package com.example.demo.config.successhandler;

import com.example.demo.converter.PersonDtoConverter;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.HomeBusinessUser;
import com.example.demo.entity.Person;
import com.example.demo.repository.PersonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class UserProvider {

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDtoConverter personDtoConverter;

    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {

        HomeBusinessUser principal = (HomeBusinessUser) authentication.getPrincipal();

        Optional<Person> personRepo = personRepository.findById(principal.getId());
        String user = personRepo
                .map(person -> personDtoConverter.convert(person))
                .map(this::prepareResponseBody)
                .orElse("");

        response.getWriter().write(user);

    }

    private String prepareResponseBody(PersonDto personDto) {
        String authenticatedUser = "";
        try {
            authenticatedUser = jacksonObjectMapper.writeValueAsString(personDto);
        } catch (JsonProcessingException ignored) {
        }
        return authenticatedUser;
    }
}
