package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveCategoryRequest;
import com.bookshop.features.book.api.response.CategoryResponse;
import com.bookshop.features.book.data.entity.CategoryEntity;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryResponse mapToCategoryResponse(CategoryEntity category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryResponse).collect(Collectors.toList()))
                .build();
    }

    public static CategoryEntity mapSaveCategoryRequestsToCategoryEntity(SaveCategoryRequest request) {
        return CategoryEntity.builder()
                .name(request.getName())
                .subcategories(new ArrayList<>())
                .build();
    }
}
