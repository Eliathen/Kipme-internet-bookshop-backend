package com.bookshop.features.book.domain;


import com.bookshop.features.book.domain.model.Category;

import java.util.List;

public interface CategoryRepository {

    List<Category> getCategories();
}
