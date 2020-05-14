package com.example.demo.service;

import com.example.demo.exception.BaseSystemException;
import com.example.demo.exception.CustomExceptionStatus;
import com.example.demo.system.ApplicationContextProvider;
import com.google.common.base.Predicates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ApplicationService {

    private final static String ENUM_PATH_PREFIX = "com.example.demo.entity.";

    public Object getDataByRepository(String repositoryName) throws BaseSystemException {

        Optional<Object> optionalRepo =
                Optional.of(ApplicationContextProvider.getApplicationContext().getBean(repositoryName));

        return optionalRepo
                .filter(Predicates.instanceOf(JpaRepository.class))
                .map(repo -> ((JpaRepository) repo).findAll())
                .orElseThrow(() ->
                        new BaseSystemException(String.format("Cannot find repository with name: %s", repositoryName), CustomExceptionStatus.GENERAL_EXCEPTION));
    }

    public Object getDataByEnumClass(String className) throws ClassNotFoundException, BaseSystemException {

        Class<?> clazz = Class.forName(ENUM_PATH_PREFIX + className);

        if (clazz.isEnum()) {
            return clazz.getEnumConstants();
        }
        throw new BaseSystemException(String.format("Can't obtain data from enum: %s", className), CustomExceptionStatus.GENERAL_EXCEPTION);

    }

}
