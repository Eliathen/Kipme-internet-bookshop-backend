package com.bookshop.core.exceptions.advice;

import com.bookshop.core.exceptions.ErrorInfo;
import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.features.user.exception.InvalidEmailOrPassword;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AuthExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(AuthExceptionAdvice.class);

    @ExceptionHandler(InvalidEmailOrPassword.class)
    public ResponseEntity<ErrorInfo> authException(InvalidEmailOrPassword exception) {
        logger.error(exception.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ErrorInfo> badCredentials(BadCredentialsException exception) {
        logger.error(exception.getMessage());
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), ExceptionMessages.INVALID_EMAIL_ADDRESS_OR_PASSWORD);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
