package com.bookshop.features.order.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.BusinessException;

public class BasketIsEmpty extends BusinessException {


    public BasketIsEmpty() {
        super(ExceptionMessages.EMPTY_BASKET);
    }
}
