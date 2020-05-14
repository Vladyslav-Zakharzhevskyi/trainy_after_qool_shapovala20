package com.example.demo.dto;


import com.example.demo.entity.Currency;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class NewPersonDto {

    private UUID id;
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Integer positionId;
    private Integer age;
    private Double salary;
    private Currency currency;

    @JsonCreator
    public NewPersonDto(@JsonProperty(value = "id") UUID id,
                        @JsonProperty(value = "userName", required = true) String userName,
                        @JsonProperty(value = "firstName", required = true) String firstName,
                        @JsonProperty(value = "lastName", required = true) String lastName,
                        @JsonProperty(value = "email", required = true) String email,
                        @JsonProperty(value = "password") String password,
                        @JsonProperty(value = "positionId") Integer positionId,
                        @JsonProperty(value = "age") Integer age,
                        @JsonProperty(value = "salary") Double salary,
                        @JsonProperty(value = "currency") Currency currency

    ) {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.positionId = positionId;
        this.age = age;
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

    public String getPassword() {
        return password;
    }

    public Integer getAge() {
        return age;
    }

    public Double getSalary() {
        return salary;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Integer getPositionId() {
        return positionId;
    }
}
