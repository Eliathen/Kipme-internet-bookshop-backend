package com.bookshop.core.exceptions.advice;

import com.bookshop.core.exceptions.ErrorInfo;
import com.bookshop.core.exceptions.base.ResourceAlreadyExists;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;
import com.bookshop.features.book.exception.EmptyCoverException;
import com.bookshop.features.user.exception.InvalidEmailAddress;
import jakarta.validation.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@ControllerAdvice
public class BusinessExceptionAdvice {

    private final Logger logger = LoggerFactory.getLogger(BusinessExceptionAdvice.class);
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorInfo> notFound(ResourceNotFoundException exception) {
        String message = exception.getMessage();
        logger.error(message);
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), Collections.singletonList(message));
        return new ResponseEntity<>(errorInfo, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ResourceAlreadyExists.class)
    public ResponseEntity<ErrorInfo> alreadyExists(ResourceAlreadyExists exception) {
        String message = exception.getMessage();
        logger.error(message);
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), Collections.singletonList(message));
        return new ResponseEntity<>(errorInfo, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorInfo> constraintViolation(ConstraintViolationException exception) {
        String message = exception.getMessage();
        logger.error(message);
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), Collections.singletonList(message));
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidEmailAddress.class)
    public ResponseEntity<ErrorInfo> invalidEmailAddress(InvalidEmailAddress exception) {
        String message = exception.getMessage();
        logger.error(message);
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), Collections.singletonList(message));
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(EmptyCoverException.class)
    public ResponseEntity<ErrorInfo> handleEmptyCoverException(EmptyCoverException exception) {
        String errorMessage = exception.getMessage();
        logger.error(errorMessage);
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), Collections.singletonList(errorMessage));
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorInfo> argumentsNotValid(MethodArgumentNotValidException exception) {
        List<String> errorMessage = getErrorMessagesFromException(exception);
        for (String message : errorMessage) {
            logger.error(message);
        }
        ErrorInfo errorInfo = new ErrorInfo(LocalDateTime.now(), errorMessage);
        return new ResponseEntity<>(errorInfo, HttpStatus.BAD_REQUEST);
    }

    private List<String> getErrorMessagesFromException(MethodArgumentNotValidException exception) {
        return exception.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();
    }
}
