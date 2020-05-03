package com.example.demo.converter;

import com.example.demo.dto.NewPersonDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDtoConverterImpl implements PersonDtoConverter {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private AddressDtoConverter addressDtoConverter;

    @Autowired
    private JobDtoConverter jobDtoConverter;

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
                addressDtoConverter.convert(person.getAddresses(), false),
                jobDtoConverter.convert(person.getJobs(), false),
                person.getSalary(),
                person.getCurrency() != null ? person.getCurrency().name() : ""
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
