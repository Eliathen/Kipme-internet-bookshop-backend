package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import org.hibernate.resource.transaction.backend.jta.internal.synchronization.ExceptionMapper;

public class CategoryNotFound extends RuntimeException{

    public CategoryNotFound(int id) {
        super(String.format(ExceptionMessages.CATEGORY_NOT_FOUND_EXCEPTION, id));
    }
}
