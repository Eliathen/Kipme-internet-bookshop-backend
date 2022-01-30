package com.bookshop.features.payment.domain.repository;


import com.bookshop.features.payment.data.entity.PaymentMethodEntity;

import java.util.List;
import java.util.Optional;

public interface PaymentRepository {

    List<PaymentMethodEntity> getPaymentMethods();

    Optional<PaymentMethodEntity> getPaymentMethodById(Integer paymentMethodId);
}
