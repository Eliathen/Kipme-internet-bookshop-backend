package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class CoverNotFound extends ResourceNotFoundException {



    public CoverNotFound() {
        super(ExceptionMessages.COVER_NOT_FOUND);
    }
}
