package com.bookshop.features.payment.mapper;

import com.bookshop.features.payment.api.response.PaymentDetailsResponse;
import com.bookshop.features.payment.api.response.PaymentMethodResponse;
import com.bookshop.features.payment.data.entity.PaymentEntity;
import com.bookshop.features.payment.data.entity.PaymentMethodEntity;

public class PaymentMapper {


    public static PaymentMethodResponse mapToPaymentMethodResponse(PaymentMethodEntity paymentEntity) {
        return PaymentMethodResponse.builder()
                .id(paymentEntity.getId())
                .name(paymentEntity.getName())
                .build();
    }

    public static PaymentDetailsResponse mapToPaymentDetailsResponse(PaymentEntity payment) {
        return PaymentDetailsResponse.builder()
                .id(payment.getId())
                .amount(payment.getAmount())
                .paymentStatus(payment.getPaymentStatus())
                .paymentMethod(mapToPaymentMethodResponse(payment.getPaymentMethodEntity()))
                .createdAt(payment.getCreatedAt())
                .build();
    }
}
