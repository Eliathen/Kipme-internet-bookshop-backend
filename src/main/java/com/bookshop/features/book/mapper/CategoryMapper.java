package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.request.SaveCategoryRequest;
import com.bookshop.features.book.api.response.CategoryResponse;
import com.bookshop.features.book.data.entity.CategoryEntity;
import com.bookshop.features.book.domain.model.Category;

import java.util.Collections;
import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryEntity mapCategoryToCategoryEntity(Category category) {
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapSubcategoryToSubcategoryEntity).collect(Collectors.toSet()))
                .build();
    }

    public static Category mapCategoryEntityToCategory(CategoryEntity category) {
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapSubcategoryEntityToSubcategory).collect(Collectors.toList()))
                .build();
    }

    public static CategoryResponse mapCategoryToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapSubcategoryToSubcategoryResponse).collect(Collectors.toList()))
                .build();
    }

    public static Category mapSaveCategoryRequestToCategory(SaveCategoryRequest request) {
        return Category.builder()
                .name(request.getName())
                .subcategories(Collections.emptyList())
                .build();
    }
}
