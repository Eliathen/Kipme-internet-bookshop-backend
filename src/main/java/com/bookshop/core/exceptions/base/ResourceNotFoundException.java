package com.bookshop.core.exceptions.base;

import com.bookshop.core.exceptions.base.BusinessException;

public class ResourceNotFoundException extends BusinessException {

    public ResourceNotFoundException(String  message) {
        super(message);
    }
}
