package com.bookshop.features.order.api.response;

import com.bookshop.features.book.api.response.BookBaseResponse;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class OrderPositionResponse {

    private Long id;

    private BookBaseResponse orderedBook;

    private BigDecimal price;

    @JsonCreator
    public OrderPositionResponse(@JsonProperty("id") Long id,
                                 @JsonProperty("book") BookBaseResponse orderedBook,
                                 @JsonProperty("price") BigDecimal price) {
        this.id = id;
        this.orderedBook = orderedBook;
        this.price = price;
    }
}
