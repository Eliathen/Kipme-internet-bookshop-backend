package com.bookshop.features.order.data;

import com.bookshop.features.order.data.entity.DeliveryMethodEntity;
import com.bookshop.features.order.data.jpa.DeliveryMethodJpaRepository;
import com.bookshop.features.order.domain.repository.DeliveryMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class DeliveryMethodRepositoryImpl implements DeliveryMethodRepository {

    private final DeliveryMethodJpaRepository deliveryMethodJpaRepository;

    @Override
    public List<DeliveryMethodEntity> getDeliveryMethods() {
        return deliveryMethodJpaRepository.findAll();
    }

    @Override
    public Optional<DeliveryMethodEntity> getDeliveryById(Integer deliveryMethodId) {
        return deliveryMethodJpaRepository.findById(deliveryMethodId);
    }
}
