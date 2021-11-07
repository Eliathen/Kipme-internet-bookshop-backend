package com.bookshop.features.book.mapper;

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
}
