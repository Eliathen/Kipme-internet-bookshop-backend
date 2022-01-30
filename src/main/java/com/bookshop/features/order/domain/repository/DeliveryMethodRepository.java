package com.bookshop.features.order.domain.repository;

import com.bookshop.features.order.data.entity.DeliveryMethodEntity;

import java.util.List;
import java.util.Optional;

public interface DeliveryMethodRepository {

    List<DeliveryMethodEntity> getDeliveryMethods();

    Optional<DeliveryMethodEntity> getDeliveryById(Integer deliveryMethodId);
}
