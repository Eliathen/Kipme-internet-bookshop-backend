package com.bookshop.features.book.domain.repository;


import com.bookshop.features.book.domain.model.Book;

public interface BookRepository {
    Book saveBook(Book book);

}
