package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CategoryResponseWithoutSubcategories {


    private Integer id;

    private String name;

    @JsonCreator
    public CategoryResponseWithoutSubcategories(
            @JsonProperty("id") Integer id,
            @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }
}
