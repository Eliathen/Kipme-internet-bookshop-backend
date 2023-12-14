package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveCategoryRequest;
import com.bookshop.features.book.api.response.CategoryResponse;
import com.bookshop.features.book.api.response.CategoryResponseWithoutSubcategories;
import com.bookshop.features.book.data.entity.CategoryEntity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.stream.Collectors;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
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
                .name(request.name())
                .subcategories(new ArrayList<>())
                .build();
    }

    public static CategoryResponseWithoutSubcategories mapToCategoryWithoutSubcategoriesResponse(CategoryEntity category) {
        return CategoryResponseWithoutSubcategories.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
