package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SetSaleRequest;
import com.bookshop.features.book.data.entity.SaleEntity;

public class SaleMapper {

    public static SaleEntity mapToSaleEntity(SetSaleRequest request) {
        return SaleEntity.builder()
                .endAt(request.getEndAt())
                .startAt(request.getStartAt())
                .value(request.getValue())
                .isActive(false)
                .saleUnit(request.getUnit())
                .build();
    }

}
