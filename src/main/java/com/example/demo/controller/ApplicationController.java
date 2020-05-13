package com.example.demo.controller;


import com.example.demo.exception.BaseSystemException;
import com.example.demo.exception.CustomExceptionStatus;
import com.example.demo.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Controller
public class ApplicationController {


    @Value("${authentication.method}")
    private String authenticationType;

    @Autowired
    private ApplicationService applicationService;

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

    @RequestMapping(value = "/api/getDataByType/{type}/{resource}", method = RequestMethod.GET)
    public ResponseEntity<Object> getDataByType(@PathVariable(name = "type") String type,
                                                @PathVariable(name = "resource") String name) throws BaseSystemException, ClassNotFoundException {
        Objects.requireNonNull(type);
        Objects.requireNonNull(name);

        Object data;
        switch (type) {
            case "enum":
                data = applicationService.getDataByEnumClass(name);
                break;
            case "repository":
                data = applicationService.getDataByRepository(name);
                break;
            default:
                throw new BaseSystemException(String.format("Can't handle for type: %s", type), CustomExceptionStatus.GENERAL_EXCEPTION);
        }

        return ResponseEntity.ok(data);
    }
}
