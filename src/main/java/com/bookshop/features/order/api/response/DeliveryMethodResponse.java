package com.bookshop.features.order.api.response;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Builder
@Getter
public class DeliveryMethodResponse {

    private Integer id;

    private String name;

    private BigDecimal price;

}
