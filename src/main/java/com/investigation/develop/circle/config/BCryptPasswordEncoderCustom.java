package com.investigation.develop.circle.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class BCryptPasswordEncoderCustom extends BCryptPasswordEncoder {

    public BCryptPasswordEncoderCustom() {
        super(8);
    }
}
