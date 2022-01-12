package com.bookshop.features.book.api.response;

import com.bookshop.features.book.domain.model.Subcategory;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Collections;
import java.util.List;

@Getter
@Setter
@Builder
public class CategoryResponse {


    private Integer id;

    private String name;

    private List<SubcategoryResponse> subcategories = Collections.emptyList();

}
