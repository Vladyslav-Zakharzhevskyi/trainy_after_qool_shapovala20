package com.investigation.develop.circle.converter;

import com.investigation.develop.circle.dto.NewPersonDto;
import com.investigation.develop.circle.dto.PersonDto;
import com.investigation.develop.circle.entity.Person;

import java.util.List;

public interface PersonDtoConverter {

    List<PersonDto> convert(List<Person> persons);

    PersonDto convert(Person person);

    Person convert(NewPersonDto newPersonDto);

}
