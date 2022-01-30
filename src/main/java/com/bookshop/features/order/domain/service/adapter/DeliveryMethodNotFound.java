package com.bookshop.features.order.domain.service.adapter;


import com.bookshop.core.exceptions.ExceptionMessages;
import com.bookshop.core.exceptions.base.ResourceNotFoundException;

public class DeliveryMethodNotFound extends ResourceNotFoundException {


    public DeliveryMethodNotFound() {
        super(ExceptionMessages.DELIVERY_METHOD_NOT_FOUND);
    }
}
