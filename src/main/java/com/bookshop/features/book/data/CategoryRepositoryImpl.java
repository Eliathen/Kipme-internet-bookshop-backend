package com.bookshop.features.book.data;

import com.bookshop.features.book.data.jpa.CategoryJpaRepository;
import com.bookshop.features.book.domain.model.Category;
import com.bookshop.features.book.domain.repository.CategoryRepository;
import com.bookshop.features.book.exception.CategoryNotFound;
import com.bookshop.features.book.mapper.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;

    @Override
    public List<Category> getCategories() {
        return categoryJpaRepository.findAll().stream().map(CategoryMapper::mapCategoryEntityToCategory).collect(Collectors.toList());
    }

    @Override
    public Category saveCategory(Category category) {
        return CategoryMapper.mapCategoryEntityToCategory(categoryJpaRepository.saveAndFlush(CategoryMapper.mapCategoryToCategoryEntity(category)));
    }

    @Override
    public Category getCategory(int id) {
        return CategoryMapper.mapCategoryEntityToCategory(categoryJpaRepository.findById(id).orElseThrow(() -> new CategoryNotFound(id)));
    }
}
