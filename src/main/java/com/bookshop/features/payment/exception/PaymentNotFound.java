package com.bookshop.features.payment.exception;

import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class PaymentNotFound extends ResourceNotFoundException {


    public PaymentNotFound() {
        super(ExceptionMessages.PAYMENT_NOT_FOUND);
    }
}
