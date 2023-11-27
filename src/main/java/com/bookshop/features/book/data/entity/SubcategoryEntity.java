package com.bookshop.features.book.data.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
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
}
