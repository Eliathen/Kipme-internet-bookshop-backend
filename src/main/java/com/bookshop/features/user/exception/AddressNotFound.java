package com.bookshop.features.user.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class AddressNotFound extends ResourceNotFoundException {

    public AddressNotFound() {
        super(ExceptionMessages.ADDRESS_NOT_FOUND);
    }
}
