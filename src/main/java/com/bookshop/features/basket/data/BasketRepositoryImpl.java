package com.bookshop.features.basket.data;

import com.bookshop.features.basket.data.entity.BasketEntity;
import com.bookshop.features.basket.data.jpa.BasketJpaRepository;
import com.bookshop.features.basket.domain.repository.BasketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class BasketRepositoryImpl implements BasketRepository {

    private final BasketJpaRepository basketJpaRepository;

    @Override
    public Optional<BasketEntity> getUserBasket(Long userId) {
        return basketJpaRepository.getBasketEntityByUserEntityId(userId);
    }

    @Override
    public BasketEntity saveBasket(BasketEntity basketEntity) {
        return basketJpaRepository.saveAndFlush(basketEntity);
    }
}
