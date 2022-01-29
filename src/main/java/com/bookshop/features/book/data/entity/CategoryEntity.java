package com.bookshop.features.book.data.entity;


import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Category")
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
        return books.stream().filter(BookEntity::getIsAvailable).collect(Collectors.toList());
    }
}
