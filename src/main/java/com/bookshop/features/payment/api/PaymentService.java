package com.bookshop.features.payment.api;

import com.bookshop.features.payment.data.entity.PaymentMethodEntity;

import java.util.List;

public interface PaymentService {

    List<PaymentMethodEntity> getPaymentMethods();

    PaymentMethodEntity getPaymentMethodById(Integer paymentMethodId);
}
