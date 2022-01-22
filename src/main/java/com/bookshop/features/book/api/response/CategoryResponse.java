package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonCreator
    public CategoryResponse(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name,
            @JsonProperty("subcategories") List<SubcategoryResponse> subcategories) {
        this.id = id;
        this.name = name;
        this.subcategories = subcategories;
    }
}
