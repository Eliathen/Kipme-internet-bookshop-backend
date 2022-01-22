package com.bookshop.features.book.domain.service.port;

import com.bookshop.features.book.api.request.SaveSubcategoryRequest;
import com.bookshop.features.book.data.entity.CategoryEntity;
import com.bookshop.features.book.data.entity.SubcategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getCategories();

    CategoryEntity saveCategory(CategoryEntity category);

    SubcategoryEntity saveSubcategory(int categoryId, SaveSubcategoryRequest subcategory);

    CategoryEntity getCategory(Integer id);

    SubcategoryEntity getSubcategoryById(Integer categoryId);
}
