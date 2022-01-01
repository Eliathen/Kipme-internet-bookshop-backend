package com.bookshop.features.book.api.response;

import com.bookshop.features.book.domain.model.*;
import com.bookshop.features.opinion.api.response.OpinionResponse;
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

    private CategoryResponse category;

    private List<SubcategoryResponse> subcategories;

    private Integer amount;

}
