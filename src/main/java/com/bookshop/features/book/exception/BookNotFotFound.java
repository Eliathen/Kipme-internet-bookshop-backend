package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;

public class BookNotFotFound extends RuntimeException {

    public BookNotFotFound(Long id) {
        super(String.format(ExceptionMessages.BOOK_NOT_FOUND, id));
    }
}
