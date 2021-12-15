package com.bookshop.features.book.data.entity;


import com.bookshop.features.magazine.data.entity.MagazineStateEntity;
import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.order.data.entity.OrderEntity;
import com.bookshop.features.order.data.entity.OrderPositionEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.List;

@Getter
@Builder
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true)
    private String isbn;

    private Integer publishedYear;

    private String description;

    private Integer quantity;

    private BigDecimal price;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "book_orders",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private List<OrderEntity> bookOrders = new LinkedList<>();

    @ManyToMany(
            cascade = {CascadeType.MERGE}
    )
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> bookAuthors = new LinkedList<>();

    @ManyToMany
    @JoinTable(
            name = "book_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private List<PublisherEntity> bookPublishers = new LinkedList<>();

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OpinionEntity> opinions = new LinkedList<>();

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id")
    private CoverEntity cover;

    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;


    @ManyToMany
    @JoinTable(
            name = "book_subcategory",
            joinColumns = @JoinColumn(name = "subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<SubcategoryEntity> subcategories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderedBook")
    private List<OrderPositionEntity> orderPositions;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="magazine_state_id", referencedColumnName = "id")
    private MagazineStateEntity magazineState;
}
