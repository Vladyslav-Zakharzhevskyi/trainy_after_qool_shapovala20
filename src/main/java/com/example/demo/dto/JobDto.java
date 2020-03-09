package com.example.demo.dto;

import java.util.List;
import java.util.UUID;

public class JobDto {

    private UUID id;
    private String position;
    private AddressDto address;
    private List<PersonDto> employees;
    private String strategy;

    public JobDto(UUID id, String position, AddressDto address, List<PersonDto> employees, String strategy) {
        this.id = id;
        this.position = position;
        this.address = address;
        this.employees = employees;
        this.strategy = strategy;
    }

    public UUID getId() {
        return id;
    }

    public String getPosition() {
        return position;
    }

    public AddressDto getAddress() {
        return address;
    }

    public List<PersonDto> getEmployees() {
        return employees;
    }

    public String getStrategy() {
        return strategy;
    }
}
