package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class SubcategoryNotFoundException extends ResourceNotFoundException {
    public SubcategoryNotFoundException(Integer subcategoryId) {
        super(String.format(ExceptionMessages.SUBCATEGORY_WITH_ID_NOT_FOUND, subcategoryId));
    }
}
