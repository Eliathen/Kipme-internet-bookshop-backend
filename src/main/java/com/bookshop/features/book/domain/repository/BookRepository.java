package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.domain.model.Book;
import com.bookshop.features.opinion.domain.model.Opinion;

public interface BookRepository {
    Book saveBook(Book book);

    Book getBookById(Long id);

    void saveOpinion(Long bookId, Opinion opinion);
}
