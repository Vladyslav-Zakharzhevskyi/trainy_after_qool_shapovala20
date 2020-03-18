package com.example.demo.converter;

import com.example.demo.dto.AddressDto;
import com.example.demo.entity.Address;

import java.util.List;

public interface AddressDtoConverter {

    List<AddressDto> convert(List<Address> addresses, boolean includePerson);

    AddressDto convert(Address address, boolean includePerson);

}
