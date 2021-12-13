package com.bookshop.features.book.domain;

import com.bookshop.features.book.api.service.CategoryService;
import com.bookshop.features.book.domain.model.Category;
import com.bookshop.features.book.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getCategories() {
        return categoryRepository.getCategories();
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.saveCategory(category);
    }
}
