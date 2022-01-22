package com.bookshop.features.book.api.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class CategoryResponse {


    private Integer id;

    private String name;

    private List<SubcategoryResponse> subcategories;

}
