package com.bookshop.features.order.data.jpa;


import com.bookshop.features.order.data.entity.DeliveryMethodEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryMethodJpaRepository extends JpaRepository<DeliveryMethodEntity, Integer> {
}
