package com.bookshop.features.order.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.BusinessException;

public class NotEnoughBook extends BusinessException {
    public NotEnoughBook(Long id) {
        super(String.format(ExceptionMessages.NOT_ENOUGH_BOOKS, id));
    }
}
