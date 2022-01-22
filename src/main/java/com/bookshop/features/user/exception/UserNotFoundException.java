package com.bookshop.features.user.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class UserNotFoundException extends ResourceNotFoundException {
    public UserNotFoundException() {
        super(ExceptionMessages.USER_NOT_FOUND_EXCEPTION);
    }
}
