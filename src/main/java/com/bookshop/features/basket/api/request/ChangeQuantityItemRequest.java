package com.bookshop.features.basket.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChangeQuantityItemRequest {

    @NotNull(message = "Provide book's id")
    private Long bookId;

    @NotNull(message = "Provide book's quantity")
    private Integer quantity;

    @JsonCreator
    public ChangeQuantityItemRequest(@JsonProperty(value = "book_id", required = true) Long bookId,
                                     @JsonProperty(value = "quantity", required = true) Integer quantity) {
        this.bookId = bookId;
        this.quantity = quantity;
    }
}
