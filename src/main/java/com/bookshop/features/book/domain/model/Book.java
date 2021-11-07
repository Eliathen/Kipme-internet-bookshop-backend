package com.bookshop.features.book.domain.model;

import com.bookshop.features.book.data.entity.*;
import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.opinion.domain.model.Opinion;
import com.bookshop.features.order.data.entity.OrderEntity;
import com.bookshop.features.order.domain.model.Order;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

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

    private Set<Opinion> bookOpinions = new HashSet<>();

    private Set<Author> bookAuthors = new HashSet<>();

    private Set<Publisher> bookPublishers = new HashSet<>();

    private Cover cover;

    private Language language;

    private Category category;

    private Set<Subcategory> subcategories;

}
