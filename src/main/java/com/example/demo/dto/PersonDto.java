package com.example.demo.dto;

import java.util.List;
import java.util.UUID;

public class PersonDto {

    private UUID id;
    private String firstName;
    private String lastName;
    private Integer age;
    private List<AddressDto> addresses;
    private List<JobDto> jobs;
    private Double salary;
    private String currency;

    public PersonDto(UUID id, String firstName, String lastName, Integer age, List<AddressDto> addresses, List<JobDto> jobs, Double salary, String currency) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.addresses = addresses;
        this.jobs = jobs;
        this.salary = salary;
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<AddressDto> getAddresses() {
        return addresses;
    }

    public List<JobDto> getJobs() {
        return jobs;
    }

    public Double getSalary() {
        return salary;
    }

    public String getCurrency() {
        return currency;
    }
}
