package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class BookResponse {

    private Long id;

    private String title;

    private String isbn;

    private Integer publishedYear;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private List<OpinionResponse> bookOpinions;

    private List<AuthorResponse> bookAuthors;

    private List<PublisherResponse> bookPublishers;

    private LanguageResponse language;

    private CategoryResponseWithoutSubcategories category;

    private List<SubcategoryResponse> subcategories;

    boolean isFavorite;

    private Double rating;

    @JsonCreator
    public BookResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("isbn") String isbn,
            @JsonProperty("published_year") Integer publishedYear,
            @JsonProperty("description") String description,
            @JsonProperty("quantity") Integer quantity,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("opinions") List<OpinionResponse> bookOpinions,
            @JsonProperty("authors") List<AuthorResponse> bookAuthors,
            @JsonProperty("publishers") List<PublisherResponse> bookPublishers,
            @JsonProperty("language") LanguageResponse language,
            @JsonProperty("category") CategoryResponseWithoutSubcategories category,
            @JsonProperty("subcategories") List<SubcategoryResponse> subcategories,
            @JsonProperty("is_favorite") boolean isFavorite,
            @JsonProperty("rating") Double rating) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.publishedYear = publishedYear;
        this.description = description;
        this.quantity = quantity;
        this.price = price;
        this.bookOpinions = bookOpinions;
        this.bookAuthors = bookAuthors;
        this.bookPublishers = bookPublishers;
        this.language = language;
        this.category = category;
        this.subcategories = subcategories;
        this.isFavorite = isFavorite;
        this.rating = rating;
    }
}
