package com.bookshop.features.basket.domain.repository;

import com.bookshop.features.basket.data.entity.BasketEntity;

import java.util.Optional;

public interface BasketRepository {

    Optional<BasketEntity> getUserBasket(Long userId);

    BasketEntity saveBasket(BasketEntity basketEntity);
}
