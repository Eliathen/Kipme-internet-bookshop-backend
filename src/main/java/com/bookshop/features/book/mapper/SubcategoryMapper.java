package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.response.SubcategoryResponse;
import com.bookshop.features.book.data.entity.SubcategoryEntity;
import com.bookshop.features.book.domain.model.Subcategory;

public class SubcategoryMapper {

    public static SubcategoryEntity mapToSubcategoryEntity(Subcategory subcategory){
        return SubcategoryEntity.builder()
                .id(subcategory.getId())
                .name(subcategory.getName())
                .build();
    }

    public static Subcategory mapToSubcategory(SubcategoryEntity subcategoryEntity) {
        return Subcategory.builder()
                .id(subcategoryEntity.getId())
                .name(subcategoryEntity.getName())
                .build();

    }

    public static SubcategoryResponse mapToSubcategoryResponse(Subcategory subcategory) {
        return SubcategoryResponse.builder()
                .id(subcategory.getId())
                .name(subcategory.getName())
                .build();
    }
}
