package com.bookshop.features.basket.data.jpa;

import com.bookshop.features.basket.data.entity.BasketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BasketJpaRepository extends JpaRepository<BasketEntity, Long> {

    Optional<BasketEntity> getBasketEntityByUserEntityId(Long userId);
}
