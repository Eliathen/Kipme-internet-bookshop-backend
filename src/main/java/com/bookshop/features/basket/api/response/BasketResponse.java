package com.bookshop.features.basket.api.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class BasketResponse {

    private Long id;

    private List<BasketItemResponse> items;

    private BigDecimal fullPrice;
}
