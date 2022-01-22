package com.bookshop.features.book.data;

import com.bookshop.features.book.data.entity.CategoryEntity;
import com.bookshop.features.book.data.entity.SubcategoryEntity;
import com.bookshop.features.book.data.jpa.CategoryJpaRepository;
import com.bookshop.features.book.data.jpa.SubcategoryJpaRepository;
import com.bookshop.features.book.domain.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Repository
public class CategoryRepositoryImpl implements CategoryRepository {

    private final CategoryJpaRepository categoryJpaRepository;
    private final SubcategoryJpaRepository subcategoryJpaRepository;

    @Override
    public List<CategoryEntity> getCategories() {
        return categoryJpaRepository.findAll();
    }

    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryJpaRepository.saveAndFlush(category);
    }

    @Override
    public Optional<CategoryEntity> getCategory(int id) {
        return categoryJpaRepository.findById(id);
    }

    @Override
    public Optional<SubcategoryEntity> getSubcategoryById(Integer subcategoryId) {
        return subcategoryJpaRepository.findById(subcategoryId);
    }
}