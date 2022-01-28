package com.bookshop.features.book.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.InvalidAttributeValueException;

public class InvalidCategorySaleUnitException extends InvalidAttributeValueException {


    public InvalidCategorySaleUnitException() {
        super(ExceptionMessages.INVALID_SALE_UNIT_CATEGORY);
    }
}
