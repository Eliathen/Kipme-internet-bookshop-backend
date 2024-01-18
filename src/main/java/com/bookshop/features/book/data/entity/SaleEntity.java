package com.bookshop.features.book.data.entity;


import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SALE")
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal value;

    @Column(name = "IS_ACTIVE")
    private boolean isActive;

    @Column(name = "CREATED_AT")
    private LocalDateTime startAt;

    @Column(name = "END_AT")
    private LocalDateTime endAt;

    @Column(name = "SALE_UNIT", columnDefinition = "VARCHAR")
    @Enumerated(value = EnumType.STRING)
    private SaleUnit saleUnit;

    @JoinTable(name = "BOOK_SALE",
            joinColumns = {@JoinColumn(name = "SALE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")}
    )
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookEntity> books;

    public void addBook(BookEntity book) {
        if (books == null) {
            books = new ArrayList<>();
        }
        books.add(book);
    }

    public List<BookEntity> getAvailableBooks() {
        return books.stream().filter(BookEntity::isAvailable).toList();
    }

    public BigDecimal getDiscountedPrice(BigDecimal price) {
        if (saleUnit == SaleUnit.VALUE) {
            return price.subtract(value);
        }
        var saleValue = price.multiply(value.divide(BigDecimal.valueOf(100.00), RoundingMode.HALF_EVEN));
        return price.subtract(saleValue);
    }
}
