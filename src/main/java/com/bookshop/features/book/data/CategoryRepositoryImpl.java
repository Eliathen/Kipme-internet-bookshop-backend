package com.bookshop.features.book.data;

import com.bookshop.features.book.data.jpa.CategoryJpaRepository;
import com.bookshop.features.book.domain.CategoryRepository;
import com.bookshop.features.book.domain.model.Category;
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
        return categoryJpaRepository.findAll().stream().map(CategoryMapper::mapToCategory).collect(Collectors.toList());
    }
}
