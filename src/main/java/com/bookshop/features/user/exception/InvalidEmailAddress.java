package com.bookshop.features.user.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.BusinessException;

public class InvalidEmailAddress extends BusinessException {

    public InvalidEmailAddress() {
        super(ExceptionMessages.INVALID_EMAIL_ADDRESS_EXCEPTION);
    }
}
