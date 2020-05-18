package com.investigation.develop.circle.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.UUID;

public class HomeBusinessUser extends User {
    private UUID id;
    private String firstName;
    private String lastName;
    /*additional field*/

    public HomeBusinessUser(UUID id, String username, String password, Collection<? extends GrantedAuthority> authorities,
                            String firstName, String lastName) {
        super(username, password, authorities);
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
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
}
