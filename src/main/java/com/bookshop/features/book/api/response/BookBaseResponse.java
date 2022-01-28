package com.bookshop.features.book.api.response;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Builder
public class BookBaseResponse {

    private Long id;

    private String title;

    private BigDecimal price;

    private BigDecimal salePrice;

    private List<AuthorResponse> bookAuthors;

    boolean isFavorite;

    private Double rating;

    private Integer numberOfRatings;

    @JsonCreator
    public BookBaseResponse(
            @JsonProperty("id") Long id,
            @JsonProperty("title") String title,
            @JsonProperty("price") BigDecimal price,
            @JsonProperty("sale_price") BigDecimal salePrice,
            @JsonProperty("authors") List<AuthorResponse> bookAuthors,
            @JsonProperty("is_favorite") boolean isFavorite,
            @JsonProperty("rating") Double rating,
            @JsonProperty("number_of_ratings") Integer numberOfRatings
    ) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.salePrice = salePrice;
        this.bookAuthors = bookAuthors;
        this.isFavorite = isFavorite;
        this.rating = rating;
        this.numberOfRatings = numberOfRatings;
    }

}
