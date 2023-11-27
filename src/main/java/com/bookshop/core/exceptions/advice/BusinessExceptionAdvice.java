package com.bookshop.core.exceptions.advice;

import com.bookshop.core.exceptions.ErrorInfo;
import com.bookshop.core.exceptions.base.ResourceAlreadyExists;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;
import com.bookshop.features.user.exception.InvalidEmailAddress;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class BusinessExceptionAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfo> notFound(ResourceNotFoundException exception) {
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorInfo> alreadyExists(ResourceAlreadyExists exception) {
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> constraintViolation(ConstraintViolationException exception) {
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailAddress.class)
    public ResponseEntity<ErrorInfo> invalidEmailAddress(InvalidEmailAddress exception) {
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }
}
