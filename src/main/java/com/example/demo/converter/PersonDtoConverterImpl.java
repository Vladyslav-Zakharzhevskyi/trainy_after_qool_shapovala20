package com.example.demo.converter;

import com.example.demo.dto.PersonDto;
import com.example.demo.entities.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonDtoConverterImpl implements PersonDtoConverter {

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
                person.getFirstName(),
                person.getLastName(),
                person.getAge(),
                addressDtoConverter.convert(person.getAddresses(), false),
                jobDtoConverter.convert(person.getJobs(), false),
                person.getSalary(),
                person.getCurrency().name()
        );
    }
}
