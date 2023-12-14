package com.bookshop.features.order.api.response;

import com.bookshop.features.book.api.response.BookBaseResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderPositionResponse(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "book")
        BookBaseResponse orderedBook,
        @JsonProperty(value = "price")
        BigDecimal price
) {
}
