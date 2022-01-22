package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveSubcategoryRequest;
import com.bookshop.features.book.api.response.SubcategoryResponse;
import com.bookshop.features.book.data.entity.SubcategoryEntity;

public class SubcategoryMapper {

    public static SubcategoryEntity mapToSubcategoryEntity(SaveSubcategoryRequest request){
        return SubcategoryEntity.builder()
                .name(request.getName())
                .build();
    }

    public static SubcategoryResponse mapToSubcategoryResponse(SubcategoryEntity subcategory) {
        return SubcategoryResponse.builder()
                .id(subcategory.getId())
                .name(subcategory.getName())
                .build();
    }

}
