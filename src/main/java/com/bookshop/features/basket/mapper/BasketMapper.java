package com.bookshop.features.basket.mapper;

import com.bookshop.features.basket.api.response.BasketItemResponse;
import com.bookshop.features.basket.api.response.BasketResponse;
import com.bookshop.features.basket.data.entity.BasketEntity;
import com.bookshop.features.basket.data.entity.BasketItemEntity;
import com.bookshop.features.book.mapper.BookMapper;

import java.util.Collections;
import java.util.stream.Collectors;

public class BasketMapper {

    public static BasketResponse mapBasketEntityToBasketResponse(BasketEntity entity) {
        return BasketResponse.builder()
                .id(entity.getId())
                .items((entity.getItems() != null) ?
                        entity.getItems().stream().map(BasketMapper::mapBasketItemEntityToBasketResponse).collect(Collectors.toList()) :
                        Collections.emptyList()
                )
                .fullPrice(entity.getBasketFullPrice())
                .build();
    }

    public static BasketItemResponse mapBasketItemEntityToBasketResponse(BasketItemEntity entity) {
        return BasketItemResponse.builder()
                .id(entity.getId())
                .price(entity.getBook().getPrice())
                .quantity(entity.getQuantity())
                .bookResponse(BookMapper.mapToBookResponse(entity.getBook()))
                .build();
    }

}
