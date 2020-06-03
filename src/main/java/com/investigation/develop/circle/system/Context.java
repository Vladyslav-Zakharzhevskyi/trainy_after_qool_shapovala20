package com.investigation.develop.circle.system;

import com.investigation.develop.circle.entity.Person;
import org.springframework.security.core.Authentication;

import java.security.Principal;

public class Context {

    public static Person getUser(Principal principal) {
        if (principal == null) {
            return null;
        }
        return (Person) ((Authentication) principal).getPrincipal();
    }
}
