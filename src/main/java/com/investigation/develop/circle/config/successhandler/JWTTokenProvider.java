package com.investigation.develop.circle.config.successhandler;

import com.investigation.develop.circle.config.JWTUtils;
import com.investigation.develop.circle.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
public class JWTTokenProvider {

    @Autowired
    private JWTUtils jwtUtils;

    private final static Integer JWT_TOKEN_AVAILABLE_TIME = 3;

    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) {

        Person user = (Person) authentication.getPrincipal();
        String jwtToken = jwtUtils.generateJwtToken(user, JWT_TOKEN_AVAILABLE_TIME);

        response.addHeader("Authentication", jwtToken);
    }

}


