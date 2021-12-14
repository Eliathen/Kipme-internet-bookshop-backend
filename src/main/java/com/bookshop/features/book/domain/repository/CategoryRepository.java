package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.domain.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getCategories();

    Category saveCategory(Category category);

    Category getCategory(int id);
}
