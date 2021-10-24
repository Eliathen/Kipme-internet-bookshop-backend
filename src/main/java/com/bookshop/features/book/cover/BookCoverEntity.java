package com.bookshop.features.book.cover;

import com.bookshop.features.book.entity.BookEntity;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Blob;

@EqualsAndHashCode
@Entity(name = "Book_Cover")
public class BookCoverEntity {

    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Getter
    @Setter
    @Lob
    private Blob bookCover;

    @Getter
    @Setter
    @ManyToOne
    private BookEntity book;
}
