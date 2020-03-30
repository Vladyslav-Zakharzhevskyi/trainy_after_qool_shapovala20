package com.example.demo.dto;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class NewPersonDto {

    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    @JsonCreator
    public NewPersonDto(@JsonProperty(value = "username", required = true) String userName,
                        @JsonProperty(value = "firstName", required = false) String firstName,
                        @JsonProperty(value = "lastName", required = false) String lastName,
                        @JsonProperty(value = "email", required = false) String email,
                        @JsonProperty(value = "password", required = true) String password) {
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
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
}
