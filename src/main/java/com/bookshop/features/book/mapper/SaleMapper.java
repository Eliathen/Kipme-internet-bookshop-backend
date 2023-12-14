package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SetSaleRequest;
import com.bookshop.features.book.data.entity.SaleEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SaleMapper {

    public static SaleEntity mapToSaleEntity(SetSaleRequest request) {
        return SaleEntity.builder()
                .endAt(request.endAt())
                .startAt(request.startAt())
                .value(request.value())
                .isActive(false)
                .saleUnit(request.unit())
                .build();
    }

}
