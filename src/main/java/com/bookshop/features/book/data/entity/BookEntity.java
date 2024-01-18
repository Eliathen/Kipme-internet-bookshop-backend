package com.bookshop.features.book.data.entity;


import com.bookshop.features.order.data.entity.OrderEntity;
import com.bookshop.features.order.data.entity.OrderPositionEntity;
import com.bookshop.features.user.data.entity.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "BOOK")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(unique = true)
    private String isbn;

    @Column(name = "PUBLISHED_YEAR")
    private Integer publishedYear;

    @Column(length = 65535, columnDefinition = "text")
    private String description;

    private Integer quantity;

    private BigDecimal price;

    @Column(name = "ADDED_AT", nullable = false)
    private LocalDateTime addedAt;

    @Column(name = "IS_AVAILABLE", nullable = false)
    private Boolean isAvailable;

    @ManyToMany(
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "BOOK_ORDERS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "ORDER_ID")
    )
    private List<OrderEntity> bookOrders;

    @ManyToMany(
            cascade = {CascadeType.MERGE}
    )
    @JoinTable(
            name = "BOOK_AUTHORS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "AUTHOR_ID")
    )
    private List<AuthorEntity> bookAuthors;

    @ManyToMany
    @JoinTable(
            name = "BOOK_PUBLISHERS",
            joinColumns = @JoinColumn(name = "BOOK_ID"),
            inverseJoinColumns = @JoinColumn(name = "PUBLISHER_ID")
    )
    private List<PublisherEntity> bookPublishers;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OpinionEntity> opinions;

    @OneToOne(orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinColumn(name = "COVER_ID")
    private CoverEntity cover;

    @ManyToOne
    @JoinColumn(name = "LANGUAGE_ID", nullable = false)
    private LanguageEntity language;


    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryEntity category;


    @ManyToMany
    @JoinTable(
            name = "BOOK_SUBCATEGORY",
            joinColumns = @JoinColumn(name = "SUBCATEGORY_ID"),
            inverseJoinColumns = @JoinColumn(name = "BOOK_ID")
    )
    private List<SubcategoryEntity> subcategories;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "orderedBook")
    private List<OrderPositionEntity> orderPositions;

    @ManyToMany(mappedBy = "favouriteBooks", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @Transient
    private boolean isFavorite = false;

    public Double getAvgRating() {
        if (getOpinions() == null) return 0.0;
        return getOpinions().stream().mapToDouble(OpinionEntity::getRating).average().orElse(0.0);
    }

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<SaleEntity> sales;

    public BigDecimal getCurrentPrice() {
        var currentPrice = price;
        var currentSales = (getSales() != null) ? getSales().stream()
                .filter(SaleEntity::isActive)
                .toList() : new ArrayList<SaleEntity>();
        if (!currentSales.isEmpty()) {
            currentPrice = currentSales.stream().map(sale -> sale.getDiscountedPrice(price)).min(BigDecimal::compareTo).orElse(price);
        }
        return currentPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    public void addSubcategory(SubcategoryEntity subcategory) {
        if (subcategories == null) subcategories = new ArrayList<>();

        subcategories.add(subcategory);
        subcategory.addBook(this);
    }

    public void addPublisher(PublisherEntity publisher) {
        if (bookPublishers == null) {
            bookPublishers = new ArrayList<>();
        }
        bookPublishers.add(publisher);
    }

    public void changeCover(CoverEntity newCover) {
        cover = newCover;
    }

    public void changeLanguage(LanguageEntity newLanguage) {
        language = newLanguage;
        newLanguage.addBook(this);
    }

    public void setAvailable() {
        isAvailable = true;
    }
}
