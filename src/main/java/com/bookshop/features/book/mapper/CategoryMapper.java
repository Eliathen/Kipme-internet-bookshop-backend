package com.bookshop.features.book.mapper;

import com.bookshop.features.book.api.response.CategoryResponse;
import com.bookshop.features.book.data.entity.CategoryEntity;
import com.bookshop.features.book.domain.model.Category;
import com.bookshop.features.book.domain.model.Subcategory;

import java.util.stream.Collectors;

public class CategoryMapper {

    public static CategoryEntity mapToCategoryEntity(Category category){
        return CategoryEntity.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryEntity).collect(Collectors.toSet()))
                .build();
    }

    public static Category mapToCategory(CategoryEntity category) {
        return Category.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategory).collect(Collectors.toList()))
                .build();
    }

    public static CategoryResponse mapToCategoryResponse(Category category) {
        return CategoryResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .subcategories(category.getSubcategories().stream().map(SubcategoryMapper::mapToSubcategoryResponse).collect(Collectors.toList()))
                .build();
    }
}
