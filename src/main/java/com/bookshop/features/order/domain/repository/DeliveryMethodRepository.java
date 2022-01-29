package com.bookshop.features.order.domain.repository;

import com.bookshop.features.order.data.entity.DeliveryMethodEntity;

import java.util.List;

public interface DeliveryMethodRepository {

    List<DeliveryMethodEntity> getDeliveryMethods();
}
