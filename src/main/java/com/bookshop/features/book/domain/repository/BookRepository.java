package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.data.entity.BookEntity;
import com.bookshop.features.book.data.entity.OpinionEntity;

import java.util.List;
import java.util.Optional;

public interface BookRepository {
    BookEntity saveBook(BookEntity book);

    Optional<BookEntity> getBookById(Long id);

    void saveOpinion(OpinionEntity opinion);

    List<BookEntity> findByTitleOrAuthorNameOrAuthorSurname(String query);

    Optional<BookEntity> getBookByIsbn(String isbn);
}
