package com.bookshop.features.book.data.entity;


import com.bookshop.features.order.data.entity.OrderEntity;
import com.bookshop.features.order.data.entity.OrderPositionEntity;
import com.bookshop.features.user.data.entity.UserEntity;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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

    @Column(name = "PUBLISHED_YEAR")
    private Integer publishedYear;

    @Column(length = 65535, columnDefinition = "text")
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
    private List<OrderEntity> bookOrders;

    @ManyToMany(
            cascade = {CascadeType.MERGE}
    )
    @JoinTable(
            name = "book_authors",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private List<AuthorEntity> bookAuthors;

    @ManyToMany
    @JoinTable(
            name = "book_publishers",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "publisher_id")
    )
    private List<PublisherEntity> bookPublishers;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<OpinionEntity> opinions;

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

    @ManyToMany(mappedBy = "favouriteBooks", cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
    private List<UserEntity> users;

    @Transient
    private boolean isFavorite = false;

    public Double getAvgRating() {
        return getOpinions() != null ? getOpinions().stream().mapToDouble(OpinionEntity::getRating).average().orElse(0.0) : (0.0);
    }

    @ManyToMany(mappedBy = "books", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST})
    private List<SaleEntity> sales;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookEntity that = (BookEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public BigDecimal getSalePrice() {
        var currentPrice = price;
        var sales = (getSales() != null) ? getSales().stream()
                .peek(sale -> sale.setActive(true))
                .filter(SaleEntity::isActive)
                .collect(Collectors.toList()) : new ArrayList<SaleEntity>();
        if (!sales.isEmpty()) {
            var sale = resolveSale(currentPrice, sales);
            currentPrice = resolvePrice(currentPrice, sale.getValue(), sale.getSaleUnit());
        }
        return currentPrice.setScale(2, RoundingMode.HALF_EVEN);
    }

    private SaleEntity resolveSale(BigDecimal currentPrice, List<SaleEntity> sales) {
        var currentSale = sales.get(0);
        var theLowerPrice = resolvePrice(currentPrice, currentSale.getValue(), currentSale.getSaleUnit());
        for (SaleEntity sale : sales) {
            BigDecimal newPrice = resolvePrice(currentPrice, sale.getValue(), sale.getSaleUnit());
            if (theLowerPrice.compareTo(newPrice) > 0) {
                currentPrice = newPrice;
                currentSale = sale;
            }
        }
        return currentSale;
    }

    private BigDecimal resolvePrice(BigDecimal price, BigDecimal value, SALE_UNIT type) {
        if (type == SALE_UNIT.VALUE) {
            return price.subtract(value);
        }
        var saleValue = price.multiply(value.divide(BigDecimal.valueOf(100.00), RoundingMode.HALF_EVEN));
        return price.subtract(saleValue);
    }
}
