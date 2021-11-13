package com.bookshop.features.book.api;


import com.bookshop.features.book.api.response.CategoryResponse;
import com.bookshop.features.book.mapper.CategoryMapper;
import com.bookshop.features.user.api.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @GetMapping
    private ResponseEntity<List<CategoryResponse>> getCategories() {
        return new ResponseEntity<>(
                categoryService.getCategories()
                        .stream().map(CategoryMapper::mapToCategoryResponse)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }
}
