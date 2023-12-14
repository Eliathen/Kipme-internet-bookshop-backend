package com.bookshop.features.order.api.response;


import com.bookshop.features.order.data.entity.OrderStatus;
import com.bookshop.features.payment.api.response.PaymentDetailsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(
        @JsonProperty(value = "id")
        UUID id,
        @JsonProperty(value = "date")
        LocalDateTime orderDate,
        @JsonProperty(value = "books")
        List<OrderPositionResponse> books,
        @JsonProperty(value = "payment_details")
        PaymentDetailsResponse payment,
        @JsonProperty(value = "delivery_details")
        DeliveryDetailsResponse delivery,
        @JsonProperty(value = "status")
        OrderStatus orderStatus,
        @JsonProperty(value = "price")
        BigDecimal fullPrice
) {
}
