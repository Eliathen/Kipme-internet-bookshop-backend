package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record CategoryResponseWithoutSubcategories(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("name")
        String name
) {

}
