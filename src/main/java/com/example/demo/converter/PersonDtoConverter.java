package com.example.demo.converter;

import com.example.demo.dto.NewPersonDto;
import com.example.demo.dto.PersonDto;
import com.example.demo.entity.Person;

import java.util.List;

public interface PersonDtoConverter {

    List<PersonDto> convert(List<Person> persons);

    PersonDto convert(Person person);

    Person convert(NewPersonDto newPersonDto);

}
