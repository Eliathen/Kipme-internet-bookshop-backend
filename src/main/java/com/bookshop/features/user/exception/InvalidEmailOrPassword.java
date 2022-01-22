package com.bookshop.features.user.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.AuthException;

public class InvalidEmailOrPassword extends AuthException {

    public InvalidEmailOrPassword() {
        super(ExceptionMessages.INVALID_EMAIL_ADDRESS_OR_PASSWORD);
    }
}
