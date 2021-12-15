package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;

public class AuthorNotFound extends RuntimeException{

    public AuthorNotFound(Integer id) {
        super(String.format(ExceptionMessages.AUTHOR_NOT_FOUND, id));
    }
}
