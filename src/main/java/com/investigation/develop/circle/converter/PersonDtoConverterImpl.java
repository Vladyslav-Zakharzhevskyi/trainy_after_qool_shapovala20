package com.investigation.develop.circle.converter;

import com.investigation.develop.circle.dto.NewPersonDto;
import com.investigation.develop.circle.dto.PersonDto;
import com.investigation.develop.circle.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

@Component
public class PersonDtoConverterImpl implements PersonDtoConverter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public List<PersonDto> convert(List<Person> persons) {
        return persons.stream().map(this::convert).collect(Collectors.toList());
    }

    @Override
    public PersonDto convert(Person person) {
        return new PersonDto(
                person.getId(),
                person.getUserName(),
                person.getFirstName(),
                person.getLastName(),
                person.getEmail(),
                person.getAge(),
                person.getPosition() != null ? person.getPosition().getId() : -1,
                person.getSalary(),
                person.getCurrency() != null ? person.getCurrency().name() : "",
                person.getAddress()
        );
    }

    @Override
    public Person convert(NewPersonDto newPersonDto) {
        Person person = new Person();
        person.setUserName(newPersonDto.getUserName());
        person.setFirstName(newPersonDto.getFirstName());
        person.setLastName(newPersonDto.getLastName());
        person.setEmail(newPersonDto.getEmail());
        person.setPassword(passwordEncoder.encode(newPersonDto.getPassword()));

        return person;
    }

}
