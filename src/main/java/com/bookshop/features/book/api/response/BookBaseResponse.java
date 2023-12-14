package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record BookBaseResponse(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "title")
        String title,
        @JsonProperty(value = "price")
        BigDecimal price,
        @JsonProperty(value = "sale_price")
        BigDecimal salePrice,
        @JsonProperty(value = "authors")
        List<AuthorResponse> bookAuthors,
        @JsonProperty("is_favorite")
        boolean isFavorite,
        @JsonProperty(value = "rating")
        Double rating,
        @JsonProperty("number_of_ratings")
        Integer numberOfRatings
) {
}
