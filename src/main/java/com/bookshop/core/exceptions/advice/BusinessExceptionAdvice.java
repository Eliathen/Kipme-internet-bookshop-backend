package com.bookshop.core.exceptions.advice;

import com.bookshop.core.exceptions.ErrorInfo;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;
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

}
