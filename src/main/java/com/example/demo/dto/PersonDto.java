package com.example.demo.dto;

import java.util.List;
import java.util.UUID;

public class PersonDto {

    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private List<AddressDto> addresses;
    private List<JobDto> jobs;
    private Integer positionId;
    private Double salary;
    private String currency;

    public PersonDto(UUID id, String userName, String firstName, String lastName, String email, Integer age, List<AddressDto> addresses, List<JobDto> jobs, Integer positionId, Double salary, String currency) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.addresses = addresses;
        this.jobs = jobs;
        this.positionId = positionId;
        this.salary = salary;
        this.currency = currency;
    }

    public UUID getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
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

    public Integer getPositionId() {
        return positionId;
    }
}
