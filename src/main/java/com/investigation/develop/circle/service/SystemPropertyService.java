package com.investigation.develop.circle.service;

import com.investigation.develop.circle.entity.SystemProperty;
import com.investigation.develop.circle.repository.SystemPropertyRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SystemPropertyService {

    private Logger logger = LogManager.getLogger(SystemPropertyService.class);

    @Autowired
    private SystemPropertyRepository systemPropertyRepository;

    public boolean getSystemPropertyAsBoolean(String key) {
        SystemProperty property = systemPropertyRepository.findSystemPropertyByKey(key);
        logger.info(property);

        return property != null && Boolean.parseBoolean(property.getValue());
    }

    public String getSystemPropertyAsString(String key) {
        SystemProperty property = systemPropertyRepository.findSystemPropertyByKey(key);
        logger.info(property);

        return property != null ? property.getValue() : "";
    }
}
