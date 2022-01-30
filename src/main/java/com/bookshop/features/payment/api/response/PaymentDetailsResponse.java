package com.bookshop.features.payment.api.response;

import com.bookshop.features.payment.data.entity.PaymentStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Builder
public class PaymentDetailsResponse {

    private UUID id;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    private PaymentMethodResponse paymentMethod;

    private PaymentStatus paymentStatus;

    @JsonCreator
    public PaymentDetailsResponse(@JsonProperty("id") UUID id,
                                  @JsonProperty("amount") BigDecimal amount,
                                  @JsonProperty("created_at") LocalDateTime createdAt,
                                  @JsonProperty("method") PaymentMethodResponse paymentMethod,
                                  @JsonProperty("status") PaymentStatus paymentStatus) {
        this.id = id;
        this.amount = amount;
        this.createdAt = createdAt;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }
}
