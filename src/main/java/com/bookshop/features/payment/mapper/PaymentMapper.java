package com.bookshop.features.payment.mapper;

import com.bookshop.features.payment.api.response.PaymentMethodResponse;
import com.bookshop.features.payment.data.entity.PaymentMethodEntity;

public class PaymentMapper {


    public static PaymentMethodResponse mapToPaymentMethodResponse(PaymentMethodEntity paymentEntity) {
        return PaymentMethodResponse.builder()
                .id(paymentEntity.getId())
                .name(paymentEntity.getName())
                .build();
    }
}
