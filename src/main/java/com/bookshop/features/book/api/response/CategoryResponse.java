package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.util.List;

@Builder
public record CategoryResponse(
        @JsonProperty(value = "id")
        Integer id,
        @JsonProperty(value = "name")
        String name,
        @JsonProperty(value = "subcategories")
        List<SubcategoryResponse> subcategories
) {

}
