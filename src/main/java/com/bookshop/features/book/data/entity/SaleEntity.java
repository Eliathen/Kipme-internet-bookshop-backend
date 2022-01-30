package com.bookshop.features.book.data.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(name = "SALE_UNIT")
    @Enumerated(value = EnumType.STRING)
    private SALE_UNIT saleUnit;

    @JoinTable(name = "BOOK_SALE",
            joinColumns = {@JoinColumn(name = "SALE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "BOOK_ID")}
    )
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BookEntity> books;

    public void addBook(BookEntity book) {
        if (books == null) {
            books = new ArrayList<>(List.of(book));
            return;
        }
        books.add(book);
    }

    public List<BookEntity> getAvailableBooks() {
        return books.stream().filter(BookEntity::getIsAvailable).collect(Collectors.toList());
    }
}
