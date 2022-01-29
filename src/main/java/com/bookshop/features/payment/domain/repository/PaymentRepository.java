package com.bookshop.features.payment.domain.repository;


import com.bookshop.features.payment.data.entity.PaymentMethodEntity;

import java.util.List;

public interface PaymentRepository {

    List<PaymentMethodEntity> getPaymentMethods();
}
