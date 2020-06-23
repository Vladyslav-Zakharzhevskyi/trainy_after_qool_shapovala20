package com.investigation.develop.circle.system;

import com.google.common.base.Strings;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.exception.ApplicationException;
import com.investigation.develop.circle.exception.Code;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Service
public class Context {

    private Logger logger = LogManager.getLogger(Context.class);

    @Autowired
    private Environment env;

    public static Person getUser(Principal principal) {
        if (principal == null) {
            return null;
        }
        return (Person) ((Authentication) principal).getPrincipal();
    }

    public String getHost() {
        String host = System.getenv("HOST_URL");
        //Use default profile for develop
        if (getActiveProfiles().isEmpty() && Strings.isNullOrEmpty(host)) {
            host = "http://localhost:8080/";
        }
        logger.info("Host is: " + host);
        if (Strings.isNullOrEmpty(host)) {
            throw new ApplicationException("Host value is absent. Can't build link.", Code.VALUE_MISS_MATCH);
        }
        return host;
    }

    public List<String> getActiveProfiles() {
        return Arrays.asList(env.getActiveProfiles());
    }
}
