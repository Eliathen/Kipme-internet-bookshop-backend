package com.bookshop.features.payment.domain.payment;

import com.bookshop.features.order.data.entity.OrderEntity;

public class PayPalMethod implements PaymentMethod {

    @Override
    public boolean pay(OrderEntity order) {
        return false;
    }
}
