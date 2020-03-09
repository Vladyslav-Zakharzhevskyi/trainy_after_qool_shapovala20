package com.example.demo.converter;

import com.example.demo.dto.AddressDto;
import com.example.demo.entities.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AddressDtoConverterImpl implements AddressDtoConverter {

    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Override
    public List<AddressDto> convert(List<Address> addresses, boolean includePerson) {
        return addresses.stream().map(address -> convert(address, includePerson)).collect(Collectors.toList());
    }

    @Override
    public AddressDto convert(Address address, boolean includePerson) {
        return new AddressDto(address.getId(),
                address.getCity(),
                address.getStreet(),
                address.getBuildingNum(),
                address.getAddressType().name(),
                includePerson ? personDtoConverter.convert(address.getPerson()): null);
    }
}
