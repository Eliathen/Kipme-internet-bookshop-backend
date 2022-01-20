package com.bookshop.features.book.domain.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

@ToString
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Book {

    private Long id;

    private String title;

    private String isbn;

    private Integer publishedYear;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    private List<Opinion> bookOpinions = new LinkedList<>();

    private List<Author> bookAuthors = new LinkedList<>();

    private List<Publisher> bookPublishers = new LinkedList<>();

    private Cover cover;

    private Language language;

    private Category category;

    private List<Subcategory> subcategories = new LinkedList<>();

    private Double avgRating = 0.0;

}
