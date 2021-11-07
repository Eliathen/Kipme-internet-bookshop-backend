package com.bookshop.features.order.domain.model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;

@Builder
public class DeliveryType {

    private Integer id;

    private String name;

    private BigDecimal price;
}
