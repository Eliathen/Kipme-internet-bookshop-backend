package com.bookshop.features.book.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "AUTHOR")
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String surname;

    @ManyToMany(mappedBy = "bookAuthors")
    private List<BookEntity> authorsBooks;

    public void addBook(BookEntity book) {
        if (authorsBooks == null) {
            authorsBooks = new ArrayList<>();
        }
        authorsBooks.add(book);
    }
}
