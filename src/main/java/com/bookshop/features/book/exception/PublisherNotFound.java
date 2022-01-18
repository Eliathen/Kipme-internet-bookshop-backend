package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class PublisherNotFound extends ResourceNotFoundException {
    public PublisherNotFound(Integer id) {
        super(String.format(ExceptionMessages.PUBLISHER_NOT_FOUND, id));
    }
}
