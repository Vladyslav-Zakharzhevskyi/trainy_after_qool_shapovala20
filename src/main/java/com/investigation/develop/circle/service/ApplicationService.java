package com.investigation.develop.circle.service;

import com.investigation.develop.circle.exception.ApplicationException;
import com.investigation.develop.circle.exception.Code;
import com.investigation.develop.circle.system.ApplicationContextProvider;
import com.google.common.base.Predicates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    private final static String ENUM_PATH_PREFIX = "com.investigation.develop.circle.entity.";

    public Object getDataByRepository(String repositoryName) throws ApplicationException {

        Optional<Object> optionalRepo =
                Optional.of(ApplicationContextProvider.getApplicationContext().getBean(repositoryName));

        return optionalRepo
                .filter(Predicates.instanceOf(JpaRepository.class))
                .map(repo -> ((JpaRepository) repo).findAll())
                .orElseThrow(() ->
                        new ApplicationException(String.format("Cannot find repository with name: %s", repositoryName), Code.GENERAL_EXCEPTION));
    }

    public Object getDataByEnumClass(String className) throws ClassNotFoundException, ApplicationException {

        Class<?> clazz = Class.forName(ENUM_PATH_PREFIX + className);

        if (clazz.isEnum()) {
            return clazz.getEnumConstants();
        }
        throw new ApplicationException(String.format("Can't obtain data from enum: %s", className), Code.GENERAL_EXCEPTION);

    }

}
