package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class SaveBookRequest {

    @NotBlank(message = "Provide title")
    private String title;

    @NotBlank(message = "Provide isbn")
    private String isbn;

    @NotNull(message = "Provide published year")
    @Positive(message = "Invalid published year")
    private Integer publishedYear;

    @NotBlank(message = "Provide description")
    private String description;

    @NotNull(message = "Provide quantity")
    @Min(value = 0, message = "Quantity should be greater or equal 0")
    private Integer quantity;

    @DecimalMin(value = "0.0", message = "Price should be greater or equal 0.00")
    private BigDecimal price;

    @Size(min = 1, message = "Provide authors")
    @NotNull(message = "Provide at least one author")
    private List<AuthorRequest> bookAuthors;

    @Size(min = 1, message = "Provide publishers' ids")
    @NotNull(message = "Provide at least one publisher")
    private Set<Integer> bookPublishersIds;

    @NotNull(message = "Provide language id")
    private Integer languageId;

    @NotNull(message = "Provide category id")
    private Integer categoryId;

    @Size(min = 1, message = "Provide subcategories' ids")
    @NotNull(message = "Provide at least one subcategory")
    private List<Integer> subcategoriesIds;

    @JsonCreator
    public SaveBookRequest(
            @JsonProperty(value = "title", required = true) String title,
            @JsonProperty(value = "isbn", required = true) String isbn,
            @JsonProperty(value = "published_year", required = true) Integer publishedYear,
            @JsonProperty(value = "description", required = true) String description,
            @JsonProperty(value = "quantity", required = true) Integer quantity,
            @JsonProperty(value = "price", required = true) BigDecimal price,
            @JsonProperty(value = "authors", required = true) List<AuthorRequest> bookAuthors,
            @JsonProperty(value = "publishers_ids", required = true) Set<Integer> bookPublishersIds,
            @JsonProperty(value = "language_id", required = true) Integer languageId,
            @JsonProperty(value = "category_id", required = true) Integer categoryId,
            @JsonProperty(value = "subcategories_ids", required = true) List<Integer> subcategoriesIds
    ) {
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.bookAuthors = bookAuthors;
        this.bookPublishersIds = bookPublishersIds;
        this.languageId = languageId;
        this.categoryId = categoryId;
        this.subcategoriesIds = subcategoriesIds;
    }
}
