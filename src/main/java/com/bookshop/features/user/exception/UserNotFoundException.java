package com.bookshop.features.user.exception;

import com.bookshop.core.exceptions.ExceptionMessages;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException() {
        super(ExceptionMessages.USER_NOT_FOUND_EXCEPTION);
    }
}
