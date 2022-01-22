package com.bookshop.features.user.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceAlreadyExists;

public class UserAlreadyExists extends ResourceAlreadyExists {

    public UserAlreadyExists(String message) {
        super(ExceptionMessages.USER_ALREADY_EXISTS);
    }
}
