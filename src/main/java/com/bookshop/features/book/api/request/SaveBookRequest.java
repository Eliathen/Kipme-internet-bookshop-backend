package com.bookshop.features.book.api.request;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    private String title;

    private String isbn;

    private Integer publishedYear;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private List<AuthorRequest> bookAuthors;

    private Set<Integer> bookPublishersIds;

    private Integer languageId;

    private Integer categoryId;

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
