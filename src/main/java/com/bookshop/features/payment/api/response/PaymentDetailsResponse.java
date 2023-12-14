package com.bookshop.features.payment.api.response;

import com.bookshop.features.payment.data.entity.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record PaymentDetailsResponse(
        @JsonProperty(value = "id")
        UUID id,
        @JsonProperty(value = "amount")
        BigDecimal amount,
        @JsonProperty(value = "created_at")
        LocalDateTime createdAt,
        @JsonProperty(value = "method")
        PaymentMethodResponse paymentMethod,
        @JsonProperty(value = "status")
        PaymentStatus paymentStatus
) {

}
