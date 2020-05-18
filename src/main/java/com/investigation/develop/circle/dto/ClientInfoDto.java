package com.investigation.develop.circle.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ClientInfoDto {

    private String name;
    private String email;
    private String phoneNumber;
    private String city;

    @JsonCreator
    public ClientInfoDto(@JsonProperty(value = "name") String name,
                         @JsonProperty(value = "email") String email,
                         @JsonProperty(value = "mobilePhone") String phoneNumber,
                         @JsonProperty(value = "city") String city) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getCity() {
        return city;
    }
}
