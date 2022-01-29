package com.bookshop.features.order.domain.service.port;

import com.bookshop.features.order.data.entity.DeliveryMethodEntity;

import java.util.List;

public interface OrderService {

    List<DeliveryMethodEntity> getDeliveryMethods();
}
