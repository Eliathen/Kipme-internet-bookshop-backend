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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

    private Integer amount;



}
