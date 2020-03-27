package com.example.demo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationSettingsController {

    @Value("${authentication.method}")
    private String authenticationType;

    @RequestMapping(value = "/getApplicationSettings", method = RequestMethod.GET)
    public ResponseEntity<Map<String, String>> getApplicationSettingsList() {
        Map<String, String> settings = new HashMap<String, String>();
        settings.put("authenticationType", authenticationType);

        return ResponseEntity.ok().body(settings);
    }
}
