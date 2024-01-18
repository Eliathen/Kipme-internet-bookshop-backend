package com.bookshop.features.book.data.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "LANGUAGE")
@Getter
@Setter
public class LanguageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "language")
    private Set<BookEntity> books = Collections.emptySet();

    public void addBook(BookEntity bookEntity) {
        if (books == null) books = new HashSet<>();
        books.add(bookEntity);
    }
}
