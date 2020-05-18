package com.investigation.develop.circle.dto;

import java.util.List;
import java.util.UUID;

public class PersonDto {

    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private Integer age;
    private Integer positionId;
    private Double salary;
    private String currency;
    private String address;

    public PersonDto(UUID id, String userName, String firstName, String lastName, String email, Integer age, Integer positionId, Double salary, String currency, String address) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.positionId = positionId;
        this.salary = salary;
        this.currency = currency;
        this.address = address;
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

    public Double getSalary() {
        return salary;
    }

    public String getCurrency() {
        return currency;
    }

    public Integer getPositionId() {
        return positionId;
    }

    public String getAddress() {
        return address;
    }
}
