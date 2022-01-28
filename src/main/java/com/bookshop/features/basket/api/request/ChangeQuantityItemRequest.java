package com.bookshop.features.basket.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeQuantityItemRequest {

    private Long bookId;

    private Integer quantity;

    @JsonCreator
    public ChangeQuantityItemRequest(@JsonProperty(value = "book_id", required = true) Long bookId,
                                     @JsonProperty(value = "quantity", required = true) Integer quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
