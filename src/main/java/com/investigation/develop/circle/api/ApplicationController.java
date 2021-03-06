package com.investigation.develop.circle.api;


import com.investigation.develop.circle.exception.ApplicationException;
import com.investigation.develop.circle.exception.Code;
import com.investigation.develop.circle.service.ApplicationService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private Logger logger = LogManager.getLogger(ApplicationController.class);

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

    @SuppressWarnings("Data from any repository available from anywhere!")
    @RequestMapping(value = "/api/getDataByType/{type}/{resource}", method = RequestMethod.GET)
    public ResponseEntity<Object> getDataByType(@PathVariable(name = "type") String type,
                                                @PathVariable(name = "resource") String name) throws ApplicationException, ClassNotFoundException {
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
                throw new ApplicationException(String.format("Can't handle for type: %s", type), Code.GENERAL_EXCEPTION);
        }

        return ResponseEntity.ok(data);
    }
}
