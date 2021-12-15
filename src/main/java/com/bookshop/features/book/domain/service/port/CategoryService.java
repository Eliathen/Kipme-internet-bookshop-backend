package com.bookshop.features.book.domain.service.port;

import com.bookshop.features.book.domain.model.Category;
import com.bookshop.features.book.domain.model.Subcategory;

import java.util.List;

public interface CategoryService {

    List<Category> getCategories();

    Category saveCategory(Category category);

    Subcategory saveSubcategory(int categoryId, Subcategory subcategory);

    Category getCategory(Integer id);
}
