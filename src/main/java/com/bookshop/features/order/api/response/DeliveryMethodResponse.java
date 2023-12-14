package com.bookshop.features.order.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record DeliveryMethodResponse(
        @JsonProperty(value = "id")
        Integer id,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "price")
        BigDecimal price
) {
}
