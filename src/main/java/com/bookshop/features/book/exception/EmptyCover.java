package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.BusinessException;

public class EmptyCover extends BusinessException {

    public EmptyCover() {
        super(ExceptionMessages.EMPTY_COVER);
    }
}
