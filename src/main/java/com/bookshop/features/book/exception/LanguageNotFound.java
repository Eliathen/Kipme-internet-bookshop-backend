package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class LanguageNotFound extends ResourceNotFoundException {

    public LanguageNotFound(Integer id) {
        super(String.format(ExceptionMessages.LANGUAGE_NOT_FOUND, id));
    }
}
