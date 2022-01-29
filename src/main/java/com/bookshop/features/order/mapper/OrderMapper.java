package com.bookshop.features.order.mapper;

import com.bookshop.features.order.api.response.DeliveryMethodResponse;
import com.bookshop.features.order.data.entity.DeliveryMethodEntity;

public class OrderMapper {


    public static DeliveryMethodResponse mapToDeliveryMethodResponse(DeliveryMethodEntity entity) {
        return DeliveryMethodResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .build();
    }

}
