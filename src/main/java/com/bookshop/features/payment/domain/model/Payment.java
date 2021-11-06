package com.bookshop.features.payment.domain.model;

import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;
@Builder
public class Payment {

    private UUID id;

    private BigDecimal amount;

    private PaymentType paymentType;

    private PaymentStatus paymentStatus;

}
