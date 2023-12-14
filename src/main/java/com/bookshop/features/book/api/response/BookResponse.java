package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;

@Builder
public record BookResponse(
        @JsonProperty(value = "id")
        Long id,
        @JsonProperty(value = "title")
        String title,
        @JsonProperty(value = "isbn")
        String isbn,
        @JsonProperty(value = "published_year")
        Integer publishedYear,
        @JsonProperty(value = "description")
        String description,
        @JsonProperty(value = "quantity")
        Integer quantity,
        @JsonProperty(value = "price")
        BigDecimal price,
        @JsonProperty(value = "sale_price")
        BigDecimal salePrice,
        @JsonProperty(value = "opinions")
        List<OpinionResponse> bookOpinions,
        @JsonProperty(value = "authors")
        List<AuthorResponse> bookAuthors,
        @JsonProperty(value = "publishers")
        List<PublisherResponse> bookPublishers,
        @JsonProperty(value = "language")
        LanguageResponse language,
        @JsonProperty(value = "category")
        CategoryResponseWithoutSubcategories category,
        @JsonProperty(value = "subcategories")
        List<SubcategoryResponse> subcategories,
        @JsonProperty(value = "favorite")
        boolean isFavorite,
        @JsonProperty(value = "rating")
        Double rating,
        @JsonProperty(value = "number_of_ratings")
        Integer numberOfRatings
) {
}
