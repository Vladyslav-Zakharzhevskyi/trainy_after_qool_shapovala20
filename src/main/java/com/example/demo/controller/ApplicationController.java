package com.example.demo.controller;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ApplicationController {


    @Value("${authentication.method}")
    private String authenticationType;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String welcomeMessage() {
        return "index";
    }

    @RequestMapping(value = "/api/getApplicationSettings", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getApplicationSettingsList() {
        Map<String, String> settings = new HashMap<String, String>();
        settings.put("authenticationType", authenticationType);

        return ResponseEntity.ok().body(settings);
    }
}
