package com.investigation.develop.circle.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    private Logger logger = LogManager.getLogger(GeneralExceptionHandler.class);


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleError(Exception ex) {
        logger.error(ex);
        return new ResponseEntity<>(new ExceptionResponse("Backend Error", ex.toString(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BaseSystemException.class)
    public ResponseEntity<ExceptionResponse> handleBaseSystemError(BaseSystemException ex) {
        logger.error(ex);
        return new ResponseEntity<>(new ExceptionResponse("Backend Error", ex.toString(), ex.getStatus().getCode(), ex.getData()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UserEmailNotConfirmedException.class)
    public ResponseEntity<ExceptionResponse> handleUserEmailNotConfirmedError(UserEmailNotConfirmedException ex) {
        logger.error(ex);
        return new ResponseEntity<>(new ExceptionResponse("Backend Error", ex.toString(), ex.getStatus().getCode()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
