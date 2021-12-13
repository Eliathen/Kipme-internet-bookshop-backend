package com.bookshop.features.book.api.service;

import com.bookshop.features.book.domain.model.Category;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category saveCategory(Category category);
}
