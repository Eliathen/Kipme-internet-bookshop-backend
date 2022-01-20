package com.bookshop.features.book.api.controller;


import com.bookshop.features.book.api.request.SaveCategoryRequest;
import com.bookshop.features.book.api.request.SaveSubcategoryRequest;
import com.bookshop.features.book.api.response.CategoryResponse;
import com.bookshop.features.book.api.response.SubcategoryResponse;
import com.bookshop.features.book.domain.service.port.CategoryService;
import com.bookshop.features.book.mapper.CategoryMapper;
import com.bookshop.features.book.mapper.SubcategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<List<CategoryResponse>> getCategories() {
        return new ResponseEntity<>(
                categoryService.getCategories()
                        .stream().map(CategoryMapper::mapCategoryToCategoryResponse)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<CategoryResponse> saveCategory(@RequestBody SaveCategoryRequest request) {
        return new ResponseEntity<>(
                CategoryMapper.mapCategoryToCategoryResponse(categoryService.saveCategory(CategoryMapper.mapSaveCategoryRequestToCategory(request))
                ), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getCategory(@PathVariable Integer id){
        return ResponseEntity.ok(
                CategoryMapper.mapCategoryToCategoryResponse(categoryService.getCategory(id))
        );
    }

    @Transactional
    @PostMapping("/{id}/subcategories")
    public ResponseEntity<SubcategoryResponse> saveSubcategory(@PathVariable int id, @RequestBody SaveSubcategoryRequest request){
        return new ResponseEntity<>(
                SubcategoryMapper.mapSubcategoryToSubcategoryResponse(categoryService.saveSubcategory(id, SubcategoryMapper.mapSaveSubcategoryRequestToSubcategory(request)))
        , HttpStatus.CREATED);
    }

}
