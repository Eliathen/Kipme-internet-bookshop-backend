package com.bookshop.features.book.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity(name = "PUBLISHER")
public class PublisherEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String publisherName;

    private String publisherCity;

    @ManyToMany(mappedBy = "bookPublishers")
    private Set<BookEntity> publisherBooks = new HashSet<>();

    void addBook(BookEntity book) {
        this.publisherBooks.add(book);
    }

}
