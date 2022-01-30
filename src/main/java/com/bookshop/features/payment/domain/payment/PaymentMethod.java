package com.bookshop.features.payment.domain.payment;

import com.bookshop.features.order.data.entity.OrderEntity;

public interface PaymentMethod {

    boolean pay(OrderEntity order);
}
