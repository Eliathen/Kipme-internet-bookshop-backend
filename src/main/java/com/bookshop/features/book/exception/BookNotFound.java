package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class BookNotFound extends ResourceNotFoundException {

    public BookNotFound(Long id) {
        super(String.format(ExceptionMessages.BOOK_NOT_FOUND, id));
    }
}
