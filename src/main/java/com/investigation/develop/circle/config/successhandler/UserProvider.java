package com.investigation.develop.circle.config.successhandler;

import com.investigation.develop.circle.entity.HomeBusinessUser;
import com.investigation.develop.circle.converter.PersonDtoConverter;
import com.investigation.develop.circle.dto.PersonDto;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.repository.PersonRepository;
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
