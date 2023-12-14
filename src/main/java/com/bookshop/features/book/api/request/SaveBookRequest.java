package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

public record SaveBookRequest(

        @NotBlank(message = "Provide title")
        @JsonProperty(value = "title", required = true)
        String title,
        @NotBlank(message = "Provide isbn")
        @JsonProperty(value = "isbn", required = true)
        String isbn,
        @NotNull(message = "Provide published year")
        @Positive(message = "Invalid published year")
        @JsonProperty(value = "published_year", required = true)
        Integer publishedYear,
        @NotBlank(message = "Provide description")
        @JsonProperty(value = "description", required = true)
        String description,
        @NotNull(message = "Provide quantity")
        @Min(value = 0, message = "Quantity should be greater or equal 0")
        @JsonProperty(value = "quantity", required = true)
        Integer quantity,
        @DecimalMin(value = "0.0", message = "Price should be greater or equal 0.00")
        @JsonProperty(value = "price", required = true)
        BigDecimal price,
        @Size(min = 1, message = "Provide authors")
        @NotNull(message = "Provide at least one author")
        @JsonProperty(value = "authors", required = true)
        List<AuthorRequest> bookAuthors,
        @Size(min = 1, message = "Provide publishers' ids")
        @NotNull(message = "Provide at least one publisher")
        @JsonProperty(value = "publishers_ids", required = true)
        Set<Integer> bookPublishersIds,
        @NotNull(message = "Provide language id")
        @JsonProperty(value = "language_id", required = true)
        Integer languageId,
        @NotNull(message = "Provide category id")
        @JsonProperty(value = "category_id", required = true)
        Integer categoryId,
        @Size(min = 1, message = "Provide subcategories' ids")
        @NotNull(message = "Provide at least one subcategory")
        @JsonProperty(value = "subcategories_ids", required = true)
        List<Integer> subcategoriesIds) {

}
