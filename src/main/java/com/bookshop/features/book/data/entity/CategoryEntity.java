package com.bookshop.features.book.data.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "CATEGORY")
public class CategoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "CATEGORY_ID")
    private List<SubcategoryEntity> subcategories;

    @OneToMany(mappedBy = "category")
    private List<BookEntity> books;

    public List<BookEntity> getAvailableBooks() {
        return books.stream().filter(BookEntity::isAvailable).toList();
    }

    public void addSubcategory(SubcategoryEntity subcategory) {
        if (subcategories == null) {
            subcategories = new ArrayList<>();
        }
        if (subcategories.contains(subcategory)) return;

        subcategories.add(subcategory);
        subcategory.changeCategory(this);

    }

    public void addBook(BookEntity bookEntity) {
        if (books == null) books = new ArrayList<>();
        books.add(bookEntity);
    }

    public void removeBook(BookEntity bookEntity) {
        this.books.remove(bookEntity);
    }
}
