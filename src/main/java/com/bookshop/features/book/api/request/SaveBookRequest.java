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
            @JsonProperty("title") String title,
            @JsonProperty("title") String isbn,
            @JsonProperty("published_year") Integer publishedYear,
            @JsonProperty("description") String description,
            @JsonProperty("quantity") Integer quantity,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("authors") List<AuthorRequest> bookAuthors,
            @JsonProperty("publishers_ids") Set<Integer> bookPublishersIds,
            @JsonProperty("language_id") Integer languageId,
            @JsonProperty("category_id") Integer categoryId,
            @JsonProperty("subcategories_ids") List<Integer> subcategoriesIds
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
