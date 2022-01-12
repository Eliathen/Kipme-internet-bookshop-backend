package com.bookshop.features.book.api.request;

import com.bookshop.features.book.domain.model.*;
import com.bookshop.features.opinion.domain.model.Opinion;
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

    private Integer amount;

}
