package com.bookshop.features.book.entity;


import com.bookshop.features.author.entity.AuthorEntity;
import com.bookshop.features.opinion.entity.OpinionEntity;
import com.bookshop.features.order.entity.OrderEntity;
import com.bookshop.features.publisher.entity.PublisherEntity;
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
    @OneToMany
    @JoinColumn(name = "cover_id")
    private Set<BookCoverEntity> booksWithCovers = new HashSet<>();

    public BookEntity() {
    }

    @Getter
    @Setter
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private CategoryEntity category;

    @Override
    public String toString() {
        return "BookEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publishedYear=" + publishedYear +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}
