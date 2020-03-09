package com.example.demo.dto;

import java.util.UUID;

public class AddressDto {

    private UUID id;
    private String city;
    private String street;
    private String buildingNum;
    private String addressType;
    private PersonDto person;

    public AddressDto(UUID id, String city, String street, String buildingNum, String addressType, PersonDto person) {
        this.id = id;
        this.city = city;
        this.street = street;
        this.buildingNum = buildingNum;
        this.addressType = addressType;
        this.person = person;
    }

    public UUID getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    public String getStreet() {
        return street;
    }

    public String getBuildingNum() {
        return buildingNum;
    }

    public String getAddressType() {
        return addressType;
    }

    public PersonDto getPerson() {
        return person;
    }
}
