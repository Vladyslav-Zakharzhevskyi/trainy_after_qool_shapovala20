package com.investigation.develop.circle.config.failurehandler;

import com.investigation.develop.circle.exception.CustomExceptionStatus;
import com.investigation.develop.circle.exception.UserEmailNotConfirmedException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final Logger logger = LogManager.getLogger(CustomAuthenticationFailureHandler.class);

    @Autowired
    private ObjectMapper jacksonObjectMapper;

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        try {
            writeError(response, exception);
        } catch (IOException ioException) {
            logger.error("Error writing Exception to response");
            throw new IOException("Error writing Exception to response", ioException);
        }
    }

    private void writeError(HttpServletResponse response, AuthenticationException ex) throws IOException {
        Map<String, Object> err = new HashMap<>();
        err.put("authenticationError", ex);
        Integer statusCode = getStatusCode(ex);
        if (statusCode != null) {
            err.put("statusCode", statusCode);
        }

        PrintWriter writer = response.getWriter();
        writer.write(jacksonObjectMapper.writeValueAsString(err));
    }

    private Integer getStatusCode(AuthenticationException ex) {
        Integer statusCode = null;
        if (ex instanceof UserEmailNotConfirmedException) {
            statusCode = ((UserEmailNotConfirmedException) ex).getStatus().getCode();
        } else if (ex instanceof UsernameNotFoundException){
            statusCode = CustomExceptionStatus.USER_NAME_NOT_FOUND.getCode();
        } else if (ex instanceof BadCredentialsException) {
            statusCode = CustomExceptionStatus.BAD_CREDENTIALS.getCode();
        }
        return statusCode;
    }
}
