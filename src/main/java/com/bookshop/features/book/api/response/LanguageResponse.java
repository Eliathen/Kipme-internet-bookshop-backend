package com.bookshop.features.book.api.response;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record LanguageResponse(
        @JsonProperty("id")
        Integer id,
        @JsonProperty("name")
        String name
) {
}
