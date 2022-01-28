package com.bookshop.features.basket.api.response;

import com.bookshop.features.book.api.response.BookResponse;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class BasketItemResponse {

    private Long id;

    private BookResponse bookResponse;

    private Integer quantity;

    private BigDecimal price;


}
