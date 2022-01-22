package com.bookshop.features.book.domain.service.adapter;

import com.bookshop.features.book.api.request.SaveSubcategoryRequest;
import com.bookshop.features.book.data.entity.CategoryEntity;
import com.bookshop.features.book.data.entity.SubcategoryEntity;
import com.bookshop.features.book.domain.repository.CategoryRepository;
import com.bookshop.features.book.domain.service.port.CategoryService;
import com.bookshop.features.book.exception.CategoryNotFound;
import com.bookshop.features.book.exception.SubcategoryNotFoundException;
import com.bookshop.features.book.mapper.SubcategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryEntity> getCategories() {
        return categoryRepository.getCategories();
    }

    @Override
    public CategoryEntity saveCategory(CategoryEntity category) {
        return categoryRepository.saveCategory(category);
    }

    @Override
    public SubcategoryEntity saveSubcategory(int categoryId, SaveSubcategoryRequest request) {
        CategoryEntity category = categoryRepository.getCategory(categoryId).orElseThrow(() -> new CategoryNotFound(categoryId));
        category.getSubcategories().add(SubcategoryMapper.mapToSubcategoryEntity(request));
        return categoryRepository.saveCategory(category).getSubcategories().stream().filter(sub -> sub.getName().equals(request.getName())).findFirst().orElseThrow(AssertionError::new);
    }

    @Override
    public CategoryEntity getCategory(Integer id) {
        return categoryRepository.getCategory(id).orElseThrow(() -> new CategoryNotFound(id));
    }

    @Override
    public SubcategoryEntity getSubcategoryById(Integer subcategoryId) {
        return categoryRepository.getSubcategoryById(subcategoryId).orElseThrow(() -> new SubcategoryNotFoundException(subcategoryId));
    }

}
