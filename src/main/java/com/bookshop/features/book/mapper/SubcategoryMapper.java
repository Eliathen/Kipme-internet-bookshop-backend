package com.bookshop.features.book.mapper;

import com.bookshop.features.book.data.entity.SubcategoryEntity;
import com.bookshop.features.book.domain.model.Subcategory;

public class SubcategoryMapper {

    public static SubcategoryEntity mapToSubcategoryEntity(Subcategory subcategory){
        return SubcategoryEntity.builder()
                .id(subcategory.getId())
                .name(subcategory.getName())
                .build();
    }
}
