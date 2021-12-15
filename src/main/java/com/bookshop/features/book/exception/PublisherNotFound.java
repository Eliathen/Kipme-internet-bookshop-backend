package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;

public class PublisherNotFound extends RuntimeException {
    public PublisherNotFound(Integer id) {
        super(String.format(ExceptionMessages.PUBLISHER_NOT_FOUND, id));
    }
}
