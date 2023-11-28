package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.BusinessException;

public class EmptyCoverException extends BusinessException {

    public EmptyCoverException(String emptyCover) {
        super(ExceptionMessages.EMPTY_COVER);
    }
}
