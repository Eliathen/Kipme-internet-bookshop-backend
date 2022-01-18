package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class CategoryNotFound extends ResourceNotFoundException {

    public CategoryNotFound(int id) {
        super(String.format(ExceptionMessages.CATEGORY_NOT_FOUND_EXCEPTION, id));
    }
}
