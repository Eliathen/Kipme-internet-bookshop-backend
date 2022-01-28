package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.InvalidAttributeValueException;

public class InvalidSaleValue extends InvalidAttributeValueException {

    public InvalidSaleValue() {
        super(ExceptionMessages.INVALID_SALE_VALUE);
    }
}
