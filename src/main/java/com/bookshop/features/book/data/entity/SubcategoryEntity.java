package com.bookshop.features.book.data.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "SUBCATEGORY")
public class SubcategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @ManyToOne
    private CategoryEntity category;

    @ManyToMany(mappedBy = "subcategories")
    private List<BookEntity> books;

    public List<BookEntity> getAvailableBooks() {
        return books.stream().filter(BookEntity::isFavorite).collect(Collectors.toList());
    }

    public void addBook(BookEntity bookEntity) {
        if (books == null) books = new ArrayList<>();
        books.add(bookEntity);
    }
}
