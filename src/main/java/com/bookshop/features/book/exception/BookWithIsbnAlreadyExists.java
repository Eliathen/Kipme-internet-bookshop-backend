package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceAlreadyExists;

public class BookWithIsbnAlreadyExists extends ResourceAlreadyExists {


    public BookWithIsbnAlreadyExists(String isbn) {
        super(String.format(ExceptionMessages.BOOK_WITH_ID_ALREADY_EXISTS, isbn));
    }
}
