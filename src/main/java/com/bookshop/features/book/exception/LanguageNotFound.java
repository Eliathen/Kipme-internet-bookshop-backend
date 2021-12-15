package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;

public class LanguageNotFound extends RuntimeException{


    public LanguageNotFound(Integer id) {
        super(String.format(ExceptionMessages.LANGUAGE_NOT_FOUND, id));
    }
}
