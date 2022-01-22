package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class AuthorNotFound extends ResourceNotFoundException {

    public AuthorNotFound(Integer id) {
        super(String.format(ExceptionMessages.AUTHOR_NOT_FOUND, id));
    }
}
