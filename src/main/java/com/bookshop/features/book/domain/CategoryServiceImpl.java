package com.bookshop.features.book.domain;

import com.bookshop.features.book.api.service.CategoryService;
import com.bookshop.features.book.domain.model.Category;
import com.bookshop.features.book.domain.model.Subcategory;
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

    @Override
    public Subcategory saveSubcategory(int categoryId, Subcategory subcategory) {
        Category category = categoryRepository.getCategory(categoryId);
        category.getSubcategories().add(subcategory);
        return categoryRepository.saveCategory(category).getSubcategories().stream().filter(sub -> sub.getName().equals(subcategory.getName())).findFirst().get();
    }
}
