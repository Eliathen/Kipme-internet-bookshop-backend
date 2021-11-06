package com.bookshop.features.book.data.entity;


import com.bookshop.features.opinion.data.entity.OpinionEntity;
import com.bookshop.features.order.data.entity.OrderEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity(name = "Book")
public class BookEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    private String title;

    @Getter
    @Setter
    @Column(unique = true)
    private String isbn;

    @Getter
    @Setter
    private Integer publishedYear;

    @Getter
    @Setter
    private String description;

    @Getter
    @Setter
    private Integer quantity;

    @Getter
    @Setter
    private BigDecimal price;

    @Getter
    @Setter
    @OneToMany
    @JoinColumn(name = "book_id")
    private Set<OpinionEntity> bookOpinions = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_orders",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "order_id")
    )
    private Set<OrderEntity> bookOrders = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<AuthorEntity> bookAuthors = new HashSet<>();

    @Getter
    @Setter
    @ManyToMany(
            cascade = {CascadeType.MERGE, CascadeType.PERSIST}
    )
    @JoinTable(
            name = "book_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private Set<PublisherEntity> bookPublishers = new HashSet<>();

    @Getter
    @Setter
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true, cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private Set<OpinionEntity> opinions = new HashSet<>();

    @Getter
    @Setter
    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "cover_id")
    private CoverEntity cover;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "language_id", nullable = false)
    private LanguageEntity language;

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;


    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "book_subcategory",
            joinColumns = @JoinColumn(name = "subcategory_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<SubcategoryEntity> subcategories;
}
