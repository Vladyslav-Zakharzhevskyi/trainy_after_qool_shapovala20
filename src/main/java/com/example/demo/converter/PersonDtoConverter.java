package com.example.demo.converter;

import com.example.demo.dto.PersonDto;
import com.example.demo.entities.Person;

import java.util.List;

public interface PersonDtoConverter {

    List<PersonDto> convert(List<Person> persons);

    PersonDto convert(Person person);

}